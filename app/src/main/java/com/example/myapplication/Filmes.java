package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class Filmes extends AppCompatActivity {

    Button button8;
    CheckBox derepente30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filmes);

        button8 = findViewById(R.id.button8);

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(Filmes.this, MainActivity.class);
                startActivity(it);
            }
        });

        derepente30 = findViewById(R.id.derepente30);

        derepente30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.netflix.com/br/title/60034573"));
                startActivity(it);
            }
        });

    }
}