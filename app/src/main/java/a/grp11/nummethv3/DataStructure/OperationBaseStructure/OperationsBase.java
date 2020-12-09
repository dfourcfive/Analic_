package a.grp11.nummethv3.DataStructure.OperationBaseStructure;



import java.util.ArrayList;
import java.util.List;

import a.grp11.nummethv3.DataStructure.OperationBaseStructure.OperationStructure.Operation;




public class OperationsBase<T> {
    private List<T> mListOperation;
    protected  int selectedOperationId;

    public OperationsBase() {
        mListOperation = new ArrayList<>();
        selectedOperationId = -1;

    }

    public OperationsBase(List<T> listOperation) {
        mListOperation = listOperation;
        selectedOperationId = -1;
    }


    public void addOperation(T operation){
            if(operation!=null){
                mListOperation.add(operation);
            }
    }
    public void addOperations(ArrayList<T> listOperation){
        mListOperation.addAll(listOperation);
    }

    public int getLength(){
        return mListOperation.size();
    }

    public T getOperation(int operationId){
        if(operationId>=0&&operationId<getLength())
            return mListOperation.get(operationId);
        else return null;
    }


    public T getSelectedOperation() {
        if(selectedOperationId != -1) return mListOperation.get(selectedOperationId);
        else return null;
    }

    public void setSelectedOperationId(int selectedOperation) {
        System.out.println("operation selected");
        this.selectedOperationId = selectedOperation;
    }

}
