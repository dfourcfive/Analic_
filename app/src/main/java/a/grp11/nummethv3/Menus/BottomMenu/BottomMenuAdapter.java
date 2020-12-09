package a.grp11.nummethv3.Menus.BottomMenu;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import a.grp11.nummethv3.DataStructure.OperationBaseStructure.OperationBase;
import a.grp11.nummethv3.DataStructure.OperationBaseStructure.OperationStructure.Operation;
import a.grp11.nummethv3.DataStructure.OperationBaseStructure.OperationStructure.SubOperationStrcuture.SubOperations;
import a.grp11.nummethv3.DataStructure.OptionStructure.Options;
import a.grp11.nummethv3.Linking.FunctionAndroid.FunctionAndroid;
import a.grp11.nummethv3.Linking.FunctionAndroid.FunctionAndroidFragment;
import a.grp11.nummethv3.Linking.MatrixAndroid.MatrixAndroid;
import a.grp11.nummethv3.Linking.MatrixAndroid.MatrixAndroidFragment;
import a.grp11.nummethv3.PartData.PartIntegrale;
import a.grp11.nummethv3.ListFragment.ElemAndroidList.ElemAndroidListFragment;


import a.grp11.nummethv3.DataStructure.Part;


import a.grp11.nummethv3.Menus.BottomMenu.Animation.BottomMenuAnimation;
import a.grp11.nummethv3.PartData.PartMatrice;
import a.grp11.nummethv3.PartData.PartRoot;
import a.grp11.nummethv3.PartData.PartsConfig;
import a.grp11.nummethv3.R;
import app.matrice.exceptions.MatrixMultiplicationException;
import app.matrice.exceptions.MatrixNullException;
import app.matrice.exceptions.NotInversibleMatrixException;
import app.matrice.exceptions.NotSquareMatrixException;
import app.matrice.exceptions.OperationFormException;

public class BottomMenuAdapter {
    private final LinearLayout mBottomMenuContainer;
    private BottomMenuHeaderAdapter mBottomHeaderAdapter;
    private BottomMenuSubOperationAdapter mSubOperationAdapter;
    private BottomMenuOptionsAdapter mBottomOptionsAdapter;
    private Operation mOperation;
    private Operation mLastOperationUsed;
    private Context mContext;

    public BottomMenuAdapter(AppCompatActivity activity , Operation initOperation , Options options) {

        mContext = activity;
        mOperation = null;
        mLastOperationUsed = null;
        mBottomMenuContainer = activity.findViewById(R.id.bottom_menu_container);
        mBottomHeaderAdapter = new BottomMenuHeaderAdapter(activity,initOperation);
        mSubOperationAdapter = BottomMenuSubOperationAdapter.initOperations(activity,initOperation.getSubOperations());
        mBottomOptionsAdapter = BottomMenuOptionsAdapter.initOperations(activity,options);
        upDateButtonOperation(initOperation);

    }

    public static BottomMenuAdapter initBottomMenuAdapter(AppCompatActivity activity,int partId, int operationSelected){
        Part part = PartsConfig.getParts().getPartData(partId);
        return new BottomMenuAdapter(activity,
                part.getPartOperations().getOperation(operationSelected),
                part.getPartOptions());
    }

    public void upDate(Fragment partFragment, Part selectedPartData) {
        if(selectedPartData instanceof PartMatrice)
            this.upDateMatrix((ElemAndroidListFragment) partFragment);
        else
            this.upDateFunction((FunctionAndroidFragment) partFragment,selectedPartData);
    }

