package com.example.robad.scorekeeper;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int guestScore = 0;
    int homeScore = 0;
    int ballCount = 0;
    int strikeCount = 0;
    int outCount = 0;
    int inningCount = 1;
    boolean isGuestTurn = true;
    TextView guestView, homeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        guestView = findViewById(R.id.guest);
        homeView = findViewById(R.id.home);
        guestView.setTextColor(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));
    }
    /**
     * Increases the Guest Score by 1. Checks for a winner once inning reaches 9 or more.
     */
    public void addOneRunForGuest(View v){
        guestScore = guestScore + 1;
        displayGuestScore(guestScore);
    }
    /**
     * Increases the Home Score by 1. Checks for a winner once inning reaches 9 or more.
     */
    public void addOneRunForHome(View v){
        homeScore = homeScore + 1;
        displayHomeScore(homeScore);
    }
    /**
     * Increases the Ball Count by 1.
     */
    public void ballCount(View v){
        ballCount = ballCount + 1;
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
    }
    /**
     * Increases the Strike Count by 1 after the once it reaches 3rd Strike the Ball & Strike count resets and Out
     * count is updated by 1. Once 3rd Out is reached the Strike,Ball, and Out counts reset.
     */
    public void strikeCount(View v){
        strikeCount = strikeCount + 1;
        if (strikeCount == 1){
            displayStrikeCount("S");
        } else if (strikeCount == 2){
            displayStrikeCount("SS");
        } else {
            outCount = outCount + 1;
            displayBallCount("-----");
            displayStrikeCount("-----");
            strikeCount = 0;
            ballCount = 1;
            if (outCount == 1) {
                displayOutCount("O");
            } else if(outCount == 2){
                displayOutCount("OO");
            } else {
                displayOutCount("-----");
                displayBallCount("-----");
                displayStrikeCount("-----");
                outCount = 0;
                ballCount = 1;
                strikeCount = 0;
                isGuestTurn = !isGuestTurn;
            }
        }
        if(isGuestTurn) {
            guestView.setTextColor(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));
            homeView.setTextColor(ColorStateList.valueOf(getResources().getColor(R.color.topOfInning)));
        } else {
            homeView.setTextColor(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));
            guestView.setTextColor(ColorStateList.valueOf(getResources().getColor(R.color.topOfInning)));
        }
    }

    /**
     * Increases the Inning value by 1. Checks for a winner once inning reaches 9 or more.
     */
    public void increaseInningByOne(View v){
        if (inningCount < 9 || guestScore == homeScore) {
            inningCount = inningCount + 1;
            displayInning(inningCount);
        } else {
            checkForWinner();
        }
    }
    /**
     * Decreases the Inning value by 1. Checks for a winner once inning reaches 9 or more.
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
        displayInning(1);
        displayOutCount("-----");
        displayStrikeCount("-----");
        displayBallCount("-----");
        guestScore = 0;
        homeScore = 0;
        ballCount = 0;
        strikeCount = 0;
        inningCount = 1 ;
        guestView.setTextColor(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));
        homeView.setTextColor(ColorStateList.valueOf(getResources().getColor(R.color.topOfInning)));
    }
    

    /**
     * Displays the given score for Guest.
     */
    private void displayGuestScore(int score) {
        TextView scoreView = findViewById(R.id.guest_score);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Displays the given score for Home.
     */
    private void displayHomeScore(int score) {
        TextView scoreView = findViewById(R.id.home_score);
        scoreView.setText(String.valueOf(score));
    }

    /**
     *  Display the current inning.
     */
    private void displayInning(int inning) {
        TextView inningView = findViewById(R.id.current_inning);
        inningView.setText(String.valueOf(inning));
    }

    /**
     * Displays the ball count.
     */
    private void displayBallCount(String ball) {
        TextView ballView = findViewById(R.id.ball_count);
        ballView.setText(String.valueOf(ball));
    }

    /**
     * Displays the out count.
     */
    private void displayOutCount(String out) {
        TextView outView = findViewById(R.id.out_count);
        outView.setText(String.valueOf(out));
    }

    /**
     * Displays the strike count.
     */
    private void displayStrikeCount(String strike) {
        TextView strikeView = findViewById(R.id.strike_count);
        strikeView.setText(String.valueOf(strike));
    }

    /**
     * Checks to see if there is a winner
     */
    private void checkForWinner() {
        if(inningCount >= 9 && (guestScore > homeScore || guestScore < homeScore)) {
            Intent intent = new Intent(this, WinnerActivity.class);
            if(guestScore > homeScore) {
                intent.putExtra("winner", "Ball Game! The away team wins!");
            } else {
                intent.putExtra("winner", "Ball Game! The home team wins!");
            }
            startActivity(intent);
            displayGuestScore(0);
            displayHomeScore(0);
            displayInning(1);
            displayOutCount("-----");
            displayStrikeCount("-----");
            displayBallCount("-----");
            guestScore = 0;
            homeScore = 0;
            ballCount = 1;
            strikeCount = 1;
            inningCount = 1;
            guestView.setTextColor(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));
            homeView.setTextColor(ColorStateList.valueOf(getResources().getColor(R.color.topOfInning)));
        }
    }
}
