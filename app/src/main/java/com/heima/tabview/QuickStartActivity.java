package com.heima.tabview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;
import com.heima.tabview.library.TabView;
import com.heima.tabview.library.TabViewChild;
import java.util.ArrayList;
import java.util.List;


public class QuickStartActivity extends FragmentActivity {
    TabView tabView;
    private FragmentManager fragmentManager;

    List<TabViewChild> tabViewChildList=new ArrayList<>();

    private int currentIndex = 0;
    private static final String CURRENT_FRAGMENT = "STATE_FRAGMENT_SHOW";

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quick_start_activity);

        tabView= (TabView) findViewById(R.id.tabView);
        fragmentManager=getSupportFragmentManager();

        //start add data
//        List<TabViewChild> tabViewChildList=new ArrayList<>();
//        TabViewChild tabViewChild01=new TabViewChild(R.drawable.home_sel,R.drawable.tab01_unsel,"首页",  FragmentCommon.newInstance("首页"));
//        TabViewChild tabViewChild02=new TabViewChild(R.drawable.lock_sel,R.drawable.lock_unsel,"我的地锁",  FragmentCommon.newInstance("分类"));
//        TabViewChild tabViewChild05=new TabViewChild(R.drawable.my_sel,R.drawable.my_unsel,"我",  FragmentCommon.newInstance("我的"));
//        tabViewChildList.add(tabViewChild01);
//        tabViewChildList.add(tabViewChild02);
//        tabViewChildList.add(tabViewChild05);
//
//        //end add data
//        tabView.setTabViewDefaultPosition(1);
//
//        tabView.setTabViewChild(tabViewChildList,fragmentManager);
//        tabView.setOnTabChildClickListener(new TabView.OnTabChildClickListener() {
//            @Override
//            public void onTabChildClick(int  position, ImageView currentImageIcon, TextView currentTextView) {
//                // Toast.makeText(getApplicationContext(),"position:"+position,Toast.LENGTH_SHORT).show();
//            }
//        });

        if (savedInstanceState != null) { // “内存重启”时调用
            Log.e("!!!!!","内存重启");


            TabViewChild tabViewChild01=new TabViewChild(R.drawable.home_sel,R.drawable.tab01_unsel,"首页", fragmentManager.findFragmentByTag(0+""));
            TabViewChild tabViewChild02=new TabViewChild(R.drawable.lock_sel,R.drawable.lock_unsel,"我的地锁",  fragmentManager.findFragmentByTag(1+""));
            TabViewChild tabViewChild05=new TabViewChild(R.drawable.my_sel,R.drawable.my_unsel,"我",  fragmentManager.findFragmentByTag(2+""));
            tabViewChildList.removeAll(tabViewChildList);
            tabViewChildList.add(tabViewChild01);
            tabViewChildList.add(tabViewChild02);
            tabViewChildList.add(tabViewChild05);

            //获取“内存重启”时保存的索引下标
            currentIndex = savedInstanceState.getInt(CURRENT_FRAGMENT,0);
            Log.e("currentIndex", "!!!!"+currentIndex);

            //end add data
            tabView.setTabViewDefaultPosition(currentIndex);


            tabView.setTabViewChild(tabViewChildList,fragmentManager,true);


        }else{      //正常启动时调用
            Log.e("!!!!!","内存没重启");

            //start add data
            TabViewChild tabViewChild01=new TabViewChild(R.drawable.home_sel,R.drawable.tab01_unsel,"首页",  FragmentCommon.newInstance("首页"));
            TabViewChild tabViewChild02=new TabViewChild(R.drawable.lock_sel,R.drawable.lock_unsel,"我的地锁",  FragmentCommon.newInstance("分类"));
            TabViewChild tabViewChild05=new TabViewChild(R.drawable.my_sel,R.drawable.my_unsel,"我",  FragmentCommon.newInstance("我的"));
            tabViewChildList.add(tabViewChild01);
            tabViewChildList.add(tabViewChild02);
            tabViewChildList.add(tabViewChild05);
            //end add data
            tabView.setTabViewDefaultPosition(1);

            tabView.setTabViewChild(tabViewChildList,fragmentManager,false);
            tabView.setOnTabChildClickListener(new TabView.OnTabChildClickListener() {
                @Override
                public void onTabChildClick(int  position, ImageView currentImageIcon, TextView currentTextView) {
                    // Toast.makeText(getApplicationContext(),"position:"+position,Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        //“内存重启”时保存当前的fragment名字
        outState.putInt(CURRENT_FRAGMENT,tabView.getCurrentTabIndex());
        Log.e("onSaveInstanceState", "!!!!");
        super.onSaveInstanceState(outState);
    }

    public  int dip2px(float dpValue)
    {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
