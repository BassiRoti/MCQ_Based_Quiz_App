package com.example.mcqbasedquizapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

    int score;
    int question_no;

    Quizes(){
        score=0;
        questions = new String[] {
                "When was Osama born?",
                "What is the capital of France?",
                "What is 2 + 2?",
                "Who wrote 'Hamlet'?",
                "Which planet is known as the Red Planet?",
                "What is the square root of 64?",
                "Who painted the Mona Lisa?",
                "What is the chemical symbol for water?",
                "Which animal is known as the King of the Jungle?",
                "What is the largest ocean on Earth?"
        };

        options = new String[][] {
                {"1980", "1985", "1990", "1995"},
                {"Berlin", "Madrid", "Paris", "Rome"},
                {"1", "2", "3", "4"},
                {"Charles Dickens", "William Shakespeare", "Jane Austen", "Mark Twain"},
                {"Earth", "Mars", "Jupiter", "Saturn"},
                {"6", "7", "8", "9"},
                {"Van Gogh", "Leonardo da Vinci", "Pablo Picasso", "Claude Monet"},
                {"H2", "H2O", "O2", "CO2"},
                {"Tiger", "Elephant", "Lion", "Cheetah"},
                {"Atlantic Ocean", "Indian Ocean", "Arctic Ocean", "Pacific Ocean"}
        };


        answers = new String[] {
                "1990",
                "Paris",
                "4",
                "William Shakespeare",
                "Mars",
                "8",
                "Leonardo da Vinci",
                "H2O",
                "Lion",
                "Pacific Ocean"
        };

    }

    void setscore(int x){
        score=score+x;
    }

    int getscore(){
        return score;
    }


}

public class QuizActivity extends AppCompatActivity {

    TextView current_quesion;
    TextView total_questions;
    TextView display_question;
    RadioGroup radio_grp_identifier;
    Button changer;
    RadioButton option_1,option_2,option_3,option_4;


    void init(){
        current_quesion=findViewById(R.id.qindex);
        total_questions=findViewById(R.id.total_score);
        display_question=findViewById(R.id.tvquestion);
        radio_grp_identifier =findViewById(R.id.rgid);
        changer=findViewById(R.id.changer);
        option_1=findViewById(R.id.option1);
        option_2=findViewById(R.id.option2);
        option_3=findViewById(R.id.option3);
        option_4=findViewById(R.id.option4);



    }
    public void question_displayer(int q, TextView display_question, Quizes quizes, RadioButton option_1, RadioButton option_2, RadioButton option_3, RadioButton option_4  ){
        int number=q;
        display_question.setText(quizes.questions[number]);
        option_1.setText(quizes.options[number][0]);
        option_2.setText(quizes.options[number][1]);
        option_3.setText(quizes.options[number][2]);
        option_4.setText(quizes.options[number][3]);


    }

    public void question_checker(Quizes quizes, RadioGroup raid, int qno){
        int selected_option=raid.getCheckedRadioButtonId();
        RadioButton selected_option_2=findViewById(selected_option);
        String selected_option_data=selected_option_2.getText().toString().trim();
        if(quizes.answers[qno].equals(selected_option_data)){
            ++quizes.score;
            Toast.makeText(this, "Correct !", Toast.LENGTH_SHORT).show();
        }
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

        String question_number=current_quesion.getText().toString().trim();
        int []question_number2 = new int[1];
        question_number2[0]=Integer.parseInt(question_number);


        int no=Integer.parseInt(question_number);


        question_displayer(question_number2[0],display_question,quizes,option_1,option_2,option_3,option_4);
        changer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question_checker(quizes, radio_grp_identifier,no);
                ++question_number2[0];
                question_displayer(question_number2[0],display_question,quizes,option_1,option_2,option_3,option_4);

            }

        });











    }
}