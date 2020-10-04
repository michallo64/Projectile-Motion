package com.example.projectilemotion;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.projectilemotion.objects.DrawView;
import com.example.projectilemotion.objects.Motion;
import com.example.projectilemotion.objects.Point;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private SeekBar velocity;
    private SeekBar angle;
    private TextView velocityLabel;
    private TextView angleLabel;
    private DrawView view;
    private Button tableButton;
    private Motion m = new Motion();
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
        tableButton = findViewById(R.id.tableButton);
        seekBarListeners();
    }

    @SuppressLint("WrongConstant")
    public void compute(View v) {
        m.motionCalculation(velocity.getProgress(), angle.getProgress());
        view.setResultList(m.resultList);
        view.drawMotion(new Canvas());
        tableButton.setVisibility(1);
    }

    public void showTable(View v){
        Intent intent = new Intent(this, TableActivity.class);
        intent.putExtra("ResultListExtra", m.resultList);
        startActivity(intent);
        Animatoo.animateSwipeLeft(this);
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