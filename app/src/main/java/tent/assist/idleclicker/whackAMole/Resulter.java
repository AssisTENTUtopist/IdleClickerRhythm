package tent.assist.idleclicker.whackAMole;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

import tent.assist.idleclicker.MainMainActivity;
import tent.assist.idleclicker.R;

public class Resulter extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whack_a_mole_resulter);
        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.preferences), MODE_PRIVATE);

        TextView counterView = (TextView) findViewById(R.id.counterView);
        int counter = sharedPref.getInt(getString(R.string.preferences_counter), 0);
        int duration = sharedPref.getInt(getString(R.string.preferences_duration), 10000) / 1000;
        float defaultFloatValue = (float)counter/duration;
        if (sharedPref.getFloat(getString(R.string.preferences_best_moles), defaultFloatValue) <= defaultFloatValue)
            sharedPref.edit().putFloat(getString(R.string.preferences_best_moles), defaultFloatValue).apply();

        counterView.setText(String.format(Locale.getDefault(), "%s\t\t%s\n\n%s\t\t%s\n\n%s\t\t%s\n\n%s\t\t%s",
                getString(R.string.moles_hit_counter), counter, getString(R.string.moles_duration), duration,
                getString(R.string.moles_per_second), defaultFloatValue, getString(R.string.best_moles_per_second),
                sharedPref.getFloat(getString(R.string.preferences_best_moles), defaultFloatValue)));
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
