package com.shishanqing.demo2;

import android.graphics.Bitmap;

/**
 * Created by shishanqing on 16-7-13.
 * 封装聊天内容，便于在Adapter中获取数据信息
 */
public class ChatItemListViewBean {

    private int type;
    private String text;
    private Bitmap icon;

    public ChatItemListViewBean(){

    }

    public  int getType(){
        return type;
    }

    public void setType(int type){
        this.type = type;
    }

    public String getText(){
        return text;
    }

    public void setText(String text){
        this.text = text;
    }

    public Bitmap getIcon(){
        return icon;
    }

    public void setIcon(Bitmap icon){
        this.icon = icon;
    }
}
