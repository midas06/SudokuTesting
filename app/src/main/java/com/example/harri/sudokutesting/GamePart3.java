package com.example.harri.sudokutesting;

import android.app.AlertDialog;
import android.content.*;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.example.harri.sudokutesting.Model.GameImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class GamePart3 extends AppCompatActivity {
    GridView gridView;
    CellAdapter cellAdapter;
    Button btnSetValue_1, btnSetValue_2, btnSetValue_3, btnSetValue_4;
    Button btnUndo, btnClearValue, btnNote, btnHint;
    String[] optionsArray = {"1", "2", "3", "4"};
    Context mContext = this;

    public Integer[] cellValues;
    GameImpl theGame;

    private int selectedCell = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_part3);

        ButterKnife.bind(this);
        btnSetValue_1 = (Button)findViewById(R.id.btnSetValue_1);
        btnSetValue_2 = (Button)findViewById(R.id.btnSetValue_2);
        btnSetValue_3 = (Button)findViewById(R.id.btnSetValue_3);
        btnSetValue_4 = (Button)findViewById(R.id.btnSetValue_4);
        btnClearValue = (Button)findViewById(R.id.btnClearValue);
        btnUndo = (Button)findViewById(R.id.btnUndo);
        btnNote = (Button)findViewById(R.id.btnNote);
        btnHint = (Button)findViewById(R.id.btnHint);
        gridView = (GridView)findViewById(R.id.cellGridview);
        theGame = new GameImpl();
        this.initMap();

        this.cellAdapter = new CellAdapter(this);
        for (int i = 0; i < this.cellValues.length; i++) {
            if (this.cellValues[i] == 0) {
                cellAdapter.setCellData(i, 0);
            } else {
                cellAdapter.setFixedCell(i, this.cellValues[i]);
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
                if (isValidValue(1)) {
                    setCellValue(selectedCell, 1);
                    setSelectedCell(selectedCell);
                }
            }
        });
        btnSetValue_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidValue(2)) {
                    setCellValue(selectedCell, 2);
                    setSelectedCell(selectedCell);
                }
            }
        });
        btnSetValue_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidValue(3)) {
                    setCellValue(selectedCell, 3);
                    setSelectedCell(selectedCell);
                }
            }
        });
        btnSetValue_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidValue(4)) {
                    setCellValue(selectedCell, 4);
                    setSelectedCell(selectedCell);
                }
            }
        });
        btnClearValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidValue(0)) {
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
        btnNote.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                final List<Integer> selectedItemsList = new ArrayList<Integer>();
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Select possible values")
                        .setMultiChoiceItems(optionsArray, null, new android.content.DialogInterface.OnMultiChoiceClickListener() {
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



        });
    }

    protected void setCellValue(int cellIndex, int cellValue) {
        if (cellValue != 0) {
            this.theGame.setSingleValue(cellValue, theGame.getCellByIndex(cellIndex));
        } else {
            this.theGame.clearCell(theGame.getCellByIndex(cellIndex));
        }
        this.cellValues = theGame.exportCellValues();
        cellAdapter.setCellData(cellIndex, cellValue);
        this.gridView.invalidateViews();
    }

    protected boolean isValidValue(int newValue) {
        boolean isValid = true;
        if (this.selectedCell == -1) {
            Toast.makeText(GamePart3.this, "No cell has been selected", Toast.LENGTH_SHORT).show();
            isValid = false;
        } else if (theGame.isCellFixed(theGame.getCellByIndex(selectedCell))){
            isValid = false;
        }
        return isValid;
    }

    protected void setSelectedCell(int cellIndex) {
        if (theGame.isCellFixed(theGame.getCellByIndex(selectedCell))) {
            cellAdapter.setSelectedFixedCell(cellIndex, this.cellValues[cellIndex]);
        } else {
            cellAdapter.setSelectedCell(cellIndex, this.cellValues[cellIndex]);
        }

        this.gridView.invalidateViews();
    }

    protected void setUnselected(int newCell) {
        if (this.selectedCell != -1 && this.selectedCell != newCell) {
            if (theGame.isCellFixed(theGame.getCellByIndex(selectedCell))) {
                this.cellAdapter.setFixedCell(this.selectedCell, this.cellValues[selectedCell]);
            } else {
                this.cellAdapter.setCellData(this.selectedCell, this.cellValues[selectedCell]);
            }
        }
    }

    protected void initMap() {
        theGame.setMaxValue(16);
        theGame.setSingleValue(2, theGame.getCellByCoord(2, 0));
        theGame.setSingleValue(3, theGame.getCellByCoord(1, 1));
        theGame.setSingleValue(4, theGame.getCellByCoord(3, 1));
        theGame.setSingleValue(3, theGame.getCellByCoord(0, 2));
        theGame.setSingleValue(4, theGame.getCellByCoord(2, 2));
        theGame.setSingleValue(1, theGame.getCellByCoord(1, 3));
        theGame.finaliseInitialPuzzle();

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
        this.theGame.undo();
        this.refreshUnfixedCells();
    }

    protected void setMultipleValues(List<Integer> newValues) {
        StringBuilder s = new StringBuilder();
        for (Integer i : newValues) {
            s.append(optionsArray[i] + "\n");
        }
        Toast.makeText(this, s.toString(), Toast.LENGTH_SHORT).show();


    }


}
