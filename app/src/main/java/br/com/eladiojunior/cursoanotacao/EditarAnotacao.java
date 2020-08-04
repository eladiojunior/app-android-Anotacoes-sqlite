package br.com.eladiojunior.cursoanotacao;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import br.com.eladiojunior.cursoanotacao.db.BancoAnotacao;
import br.com.eladiojunior.cursoanotacao.dtos.AnotacaoDTO;

public class EditarAnotacao extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_anotacao);
    }

    public void gravarAnotacao(View view) {

        AnotacaoDTO anotacaoDTO = new AnotacaoDTO();
        anotacaoDTO.setId(this.getIntent().getIntExtra("id", 0));
        anotacaoDTO.setTitulo(obterTextoCampo(R.id.textTitulo));
        anotacaoDTO.setConteudo(obterTextoCampo(R.id.textConteudo));

        BancoAnotacao bancoAnotacao = new BancoAnotacao(getBaseContext());
        boolean result = bancoAnotacao.atualizarAnotacao(anotacaoDTO);
        if (result)
        {
            Toast.makeText(getApplicationContext(), "Anotação atualizada com sucesso!", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Erro: Não foi possível atualizar a anotação.", Toast.LENGTH_LONG).show();
        }

        voltarListaAnotacao(view);

    }

    private String obterTextoCampo(int idFieldView) {
        String result = "";
        EditText editText = (EditText)findViewById(idFieldView);
        if (editText != null) {
            result = editText.getText().toString();
        }
        return result;
    }

    public void excluirAnotacao(View view) {

        int idAnotacao = this.getIntent().getIntExtra("id", 0);

        BancoAnotacao bancoAnotacao = new BancoAnotacao(getBaseContext());
        boolean result = bancoAnotacao.removerAnotacao(idAnotacao);
        if (result)
        {
            Toast.makeText(getApplicationContext(), "Anotação REMOVIDA com sucesso!", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Erro: Não foi possível remover a anotação.", Toast.LENGTH_LONG).show();
        }

        voltarListaAnotacao(view);
    }

    public void voltarListaAnotacao(View view) {
        Intent startNewActivity = new Intent(this, MainActivity.class);
        startActivity(startNewActivity);
    }
}