package tent.assist.idleclicker.SpeedReaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import tent.assist.idleclicker.MainMainActivity;

import static tent.assist.idleclicker.R.*;
import static tent.assist.idleclicker.R.color.*;

public class Clicker extends AppCompatActivity {
    Button butt;
    int attempts = (int)getIntent().getSerializableExtra("ATM");
    long randomStartTime = 0;
    long speedReactionTime = 0;

    int counter = 0;
    int unitOfTime = 0;

    public void setResults(View view) {
        Intent intent = new Intent(Clicker.this, Resulter.class);
        intent.putExtra("CLK", counter);
        intent.putExtra("UOT", unitOfTime);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_sense_of_bar_clicker);
        butt = (Button) findViewById(id.speed_react_clicker_button);

        View.OnClickListener buttSlap = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speedReactionTime = SystemClock.currentThreadTimeMillis() - speedReactionTime;
                counter++;
                attempts--;
                butt.setBackgroundColor(getResources().getColor(colorVeryClose));
                if (attempts < 1) setResults(v);
            }
        };

        new Thread() {
            public void run() {
                randomStartTime = (long)(Math.random() * 10000000);
                while (MainMainActivity.ANSWER_TO_THE_ULTIMATE_QUESTION_OF_LIFE_THE_UNIVERSE_AND_EVERYTHING == 42) {
                    unitOfTime++;
                    butt.setBackgroundColor(getResources().getColor(colorTooFar));
                    try {
                        Thread.sleep(randomStartTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        butt.setOnClickListener(buttSlap);
    }
}