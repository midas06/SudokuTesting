package com.example.harri.sudokutesting;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GameActivity extends AppCompatActivity {

    @BindView(R.id.layout) RelativeLayout layout;
    public GridView gridView;
    protected List<Button> buttonList = new ArrayList<Button>();
    Context c = this;
    Button b;
    String[] optionsArray = {"option1", "option2", "option3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        ButterKnife.bind(this);

//        CellButton b = new CellButton(this);
//        b.set(50, 50, 50);
//        b.setText("a");
//        layout.addView(b);

        b = (Button)findViewById(R.id.btnAlert);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final List<Integer> selectedItemsList = new ArrayList<Integer>();
                AlertDialog.Builder builder = new AlertDialog.Builder(c);
                builder.setTitle("Pick one")
                        .setMultiChoiceItems(optionsArray, null, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int selectedOption, boolean isChecked) {
                                if (isChecked) {
                                    selectedItemsList.add(selectedOption);

                                } else if (selectedItemsList.contains(selectedOption)) {
                                    selectedItemsList.remove(selectedOption);
                                }
                            }
                        })
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface d, int id) {
                                onOkay(selectedItemsList);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface d, int id) {
                                onCancel();
                            }
                        });

                AlertDialog d = builder.create();
                d.show();
            }

        });


    }

    public void onOkay(List<Integer> selectedList) {
        StringBuilder s = new StringBuilder();
        for (Integer i : selectedList) {
            s.append(optionsArray[i] + "\n");
        }
        Toast.makeText(this, s.toString(), Toast.LENGTH_SHORT).show();

    }

    public void onCancel() {
        //
    }






}
