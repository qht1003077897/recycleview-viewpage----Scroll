package com.example.recycleviewtest;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder>
{
	private Context context;
	private List<String> mDatas;
	private OnItemClickerListener onItemClickerListener;
	public interface OnItemClickerListener{
		void OnItemClicker(View view,int position);
	}
	public void setOnItemClickerListener(OnItemClickerListener onItemClickerListener){
		this.onItemClickerListener=onItemClickerListener;
	}
	
	public HomeAdapter(Context context,List<String> mDatas){
		this.context=context;
		this.mDatas=mDatas;
	}
	@Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
        		context).inflate(R.layout.item, parent,
                false));
        return holder;
    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position)
    {
    	LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
 	            (int)(Constant.displayWidth*0.2), 
 	            (int)(Constant.recycleviewHeight*0.25));
    	holder.itemView.setLayoutParams(params);
        holder.tv.setText(mDatas.get(position));
        if(onItemClickerListener!=null){
        	  holder.itemView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					int p=holder.getLayoutPosition();
					onItemClickerListener.OnItemClicker(holder.itemView, p);
				}
			});
        }
      
    }
    @Override
    public int getItemCount()
    {
        return mDatas.size();
    }
    class MyViewHolder extends ViewHolder
    {
        TextView tv;
        public MyViewHolder(View view)
        {
            super(view);
            tv = (TextView) view.findViewById(R.id.item_recyclerview);
        
        }
    }
}
