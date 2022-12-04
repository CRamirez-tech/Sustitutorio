package com.cnameless.sustitutorio.chart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class RingChart extends View implements View.OnTouchListener {
    private static final String TAG = "RingChart";
    private float WIDTH;
    private float HEIGHT;
    private Paint sectionPaint;
    private RectF arcRectangle;
    private Paint circlePaint;
    private Paint labelPaint;
    private Paint recHora;
    private Paint recMinuto;

    public RingChart(Context context) {
        super(context);
        init();
        this.setOnTouchListener(this);
    }

    public RingChart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        String layout_height = attrs.getAttributeValue("http://schemas.android.com/apk/res/android", "layout_height");
        String layout_width = attrs.getAttributeValue("http://schemas.android.com/apk/res/android", "layout_width");

        layout_height = layout_height.replace("dip", "");
        layout_width = layout_width.replace("dip", "");

        float density = getResources().getDisplayMetrics().density;
        HEIGHT = density * Float.parseFloat(layout_height);
        WIDTH = density * Float.parseFloat(layout_width);
        Log.d(TAG, "WIDTH:" + WIDTH + ",HEIGHT:" + HEIGHT);
        init();
    }

    private void init() {

        sectionPaint = new Paint();
        circlePaint = new Paint();
        labelPaint = new Paint();
        recHora = new Paint();
        recMinuto = new Paint();

        circlePaint.setColor(Color.WHITE);
        labelPaint.setColor(Color.BLACK);
        labelPaint.setTextSize(40f);
        recHora.setColor(Color.RED);
        recMinuto.setColor(Color.BLUE);

        //Center graphic
        float ring_squard_size = Math.min(WIDTH, HEIGHT);
        float originX = (WIDTH - ring_squard_size) / 2;
        float originY = (HEIGHT - ring_squard_size) / 2;

        arcRectangle = new RectF(originX, originY, ring_squard_size + originX, ring_squard_size + originY);
    }

    private void drawSections(Canvas canvas) {
        Color color = Color.valueOf(255,204,153);
        sectionPaint.setColor(color.toArgb());
        canvas.drawArc(arcRectangle, 0, 360, true, sectionPaint);
        float factorX = 15f;
        float factorY = 20f;
        float x = WIDTH / 2 - factorX;
        float y = HEIGHT / 2 + factorY;
        float r = (Math.min(arcRectangle.height(), arcRectangle.width()) / 2f) * 0.85f;
        canvas.drawText("3",x+r,y,labelPaint);
        canvas.drawText("6",x,y+r,labelPaint);
        canvas.drawText("9",x-r,y,labelPaint);
        canvas.drawText("12",x-factorX/2,y-r,labelPaint);
    }

    private void drawInnerCircle(Canvas canvas) {
        float centerX = arcRectangle.centerX();
        float centerY = arcRectangle.centerY();
        float r = (Math.min(arcRectangle.height(), arcRectangle.width()) / 2f) * 0.7f;
        canvas.drawCircle(centerX, centerY, r, circlePaint);
    }
    private void drawHora(Canvas canvas) {
        float x = WIDTH / 2;
        float y = HEIGHT / 2;
        float r = (Math.min(arcRectangle.height(), arcRectangle.width()) / 2f) * 0.85f;
        canvas.drawRect(x-10f, y-r,x+10f,y, recHora);
    }
    private void drawMinuto(Canvas canvas) {
        float x = WIDTH / 2;
        float y = HEIGHT / 2;
        float r = (Math.min(arcRectangle.height(), arcRectangle.width()) / 2f) * 0.45f;
        canvas.drawRect(x-10f, y-r,x+10f,y, recMinuto);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        drawSections(canvas);
        drawInnerCircle(canvas);
        drawMinuto(canvas);
        drawHora(canvas);
        invalidate();
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        Log.d("TAG","Clicked");
        return true;
    }
}
