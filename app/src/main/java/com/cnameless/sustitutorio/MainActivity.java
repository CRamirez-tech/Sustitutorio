package com.cnameless.sustitutorio;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnRingChart = findViewById(R.id.btnRingChart);
        btnRingChart.setOnClickListener(btnRingChart_Event);

    }

    private View.OnClickListener btnRingChart_Event = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), RingActivity.class);
            startActivity(intent);
        }
    };
}