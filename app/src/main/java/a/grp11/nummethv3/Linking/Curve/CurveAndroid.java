package a.grp11.nummethv3.Linking.Curve;


import android.support.v4.app.Fragment;

import java.util.List;

import a.grp11.nummethv3.DataStructure.OperationBaseStructure.OperationBase;
import a.grp11.nummethv3.DataStructure.OperationBaseStructure.OperationParam.OperationParams;
import a.grp11.nummethv3.ListFragment.ElemAndroid.ElemAndroid;
import a.grp11.nummethv3.R;
import app.function.Function;
import app.integrale.Integrale;
import app.matrice.exceptions.MatrixMultiplicationException;
import app.matrice.exceptions.MatrixNullException;
import app.matrice.exceptions.NotInversibleMatrixException;
import app.matrice.exceptions.NotSquareMatrixException;
import app.matrice.exceptions.OperationFormException;



public class CurveAndroid extends ElemAndroid {
    private Function mFunction;
    private OperationParams params;
    private static  int sSavedId = -1; // -1 means not saved


    public CurveAndroid(Function function) {
        super();
        mFunction= function;
    }


    public Function getFunctionValues() {
        return mFunction;
    }

    public void setFunction(Function mFunction) {
        this.mFunction = mFunction;
    }



    public ElemAndroid doElemOperation(OperationBase operation , List<String> result) {
        Function resultFunction;

        switch (operation.getTitleRes()) {
            case R.string.part_adjustment_operation_expo:
                Integrale trapez = new Integrale(mFunction.getStrFunc());
                resultFunction = new Function(
                        trapez.integrateTrapez(
                                Double.parseDouble(result.get(0)),
                                Double.parseDouble(result.get(1)),
                                Integer.parseInt(result.get(2)))
                                +"");
                break;
            case R.string.part_adjustment_operation_lin:
                Integrale sim1 = new Integrale(mFunction.getStrFunc());
                resultFunction = new Function(
                        sim1.integrateSimpson1of3(
                                Double.parseDouble(result.get(0)),
                                Double.parseDouble(result.get(1)),
                                Integer.parseInt(result.get(2)))
                                +"");
                break;
            case R.string.part_adjustment_operation_poly:
                Integrale sim2 = new Integrale(mFunction.getStrFunc());
                resultFunction = new Function(
                        sim2.integrateSimpson3of8(
                                Double.parseDouble(result.get(0)),
                                Double.parseDouble(result.get(1)),
                                Integer.parseInt(result.get(2)))
                                +"");
                break;
            default:
                resultFunction = null;
        }
        System.out.println("result funstion is "+resultFunction);
        return new CurveAndroid(resultFunction);
    }

    @Override
    public ElemAndroid doElemOperation(OperationBase operation) throws NotSquareMatrixException, NotInversibleMatrixException, MatrixNullException, MatrixMultiplicationException, OperationFormException {
        return null;
    }

    @Override
    public Fragment getFragmentElem() {
        return CurveAndroidFragment.newInstance(mElemAndroidId);
    }


    @Override
    public String toString() {
        return "CurveAndroid{" +
                "mElemAndroidId=" + mElemAndroidId +
                ", mIsFilled=" + mIsFilled +
                ", mIsSaved=" + mIsSaved +
                ", mFunction=" + mFunction +
                '}';
    }
}
