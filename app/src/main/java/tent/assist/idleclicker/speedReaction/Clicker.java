package tent.assist.idleclicker.speedReaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import static tent.assist.idleclicker.R.*;
import static tent.assist.idleclicker.R.color.*;

public class Clicker extends AppCompatActivity {
    Button butt;
    int attempts = 1;
    long randomStartTime = 0;
    long speedReactionTime = 0;
    boolean timeToPress;
    long[] speedReactionTimes;


    public void setResults(View view) {
        Intent intent = new Intent(Clicker.this, Resulter.class);
        intent.putExtra("SPD[]", speedReactionTimes);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_speed_reaction_clicker);
        butt = (Button) findViewById(id.speed_react_clicker_button);
        attempts = (int) getIntent().getSerializableExtra("ATM");
        speedReactionTimes = new long[attempts];

        View.OnClickListener buttSlap = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timeToPress) {
                    timeToPress = false;
                    speedReactionTime = SystemClock.elapsedRealtime() - speedReactionTime;
                    attempts--;
                    speedReactionTimes[attempts] = speedReactionTime;
                    butt.setBackgroundColor(getResources().getColor(colorVeryClose));
                    if (attempts < 1) setResults(v);
                    else butt.setText(string.wait);
                }
            }
        };

        new Thread() {
            public void run() {
                while (attempts > 0) {
                    if (!timeToPress) {
                        try {
                            randomStartTime = (long) (Math.random() * 10000);
                            Thread.sleep(randomStartTime);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        timeToPress = true;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                butt.setBackgroundColor(getResources().getColor(colorTooFar));
                                butt.setText(string.tap_button);
                            }
                        });
                        speedReactionTime = SystemClock.elapsedRealtime();
                    }
                }
            }
        }.start();

        butt.setOnClickListener(buttSlap);
    }
}