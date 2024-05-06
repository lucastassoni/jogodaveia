package com.example.jogo_da_veia;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.jogo_da_veia.R;

import java.util.Random;

public class JogoDaVeia extends AppCompatActivity implements View.OnClickListener{

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

        Button botaoReset = findViewById(R.id.botao_reset);
        botaoReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reiniciarJogo();
            }
        });
    }

    @Override
    public void onClick(View v) {
        Button botaoClicado = (Button) v;
        if (!botaoClicado.getText().toString().isEmpty()) {
            return;
        }


        if (turnoJogador1) {
            botaoClicado.setText("O");
        } else {
            botaoClicado.setText("X");
        }

        contagemRodada++;


        if (verificarVitoria()) {
            finalizarJogo();
        } else if (contagemRodada == 9) {
            empate();
        } else {
            turnoJogador1 = !turnoJogador1;
            if (!turnoJogador1) {
                jogadaMaquina();
            }
        }
    }

    private void jogadaMaquina() {
        Random rand = new Random();
        int linha, coluna;

        do {
            linha = rand.nextInt(3);
            coluna = rand.nextInt(3);
        } while (!botoes[linha][coluna].getText().toString().isEmpty());

        botoes[linha][coluna].setText("X");
        contagemRodada++;


        if (verificarVitoria()) {
            finalizarJogo();
        } else if (contagemRodada == 9) {
            empate();
        } else {
            turnoJogador1 = true;
        }
    }

    private boolean verificarVitoria() {
        String[][] campo = new String[3][3];


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                campo[i][j] = botoes[i][j].getText().toString();
            }
        }


        for (int i = 0; i < 3; i++) {
            if (campo[i][0].equals(campo[i][1]) && campo[i][0].equals(campo[i][2]) && !campo[i][0].isEmpty()) {
                return true;
            }
            if (campo[0][i].equals(campo[1][i]) && campo[0][i].equals(campo[2][i]) && !campo[0][i].isEmpty()) {
                return true;
            }
        }
        if (campo[0][0].equals(campo[1][1]) && campo[0][0].equals(campo[2][2]) && !campo[0][0].isEmpty()) {
            return true;
        }
        if (campo[0][2].equals(campo[1][1]) && campo[0][2].equals(campo[2][0]) && !campo[0][2].isEmpty()) {
            return true;
        }

        return false;
    }

    private void finalizarJogo() {
        if (turnoJogador1) {
            textoResultado.setText("Você venceu!");
        } else {
            textoResultado.setText("A máquina venceu!");
        }
    }

    private void empate() {
        textoResultado.setText("Empate!");

    }



    private void reiniciarJogo() {

        contagemRodada = 0;
        turnoJogador1 = true;
        textoResultado.setText("");
        for (Button[] linhaBotoes : botoes) {
            for (Button botao : linhaBotoes) {
                botao.setText("");
                botao.setEnabled(true);
            }
        }
    }
}
