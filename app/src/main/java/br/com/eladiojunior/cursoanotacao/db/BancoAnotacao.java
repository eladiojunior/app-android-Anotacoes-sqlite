package br.com.eladiojunior.cursoanotacao.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.com.eladiojunior.cursoanotacao.dtos.AnotacaoDTO;

import java.util.List;

public class BancoAnotacao {
    private GerenciarBancoAnotacao gerenciarBancoAnotacao;
    public BancoAnotacao(Context context) {
        gerenciarBancoAnotacao = new GerenciarBancoAnotacao(context);
    }

    /**
     * Registra um nova anotação no SQLite do dispositivo.
     * @param anotacao - Informação da anotacao.
     * @return
     */
    public boolean incluirAnotacao(AnotacaoDTO anotacao) {
        SQLiteDatabase dbSqLiteDatabase = gerenciarBancoAnotacao.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("titulo", anotacao.getTitulo());
        valores.put("conteudo", anotacao.getConteudo());
        long resultado = dbSqLiteDatabase.insert("anotacoes", null, valores);
        dbSqLiteDatabase.close();
        return (resultado > 0);
    }

    /**
     * Abrir cursor para listar as anotações.
     * @return
     */
    public Cursor listarAnotacao() {
        SQLiteDatabase dbSqLiteDatabase = gerenciarBancoAnotacao.getReadableDatabase();
        String[] campos = {"_id", "titulo"};
        Cursor query = dbSqLiteDatabase.query("anotacoes", campos, null, null, null, null, "titulo ASC");
        if (query!=null) {
            query.moveToFirst();
        }
        dbSqLiteDatabase.close();
        return query;
    }

    /**
     * Recupera uma anotação pelo ID
     * @param idAnotacao - Id da anotação.
     * @return
     */
    public Cursor obterAnotacaoPorId(int idAnotacao) {
        SQLiteDatabase dbSqLiteDatabase = gerenciarBancoAnotacao.getReadableDatabase();
        String[] campos = {"_id", "titulo", "conteudo"};
        String where = "_id=" + idAnotacao;

        Cursor query = dbSqLiteDatabase.query("anotacoes", campos, where, null, null, null, null);
        if (query!=null) {
            query.moveToFirst();
        }
        dbSqLiteDatabase.close();
        return query;
    }

    /**
     * Remover uma anotação pelo seu ID.
     * @param idAnotacao - Identificador da anotação.
     * @return
     */
    public boolean removerAnotacao(int idAnotacao) {
        SQLiteDatabase dbSqLiteDatabase = gerenciarBancoAnotacao.getReadableDatabase();
        String where = "_id="+idAnotacao;
        long resultado = dbSqLiteDatabase.delete("anotacoes", where, null);
        dbSqLiteDatabase.close();
        return (resultado > 0);
    }

    /**
     * Atualiza a anotação enviada.
     * @param dto - informações da anotação alterada;
     * @return
     */
    public boolean atualizarAnotacao(AnotacaoDTO dto) {
        SQLiteDatabase dbSqLiteDatabase = gerenciarBancoAnotacao.getReadableDatabase();
        String where = "_id="+dto.getId();

        ContentValues valores = new ContentValues();
        valores.put("titulo", dto.getTitulo());
        valores.put("conteudo", dto.getConteudo());

        long resultado = dbSqLiteDatabase.update("anotacoes", valores, where, null);
        dbSqLiteDatabase.close();
        return (resultado > 0);

    }
}
