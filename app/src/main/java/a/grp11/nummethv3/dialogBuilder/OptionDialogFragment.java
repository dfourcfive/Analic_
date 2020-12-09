package a.grp11.nummethv3.dialogBuilder;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.List;

import a.grp11.nummethv3.PartData.PartsConfig;
import a.grp11.nummethv3.R;
import a.grp11.nummethv3.dialogBuilder.OprtionsAdapters.OptionAdapter;

public class OptionDialogFragment extends DialogFragment {

    private static String ROW_NB_KEY= "rowNbKey";
    private static String COL_NB_KEY= "colNbKey";

    private Button validatorButton;
    private OptionAdapter mOptionAdapter;
    private LinearLayout mOptionContainer;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int theme = android.R.style.Theme_DeviceDefault_Dialog;
        setStyle(android.app.DialogFragment.STYLE_NO_TITLE,theme);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_option_dialog, container,false);
        validatorButton = rootView.findViewById(R.id.dailog_validator_button);
        mOptionContainer = rootView.findViewById(R.id.dailog_operation_container);

        mOptionAdapter =PartsConfig.getSelectedPartData().getPartOptions().getSelectedOption().getOptionAdapter();

        if(mOptionAdapter!=null) {
            mOptionAdapter.initOption(rootView.getContext());
            mOptionContainer.addView(mOptionAdapter.getContainer());
        }

        validatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCancel(getDialog());
            }
        });

        return rootView;
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        if(getActivity() instanceof resulTableActivity) {
            if(mOptionAdapter!=null)
                ((resulTableActivity) getActivity()).setResult(mOptionAdapter.getResults());
        }
        dialog.cancel();
    }


    public interface resulTableActivity{
        void setResult(List results);
    }


}
