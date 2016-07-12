package com.shishanqing.demo2;

/*
 *ViewHolder模式是提高ListView效率的很重要的方法。充分利用ListView的视图缓存机制，
 *避免了每次在调用getView()的时候都去通过findViewById()实例化控件。
 * 使用ViewHolder模式来优化ListView，只需要在自定义Adapter中定义一个内部类ViewHolder,
 * 并将布局中的控件作为成员变量。
 * */
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ViewHolderAdapter extends BaseAdapter {

    private List<String> mData;
    private LayoutInflater minflater;

    public ViewHolderAdapter(Context context,List<String> data){
        this.mData = data;
        minflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        //判断是否缓存
        if(convertView == null){
            holder = new ViewHolder();
            //通过LayoutInflater实例化布局
            convertView = minflater.inflate(R.layout.activity_view_holder_adapter,null);
            holder.img = (ImageView)convertView.findViewById(R.id.imageView);
            holder.title = (TextView)convertView.findViewById(R.id.textView);
            convertView.setTag(holder);
        }else {
            //通过tag找到缓存的布局
            holder = (ViewHolder)convertView.getTag();
        }
        //设置布局中控件要显示的视图
        holder.img.setBackgroundResource(R.drawable.ic_launcher);
        holder.title.setText(mData.get(position));

        return convertView;
    }

    public final class ViewHolder{
        public ImageView img;
        public TextView title;
    }
}
