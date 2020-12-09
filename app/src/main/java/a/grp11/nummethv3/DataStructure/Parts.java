package a.grp11.nummethv3.DataStructure;

import java.util.ArrayList;
import java.util.List;

public class Parts {
    private List<Part> mPartList;
    private int selectedPartId;

    public Parts() {
        mPartList = new ArrayList<>();
        selectedPartId = -1; // note selected
    }

    public int getNbPart() {
        return mPartList.size();
    }

    public Part getPartData(int partKey){
        Part part=mPartList.get(partKey);
        if(part==null){
            return mPartList.get(0);
        }else return part;
    }

    public  void addPartData(Part part){
            part.setPartKey(getNbPart());
            System.out.println("part added "+part);
            mPartList.add(part);
        }

    public int getSelectedPartId() {
        return selectedPartId;
    }

    public void setSelectedPartId(int selectedPartId) {
        this.selectedPartId = selectedPartId;
    }

}

