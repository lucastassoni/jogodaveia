package com.example.jogo_da_veia;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.jogo_da_veia.R;

import java.util.Random;

public class JogoDaVeia extends AppCompatActivity {

    private Button[][] botoes = new Button[3][3];
    private boolean turnoJogador1 = true;
    private int contagemRodada;
    private TextView textoResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo_velha);

        textoResultado = findViewById(R.id.texto_resultado);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                botoes[i][j] = findViewById(resID);
                if(botoes[i][j] == null) {
                    Log.e("JogoDaVelha", "Botão não encontrado para ID: " + buttonID);
                }
                botoes[i][j].setOnClickListener(this);
            }
        }

}

}
