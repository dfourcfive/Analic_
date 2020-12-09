package a.grp11.nummethv3.PartData;

import android.support.v4.app.Fragment;

import a.grp11.nummethv3.DataStructure.Part;
import a.grp11.nummethv3.DataStructure.OptionStructure.Option;
import a.grp11.nummethv3.Linking.Curve.CurveAndroidFragment;
import a.grp11.nummethv3.R;
import a.grp11.nummethv3.DataStructure.OperationBaseStructure.OperationStructure.Operation;



public class PartAdjustment extends Part {


    public PartAdjustment() {
        super();
        title = "adjustment";
        mPartKey = -1;//-1 mean don't exist
        mPartTitleRes = R.string.part_adjustment_title;
        mPartColorRes = R.color.part_adjustment_color;
        mPartImageRes = R.mipmap.main_curve;
        mPartIconRes = R.drawable.curvefit;
        initOperations();
        initOptions();
    }


    @Override
    public void initOperations() {
        mOperations.addOperation(new Operation(R.string.part_adjustment_operation_poly, R.drawable.polynom, mPartColorRes,R.drawable.subsubpolynom));
        mOperations.addOperation(new Operation(R.string.part_adjustment_operation_lin, R.drawable.linear, mPartColorRes,R.drawable.subsublinear));
        mOperations.addOperation(new Operation(R.string.part_adjustment_operation_expo, R.drawable.expo, mPartColorRes,R.drawable.subsubexpo));
    }


    @Override
    protected Option getSpecialOption() {
        return new Option(R.string.part_integrale_option_graph,R.drawable.ic_operation,R.string.part_integrale_option_graph,null);
    }

    @Override
    public Fragment getPartFragment() {
        return new CurveAndroidFragment();
    }


}
