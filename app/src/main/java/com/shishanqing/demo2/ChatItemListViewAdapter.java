package com.shishanqing.demo2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by shishanqing on 16-7-13.
 * 使用ViewHolder模式来提高ListView的效率
 */
public class ChatItemListViewAdapter extends BaseAdapter {

    private List<ChatItemListViewBean> mData;
    private LayoutInflater mInflater;

    public ChatItemListViewAdapter(Context context, List<ChatItemListViewBean> data){
        mData = data;
        mInflater = LayoutInflater.from(context);
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

    //用来返回第position个Item是何种类型
    @Override
    public int getItemViewType(int position) {
        ChatItemListViewBean bean = mData.get(position);
        return bean.getType();
    }

    //用来返回不同布局的总数
    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount();
    }

    //用来获取布局，通过在getView()方法中进行布局类型的判断，从而确定使用哪种布局
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null){
            holder = new ViewHolder();
            if(getItemViewType(position) == 0){
                convertView = mInflater.inflate(R.layout.chat_item_in,null);
                holder.icon = (ImageView) convertView.findViewById(R.id.icon_in);
                holder.text = (TextView) convertView.findViewById(R.id.text_in);
            }else if(getItemViewType(position) == 1){
                convertView = mInflater.inflate(R.layout.chat_item_out,null);
                holder.icon = (ImageView) convertView.findViewById(R.id.icon_out);
                holder.text = (TextView) convertView.findViewById(R.id.text_out);
            }
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.icon.setImageBitmap(mData.get(position).getIcon());
        holder.text.setText(mData.get(position).getText());
        return convertView;

    }

    public final class ViewHolder {
        public ImageView icon;
        public TextView text;
    }
}
