package com.jadlsoft.jadl_tyqbq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GsonUtils.INSTANCE.init();
        findViewById(R.id.jump).setOnClickListener(v -> {
            try {
                Intent intent = getPackageManager().getLaunchIntentForPackage("com.jadl_tyxqbq.Z4_A8_61");
                intent.putExtra("tzym", "1");
                startActivity(intent);
            } catch (Exception e) {
                Log.e("qbgl", "请安装相应的apk");
            }
        });
    }
}