package com.example.harri.sudokutesting.Model;

public interface Gets {
	public Cell getCellByCoord(int columnIndex, int rowIndex);
    public Cell getCellBySquare(int cellIndex, int theSquare);
}
