package tent.assist.idleclicker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import tent.assist.idleclicker.speedReaction.MainActivity;

public class MainMainActivity extends AppCompatActivity {
    private Button[] butts;
    private TextView info;
    public static final int ANSWER_TO_THE_ULTIMATE_QUESTION_OF_LIFE_THE_UNIVERSE_AND_EVERYTHING = 42;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        info = (TextView) findViewById(R.id.textView2);
        butts = new Button[2];
        butts[0] = (Button) findViewById(R.id.sense_of_bar_button);
        butts[1] = (Button) findViewById(R.id.speed_reaction_button);

        for (final Button butt : butts) {
            butt.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    info.setText(R.string.introduction);
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
