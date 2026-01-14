package com.example.sistema.data;

import android.content.Context;
import android.content.SharedPreferences;

public class HabitoRepository {

    private SharedPreferences prefs;

    public HabitoRepository(Context context) {
        prefs = context.getSharedPreferences("habitos", Context.MODE_PRIVATE);
    }

    public void salvarHabito(String nome, int streak) {
        prefs.edit().putInt(nome, streak).apply();
    }

    public int carregarStreak(String nome) {
        return prefs.getInt(nome, 0);
    }
    public void salvarUltimaData(String nomeHabito, String data) {
        prefs.edit().putString(nomeHabito + "_data", data).apply();
    }

    public String carregarUltimaData(String nomeHabito) {
        return prefs.getString(nomeHabito + "_data", "");
    }
    private String hoje() {
        return java.time.LocalDate.now().toString();
    }

}