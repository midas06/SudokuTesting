package com.example.harri.sudokutesting;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.example.harri.sudokutesting.Model.GameImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.ButterKnife;

public class GamePart3 extends AppCompatActivity {
    GridView gridView;
    CellAdapter cellAdapter;
    Button btnSetValue_1, btnSetValue_2, btnSetValue_3, btnSetValue_4;
    Button btnUndo, btnClearValue, btnNote, btnHint, btnRestart;
    String[] optionsArray = {"1", "2", "3", "4"};
    boolean[] isSetArray = {false, true, false, false};
    Context mContext = this;
    SudokuController controller = SudokuController.getInstance();

    public Integer[] cellValues;
    GameImpl theGame;

    private int selectedCell = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_game_part3);
        setContentView(R.layout.testlayout);

        ButterKnife.bind(this);
        btnSetValue_1 = (Button)findViewById(R.id.btnSetValue_1);
        btnSetValue_2 = (Button)findViewById(R.id.btnSetValue_2);
        btnSetValue_3 = (Button)findViewById(R.id.btnSetValue_3);
        btnSetValue_4 = (Button)findViewById(R.id.btnSetValue_4);
        btnClearValue = (Button)findViewById(R.id.btnClearValue);
        btnUndo = (Button)findViewById(R.id.btnUndo);
        btnNote = (Button)findViewById(R.id.btnNote);
        btnHint = (Button)findViewById(R.id.btnHint);
        btnRestart = (Button)findViewById(R.id.btnRestart);
        gridView = (GridView)findViewById(R.id.cellGridview);
        theGame = controller.getModel();
        this.initMap();

        this.cellAdapter = new CellAdapter(this);
        for (int i = 0; i < this.cellValues.length; i++) {
            if (this.cellValues[i] == 0) {
                cellAdapter.setCellData(i, 0);
            } else if (this.controller.fixed(i)){
                cellAdapter.setFixedCell(i, this.cellValues[i]);
            } else {
                cellAdapter.setCellData(i, this.cellValues[i]);
            }
        }

        gridView.setAdapter(this.cellAdapter);

        gridView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id){
                setUnselected(position);
                selectedCell = position;
                setSelectedCell(position);
            }
        });

        btnSetValue_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidValue()) {
                    setCellValue(selectedCell, 1);
                    setSelectedCell(selectedCell);
                }
            }
        });
        btnSetValue_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidValue()) {
                    setCellValue(selectedCell, 2);
                    setSelectedCell(selectedCell);
                }
            }
        });
        btnSetValue_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidValue()) {
                    setCellValue(selectedCell, 3);
                    setSelectedCell(selectedCell);
                }
            }
        });
        btnSetValue_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidValue()) {
                    setCellValue(selectedCell, 4);
                    setSelectedCell(selectedCell);
                }
            }
        });
        btnClearValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidValue()) {
                    setCellValue(selectedCell, 0);
                    setSelectedCell(selectedCell);
                }
            }
        });
        btnUndo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                undo();
            }
        });
        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restart();
            }
        });

        btnNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidValue()) {
                    getMultipleValues();
                    final List<Integer> selectedItemsList = new ArrayList<Integer>();

                    for (int i = 0; i < isSetArray.length; i++) {
                        if (isSetArray[i]) {
                            selectedItemsList.add(i);
                        }
                    }

                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setTitle("Select possible values")
                            .setMultiChoiceItems(optionsArray, isSetArray, new android.content.DialogInterface.OnMultiChoiceClickListener() {
                                @Override
                                public void onClick(android.content.DialogInterface dialog, int selectedOption, boolean isChecked) {
                                    if (isChecked) {
                                        selectedItemsList.add(selectedOption);

                                    } else if (selectedItemsList.contains(selectedOption)) {
                                        selectedItemsList.remove(selectedOption);
                                    }
                                }
                            })
                            .setPositiveButton("Ok", new android.content.DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(android.content.DialogInterface d, int id) {
                                    setMultipleValues(selectedItemsList);
                                }
                            })
                            .setNegativeButton("Cancel", new android.content.DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(android.content.DialogInterface d, int id) {
                                    //
                                }
                            });

                    AlertDialog d = builder.create();
                    d.show();
                }

            }
        });

        btnHint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] optionsArray = {"Row", "Column", "Square", "Cell"};
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("What sort of hint do you want?")
                        .setItems(optionsArray, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface d, int which) {
                                getHint(which);
                            }
                        });
                AlertDialog d = builder.create();
                d.show();
            }
        });
    }

    protected void setCellValue(int cellIndex, int cellValue) {
        if (cellValue != 0) {
            //this.theGame.setSingleValue(cellValue, theGame.getCellByIndex(cellIndex));
            this.controller.setSingle(cellIndex, cellValue);
        } else {
            this.theGame.clearCell(theGame.getCellByIndex(cellIndex));
        }
        this.cellValues = theGame.exportCellValues();
        cellAdapter.setCellData(cellIndex, cellValue);
        this.gridView.invalidateViews();
        if (this.isFull()) {
            this.isComplete();
        }
    }

    protected boolean isValidValue() {
        boolean isValid = true;
        if (this.selectedCell == -1) {
            Toast.makeText(GamePart3.this, "No cell has been selected", Toast.LENGTH_SHORT).show();
            isValid = false;
        } else if (this.controller.fixed(this.selectedCell)){
            Toast.makeText(GamePart3.this, "The cell is fixed", Toast.LENGTH_SHORT).show();
            isValid = false;
        }
        return isValid;
    }

    protected void setSelectedCell(int cellIndex) {
        if (this.controller.fixed(this.selectedCell)) {
            cellAdapter.setSelectedFixedCell(cellIndex, this.cellValues[cellIndex]);
        } else {
            cellAdapter.setSelectedCell(cellIndex, this.cellValues[cellIndex]);
        }

        this.gridView.invalidateViews();
    }

    protected void setUnselected(int newCell) {
        if (this.selectedCell != -1 && this.selectedCell != newCell) {
            if (this.controller.fixed(this.selectedCell)) {
                this.cellAdapter.setFixedCell(this.selectedCell, this.cellValues[selectedCell]);
            } else {
                this.cellAdapter.setCellData(this.selectedCell, this.cellValues[selectedCell]);
            }
        }
    }

    protected void initMap() {
//        theGame.setMaxValue(16);
//        theGame.setSingleValue(2, theGame.getCellByCoord(2, 0));
//        theGame.setSingleValue(3, theGame.getCellByCoord(1, 1));
//        theGame.setSingleValue(4, theGame.getCellByCoord(3, 1));
//        theGame.setSingleValue(3, theGame.getCellByCoord(0, 2));
//        theGame.setSingleValue(4, theGame.getCellByCoord(2, 2));
//        theGame.setSingleValue(1, theGame.getCellByCoord(1, 3));
//        theGame.finaliseInitialPuzzle();

        if (!this.controller.getModel().isMapSet()) {
            this.controller.setMap(1);
        }

        this.cellValues = theGame.exportCellValues();

    }

    protected void refreshUnfixedCells() {
        List<Point> pointList = theGame.exportUnfixedValues();
        for(Point p : pointList) {
            this.cellValues[p.x] = p.y;
            this.cellAdapter.setCellData(p.x, p.y);
        }
        this.gridView.invalidateViews();
    }

    protected void undo() {
        this.controller.undo();
        this.refreshUnfixedCells();
    }

    public void getMultipleValues() {
        //int[] cellValues = this.theGame.getCellByIndex(selectedCell).getDigit().getValues();
        int[] cellValues = this.controller.getMultiple(selectedCell);
        if (cellValues.length == 0) {
            for (int i = 0; i < this.isSetArray.length; i++) {
                this.isSetArray[i] = false;
            }
        } else {
            for (int i : cellValues) {
                this.isSetArray[i - 1] = true;
            }
        }
    }

    protected void setMultipleValues(List<Integer> newValues) {

        if (newValues.size() == 0) {
            //this.theGame.clearCell(theGame.getCellByIndex(selectedCell));
            this.controller.clearCell(selectedCell);
        } else {
            int[] a = new int[newValues.size()];
            for (int i = 0; i < newValues.size(); i++) {
                Integer j = newValues.get(i);
                a[i] = Integer.valueOf(this.optionsArray[j]);

            }
            //theGame.setMultipleValues(a, theGame.getCellByIndex(selectedCell));
            this.controller.setMultiple(selectedCell, a);
            this.cellValues[selectedCell] = 99;
            this.cellAdapter.setCellData(selectedCell, 99);
        }
        this.setSelectedCell(selectedCell);
        this.gridView.invalidateViews();

    }

    protected boolean isFull() {
        boolean output = true;
        for (int i : this.cellValues) {
            if (i == 0 || i == 99) {
                output = false;
            }
        }
        return output;
    }

    public void isComplete() {
        if (this.theGame.isSolved()) {
            Toast.makeText(this, "Congratulations, you solved the puzzle!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Sorry, the puzzle isn't solved.", Toast.LENGTH_LONG).show();
        }
    }

    public void restart() {
        this.controller.restart();
        this.refreshUnfixedCells();
        this.gridView.invalidateViews();
    }

    public void getHint(int theHint) {

        List<Integer> possibleList = new ArrayList<Integer>();
        String theType = "";

        switch(theHint) {
            case 0:
                theType = "row";
                possibleList = theGame.getAllPossibleRowValues(theGame.getCellByIndex(selectedCell));
                break;
            case 1:
                theType = "column";
                possibleList = theGame.getAllPossibleColumnValues(theGame.getCellByIndex(selectedCell));
                break;
            case 2:
                theType = "square";
                possibleList = theGame.getAllPossibleSquareValues(theGame.getCellByIndex(selectedCell));
                break;
            case 3:
                theType = "cell";
                possibleList = theGame.getAllPossibleValues(theGame.getCellByIndex(selectedCell));
                break;
            default:
                break;
        }

        Collections.sort(possibleList);

        StringBuilder s = new StringBuilder("The possible values for the " + theType + " are: ");
        for (Integer i : possibleList) {
            s.append(i + ", ");
        }

        s.delete(s.length() - 2, s.length());



        Toast.makeText(this, s.toString(), Toast.LENGTH_LONG).show();
    }


}
