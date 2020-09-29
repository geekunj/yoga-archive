package com.nerdlabs.yogaarchive;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.MyViewHolder> {

    final private ListItemClickListener mOnClickListener;
    private ArrayList<PosesModel> data = new ArrayList<PosesModel>();
    private static final int MAX_WIDTH = 1024;
    private static final int MAX_HEIGHT = 768;
    private Context context;

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    public CategoryListAdapter(ArrayList<PosesModel> data, Context mContext, ListItemClickListener listener){
        mOnClickListener = listener;
        this.data = data;
        this.context = mContext;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_list_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        PosesModel model = data.get(position);
        holder.textPoseName.setText(model.getPoseName());
        holder.textPoseSubName.setText(model.getPoseSubName());
        // holder.imagePoseImage.setImageResource(model.getPoseimg_res());
        Picasso.with(context).load(model.getPoseimg_res()).transform(new BitmapTransformer(MAX_WIDTH, MAX_HEIGHT)).into(holder.imagePoseImage);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textPoseName;
        TextView textPoseSubName;
        ImageView imagePoseImage;

        public MyViewHolder(View itemView){
            super(itemView);
            textPoseName = (TextView) itemView.findViewById(R.id.tv_cat_rv_list);
            textPoseSubName = (TextView) itemView.findViewById(R.id.tv_cat_subname);
            imagePoseImage = (ImageView) itemView.findViewById(R.id.iv_cat_images);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }
    }
}

