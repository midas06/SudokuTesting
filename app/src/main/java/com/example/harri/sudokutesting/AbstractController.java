package com.example.harri.sudokutesting;


import java.util.List;

public abstract class AbstractController {
    abstract int getMax();
    abstract int getRowmax();
    abstract int getColumMax();
    abstract int getSquareWidth();
    abstract int getSquareHeight();

    abstract int get( int where );
    abstract int[] getMultiple(int where);
    abstract boolean fixed( int where );
    abstract boolean isFinished();

    abstract List<Integer> getRowHint(int where);
    abstract List<Integer> getColumnHint(int where);
    abstract List<Integer> getSquareHint(int where);
    abstract List<Integer> getCellHint(int where);


    abstract void undo();
    abstract int[] loadFile();
    abstract void save();
    abstract void setSingle(int where, int value);
    abstract void setMultiple (int where,int[] values);

    abstract void restart();
    abstract long getTime();

}