    private void upDateMatrix(ElemAndroidListFragment fragment) {
        OperationBase operationBase;
        if(mOperation.getBg_colorRes() != R.color.part_matrice_color) return;

        if ((mOperation.haveSubOperation())) {
            operationBase = mSubOperationAdapter.getSelectedSubOperation();
            System.out.println("do subOp num: "+mSubOperationAdapter.getSelectedSubOperations());

        }else{
            operationBase = mOperation;
            System.out.println("do mianOp");
        }

        try {
            MatrixAndroid currentMatrixAndroid =  ((MatrixAndroidFragment)fragment.getCurrentElemAndroidFragment()).getMatrixAndroid();
            MatrixAndroid result;


            getCurrentOperationName(mOperation);

            upDateState(operationBase,fragment);

            result = (MatrixAndroid)currentMatrixAndroid.doElemOperation(operationBase);
            System.out.println(result);
            fragment.upDateAddPage(result);

        } catch (MatrixNullException e) {
            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (NotInversibleMatrixException e) {
            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (MatrixMultiplicationException e) {
            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (OperationFormException e) {
            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (NotSquareMatrixException e) {

            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (NullPointerException e){
            e.printStackTrace();
            Toast.makeText(mContext,"Select a subOperation" ,Toast.LENGTH_SHORT).show();
        }
    }



    private void upDateFunction(FunctionAndroidFragment fragment, Part selectedPartData){

        FunctionAndroid result;

        getCurrentOperationName(mOperation);

        //System.out.println("original" +  mOperation.getParams());
        //fragment.updateParams(mOperation);
        //System.out.println("updated" +  mOperation.getParams());

        //upDateState(mOperation,fragment);

        System.out.println("selected part "+selectedPartData);

        if(selectedPartData instanceof PartIntegrale)
             result = fragment.getElemAndroid().doIntegralOperation(mOperation, fragment.getResult());
        else if(selectedPartData instanceof PartRoot)
             result = fragment.getElemAndroid().doRootOperation(mOperation,fragment.getResult());
        else result = null;

        fragment.upDatePage(result);
    }

    private void upDateState(OperationBase newOperation , ElemAndroidListFragment fragment){
        if(!newOperation.isUnary() && !isWaiting()) {
            setWaiting(true);
            fragment.upDateSavePage();
        }else if(!newOperation.isUnary()){
            setWaiting(false);
        }

    }

    public void upDateButtonOperation(Operation newOperation) {


        mLastOperationUsed = mOperation;
        mOperation = newOperation;
        System.out.println("update button to :");
        getCurrentOperationName(mOperation);
        mBottomHeaderAdapter.upDateButtonOperation(newOperation);

        if(mOperation.haveSubOperation()){
            upDateSubOperations(newOperation.getSubOperations());
            setSubOperationEvents();
            clickOnExpander();
        }
    }



    public void upDateSubOperations(SubOperations subOperations){
        mSubOperationAdapter.update(subOperations);
    }

    private void setSubOperationEvents(){
        for (int i = 0; i <mBottomMenuContainer.getChildCount() ; i++) {
            mSubOperationAdapter.getSubOperationContainer().getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSubOperationAdapter.onCLick(v);
                    mSubOperationAdapter.setVisibilty(false);
                    System.out.println("sub operation clicked");
                    if(!isWaiting()){
                        mBottomHeaderAdapter.upDateButtonOperation(mSubOperationAdapter.getSelectedSubOperation());
                    }else {
                        clickOnButton();
                        restoreSave(mOperation);
                    }
                    expand();
                }
            });
        }

    }

    public FloatingActionButton getButtonOperation() {
        return mBottomHeaderAdapter.getButtonOperation();
    }

    public BottomMenuHeaderAdapter getBottomHeaderAdapter() {
        return mBottomHeaderAdapter;
    }

    public BottomMenuSubOperationAdapter getSubOperationMenuAdapter() {
        return mSubOperationAdapter;
    }

    public LinearLayout getBottomMenuContainer() {
        return mBottomMenuContainer;
    }

    public void clickOnButton() {
        if(mBottomHeaderAdapter.getButtonOperation() != null){
            mBottomHeaderAdapter.getButtonOperation().callOnClick();
        }
    }

    public void clickOnExpander(){
        mBottomHeaderAdapter.getExpanderButton().callOnClick();
    }

    public boolean isExpanded(){
        return mBottomMenuContainer.getTranslationY() == 0 ;
    }
    public void collaps(){
        BottomMenuAnimation.slide(mBottomMenuContainer,mBottomOptionsAdapter.getOpionsTab().getHeight(),250);
        mBottomHeaderAdapter.getExpanderButton().setCircleBackgroundColorResource(R.color.dark);
        mBottomHeaderAdapter.getExpanderButton().setRotation(0);
    }
    public void expand(){
        BottomMenuAnimation.slide(mBottomMenuContainer,0,250);
        mBottomHeaderAdapter.getExpanderButton().setCircleBackgroundColorResource(R.color.success);
        mBottomHeaderAdapter.getExpanderButton().setRotation(180);
    }
    public boolean hasSubOperation(){
        return mOperation.haveSubOperation();
    }
    public boolean isWaiting(){
        return mBottomHeaderAdapter.getCurrentState() == BottomMenuHeaderAdapter.STATE_WAITING;
    }
    public void setWaiting(boolean setWaiting){
        if(setWaiting) {
            mBottomHeaderAdapter.setCurrentState(BottomMenuHeaderAdapter.STATE_WAITING);
            mBottomHeaderAdapter.upDateButtonOperation(mOperation);
        }
        else{
            mBottomHeaderAdapter.setCurrentState(BottomMenuHeaderAdapter.STATE_NORMAL);
            if(mLastOperationUsed!=null)
                upDateButtonOperation(mLastOperationUsed);
            else
                upDateButtonOperation(mOperation);
        }
        System.out.println("state is waiting :" +setWaiting);
    }

    public void getCurrentOperationName(OperationBase operationBase) {
        System.out.println("current op :"+mBottomMenuContainer.getResources().getText(operationBase.getTitleRes()));
    }

    public void setCurrentOperation(Operation operation) {
        mOperation = operation;
    }

    public void restoreSave(Operation lastOperation){
        mOperation = mLastOperationUsed;
        mLastOperationUsed = lastOperation;

        upDateSubOperations(mOperation.getSubOperations());

        int savedSelectedSubOp = mSubOperationAdapter.getLastSelectedSubOpertions();
        if(savedSelectedSubOp !=-1){
            mSubOperationAdapter.setSelectedSubOperations(savedSelectedSubOp);
        }
        System.out.println("restore save: \n from :");
        getCurrentOperationName(lastOperation);
        System.out.println("to : ");
        getCurrentOperationName(mOperation);
    }

    public BottomMenuOptionsAdapter getBottomOptionsAdapter() {
        return mBottomOptionsAdapter;
    }


}

