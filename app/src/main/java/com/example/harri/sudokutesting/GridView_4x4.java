package com.example.harri.sudokutesting;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.util.AttributeSet;
import android.widget.Button;

public class GridView_4x4 extends GridView{

    Context context;
    int[] cellInfo;

    public GridView_4x4(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GridView_4x4(Context context) {
        super(context);
    }

    public void setCellInfo(int[] newInfo) {
        this.cellInfo = newInfo;
    }



    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // draw square lines

        this.p.setStrokeWidth(8);
        double boxWidth = getWidth() / 2;
        for (int i = 0; i < 2; i++) {
//            float x = (float) (this.startWidth + (i * boxWidth));
//            float y = (float) (this.startHeight + (i * boxWidth));
//            canvas.drawLine(x, this.startHeight, x, this.endHeight, p);
//            canvas.drawLine(this.startWidth, y, this.endWidth, y, this.p);
            float x = (float) (i * boxWidth);
            float y = (float) (i * boxWidth);
            canvas.drawLine(x, 0, x, getWidth(), p);
            canvas.drawLine(0, y, getWidth(), y, this.p);
        }


        // draw individual cell lines

        this.p.setStrokeWidth(3);
        this.cellWidth = getWidth() / 4;

        for (int i = 0; i < 4; i++) {
//            float x = (float) (this.startWidth + (i * cellWidth));
//            float y = (float) (this.startHeight + (i * cellWidth));
//            canvas.drawLine(x, this.startHeight, x, this.endHeight, this.p);
//            canvas.drawLine(this.startWidth, y, this.endWidth, y, this.p);
            float x = (float)(i * cellWidth);
            float y = (float)(i * cellWidth);
            canvas.drawLine(x, 0, x, getWidth(), this.p);
            canvas.drawLine(0, y, getWidth(), y, this.p);
        }
    }


}
