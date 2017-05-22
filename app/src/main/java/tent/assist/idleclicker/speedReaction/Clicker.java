package tent.assist.idleclicker.speedReaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import tent.assist.idleclicker.R;

import static tent.assist.idleclicker.R.*;
import static tent.assist.idleclicker.R.color.*;

public class Clicker extends AppCompatActivity {
    Button button;
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
        button = (Button) findViewById(id.speed_react_clicker_button);
        attempts = (int) getIntent().getSerializableExtra("ATM");
        speedReactionTimes = new long[attempts];

        View.OnClickListener onButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timeToPress) {
                    timeToPress = false;
                    speedReactionTime = System.currentTimeMillis() - speedReactionTime;
                    attempts--;
                    speedReactionTimes[attempts] = speedReactionTime;
                    button.setBackgroundColor(Color.rgb(0, 170, 0));
                    if (attempts < 1) setResults(v);
                    else button.setText(string.wait);
                } else Toast.makeText(Clicker.this, getText(string.warning), Toast.LENGTH_SHORT).show();
            }
        };

        new Thread() {
            public void run() {
                while (attempts > 0) {
                    if (!timeToPress) {
                        try {
                            randomStartTime = (long) (Math.random() * 5000) + 2000;
                            Thread.sleep(randomStartTime);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        timeToPress = true;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                button.setBackgroundColor(Color.rgb(170, 0, 0));
                                button.setText(string.tap_button);
                            }
                        });
                        speedReactionTime = System.currentTimeMillis();
                    }
                }
            }
        }.start();

        button.setOnClickListener(onButtonClickListener);
    }
}