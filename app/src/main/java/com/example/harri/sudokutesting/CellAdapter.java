package com.example.harri.sudokutesting;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.List;


public class CellAdapter extends BaseAdapter {

    private Context mContext;
    public Integer[] cellImgs = {
            R.drawable.cellimage_1, R.drawable.cellimage_2, R.drawable.cellimage_3, R.drawable.cellimage_0,
            R.drawable.cellimage_0, R.drawable.cellimage_0, R.drawable.cellimage_1, R.drawable.cellimage_0,

            R.drawable.cellimage_3, R.drawable.cellimage_1, R.drawable.cellimage_0, R.drawable.cellimage_2,
            R.drawable.cellimage_0, R.drawable.cellimage_0, R.drawable.cellimage_4, R.drawable.cellimage_0
    };



    public CellAdapter(Context c) {
        this.mContext = c;
    }

    public int getCount() {
        return this.cellImgs.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if (convertView == null) {
            imageView = new ImageView(this.mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(325, 325));
            imageView.setScaleType(ImageView.ScaleType.CENTER);
            imageView.setPadding(8, 8, 8, 8);

        } else {
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(cellImgs[position]);
        return imageView;
    }


    public void setCellData(int cellIndex, int cellValue) {
        String imagePath = "cellimage_" + cellValue;
        int newImage = mContext.getResources().getIdentifier(imagePath, "drawable", mContext.getPackageName());
        this.cellImgs[cellIndex] = newImage;
    }

    public void setSelectedCell(int cellIndex, int cellValue) {
        String imagePath = "cellimage_" + cellValue + "_selected";
        int newImage = mContext.getResources().getIdentifier(imagePath, "drawable", mContext.getPackageName());
        this.cellImgs[cellIndex] = newImage;
    }

    public void setFixedCell(int cellIndex, int cellValue) {
        String imagePath = "cellimage_" + cellValue + "_fixed";
        int newImage = mContext.getResources().getIdentifier(imagePath, "drawable", mContext.getPackageName());
        this.cellImgs[cellIndex] = newImage;
    }

    public void setSelectedFixedCell(int cellIndex, int cellValue) {
        String imagePath = "cellimage_" + cellValue + "_selected_fixed";
        int newImage = mContext.getResources().getIdentifier(imagePath, "drawable", mContext.getPackageName());
        this.cellImgs[cellIndex] = newImage;
    }



}
