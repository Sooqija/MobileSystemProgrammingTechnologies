package com.example.a09_canvas;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainGamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private static final String TAG = MainGamePanel.class.getSimpleName();

    private MainThread thread;
    private ElaineAnimated elaine;

    public MainGamePanel(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
        elaine = new ElaineAnimated(
                BitmapFactory.decodeResource(getResources(), R.drawable.walk_elaine)
                , 10, 50
                , 30, 47
                , 5, 5);
        thread = new MainThread(getHolder(), this);
        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.d(TAG, "Surface is being destroyed");
        thread.setRunning(false);
        Log.d(TAG, "Thread was shut down cleanly");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (canvas != null) {
            canvas.drawColor(Color.WHITE);
            elaine.draw(canvas);
        } else {
            surfaceDestroyed(getHolder());
        }
    }

    public void update() {
        elaine.update(System.currentTimeMillis());
    }

    public static class MainThread extends Thread {

        private final static int MAX_FPS = 50;
        private final static int MAX_FRAME_SKIPS = 5;
        private final static int FRAME_PERIOD = 1000 / MAX_FPS;

        private static final String TAG = MainThread.class.getSimpleName();

        private SurfaceHolder surfaceHolder;
        private MainGamePanel gamePanel;
        private boolean running;

        public void setRunning(boolean running) {
            this.running = running;
        }

        public MainThread(SurfaceHolder surfaceHolder, MainGamePanel gamePanel) {
            super();
            this.surfaceHolder = surfaceHolder;
            this.gamePanel = gamePanel;
        }


        @SuppressLint("WrongCall")
        @Override
        public void run() {
            Canvas canvas;
            Log.d(TAG, "Starting game loop");

            long beginTime;
            long timeDiff;
            int sleepTime;
            int framesSkipped;

            sleepTime = 0;

            while (running) {
                canvas = null;
                try {
                    canvas = this.surfaceHolder.lockCanvas();
                    synchronized (surfaceHolder) {
                        beginTime = System.currentTimeMillis();
                        framesSkipped = 0;
                        this.gamePanel.update();
                        this.gamePanel.onDraw(canvas);
                        timeDiff = System.currentTimeMillis() - beginTime;
                        sleepTime = (int) (FRAME_PERIOD - timeDiff);

                        if (sleepTime > 0) {
                            try {
                                Thread.sleep(sleepTime);
                            } catch (InterruptedException e) {
                            }
                        }

                        while (sleepTime < 0 && framesSkipped < MAX_FRAME_SKIPS) {
                            this.gamePanel.update();
                            sleepTime += FRAME_PERIOD;
                            framesSkipped++;
                        }
                    }
                } finally {
                    if (canvas != null) {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                }
            }
        }
    }

    public class ElaineAnimated {
        private Bitmap bitmap;
        private Rect sourceRect;
        private int frameNr;
        private int currentFrame;
        private long frameTicker;
        private int framePeriod;

        private int spriteWidth;
        private int spriteHeight;

        private int x;
        private int y;

        public ElaineAnimated(Bitmap bitmap, int x, int y, int width, int height, int fps, int frameCount) {
            this.bitmap = bitmap;
            this.x = x;
            this.y = y;
            currentFrame = 0;
            frameNr = frameCount;
            spriteWidth = bitmap.getWidth() / frameCount;
            spriteHeight = bitmap.getHeight();
            sourceRect = new Rect(0, 0, spriteWidth, spriteHeight);
            framePeriod = 1000 / fps;
            frameTicker = 0l;
        }

        public void update(long gameTime) {
            if (gameTime > frameTicker + framePeriod) {
                frameTicker = gameTime;
                currentFrame++;
                if (currentFrame >= frameNr) {
                    currentFrame = 0;
                }
            }
            this.sourceRect.left = currentFrame * spriteWidth;
            this.sourceRect.right = this.sourceRect.left + spriteWidth;
        }

        public void draw(Canvas canvas) {
            Rect destRect = new Rect(x, y, x + spriteWidth, y + spriteHeight);
            canvas.drawBitmap(bitmap, sourceRect, destRect, null);

            canvas.drawBitmap(bitmap, 20, 150, null);
            Paint paint = new Paint();
            paint.setARGB(50, 0, 255, 0);
            canvas.drawRect(20 + (currentFrame * destRect.width()), 150, 20 + (currentFrame * destRect.width()) + destRect.width(), 150 + destRect.height(),  paint);
        }
    }
}