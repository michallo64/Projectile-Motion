package com.example.projectilemotion;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.projectilemotion.objects.AnimatedView;
import com.example.projectilemotion.objects.ExampleClient;
import com.example.projectilemotion.objects.Motion;
import com.example.projectilemotion.objects.Point;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private SeekBar velocity;
    private SeekBar angle;
    private TextView velocityLabel;
    private TextView angleLabel;
    private AnimatedView view;
    private Button tableButton;
    private Motion m = new Motion();
    private ArrayList<Point> resultList;
    private Button graphButton;
    private ExampleClient c;

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
        graphButton = findViewById(R.id.graphButton);
        seekBarListeners();
    }

    @SuppressLint("WrongConstant")
    public void compute(View v) {
        m.motionCalculation(velocity.getProgress(), angle.getProgress());
        view.setResultList(m.resultList);
        view.draw(new Canvas());
        tableButton.setVisibility(1);
        graphButton.setVisibility(1);
    }

    @SuppressLint("WrongConstant")
    public void computeServer(View v) throws URISyntaxException {
        c = new ExampleClient(new URI(
                "ws://10.0.2.2:8887"), velocity.getProgress(), angle.getProgress());
        c.connect();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                resultList = c.resultList;
                afterResult(c.resultList);
            }
        }, 3000);   //5 seconds

//        m.motionCalculation(velocity.getProgress(), angle.getProgress());
//        view.setResultList(m.resultList);
//        view.draw(new Canvas());
//        tableButton.setVisibility(1);
//        graphButton.setVisibility(1);

    }

    @SuppressLint("WrongConstant")
    public void afterResult(ArrayList<Point> resultList){
        view.setResultList(resultList);
        view.draw(new Canvas());
        tableButton.setVisibility(1);
        graphButton.setVisibility(1);
    }

    public void showTable(View v) {
        Intent intent = new Intent(this, TableActivity.class);
        intent.putExtra("ResultListExtra", c.resultList);
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

    public void showGraph(View view) {
        Intent intent = new Intent(this, GraphActivity.class);
        intent.putExtra("ResultListExtra", c.resultList);
        startActivity(intent);
        Animatoo.animateSwipeLeft(this);
    }

}