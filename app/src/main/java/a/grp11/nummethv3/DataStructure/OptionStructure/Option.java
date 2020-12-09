package a.grp11.nummethv3.DataStructure.OptionStructure;

import a.grp11.nummethv3.dialogBuilder.OprtionsAdapters.OptionAdapter;

public class Option {

    protected int mOptionTitleRes;
    protected int mOptionIconRes;
    protected int mDescriptionTextRes;
    protected int mOptionKey;
    protected OptionAdapter mOptionAdapter;

    public Option(int optionTitleRes, int optionIconRes ,int descriptionTextRes ,OptionAdapter optionAdapter){
        mOptionTitleRes = optionTitleRes;
        mOptionIconRes = optionIconRes;
        mDescriptionTextRes = descriptionTextRes;
        mOptionAdapter = optionAdapter;
        mOptionKey = -1;
    }

    public int getTitleRes() {
        return mOptionTitleRes;
    }

    public int getIconRes() {
        return mOptionIconRes;
    }


    public int getOptionKey() {
        return mOptionKey;
    }

    public void setOptionKey(int optionKey) {
        mOptionKey = optionKey;
    }

    public OptionAdapter getOptionAdapter() {
        return mOptionAdapter;
    }

    public int getOptionTitleRes() {
        return mOptionTitleRes;
    }

    public int getOptionIconRes() {
        return mOptionIconRes;
    }

    public int getDescriptionTextRes() {
        return mDescriptionTextRes;
    }
}
