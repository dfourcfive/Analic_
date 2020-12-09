package a.grp11.nummethv3.Linking.FunctionAndroid;


import android.support.v4.app.Fragment;
import android.util.SparseArray;


import java.util.ArrayList;
import java.util.List;

import a.grp11.nummethv3.DataStructure.OperationBaseStructure.OperationBase;
import a.grp11.nummethv3.ListFragment.ElemAndroid.ElemAndroid;
import a.grp11.nummethv3.R;
import app.function.Function;
import app.integrale.Integrale;
import app.matrice.exceptions.MatrixMultiplicationException;
import app.matrice.exceptions.MatrixNullException;
import app.matrice.exceptions.NotInversibleMatrixException;
import app.matrice.exceptions.NotSquareMatrixException;
import app.matrice.exceptions.OperationFormException;
import app.rootFinding.RootFinding;



public class FunctionAndroid extends ElemAndroid{
    private Function mFunction;
    private SparseArray<String> paramsValue;
    private List<String> paramsId;


    private static  int sSavedId = -1; // -1 means not saved

    public FunctionAndroid(Function function) {
        mFunction = function;
        paramsValue = new SparseArray<>();
        paramsId = new ArrayList<>();
    }

    public void setFunction(Function function) {
        mFunction = function;
    }

    public Function getFunction() {
        return mFunction;
    }

    @Override
    public ElemAndroid doElemOperation(OperationBase operation) throws NotSquareMatrixException, NotInversibleMatrixException, MatrixNullException, MatrixMultiplicationException, OperationFormException {
        return null;
    }

    public FunctionAndroid doIntegralOperation(OperationBase operation , SparseArray<String > result) {
        Function resultFunction;

        switch (operation.getTitleRes()) {
            case R.string.part_integrale_operation_tra:
                Integrale trapez = new Integrale(mFunction.getStrFunc());
                resultFunction = new Function(
                        trapez.integrateTrapez(
                                Double.parseDouble(result.get(getStringHash("a"))),
                                Double.parseDouble(result.get(getStringHash("b"))),
                                Integer.parseInt(result.get(getStringHash("N"))))
                                +"");
                break;
            case R.string.part_integrale_operation_sim1:
                Integrale sim1 = new Integrale(mFunction.getStrFunc());
                resultFunction = new Function(
                        sim1.integrateSimpson1of3(
                                Double.parseDouble(result.get(getStringHash("a"))),
                                Double.parseDouble(result.get(getStringHash("b"))),
                                Integer.parseInt(result.get(getStringHash("N"))))
                                +"");
                break;
            case R.string.part_integrale_operation_sim3:
                Integrale sim2 = new Integrale(mFunction.getStrFunc());
                resultFunction = new Function(
                        sim2.integrateSimpson3of8(
                                Double.parseDouble(result.get(getStringHash("a"))),
                                Double.parseDouble(result.get(getStringHash("b"))),
                                Integer.parseInt(result.get(getStringHash("N"))))
                                +"");
                break;
            default:
                resultFunction = null;
        }

        return new FunctionAndroid(resultFunction);
    }
    public FunctionAndroid doRootOperation(OperationBase operation , SparseArray<String > result){
        Function resultFunction;

        switch (operation.getTitleRes()) {
            case R.string.part_root_operation_bis:


                resultFunction = new Function(
                        RootFinding.findRootWithBisection(
                                mFunction,
                                Double.parseDouble(result.get(getStringHash("a"))),
                                Double.parseDouble(result.get(getStringHash("b"))),
                                Integer.parseInt(result.get(getStringHash("itr"))))
                                +"");
                break;
            case R.string.part_root_operation_new:

                resultFunction = new Function(
                        RootFinding.findRootWithNewtonRaphson(
                                mFunction,
                                new Function(result.get(getStringHash("div"))),
                                Double.parseDouble(result.get(getStringHash("a"))),
                                Double.parseDouble(result.get(getStringHash("b"))),
                                Integer.parseInt(result.get(getStringHash("itr"))))
                                +"");
                break;
            case R.string.part_root_operation_sec:
                resultFunction = new Function(
                        RootFinding.findRootWithSecant(
                                mFunction,
                                Double.parseDouble(result.get(getStringHash("x0"))),
                                Double.parseDouble(result.get(getStringHash("x1"))),
                                Integer.parseInt(result.get(getStringHash("itr"))))
                                +"");
                break;
            default:
                resultFunction = null;
        }

        return new FunctionAndroid(resultFunction);
    }
    @Override
    public Fragment getFragmentElem() {
        return null;
    }

    public SparseArray<String> getParamsValue() {
        return paramsValue;
    }

    public List<String> getParamsId() {
        return paramsId;
    }

    public int getStringHash(String name){
        int sum = 0;
        for (int i = 0; i <name.length() ; i++) {
            sum+= name.codePointAt(i);
        }

        return sum;
    }

}
