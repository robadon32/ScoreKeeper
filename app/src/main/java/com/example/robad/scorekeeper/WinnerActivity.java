package com.example.robad.scorekeeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class WinnerActivity extends AppCompatActivity {

    private TextView winnerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);

        winnerView = findViewById(R.id.theWinner);

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }
        String theWinner = extras.getString("winner");
        winnerView.setText(theWinner);
    }
}
