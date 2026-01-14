package com.example.sistema.model;

public class Habito {
    private String nome;
    private int streak;
    private boolean feitoHoje;

    public Habito(String nome) {
        this.nome = nome;
        this.streak = 0;
        this.feitoHoje = false;
    }

    public void concluir() {
        if (!feitoHoje) {
            streak++;
            feitoHoje = true;
        }
    }

    public void resetarDia() {
        feitoHoje = false;
    }

    public String getNome() {
        return nome;
    }

    public int getStreak() {
        return streak;
    }

    public boolean isFeitoHoje() {
        return feitoHoje;
    }
}
