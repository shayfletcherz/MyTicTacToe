package com.example.mytictactoe;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class Board extends View {

    private final int boardColor;
    private final int xColor;
    private final int oColor;
    private final int winningLineColor;
    private final Paint paint = new Paint();
    private int cellSize = getWidth() / 3;
    private final Game game;
    private boolean winningLine = false;

    public Board(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        game = new Game();
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.Board, 0, 0);
        try {
            boardColor = a.getInteger(R.styleable.Board_boardColor, 0);
            xColor = a.getInteger(R.styleable.Board_xColor, 0);
            oColor = a.getInteger(R.styleable.Board_oColor, 0);
            winningLineColor = a.getInteger(R.styleable.Board_winningLineColor, 0);

        } finally {
            a.recycle();
        }
    }

    @Override
    protected void onMeasure(int width, int height) {
        super.onMeasure(width, height);
        int dimension = Math.min(getMeasuredWidth(), getMeasuredHeight());
        cellSize = dimension / 3;
        setMeasuredDimension(dimension, dimension);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        drewBoard(canvas);
        drawMarkers(canvas);

        if(winningLine){
            paint.setColor(winningLineColor);
            drawWinningLine(canvas);
        }

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            int row = (int) Math.ceil(y / cellSize);
            int col = (int) Math.ceil(x / cellSize);

            if (!winningLine) {
                if (game.updateGameBoard(row, col)) {
                    invalidate();

                    if (game.winnerCheck()) {
                        winningLine = true;
                        invalidate();
                    }
                    if (game.getPlayer() % 2 == 0) {
                        game.setPlayer(game.getPlayer() - 1);
                    } else {
                        game.setPlayer(game.getPlayer() + 1);
                    }
                }
            }


            invalidate();
            return true;
        }

        return false;
    }

    private void drewBoard(Canvas canvas) {
        paint.setColor(boardColor);
        paint.setStrokeWidth(16);


        for (int i = 1; i < 3; i++) {
            canvas.drawLine(cellSize * i, 0, cellSize * i, canvas.getWidth(), paint);
        }

        for (int j = 1; j < 3; j++) {
            canvas.drawLine(0, cellSize * j, canvas.getWidth(), cellSize * j, paint);
        }

    }

    private void drawMarkers(Canvas canvas) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (game.getGameBoard()[i][j] != 0) {
                    if (game.getGameBoard()[i][j] == 1) {
                        drawX(canvas, i, j);
                    } else {
                        drawO(canvas, i, j);
                    }
                }
            }
        }
    }

    private void drawO(Canvas canvas, int row, int col) {
        paint.setColor(oColor);
        canvas.drawOval((float) (col * cellSize + cellSize * 0.2), (float) (row * cellSize + cellSize * 0.2), (float) ((col * cellSize + cellSize) - cellSize * 0.2), (float) ((row * cellSize + cellSize) - cellSize * 0.2), paint);
    }

    private void drawX(Canvas canvas, int row, int col) {
        paint.setColor(xColor);
        canvas.drawLine((float) ((col + 1) * cellSize - cellSize * 0.2), (float) (row * cellSize + cellSize * 0.2), (float) (col * cellSize + cellSize * 0.2), (float) ((row + 1) * cellSize - cellSize * 0.2), paint);
        canvas.drawLine((float) (col * cellSize + cellSize * 0.2), (float) (row * cellSize + cellSize * 0.2), (float) ((col + 1) * cellSize - cellSize * 0.2), (float) ((row + 1) * cellSize - cellSize * 0.2), paint);
    }


    private void horizontalLine(Canvas canvas, int row, int col) {
        canvas.drawLine(col, (row*cellSize + (float)cellSize/2), (cellSize*3), (row*cellSize + (float)cellSize/2), paint);
    }


    private void verticalLine(Canvas canvas, int row, int col){
        canvas.drawLine(col*cellSize + (float)cellSize/2, row, col*cellSize + (float)cellSize/2, cellSize*3, paint);
    }


    private void leftToRightDiagonal(Canvas canvas){
        canvas.drawLine(0, cellSize*3, cellSize*3, 0, paint);
    }

    private void rightToLeftDiagonal(Canvas canvas){
        canvas.drawLine(0, 0, cellSize*3, cellSize*3, paint);
    }

    private void drawWinningLine(Canvas canvas){
        int row = game.getWinType()[0];
        int col = game.getWinType()[1];

        switch (game.getWinType()[2]) {
            case 1:
                horizontalLine(canvas, row, col);
                break;
            case 2:
                verticalLine(canvas, row, col);
                break;
            case 3:
                rightToLeftDiagonal(canvas);
                break;
            case 4:
                leftToRightDiagonal(canvas);
                break;
        }

    }

    public void setUpGame(Button reset, Button home, TextView playerDisplay, String[] names) {
        game.setResetButton(reset);
        game.setHomeButton(home);
        game.setPlayerTurn(playerDisplay);
        game.setPlayerNames(names);

    }

    public void resetGame() {
        game.resetGame();
        winningLine = false;
    }
}

