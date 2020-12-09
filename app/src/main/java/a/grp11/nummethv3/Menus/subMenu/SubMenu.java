package a.grp11.nummethv3.Menus.subMenu;

import android.content.Context;
import android.graphics.Canvas;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;




public class SubMenu extends TabLayout {

    int[] posTab = new int[2];
    private static final float MAX_ZOOM = 1.0f;
    private static final float MIN_ZOOM = 0.7f;


    public SubMenu(Context context) {
        super(context);
    }

    public SubMenu(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public SubMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if(changed){
            centerTabs();
        }
    }


    private void centerTabs() {

        TabLayout.Tab firstTab = getTabAt(0);
        TabLayout.Tab lastTab = getTabAt(getTabCount() - 1);

        if (firstTab != null && lastTab != null) {
            int paddingLeft = (getWidth() / 2) - (firstTab.getCustomView().getWidth() / 2);
            int paddingRight = (getWidth() / 2) - (lastTab.getCustomView().getWidth() / 2);
            ViewCompat.setPaddingRelative(getChildAt(0),paddingLeft,0,paddingRight,0);
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        ViewGroup tabLayout = (ViewGroup) getChildAt(0);

        float scale;

        for (int i = 0 ; i< tabLayout.getChildCount() ; i++){
            View tab = tabLayout.getChildAt(i);
            tab.getLocationOnScreen(posTab);

            scale = zoomIn(tab ,posTab[0]);

            tab.setPivotY(0f);
            tab.setScaleX(scale);
            tab.setScaleY(scale);
        }
    }

    private float zoomIn(View tab , int posX){

        int width = tab.getWidth();

        float scale;
        float tabCenter = posX + width / 2 ;

        if (tabCenter <= 0 || getWidth() <= tabCenter) {
            scale = MIN_ZOOM;
        } else {
            float sliderCenter = (getWidth() / 2);
            float distance = Math.abs(sliderCenter - tabCenter);
            scale = MAX_ZOOM - (MAX_ZOOM - MIN_ZOOM) *( distance / sliderCenter);
        }
        return scale;
    }


}
