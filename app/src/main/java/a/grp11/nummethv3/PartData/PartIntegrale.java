package a.grp11.nummethv3.PartData;

import android.support.v4.app.Fragment;

import a.grp11.nummethv3.DataStructure.OperationBaseStructure.OperationParam.OperationParams;
import a.grp11.nummethv3.DataStructure.Part;
import a.grp11.nummethv3.DataStructure.OptionStructure.Option;
import a.grp11.nummethv3.Linking.FunctionAndroid.FunctionAndroidFragment;
import a.grp11.nummethv3.R;
import a.grp11.nummethv3.DataStructure.OperationBaseStructure.OperationStructure.Operation;



public class PartIntegrale extends Part {


    public PartIntegrale() {
        super();
        title = "Integrale";
        mPartKey = -1;//-1 mean don't exist
        mPartTitleRes = R.string.part_integrale_title;
        mPartColorRes = R.color.part_integrale_color;
        mPartImageRes = R.mipmap.mainintegral;
        mPartIconRes = R.drawable.integaral;
        initOperations();
        initOptions();
    }

    @Override
    public void initOperations() {
        OperationParams params = new OperationParams();
        params.addOperationParam("The Interval","a","b");
        params.addOperationParam("The Precision ","N");

        System.out.println("params "+ params);

        mOperations.addOperation(
                new Operation(R.string.part_integrale_operation_tra, R.drawable.trap, mPartColorRes,R.drawable.subsubtrapezoidal,params));
        mOperations.addOperation(new Operation(R.string.part_integrale_operation_sim1, R.drawable.s18, mPartColorRes,R.drawable.subsubsim18,params));
        mOperations.addOperation(new Operation(R.string.part_integrale_operation_sim3, R.drawable.s38, mPartColorRes,R.drawable.subsubsim38,params));
    }


    @Override
    protected Option getSpecialOption() {
        return new Option(R.string.part_integrale_option_graph,R.drawable.ic_operation,R.string.part_integrale_option_graph,null);
    }

    @Override
    public Fragment getPartFragment() {
        return new FunctionAndroidFragment();
    }
}


