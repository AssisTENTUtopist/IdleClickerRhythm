package tent.assist.idleclicker.SenseOfBar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import tent.assist.idleclicker.MainMainActivity;
import tent.assist.idleclicker.R;

public class Clicker extends AppCompatActivity {
    Button butt;
    int counter = 0;
    int unitOfTime = 0;
    long period = 0;

    public void setResults(View view) {
        Intent intent = new Intent(Clicker.this, Resulter.class);
        intent.putExtra("CLK", counter);
        intent.putExtra("UOT", unitOfTime);
        intent.putExtra("PRD", period);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sense_of_bar_clicker);
        butt = (Button) findViewById(R.id.button);

        View.OnClickListener buttSlap = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
            }
        };

        new Thread() {
            public void run() {
                period = (long) getIntent().getSerializableExtra("PRD");
                while (MainMainActivity.ANSWER_TO_THE_ULTIMATE_QUESTION_OF_LIFE_THE_UNIVERSE_AND_EVERYTHING == 42) {
                    unitOfTime++;
                    try {
                        Thread.sleep(period);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        butt.setOnClickListener(buttSlap);
    }
}