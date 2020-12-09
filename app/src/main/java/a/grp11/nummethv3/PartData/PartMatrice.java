package a.grp11.nummethv3.PartData;



import android.support.v4.app.Fragment;

import a.grp11.nummethv3.DataStructure.Part;
import a.grp11.nummethv3.ListFragment.ElemAndroidList.ElemAndroidListFragment;
import a.grp11.nummethv3.DataStructure.OptionStructure.Option;
import a.grp11.nummethv3.R;
import a.grp11.nummethv3.DataStructure.OperationBaseStructure.OperationStructure.Operation;
import a.grp11.nummethv3.DataStructure.OperationBaseStructure.OperationStructure.SubOperationStrcuture.SubOperation;
import a.grp11.nummethv3.DataStructure.OperationBaseStructure.OperationStructure.SubOperationStrcuture.SubOperations;
import a.grp11.nummethv3.dialogBuilder.OprtionsAdapters.OptionSizerAdapter;



public class PartMatrice extends Part {


    public PartMatrice() {
        super();
        title = "Matrice";
        mPartKey = -1;//-1 mean don't exist
        mPartTitleRes = R.string.part_matrice_title;
        mPartColorRes = R.color.part_matrice_color;
        mPartImageRes = R.mipmap.matrice2;
        mPartIconRes = R.drawable.matricefrag;
        initOperations();
        initOptions();
    }


    @Override
    public void initOperations() {

        SubOperations subOperations;

        subOperations = new SubOperations();
        subOperations.addOperation(new SubOperation(R.string.part_matrice_sub_op_add,R.drawable.add,mPartColorRes,R.drawable.background_img,false));
        subOperations.addOperation(new SubOperation(R.string.part_matrice_sub_op_sub,R.drawable.sub,mPartColorRes,R.drawable.background_img, false));
        subOperations.addOperation(new SubOperation(R.string.part_matrice_sub_op_mul,R.drawable.mul,mPartColorRes,R.drawable.background_img, false));

        mOperations.addOperation(
                new Operation(
                        R.string.part_matrice_operation_op,R.drawable.addsub,mPartColorRes,R.drawable.subsubaddaddsou, subOperations));

        mOperations.addOperation(new Operation(R.string.part_matrice_operation_det,R.drawable.determinant,mPartColorRes,R.drawable.subdeterminant));
        mOperations.addOperation(new Operation(R.string.part_matrice_operation_rank,R.drawable.rank,mPartColorRes,R.drawable.subrank));
        mOperations.addOperation(new Operation(R.string.part_matrice_operation_tro,R.drawable.transpose,mPartColorRes,R.drawable.subtranspose));

        subOperations = new SubOperations();
        subOperations.addOperation(new SubOperation(R.string.part_matrice_sub_ech_redEch,R.drawable.echloningreduce,mPartColorRes,R.drawable.background_img));
        subOperations.addOperation(new SubOperation(R.string.part_matrice_sub_ech_upEch,R.drawable.echloningupper,mPartColorRes,R.drawable.background_img));
        subOperations.addOperation(new SubOperation(R.string.part_matrice_sub_ech_lowEch,R.drawable.echloninglow,mPartColorRes,R.drawable.background_img));
        mOperations.addOperation(
                new Operation(
                        R.string.part_matrice_operation_ech,R.drawable.echloning,mPartColorRes,R.drawable.subaddtionsoustramult,subOperations));

        mOperations.addOperation(new Operation(R.string.part_matrice_operation_inv,R.drawable.inverse,mPartColorRes,R.drawable.subinverse));
        mOperations.addOperation(new Operation(R.string.part_matrice_operation_coMat,R.drawable.comatrice,mPartColorRes,R.drawable.subcomatrice));

    }


    @Override
    public Fragment getPartFragment() {
        return ElemAndroidListFragment.newInstance();
    }
    @Override
    protected Option getSpecialOption() {
        return new Option(R.string.part_matrice_option_sizer,R.drawable.ic_operation,R.string.part_matrice_option_sizer
                ,new OptionSizerAdapter());
    }
}
