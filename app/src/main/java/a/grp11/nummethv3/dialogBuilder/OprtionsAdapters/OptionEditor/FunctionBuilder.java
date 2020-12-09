package a.grp11.nummethv3.dialogBuilder.OprtionsAdapters.OptionEditor;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import a.grp11.nummethv3.R;
import app.function.Function;
import dep.tree.Node;
import io.github.kexanie.library.MathView;


public  class FunctionBuilder {
    private int mBgColorRes;
    private int mTextColor;
    private CardView mFunctionContainer;
    private MathView mFunctionEditContainer;
    private String functionTexCode;

    public FunctionBuilder(int bgColorRes, int textColor,View rootView) {
        mBgColorRes = bgColorRes;
        mTextColor = textColor;

        mFunctionContainer = rootView.findViewById(R.id.functionCard);

        mFunctionContainer.setCardBackgroundColor(rootView.getResources().getColor(mBgColorRes));
        mFunctionEditContainer = mFunctionContainer.findViewById(R.id.functionEditorContainer);


    }

    public static FunctionBuilder getInstance(Function init, View rootView) {
        FunctionBuilder functionBuilder = new FunctionBuilder(
                R.color.white,
                R.color.darkLight,
               rootView
        );

        functionBuilder.setFunction(init.getTreeFunc().getRoot());
        return functionBuilder;
    }

    public void addFunctionElem(String elemStr){
            setFunction(functionTexCode+elemStr);
    }

    public void addFunction(Node functionTreeRoot){

        if(functionTreeRoot == null) return;

        if (functionTreeRoot.getLeftSon()!=null) {
            if ((!functionTreeRoot.getLeftSon().isLeaf() &&
                    Function.isComposeFunction(functionTreeRoot.getLeftSon().getValue())==-1)) {
                mFunctionEditContainer.addView(getFunctionElemSample("("));
                addFunction(functionTreeRoot.getLeftSon());
                mFunctionEditContainer.addView(getFunctionElemSample(")"));
            } else {
                addFunction(functionTreeRoot.getLeftSon());
            }
        }
        String str = functionTreeRoot.getValue();
        if(Function.isNumeric(str)){
            for (int i = 0; i <str.length() ; i++) {
                addFunctionElem(""+str.charAt(i));
            }
        } else
            addFunctionElem(functionTreeRoot.getValue());

        if (functionTreeRoot.getRightSon()!=null) {
            if (functionTreeRoot.getRightSon().isLeaf() && Function.isComposeFunction(functionTreeRoot.getRightSon().getValue()) == -1) {
                addFunction(functionTreeRoot.getRightSon());
            }else {
                mFunctionEditContainer.addView(getFunctionElemSample("("));
                addFunction(functionTreeRoot.getRightSon());
                mFunctionEditContainer.addView(getFunctionElemSample(")"));
            }
        }

    }

    public String  convertToTexCode(Node functionTreeRoot){
        String str ="";
        if(functionTreeRoot == null) return "";
        String value = functionTreeRoot.getValue();
        if(Function.isNumeric(value)) str+=value;
        else if(Function.isComposeFunction(value)!=-1){
            if(value.equals("e")) str+=("e^{" +convertToTexCode(functionTreeRoot.getRightSon())+"}");
            else if(value.equals("sqrt")) str+=("\\sqrt{" +convertToTexCode(functionTreeRoot.getRightSon())+"}");
            else str+="\\"+value+"("+convertToTexCode(functionTreeRoot.getRightSon())+")";
        }else {
            switch (value) {
                case "/":
                    str += convertToTexCode(functionTreeRoot.getLeftSon()) +
                            "\\over" + convertToTexCode(functionTreeRoot.getRightSon());
                    break;
                case "*":
                    if (Function.isNumeric(functionTreeRoot.getLeftSon().getValue())
                            && Function.isBaseFunction(functionTreeRoot.getRightSon().getValue()) != -1) {
                        str += convertToTexCode(functionTreeRoot.getLeftSon()) + convertToTexCode(functionTreeRoot.getRightSon());
                    } else {
                        str += convertToTexCode(functionTreeRoot.getLeftSon())
                                + "*" + convertToTexCode(functionTreeRoot.getRightSon());
                    }
                    break;
                default:
                    str += convertToTexCode(functionTreeRoot.getLeftSon())
                             + value+convertToTexCode(functionTreeRoot.getRightSon()) ;
                    break;
            }
        }
        return str;
    }
    public LinearLayout getFunctionElemSample(String content){
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(mFunctionContainer.getContext()).inflate(R.layout.card_function_elem_sample,null);
        CardView cardView = linearLayout.findViewById(R.id.functionElemCard);
        TextView text = cardView.findViewById(R.id.content);

        linearLayout.setTag(mFunctionEditContainer.getChildCount());

        text.setText(content);
        text.setTextColor(ColorStateList.valueOf(mFunctionContainer.getResources().getColor(mTextColor)));

        return linearLayout;
    }


    public CardView getFunctionContainer() {
        return mFunctionContainer;
    }

    public void setFunction(Node function) {
         setFunction(convertToTexCode(function));
    }
    public void setFunction(String functionStr){
        functionTexCode =functionStr;
        mFunctionEditContainer.setText("\\("+functionStr+"\\)");
    }
}
