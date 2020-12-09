package a.grp11.nummethv3.Linking.Test;


import android.widget.EditText;

import dep.fraction.Fraction;



public class TestAndroidControl {

    private TestAndroid mTestAndroid;
    private boolean haveErrorSize;
    public  final static int MAX_LENGHT_NUMBER = 6;

    public TestAndroidControl(TestAndroid testAndroid) {
        mTestAndroid = testAndroid;

        haveErrorSize =false;
    }


    public TestAndroid getTestAndroid(){
        return mTestAndroid;
    }

    public boolean haveErrorSize() {
        return haveErrorSize;
    }

}
