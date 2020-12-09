package a.grp11.nummethv3.PartData;


import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import a.grp11.nummethv3.DataStructure.Part;
import a.grp11.nummethv3.DataStructure.Parts;
import a.grp11.nummethv3.ListFragment.ElemAndroidList.ElemAndroidList;
import a.grp11.nummethv3.ListFragment.ElemAndroidList.ElemAndroidListFragment;

import a.grp11.nummethv3.PartActivity;
import a.grp11.nummethv3.PartsMenu.PartsMenuPagerAdapter;
import a.grp11.nummethv3.R;



public abstract class PartsConfig {
    private static Parts mParts;
    private static PartsMenuPagerAdapter sOperationMenuPagerAdapter;

    public PartsConfig() {

    }



    public static void init(AppCompatActivity activity){

        if(mParts == null){
            mParts = new Parts();
            ElemAndroidList.clear();
            mParts.addPartData(new PartMatrice());
            mParts.addPartData(new PartIntegrale());
            mParts.addPartData(new PartAdjustment());
            mParts.addPartData(new PartRoot());
            setOperationMenuPagerAdapter(new PartsMenuPagerAdapter(activity.getSupportFragmentManager()));
        }

    }



    public static void startPartActivity(Activity parentActivity , int operationId) {
        if(getSelectedPartData().getPartTitleRes() == R.string.part_adjustment_title){
            PartActivity l=new PartActivity();
            l.startActivity(mParts.getSelectedPartId(),operationId,parentActivity);
            /** Start your activity**/
        }else
            PartActivity.startActivity(mParts.getSelectedPartId(),operationId,parentActivity);

        getSelectedPartData().getPartOptions().setSelectedOptionId(operationId);
    }

    private static void setOperationMenuPagerAdapter(PartsMenuPagerAdapter operationMenuPagerAdapter) {
        sOperationMenuPagerAdapter = operationMenuPagerAdapter;
    }
    public static PartsMenuPagerAdapter getOperationMenuPagerAdapter() {
        return sOperationMenuPagerAdapter;
    }

    public static Part getSelectedPartData(){
        return mParts.getPartData(mParts.getSelectedPartId());
    }

    public static void setSelectedPartId(int partId){
        mParts.setSelectedPartId(partId);
    }

    public static Parts getParts() {
        return mParts;
    }

    public static Fragment getPartFragment(){
        return ElemAndroidListFragment.newInstance();
    }
}
