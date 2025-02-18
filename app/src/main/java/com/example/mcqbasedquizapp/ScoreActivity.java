package com.example.mcqbasedquizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ScoreActivity extends AppCompatActivity {
    TextView userscore;
    TextView n;
    Button share;
    ActivityResultLauncher<Intent> sharer;

    private void init(){
        userscore=findViewById(R.id.userscore);
        n=findViewById(R.id.usernamescore);
        share=findViewById(R.id.share);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_score);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();

        sharer=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),(result)->{
                if(result.getResultCode()==RESULT_OK){
                    Toast.makeText(this, "shared successfully", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this, "Share action cancelled", Toast.LENGTH_SHORT).show();
                }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                StringBuilder s=new StringBuilder();
                s.append("Hey! i just played the MCQ Based Quiz App made by Abbas. Here's my score: ");
                s.append(userscore.getText());
                i.putExtra(Intent.EXTRA_TEXT,s.toString().trim());
                sharer.launch(i);
            }
        });

        Intent i=getIntent();
        int score=i.getIntExtra("score",0);
        String name=i.getStringExtra("name");
        userscore.setText(String.valueOf(score));
        n.setText(name);


    }
}