package a.grp11.nummethv3.DataStructure.OptionStructure;


import java.util.ArrayList;
import java.util.List;

public class Options {
    private List<Option> mOptionList;
    private int mOptionColorRes;
    private int selectedOptionId;

    public Options(int optionColorRes) {
        mOptionList = new ArrayList<>();
        mOptionColorRes = optionColorRes;
    }

    public Options (List<Option> listOperation) {
        mOptionList = listOperation;
    }


    public void addOption(Option option){
        if(option!=null){
            option.setOptionKey(mOptionList.size());
            mOptionList.add(option);
        }
    }

    public void addOptions(ArrayList<Option> listOptions){
        mOptionList.addAll(listOptions);
    }

    public int getNbOptions(){
        return mOptionList.size();
    }

    public Option getOption (int optionId){
        if(optionId>=0&&optionId<getNbOptions())
            return mOptionList.get(optionId);
        else return null;
    }

    public int getOptionColorRes() {
        return mOptionColorRes;
    }

    public int getSelectedOptionId() {
        return selectedOptionId;
    }

    public void setSelectedOptionId(int selectedOptionId) {
        this.selectedOptionId = selectedOptionId;
    }

    public Option getSelectedOption(){
        return mOptionList.get(selectedOptionId);
    }
}
