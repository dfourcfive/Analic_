package a.grp11.nummethv3.PartsMenu.OperationsMenu;


import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import a.grp11.nummethv3.PartData.PartsConfig;
import a.grp11.nummethv3.R;
import a.grp11.nummethv3.DataStructure.OperationBaseStructure.OperationStructure.Operation;
import a.grp11.nummethv3.DataStructure.OperationBaseStructure.OperationStructure.Operations;
import de.hdodenhof.circleimageview.CircleImageView;


public class OperationMenuRecycleAdapter extends RecyclerView.Adapter<OperationMenuRecycleAdapter.OperationViewHolder> {

    private Operations mOperations;
    private AppCompatActivity mActivity;

    public OperationMenuRecycleAdapter(AppCompatActivity activity, int pagerId){
        mOperations = PartsConfig.getParts().getPartData(pagerId).getPartOperations();
        mActivity = activity;
    }

    public static class OperationViewHolder extends RecyclerView.ViewHolder{
        public CardView mCardView;
        public TextView mTextView;
        public CircleImageView mImageView;
        public LinearLayout mImageBackGround;
        public OperationViewHolder(View v){
            super(v);
            mCardView = (CardView) v.findViewById(R.id.card_view_operation);
            mTextView = (TextView) v.findViewById(R.id.card_view_operation_title);
            mImageView =(CircleImageView) v.findViewById(R.id.card_view_operation_icon);
            mImageBackGround = v.findViewById(R.id.operation_bg_img);
        }

    }



    @Override
    public OperationViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        OperationViewHolder vh = new OperationViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final OperationViewHolder holder, final int position){

        final Operation operationModel = mOperations.getOperation(position);

        if(operationModel!=null){

            holder.mImageView.setImageResource(operationModel.getIconRes());
            holder.mTextView.setText(operationModel.getTitleRes());
            holder.mImageView.setCircleBackgroundColorResource(operationModel.getBg_colorRes());
            holder.mCardView.setTag(position);
            holder.mCardView.setBackgroundResource(operationModel.getBg_colorRes());
            holder.mTextView.setTextColor(mActivity.getResources().getColor(operationModel.getBg_colorRes()));
            holder.mImageBackGround.setBackgroundResource(operationModel.getBg_ImageRes());
            holder.mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PartsConfig.getSelectedPartData().getPartOperations().setSelectedOperationId((int)v.getTag());
                    PartsConfig.startPartActivity(mActivity,(int)v.getTag());

                }
            });
        }
    }
    @Override
    public int getItemCount() { return mOperations.getLength(); }


}
