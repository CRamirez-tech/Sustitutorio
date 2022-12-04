package com.cnameless.sustitutorio;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.cnameless.sustitutorio.chart.RingChart;

public class RingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ring);

        RingChart ringChart = findViewById(R.id.ringChart);

    }
}