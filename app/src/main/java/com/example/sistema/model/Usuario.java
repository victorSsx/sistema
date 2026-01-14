package com.example.sistema.model;

public class Usuario {
    private String nome;
    private int xp;
    private Nivel nivel;

    public Usuario(String nome, int xpInicial) {
        this.nome = nome;
        this.xp = xpInicial;
        atualizarNivel();
    }
    public void ganharXp(int valor) {
        xp += valor;
        atualizarNivel();
    }
    private void atualizarNivel() {
        nivel = Nivel.calcularPorXp(xp);
    }
    public String getNome() {
        return nome;
    }
    public int getXp() {
        return xp;
    }
    public Nivel getNivel() {
        return nivel;
    }

}
