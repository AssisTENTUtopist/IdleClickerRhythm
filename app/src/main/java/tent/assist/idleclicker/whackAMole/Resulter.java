package tent.assist.idleclicker.whackAMole;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

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
        int defaultValue = 0;
        int counter = sharedPref.getInt(getString(R.string.preferences_counter), defaultValue);
        defaultValue = 10000;
        int duration = sharedPref.getInt(getString(R.string.preferences_duration), defaultValue);
        counterView.setText(String.valueOf(counter));
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
