package a.grp11.nummethv3.ListFragment.ElemAndroidList;



import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import a.grp11.nummethv3.ListFragment.ElemAndroid.ElemAndroid;
import a.grp11.nummethv3.Linking.MatrixAndroid.MatrixAndroid;
import a.grp11.nummethv3.Linking.MatrixAndroid.MatrixAndroidFragment;



public class ElemAndroidList {
    protected static List<ElemAndroid> sElemAndroids = new ArrayList<>();

    public ElemAndroidList() {
    }


    public static  void init (ElemAndroid initElem){
        //add a default matrix
        clear();
        addElemAndroid(initElem);
    }

    public static void removeFromToEnd(int postion){

        int nbRemoved = getNbElemAnrdoid()-postion;

        for (int i = 0; i <nbRemoved ; i++) {
            if(!sElemAndroids.get(postion).isSaved())
                sElemAndroids.remove(postion);
        }

    }
    public static void addElemAndroid(ElemAndroid elemAndroid){
        elemAndroid.setFilled();
        elemAndroid.setElemAndroidId(sElemAndroids.size());
        sElemAndroids.add(elemAndroid);
        System.out.println("list added "+sElemAndroids);
    }

    public static void changeElemAndroid(ElemAndroid elemAndroid , int positon){
        elemAndroid.setFilled();
        elemAndroid.setElemAndroidId(positon);
        sElemAndroids.set(positon,elemAndroid);
    }

    public static int  getNbElemAnrdoid(){
        return sElemAndroids.size();
    }

    public static ElemAndroid getElemAndroid(int elemId) {
        if(elemId>=0 && elemId<getNbElemAnrdoid() && getNbElemAnrdoid()!=0) {
            return sElemAndroids.get(elemId);
        }
        return null;
    }

    public static Fragment getFragmentElem(int elemId){
        ElemAndroid elemAndroid = sElemAndroids.get(elemId);
        if(elemAndroid instanceof MatrixAndroid){
            return MatrixAndroidFragment.newInstance(elemId);
        }
        return null;
    }

    public static void clear(){
        sElemAndroids.clear();
    }


    public static String print() {
        return sElemAndroids.toString();
    }

    public static List<ElemAndroid> getElemAndroids() {
        return sElemAndroids;
    }
}
