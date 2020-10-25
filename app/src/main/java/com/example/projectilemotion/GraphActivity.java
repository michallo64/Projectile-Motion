package com.example.projectilemotion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.projectilemotion.objects.Point;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class GraphActivity extends AppCompatActivity {

    private ArrayList<Point> resultList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        resultList = (ArrayList<Point>) getIntent().getSerializableExtra("ResultListExtra");
        GraphView graph = (GraphView) findViewById(R.id.graph);
        DataPoint data[] = new DataPoint[resultList.size()];
        for(int i = 0;i<resultList.size();i++){
            data[i] = new DataPoint(resultList.get(i).x, resultList.get(i).y);
        }
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(data);
        // enable scaling
        graph.getViewport().setScalable(true);

        series.setTitle("Random Curve");
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(data[resultList.size() - 1].getX() + 10);
        graph.getGridLabelRenderer().setVerticalAxisTitle("Výška [m]");
        graph.getGridLabelRenderer().setHorizontalAxisTitle("Dráha [m]");
        graph.addSeries(series);
        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSwipeRight(this);
    }
}