package com.example.harri.sudokutesting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.BindView;

public class SplashActivity extends AppCompatActivity {

    GameController gameController;
    @BindView(R.id.btnStartGame) Button btnStart;

    private static final int REQUEST_STARTGAME = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ButterKnife.bind(this);

        gameController = new GameController();

        this.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GamePart3.class);
                startActivityForResult(intent, REQUEST_STARTGAME);
            }
        });
    }
}
