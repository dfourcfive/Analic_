package a.grp11.nummethv3.ListFragment.ElemAndroidList;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import a.grp11.nummethv3.ListFragment.ElemAndroid.ElemAndroid;
import a.grp11.nummethv3.Linking.MatrixAndroid.MatrixAndroid;





public class ElemAndroidListAdapter extends FragmentStatePagerAdapter {
    protected List<Fragment> mElemAndroidFragments;

    public ElemAndroidListAdapter(FragmentManager fm ) {
        super(fm);
        mElemAndroidFragments = new ArrayList<>();

        ElemAndroidList.init(new MatrixAndroid(3,3));
        mElemAndroidFragments.add(ElemAndroidList.getFragmentElem(0));

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        System.out.println("helllo 2017");
        return super.instantiateItem(container, position);
    }

    @Override
    public Fragment getItem(int position) {
        return mElemAndroidFragments.get(position);
    }

    @Override
    public int getCount() {
        return mElemAndroidFragments.size();
    }

    @Override
    public int getItemPosition(Object object) {
        Fragment fragment = (Fragment) object;
        int position = mElemAndroidFragments.indexOf(fragment);

        return ( position == -1) ? POSITION_NONE : position;
    }

    public void addPage(ElemAndroid newElem , ViewPager viewPager){

        int currentNumPage = viewPager.getCurrentItem();

        ElemAndroidList.removeFromToEnd(currentNumPage+1);
        ElemAndroidList.addElemAndroid(newElem);

        int nbRemove = getCount() - currentNumPage - 1;
        for (int i = 0; i < nbRemove ; i++) {
            mElemAndroidFragments.get(currentNumPage+1);
            mElemAndroidFragments.remove(currentNumPage+1);
        }

        mElemAndroidFragments.add(newElem.getFragmentElem());

        notifyDataSetChanged();
        viewPager.setCurrentItem(currentNumPage+1,true);
    }


    public void changeCurrentPage(ElemAndroid newElem , ViewPager viewPager){
        changePage(newElem,viewPager.getCurrentItem());
    }

    public void changePage(ElemAndroid newElem , int pageID){

        ElemAndroidList.changeElemAndroid(newElem,pageID);
        mElemAndroidFragments.set(pageID,newElem.getFragmentElem());
        notifyDataSetChanged();
    }

    public void setPageSaved(ViewPager viewPager) {
        ElemAndroid currentElemt = ElemAndroidList.getElemAndroid(viewPager.getCurrentItem());
        currentElemt.setSaved();
        changeCurrentPage(currentElemt,viewPager);
    }

}