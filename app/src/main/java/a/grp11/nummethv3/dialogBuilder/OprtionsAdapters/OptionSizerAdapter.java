package a.grp11.nummethv3.dialogBuilder.OprtionsAdapters;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;


import a.grp11.nummethv3.ListFragment.ElemAndroid.ElemAndroid;
import a.grp11.nummethv3.ListFragment.ElemAndroidList.ElemAndroidListFragment;
import a.grp11.nummethv3.Linking.MatrixAndroid.MatrixAndroid;
import a.grp11.nummethv3.Linking.MatrixAndroid.MatrixAndroidFragment;
import a.grp11.nummethv3.R;

public class OptionSizerAdapter extends OptionAdapter {
    private ImageView expandableImg;
    private SeekBar mainSeekBar;
    private SeekBar rowSeekBar;
    private SeekBar colSeekBar;
    private TextView optionTitle;
    private String originalTitle;

    public OptionSizerAdapter() {
        super(R.layout.fragment_option_sizer);
    }

    @Override
    public void initOption(Context context) {
        super.initOption(context);
        expandableImg = mContainer.findViewById(R.id.option_sizer_expend);
        optionTitle = mContainer.findViewById(R.id.option_sizer_title);

        originalTitle = optionTitle.getText().toString();
        optionTitle.setText(getTitle(1,1));

        mainSeekBar= mContainer.findViewById(R.id.mainSeekBar);
        rowSeekBar = mContainer.findViewById(R.id.rowSeekBar);
        colSeekBar = mContainer.findViewById(R.id.colSeekBar);

        System.out.println("row" +rowSeekBar);

        mainSeekBar.setMax(Math.min(MatrixAndroidFragment.MAX_COL_MATRIX,MatrixAndroidFragment.MAX_ROW_MATRIX) -1);
        rowSeekBar.setMax(MatrixAndroidFragment.MAX_ROW_MATRIX - 1);
        colSeekBar.setMax(MatrixAndroidFragment.MAX_COL_MATRIX - 1);


        mainSeekBar.setOnSeekBarChangeListener(getMainSeekBarListener(rowSeekBar,colSeekBar));
        rowSeekBar.setOnSeekBarChangeListener(getSubSeekBarListener());
        colSeekBar.setOnSeekBarChangeListener(getSubSeekBarListener());

        final LinearLayout expendLayout = mContainer.findViewById(R.id.expendedLayout);

        expandableImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (expendLayout.getVisibility() == View.VISIBLE) {
                    // Its visible
                    expendLayout.setVisibility(View.GONE);
                    mainSeekBar.setVisibility(View.VISIBLE);
                } else {
                    // Either gone or invisible
                    expendLayout.setVisibility(View.VISIBLE);
                    mainSeekBar.setVisibility(View.GONE);
                }

            }
        });
    }

    private  SeekBar.OnSeekBarChangeListener getMainSeekBarListener(final SeekBar... subSeekbars){
        return new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                for (SeekBar subSeekBAr:subSeekbars) {
                    subSeekBAr.setProgress(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        };
    }
    private SeekBar.OnSeekBarChangeListener getSubSeekBarListener(){
        return new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                optionTitle.setText(getTitle(rowSeekBar.getProgress()+1,colSeekBar.getProgress()+1));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        };

    }
    @Override
    public List getResults() {
        return Arrays.asList(rowSeekBar.getProgress()+1,colSeekBar.getProgress()+1);
    }

    @Override
    public void setResults(Fragment partFragment){
            List results = getResults();
            ElemAndroid elemAndroid = this.matrixNewSizer((int)results.get(0),(int)results.get(1));

           if(elemAndroid!=null) ((ElemAndroidListFragment)partFragment).upDateChangePage(elemAndroid);
           else
            Toast.makeText(partFragment.getContext(), "Impossible to do this operation", Toast.LENGTH_SHORT).show();
    }

    public MatrixAndroid matrixNewSizer(int newRow , int newCol) {
        MatrixAndroid newMatrix;
        if (newRow > 0 && newCol > 0 && (newCol <= MatrixAndroidFragment.MAX_COL_MATRIX) && (newRow <= MatrixAndroidFragment.MAX_ROW_MATRIX)) {
            newMatrix = new MatrixAndroid(newRow, newCol);
        } else {
            newMatrix = null;
        }
        return newMatrix;
    }

    private String getTitle(int row ,int col){
        return originalTitle +" "+ row +" * "+col;
    }

}
