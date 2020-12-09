package a.grp11.nummethv3.Linking.FunctionAndroid;


import android.content.Context;
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
import android.text.method.TextKeyListener;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import a.grp11.nummethv3.DataStructure.OperationBaseStructure.OperationBase;
import a.grp11.nummethv3.DataStructure.OperationBaseStructure.OperationParam.OperationParam;
import a.grp11.nummethv3.DataStructure.OperationBaseStructure.OperationParam.OperationParams;
import a.grp11.nummethv3.DataStructure.OperationBaseStructure.OperationStructure.Operation;
import a.grp11.nummethv3.PartData.PartsConfig;
import a.grp11.nummethv3.R;
import app.function.Function;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;



public class FunctionAndroidFragment extends Fragment{



    private EditText mFunctionEdit;
    private LinearLayout mFunctionParamsContainer;
    private LinearLayout mFunctionResultContainer;
    private TextView mFunctionResultEdit;

    private View mRootView;
    private FunctionAndroid mFunctionAndroid;

    public FunctionAndroidFragment() {
        super();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("heelo herre");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        mRootView = inflater.inflate(R.layout.fragment_function_android, container,false);

        mFunctionAndroid = new FunctionAndroid(new Function(""));
        mFunctionEdit =  mRootView.findViewById(R.id.functionEdit);
        mFunctionResultContainer = mRootView.findViewById(R.id.functionResultContainer);
        mFunctionResultEdit = mRootView.findViewById(R.id.functionResultEdit);
        mFunctionParamsContainer = mRootView.findViewById(R.id.functionParamsContainer);

        System.out.println("selected part" + PartsConfig.getSelectedPartData());
        System.out.println("Seledcted option" + PartsConfig.getSelectedPartData().getPartOperations().getSelectedOperation());
        printParams(PartsConfig.getSelectedPartData().getPartOperations().getSelectedOperation());

        return mRootView;
    }


    public void updateFunctionAndroid() {
        mFunctionAndroid.setFunction(
                new Function(mFunctionEdit.getText().toString()));
    }

    public FunctionAndroid getElemAndroid() {
        updateFunctionAndroid();
        return mFunctionAndroid;
    }



    public void clear() {
        mFunctionEdit.setText(" ");
        mFunctionResultContainer.setVisibility(View.GONE);
    }

    private void printParams(OperationBase operationBase){
        if(operationBase.getParams()!=null){
            OperationParams params = operationBase.getParams();
            mFunctionParamsContainer.removeAllViews();
            mFunctionAndroid.getParamsValue().clear();
            mFunctionAndroid.getParamsId().clear();
            mFunctionResultContainer.setVisibility(View.GONE);

            for (int i = 0; i <params.getNbParams() ; i++) {
                mFunctionParamsContainer.addView(getParamLayout(params.getOperationParam(i)));
            }

        }
    }

    private LinearLayout getParamLayout(OperationParam operationParam){
        List<String> nameFields = operationParam.getNameFields();

        LinearLayout paramLayout = (LinearLayout) LayoutInflater
                .from(mFunctionParamsContainer.getContext()).inflate(R.layout.fragment_function_param,null);
        TextView textView = paramLayout.findViewById(R.id.paramDesText);
        textView.setText(operationParam.getDescription());

        GridLayout fildsGrid = paramLayout.findViewById(R.id.paramsFieldContainer);
        fildsGrid.setBackgroundResource(PartsConfig.getSelectedPartData().getPartColorRes());
        EditText editText;
        for (int i = 0; i < nameFields.size() ; i++) {
            editText = getEditText(nameFields.get(i));
            editText.setHint(nameFields.get(i));
            mFunctionAndroid.getParamsId().add(nameFields.get(i));
            fildsGrid.addView(editText);

            GridLayout.LayoutParams params = new GridLayout.LayoutParams(editText.getLayoutParams());
            params.rowSpec = GridLayout.spec(1,1f);
            params.columnSpec = GridLayout.spec(i,1f);
            params.setMargins(5,5,5,5);
            params.width = WRAP_CONTENT;
            params.height = WRAP_CONTENT;
            params.setGravity(Gravity.FILL);
            editText.setLayoutParams(params);
            editText.setGravity(Gravity.CENTER);

        }

        return paramLayout;
    }

    private AppCompatEditText getEditText(String id){
        AppCompatEditText editText = new AppCompatEditText(getActivity());

        editText.setId(mFunctionAndroid.getStringHash(id));

        editText.setBackgroundColor(Color.WHITE);
        editText.setTextColor(ColorStateList.valueOf(getResources().getColor(R.color.dark)));
        editText.setRawInputType(InputType.TYPE_CLASS_NUMBER);
        editText.setKeyListener(TextKeyListener.getInstance());
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(5)});

        return editText;
    }

    public SparseArray<String> getResult(){
        SparseArray<String> results = mFunctionAndroid.getParamsValue();
        List<String> paramsNames  = mFunctionAndroid.getParamsId();

        EditText editText ;
        for (String name: paramsNames) {
            editText = mFunctionParamsContainer.findViewById(mFunctionAndroid.getStringHash(name));
            results.put(mFunctionAndroid.getStringHash(name),editText.getText().toString());
        }

        System.out.println("results updated "+results);
        return results;

    }

    public void upDatePage(FunctionAndroid result) {
        mFunctionResultEdit.setText(result.getFunction().getStrFunc());
        mFunctionResultContainer.setVisibility(View.VISIBLE);
    }

    public void updateParams(Operation op) {
        printParams(op);
    }
}