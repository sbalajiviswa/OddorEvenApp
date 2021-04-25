package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.state.State;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button button;
    Button NextOk;
    Button NextGame;
    EditText editText;
    TextView CompRun;
    TextView CSCoreText;
    TextView ScorePlayer;
    TextView TurnText;
    ConstraintLayout TossLayout;
    ConstraintLayout GameLayout;
    Button oddButton;
    Button evenButton;
    Boolean oddChoice;
    Boolean isitOdd;
    EditText PlayerToss;
    TextView ComputerToss;
    Button Tossok;
    ConstraintLayout CoinLayout;
    Boolean PlayerBat;
    ConstraintLayout ChoiceLayout;
    Boolean GameState = true;
    TextView GameOverText;
    ConstraintLayout GameOverLay;
    Boolean PlayerBatFirst;
    int player_score = 0;
    int Computer_score = 0;
    public int generate(View view){
        Random rand = new Random();
        int num = rand.nextInt(7);
        return num;
    }
    public Boolean CompTurn(Boolean notout,View view){

        ScorePlayer.setText(Integer.toString(player_score));
        CSCoreText.setText(Integer.toString(Computer_score));
        if (notout){
            int num = generate(view);
            CompRun.setText(Integer.toString(num));
            Log.i("Computer:",Integer.toString(num));
            if(num != Integer.valueOf(editText.getText().toString())){
                Computer_score = Computer_score+num;
                CSCoreText.setText(Integer.toString(Computer_score));

            }else {
                notout = false;
                Toast.makeText(this, "Howzat", Toast.LENGTH_SHORT).show();
                if((isitOdd==true&&oddChoice==true)||(isitOdd==false&&oddChoice==false)){
                    if(PlayerBatFirst){
                        GameState = false;
                        button.setVisibility(View.INVISIBLE);
                        NextOk.setVisibility(View.VISIBLE);

                        if(player_score > Computer_score){
                            GameOverText.setText("You won");
                        }else{
                            GameOverText.setText("You Lost");
                        }
                    }else{
                        PlayerBat = true;
                        TurnText.setText("User's Turn");
                    }

                }else{
                    PlayerBat = true;
                    TurnText.setText("User's Turn");                }
            }
        }
        return notout;

    }
    public  void  Howzat(View view){
        GameLayout.setVisibility(View.INVISIBLE);
        GameOverLay.setVisibility(View.VISIBLE);

    }
    public Boolean UserTurn(Boolean notout,View view){

        ScorePlayer.setText(Integer.toString(player_score));
        CSCoreText.setText(Integer.toString(Computer_score));
        if (notout){
            int num = generate(view);
            CompRun.setText(Integer.toString(num));
            Log.i("Computer:",Integer.toString(num));
            if(num != Integer.valueOf(editText.getText().toString())){
                player_score = player_score+Integer.valueOf(editText.getText().toString());
                ScorePlayer.setText(Integer.toString(player_score));

            }else{
                notout = false;
                Toast.makeText(this, "Howzat", Toast.LENGTH_SHORT).show();

                if((isitOdd==true&&oddChoice==true)||(isitOdd==false&&oddChoice==false)){
                    if(PlayerBatFirst) {
                        PlayerBat = false;
                    }else {
                        GameState = false;
                        button.setVisibility(View.INVISIBLE);
                        NextOk.setVisibility(View.VISIBLE);

                        if(player_score > Computer_score){
                            GameOverText.setText("You won");
                        }else{
                            GameOverText.setText("You Lost");
                        }

                    }
                }else{
                    GameState = false;
                    button.setVisibility(View.INVISIBLE);
                    NextOk.setVisibility(View.VISIBLE);

                    if(player_score > Computer_score){
                        GameOverText.setText("You won");
                    }else{
                        GameOverText.setText("You Lost");
                    }
                }

            }
        }
        return notout;

    }
    public void cliked(View view){
        if(Integer.valueOf(editText.getText().toString())<7){
            Log.i("Info:",editText.getText().toString());


                if (PlayerBat) {
                    if(PlayerBatFirst) {
                        TurnText.setText("User's Turn");
                        Boolean notout = UserTurn(true, view);
                    }else{
                        if(player_score<=Computer_score){
                            TurnText.setText("User's Turn");
                            Boolean notout = UserTurn(true, view);
                        }else{
                            GameState = false;
                            button.setVisibility(View.INVISIBLE);
                            NextOk.setVisibility(View.VISIBLE);

                            if(player_score > Computer_score){
                                GameOverText.setText("You won");
                            }else{
                                GameOverText.setText("You Lost");
                            }
                        }
                    }
                } else {
                    if(PlayerBatFirst == false) {
                        TurnText.setText("Computer's Turn");
                        Boolean notout = CompTurn(true, view);
                    }else {
                        if (Computer_score <= player_score){
                            TurnText.setText("Computer's Turn");
                            Boolean notout = CompTurn(true, view);
                        }else{
                            GameState = false;
                            button.setVisibility(View.INVISIBLE);
                            NextOk.setVisibility(View.VISIBLE);

                            if(player_score > Computer_score){
                                GameOverText.setText("You won");
                            }else{
                                GameOverText.setText("You Lost");
                            }
                        }
                    }
                }

        }else {
            Log.i("Info:","Incorrect Entry");
            Toast.makeText(this, "Enter a number from 0 to 6", Toast.LENGTH_SHORT).show();
        }
    }
    public void Odd(View view){
        oddChoice = true;
        TossLayout.setVisibility(View.INVISIBLE);
        CoinLayout.setVisibility(View.VISIBLE);

    }
    public void even(View view){
        oddChoice = false;
        TossLayout.setVisibility(View.INVISIBLE);
        CoinLayout.setVisibility(View.VISIBLE);

    }
    public void TossClicked(View view){
        if(GameState) {
            if (Integer.valueOf(PlayerToss.getText().toString()) < 7) {
                int num = generate(view);
                ComputerToss.setText(Integer.toString(num));
                if ((Integer.valueOf(PlayerToss.getText().toString()) + num) % 2 != 0) {
                    isitOdd = true;
                    Toast.makeText(this, "Odd", Toast.LENGTH_SHORT).show();
                } else {
                    isitOdd = false;
                    Toast.makeText(this, "Even", Toast.LENGTH_SHORT).show();
                }
                if ((isitOdd == true && oddChoice == true) || (isitOdd == false && oddChoice == false)) {
                    Toast.makeText(this, "You won the toss", Toast.LENGTH_SHORT).show();
                    NextGame.setVisibility(View.VISIBLE);
                    Tossok.setVisibility(View.INVISIBLE);
                    PlayerBat = true;
                } else {
                    PlayerBatFirst = false;
                    NextGame.setVisibility(View.VISIBLE);
                    Tossok.setVisibility(View.INVISIBLE);
                    PlayerBat = false;
                    TurnText.setText("Computer's Turn");

                }


            } else {
                Toast.makeText(this, "Enter a number from 0 to 6", Toast.LENGTH_SHORT).show();
            }
        }


    }
    public void NextGame(View view){
        if((isitOdd == true && oddChoice == true) || (isitOdd == false && oddChoice == false)){
            CoinLayout.animate().translationX(800);
            ChoiceLayout.setVisibility(View.VISIBLE);
        }else {
            CoinLayout.animate().translationX(800);
            GameLayout.setVisibility(View.VISIBLE);
        }
    }
    public void bat(View view){
        PlayerBat = true;
        PlayerBatFirst = true;
        ChoiceLayout.animate().translationX(-800);
        GameLayout.setVisibility(View.VISIBLE);
        TurnText.setText("User's Turn");
    }
    public void bowl(View view){
        PlayerBat = false;
        PlayerBatFirst = false;
        ChoiceLayout.animate().translationX(-800);
        GameLayout.setVisibility(View.VISIBLE);
        TurnText.setText("Computer's Turn");
    }
