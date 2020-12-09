package a.grp11.nummethv3.DataStructure.OperationBaseStructure;

import a.grp11.nummethv3.DataStructure.OperationBaseStructure.OperationParam.OperationParams;

public class OperationBase {

    protected int bg_colorRes;
    protected int bg_ImageRes;
    protected int titleRes;// will be used like id
    protected int iconRes;
    private  boolean isUnary;
    private OperationParams mParams;




    public OperationBase(int titleRes, int iconRes ,int bg_colorRes,int bg_ImageRes) {
        this.titleRes = titleRes;
        this.iconRes = iconRes;
        this.bg_colorRes = bg_colorRes;
        this.bg_ImageRes = bg_ImageRes;
        this.isUnary = true;
        mParams = null;
    }
    public OperationBase(int titleRes, int iconRes,int bg_colorRes,int bg_ImageRes,boolean isUnary) {
        this.titleRes = titleRes;
        this.iconRes = iconRes;
        this.isUnary = isUnary;
        this.bg_colorRes = bg_colorRes;
        this.bg_ImageRes = bg_ImageRes;
        mParams = null;
    }

    public OperationBase(int titleRes, int iconRes,int bg_colorRes,int bg_ImageRes,OperationParams params) {
        this.titleRes = titleRes;
        this.iconRes = iconRes;
        this.mParams = params;
        this.bg_colorRes = bg_colorRes;
        this.bg_ImageRes = bg_ImageRes;
        this.isUnary = true;
    }

    public int getTitleRes() {
        return titleRes;
    }

    public int getIconRes() {
        return iconRes;
    }

    public int getBg_colorRes() {
        return bg_colorRes;
    }

    public int getBg_ImageRes() {
        return bg_ImageRes;
    }

    public boolean isUnary() {
        return isUnary;
    }

    public OperationParams getParams() {
        return mParams;
    }
}
