package com.example.harri.sudokutesting.Model;

public interface Serialize {
	public void fromCSV(String csv);
    public String toCSV();
    public void setCell(int value, int gridIndex);
    public int getCell(int gridIndex);
    @Override
    public String toString();
}
