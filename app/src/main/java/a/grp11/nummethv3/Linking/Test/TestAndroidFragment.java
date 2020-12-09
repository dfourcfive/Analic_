package a.grp11.nummethv3.Linking.Test;


import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.GridLayout;
import android.text.InputFilter;
import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import a.grp11.nummethv3.ListFragment.ElemAndroidList.ElemAndroidList;
import a.grp11.nummethv3.R;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;



public class TestAndroidFragment extends Fragment{

    public static String KEY_MATRIX_ANDROID_ID = "matrix android id";

    private TestAndroidControl mTestAndroidControl;
    private View mRootView;

    public TestAndroidFragment() {
        super();
    }

    public static Fragment newInstance(int matrixAndroidID) {

        Bundle args = new Bundle();

        args.putInt(KEY_MATRIX_ANDROID_ID,matrixAndroidID);
        TestAndroidFragment fragment = new TestAndroidFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        mRootView = inflater.inflate(R.layout.fragment_keyboard_sample, container,false);

        mTestAndroidControl = new TestAndroidControl((TestAndroid) ElemAndroidList.getElemAndroid((getArguments().getInt(KEY_MATRIX_ANDROID_ID))));



        if(mTestAndroidControl.haveErrorSize()){
            Toast.makeText(getContext(),"The length of the number is too long",Toast.LENGTH_SHORT).show();
        }


        return mRootView;
    }



    private void updateTestAndroid(TestAndroid testAndroid){
        EditText editText;
    }

    public TestAndroid getTestAndroid() {
        this.updateTestAndroid(mTestAndroidControl.getTestAndroid());
        return mTestAndroidControl.getTestAndroid();
    }

}