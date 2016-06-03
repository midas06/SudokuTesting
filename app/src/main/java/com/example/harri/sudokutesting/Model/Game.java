package com.example.harri.sudokutesting.Model;

import java.util.List;

public interface Game {
	public void setMaxValue(int maximum);
    public int getMaxValue();
    public int getMaxDimension();
    public int getSquareWidth();
    public int getSquareHeight();
    public void setSquareWidth(int squareWidth);
    public void setSquareHeight(int squareHeight);
    public void restart();
    public List<Cell> getPuzzle();
}
