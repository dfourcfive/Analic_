package a.grp11.nummethv3.PartData;

import android.support.v4.app.Fragment;

import a.grp11.nummethv3.DataStructure.OperationBaseStructure.OperationParam.OperationParams;
import a.grp11.nummethv3.DataStructure.Part;
import a.grp11.nummethv3.DataStructure.OptionStructure.Option;
import a.grp11.nummethv3.Linking.FunctionAndroid.FunctionAndroidFragment;
import a.grp11.nummethv3.R;
import a.grp11.nummethv3.DataStructure.OperationBaseStructure.OperationStructure.Operation;



public class PartRoot extends Part {
    


    public PartRoot() {
        super();
        title = "Root";
        mPartKey = -1;//-1 mean don't exist
        mPartTitleRes = R.string.part_root_title;
        mPartColorRes = R.color.part_root_color;
        mPartImageRes = R.mipmap.rootfind;
        mPartIconRes = R.drawable.uproot;

        initOperations();
        initOptions();
    }

    @Override
    public void initOperations() {

        OperationParams params = new OperationParams();
        params.addOperationParam("The Interval","a","b");
        params.addOperationParam("Max Iteration","itr");
        mOperations.addOperation(new Operation(R.string.part_root_operation_bis,R.drawable.bisection,mPartColorRes,R.drawable.subsubbisection,params));

        params = new OperationParams();
        params.addOperationParam("The Derivate of the function" ,"div");
        params.addOperationParam("The Interval","a","b");
        params.addOperationParam("Max Iteration","itr");

        mOperations.addOperation(new Operation(R.string.part_root_operation_new,R.drawable.newton,mPartColorRes,R.drawable.subsubnewtonrap,params));

        params = new OperationParams();
        params.addOperationParam("Intial values","x0","x1");
        params.addOperationParam("Max Iteration","itr");

        mOperations.addOperation(new Operation(R.string.part_root_operation_sec,R.drawable.secant,mPartColorRes,R.drawable.subsubsecant,params));

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
