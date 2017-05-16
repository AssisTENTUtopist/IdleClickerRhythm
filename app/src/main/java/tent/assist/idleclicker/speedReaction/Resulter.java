package tent.assist.idleclicker.speedReaction;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import tent.assist.idleclicker.MainMainActivity;
import tent.assist.idleclicker.R;

public class Resulter extends AppCompatActivity{
    private String getTime(long millis) {
        return millis/1000 + ":" + millis%1000;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speed_reaction_resulter);

        TextView timeView = (TextView) findViewById(R.id.timeView);

        long[] speedReactionTimes = (long[]) getIntent().getSerializableExtra("SPD[]");
        int bestTimeId = 0;
        long averageTime = 0;

        String result = getString(R.string.ss_ms) + "\n";

        for (int i = 0; i < speedReactionTimes.length; i++) {
            averageTime += speedReactionTimes[i];
            if (speedReactionTimes[i] < speedReactionTimes[bestTimeId]) bestTimeId = i;
            result = result + (i+1) + ")\t" + getTime(speedReactionTimes[i]) + "\n";
        }

        result = result + "\n" + getString(R.string.best_time) + "\n" + getTime(speedReactionTimes[bestTimeId]) +
                "\n\n" + getString(R.string.average_time) + "\n" + getTime(averageTime / speedReactionTimes.length);

        timeView.setText(result);
    }

    public void gottaGetBack(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void backToThePast(View view) {
        Intent intent = new Intent(this, MainMainActivity.class);
        startActivity(intent);
    }

    /*
    public void samuraiJack(View view) {
        Toast.makeText(this, "Dab", Toast.LENGTH_SHORT).show();
    }
    */
}