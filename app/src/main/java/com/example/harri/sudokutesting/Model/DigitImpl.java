package com.example.harri.sudokutesting.Model;

import java.util.ArrayList;
import java.util.List;

public class DigitImpl implements Digit {
	
	protected List<Integer> allValues;
	
	public DigitImpl() {
		this.allValues = new ArrayList<Integer>();
	}
		
	public void addValues(int[] newValues) {
		for (int i : newValues) {
			if (!this.allValues.contains(i)) {
				this.allValues.add(i);
			}			
		}
	}

	public void addSingleValue(int newValue) {
		this.allValues.clear();
		this.allValues.add(newValue);
	}
	
	public int[] getValues() {
		int[] output = new int[this.allValues.size()];
		for (int i = 0; i < this.allValues.size(); i++) {
			output[i] = this.allValues.get(i);
		}
		return output;
	}
	
	@Override
	public String toString() {
		String s;
		
		if (this.allValues.size() == 0) {
			s = " ";
		} else if (this.allValues.size() == 1){
			s = this.allValues.get(0).toString();
		} else {
			s = "M";
		}
			
		return s;		
	}
	
	public void clear() {
		this.allValues.clear();
	}
	
	
	public String serialize() {
		StringBuilder sb = new StringBuilder();
		
		if (this.allValues.size() > 0) {
			int[] temp = this.getValues();
			
			for (int i = 0; i < temp.length - 1; i++) {
				sb.append(temp[i] + ";");
			}
			
			sb.append(temp[temp.length - 1]);
		}
				
		return sb.toString();
	}
	
	public void deserialize(String s) {
		String[] temp = s.split(";");
		int[] intA = new int[temp.length];
		
		for (int i = 0; i < temp.length; i++) {
			intA[i] = Integer.valueOf(temp[i]);
		}
		
		this.addValues(intA);		
	}	
}
	
	

