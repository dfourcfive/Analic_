package a.grp11.nummethv3.Linking.MatrixAndroid;



import android.widget.EditText;

import a.grp11.nummethv3.ListFragment.ElemAndroidList.ElemAndroidList;

import dep.fraction.Fraction;



public class MatrixAndroidControl {

    private MatrixAndroid mMatrixAndroid;
    private boolean haveErrorSize;
    public  final static int MAX_LENGHT_NUMBER = 6;

    public MatrixAndroidControl(MatrixAndroid matrixAndroid) {
        mMatrixAndroid = matrixAndroid;

        haveErrorSize =false;
    }

    public int getColNb(){
        return mMatrixAndroid.getColNb();
    }
    public int getRowNb(){
        return mMatrixAndroid.getRowNb();
    }

    public void setToMatrixValues(int x , int y, EditText editText){
        if(editText.getText().length()>0 && !editText.getText().toString().contains(".")){
            mMatrixAndroid.getMatrixValues().setElem(x,y,new Fraction(editText.getText().toString()));
        }else if(editText.getText().length()>0) {
            mMatrixAndroid.getMatrixValues().setElem(x,y,
                    ((MatrixAndroid)ElemAndroidList.getElemAndroid(
                            mMatrixAndroid.getElemAndroidId())).getMatrixValues().getElem(x,y));
        }else
            mMatrixAndroid.getMatrixValues().setElem(x,y,new Fraction(0));

    }

    public void setToMatrixId(int x ,int y ,int id){
        mMatrixAndroid.getMatrixIds().setElem(x,y,new Fraction(id));
    }

    public void setToEditText(int x , int y , EditText editText){
        if(mMatrixAndroid.isFilled() && !mMatrixAndroid.getMatrixValues().getElem(x,y).isNul()){
            String value = mMatrixAndroid.getMatrixValues().getElem(x,y).toString();

            if(value.length()>MAX_LENGHT_NUMBER) {
                if(mMatrixAndroid.getMatrixValues().getElem(x,y).getB()==1){
                    value = value.substring(0,MAX_LENGHT_NUMBER-3)+"...";
                }else
                    value = Math.ceil(mMatrixAndroid.getMatrixValues().getElem(x, y).getresultFraction())+"";
                haveErrorSize =true;
            }
            editText.setText(value);
        }else{
            editText.setHint("0");
        }
    }


    public MatrixAndroid getMatrixAndroid(){
        return mMatrixAndroid;
    }

    public boolean haveErrorSize() {
        return haveErrorSize;
    }

}
