package com.example.sharif.imagesildeshow;

import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;


public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewPager viewPager;
    SlideshowAdapter adapter;
    CircleIndicator indicator;

    Handler handler;
    Runnable runnable;
    Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar  = findViewById(R.id.toolbar_id);
        setSupportActionBar(toolbar);

        viewPager =  findViewById(R.id.viewPager_id);
        indicator = findViewById(R.id.circleindicator);

        adapter = new SlideshowAdapter(this);

        viewPager.setAdapter(adapter);

        indicator.setViewPager(viewPager);

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                int  i = viewPager.getCurrentItem();
                if(i == (adapter.images.length - 1)){
                    i=0;
                }
                else{
                    i++;
                }

                viewPager.setCurrentItem(i,true);
            }
        };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        },4000,4000);

    }
}
