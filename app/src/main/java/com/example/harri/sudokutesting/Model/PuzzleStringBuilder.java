package com.example.harri.sudokutesting.Model;
import java.util.List;

public final class PuzzleStringBuilder {
	
	protected List<Cell> puzzleList;
	protected Game theGame;
	
	public PuzzleStringBuilder(Game theGame) {
		this.theGame = theGame;
	}
	
	public void setPuzzle(List<Cell> newPuzzle) {
		this.puzzleList = newPuzzle;
	}	
	
	@Override
	public String toString() {		
		this.puzzleList = this.theGame.getPuzzle();
		
		StringBuilder sb = new StringBuilder();
		
		int maxHeight = (int)Math.sqrt(this.puzzleList.size());
		int count = 0;
		
		sb.append(this.generateLineBreak(maxHeight) + "\n");
				
		while(count < maxHeight) {
			sb.append(this.generateRow(count) + "\n");
			sb.append(this.generateLineBreak(maxHeight) + "\n");
			count++;
		}
		
		return sb.toString();
		
	}
	
	protected String generateRow(int rowIndex) {
		StringBuilder sb = new StringBuilder();
		List<Cell> theRow = PuzzleHelper.getCellListByRow(this.theGame.getPuzzle(), rowIndex);
		
		sb.append("|");
		
		for (Cell c : theRow) {
			sb.append(" " + c.getDigit().toString() + " |");
		}
		
		return sb.toString();		
	}
	
	protected String generateLineBreak(int maxWidth) {
		StringBuilder sb = new StringBuilder();
		int count = 0;
		while (count < maxWidth) {
			sb.append(" ---");
			count++;
		}
		return sb.toString();
	}
	
	
	
}
