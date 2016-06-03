package com.example.harri.sudokutesting.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Checker implements Validating, HintPossible, HintImpossible {
	
	protected List<Cell> theList;
	protected int[] acceptableValues;
	protected List<Integer> usedValues;
	
	protected void init(int maxValue) {
		this.acceptableValues = new int[maxValue];
		for(int i = 1; i <= maxValue; i++) {
			this.acceptableValues[i - 1] = i;
		}		
	}	
	
	public void set(List<Cell> newList) {
		this.theList = newList;
		this.setUsedValues();
	}
	
	protected void setUsedValues() {
		this.usedValues = new ArrayList<Integer>();
		for (Cell c : this.theList) {
			for (int i : c.getDigit().getValues()) {
				this.usedValues.add(i);
			}
		}	
	}
	
	public List<Integer> getUsedValues() {
		return this.usedValues;
	}
	
	public boolean hasDuplicates() {
		Set<Integer> hashset = new HashSet<Integer>();
		for (int i : this.usedValues) {
			if (hashset.contains(i)) {
				return true;
			}
			hashset.add(i);
		}		
		return false;
	}
	
	protected List<Integer> acceptableArrayToList() {
		List<Integer> comparisonList = new ArrayList<Integer>();
		for (int i = 0; i < this.acceptableValues.length; i++) {
			comparisonList.add(this.acceptableValues[i]);
		}
		return comparisonList;
	}
	
	public boolean isComplete() {
		
		for (Cell c : this.theList) {
			if (c.getDigit().getValues().length != 1) {
				return false;
			}
		}
		
		List<Integer> comparisonList = this.acceptableArrayToList();
		
		Collections.sort(this.usedValues);
		
		return comparisonList.equals(this.usedValues);
	}	
	
	public List<Integer> getUnusedValues() {
		Set<Integer> unusedHash = new HashSet<Integer>(this.acceptableArrayToList());
		Set<Integer> usedHash = new HashSet<Integer>(this.usedValues);
		
		unusedHash.removeAll(usedHash);
		return new ArrayList<Integer>(unusedHash);
	}	
	
}
