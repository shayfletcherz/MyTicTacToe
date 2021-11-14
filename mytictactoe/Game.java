package com.example.mytictactoe;

import android.widget.Button;
import android.widget.TextView;

public class Game {
    private int [][] gameBoard;
    private int player = 1;
    private Button resetButton;
    private Button homeButton;
    private TextView playerTurn;
    private String[] playerNames = {"Player X", "player O"};
    private int [] winType = {-1, -1, -1};


    Game(){
        gameBoard = new  int[3][3];
        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                gameBoard[i][j] = 0;
            }
        }

    }

    public boolean updateGameBoard(int row, int col){
        if (gameBoard[row-1][col-1] == 0){
            gameBoard[row-1][col-1] = player;

            if (player == 1){
                playerTurn.setText((playerNames[1]) + " O Turn");
            }
            else {
                playerTurn.setText((playerNames[0]) + " X Turn");
            }
            return true;
        }
        else{
            return false;
        }
    }

    public int[] getWinType() {
        return winType;
    }

    public int[][] getGameBoard() {
        return gameBoard;
    }

    public void setPlayer(int player){
        this.player = player;
    }

    public int getPlayer() {
        return player;
    }

    public void resetGame(){
        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                gameBoard[i][j] = 0;
            }
        }

        player = 1;
        playerTurn.setText((playerNames[0] + " Is Starting"));
    }

    public void setResetButton(Button resetButton){
        this.resetButton = resetButton;
    }

    public void setHomeButton(Button homeButton) {
        this.homeButton = homeButton;
    }

    public void setPlayerTurn(TextView playerTurn) {
        this.playerTurn = playerTurn;
    }

    public void setPlayerNames(String[] playerNames) {
        this.playerNames = playerNames;
    }

    public boolean winnerCheck(){
        boolean winner = false;
        int boardFilled = 0;


        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                if (gameBoard[i][j] != 0){
                    boardFilled += 1;
                }
            }
        }

        //Horizontal check
        for (int r = 0; r<3; r++){
            //player 1 win check
            if (gameBoard[r][0] == gameBoard[r][1] && gameBoard[r][0] == gameBoard[r][2] && gameBoard[r][0] != 0){

                winType = new int[]{r, 0, 1};
                winner = true;
                break;
            }
        }

        //Vertical check
        for (int c=0; c<3; c++){
            //player 1 win check
            if (gameBoard[0][c] == gameBoard[1][c] && gameBoard[2][c] == gameBoard[0][c] && gameBoard[0][c] != 0){

                winType = new int[]{0, c, 2};
                winner = true;
                break;
            }
        }

        //Negative diagonal check
        if (gameBoard[0][0] == gameBoard[1][1] && gameBoard[2][2] == gameBoard[0][0] && gameBoard[0][0] != 0){

            winType = new int[]{0, 2, 3};
            winner = true;
        }

        //Positive diagonal check
        if (gameBoard[2][0] == gameBoard[1][1] && gameBoard[0][2] == gameBoard[2][0] && gameBoard[2][0] != 0){

            winType = new int[]{2, 2, 4};
            winner = true;
        }

        if (winner){
            playerTurn.setText((playerNames[player-1] + " WON!"));
            return true;
        }

        else if (boardFilled == 9){
            playerTurn.setText("TIE!");
            return false;
        }
        else {
            return false;
        }
    }

}
