package com.example.harri.sudokutesting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.example.harri.sudokutesting.Model.GameImpl;

import butterknife.ButterKnife;

public class GamePart3 extends AppCompatActivity {
    GridView gridView;
    CellAdapter cellAdapter;
    Button btnSetValue_2;
    Button btnSetValue_3;
    Button btnSetValue_4;
    Button btnSetValue_1;
    Button btnClearValue;
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
    }

    protected void setCellValue(int cellIndex, int cellValue) {
        this.theGame.setSingleValue(cellValue, theGame.getCellByIndex(cellIndex));
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


}
