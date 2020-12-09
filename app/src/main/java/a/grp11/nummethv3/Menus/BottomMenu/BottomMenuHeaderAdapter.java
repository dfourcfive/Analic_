package a.grp11.nummethv3.Menus.BottomMenu;


import android.content.res.ColorStateList;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;

import a.grp11.nummethv3.R;
import a.grp11.nummethv3.DataStructure.OperationBaseStructure.OperationStructure.Operation;
import a.grp11.nummethv3.DataStructure.OperationBaseStructure.OperationBase;
import de.hdodenhof.circleimageview.CircleImageView;

public class BottomMenuHeaderAdapter {
    private FloatingActionButton mButtonOperation ;
    private CircleImageView mExpanderButton;
    private int mCurrentState;

    public final static int STATE_NORMAL = 0;
    public final static int STATE_WAITING = 1;

    public BottomMenuHeaderAdapter(AppCompatActivity activity, Operation initOperation) {
        mButtonOperation = activity.findViewById(R.id.bottom_menu_actionButton);
        mExpanderButton = activity.findViewById(R.id.bottom_menu_header_expander);
        upDateButtonOperation(initOperation);
        mCurrentState = STATE_NORMAL;
    }



    public void upDateButtonOperation(OperationBase newOperation) {
        if(mCurrentState == STATE_NORMAL){
            mButtonOperation.setImageResource(newOperation.getIconRes());
            mButtonOperation.setBackgroundTintList(
                    ColorStateList.valueOf(mButtonOperation.getResources().getColor(newOperation.getBg_colorRes())));

        }else {
            mButtonOperation.setImageResource(R.drawable.ic_waiting);
            mButtonOperation.setBackgroundTintList(
                    ColorStateList.valueOf(mButtonOperation.getResources().getColor(R.color.success)));
        }
    }



    public FloatingActionButton getButtonOperation() {
        return mButtonOperation;
    }

    public CircleImageView getExpanderButton() {return mExpanderButton;}

    public int getCurrentState() {
        return mCurrentState;
    }

    public void setCurrentState(int newState){
        mCurrentState = newState;
    }
}
