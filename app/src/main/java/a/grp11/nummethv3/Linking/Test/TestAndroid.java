package a.grp11.nummethv3.Linking.Test;


import android.support.v4.app.Fragment;

import a.grp11.nummethv3.DataStructure.OperationBaseStructure.OperationBase;
import a.grp11.nummethv3.ListFragment.ElemAndroid.ElemAndroid;
import app.matrice.exceptions.MatrixMultiplicationException;
import app.matrice.exceptions.MatrixNullException;
import app.matrice.exceptions.NotInversibleMatrixException;
import app.matrice.exceptions.NotSquareMatrixException;
import app.matrice.exceptions.OperationFormException;



public class TestAndroid extends ElemAndroid {
    private  int mValue;


    private static  int sSavedId = -1; // -1 means not saved


    public TestAndroid(int value) {
        super();
        mValue = value;
    }




    @Override
    public ElemAndroid doElemOperation(OperationBase operation) throws NotSquareMatrixException, NotInversibleMatrixException, MatrixNullException, MatrixMultiplicationException, OperationFormException {
       return new TestAndroid(2);
    }

    public int getValue() {
        return mValue;
    }


    @Override
    public Fragment getFragmentElem() {
        return TestAndroidFragment.newInstance(mElemAndroidId);
    }

    @Override
    public String toString() {
        return "TestAndroid{" +
                "mValue=" + mValue +
                '}';
    }
}
