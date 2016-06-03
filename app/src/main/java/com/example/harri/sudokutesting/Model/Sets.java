package com.example.harri.sudokutesting.Model;

public interface Sets {
	public void setSingleValue(int newValue, Cell theCell);
    public void setMultipleValues(int[] newValues, Cell theCell);
}
