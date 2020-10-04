package com.example.projectilemotion;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.projectilemotion.objects.Point;

import java.util.ArrayList;

public class TableActivity extends AppCompatActivity {

    ArrayList<Point> resultList = new ArrayList<Point>();
    TableLayout table;
    ScrollView scroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        resultList = (ArrayList<Point>) getIntent().getSerializableExtra("ResultListExtra");
        table = findViewById(R.id.table);
        scroll = findViewById(R.id.scrollView);

        fillTable();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSwipeRight(this);
    }

    @SuppressLint("DefaultLocale")
    public void fillTable() {
        prepareHeaders();
        for (Point p : resultList) {
            TableRow r = new TableRow(this);
            TextView time = new TextView(this);
            TextView x = new TextView(this);
            TextView y = new TextView(this);
            time.setText(String.format("%.2f", p.time));
            x.setText(String.format("%.2f", p.x));
            y.setText(String.format("%.2f", p.y));
            time.setTextAppearance(this, R.style.TextAppearance_AppCompat_Body1);
            x.setTextAppearance(this, R.style.TextAppearance_AppCompat_Body1);
            y.setTextAppearance(this, R.style.TextAppearance_AppCompat_Body1);

            r.addView(time);
            r.addView(x);
            r.addView(y);
            table.addView(r);
        }
        scroll.scrollTo(0, scroll.getBottom());
    }

    private void prepareHeaders() {
        TableRow headers = new TableRow(this);
        TextView h1 = new TextView(this);
        h1.setText("Čas");
        TextView h2 = new TextView(this);
        h2.setText("X");
        TextView h3 = new TextView(this);
        h3.setText("Y");
        h1.setTextAppearance(this, R.style.TextAppearance_AppCompat_Body2);
        h2.setTextAppearance(this, R.style.TextAppearance_AppCompat_Body2);
        h3.setTextAppearance(this, R.style.TextAppearance_AppCompat_Body2);

        headers.addView(h1);
        headers.addView(h2);
        headers.addView(h3);
        table.addView(headers);
        table.setColumnStretchable(0, true);
        table.setColumnStretchable(1, true);
        table.setColumnStretchable(2, true);
    }

}