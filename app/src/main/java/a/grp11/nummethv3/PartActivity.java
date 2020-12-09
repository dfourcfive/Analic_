package a.grp11.nummethv3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


import java.util.List;


import a.grp11.nummethv3.ListFragment.ElemAndroidList.ElemAndroidList;
import a.grp11.nummethv3.PartData.PartsConfig;
import a.grp11.nummethv3.dialogBuilder.OprtionsAdapters.OptionAdapter;
import a.grp11.nummethv3.dialogBuilder.OptionDialogFragment;




public class PartActivity extends AppCompatActivity implements OptionDialogFragment.resulTableActivity{


    public static  final String KEY_PART_ID = "keyPartId";
    public static  final String KEY_OPERATION_ID = "keyOperatiiontitleRes";



    private Coordinator coordinator;

    public static void startActivity (int partId , int operationId ,Activity activity) {

        Intent intent = new Intent(activity,PartActivity.class);
        intent.putExtra(KEY_PART_ID,partId);
        intent.putExtra(KEY_OPERATION_ID,operationId);
        activity.startActivity(intent);
    }

    int partKey;
    int operationId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         partKey = getIntent().getIntExtra(KEY_PART_ID,0);
         operationId = getIntent().getIntExtra(KEY_OPERATION_ID,0);
         setContentView(R.layout.activity_display_part);

         setTitle(PartsConfig.getParts().getPartData(partKey).getPartTitleRes());

         Toolbar toolBar  = findViewById(R.id.toolbar);
         setSupportActionBar(toolBar);
         final ActionBar actionBar = getSupportActionBar();
         if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
         }
        coordinator = new Coordinator(this,partKey,operationId);
        System.out.println("List des elem "+ ElemAndroidList.print());
    }


    @Override
    public void setResult(List results) {

        OptionAdapter optionAdapter = PartsConfig.getSelectedPartData().
                getPartOptions().getSelectedOption().getOptionAdapter();
        optionAdapter.setResults(coordinator.getPartFragment());

    }
}
