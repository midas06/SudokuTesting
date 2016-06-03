package com.example.harri.sudokutesting.Model;

public class Cell {
	
	protected Game theGame;
	protected int cellIndex, rowIndex, columnIndex, squareIndex;
	protected Digit digit;
	protected boolean isFixed;

	public Digit getDigit() {
		return this.digit;
	}
	
	public void addDigitValues(int[] newValues) {
		if(!this.isFixed) {
			this.digit.addValues(newValues);
		}		
	}

	public void addDigitSingleValue(int newValue) {
        if(!this.isFixed) {
            this.digit.addSingleValue(newValue);
        }
    }
	
	public Cell(Game theGame) {
		this.theGame = theGame;
		this.digit = new DigitImpl();
	}
	
	
	public Cell(int newIndex, Game theGame) {
		this.cellIndex = newIndex;
		this.theGame = theGame;
		this.calcRowColumn();
		this.calcSquare();
		this.digit = new DigitImpl();
	}
	
	protected void calcRowColumn() {
		double sqrt, row;
		sqrt = Math.sqrt(theGame.getMaxValue());

		row = (double)this.cellIndex / sqrt;
		this.rowIndex = (int)Math.floor(row);
		
		this.columnIndex = this.cellIndex % (int)sqrt;
	}
	
	protected void calcSquare() {
		int squareWidth, squareHeight, numRowSquares, squareRow, squareCol;
		squareWidth = this.theGame.getSquareWidth();
		squareHeight = this.theGame.getSquareHeight();
		numRowSquares = this.theGame.getMaxDimension() / squareWidth;
		
		squareCol = this.columnIndex / squareWidth;
		squareRow = this.rowIndex / squareHeight;
		
		this.squareIndex = squareCol + squareRow * numRowSquares;
		
	}
	
	public void setFixed() {
		this.isFixed = true;
	}
	
	public void toggleFixed() {
		this.isFixed = !this.isFixed;
	}
	
	public void clear() {
		this.digit.clear();
	}	
	
	public int getRowIndex() {
		return this.rowIndex;
	}
	
	public int getColumnIndex() {
		return this.columnIndex;
	}
	
	public int getIndex() {
		return this.cellIndex;
	}
	
	public int getSquareIndex() {
		return this.squareIndex;
	}
	
	public boolean getIsFixed() {
		return this.isFixed;
	}
	
	public String serialize() {
		String srlDigit = "[" + this.digit.serialize();
		StringBuilder sb = new StringBuilder();
		if (this.isFixed) {
			sb.append("f");
		} else {
			sb.append("n");
		}
		sb.append("$" + this.cellIndex);
		sb.append(srlDigit);
		
		return sb.toString();						
	}
	
	public void deserialize(String s) {
		String[] tempArray;
		String tempStr, digitStr;
		boolean fixed;
		int index;
		// isFixed?
		tempArray = s.split("\\$");

		fixed = tempArray[0].equals("f") ? true : false;
		
		// digit & cell index
		tempStr = tempArray[1];
		tempArray = tempStr.split("\\[");
		
		index = Integer.valueOf(tempArray[0]);
		if (tempArray.length > 1) {
			digitStr = tempArray[1];
			this.digit.deserialize(digitStr);
		}
						
		this.isFixed = fixed;
		this.cellIndex = index;
		
		this.calcRowColumn();
		this.calcSquare();		
	}
		
}
    

