package com.example.harri.sudokutesting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.BindView;

public class GameActivity extends AppCompatActivity {

    @BindView(R.id.layout) RelativeLayout layout;
    public GridView gridView;
    protected List<Button> buttonList = new ArrayList<Button>();
    protected GameController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        ButterKnife.bind(this);

//        CellButton b = new CellButton(this);
//        b.set(50, 50, 50);
//        b.setText("a");
//        layout.addView(b);

        this.gridView = new GridView_4x4(this);
        layout.addView(this.gridView);

        int dimensions = (int)(layout.getWidth() * .9);
        float startHeight = (float)(layout.getHeight() * .1);
        float startWidth = (float)(layout.getWidth() * .05);

    }

    public void setController(GameController newController) {
        this.controller = newController;
    }





}
