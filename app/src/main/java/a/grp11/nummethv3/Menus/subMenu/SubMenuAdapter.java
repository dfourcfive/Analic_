package a.grp11.nummethv3.Menus.subMenu;


import android.graphics.Typeface;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.TextView;

import a.grp11.nummethv3.DataStructure.Parts;
import a.grp11.nummethv3.PartData.PartsConfig;
import a.grp11.nummethv3.R;
import a.grp11.nummethv3.DataStructure.OperationBaseStructure.OperationStructure.Operation;
import a.grp11.nummethv3.DataStructure.OperationBaseStructure.OperationStructure.Operations;
import de.hdodenhof.circleimageview.CircleImageView;



public class SubMenuAdapter implements TabLayout.OnTabSelectedListener{
    private TabLayout mTabLayout;
    private Operations mOperations;
    private int mState;
    public final static int  STATE_EXPANDED  = 0;
    public final static int  STATE_COLAPSED  = 1;
    public SubMenuAdapter(int partId , AppCompatActivity activity){

        mTabLayout =  activity.findViewById(R.id.tabLayout_subMenu);
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mTabLayout.addOnTabSelectedListener(this);
        mTabLayout.setSelectedTabIndicatorHeight(0);
        mOperations = PartsConfig.getParts().getPartData(partId).getPartOperations();
        mState = STATE_EXPANDED;
    }



    public static SubMenuAdapter initOperations(AppCompatActivity activity,int partId, int operationSelected){
        SubMenuAdapter subMenuAdapter = new SubMenuAdapter(partId,activity);
        subMenuAdapter.addOperations(activity,subMenuAdapter.getOperations() ,operationSelected);
        return subMenuAdapter;
    }


    private View getCustomTabView(FragmentActivity activity, Operation op){
        View customTabView = LayoutInflater.from(activity).inflate(R.layout.sub_menu_tab_item,null);
        CircleImageView tabIcon = customTabView.findViewById(R.id.tab_icon);
        TextView tabTitle = customTabView.findViewById(R.id.tab_text);

        tabIcon.setBorderColor(activity.getResources().getColor(op.getBg_colorRes()));
        tabIcon.setCircleBackgroundColorResource(op.getBg_colorRes());
        tabIcon.setImageResource(op.getIconRes());

        tabTitle.setText(op.getTitleRes());

        return customTabView;
    }

    private void addOperations(FragmentActivity activity , Operations ops , int operationSelected){
        TabLayout.Tab tabItem;
        for (int i = 0; i <ops.getLength(); i++) {
            tabItem = mTabLayout.newTab();
            tabItem.setCustomView(getCustomTabView(activity,ops.getOperation(i)));
            mTabLayout.addTab(tabItem);

            if(i==operationSelected) {
                tabItem.select();
            }
        }
    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        Operation op = mOperations.getOperation(tab.getPosition());


        TextView title = tab.getCustomView().findViewById(R.id.tab_text);
        CircleImageView icon = tab.getCustomView().findViewById(R.id.tab_icon);

        title.setTypeface(null, Typeface.BOLD);
        title.setTextColor(tab.getCustomView().getResources().getColor(op.getBg_colorRes()));
        icon.setBorderColor(tab.getCustomView().getResources().getColor(R.color.white));

    }


    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

        Operation op = mOperations.getOperation(tab.getPosition());

        TextView title = tab.getCustomView().findViewById(R.id.tab_text);
        CircleImageView icon = tab.getCustomView().findViewById(R.id.tab_icon);

        title.setTypeface(null, Typeface.NORMAL);
        title.setTextColor(tab.getCustomView().getResources().getColor(R.color.white));
        icon.setBorderColor(tab.getCustomView().getResources().getColor(op.getBg_colorRes()));
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        onTabSelected(tab);
    }


    public TabLayout getTabLayout() {
        return mTabLayout;
    }

    public Operations getOperations() {
        return mOperations;
    }

    public int getState() {
        return mState;
    }

    public void setState(int state) {
        mState = state;
    }
}
