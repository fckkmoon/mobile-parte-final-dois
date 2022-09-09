package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Afazeres extends AppCompatActivity {

    Button botaovoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afazeres);

        botaovoltar = findViewById(R.id.botaovoltar);

        botaovoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(Afazeres.this, MainActivity.class);
                startActivity(it);
            }
        });
    }
}