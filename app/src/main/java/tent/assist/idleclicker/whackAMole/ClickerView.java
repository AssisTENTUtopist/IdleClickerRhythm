package tent.assist.idleclicker.whackAMole;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;

import tent.assist.idleclicker.R;

class ClickerView extends View {
    private Sprite[] mole;
    Paint p = new Paint();

    private int currentMole, molesAmount, duration, counter;

    private final int timerInterval = 30;

    public ClickerView(Context context, int molesAmount, int duration) {
        super(context);
        this.molesAmount = molesAmount;
        this.duration = duration;
        new ClickerView(context);
    }

    public ClickerView(Context context) {
        super(context);

        currentMole = (int) (Math.random() * molesAmount);
        Bitmap hole = BitmapFactory.decodeResource(getResources(), R.drawable.hole);
        int w = hole.getWidth() / 4;
        int h = hole.getHeight();
        Rect firstFrame = new Rect(0, 0, w, h);
        mole =new Sprite[molesAmount];
        for (int i = 0; i < molesAmount; i++) {
            if (molesAmount == 3) mole[i] = new Sprite(getWidth()/2 - w/2, getHeight()/(i+1)/2 - h/2, firstFrame, hole);
            if (molesAmount == 6) mole[i] = new Sprite(getWidth()/3*(i%2+1) - w/2, getHeight()/(i%2+1)/2 - h/2, firstFrame, hole);
        }

        for (int i = 0; i < 1; i++)
            for (int j = 0; j < 4; j++)
                for (int k = 0; k < molesAmount; k++)
                    mole[k].addFrame(new Rect(j*w, i*h, j*w+w, i*w+w));

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
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int eventAction = event.getAction();
        if (eventAction == MotionEvent.ACTION_DOWN)  {
            if (event.getY() < mole[currentMole].getY() + mole[currentMole].getFrameHeight() &&
                    event.getY() > mole[currentMole].getY() - mole[currentMole].getFrameHeight() &&
                    event.getX() < mole[currentMole].getX() + mole[currentMole].getFrameWidth() &&
                    event.getX() > mole[currentMole].getX() + mole[currentMole].getFrameWidth()) {
                mole[currentMole].setCurrentFrame(0);
                currentMole = (int) (Math.random() * molesAmount);
                counter++;
            }
        }
        return true;
    }


    private class Timer extends CountDownTimer {
        Timer() {
            super(Integer.MAX_VALUE, timerInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            update ();
        }

        @Override
        public void onFinish() {}
    }
}
// 3 22 32 33 232 323 333  343   434   444
// 3  4  5  6   7   8   9   10    11    12