package com.example.mcqbasedquizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;



class Quizes{
    String[] questions;
    String[] answers;
    String [][]options;

    int[] score;
    int totalq;

    Quizes(){
        score=new int[]{
                0,0,0,0,0,0,0,0,0,0,0
        };
        questions = new String[] {
                " ",
                "When was Osama Bin Laden born?",
                "What is the capital of France?",
                "What is 2 + 2?",
                "Who wrote 'Hamlet'?",
                "Which planet is known as the Red Planet?",
                "What is the square root of 64?",
                "Who painted the Mona Lisa?",
                "What is the chemical symbol for water?",
                "Which animal is known as the King of the Jungle?",
                "What is the largest ocean on Earth?",

        };

        options = new String[][] {
                {" "," "," "," "},
                {"1980", "1985", "1990", "1995"},
                {"Berlin", "Madrid", "Paris", "Rome"},
                {"1", "2", "3", "4"},
                {"Charles Dickens", "William Shakespeare", "Jane Austen", "Mark Twain"},
                {"Earth", "Mars", "Jupiter", "Saturn"},
                {"6", "7", "8", "9"},
                {"Van Gogh", "Leonardo da Vinci", "Pablo Picasso", "Claude Monet"},
                {"H2", "H2O", "O2", "CO2"},
                {"Tiger", "Elephant", "Lion", "Cheetah"},
                {"Atlantic Ocean", "Indian Ocean", "Arctic Ocean", "Pacific Ocean"},

        };


        answers = new String[] {
                " ",
                "1990",
                "Paris",
                "4",
                "William Shakespeare",
                "Mars",
                "8",
                "Leonardo da Vinci",
                "H2O",
                "Lion",
                "Pacific Ocean",

        };
        totalq=10;

    }

    int getsum() {
        int s=0;
        for (int i = 0; i < questions.length-1; i++) {
            s=s+score[i];
        }
        return s;
    }





}

public class QuizActivity extends AppCompatActivity {

    TextView current_quesion;
    TextView total_questions;
    TextView display_question;
    RadioGroup radio_grp_identifier;
    Button changer;
    RadioButton option_1,option_2,option_3,option_4;
    ImageView previd;
    TextView previd2;


    void init(){
        current_quesion=findViewById(R.id.qindex);
        current_quesion.setText("1");
        total_questions=findViewById(R.id.total_score);
        display_question=findViewById(R.id.tvquestion);
        radio_grp_identifier =findViewById(R.id.rgid);
        changer=findViewById(R.id.changer);
        option_1=findViewById(R.id.option1);
        option_2=findViewById(R.id.option2);
        option_3=findViewById(R.id.option3);
        option_4=findViewById(R.id.option4);
        previd=findViewById(R.id.prev_pic);
        previd2=findViewById(R.id.prev_id2);



    }
    public void question_displayer(int q, TextView display_question, Quizes quizes, RadioButton option_1, RadioButton option_2, RadioButton option_3, RadioButton option_4,RadioGroup r  ){
        r.clearCheck();
        int number=q;
        display_question.setText(quizes.questions[number]);
        option_1.setText(quizes.options[number][0]);
        option_2.setText(quizes.options[number][1]);
        option_3.setText(quizes.options[number][2]);
        option_4.setText(quizes.options[number][3]);


    }

    public void question_checker(Quizes quizes, RadioGroup raid, int qno){
        int selected_option=raid.getCheckedRadioButtonId();
        if(selected_option==-1){
            return;
        }
        RadioButton selected_option_2=findViewById(selected_option);
        String selected_option_data=selected_option_2.getText().toString().trim();

        if(quizes.answers[qno].trim().equals(selected_option_data)){
            quizes.score[qno]=1;
//            Toast.makeText(this, "Correct !", Toast.LENGTH_SHORT).show();
        }
//        else{
//            Toast.makeText(this, "not correct", Toast.LENGTH_SHORT).show();
//            Toast.makeText(this, quizes.answers[qno], Toast.LENGTH_SHORT).show();
//        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });





        init();
        Quizes quizes=new Quizes();

        int []question_number2 = new int[1];
        question_number2[0]=1;




        question_displayer(question_number2[0],display_question,quizes,option_1,option_2,option_3,option_4, radio_grp_identifier);
        String garbage = question_number2[0] + "";
        current_quesion.setText(garbage);

        changer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question_checker(quizes, radio_grp_identifier, question_number2[0]);

                if (question_number2[0] < quizes.totalq) { // Prevent going out of bounds
                    question_number2[0]++;
                    question_displayer(question_number2[0], display_question, quizes, option_1, option_2, option_3, option_4, radio_grp_identifier);
                    current_quesion.setText(String.valueOf(question_number2[0]));

                    if (question_number2[0] == quizes.totalq) {
                        changer.setText(R.string.finish); // Update button text on the last question
                    }
                } else {
                    // Logic for finishing the quiz
                    Toast.makeText(QuizActivity.this, "Quiz Finished!", Toast.LENGTH_SHORT).show();
                    int finalScore = quizes.getsum();
                    Intent i=getIntent();
                    String user_name=i.getStringExtra("user_input").toString();
                    Intent j=new Intent(QuizActivity.this, ScoreActivity.class);
                    j.putExtra("score",finalScore);
                    j.putExtra("name",user_name);
                    startActivity(j);
                    finish();
                }
            }
        });


        previd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(question_number2[0]>=1){
                    --question_number2[0];
                    String garbage2=question_number2[0]+"";
                    current_quesion.setText(garbage2);
                    question_displayer(question_number2[0],display_question,quizes,option_1,option_2,option_3,option_4, radio_grp_identifier);
                    changer.setText(R.string.nextt);
                }
            }
        });

        previd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(question_number2[0]>=1){
                    --question_number2[0];
                    String garbage2=question_number2[0]+"";
                    current_quesion.setText(garbage2);
                    question_displayer(question_number2[0],display_question,quizes,option_1,option_2,option_3,option_4, radio_grp_identifier);
                    changer.setText(R.string.nextt);
                }
            }
        });











    }
}