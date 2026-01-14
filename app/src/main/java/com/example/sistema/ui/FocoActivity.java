package com.example.sistema.ui;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sistema.R;
import com.example.sistema.data.UsuarioRepository;
import com.example.sistema.model.Usuario;

public class FocoActivity extends AppCompatActivity {

    private int diaAtual = 1;

    private static final long TEMPO_FOCO = 25 * 60 * 1000; // 25 minutos

    private CountDownTimer timer;
    private boolean rodando = false;

    private TextView txtTempo;
    private Button btnIniciar, btnCancelar;

    private Usuario usuario;
    private UsuarioRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foco);

        txtTempo = findViewById(R.id.txtTempo);
        btnIniciar = findViewById(R.id.btnIniciar);
        btnCancelar = findViewById(R.id.btnCancelar);

        repository = new UsuarioRepository(this);
        usuario = repository.carregarUsuario();

        atualizarTempo(TEMPO_FOCO);

        btnIniciar.setOnClickListener(v -> iniciarFoco());
        btnCancelar.setOnClickListener(v -> cancelarFoco());
    }

    private void iniciarFoco() {
        if (rodando) return;

        rodando = true;
        btnIniciar.setEnabled(false);
        btnCancelar.setEnabled(true);

        timer = new CountDownTimer(TEMPO_FOCO, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                atualizarTempo(millisUntilFinished);
            }

            @Override
            public void onFinish() {
                rodando = false;
                btnIniciar.setEnabled(true);
                btnCancelar.setEnabled(false);

                usuario.ganharXp(25); // recompensa
                repository.salvarUsuario(usuario);

                Toast.makeText(FocoActivity.this,
                        "Foco concluído! +25 XP 💪",
                        Toast.LENGTH_LONG).show();

                atualizarTempo(TEMPO_FOCO);

                String historico = repository.carregarHistorico();
                historico += diaAtual + ":25;";
                repository.salvarHistorico(historico);
                diaAtual++;

            }
        }.start();
    }

    private void cancelarFoco() {
        if (timer != null) {
            timer.cancel();
        }
        rodando = false;
        btnIniciar.setEnabled(true);
        btnCancelar.setEnabled(false);
        atualizarTempo(TEMPO_FOCO);
    }

    private void atualizarTempo(long millis) {
        int minutos = (int) (millis / 1000) / 60;
        int segundos = (int) (millis / 1000) % 60;
        txtTempo.setText(String.format("%02d:%02d", minutos, segundos));
    }
}