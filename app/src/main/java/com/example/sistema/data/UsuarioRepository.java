package com.example.sistema.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.sistema.model.Usuario;

public class UsuarioRepository {

    private static final String PREFS_NAME = "usuario_prefs";
    private static final String KEY_NOME = "nome";
    private static final String KEY_XP = "xp";

    private static final String KEY_HISTORICO = "historico_xp";


    private final SharedPreferences prefs;

    public UsuarioRepository(Context context) {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    // SALVAR
    public void salvarUsuario(Usuario usuario) {
        prefs.edit()
                .putString(KEY_NOME, usuario.getNome())
                .putInt(KEY_XP, usuario.getXp())
                .apply();
    }

    // CARREGAR
    public Usuario carregarUsuario() {
        String nome = prefs.getString(KEY_NOME, "Victor");
        int xp = prefs.getInt(KEY_XP, 0);

        return new Usuario(nome, xp);
    }

    public void salvarHistorico(String historico) {
        prefs.edit().putString(KEY_HISTORICO, historico).apply();
    }

    public String carregarHistorico() {
        return prefs.getString(KEY_HISTORICO, "");
    }

}