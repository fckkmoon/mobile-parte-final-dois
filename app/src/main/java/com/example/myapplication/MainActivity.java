package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    Button botaoafazeres, botaocompletos, botaotodas, botaocompras, botaofilmes, botaoferias, botaomapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_main);

        botaoafazeres = findViewById(R.id.botaoafazeres);

        botaoafazeres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent it = new Intent(MainActivity.this, Afazeres.class);
                    startActivity(it);
                }
            });

        botaocompletos = findViewById(R.id.botaocompletos);

        botaocompletos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent it = new Intent(MainActivity.this, Completos.class);
                    startActivity(it);
                }
            });

        botaotodas = findViewById(R.id.botaotodas);

        botaotodas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent it = new Intent(MainActivity.this, Todas.class);
                    startActivity(it);
                }
            });

        botaocompras = findViewById(R.id.botaocompras);

        botaocompras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent it2 = new Intent(MainActivity.this, Compras.class);
                    startActivity(it2);
               }
            });

        botaofilmes = findViewById(R.id.botaofilmes);

        botaofilmes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent it = new Intent(MainActivity.this, Filmes.class);
                    startActivity(it);
               }
            });

        botaoferias = findViewById(R.id.botaoferias);

        botaoferias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent it = new Intent(MainActivity.this, Ferias.class);
                    startActivity(it);
               }
            });

        botaomapa = findViewById(R.id.botaomapa);

        botaomapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, Mapa.class);
                startActivity(it);
               }
            });
        }
    }
