package com.example.green.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //basic buuton and textview

    Button startbutton,button,button1,button2,button3;
    TextView resulttextview,timertextView;
    TextView pointstextView;
    TextView sumtextview;
    ArrayList<Integer>answers=new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score=0;
    int numberOfQuestions=0;
    Button playagain;
    RelativeLayout gameRelative;

    public void PlayAgain(View view){

        score=0;
        numberOfQuestions=0;

        timertextView.setText("30s");
        pointstextView.setText("0/0");
        resulttextview.setText("");
        playagain.setVisibility(View.INVISIBLE);

        generateQuestions();

        new CountDownTimer(30100,1000){


            @Override
            public void onTick(long millisUntilFinished) {

                timertextView.setText(String.valueOf(millisUntilFinished / 1000) + "s");

            }

            @Override
            public void onFinish() {

                playagain.setVisibility(View.VISIBLE);
                timertextView.setText("0s");
                resulttextview.setText("your Score" + Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
            }
        }.start();

    }


    ///func
    public void generateQuestions(){

        Random rand=new Random();

        int a=rand.nextInt(100);
        int b=rand.nextInt(100);

        sumtextview.setText(Integer.toString(a) +" + "+ Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);   ///the correct answer going to array

        answers.clear();

        int inCorrectAnswer;

        for(int i=0; i<4; i++){

            if(i==locationOfCorrectAnswer){

                answers.add(a + b);

            }else{

                inCorrectAnswer=rand.nextInt(200);

                while(inCorrectAnswer==a + b){

                    answers.add(rand.nextInt(200));

                }

                answers.add(inCorrectAnswer);

            }

        }
        ///change the answers button
        button.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }
     //func
    public void ChooseAnswer(View view){


        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){

            Log.i("result","correct");
            score++;
            resulttextview.setText("Correct!");

        }else {

            Log.i("result","incorrect");
            resulttextview.setText("Wrong!");

        }

        numberOfQuestions++;
        pointstextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
        generateQuestions();

    }

    public void start(View view){

    startbutton.setVisibility(View.INVISIBLE);
    gameRelative.setVisibility(RelativeLayout.VISIBLE);

    PlayAgain(findViewById(R.id.playagainbutton));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startbutton = (Button) findViewById(R.id.StartButton);
        sumtextview = (TextView) findViewById(R.id.sumTextView);
        button = (Button) findViewById(R.id.button3);
        button1 = (Button) findViewById(R.id.button4);
        button2 = (Button) findViewById(R.id.button5);
        button3 = (Button) findViewById(R.id.button6);
        resulttextview = (TextView) findViewById(R.id.ResultTextView);
        pointstextView = (TextView) findViewById(R.id.pointsTextView);
        timertextView=(TextView) findViewById(R.id.timerTextView);
        playagain=(Button)findViewById(R.id.playagainbutton);
        gameRelative=(RelativeLayout)findViewById(R.id.gameRelative);

    }
}
