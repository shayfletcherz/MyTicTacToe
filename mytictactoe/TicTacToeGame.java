package com.example.mytictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TicTacToeGame extends AppCompatActivity {

    private Board board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe_game);
        board = findViewById(R.id.board2);
        Button resetButton = findViewById(R.id.reset_game);
        Button homeButton = findViewById(R.id.home_button);
        TextView playerTurn = findViewById(R.id.player_turn);
        String[] playerNames = getIntent().getStringArrayExtra("PLAYER NAMES");
        board.setUpGame(resetButton, homeButton, playerTurn, playerNames);

        if (playerNames != null) {
            playerTurn.setText((playerNames[0] + " Is Starting"));
        }

    }

    public void resetButtonClick(View view) {
        board.resetGame();
        board.invalidate();

    }

    public void homeButton(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
