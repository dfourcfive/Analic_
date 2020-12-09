package a.grp11.nummethv3.Linking.MatrixAndroid;



import android.support.v4.app.Fragment;

import a.grp11.nummethv3.ListFragment.ElemAndroid.ElemAndroid;
import a.grp11.nummethv3.ListFragment.ElemAndroidList.ElemAndroidList;
import a.grp11.nummethv3.R;
import a.grp11.nummethv3.DataStructure.OperationBaseStructure.OperationStructure.Operation;
import a.grp11.nummethv3.DataStructure.OperationBaseStructure.OperationBase;
import a.grp11.nummethv3.DataStructure.OperationBaseStructure.OperationStructure.SubOperationStrcuture.SubOperation;
import app.matrice.Matrix;
import app.matrice.exceptions.MatrixMultiplicationException;
import app.matrice.exceptions.MatrixNullException;
import app.matrice.exceptions.NotInversibleMatrixException;
import app.matrice.exceptions.NotSquareMatrixException;
import app.matrice.exceptions.OperationFormException;



public class MatrixAndroid  extends ElemAndroid{
    private  Matrix mMatrixValues;
    private  Matrix mMatrixIds;


    private static  int sSavedId = -1; // -1 means not saved


    public MatrixAndroid(int rowNb , int colNb) {
        super();
        mMatrixIds = new Matrix(rowNb, colNb);
        mMatrixValues = mMatrixIds.clone();
    }

    public MatrixAndroid(Matrix matrixValues) {
        super();
        mMatrixValues = matrixValues;
        mMatrixIds = mMatrixValues.clone();
    }



    @Override
    public ElemAndroid doElemOperation(OperationBase operation) throws NotSquareMatrixException, NotInversibleMatrixException, MatrixNullException, MatrixMultiplicationException, OperationFormException {
        Matrix resultMatrix ;

        if(operation == null) return null;

        if(operation.isUnary()){
            if(operation instanceof Operation){
                resultMatrix  = doOperation(operation.getTitleRes());
            }else if (operation instanceof SubOperation){
                resultMatrix = doSubOperation(operation.getTitleRes());
            }else resultMatrix = null;
        }else {
            resultMatrix = doOpTwoMatrix(operation.getTitleRes());
            if(resultMatrix == null){
                resultMatrix = mMatrixValues.clone();
                sSavedId = mElemAndroidId;
            }else {
                sSavedId = -1;
            }
        }


        return  new MatrixAndroid(resultMatrix);
    }

    public Matrix getMatrixValues() {
        return mMatrixValues;
    }
    public Matrix getMatrixIds() {
        return mMatrixIds;
    }

    public int getColNb(){
        return mMatrixValues.getColNb();
    }
    public int getRowNb(){
        return mMatrixValues.getRowNb();
    }



    private Matrix doOperation(int operationTitleRes) throws NotSquareMatrixException, MatrixNullException, NotInversibleMatrixException {
        Matrix resultMatrix;
        switch (operationTitleRes) {
            case R.string.part_matrice_operation_det:
                resultMatrix = new Matrix(1, 1);
                resultMatrix.setElem(0, 0, mMatrixValues.det());
                break;
            case R.string.part_matrice_operation_rank:
                resultMatrix = new Matrix(1, 1);
                resultMatrix.setElem(0, 0, mMatrixValues.rank());
                break;
            case R.string.part_matrice_operation_coMat:
                resultMatrix = mMatrixValues.coMat();
                break;
            case R.string.part_matrice_operation_inv:
                resultMatrix = mMatrixValues.inverse();
                break;
            case R.string.part_matrice_operation_ech:
                resultMatrix = mMatrixValues.echelonner();
                break;
            case R.string.part_matrice_operation_tro:
                resultMatrix = Matrix.transpose(mMatrixValues);
                break;
            default:
                resultMatrix = null;
        }
        return resultMatrix;
    }
    private Matrix doOpTwoMatrix(int operationTitleRes) throws MatrixNullException, OperationFormException, MatrixMultiplicationException {

        if(sSavedId== -1 || sSavedId == mElemAndroidId) return null;

        Matrix resultMatrix = ((MatrixAndroid)ElemAndroidList.getElemAndroid(sSavedId)).getMatrixValues();
        switch (operationTitleRes){
            case R.string.part_matrice_sub_op_add:
                resultMatrix = Matrix.operation(mMatrixValues,resultMatrix,'+');
                break;
            case R.string.part_matrice_sub_op_sub:
                resultMatrix = Matrix.operation(resultMatrix,mMatrixValues,'-');
                break;
            case R.string.part_matrice_sub_op_mul:
                resultMatrix = Matrix.mul(mMatrixValues,resultMatrix);
                break;
            default:
                resultMatrix = mMatrixValues;
        }
        return  resultMatrix;
    }
    private Matrix doSubOperation(int subOperationTitleRes) {
        Matrix resultMatrix;
        switch (subOperationTitleRes) {
            case R.string.part_matrice_sub_ech_upEch:
                resultMatrix = mMatrixValues.echelonner();
                break;
            case R.string.part_matrice_sub_ech_lowEch:
                resultMatrix = mMatrixValues.lowerEchelonner();
                break;
            case R.string.part_matrice_sub_ech_redEch:
                resultMatrix = mMatrixValues.echelonnerReduced();
                break;
            default:
                resultMatrix = null;
        }
        return resultMatrix;
    }

    @Override
    public Fragment getFragmentElem() {
        return MatrixAndroidFragment.newInstance(mElemAndroidId);
    }

    @Override
    public String toString() {
        return "MatrixAndroid{" +
                "mMatrixValues=" + mMatrixValues +
                ", mMatrixIds=" + mMatrixIds +
                ", mElemAndroidId=" + mElemAndroidId +
                ", mIsFilled=" + mIsFilled +
                ", mIsSaved=" + mIsSaved +
                ", mParams=" + mParams +
                '}';
    }
}
