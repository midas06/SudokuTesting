package com.example.harri.sudokutesting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import butterknife.ButterKnife;
import butterknife.BindView;

import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class GamePart3 extends AppCompatActivity {
    GridView gridView;
    CellAdapter cellAdapter;
    Button btnSetValue_2;
    Button btnSetValue_3;
    Button btnSetValue_4;
    Button btnSetValue_1;
    Button btnClearValue;
    public Integer[] cellValues = {
           1, 2, 3, 0,
            0, 2, 0, 0,
            1, 3, 4, 0,
            0, 0, 0, 0
    };

//    @BindView(R.id.btnSetValue_1) Button btnSetValue_1;
//    @BindView(R.id.btnSetValue_2) Button btnSetValue_2;
//    @BindView(R.id.btnSetValue_3) Button btnSetValue_3;
//    @BindView(R.id.btnSetValue_4) Button btnSetValue_4;
//    @BindView(R.id.btnClearValue) Button btnClearValue;

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

        this.cellAdapter = new CellAdapter(this);
        for (int i = 0; i < this.cellValues.length; i++) {
            cellAdapter.setCellData(i, this.cellValues[i]);
        }

        gridView.setAdapter(this.cellAdapter);

        gridView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id){
                //Toast.makeText(GamePart3.this, Integer.toString(position), Toast.LENGTH_SHORT).show();
//                cellAdapter.setCellData(position, 4);
//                gridView.invalidateViews();
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
        this.cellValues[cellIndex] = cellValue;
        cellAdapter.setCellData(cellIndex, cellValue);
        this.gridView.invalidateViews();
    }

    protected boolean isValidValue(int newValue) {
        boolean isValid = true;
        if (this.selectedCell == -1) {
            Toast.makeText(GamePart3.this, "No cell has been selected", Toast.LENGTH_SHORT);
            isValid = false;
        }
        return isValid;
    }

    protected void setSelectedCell(int cellIndex) {
        cellAdapter.setSelectedCell(cellIndex, this.cellValues[cellIndex]);
        this.gridView.invalidateViews();
    }

    protected void setUnselected(int newCell) {
        if (this.selectedCell != -1 && this.selectedCell != newCell) {
            this.cellAdapter.setCellData(this.selectedCell, this.cellValues[selectedCell]);
        }
    }


}
