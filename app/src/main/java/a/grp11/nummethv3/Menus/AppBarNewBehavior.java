package a.grp11.nummethv3.Menus;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewCompat;
import android.view.View;

import a.grp11.nummethv3.Menus.BottomMenu.BottomMenuAdapter;
import a.grp11.nummethv3.Menus.subMenu.SubMenuAdapter;




public class AppBarNewBehavior extends AppBarLayout.Behavior {

    private BottomMenuAdapter mBottomMenuAdapter;

    public AppBarNewBehavior(final AppBarLayout appBarLayout , final BottomMenuAdapter bottomMenuAdapter , final SubMenuAdapter subMenuAdapter) {
        super();

        mBottomMenuAdapter = bottomMenuAdapter;
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                int toolbarHeight = appBarLayout.getChildAt(0).getHeight();
                TabLayout tabLayout = (TabLayout) appBarLayout.getChildAt(1);
                int offSet;

                offSet = -(verticalOffset+toolbarHeight);
                TabLayout.Tab tab;
                float scale;



                scale = zoomOut(offSet,tabLayout.getHeight());

                if(scale<=1){

                    tabLayout.setTranslationY(offSet);
                    for (int i = 0 ; i< tabLayout.getTabCount(); i++){
                        tab = tabLayout.getTabAt(i);

                        tab.getCustomView().setPivotY(0f);
                        tab.getCustomView().setScaleX(scale);
                        tab.getCustomView().setScaleY(scale);
                    }

                }

            }
        });
    }



    @Override
    public boolean onStartNestedScroll(CoordinatorLayout parent, AppBarLayout child, View directTargetChild, View target, int nestedScrollAxes, int type) {
        return super.onStartNestedScroll(parent, child, directTargetChild, target, nestedScrollAxes, type);
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout child, View target,
                               int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed,
                dxUnconsumed, dyUnconsumed, type);

        stopNestedScrollIfNeeded(dyUnconsumed, child, target, type);

     }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, AppBarLayout child,
                                  View target, int dx, int dy, int[] consumed, int type) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
        stopNestedScrollIfNeeded(dy, child, target, type);
    }

    private void stopNestedScrollIfNeeded(int dy, AppBarLayout child, View target, int type) {
        if (type == ViewCompat.TYPE_NON_TOUCH) {
            final int currOffset = getTopAndBottomOffset();
            if ((dy < 0 && currOffset == 0)
                    || (dy > 0 && currOffset == -child.getTotalScrollRange())) {
                ViewCompat.stopNestedScroll(target, ViewCompat.TYPE_NON_TOUCH);
            }
        }
    }

    private float zoomOut(int offSet , int maxOffSet){
        return 1-((float)(offSet)/(float)(maxOffSet));
    }
}

