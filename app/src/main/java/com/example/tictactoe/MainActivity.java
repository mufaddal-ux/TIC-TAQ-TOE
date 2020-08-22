package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons=new Button[3][3];
    private boolean player1Turn=true;
    private int roundCount=0;
    private int player1Points=0,player2Points=0;
    private TextView textViewPlayer1,textViewPlayer2;
    private Button resetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewPlayer1=(TextView) findViewById(R.id.text_view_player1);
        textViewPlayer2=(TextView) findViewById(R.id.text_view_player2);

        resetButton=(Button) findViewById(R.id.button_reset);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });

        buttons[0][0]=(Button)findViewById(R.id.button_00);
        buttons[0][1]=(Button)findViewById(R.id.button_01);
        buttons[0][2]=(Button)findViewById(R.id.button_02);
        buttons[1][0]=(Button)findViewById(R.id.button_10);
        buttons[1][1]=(Button)findViewById(R.id.button_11);
        buttons[1][2]=(Button)findViewById(R.id.button_12);
        buttons[2][0]=(Button)findViewById(R.id.button_20);
        buttons[2][1]=(Button)findViewById(R.id.button_21);
        buttons[2][2]=(Button)findViewById(R.id.button_22);

        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){

                buttons[i][j].setOnClickListener(this);
            }
        }


    }

    @Override
    public void onClick(View v) {
        Button b=(Button) v;

        if(!b.getText().toString().equals("")){


            return;
        }
        if(player1Turn) b.setText("X");
        else{
            b.setText("O");
        }
        roundCount++;
        if(checkForWin()){
            if(player1Turn){
                player1Wins();
            }
            else{
                player2Wins();
            }
        }
        else
            if(roundCount==9){
                draw();
            }
        else
            {
                player1Turn=!player1Turn;
            }






    }

    private void draw() {
        Toast.makeText(this,"Game Ties",Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void player2Wins() {
        player2Points+=1;
        Toast.makeText(this,"Player 2 Wins",Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void player1Wins() {
        player1Points+=1;
        Toast.makeText(this,"Player 1 Wins",Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();

    }
    private void updatePointsText(){
        textViewPlayer1.setText("Player 1 :"+player1Points);
        textViewPlayer2.setText("Player 2 :"+player2Points);
    }

    private  void resetBoard(){

        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                buttons[i][j].setText("");
            }
        }
        roundCount=0;
        player1Turn=true;

    }
    private void resetGame(){
        player1Points=0;
        player2Points=0;
        updatePointsText();
        resetBoard();
    }







    private boolean checkForWin(){
        String[][] field=new String[3][3];
        for(int i = 0;i<3; i++){
            for(int j=0;j<3;j++){
//                Store value of X and O
                field[i][j]=buttons[i][j].getText().toString();
            }
        }
        for(int i=0;i<3;i++){
            if(field[i][0].equals(field[i][1])  &&  field[i][0].equals(field[i][2])   && !field[i][0].equals("")){
                return true;
            }
        }
        for(int i=0;i<3;i++){
            if(field[0][i].equals(field[1][i])  &&  field[0][i].equals(field[2][i])   && !field[0][i].equals("")){
                return true;
            }
        }

        if(field[0][0].equals(field[1][1])  &&   field[0][0].equals(field[2][2]) &&  !field[0][0].equals("")){
            return true;
        }
        if(field[0][2].equals(field[1][1])  &&   field[0][2].equals(field[2][0]) &&  !field[0][2].equals("")){
            return true;
        }
        return false;
    }
}