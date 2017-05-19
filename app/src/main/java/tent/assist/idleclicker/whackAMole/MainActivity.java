package tent.assist.idleclicker.whackAMole;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import tent.assist.idleclicker.R;

public class MainActivity extends AppCompatActivity {
    EditText duration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whack_a_mole_main);
        duration = (EditText) findViewById(R.id.editDurationText);
    }

    public void setDuration(View view) {
        Intent intent = new Intent(this, Clicker.class);
        if (duration.getText().toString().length()>0)
            intent.putExtra("DUR", Integer.parseInt(duration.getText().toString())*1000);
        else {
            intent.putExtra("DUR", 10000);
        }
        startActivity(intent);
    }
}
