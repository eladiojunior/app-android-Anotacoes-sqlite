package br.com.eladiojunior.cursoanotacao;

import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import br.com.eladiojunior.cursoanotacao.db.BancoAnotacao;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        carregarListaAnotacoes();
    }

    private void carregarListaAnotacoes() {
        BancoAnotacao bancoAnotacao = new BancoAnotacao(getBaseContext());
        final Cursor lista = bancoAnotacao.listarAnotacao();
        String[] campos = {"_id", "titulo"};
        int[] idsView = {R.id.labelId, R.id.labelTitulo};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getBaseContext(), R.layout.model_lista_anotacoes, lista, campos, idsView, 0);
        ListView listaAnotacoes = (ListView)findViewById(R.id.listaAnotacoes);
        listaAnotacoes.setAdapter(adapter);
        listaAnotacoes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lista.moveToPosition(position);
                Intent intent = new Intent(view.getContext(), EditarAnotacao.class);
                intent.putExtra("id", lista.getInt(lista.getColumnIndexOrThrow("_id")));
                startActivity(intent);
                finish();
            }
        });
    }

    public void abrirTelaNovaAnotacao(View view) {
        Intent startNewActivity = new Intent(this, NovaAnotacao.class);
        startActivity(startNewActivity);
    }
}