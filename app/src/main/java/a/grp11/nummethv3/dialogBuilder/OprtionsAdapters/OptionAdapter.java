package a.grp11.nummethv3.dialogBuilder.OprtionsAdapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import java.util.List;



public abstract class OptionAdapter {
    protected LinearLayout mContainer;
    protected int mContainerLayout;
    public OptionAdapter(int layout) {
        mContainerLayout = layout;
        mContainer = null;
    }

    public void initOption(Context context){
        mContainer = mContainer =(LinearLayout)LayoutInflater.from(context).inflate(mContainerLayout,null);
    }
    public abstract List getResults();
    public abstract void setResults(Fragment fragment);
    public LinearLayout getContainer() {
        return mContainer;
    }
}
