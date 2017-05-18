package tent.assist.idleclicker.senseOfBar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import tent.assist.idleclicker.MainMainActivity;
import tent.assist.idleclicker.R;

public class Clicker extends AppCompatActivity {
    boolean isAlive;
    Button butt;
    int counter = 0;
    int unitOfTime = 0;
    long period = 0;

    public void setResults() {
        isAlive = false;
        Intent intent = new Intent(Clicker.this, Resulter.class);
        intent.putExtra("CLK", counter);
        intent.putExtra("UOT", unitOfTime);
        intent.putExtra("PRD", period);
        startActivity(intent);
    }


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sense_of_bar_clicker);
        isAlive = true;
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
                int duration = (int) getIntent().getSerializableExtra("DUR");
                int example = 0;
                while (isAlive) {
                    try {
                        Thread.sleep(period/2);
                        if (example <= 4) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    butt.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                                }
                            });
                            example++;
                        }
                        Thread.sleep(period/2);
                        if (example <= 4) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    butt.setBackgroundColor(getResources().getColor(R.color.colorVeryClose));
                                }
                            });
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    unitOfTime++;
                    if (unitOfTime * period >= duration * 1000)
                        setResults();
                }
            }
        }.start();

        butt.setOnClickListener(buttSlap);
    }
}