package tent.assist.idleclicker.whackAMole;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.List;

class Sprite {
    private Bitmap bitmap;

    private List<Rect> frames;
    private int frameWidth;
    private int frameHeight;
    private int currentFrame;
    private double frameTime;
    private double timeForCurrentFrame;

    private double x;
    private double y;

    Sprite(double x,
                  double y,
                  Rect initialFrame,
                  Bitmap bitmap)     {

        this.x = x;
        this.y = y;

        this.bitmap = bitmap;

        this.frames = new ArrayList<>();
        this.frames.add(initialFrame);

        this.bitmap = bitmap;

        this.timeForCurrentFrame = 0.0;
        this.frameTime = 25;
        this.currentFrame = 0;

        this.frameWidth = initialFrame.width();
        this.frameHeight = initialFrame.height();
    }

    double getX() {
        return x;
    }

    double getY() {
        return y;
    }

    int getFrameWidth() {
        return frameWidth;
    }

    int getFrameHeight() {
        return frameHeight;
    }

    int getCurrentFrame() {
        return currentFrame;
    }

    void setCurrentFrame(int currentFrame) {
        this.currentFrame = currentFrame%frames.size();
    }

    double getTimeForCurrentFrame() {
        return timeForCurrentFrame;
    }

    void setTimeForCurrentFrame(double timeForCurrentFrame) {
        this.timeForCurrentFrame = Math.abs(timeForCurrentFrame);
    }

    void addFrame (Rect frame) {
        frames.add(frame);
    }

    int getFramesCount () {
        return frames.size();
    }

    void update (int ms) {
        timeForCurrentFrame += ms;

        if (currentFrame != frames.size() - 1) {
            if (timeForCurrentFrame >= frameTime) {
                currentFrame = (currentFrame + 1) % frames.size();
                timeForCurrentFrame = timeForCurrentFrame - frameTime;
            }
        }
    }
    void draw (Canvas canvas) {
        Paint p = new Paint();

        Rect destination = new Rect((int)x, (int)y, (int)(x + frameWidth), (int)(y + frameHeight));
        canvas.drawBitmap(bitmap, frames.get(currentFrame), destination,  p);
    }
}
