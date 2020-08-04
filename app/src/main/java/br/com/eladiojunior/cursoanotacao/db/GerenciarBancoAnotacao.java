package br.com.eladiojunior.cursoanotacao.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class GerenciarBancoAnotacao extends SQLiteOpenHelper
{
    private static final String NOME_BANCO = "dbanotacoes.db";
    private static final int VERSAO_BANCO = 1;

    public GerenciarBancoAnotacao(@Nullable Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreate = "CREATE TABLE anotacoes (_id integer primary key autoincrement, titulo text, conteudo text)";
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sqlDrop = "DROP TABLE IF EXISTS anotacoes";
        db.execSQL(sqlDrop);
        onCreate(db);
    }
}
