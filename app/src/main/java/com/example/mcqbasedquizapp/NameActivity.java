package com.example.mcqbasedquizapp;

import static android.widget.Toast.LENGTH_LONG;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class NameActivity extends AppCompatActivity {
    EditText UserData;
    Button btnactivitystart;
    String extracteddata;

    private void init() {
        UserData=findViewById(R.id.userdata);
        btnactivitystart=findViewById(R.id.btnactivitystart);
        extracteddata="";
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_name);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();
        btnactivitystart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                extracteddata=UserData.getText().toString().trim();
                if(extracteddata.isEmpty()){
                    Toast.makeText(NameActivity.this, "Kindly Enter Your Name", Toast.LENGTH_SHORT).show();
                }
                else if(extracteddata.length()<=4){
                    Toast.makeText(NameActivity.this, "", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent i = new Intent(NameActivity.this, QuizActivity.class);
                    i.putExtra("user_input", extracteddata);
                    startActivity(i);
                    finish();
                }
            }
        });
    }
}