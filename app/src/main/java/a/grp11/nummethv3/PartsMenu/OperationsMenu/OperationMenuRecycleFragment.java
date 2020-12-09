package a.grp11.nummethv3.PartsMenu.OperationsMenu;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;

import a.grp11.nummethv3.DataStructure.Part;
import a.grp11.nummethv3.DataStructure.Parts;
import a.grp11.nummethv3.PartData.PartsConfig;
import a.grp11.nummethv3.R;





public class OperationMenuRecycleFragment extends Fragment {
    private Part mOperationsType;
    private int mPagerId;



    public static final String KEY_PAGER_ID = "operations 1";


    public OperationMenuRecycleFragment() {

    }

    public static OperationMenuRecycleFragment newInstance(int pagerId){
            OperationMenuRecycleFragment fragment =  new OperationMenuRecycleFragment();
            Bundle args = new Bundle();
            args.putInt(KEY_PAGER_ID,pagerId);
            fragment.setArguments(args);
            return fragment;
    }

    public int getPagerId() {
        return mPagerId;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPagerId = getArguments().getInt(KEY_PAGER_ID);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_operation_menu_recycle, container, false);

        mPagerId = getArguments().getInt(KEY_PAGER_ID);
        mOperationsType = PartsConfig.getParts().getPartData(mPagerId);

        RecyclerView rv = rootView.findViewById(R.id.operation_recycler_view);
        rv.setHasFixedSize(true);
        rv.addItemDecoration(new MaterialViewPagerHeaderDecorator());
        OperationMenuRecycleAdapter adapter = new OperationMenuRecycleAdapter((AppCompatActivity) getActivity(),mPagerId);
        rv.setAdapter(adapter);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);
        return rootView;
    }
}

