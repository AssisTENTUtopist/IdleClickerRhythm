package tent.assist.idleclicker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SenseOfBarMainActivity extends AppCompatActivity {
    EditText period;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        period = (EditText) findViewById(R.id.editPeriodText);
    }

    public void setPeriod (View view) {
        Intent intent = new Intent(this, Clicker.class);
        if (period.getText().toString().length()>0)
            intent.putExtra("PRD", 60000/(Long.parseLong(period.getText().toString())));
        else {
            Toast.makeText(this, R.string.set_default, Toast.LENGTH_LONG).show();
            intent.putExtra("PRD", (long)500);
        }
        startActivity(intent);
    }
}
