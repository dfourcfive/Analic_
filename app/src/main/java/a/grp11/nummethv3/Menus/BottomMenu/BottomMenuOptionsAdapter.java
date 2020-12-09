package a.grp11.nummethv3.Menus.BottomMenu;


import android.graphics.Color;
import android.graphics.Typeface;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import a.grp11.nummethv3.DataStructure.OptionStructure.Option;
import a.grp11.nummethv3.DataStructure.OptionStructure.Options;
import a.grp11.nummethv3.PartData.PartsConfig;
import a.grp11.nummethv3.R;
import a.grp11.nummethv3.dialogBuilder.OptionDialogFragment;

public class BottomMenuOptionsAdapter {

    private LinearLayout mOptionsTab;
    private int mSelectedOption;
    private Options mOptions;

    public BottomMenuOptionsAdapter(AppCompatActivity activity,Options options) {

        mOptionsTab = activity.findViewById(R.id.bottom_menu_optionsTab);
        mOptions = options;
        mSelectedOption = -1;
    }

    public static BottomMenuOptionsAdapter initOperations(AppCompatActivity activity, Options options) {
        BottomMenuOptionsAdapter bottomMenuOptionsAdapter = new BottomMenuOptionsAdapter(activity,options);
        if (options != null)
            bottomMenuOptionsAdapter.addOptions(bottomMenuOptionsAdapter.getOptions());

        return bottomMenuOptionsAdapter;
    }


    private View getCustomTabView(Option option) {
        View customTabView = LayoutInflater.from(mOptionsTab.getContext()).inflate(R.layout.bottom_option_tab_item, null);
        ImageView tabIcon = customTabView.findViewById(R.id.tab_icon);
        TextView tabTitle = customTabView.findViewById(R.id.tab_text);

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) customTabView.getLayoutParams();
        tabIcon.setImageResource(option.getIconRes());
        tabTitle.setText(option.getTitleRes());

        return customTabView;
    }

    private void addOptions(Options options) {
        for (int i = 0; i < options.getNbOptions(); i++) {
           mOptionsTab.addView(getCustomTabView(options.getOption(i)));
        }
    }




    public void setOptionsTabEvents(final FragmentManager fragmentManager){
        for (int i = 0; i < mOptionsTab.getChildCount(); i++) {
            mOptionsTab.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSelectedOption = mOptionsTab.indexOfChild(v);
                    PartsConfig.getSelectedPartData().getPartOptions().setSelectedOptionId(mSelectedOption);
                    new OptionDialogFragment().show(fragmentManager,"test");
                }
            });
        }
    }
    public LinearLayout getOpionsTab() {
        return mOptionsTab;
    }

    public Option getSelectedOption() {
        return mOptions.getOption(mSelectedOption);
    }

    public Options getOptions() {
        return mOptions;
    }

    public void setOptions(Options options) {
        mOptions = options;
    }



    public void clearTabs() {
        mOptionsTab.removeAllViews();
    }


}


