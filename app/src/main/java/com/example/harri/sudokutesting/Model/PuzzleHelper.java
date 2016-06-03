package com.example.harri.sudokutesting.Model;

import java.util.ArrayList;
import java.util.List;

public final class PuzzleHelper {
	private PuzzleHelper() { }
	
	public static List<Cell> getCellListByRow(List<Cell> thePuzzle, int rowIndex) {
		List<Cell> theList = new ArrayList<Cell>();
		
		for (Cell c : thePuzzle) {
			if (c.getRowIndex() == rowIndex) {
				theList.add(c);
			}
		}
		return theList;
	}
	
	public static List<Cell> getCellListByColumn(List<Cell> thePuzzle,int columnIndex) {
		List<Cell> theList = new ArrayList<Cell>();
		
		for (Cell c : thePuzzle) {
			if (c.getColumnIndex() == columnIndex) {
				theList.add(c);
			}
		}
		return theList;
	}
	
	public static List<Cell> getCellListBySquare(List<Cell> thePuzzle, int squareIndex) {
		List<Cell> theList = new ArrayList<Cell>();
		
		for (Cell c : thePuzzle) {
			if (c.getSquareIndex() == squareIndex) {
				theList.add(c);
			}
		}		
		
		return theList;
	}
	

}
