package com.example.projectilemotion;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.projectilemotion.objects.DrawView;
import com.example.projectilemotion.objects.Motion;
import com.example.projectilemotion.objects.Point;

public class MainActivity extends AppCompatActivity {

    private SeekBar velocity;
    private SeekBar angle;
    private TextView velocityLabel;
    private TextView angleLabel;
    private DrawView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        velocity = findViewById(R.id.velocity);
        angle = findViewById(R.id.angle);
        velocityLabel = findViewById(R.id.velocityLabel);
        angleLabel = findViewById(R.id.angleLabel);
        view = findViewById(R.id.drawView);
        view.setBackgroundColor(Color.WHITE);
        seekBarListeners();
    }

    public void compute(View v) {
        Motion m = new Motion();
        m.motionCalculation(velocity.getProgress(), angle.getProgress());
        view.setResultList(m.resultList);
        view.drawMotion(new Canvas());
    }

    private void seekBarListeners() {
        velocity.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                velocityLabel.setText(String.valueOf(progress) + " m/s");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        angle.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                angleLabel.setText(String.valueOf(progress) + "\u00B0");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }
}