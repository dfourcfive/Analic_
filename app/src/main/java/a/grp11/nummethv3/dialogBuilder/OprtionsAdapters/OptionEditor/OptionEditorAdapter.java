package a.grp11.nummethv3.dialogBuilder.OprtionsAdapters.OptionEditor;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import java.util.List;
import a.grp11.nummethv3.R;
import a.grp11.nummethv3.dialogBuilder.OprtionsAdapters.OptionAdapter;
import app.function.Function;


public class OptionEditorAdapter extends OptionAdapter {

    private FunctionBuilder mFunctionBuilder;
    private KeyBoardBuilder mKeyBoardBuilder;
    public OptionEditorAdapter() {
        super(R.layout.fragment_option_editor);
        mFunctionBuilder = null;
        mKeyBoardBuilder = null;
    }

    @Override
    public void initOption(Context context) {
        super.initOption(context);

        mFunctionBuilder = FunctionBuilder.getInstance(
                new Function(""),
                mContainer);
        mKeyBoardBuilder = new KeyBoardBuilder(mContainer);
        mKeyBoardBuilder.setEventButtonEvents(mFunctionBuilder);
    }

    @Override
    public List getResults() {
        return null;
    }

    @Override
    public void setResults(Fragment fragment) {

    }

    @Override
    public LinearLayout getContainer() {
        return mContainer;
    }
}
