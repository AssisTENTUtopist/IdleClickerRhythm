package tent.assist.idleclicker.whackAMole;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import tent.assist.idleclicker.R;

public class Clicker extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int molesAmount = (int) getIntent().getSerializableExtra("MLS");
        int duration = (int) getIntent().getSerializableExtra("DUR");
        setContentView(new ClickerView(this, molesAmount, duration));
    }


}
