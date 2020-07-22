package com.example.robad.scorekeeper;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class WinnerActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "MainActivity";
    private ImageView phoneView, messageView, mapView;
    String theWinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);
        Log.d(TAG, "Start of onCreate method");

        getReferenceToViews();
        setListeners();

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }
        theWinner = extras.getString("winner");
        TextView winnerView = findViewById(R.id.theWinner);
        winnerView.setText(theWinner);

        Log.d(TAG, "end of onCreate method");
    }

    private void getReferenceToViews() {
        phoneView = findViewById(R.id.imageView_call);
        messageView = findViewById(R.id.imageView_text);
        mapView = findViewById(R.id.imageView_map);
    }

    private void setListeners() {
        Log.d(TAG, "Start of setListeners method");
        phoneView.setOnClickListener(this);
        messageView.setOnClickListener(this);
        mapView.setOnClickListener(this);
        Log.d(TAG, "End of setListeners method");
    }


    @Override
    public void onClick(View v) {
        Log.d(TAG, "Start of onClick method");

        switch (v.getId()) {
            case R.id.imageView_call:
                Log.d(TAG, "Call button was clicked");
                makePhoneCall();
                break;
            case R.id.imageView_text:
                Log.d(TAG, "Text button was clicked");
                sendText();
                break;
            case R.id.imageView_map:
                Log.d(TAG, "Map button was clicked");
                findLocation();
                break;
        }

        Log.d(TAG, "End of onClick method");

    }

    private void findLocation() {
        Log.d(TAG, "Start of findLocation method");
        String loc = "baseball field near me";
        Uri geoLoc = Uri.parse("geo:0,0?q=" + loc);
        Intent intent = new Intent(Intent.ACTION_VIEW, geoLoc);
        if(intent.resolveActivity(getPackageManager())   !=  null  ){
            startActivity(intent);
        }
        else{
            Log.d(TAG, "Cannot find location " + loc);
            Toast.makeText(this, "Cannot find location " + loc, Toast.LENGTH_LONG).show();
        }
        Log.d(TAG, "End of findLocation method");
    }

    private void sendText() {
        Log.d(TAG, "Start of sendText method");
        String mimeType = "text/plain";
        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle("Pick an app")
                .setText(theWinner + " We are the CHAMPIONS! Let's pop some champagne.")
                .startChooser();
        Log.d(TAG, "End of sendText method");

    }

    private void makePhoneCall() {
        Log.d(TAG, "Start of makePhoneCall method");
        Intent intent = new Intent(Intent.ACTION_DIAL);
        startActivity(intent);
        Log.d(TAG, "End of makePhoneCall method");
    }
}
