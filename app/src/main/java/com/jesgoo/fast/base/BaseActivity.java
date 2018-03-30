package com.jesgoo.fast.base;

import android.app.ActivityManager;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.jesgoo.fast.manager.ActivityStack;


/**
 * Created by xupeng on 2017/12/9.
 */

public class BaseActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);  //无标题栏

//        SharedPreferences preferences = SharePreferenceManager.getApplicationSetting(this);
//
//        int theme = preferences.getInt(SharePreferenceManager.ApplicationSetting.KEY_THEME, SharePreferenceManager.ApplicationSetting.ApplicationTheme.LIGHT.getKey());
//
//        if (theme == SharePreferenceManager.ApplicationSetting.ApplicationTheme.LIGHT.getKey()) {
//            setTheme(SharePreferenceManager.ApplicationSetting.ApplicationTheme.LIGHT.getResId());
//        } else if (theme == SharePreferenceManager.ApplicationSetting.ApplicationTheme.DARK.getKey()) {
//            setTheme(SharePreferenceManager.ApplicationSetting.ApplicationTheme.DARK.getResId());
//        }
        transparentStatusBar();
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);





        ActivityStack.getInstanse().addActivity(this);
    }

    /**
     * 检测系统版本并使状态栏全透明
     */
    protected void transparentStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS |
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityStack.getInstanse().removeActivity(this);
    }


    protected void toast(String content) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onResume() {
        super.onResume();
//        MobclickAgent.onResume(this);       //友盟统计
    }


    @Override
    protected void onPause() {
        super.onPause();
//        MobclickAgent.onPause(this);
    }


}
