package tent.assist.idleclicker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import tent.assist.idleclicker.SpeedReaction.MainActivity;

public class MainMainActivity extends AppCompatActivity{
    public static final int ANSWER_TO_THE_ULTIMATE_QUESTION_OF_LIFE_THE_UNIVERSE_AND_EVERYTHING = 42;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
    }

    public void startBarGame (View view) {
        Intent intent = new Intent(this, tent.assist.idleclicker.SenseOfBar.MainActivity.class);
        startActivity(intent);
    }

    public void startSpeedReactionGame(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
