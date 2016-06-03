package com.example.harri.sudokutesting.Model;

public interface Validating {
    public boolean hasDuplicates(); // no duplicates
    public boolean isComplete(); // no zeros AND no duplicates
}
