package tent.assist.idleclicker.whackAMole;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.CountDownTimer;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import tent.assist.idleclicker.R;

class ClickerView extends View {
    private Sprite[] mole;

    private int currentMole, duration, counter;

    private static final int molesAmount = 9;

    private final int timerInterval = 30;

    public ClickerView(Context context) {
        super(context);
        duration = 10000;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        currentMole = (int) (Math.random() * molesAmount);
        Bitmap hole = BitmapFactory.decodeResource(getResources(), R.drawable.hole);
        int w = hole.getWidth() / 4;
        int h = hole.getHeight();
        Rect firstFrame = new Rect(0, 0, w, h);
        mole =new Sprite[molesAmount];
        for (int i = 0; i < molesAmount/3; i++) {
            mole[i*3] = new Sprite(width / 4 * (i + 1) - w / 2, height / 4 - h, firstFrame, hole);
            mole[i*3+1] = new Sprite(width / 4 * (i + 1) - w / 2, height / 4 * 2 - h, firstFrame, hole);
            mole[i*3+2] = new Sprite(width / 4 * (i + 1) - w / 2, height / 4 * 3 - h, firstFrame, hole);
        }

        for (int i = 0; i < 1; i++)
            for (int j = 0; j < 4; j++)
                for (int k = 0; k < molesAmount; k++)
                    mole[k].addFrame(new Rect(j * w, i * h, j * w + w, i * w + w));

        Timer timer = new Timer();
        timer.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRGB(83, 229, 142);
        for (Sprite moles : mole)
            moles.draw(canvas);
    }

    protected void update () {
        mole[currentMole].update(timerInterval);
        if (mole[currentMole].getCurrentFrame() == mole[currentMole].getFramesCount() - 1
                && mole[currentMole].getTimeForCurrentFrame() >= 500) {
            mole[currentMole].setTimeForCurrentFrame(0);
            mole[currentMole].setCurrentFrame(0);
            currentMole = (int) (Math.random() * molesAmount);
        }
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int eventAction = event.getAction();
        if (eventAction == MotionEvent.ACTION_DOWN)  {
            if (event.getY() < mole[currentMole].getY() + mole[currentMole].getFrameHeight() &&
                    event.getY() > mole[currentMole].getY() - mole[currentMole].getFrameHeight() &&
                    event.getX() < mole[currentMole].getX() + mole[currentMole].getFrameWidth() &&
                    event.getX() > mole[currentMole].getX() - mole[currentMole].getFrameWidth()) {
                mole[currentMole].setTimeForCurrentFrame(0);
                mole[currentMole].setCurrentFrame(0);
                currentMole = (int) (Math.random() * molesAmount);
                counter++;
            }
        }
        return true;
    }


    private class Timer extends CountDownTimer {
        Timer() {
            super(duration, timerInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            update ();
        }

        @Override
        public void onFinish() {
            Intent intent = new Intent(getContext(), Resulter.class);
            intent.putExtra("CNT", counter);
            getContext().startActivity(intent);
        }
    }
}
// 3 22 32 33 232 323 333  343   434   444
// 3  4  5  6   7   8   9   10    11    12