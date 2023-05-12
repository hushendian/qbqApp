package com.jadlsoft.jadl_tyqbq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GsonUtils.INSTANCE.init();
        findViewById(R.id.jump).setOnClickListener(v -> {
            try {
//                Intent intent = getPackageManager().getLaunchIntentForPackage("com.jadl_tyxqbq.Z4_A8_61");
//                intent.putExtra("tzym", "1");
//                startActivity(intent);
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                ComponentName cn = new ComponentName("com.jadl_tyxqbq.Z4_A8_61","com.example.myapplication.MainActivity");
                intent.setComponent(cn);
                intent.putExtra("tzym", "1");
                startActivity(intent);
            } catch (Exception e) {
                Log.e("qbgl", "请安装相应的apk");
            }
        });
    }
}