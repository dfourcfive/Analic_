package a.grp11.nummethv3.ListFragment.ElemAndroid;





public class ElemAndroidControl {

    protected ElemAndroid mElemAndroid;
    protected boolean haveErrorSize;
    public   final static int MAX_LENGHT_NUMBER = 6;

    public ElemAndroidControl(ElemAndroid elemAndroid) {
        mElemAndroid = elemAndroid;
        haveErrorSize =false;
    }

    public ElemAndroid getElemAndroid(){
        return mElemAndroid;
    }

    public boolean haveErrorSize() {
        return haveErrorSize;
    }

}
