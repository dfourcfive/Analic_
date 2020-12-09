package a.grp11.nummethv3.DataStructure.OperationBaseStructure.OperationStructure.SubOperationStrcuture;


import a.grp11.nummethv3.DataStructure.OperationBaseStructure.OperationBase;
import a.grp11.nummethv3.DataStructure.OperationBaseStructure.OperationParam.OperationParams;



public class SubOperation extends OperationBase{

    public SubOperation(int titleRes, int iconRes, int bg_colorRes, int bg_ImageRes) {
        super(titleRes, iconRes, bg_colorRes, bg_ImageRes);
    }

    public SubOperation(int titleRes, int iconRes, int bg_colorRes, int bg_ImageRes, boolean isUnary) {
        super(titleRes, iconRes, bg_colorRes, bg_ImageRes, isUnary);
    }

    public SubOperation(int titleRes, int iconRes, int bg_colorRes, int bg_ImageRes, OperationParams params) {
        super(titleRes, iconRes, bg_colorRes, bg_ImageRes, params);
    }
}
