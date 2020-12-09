package a.grp11.nummethv3.Menus.BottomMenu;


import android.content.res.ColorStateList;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import a.grp11.nummethv3.R;

import a.grp11.nummethv3.DataStructure.OperationBaseStructure.OperationStructure.SubOperationStrcuture.SubOperation;
import a.grp11.nummethv3.DataStructure.OperationBaseStructure.OperationStructure.SubOperationStrcuture.SubOperations;


public class BottomMenuSubOperationAdapter{

    private LinearLayout mSubOperationContainer;
    private SubOperations mSubOperations;
    private int selectedSubOperations;
    private int lastSelectedSubOpertions;

    public BottomMenuSubOperationAdapter(AppCompatActivity activity , SubOperations subOperations) {
        mSubOperationContainer = activity.findViewById(R.id.bottom_menu_subOperation);
        mSubOperationContainer.removeAllViews();

        mSubOperations = subOperations;
        selectedSubOperations= -1;

    }

    public static BottomMenuSubOperationAdapter initOperations(AppCompatActivity activity, SubOperations subOperations){
        BottomMenuSubOperationAdapter bottomMenuTabLayoutAdapter = new BottomMenuSubOperationAdapter(activity,subOperations);
        if(subOperations!=null)
            bottomMenuTabLayoutAdapter.addOperations(bottomMenuTabLayoutAdapter.getSubOperations());

        return bottomMenuTabLayoutAdapter;
    }

    public void update(SubOperations subOperations){
        mSubOperations = subOperations;
        clearTabs();
        if(subOperations!= null)
            addOperations(mSubOperations);
        System.out.println("sub operation updated");
    }

    private View getCustomTabView(SubOperation op){
        View customTabView = LayoutInflater.from(mSubOperationContainer.getContext()).inflate(R.layout.bottom_sub_tab_item,null);
        FloatingActionButton fabIcon = customTabView.findViewById(R.id.bottom_button);
        TextView tabTitle = customTabView.findViewById(R.id.bottom_title);

        ViewCompat.setPaddingRelative(customTabView,5,0,5,0);
        fabIcon.setBackgroundTintList(ColorStateList.valueOf(mSubOperationContainer.getResources()
                .getColor(op.getBg_colorRes())));
        fabIcon.setImageResource(op.getIconRes());
        tabTitle.setText(op.getTitleRes());
        tabTitle.setTextColor(ColorStateList.valueOf(customTabView.getResources().getColor(R.color.darkLight)));

        return customTabView;
    }

    private void addOperations(SubOperations ops){
        for (int i = 0; i <ops.getLength(); i++) {
            mSubOperationContainer.addView(getCustomTabView(ops.getOperation(i)));
        }
    }



    public LinearLayout getSubOperationContainer() {
        return mSubOperationContainer;
    }
    public SubOperation getSelectedSubOperation(){
        return  mSubOperations.getOperation(selectedSubOperations);
    }
    public SubOperations getSubOperations() {
        return mSubOperations;
    }

    public void clearTabs(){
        mSubOperationContainer.removeAllViews();
    }

    public void setSubOperations(SubOperations subOperations) {
        mSubOperations = subOperations;
    }

    public void setVisibilty(boolean visibilty){
        if (!visibilty)
            mSubOperationContainer.setVisibility(View.GONE);
        else mSubOperationContainer.setVisibility(View.VISIBLE);
    }

    public Boolean isVisible(){
        return mSubOperationContainer.getVisibility() == View.VISIBLE;
    }

    public void setSelectedSubOperations(int selectedSubOperations) {
        this.selectedSubOperations = selectedSubOperations;
    }

    public void onCLick(View customView){
        lastSelectedSubOpertions = selectedSubOperations;
        selectedSubOperations = mSubOperationContainer.indexOfChild(customView);
    }

    public int getLastSelectedSubOpertions() {
        return lastSelectedSubOpertions;
    }

    public int getSelectedSubOperations() {
        return selectedSubOperations;
    }

    public boolean isSelected(){
        return selectedSubOperations != -1;
    }
}
