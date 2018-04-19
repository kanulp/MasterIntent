package com.kanu_lp.masterintentsample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.kanu_lp.masterintentlib.SplashScreen;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bb = new Bundle();
        bb.putString("name","kanulp.github.io");

        SplashScreen setup = new SplashScreen(SplashActivity.this)
                .setBackgroundColor(R.color.colorAccent)
                .setNextActivity(MasterIntentActivity.class)
                .setSplashTimeOut(2000)
                .setFooterText("Karan Gajjar : @kanu_lp")
                .setCenterText("My cool lib", R.color.colorPrimaryDark)
                .setLogo(R.drawable.bg1)
                .setBundleExtras(bb);

        setup.getCenterTextView().setAllCaps(true);
        View myview = setup.createView();
        setContentView(myview);
    }
}
