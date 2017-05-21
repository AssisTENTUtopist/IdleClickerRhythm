package tent.assist.idleclicker.senseOfBar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

import tent.assist.idleclicker.MainMainActivity;
import tent.assist.idleclicker.R;

public class Resulter extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sense_of_bar_resulter);

        TextView timeView;

        timeView = (TextView) findViewById(R.id.timeView);

        int counter = (int) getIntent().getSerializableExtra("CLK");
        int timePassed = (int) getIntent().getSerializableExtra("UOT");
        long period = 60000 / (long) getIntent().getSerializableExtra("PRD");

        String result;

        int residual = Math.abs(timePassed - counter);
        if (residual<5) {
            timeView.setBackgroundColor(getResources().getColor(R.color.colorVeryClose));
            result = getString(R.string.sense_of_bar_result_good);
        }
        else if (residual<10) {
            timeView.setBackgroundColor(getResources().getColor(R.color.colorAverageDistance));
            result = getString(R.string.sense_of_bar_result_average);
        }
        else {
            timeView.setBackgroundColor(getResources().getColor(R.color.colorTooFar));
            result = getString(R.string.sense_of_bar_result_bad);
        }

        timeView.setText(String.format(Locale.getDefault(), "%s\t\t%s\t\t%s\n%s\t\t%s\n%s\t\t%s\n\n\n%s",
                getText(R.string.period), period, getText(R.string.bpm), getText(R.string.real_beats), timePassed, getText(R.string.expected_beats), counter, result));
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
