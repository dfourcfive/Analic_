package a.grp11.nummethv3.Linking.Curve;



import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import a.grp11.nummethv3.DataStructure.OperationBaseStructure.OperationBase;
import a.grp11.nummethv3.R;
import a.grp11.nummethv3.curvefitting.MainActivity;
import app.function.Function;



public class CurveAndroidFragment extends Fragment{
    public static String KEY_FUNCTION_ANDROID_ID = "function";
    private CurveAndroidControl mCurveAndroidControl;
    private EditText mIntegralContainer;
    private LinearLayout mResultContainer;
    private TextView mResultEdit;
    private EditText fieldA;
    private EditText fieldB;
    private EditText fieldN;
    private View mRootView;

    public CurveAndroidFragment()
    {
        super();
    }

    public static CurveAndroidFragment newInstance(int matrixAndroidID) {

        Bundle args = new Bundle();

        args.putInt(KEY_FUNCTION_ANDROID_ID,matrixAndroidID);
        CurveAndroidFragment fragment = new CurveAndroidFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("heelo herre");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        mRootView = inflater.inflate(R.layout.activity_main_2, container,false);
Intent intent = new Intent(getActivity(), MainActivity.class);
startActivity(intent);
        mCurveAndroidControl = new CurveAndroidControl(new CurveAndroid(new Function("")));
        mIntegralContainer =  mRootView.findViewById(R.id.function);
        mResultContainer = mRootView.findViewById(R.id.functionResultContainer);
        mResultEdit = mRootView.findViewById(R.id.functionResult);
        fieldA  = mRootView.findViewById(R.id.a);
        fieldB  = mRootView.findViewById(R.id.b);
        fieldN  = mRootView.findViewById(R.id.n);

        /*
        if(mCurveAndroidControl.haveErrorSize()){
            Toast.makeText(getContext(),"The length of the number is too long",Toast.LENGTH_SHORT).show();
        }*/


        return mRootView;
    }


    public void updateIntegralAndroid() {
        mCurveAndroidControl.getIntegralAndroid().setFunction(
                new Function(mIntegralContainer.getText().toString()));
    }

    public CurveAndroid getElemAndroid() {
        updateIntegralAndroid();
        return mCurveAndroidControl.getIntegralAndroid();
    }


    public void clear() {
        mIntegralContainer.setText(" ");
    }

    public List<String> getResult(){
        List<String> results = new ArrayList<>();

        results.add(fieldA.getText().toString());
        results.add(fieldB.getText().toString());
        results.add(fieldN.getText().toString());
        return results;

    }

    public void upDatePage(CurveAndroid result) {

        mResultEdit.setText(result.getFunctionValues().getStrFunc());
        mResultContainer.setVisibility(View.VISIBLE);
    }
}