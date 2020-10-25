package com.example.projectilemotion.objects;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.example.projectilemotion.R;

import java.util.ArrayList;

@SuppressLint("AppCompatCustomView")
public class AnimatedView extends ImageView {

    private Handler h;
    private Context mContext;
    private BitmapDrawable ball;
    private Paint paint = new Paint();
    private final int FRAME_RATE = 30;
    private ArrayList<Point> resultList = new ArrayList<Point>();
    private int i = 0;
    private float x=0, y=0;

    public AnimatedView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        h = new Handler();
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ball2);
        ball = new BitmapDrawable(context.getResources(), bitmap);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(3f);
        paint.setStyle(Paint.Style.FILL);
    }

    public void setResultList(ArrayList<Point> resultList) {
        x=0;
        y=0;
        i=0;
        h.removeCallbacks(r);
        this.resultList = resultList;
    }

    private Runnable r = this::invalidate;

    protected void onDraw(Canvas c) {
        c.translate(0, getHeight());   // reset where 0,0 is located
        c.scale(1,-1);    // invert
        if (i < resultList.size()) {
            x = (float) resultList.get(i).x;
            y = (float) resultList.get(i).y;
            i += 1;
        }
        c.drawBitmap(ball.getBitmap(), x, y, null);
        h.postDelayed(r, FRAME_RATE);
    }
}
