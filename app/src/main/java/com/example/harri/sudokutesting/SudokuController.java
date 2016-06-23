package com.example.harri.sudokutesting;

import com.example.harri.sudokutesting.Model.Cell;
import com.example.harri.sudokutesting.Model.GameImpl;

import java.util.List;

public class SudokuController extends AbstractController {

    GameImpl model;

    private static SudokuController mInstance = null;

    public static SudokuController getInstance() {
        if (mInstance == null) {
            mInstance = new SudokuController();
        }
        return mInstance;
    }

    public SudokuController() {
        model = new GameImpl();
    }

    public GameImpl getModel() {
        return this.model;
    }

    public int getMax() {
        return model.getMaxValue();
    }

    public int getRowmax() {
        return model.getMaxDimension();
    }

    public int getColumMax() {
        return model.getMaxDimension();
    }

    public int getSquareWidth() {
        return model.getSquareWidth();
    }

    public int getSquareHeight() {
        return model.getSquareHeight();
    }

    public int get( int where ) {
        Cell c = model.getCellByIndex(where);
        return c.getDigit().getValues()[0];
    }

    public int[] getMultiple(int where) {
        Cell c = model.getCellByIndex(where);
        return c.getDigit().getValues();
    }

    public boolean fixed( int where ) {
        Cell c = model.getCellByIndex(where);
        return c.getIsFixed();
    }

    public boolean isFinished() {
        return model.isSolved();
    }

    public List<Integer> getRowHint(int where) {
        return model.getAllPossibleRowValues(model.getCellByIndex(where));
    }
    public List<Integer> getColumnHint(int where) {
        return model.getAllPossibleColumnValues(model.getCellByIndex(where));
    }
    public List<Integer> getSquareHint(int where) {
        return model.getAllPossibleSquareValues(model.getCellByIndex(where));
    }
    public List<Integer> getCellHint(int where) {
        return model.getAllPossibleValues(model.getCellByIndex(where));
    }

    public void undo() {
        model.undo();
    }

    public void save() {
        //
    }
    public void setSingle( int where, int value ) {
        model.setSingleValue(value, model.getCellByIndex(where));
    }

    public void setMultiple (int where, int[] values ) {
        model.setMultipleValues(values, model.getCellByIndex(where));
    }

    public void restart() {
        model.restart();
    }
    public long getTime() {
        //
        return 0;
    }

    public int[] loadFile() {
        return new int[]{2};
    }

    public void setMap(int theMap) {
        this.model.setThePuzzle(theMap);
    }

    public void clearCell(int where) {
        this.model.clearCell(model.getCellByIndex(where));
    }


}
