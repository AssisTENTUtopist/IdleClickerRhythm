package tent.assist.idleclicker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class Resulter extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView timeView;

        timeView = (TextView) findViewById(R.id.timeView);

        int counter = (int) getIntent().getSerializableExtra("CLK");
        int timePassed = (int) getIntent().getSerializableExtra("UOT");
        long period = (long) getIntent().getSerializableExtra("PRD");

        String result;

        int residual = Math.abs(timePassed-counter);
        if (residual<10) {
            timeView.setBackgroundColor(getResources().getColor(R.color.colorVeryClose));
            result = getString(R.string.result_good);
        }
        else if (residual<30) {
            timeView.setBackgroundColor(getResources().getColor(R.color.colorAverageDistance));
            result = getString(R.string.result_average);
        }
        else {
            timeView.setBackgroundColor(getResources().getColor(R.color.colorTooFar));
            result = getString(R.string.result_bad);
        }

        timeView.setText(String.format(Locale.getDefault(), "%s\n\n%s\t%s\n%s\t%s\n%s\t%s\n\n\n%s",
                getText(R.string.time_text), getText(R.string.period), period, getText(R.string.real), timePassed, getText(R.string.expected), counter, result));
    }

    public void getBack (View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
