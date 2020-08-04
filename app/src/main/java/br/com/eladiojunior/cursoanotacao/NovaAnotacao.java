package br.com.eladiojunior.cursoanotacao;

import android.content.Intent;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import br.com.eladiojunior.cursoanotacao.db.BancoAnotacao;
import br.com.eladiojunior.cursoanotacao.dtos.AnotacaoDTO;

public class NovaAnotacao extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_anotacao);
    }

    public void criarAnotacao(View view) {

        AnotacaoDTO anotacaoDTO = new AnotacaoDTO();
        anotacaoDTO.setTitulo(obterTextoCampo(R.id.textTitulo));
        anotacaoDTO.setConteudo(obterTextoCampo(R.id.textConteudo));

        BancoAnotacao bancoAnotacao = new BancoAnotacao(getBaseContext());
        boolean result = bancoAnotacao.incluirAnotacao(anotacaoDTO);
        if (result) {
            Toast.makeText(getApplicationContext(), "Anotação criada com sucesso!", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Erro: Não foi possível gravar a anotação.", Toast.LENGTH_LONG).show();
        }

        cancelarCriacao(view);

    }

    private String obterTextoCampo(int idFieldView) {
        String result = "";
        EditText editText = (EditText)findViewById(idFieldView);
        if (editText != null) {
            result = editText.getText().toString();
        }
        return result;
    }

    public void cancelarCriacao(View view) {
        Intent startNewActivity = new Intent(this, MainActivity.class);
        startActivity(startNewActivity);
    }
}