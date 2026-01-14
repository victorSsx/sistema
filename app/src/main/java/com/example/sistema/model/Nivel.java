package com.example.sistema.model;

public enum Nivel {
    INICIANTE(0, "Começando a jornada"),
    INTERMEDIARIO(30, "Ganhando consistência"),
    AVANCADO(80, "Alto desempenho"),
    MESTRE(100, "Excelência máxima");

    private final int xpMinimo;
    private final String descricao;

    Nivel(int xpMinimo, String descricao) {
        this.xpMinimo = xpMinimo;
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Nivel calcularPorXp(int xp) {
        Nivel resultado = INICIANTE;

        for (Nivel nivel : values()) {
            if (xp >= nivel.xpMinimo) {
                resultado = nivel;
            }
        }
    return resultado;
    }
}