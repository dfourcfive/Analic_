package a.grp11.nummethv3.DataStructure.OperationBaseStructure.OperationParam;

import java.util.List;
import java.util.Objects;



public class OperationParam {
    private String description;
    private List<String> nameFields;

    public OperationParam(String description, List<String> nameFields) {
        this.description = description;
        this.nameFields = nameFields;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getNameFields() {
        return nameFields;
    }




}
