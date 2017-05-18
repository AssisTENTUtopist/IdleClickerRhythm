package tent.assist.idleclicker.whackAMole;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import tent.assist.idleclicker.R;

public class MainActivity extends AppCompatActivity {
    EditText duration, amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whack_a_mole_main);
        duration = (EditText) findViewById(R.id.editDurationText);
        amount = (EditText) findViewById(R.id.editMoles);
    }

    public void setDuration(View view) {
        Intent intent = new Intent(this, Clicker.class);
        if (duration.getText().toString().length()>0)
            intent.putExtra("DUR", Integer.parseInt(duration.getText().toString())*1000);
        else {
            intent.putExtra("DUR", 10000);
        }
        int moles;
        if (amount.getText().toString().length()>0) {
            moles = Integer.parseInt(amount.getText().toString());
            if (moles >= 3 && moles <= 12)
                intent.putExtra("MLS", moles);
            else if (moles < 3)
                intent.putExtra("MLS", 3);
            else intent.putExtra("MLS", 12);
        } else intent.putExtra("MLS", 3);
        startActivity(intent);
    }
}
