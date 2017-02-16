package com.example.administrator.suvsproject.modules.luntan.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.suvsproject.util.ImageLoaderUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2016/11/1.
 */
public abstract class CommomRecyclerAdapter<T> extends RecyclerView.Adapter<CommomRecyclerAdapter.BaseViewHolder> {

    private Context context;

    private List<T> list;

    private int layoutId;

    public CommomRecyclerAdapter(Context context, List<T> list, int layoutId) {
        this.context = context;
        this.list = list;
        this.layoutId = layoutId;
    }

    @Override
    public CommomRecyclerAdapter.BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(context).inflate(layoutId,parent,false);
        return new BaseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CommomRecyclerAdapter.BaseViewHolder holder, final int position) {
        //TODO 填充界面
        fillData(holder,position,list.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener!=null){
                    onItemClickListener.onItemClick(v,position);
                }
            }
        });
    }

    public abstract void fillData(BaseViewHolder holder, int position, T t);

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class BaseViewHolder extends RecyclerView.ViewHolder {

        public BaseViewHolder(View itemView) {
            super(itemView);
        }

        public View getView(int viewId){
            return itemView.findViewById(viewId);
        }

        public void setText(int viewId,String text){
            TextView tv= (TextView) itemView.findViewById(viewId);
            tv.setText(text);
        }

        public void setImageResource(int viewId, int drawableId)
        {
            ImageView iv = (ImageView) itemView.findViewById(viewId);
            iv.setImageResource(drawableId);
        }

        public void setImageBitmap(int viewId, Bitmap bm)
        {
            ImageView view = (ImageView) itemView.findViewById(viewId);
            view.setImageBitmap(bm);
        }

        public void setImageByUrl(int viewId, String url)
        {
            ImageView iv = (ImageView) itemView.findViewById(viewId);
            ImageLoader.getInstance().displayImage(url, iv,
                    ImageLoaderUtil.getFadeInOption(300, true, true, true));
        }

    }

    private OnItemClickListener onItemClickListener;
    
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
