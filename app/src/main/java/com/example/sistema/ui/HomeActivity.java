package com.example.sistema.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sistema.R;
import com.example.sistema.data.UsuarioRepository;
import com.example.sistema.model.Usuario;
import com.example.sistema.ui.HabitosActivity;

public class HomeActivity extends AppCompatActivity {

    private Usuario usuario;
    private UsuarioRepository repository;

    private TextView txtNome, txtXp, txtNivel, txtDescricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        repository = new UsuarioRepository(this);
        usuario = repository.carregarUsuario();

        txtNome = findViewById(R.id.txtNome);
        txtXp = findViewById(R.id.txtXp);
        txtNivel = findViewById(R.id.txtNivel);
        txtDescricao = findViewById(R.id.txtDescricao);
        Button btnFoco = findViewById(R.id.btnFoco);
        Button btnGrafico = findViewById(R.id.btnGrafico);



        atualizarUI();

        btnFoco.setOnClickListener(v -> {
            startActivity(new Intent(this, FocoActivity.class));

            usuario.ganharXp(10);
            repository.salvarUsuario(usuario);
            atualizarUI();

        });
        btnGrafico.setOnClickListener(v ->
                startActivity(new Intent(this, ProgressoActivity.class))
        );

        Button btnHabitos = findViewById(R.id.btnHabitos);

        btnHabitos.setOnClickListener(v -> {
            Intent intent = new Intent(
                    HomeActivity.this,
                    HabitosActivity.class
            );
            startActivity(intent);
        });

    }

    private void atualizarUI() {
        txtNome.setText("Olá, " + usuario.getNome());
        txtXp.setText("XP: " + usuario.getXp());
        txtNivel.setText("Nível: " + usuario.getNivel());
        txtDescricao.setText(usuario.getNivel().getDescricao());
    }
    
}
