package a.grp11.nummethv3.DataStructure.OperationBaseStructure.OperationParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OperationParams {

    private List<OperationParam> mParams;

    public OperationParams() {
        mParams = new ArrayList<>();
    }

    private void addOperationParam(OperationParam operationParam){
        mParams.add(operationParam);
    }

    public void addOperationParam(String descrption ,String... nameFields){
        addOperationParam(new OperationParam(descrption, Arrays.asList(nameFields)));
    }

    public OperationParam getOperationParam(int index){
        return mParams.get(index);
    }
    public int getNbParams(){
        return mParams.size();
    }
    @Override
    public String toString() {
        return "OperationParams{" +
                "mParams=" + mParams +
                '}';
    }
}
