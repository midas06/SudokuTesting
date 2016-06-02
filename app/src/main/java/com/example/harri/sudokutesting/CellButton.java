package com.example.harri.sudokutesting;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.LinearLayout;


public class CellButton extends Button {
    public CellButton(Context context) {
        super(context);
    }

    public void set(int cellDim) { //float x, float y, int cellDim){

        MeasureSpec.makeMeasureSpec(cellDim, MeasureSpec.EXACTLY);
        MeasureSpec.makeMeasureSpec(cellDim, MeasureSpec.EXACTLY);
//        this.setX(x);
//        this.setY(y);
////        this.setWidth(cellDim);
////        this.setWidth(cellDim);
//        this.setLayoutParams(new LinearLayout.LayoutParams(cellDim, cellDim));

    }
}
