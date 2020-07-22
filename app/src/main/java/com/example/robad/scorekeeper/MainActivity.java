package com.example.robad.scorekeeper;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_MY_GUEST_SCORE = "KEY_GUEST_SCORE";
    private static final String KEY_MY_HOME_SCORE = "KEY_HOME_SCORE";
    private static final String KEY_MY_CURRENT_INNING = "KEY_CURRENT_INNING";
    private static final String KEY_MY_STRIKE_COUNT = "KEY_STRIKE_COUNT";
    private static final String KEY_MY_BALL_COUNT = "KEY_BALL_COUNT";
    private static final String KEY_MY_OUT_COUNT = "KEY_OUT_COUNT";

    int guestScore, homeScore, ballCount, strikeCount, outCount, inningCount;
    String strike = "";
    String ball = "";
    String out = "";
    boolean isGuestTurn = true;
    TextView guestView, homeView;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inningCount = 1;
        guestView = findViewById(R.id.guest);
        homeView = findViewById(R.id.home);
        guestView.setTextColor(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));
    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "inside of onSaveInstanceState");
        outState.putInt(KEY_MY_GUEST_SCORE, guestScore);
        outState.putInt(KEY_MY_HOME_SCORE, homeScore);
        outState.putInt(KEY_MY_CURRENT_INNING, inningCount);
        outState.putString(KEY_MY_STRIKE_COUNT, strike);
        outState.putString(KEY_MY_BALL_COUNT, ball);
        outState.putString(KEY_MY_OUT_COUNT, out);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "inside of onRestoreInstanceState");
        guestScore = savedInstanceState.getInt(KEY_MY_GUEST_SCORE, 0);
        homeScore = savedInstanceState.getInt(KEY_MY_HOME_SCORE, 0);
        inningCount = savedInstanceState.getInt(KEY_MY_CURRENT_INNING, 0);
        strike = savedInstanceState.getString(KEY_MY_STRIKE_COUNT, "");
        ball = savedInstanceState.getString(KEY_MY_BALL_COUNT, "");
        out = savedInstanceState.getString(KEY_MY_OUT_COUNT, "");
        displayGuestScore(guestScore);
        displayHomeScore(homeScore);
        displayInning(inningCount);
        displayStrikeCount(strike);
        displayBallCount(ball);
        displayOutCount(out);
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
            ball = ball + "B";
            displayBallCount(ball);
        }
        if (ballCount == 2){
            ball = ball + "B";
            displayBallCount(ball);
        }
        if (ballCount == 3){
            ball = ball + "B";
            displayBallCount(ball);
        }
        if (ballCount == 4){
            displayBallCount("-----");
            displayStrikeCount("-----");
            ball = "";
            ballCount = 0;
            strikeCount = 0;
        }
    }
    /**
     * Increases the Strike Count by 1 after the once it reaches 3rd Strike the Ball & Strike count resets and Out
     * count is updated by 1. Once 3rd Out is reached the Strike,Ball, and Out counts reset.
     */
    public void strikeCount(View v){
        strikeCount = strikeCount + 1;
        if (strikeCount == 1){
            strike = strike + "S";
            displayStrikeCount(strike);
        } else if (strikeCount == 2){
            strike = strike + "S";
            displayStrikeCount(strike);
        } else {
            outCount = outCount + 1;
            displayBallCount("-----");
            displayStrikeCount("-----");
            strike = "";
            ball = "";
            strikeCount = 0;
            ballCount = 0;
            if (outCount == 1) {
                out = out + "O";
                displayOutCount(out);
            } else if(outCount == 2){
                out = out + "O";
                displayOutCount(out);
            } else {
                displayOutCount("-----");
                displayBallCount("-----");
                displayStrikeCount("-----");
                strike = "";
                ball = "";
                out = "";
                strikeCount = 0;
                ballCount = 0;
                outCount = 0;
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
        clear();
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
            int difference;
            Intent intent = new Intent(this, WinnerActivity.class);
            if(guestScore > homeScore) {
                difference = guestScore - homeScore;
                intent.putExtra("winner", "Ball Game! The away team wins by " + difference + "!");
            } else {
                difference = homeScore - guestScore;
                intent.putExtra("winner", "Ball Game! The home team wins by " + difference + "!");
            }
            startActivity(intent);
            clear();
        }
    }
    private void clear() {
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
        strike = "";
        ball = "";
        out = "";
        guestView.setTextColor(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));
        homeView.setTextColor(ColorStateList.valueOf(getResources().getColor(R.color.topOfInning)));
    }
}
