package a.grp11.nummethv3.Linking.Curve;


import android.widget.EditText;

import a.grp11.nummethv3.Linking.Curve.CurveAndroid;
import app.function.Function;


public class CurveAndroidControl {

    private CurveAndroid mCurveAndroid;

    public CurveAndroidControl(CurveAndroid curveAndroid) {
        mCurveAndroid = curveAndroid;
    }


    public void setToFunction(EditText editText){
        if(editText.getText().length()>0) {
            mCurveAndroid.setFunction(new Function(editText.toString()));
        }else{
            mCurveAndroid.setFunction(new Function(""));
        }
    }

    public void setToFunctionEditText(EditText editText){
        if(mCurveAndroid.isFilled() && !mCurveAndroid.getFunctionValues().getStrFunc().equals("")){
            String value = mCurveAndroid.getFunctionValues().getStrFunc();
            editText.setText(value);
        }else{
            editText.setHint("x");
        }
    }

    public CurveAndroid getIntegralAndroid() {
        return mCurveAndroid;
    }
}
