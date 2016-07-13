package com.shishanqing.demo2;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;

/**
 * Created by shishanqing on 16-7-12.
 */
public class ScrollHideListView extends Activity {

    private final String TAG = "ScrollHideListView";
    private ListView mListView;
    private Toolbar mToolbar;
    private int mTouchSlop;
    private float mFirstY;
    private float mCurrentY;
    private int direction;
    private ObjectAnimator mAnimator;
    private boolean mShow = true;
    private String mStr[] = new String[20];

    View.OnTouchListener myTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    //触摸时操作
                    mFirstY = event.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    //移动时操作
                    mCurrentY = event.getY();
                    if(mCurrentY - mFirstY > mTouchSlop){
                        Log.d(TAG, "-------down------");
                        direction = 0;//down
                    }else if(mFirstY - mCurrentY > mTouchSlop){
                        Log.d(TAG, "-------up------");
                        direction = 1;//up
                    }
                    if(direction == 1){
                        if(mShow){
                            Log.d(TAG, "---down---mShow===" + mShow);
                            toolbarAnim(1);//hide header
                            mShow = !mShow;
                        }
                    }else if(direction == 0){
                        if(!mShow){
                            Log.d(TAG, "---up---mShow===" + mShow);
                            toolbarAnim(0);//show header
                            mShow = !mShow;
                        }
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    //离开时操作
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll_hide);

        //获取系统认为的最低滑动距离，即超过这个距离的移动，系统就将其定义为滑动状态
        mTouchSlop = ViewConfiguration.get(this).getScaledTouchSlop();

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mListView = (ListView) findViewById(R.id.listView);

        for(int i = 0; i < mStr.length; i++){
            mStr[i] = "Item" + i;
        }

        //给ListView增加一个HeaderView,避免第一个Item被Toolbar遮挡
        View header = new View(this);
        header.setLayoutParams(new AbsListView.LayoutParams(
                AbsListView.LayoutParams.MATCH_PARENT,
                (int)getResources().getDimension(
                        R.dimen.abc_action_bar_default_height_material
                )
        ));
        mListView.addHeaderView(header);
        mListView.setAdapter(new ArrayAdapter<String>(
                ScrollHideListView.this,
                android.R.layout.simple_expandable_list_item_1,
                mStr
        ));
        mListView.setOnTouchListener(myTouchListener);
    }



    //控制布局,显示隐藏的动画
    private void toolbarAnim(int flag){
        if(mAnimator != null && mAnimator.isRunning()){
            Log.d(TAG,"mAnimator != null");
            mAnimator.cancel();
        }

        if(flag == 0){
            Log.d(TAG, "flag == 0");
            mAnimator = ObjectAnimator.ofFloat(mToolbar,
                    "transltionY",mToolbar.getTranslationY(),0);
        }else if(flag == 1){
            Log.d(TAG, "flag == 1");
            mAnimator = ObjectAnimator.ofFloat(mToolbar,
                    "translationY",mToolbar.getTranslationY(),-mToolbar.getHeight());
        }

        mAnimator.start();
    }
}
