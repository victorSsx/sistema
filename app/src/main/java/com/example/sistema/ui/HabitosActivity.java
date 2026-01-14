package com.example.sistema.ui;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sistema.R;
import com.example.sistema.data.HabitoRepository;
import com.example.sistema.model.Usuario;


public class HabitosActivity extends AppCompatActivity {
    private Usuario usuario;

    private HabitoRepository repository;
    private int streak;

    private String hoje() {
        return java.time.LocalDate.now().toString();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habitos);

        repository = new HabitoRepository(this);

        CheckBox checkEstudo = findViewById(R.id.checkEstudo);
        TextView txtStreak = findViewById(R.id.txtStreak);

        streak = repository.carregarStreak("Estudo");
        txtStreak.setText("🔥 Streak: " + streak + " dias");

        streak = repository.carregarStreak("Estudo");
        txtStreak.setText("🔥 Streak: " + streak + " dias");

// === PASSO 3: RESET DIÁRIO ===
        String hoje = hoje();
        String ultimaData = repository.carregarUltimaData("Estudo");

        if (!hoje.equals(ultimaData)) {
            // Dia novo → reset visual
            checkEstudo.setChecked(false);
        }


        checkEstudo.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                streak++;
                repository.salvarHabito("Estudo", streak);
                repository.salvarUltimaData("Estudo", hoje());

                txtStreak.setText("🔥 Streak: " + streak + " dias");

                usuario.ganharXp(15); // XP só aqui
            }
        });


    }
}