package tent.assist.idleclicker.SpeedReaction;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import tent.assist.idleclicker.R;
import tent.assist.idleclicker.SenseOfBar.Clicker;

public class MainActivity extends AppCompatActivity {
    EditText attempts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speed_reaction_main);
    }

    public void setAttempts (View view) {
        Intent intent = new Intent(this, Clicker.class);
        if (attempts.getText().toString().length()>0)
            intent.putExtra("ATM", attempts.getText().toString());
        else {
            Toast.makeText(this, R.string.set_default, Toast.LENGTH_LONG).show();
            intent.putExtra("ATM", 1);
        }
        startActivity(intent);
    }
}
