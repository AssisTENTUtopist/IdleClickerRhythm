package tent.assist.idleclicker.speedReaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import tent.assist.idleclicker.MainMainActivity;
import tent.assist.idleclicker.R;

public class Resulter extends AppCompatActivity{
    private String getTime(long millis) {
        return millis/1000 + ":" + millis%1000/100 + "" + millis%100/10 + "" + millis%10;
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

        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.preferences), MODE_PRIVATE);

        if (sharedPref.getLong(getString(R.string.preferences_best_speed), speedReactionTimes[bestTimeId])
                >= speedReactionTimes[bestTimeId])
            sharedPref.edit().putLong(getString(R.string.preferences_best_speed), speedReactionTimes[bestTimeId]).apply();
        long bestTimeEver = sharedPref.getLong(getString(R.string.preferences_best_speed), speedReactionTimes[bestTimeId]);

        result = result + "\n" + getString(R.string.average_time) + "\n" + getTime(averageTime / speedReactionTimes.length) +
                "\n\n" + getString(R.string.best_time) + "\n" + getTime(speedReactionTimes[bestTimeId]) +
                "\n\n" + getString(R.string.best_time_record) + "\n" + getTime(bestTimeEver);

        timeView.setText(result);
    }

    public void backToMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void backToMainMainActivity(View view) {
        Intent intent = new Intent(this, MainMainActivity.class);
        startActivity(intent);
    }
}