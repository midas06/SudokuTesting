package com.example.harri.sudokutesting;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

public class CustomGrid_4x4 extends CustomGrid {

    Context context;
    int[] cellInfo;

    public CustomGrid_4x4(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomGrid_4x4(Context context) {
        super(context);
    }


    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // draw square lines
        this.p.setStrokeWidth(8);
        double boxWidth = getWidth() / 2;
        for (int i = 0; i < 2; i++) {
            float x = (float) (i * boxWidth);
            float y = (float) (i * boxWidth);
            canvas.drawLine(x, 0, x, getWidth(), p);
            canvas.drawLine(0, y, getWidth(), y, this.p);
        }


        // draw individual cell lines
        this.p.setStrokeWidth(3);
        this.cellWidth = getWidth() / 4;

        for (int i = 0; i < 4; i++) {
            float x = (float)(i * cellWidth);
            float y = (float)(i * cellWidth);
            canvas.drawLine(x, 0, x, getWidth(), this.p);
            canvas.drawLine(0, y, getWidth(), y, this.p);
        }
    }


}
