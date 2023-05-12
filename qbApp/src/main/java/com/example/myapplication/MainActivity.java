package com.example.myapplication;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import com.jadl_tyxqbq.Z4_A8_61.R;
import com.jadlsoft.jadl_tyqbq.IQbqAidlInterface;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private IQbqAidlInterface mInterface;
    private AppCompatTextView textView;
    private static final String TAG = "qbqyy";
    private String tzym = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        bindService();
        setOnclick();
        setStatusBar();
    }

    private void initView() {
        textView = findViewById(R.id.message);
        GsonUtils.INSTANCE.init();
        //获取跳转页面的值
        tzym = getIntent().getStringExtra("tzym");
        Log.d(TAG, "跳转页面的值: " + tzym);
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    private void setOnclick() {
        findViewById(R.id.getDwxx).setOnClickListener(this);
        findViewById(R.id.getHtXmxx).setOnClickListener(this);
        findViewById(R.id.getRyxx).setOnClickListener(this);
        findViewById(R.id.setUID).setOnClickListener(this);
        findViewById(R.id.setFBH).setOnClickListener(this);
        findViewById(R.id.setQBRZ).setOnClickListener(this);
        findViewById(R.id.getJWD).setOnClickListener(this);
    }

    private boolean isAvailable() {
        return (mInterface != null && mInterface.asBinder().isBinderAlive());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.getDwxx:
                if (isAvailable()) {
                    try {
                        String dwxxContent = mInterface.getDwxx();
                        AidlResult aidlResult = GsonUtils.INSTANCE.parseResult(dwxxContent);
                        if (aidlResult.getCode() == 0) {
                            Dwxx dwxx = GsonUtils.INSTANCE.fromJson(aidlResult.getMsg(), Dwxx.class);
                            Log.d(TAG, "dwdm : " + dwxx.getDwdm() + ",dwmc: " + dwxx.getDwmc() + ",dwlx: " + dwxx.getDwlx() + ",dwlxmc: " + dwxx.getDwlxmc());
                        } else {
                            Log.e(TAG, "getDwxx error: " + aidlResult.getMsg());
                        }
                        textView.setText(dwxxContent);
                    } catch (RemoteException e) {
                        throw new RuntimeException(e);
                    }
                }

                break;
            case R.id.getHtXmxx:
                if (isAvailable()) {
                    try {
                        String htxxContent = mInterface.getHtXmxx();
                        AidlResult aidlResult = GsonUtils.INSTANCE.parseResult(htxxContent);
                        if (aidlResult.getCode() == 0) {
                            HtXmxx htXmxx = GsonUtils.INSTANCE.fromJson(aidlResult.getMsg(), HtXmxx.class);
                            Log.d(TAG, "htid : " + htXmxx.getHtid() + ",htmc: " + htXmxx.getHtmc() + ",xmbh: " + htXmxx.getXmbh() + ",xmmc: " + htXmxx.getXmmc());
                        } else {
                            Log.e(TAG, "getHtXmxx error: " + aidlResult.getMsg());
                        }
                        textView.setText(htxxContent);
                    } catch (RemoteException e) {
                        throw new RuntimeException(e);
                    }
                }
                break;
            case R.id.getRyxx:
                if (isAvailable()) {
                    try {
                        String ryxxContent = mInterface.getRyxx();
                        AidlResult aidlResult = GsonUtils.INSTANCE.parseResult(ryxxContent);
                        if (aidlResult.getCode() == 0) {
                            Ryxx ryxx = GsonUtils.INSTANCE.fromJson(aidlResult.getMsg(), Ryxx.class);
                            Log.d(TAG, "sfz : " + ryxx.getSfz() + ",xm: " + ryxx.getXm());
                        } else {
                            Log.e(TAG, "getHtXmxx error: " + aidlResult.getMsg());
                        }
                        textView.setText(ryxxContent);
                    } catch (RemoteException e) {
                        throw new RuntimeException(e);
                    }
                }
                break;
            case R.id.getJWD:
                if (isAvailable()) {
                    try {
                        String jwdxxContent = mInterface.getJWD();
                        AidlResult aidlResult = GsonUtils.INSTANCE.parseResult(jwdxxContent);
                        if (aidlResult.getCode() == 0) {
                            Jwdxx jwdxx = GsonUtils.INSTANCE.fromJson(aidlResult.getMsg(), Jwdxx.class);
                            Log.d(TAG, "longitude : " + jwdxx.getJd() + ",latitude: " + jwdxx.getWd());
                        } else {
                            Log.e(TAG, "getJWD error: " + aidlResult.getMsg());
                        }
                        textView.setText(jwdxxContent);
                    } catch (RemoteException e) {
                        throw new RuntimeException(e);
                    }
                }
                break;
            case R.id.setUID:
                if (isAvailable()) {
                    try {
                        mInterface.setUID("setUID");
                        jumpToQbqGl();
                    } catch (RemoteException e) {
                        throw new RuntimeException(e);
                    }
                }
                break;
            case R.id.setFBH:
                if (isAvailable()) {
                    try {
                        mInterface.setFBH("setFBH");
                        jumpToQbqGl();
                    } catch (RemoteException e) {
                        throw new RuntimeException(e);
                    }
                }
                break;

            case R.id.setQBRZ:
                if (isAvailable()) {
                    try {
                        mInterface.setQBRZ("setQBRZ");
                        jumpToQbqGl();
                    } catch (RemoteException e) {
                        throw new RuntimeException(e);
                    }
                }
                break;
        }

    }

    private IBinder.DeathRecipient mDeathRecipient = new IBinder.DeathRecipient() {

        @Override
        public void binderDied() {
            // 当绑定的service异常断开连接后，自动执行此方法
            Log.e(TAG, "binderDied ");
            if (mInterface != null) {
                // 当前绑定由于异常断开时，将当前死亡代理进行解绑
                mInterface.asBinder().unlinkToDeath(mDeathRecipient, 0);
                mInterface = null;
                //  重新绑定服务端的service
                bindService();
            }
        }
    };

    private void bindService() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.jadlsoft.jadl_tyqbq", "com.jadlsoft.jadl_tyqbq.QbqService"));
        bindService(intent, connection, BIND_AUTO_CREATE);
    }


    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mInterface = IQbqAidlInterface.Stub.asInterface(iBinder);
            try {
                // 注册死亡代理
                if (mInterface != null) {
                    iBinder.linkToDeath(mDeathRecipient, 0);
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);

    }

    private void jumpToQbqGl() {
        Intent intent =
                getPackageManager().getLaunchIntentForPackage("com.jadlsoft.jadl_tyqbq");
        startActivity(intent);
        finish();
    }


    void setStatusBar() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(getWindow().getDecorView().getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            // 没有这行的话 navigationBarColor 设置不成功
            getWindow().setNavigationBarContrastEnforced(false);
        }
        getWindow().setNavigationBarColor(Color.TRANSPARENT);
    }
}