//    public void TurnOne(View view) {
//        if (PlayerBatFirst) {
//            if (PlayerBat) {
//                TurnText.setText("User's Turn");
//                Boolean notout = UserTurn(true, view);
//
//
//            } else {
//                TurnText.setText("Computer's Turn");
//                Boolean notout = CompTurn(true, view);
//            }
//        }
//    }
    public void PlayAgain(View view){

        TossLayout.setVisibility(View.VISIBLE);
        GameOverLay.setVisibility(View.INVISIBLE);
        Tossok.setVisibility(View.VISIBLE);
        CoinLayout.animate().translationX(-10);
        ChoiceLayout.animate().translationX(-10);
        ChoiceLayout.setVisibility(View.INVISIBLE);
        CoinLayout.setVisibility(View.INVISIBLE);
        ComputerToss.setText("It's Time for Toss");
        player_score = 0;
        Computer_score = 0;
        CSCoreText.setText("0");
        ScorePlayer.setText("0");
        CompRun.setText("0");
        GameState = true;
        button.setVisibility(View.VISIBLE);
        NextOk.setVisibility(View.INVISIBLE);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ScorePlayer = (TextView) findViewById(R.id.ScoreText);
        CompRun = (TextView) findViewById(R.id.CompRun);
        editText = (EditText) findViewById(R.id.editText);
        CSCoreText = (TextView) findViewById(R.id.CScoreText);
        TurnText = (TextView)findViewById(R.id.TurnText);

        button = (Button) findViewById(R.id.button);
        NextOk = (Button) findViewById(R.id.Next);
        NextGame = (Button) findViewById(R.id.NextGame);
        TossLayout = (ConstraintLayout)findViewById(R.id.TossLayout);
        GameLayout = (ConstraintLayout)findViewById(R.id.GameLayout);
        oddButton = (Button)findViewById(R.id.Odd);
        evenButton = (Button)findViewById(R.id.Even);
        PlayerToss = (EditText)findViewById(R.id.TossPlayer);
        ComputerToss = (TextView)findViewById(R.id.TossComputer);
        Tossok = (Button)findViewById(R.id.Tossbutton);
        CoinLayout=(ConstraintLayout)findViewById(R.id.CoinLayout);
        ChoiceLayout = (ConstraintLayout)findViewById(R.id.ChoiceLayout);
        GameOverText = (TextView)findViewById(R.id.GameOverText);
        GameOverLay = (ConstraintLayout)findViewById(R.id.GameOverLay);



        CoinLayout.setVisibility(View.INVISIBLE);
        TossLayout.setVisibility(View.VISIBLE);
        GameLayout.setVisibility(View.INVISIBLE);
        ChoiceLayout.setVisibility(View.INVISIBLE);
        GameOverLay.setVisibility(View.INVISIBLE);
        NextOk.setVisibility(View.INVISIBLE);
        NextGame.setVisibility(View.INVISIBLE);

    }
}
