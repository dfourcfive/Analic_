package a.grp11.nummethv3.Linking.MatrixAndroid;


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



public class MatrixAndroidFragment extends Fragment{

    public static String KEY_MATRIX_ANDROID_ID = "matrix android id";
    public final static int MAX_ROW_MATRIX = 6;
    public final static int MAX_COL_MATRIX = 6;
    public final static int MAX_TEXT_SIZE = 20;
    public final static int MIN_TEXT_SIZE = 12;
    private MatrixAndroidControl mMatrixAndroidControl;
    private GridLayout mMatrixContainer;
    private View mRootView;

    public MatrixAndroidFragment() {
        super();
    }

    public static Fragment newInstance(int matrixAndroidID) {

        Bundle args = new Bundle();

        args.putInt(KEY_MATRIX_ANDROID_ID,matrixAndroidID);
        MatrixAndroidFragment fragment = new MatrixAndroidFragment();
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

        mRootView = inflater.inflate(R.layout.fragment_matrix_android, container,false);
        mMatrixContainer =  mRootView.findViewById(R.id.fragment_matrix_android_grid);

        mMatrixAndroidControl= new MatrixAndroidControl((MatrixAndroid) ElemAndroidList.getElemAndroid((getArguments().getInt(KEY_MATRIX_ANDROID_ID))));


        createGrid();
        if(mMatrixAndroidControl.haveErrorSize()){
            Toast.makeText(getContext(),"The length of the number is too long",Toast.LENGTH_SHORT).show();
        }


        return mRootView;
    }

    private void createGrid(){

        mMatrixContainer.removeAllViews();
        mMatrixContainer.setRowCount(mMatrixAndroidControl.getRowNb());
        mMatrixContainer.setColumnCount(mMatrixAndroidControl.getColNb());

        if(mMatrixAndroidControl.getMatrixAndroid().isSaved()){
            mMatrixContainer.setBackgroundResource(R.color.darkLight);
        }

        AppCompatEditText editText;
        int id = 0;

        for (int i = 0; i <mMatrixAndroidControl.getRowNb(); i++) {
            for (int j = 0; j <mMatrixAndroidControl.getColNb(); j++) {

                mMatrixAndroidControl.setToEditText(i,j,editText=getEditText(id));
                mMatrixContainer.addView(editText);
                mMatrixAndroidControl.setToMatrixId(i,j,editText.getId());

                GridLayout.LayoutParams params = new GridLayout.LayoutParams(editText.getLayoutParams());
                params.rowSpec = GridLayout.spec(i,1f);
                params.columnSpec = GridLayout.spec(j,1f);
                params.setMargins(10,10,10,10);
                params.width = WRAP_CONTENT;
                params.height = WRAP_CONTENT;
                params.setGravity(Gravity.FILL);
                editText.setLayoutParams(params);
                editText.setHintTextColor(getResources().getColor(R.color.cardview_dark_background));
                //editText.setGravity(Gravity.CENTER);

                if(mMatrixAndroidControl.getMatrixAndroid().isSaved()) editText.setTextColor(Color.WHITE);

                int currentMinSize = Math.min(mMatrixAndroidControl.getRowNb(),mMatrixAndroidControl.getColNb())-1;

                if(currentMinSize>=4){
                    editText.setTextSize(11);
                }else {
                    int minMaxSize = Math.min(MAX_COL_MATRIX,MAX_ROW_MATRIX)-1;

                    editText.setTextSize(MAX_TEXT_SIZE*((1-(float)(currentMinSize/minMaxSize)))+MIN_TEXT_SIZE*((float)(currentMinSize/minMaxSize)));

                }

                id++;
            }

        }

    }

    private AppCompatEditText getEditText(int id){
        AppCompatEditText editText = new AppCompatEditText(getActivity());

        editText.setId(id);
        editText.setHint("0");
        editText.setTextColor(ColorStateList.valueOf(getResources().getColor(R.color.dark)));
        editText.setRawInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
        editText.setKeyListener(DigitsKeyListener.getInstance(true, true));
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(MatrixAndroidControl.MAX_LENGHT_NUMBER)});

        return editText;
    }

    private void updateMatrixAndroid(MatrixAndroid matrixAndroid){
        EditText editText;
        for (int i = 0; i <matrixAndroid.getRowNb(); i++) {
            for (int j = 0; j <matrixAndroid.getColNb(); j++) {
                editText = mRootView.findViewById((int)matrixAndroid.getMatrixIds().getElem(i,j).getA());
                mMatrixAndroidControl.setToMatrixValues(i,j,editText);
            }
        }


    }

    public MatrixAndroid getMatrixAndroid() {
        this.updateMatrixAndroid(mMatrixAndroidControl.getMatrixAndroid());
        return mMatrixAndroidControl.getMatrixAndroid();
    }

    public GridLayout getMatrixContainer() {
        return mMatrixContainer;
    }

    public void clear(){updateMatrixAndroid(new MatrixAndroid(mMatrixAndroidControl.getRowNb(),mMatrixAndroidControl.getColNb()));
    }
}