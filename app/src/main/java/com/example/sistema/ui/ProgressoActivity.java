package com.example.sistema.ui;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.example.sistema.R;
import com.example.sistema.data.UsuarioRepository;

import java.util.ArrayList;
import java.util.List;

public class ProgressoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progresso);

        LineChart chart = findViewById(R.id.lineChart);
        UsuarioRepository repository = new UsuarioRepository(this);

        String historico = repository.carregarHistorico();
        List<Entry> entries = new ArrayList<>();

        if (historico != null && !historico.isEmpty()) {
            String[] registros = historico.split(";");
            for (String r : registros) {
                String[] partes = r.split(":");
                if (partes.length == 2) {
                    try {
                        int dia = Integer.parseInt(partes[0]);
                        int xp = Integer.parseInt(partes[1]);
                        entries.add(new Entry(dia, xp));
                    } catch (NumberFormatException e) {
                        // Ignora registros malformados
                    }
                }
            }
        }

        if (!entries.isEmpty()) {
            LineDataSet dataSet = new LineDataSet(entries, "XP por Dia");
            LineData data = new LineData(dataSet);
            chart.setData(data);
            chart.invalidate(); // desenha
        }
    }
}
