package com.example.android.reviewsample1;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewParent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager = findViewById(R.id.view_pager);
        CustomFragmentPagerAdapter adapter=new CustomFragmentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

    }
}
