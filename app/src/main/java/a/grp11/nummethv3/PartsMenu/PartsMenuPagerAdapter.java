package a.grp11.nummethv3.PartsMenu;



import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.astuetz.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

import a.grp11.nummethv3.PartData.PartsConfig;
import a.grp11.nummethv3.R;
import a.grp11.nummethv3.PartsMenu.OperationsMenu.OperationMenuRecycleFragment;


public class PartsMenuPagerAdapter extends FragmentStatePagerAdapter implements PagerSlidingTabStrip.CustomTabProvider {

    private List<Fragment> mListPage;



    public PartsMenuPagerAdapter(FragmentManager fm) {
        super(fm);

        mListPage = new ArrayList<>();
        for (int i = 0; i < PartsConfig.getParts().getNbPart(); i++) {
            mListPage.add(OperationMenuRecycleFragment.newInstance(i));
        }

        PartsConfig.setSelectedPartId(0);
    }


    @Override
    public Fragment getItem(int i) {
        return mListPage.get(i);
    }

    @Override
    public int getCount() {
        return mListPage.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return PartsConfig.getParts().getPartData(position).getTitle();
    }

    @Override
    public View getCustomTabView(ViewGroup parent, int position) {


        View customTabView = LayoutInflater.from(parent.getContext()).inflate(R.layout.top_menu_tab_item,null);
        ImageView tabIcon = customTabView.findViewById(R.id.tab_icon);
        TextView tabTitle = customTabView.findViewById(R.id.tab_text);

        customTabView.setId(position);
        customTabView.setBackgroundColor(Color.TRANSPARENT);


        tabIcon.setImageResource(PartsConfig.getParts().getPartData(position).getPartIconRes());

        tabTitle.setText(PartsConfig.getParts().getPartData(position).getPartTitleRes());


        tabIcon.setBackgroundColor(Color.TRANSPARENT);
        tabTitle.setBackgroundColor(Color.TRANSPARENT);

        return customTabView;

    }

    @Override
    public void tabSelected(View tab) {
        tab.setBackgroundColor(Color.TRANSPARENT);
        PartsConfig.setSelectedPartId(tab.getId());
    }

    @Override
    public void tabUnselected(View tab) {
        tab.setBackgroundColor(Color.TRANSPARENT);
    }
}

