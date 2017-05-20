package tent.assist.idleclicker.whackAMole;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import tent.assist.idleclicker.R;

public class Clicker extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int duration = (int) getIntent().getSerializableExtra("DUR");
        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.preferences), MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(getString(R.string.preferences_duration), duration);
        editor.apply();

        setContentView(new ClickerView(this));
    }
}
