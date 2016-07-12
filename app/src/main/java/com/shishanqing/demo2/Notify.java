package com.shishanqing.demo2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shishanqing on 16-7-12.
 */
public class Notify extends Activity{
    private final String TAG = "Notify";
    private List<String> mData;
    private ListView mListView;
    private ViewHolderAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.notify);
        mData = new ArrayList<String>();
        for(int i = 0; i < 20; i++){
            mData.add("" + i);
        }
        mListView = (ListView) findViewById(R.id.listView);
        mAdapter = new ViewHolderAdapter(this, mData);
        mListView.setAdapter(mAdapter);
        //mListView.setSelection(N);设置listview默认显示第 N 个Item

        //遍历ListView中的所有Item，通过getChildAt()来获取第 i 个子View
        for (int i = 0; i < mListView.getChildCount(); i ++){
            View view = mListView.getChildAt(i);
        }

        /* 动态修改ListView
        mData.add("new");
        mAdapter.notifyDataSetChanged();
        */
    }

    //动态添加Item
    public void btnAdd(View view){
        mData.add("new");
        mAdapter.notifyDataSetChanged();
        mListView.setSelection(mData.size() - 1);
    }

    //动态删除Item
    public void btnRemove(View view){
        mData.remove("new");
        mAdapter.notifyDataSetChanged();
        mListView.setSelection(mData.size() - 1);
    }
}
