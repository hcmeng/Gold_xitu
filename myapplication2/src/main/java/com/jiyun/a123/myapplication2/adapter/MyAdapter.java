package com.jiyun.a123.myapplication2.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiyun.a123.myapplication2.R;
import com.jiyun.a123.myapplication2.bean.ResultBean;

import java.util.ArrayList;

/*
 Created by 郝超梦-H1810B on ${date}
*/
public class MyAdapter extends RecyclerView.Adapter <MyAdapter.MyHolder> {
    private ArrayList <ResultBean> arrayList;
    private Context context;
    public SparseBooleanArray sparse = new SparseBooleanArray();
    public boolean isCheck;

    public MyAdapter(ArrayList <ResultBean> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    public MyAdapter setCheck(boolean check) {
        isCheck = check;
        return this;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item, viewGroup, false);
        return new MyHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, final int i) {
        myHolder.mTv.setText(arrayList.get(i).getTitle());
        Glide.with(context).load(arrayList.get(i).getPic_small()).into(myHolder.mImg);
         if (isCheck){
             myHolder.cbx.setVisibility(View.VISIBLE);
             /*myHolder.cbx.setTag(i);
             myHolder.cbx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                 @Override
                 public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                     int tag = (int) compoundButton.getTag();
                     if (isChecked){
                         sparse.put(tag,true);
                     }else {
                         sparse.delete(tag);
                     }
                 }
             });
             myHolder.cbx.setChecked(sparse.get(i,false));
             if (myHolder.cbx.isChecked()){

             }*/
                 myHolder.cbx.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         if (myOnClick != null) {
                             myOnClick.onClick(arrayList.get(i),view);
                         }
                     }
                 });
         }else
             myHolder.cbx.setVisibility(View.GONE);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        ImageView mImg;
        TextView mTv;
        CheckBox cbx;


        public MyHolder(@NonNull View itemView) {
            super(itemView);
            this.mImg = (ImageView) itemView.findViewById(R.id.img);
            this.mTv = (TextView) itemView.findViewById(R.id.tv);
            this.cbx = (CheckBox) itemView.findViewById(R.id.cbx);
        }
    }

    private MyOnClick myOnClick;
    public interface MyOnClick{
        void onClick(ResultBean resultBean,View view);
    }

    public MyAdapter setMyOnClick(MyOnClick myOnClick) {
        this.myOnClick = myOnClick;
        return this;
    }
}
