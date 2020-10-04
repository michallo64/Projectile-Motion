package com.example.projectilemotion.objects;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class DrawView extends View {
    Paint paint = new Paint();
    ArrayList<Point> resultList = new ArrayList<Point>();
    private void init() {
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth((float) 6);
    }

    public DrawView(Context context) {
        super(context);
        init();
    }

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DrawView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public void setResultList(ArrayList<Point> arr){
        this.resultList = arr;
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        canvas.translate(0, getHeight());   // reset where 0,0 is located
        canvas.scale(1,-1);    // invert
        for(Point p:resultList){
            canvas.drawPoint((float) p.x, (float) p.y, paint);
        }
    }

    public void drawMotion(Canvas canvas){
        invalidate();
    }

}
