package a.grp11.nummethv3.dialogBuilder.OprtionsAdapters.OptionEditor;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import a.grp11.nummethv3.R;

public class KeyBoardBuilder {
    private LinearLayout mContainer;

    public KeyBoardBuilder(View rootView) {
        mContainer = rootView.findViewById(R.id.keyBoardContainer);
    }

    public void setEventButtonEvents(final FunctionBuilder functionBuilder){
        LinearLayout linearLayout;
        Button button;
        for (int i = 0; i < mContainer.getChildCount(); i++) {
             linearLayout = (LinearLayout) mContainer.getChildAt(i);
            for (int j = 0; j < linearLayout.getChildCount(); j++) {
                button = (Button) linearLayout.getChildAt(i);
                if(button!=null){
                    final Button finalButton = button;
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            functionBuilder.addFunctionElem(finalButton.getText().toString());
                        }
                    });

                }
            }
        }
    }
    public LinearLayout getContainer() {
        return mContainer;
    }
}
