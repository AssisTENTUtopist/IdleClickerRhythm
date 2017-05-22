package tent.assist.idleclicker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import tent.assist.idleclicker.speedReaction.MainActivity;

public class MainMainActivity extends AppCompatActivity {
    private Button[] buttons;
    private TextView info;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        info = (TextView) findViewById(R.id.textView2);
        buttons = new Button[3];
        buttons[0] = (Button) findViewById(R.id.sense_of_bar_button);
        buttons[1] = (Button) findViewById(R.id.speed_reaction_button);
        buttons[2] = (Button) findViewById(R.id.whack_a_mole_button);
        String intro = getString(R.string.introduction);
        info.setText(intro);

        for (int i = 0; i < buttons.length; i++) {
            switch (i) {
                case 0:
                    intro = getString(R.string.sense_of_bar_intro);
                    break;
                case 1:
                    intro = getString(R.string.speed_reaction_intro);
                    break;
                case 2:
                    intro = getString(R.string.whack_a_mole_intro);
                    break;
            }
            final String finalIntro = intro;
            buttons[i].setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    info.setText(finalIntro);
                    return false;
                }
            });
        }
    }

    public void startBarGame (View view) {
        Intent intent = new Intent(this, tent.assist.idleclicker.senseOfBar.MainActivity.class);
        startActivity(intent);
    }

    public void startSpeedReactionGame(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void startWhackAMoleGame(View view) {
        Intent intent = new Intent(this, tent.assist.idleclicker.whackAMole.MainActivity.class);
        startActivity(intent);
    }
}
