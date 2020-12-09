package a.grp11.nummethv3.DataStructure;

import android.support.v4.app.Fragment;


import a.grp11.nummethv3.DataStructure.OptionStructure.Option;
import a.grp11.nummethv3.DataStructure.OptionStructure.Options;
import a.grp11.nummethv3.R;
import a.grp11.nummethv3.DataStructure.OperationBaseStructure.OperationStructure.Operations;
import a.grp11.nummethv3.dialogBuilder.OprtionsAdapters.OptionEditor.OptionEditorAdapter;




public abstract class Part {

    protected  String title;
    protected int mPartTitleRes;
    protected int mPartImageRes;
    protected int mPartIconRes;
    protected int mPartColorRes;
    protected int mPartKey;

    protected Operations mOperations;
    protected Options mOptions;
    public Part() {
        mPartIconRes = R.drawable.ic_operation;
        mOperations  = new Operations();

    }


    public abstract Fragment getPartFragment();



    public Operations getPartOperations() {
        return mOperations;
    }
    public Options getPartOptions() {
        return mOptions;
    }


    public int getPartTitleRes() {
        return mPartTitleRes;
    }
    public int getPartImageRes() {
        return mPartImageRes;
    }
    public int getPartColorRes() {
        return mPartColorRes;
    }
    public String getTitle() {
        return title;
    }
    public int getPartKey() {
        return mPartKey;
    }
    public void setPartKey(int partKey) {
        mPartKey = partKey;
    }
    public int getPartIconRes() {
        return mPartIconRes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Part part = (Part) o;
        return (part.getPartKey() == this.mPartKey);
    }

    public abstract void initOperations();

    public void initOptions(){
        mOptions = new Options(mPartColorRes);
        mOptions.addOption(new Option(R.string.part_option_edit,R.drawable.ic_operation,R.string.part_option_edit,new OptionEditorAdapter()));
        mOptions.addOption(getSpecialOption());
        mOptions.addOption(new Option(R.string.part_option_help,R.drawable.ic_operation,R.string.part_option_edit,null));
        mOptions.addOption(new Option(R.string.part_option_clear,R.drawable.ic_operation,R.string.part_option_edit,null));
    }
    protected abstract Option getSpecialOption();
}
