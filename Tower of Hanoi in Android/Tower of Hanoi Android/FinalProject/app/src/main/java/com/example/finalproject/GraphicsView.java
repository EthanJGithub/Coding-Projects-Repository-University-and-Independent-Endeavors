package com.example.finalproject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GraphicsView extends View {
    private Paint paint, textPaint;
    private Handler handler = new Handler();
    private List<Integer>[] rods;
    private Queue<Runnable> animationQueue = new LinkedList<>();
    private boolean isAnimating = false;
    private int moveCount = 0;

    private static final int[] COLORS = {
            Color.rgb(255, 105, 180), // Hot Pink
            Color.rgb(255, 165, 0),   // Orange
            Color.rgb(173, 255, 47),  // Green Yellow
            Color.rgb(0, 191, 255),   // Deep Sky Blue
            Color.rgb(238, 130, 238), // Violet
            Color.rgb(255, 20, 147),  // Deep Pink
            Color.rgb(255, 215, 0)    // Gold
    };

    public GraphicsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(50);
        setupRods(3); // Default to 3 disks for initial setup
    }

    public void setupRods(int num) {
        rods = new ArrayList[3];
        for (int i = 0; i < 3; i++) {
            rods[i] = new ArrayList<>();
        }
        for (int i = num; i > 0; i--) {
            rods[0].add(i);
        }
        moveCount = 0;
        invalidate();
        isAnimating = false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawRods(canvas);
        drawDisks(canvas);
        canvas.drawText("Moves: " + moveCount, 10, 60, textPaint);
    }

    private void drawRods(Canvas canvas) {
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(12);
        int rodSpacing = getWidth() / 4;
        int rodHeight = getHeight() - 150;

        for (int i = 1; i <= 3; i++) {
            int x = rodSpacing * i;
            canvas.drawLine(x, 100, x, rodHeight, paint);
        }
    }

    private void drawDisks(Canvas canvas) {
        int baseHeight = getHeight() - 150;
        int rodSpacing = getWidth() / 4;
        int diskHeight = 30;

        for (int i = 0; i < 3; i++) {
            List<Integer> rodDisks = rods[i];
            for (int j = 0; j < rodDisks.size(); j++) {
                int disk = rodDisks.get(j);
                int diskWidth = disk * 20 + 40;
                int x = rodSpacing * (i + 1) - diskWidth / 2;
                int y = baseHeight - j * (diskHeight + 10);
                paint.setColor(COLORS[(disk - 1) % COLORS.length]); // Consistent color assignment
                canvas.drawRect(x, y, x + diskWidth, y + diskHeight, paint);
            }
        }
    }

    public void startSolving() {
        moveCount = 0;
        int numDisks = rods[0].size();
        solveHanoi(numDisks, 0, 1, 2);
        executeNextMove();
    }

    private void solveHanoi(int n, int fromRod, int toRod, int auxRod) {
        if (n > 0) {
            solveHanoi(n - 1, fromRod, auxRod, toRod);
            animationQueue.offer(() -> moveDisk(fromRod, toRod));
            solveHanoi(n - 1, auxRod, toRod, fromRod);
        }
    }

    private void executeNextMove() {
        if (!animationQueue.isEmpty() && !isAnimating) {
            Runnable nextMove = animationQueue.poll();
            isAnimating = true;
            handler.post(nextMove);
        }
    }

    private void moveDisk(int fromRod, int toRod) {
        if (!rods[fromRod].isEmpty()) {
            Integer disk = rods[fromRod].remove(rods[fromRod].size() - 1);
            if (rods[toRod].isEmpty() || disk < rods[toRod].get(rods[toRod].size() - 1)) {
                rods[toRod].add(disk);
                moveCount++;
                handler.postDelayed(() -> {
                    invalidate();
                    isAnimating = false;
                    executeNextMove(); // Ensure sequential move execution
                }, 1000);
            } else {
                rods[fromRod].add(disk);
                Log.e("TowerOfHanoi", "Invalid move attempted with disk " + disk);
                isAnimating = false;
                executeNextMove();
            }
        } else {
            isAnimating = false;
            executeNextMove();
        }
    }
}
