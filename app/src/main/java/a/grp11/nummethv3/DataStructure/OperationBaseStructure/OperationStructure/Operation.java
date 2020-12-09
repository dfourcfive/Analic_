package a.grp11.nummethv3.DataStructure.OperationBaseStructure.OperationStructure;


import a.grp11.nummethv3.DataStructure.OperationBaseStructure.OperationBase;
import a.grp11.nummethv3.DataStructure.OperationBaseStructure.OperationParam.OperationParams;
import a.grp11.nummethv3.DataStructure.OperationBaseStructure.OperationStructure.SubOperationStrcuture.SubOperations;


public class Operation extends OperationBase {

    private SubOperations subOperations;


    public Operation(int titleRes, int iconRes, int bg_colorRes, int bg_ImageRes) {
        super(titleRes, iconRes, bg_colorRes, bg_ImageRes);
        this.subOperations = null;
    }

    public Operation(int titleRes, int iconRes, int bg_colorRes, int bg_ImageRes, SubOperations subOperations ,OperationParams params) {
        super(titleRes, iconRes, bg_colorRes, bg_ImageRes, params);
        this.subOperations = subOperations;
    }

    public Operation(int titleRes, int iconRes, int bg_colorRes, int bg_ImageRes, SubOperations subOperations ) {
        super(titleRes, iconRes, bg_colorRes, bg_ImageRes);
        this.subOperations = subOperations;
    }

    public Operation(int titleRes, int iconRes,int bg_colorRes,int bg_imgRes, OperationParams params) {
        super(titleRes, iconRes,bg_colorRes,bg_imgRes, params);
        this.subOperations = null;
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

    protected void setBg_colorRes(int bg_colorRes) {
        this.bg_colorRes = bg_colorRes;
    }

    public SubOperations getSubOperations() {
        return subOperations;
    }
    public boolean haveSubOperation(){
        return subOperations!=null;
    }
}
