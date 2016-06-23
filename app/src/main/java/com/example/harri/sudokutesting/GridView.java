package com.example.harri.sudokutesting;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public abstract class GridView extends View {
    protected double cellWidth;
    protected Paint p;
    protected float dimensions;
    protected float startHeight;
    protected float startWidth;

    protected float endWidth;
    protected float endHeight;

    private int width, height;


    public GridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GridView(Context context) {
        super(context);
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        height = metrics.heightPixels;
        width = metrics.widthPixels;
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        setMeasuredDimension(measureWidth(widthMeasureSpec),
//                70                 measureHeight(heightMeasureSpec));
//    }
//
//    private int measureWidth(int measureSpec) {
//        int preferred = image.getWidth() * 2;
//        return getMeasurement(measureSpec, preferred);
//    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int desiredWidth = 100;
        int desiredHeight = 100;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        //Measure Width
        if (widthMode == MeasureSpec.EXACTLY) {
            //Must be this size
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            width = Math.min(desiredWidth, widthSize);
        } else {
            //Be whatever you want
            width = desiredWidth;
        }

        //Measure Height
        if (heightMode == MeasureSpec.EXACTLY) {
            //Must be this size
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            height = Math.min(desiredHeight, heightSize);
        } else {
            //Be whatever you want
            height = desiredHeight;
        }

        //MUST CALL THIS
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        this.p = new Paint();
        this.p.setColor(Color.BLACK);
        this.p.setStyle(Paint.Style.STROKE);


        this.dimensions = (float)(getWidth() * .9);
        this.startHeight = (float)(getHeight() * .1);
        this.startWidth = (float)(getWidth() * .05);

        this.endWidth = startWidth + dimensions;
        this.endHeight = startHeight + dimensions;

        // draw outline
        this.p.setStrokeWidth(12);
        //canvas.drawRect(startWidth, startHeight, endWidth, endHeight, p);
        //canvas.drawRect(0, 0, dimensions, dimensions, p);
        canvas.drawRect(0, 0, getWidth(), getWidth(), p);

    }

}
