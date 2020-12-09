package a.grp11.nummethv3.ListFragment.ElemAndroid;




import android.support.v4.app.Fragment;

import a.grp11.nummethv3.DataStructure.OperationBaseStructure.OperationBase;
import a.grp11.nummethv3.DataStructure.OperationBaseStructure.OperationParam.OperationParams;
import app.matrice.exceptions.MatrixMultiplicationException;
import app.matrice.exceptions.MatrixNullException;
import app.matrice.exceptions.NotInversibleMatrixException;
import app.matrice.exceptions.NotSquareMatrixException;
import app.matrice.exceptions.OperationFormException;



public abstract class ElemAndroid {
    protected int mElemAndroidId;
    protected boolean mIsFilled;
    protected boolean mIsSaved;
    protected OperationParams mParams;

    protected static  int sSavedId = -1; // -1 means not saved


    public ElemAndroid() {
        mElemAndroidId = -1;// -1 mean not add yet
        mIsFilled = false;
        mIsSaved = false;
        mParams = null;
    }


    public void setElemAndroidId(int elemAndroidId) {
        mElemAndroidId = elemAndroidId;
    }
    public int getElemAndroidId() {
        return mElemAndroidId;
    }

    public boolean isFilled() {
        return mIsFilled;
    }
    public boolean isSaved() {
        return mIsSaved;
    }

    public void setFilled() {
        mIsFilled = true;
    }
    public void setSaved(){
        mIsSaved = true;
    }


    public abstract ElemAndroid doElemOperation(OperationBase operation) throws NotSquareMatrixException, NotInversibleMatrixException, MatrixNullException, MatrixMultiplicationException, OperationFormException;
    public abstract Fragment getFragmentElem();

    @Override
    public String toString() {
        return "ElemAndroid{" +
                "mElemAndroidId=" + mElemAndroidId +
                ", mIsFilled=" + mIsFilled +
                ", mIsSaved=" + mIsSaved +
                '}';
    }
}
