package a.grp11.nummethv3.ListFragment.ElemAndroidList;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import a.grp11.nummethv3.ListFragment.Animation.ZoomOutPageTransformer;
import a.grp11.nummethv3.ListFragment.ElemAndroid.ElemAndroid;
import a.grp11.nummethv3.R;




public class ElemAndroidListFragment extends Fragment {

    ElemAndroidListAdapter mAndroidListAdapter;
    ViewPager mViewPager;


    public ElemAndroidListFragment() {
        super();
    }

    public static ElemAndroidListFragment newInstance() {
        return new ElemAndroidListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_elem_android_list, container,false);
        mViewPager = rootView.findViewById(R.id.elem_android_pager);

        mAndroidListAdapter = new ElemAndroidListAdapter(getActivity().getSupportFragmentManager());

        mViewPager.setAdapter(mAndroidListAdapter);
        mViewPager.setPageTransformer(true ,new ZoomOutPageTransformer());
        return rootView;
    }


    public void upDateAddPage(ElemAndroid elemAndroid){
        mAndroidListAdapter.addPage(elemAndroid ,mViewPager);
    }
    public void upDateChangePage(ElemAndroid elemAndroid){
        mAndroidListAdapter.changeCurrentPage(elemAndroid,mViewPager);
    }

    public void upDateSavePage(){
        mAndroidListAdapter.setPageSaved(mViewPager);
    }

    public Fragment getCurrentElemAndroidFragment(){
        return getElemAndroidFragment(mViewPager.getCurrentItem());
    }

    public Fragment getElemAndroidFragment(int elemID){
        return mAndroidListAdapter.getItem(elemID);
    }
}
