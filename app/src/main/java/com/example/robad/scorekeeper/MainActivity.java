package com.example.robad.scorekeeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int guestScore = 0;
    int homeScore = 0;
    int ballCount = 1;
    int strikeCount = 1;
    int outCount = 1;
    int inningCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /**
     * Increases the Guest Score by 1.
     */
    public void addOneRunForGuest(View v){
        guestScore = guestScore + 1;
        displayGuestScore(guestScore);
    }
    /**
     * Increases the Home Score by 1.
     */
    public void addOneRunForHome(View v){
        homeScore = homeScore + 1;
        displayHomeScore(homeScore);
    }
    /**
     * Increases the Ball Count by 1.
     */
    public void ballCount(View v){
        if (ballCount == 1){
            displayBallCount("B");
        }
        if (ballCount == 2){
            displayBallCount("BB");
        }
        if (ballCount == 3){
            displayBallCount("BBB");
        }
        if (ballCount == 4){
            displayBallCount("-----");
            displayStrikeCount("-----");
            ballCount = 0;
            strikeCount = 1;
        }
        ballCount = ballCount + 1;
    }
    /**
     * Increases the Strike Count by 1 after the once it reaches 3rd Strike the Ball & Strike count resets and Out
     * count is updated by 1. Once 3rd Out is reached the Strike,Ball, and Out counts reset.
     */
    public void strikeCount(View v){
        if (strikeCount == 1){
            displayStrikeCount("S");
        }
        if (strikeCount == 2){
            displayStrikeCount("SS");
        }
        if (strikeCount == 3){
            displayBallCount("-----");
            displayStrikeCount("-----");
            strikeCount = 0;
            ballCount = 1;
            if (outCount == 1) {
                displayOutCount("O");
            }
            if(outCount == 2){
                displayOutCount("OO");
            }
            if(outCount == 3){
                displayOutCount("-----");
                displayBallCount("-----");
                displayStrikeCount("-----");
                outCount = 0;
                ballCount = 1;
                strikeCount = 0;
            }
            outCount = outCount + 1;
        }
        strikeCount = strikeCount + 1;

    }

    /**
     * Increases the Inning value by 1 & displays an announcement when the value is equal to 1 or 8.
     */
    public void increaseInningByOne(View v){
        if (inningCount < 10) {
            inningCount = inningCount + 1;
            displayInning(inningCount);
        }
    }
    /**
     * Decreases the Inning value by 1.
     */
    public void decreaseInningByOne(View v){
        if(inningCount > 0){
            inningCount = inningCount - 1;
            displayInning(inningCount);
        }
    }
    /**
     * Decreases the Guest value by 1.
     */
    public void decreaseGuestScoreByOne(View v){
        if(guestScore > 0){
            guestScore = guestScore - 1;
            displayGuestScore(guestScore);
        }
    }
    /**
     * Decreases the Home value by 1.
     */
    public void decreaseHomeScoreByOne(View v){
        if(homeScore > 0) {
            homeScore = homeScore - 1;
            displayHomeScore(homeScore);
        }
    }
    /**
     * Decreases the ball value.
     */
    public void decreaseBallCount(View v){
        if (ballCount == 4) {
            displayBallCount("BB");
        }
        if(ballCount == 3){
            displayBallCount("B");
        }
        if (ballCount > 2){
            ballCount = ballCount - 1;
        }
    }
    /**
     * Decreases the Out value.
     */
    public void decreaseOutCount(View v){
        if (outCount == 3) {
            displayOutCount("O");
            outCount = 2;
        }
    }
    /**
     * Decreases the strike value.
     */
    public void decreaseStrikeCount(View v){
        if (strikeCount == 3){
            displayStrikeCount("S");
            strikeCount = 2;
        }
    }

    /**
     * Reset all values.
     */
    public void reset(View v) {
        displayGuestScore(0);
        displayHomeScore(0);
        displayInning(0);
        displayOutCount("-----");
        displayStrikeCount("-----");
        displayBallCount("-----");
        guestScore = 0;
        homeScore = 0;
        ballCount = 1;
        strikeCount = 1;
        inningCount = 0;
    }

    /**
     * Displays the given score for Guest.
     */
    public void displayGuestScore(int score) {
        TextView scoreView = findViewById(R.id.guest_score);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Displays the given score for Home.
     */
    public void displayHomeScore(int score) {
        TextView scoreView = findViewById(R.id.home_score);
        scoreView.setText(String.valueOf(score));
    }

    /**
     *  Display the current inning.
     */
    public void displayInning(int inning) {
        TextView inningView = findViewById(R.id.current_inning);
        inningView.setText(String.valueOf(inning));
    }

    /**
     * Displays the ball count.
     */
    public void displayBallCount(String ball) {
        TextView ballView = findViewById(R.id.ball_count);
        ballView.setText(String.valueOf(ball));
    }

    /**
     * Displays the out count.
     */
    public void displayOutCount(String out) {
        TextView outView = findViewById(R.id.out_count);
        outView.setText(String.valueOf(out));
    }

    /**
     * Displays the strike count.
     */
    public void displayStrikeCount(String strike) {
        TextView strikeView = findViewById(R.id.strike_count);
        strikeView.setText(String.valueOf(strike));
    }
}
