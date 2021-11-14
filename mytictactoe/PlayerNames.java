package com.example.mytictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class PlayerNames extends AppCompatActivity {

    private EditText player1;
    private EditText player2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_names);
        player1 = findViewById(R.id.playerX);
        player2 = findViewById(R.id.playerO);

    }

    public void startButton(View view){
        String playerX = player1.getText().toString();
        String playerO = player2.getText().toString();
        Intent intent = new Intent(this, TicTacToeGame.class);
        intent.putExtra("PLAYER NAMES",new String[] {playerX, playerO});
        startActivity(intent);

    }
}