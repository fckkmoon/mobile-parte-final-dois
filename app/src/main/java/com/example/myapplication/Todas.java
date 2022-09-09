package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Todas extends AppCompatActivity {

    Button botaovoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todas);

        botaovoltar = findViewById(R.id.botaovoltar);

        botaovoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(Todas.this, MainActivity.class);
                startActivity(it);
            }
        });
    }
}
