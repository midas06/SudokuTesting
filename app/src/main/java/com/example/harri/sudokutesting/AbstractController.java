package com.example.harri.sudokutesting;

public abstract class AbstractController {
    abstract int getMax();
    abstract int getRowmax();
    abstract int getColumMax();
    abstract int getSquareWidth();
    abstract int getSquareHeight();

    abstract int get( int where );
    abstract boolean fixed( int where );
    abstract  boolean isFinished();
    abstract String getHint( int where );

    abstract void undo();
    abstract int[] loadFile();
    abstract  void save();
    abstract void set( int value );
    abstract void sets ( int[] values );
    abstract void restart();
    abstract long getTime();

}
