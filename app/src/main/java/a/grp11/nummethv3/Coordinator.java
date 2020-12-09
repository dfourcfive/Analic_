package a.grp11.nummethv3;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


import a.grp11.nummethv3.Linking.FunctionAndroid.FunctionAndroid;
import a.grp11.nummethv3.Linking.FunctionAndroid.FunctionAndroidFragment;
import a.grp11.nummethv3.Menus.AppBarNewBehavior;
import a.grp11.nummethv3.Menus.BottomMenu.BottomMenuAdapter;
import a.grp11.nummethv3.Menus.subMenu.SubMenuAdapter;
import a.grp11.nummethv3.DataStructure.OperationBaseStructure.OperationStructure.Operation;
import a.grp11.nummethv3.PartData.PartIntegrale;
import a.grp11.nummethv3.PartData.PartRoot;
import a.grp11.nummethv3.PartData.PartsConfig;

public class Coordinator {

    private SubMenuAdapter mSubMenuAdapter;
    private BottomMenuAdapter mBottomMenuAdapter;
    private AppBarLayout mAppBarLayout;
    private FragmentManager mFragmentManager;
    private Fragment mPartFragment;


    public Coordinator(AppCompatActivity activity , int partKey , int operationId) {

        mFragmentManager = activity.getSupportFragmentManager();
        mFragmentManager.getFragments().clear();

        mPartFragment = PartsConfig.getSelectedPartData().getPartFragment();

        mFragmentManager.beginTransaction()
                .add(R.id.fragment_matrix_android_container,mPartFragment)
                .commit();



        mBottomMenuAdapter = BottomMenuAdapter.initBottomMenuAdapter(activity,partKey,operationId);
        mSubMenuAdapter = SubMenuAdapter.initOperations(activity,partKey,operationId);

        mAppBarLayout = activity.findViewById(R.id.appBarLayout);


        ((CoordinatorLayout.LayoutParams) mAppBarLayout.getLayoutParams())
                .setBehavior(new AppBarNewBehavior(mAppBarLayout,mBottomMenuAdapter,mSubMenuAdapter));



        addSubMenuEvents(mBottomMenuAdapter);
        addBottomMenuEvents(mPartFragment);
        addEventsAppBarLayout();
    }

    private void addSubMenuEvents(final BottomMenuAdapter bottomMenuAdapter){
        mSubMenuAdapter.getTabLayout().addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(bottomMenuAdapter!=null){
                    mSubMenuAdapter.getOperations().setSelectedOperationId(tab.getPosition());
                    Operation op = mSubMenuAdapter.getOperations().getOperation(tab.getPosition());

                    bottomMenuAdapter.upDateButtonOperation(op);

                    if(!bottomMenuAdapter.hasSubOperation() && bottomMenuAdapter.isWaiting()){
                        bottomMenuAdapter.clickOnButton();
                        bottomMenuAdapter.restoreSave(op);
                    }else if(!bottomMenuAdapter.hasSubOperation()&& !mBottomMenuAdapter.isExpanded()){
                        System.out.println("here sub menu");
                        mBottomMenuAdapter.clickOnExpander();
                    }

                    if(PartsConfig.getSelectedPartData() instanceof PartIntegrale
                            || PartsConfig.getSelectedPartData() instanceof PartRoot){
                        ((FunctionAndroidFragment) mPartFragment).updateParams(op);
                    }

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                onTabSelected(tab);
            }
        });
    }
    private void addBottomMenuEvents(final Fragment partFragment){

        if(mBottomMenuAdapter.getButtonOperation()!=null){
            mBottomMenuAdapter.getButtonOperation().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        mBottomMenuAdapter.upDate(partFragment,PartsConfig.getSelectedPartData());
                }
            });
        }

        mBottomMenuAdapter.getBottomHeaderAdapter().getExpanderButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mSubMenuAdapter.getState() == SubMenuAdapter.STATE_COLAPSED) {
                    if(!mBottomMenuAdapter.hasSubOperation()){
                        if(!mBottomMenuAdapter.isExpanded()){
                            mBottomMenuAdapter.getSubOperationMenuAdapter().setVisibilty(false);
                            mBottomMenuAdapter.expand();
                        }else
                            mBottomMenuAdapter.collaps();
                    }else if(mBottomMenuAdapter.getSubOperationMenuAdapter().isSelected()){
                        if(!mBottomMenuAdapter.isExpanded()){
                            mBottomMenuAdapter.getSubOperationMenuAdapter().setVisibilty(false);
                            mBottomMenuAdapter.expand();
                        }else{
                            mBottomMenuAdapter.collaps();
                            mBottomMenuAdapter.getSubOperationMenuAdapter().setVisibilty(true);
                        }
                    }
                    else {
                        if(mBottomMenuAdapter.isExpanded()) mBottomMenuAdapter.collaps();
                        mBottomMenuAdapter.getSubOperationMenuAdapter().setVisibilty(true);
                    }

                }else if (mSubMenuAdapter.getState()==SubMenuAdapter.STATE_EXPANDED){
                    mBottomMenuAdapter.collaps();
                    mBottomMenuAdapter.getSubOperationMenuAdapter().setVisibilty(false);
                }

                mBottomMenuAdapter.getBottomOptionsAdapter().setOptionsTabEvents(mFragmentManager);

            }
        });

    }

    private void addEventsAppBarLayout(){
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if(verticalOffset == 0 && mSubMenuAdapter.getState()!=SubMenuAdapter.STATE_EXPANDED){
                    mSubMenuAdapter.setState(SubMenuAdapter.STATE_EXPANDED);
                    mBottomMenuAdapter.clickOnExpander();
                }else if((-verticalOffset)==appBarLayout.getTotalScrollRange()
                        && mSubMenuAdapter.getState()!=SubMenuAdapter.STATE_COLAPSED){
                    mSubMenuAdapter.setState(SubMenuAdapter.STATE_COLAPSED);
                    mBottomMenuAdapter.clickOnExpander();
                }
            }
        });
    }

    public Fragment getPartFragment() {
        return mPartFragment;
    }
}
