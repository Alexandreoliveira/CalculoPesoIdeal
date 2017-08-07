package senac.com.br.calculo_peso_ideal_senac;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Formatter;
import java.util.Locale;

public class Principal extends AppCompatActivity {

    private EditText nome;
    private EditText altura;
    private RadioButton masculino;
    private RadioButton feminino;
    private Button calcular;
    private Button limpar;
    private TextView mensagem;

    private double pesoIdeal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        StringBuilder sb = new StringBuilder();
        final Formatter formatter = new Formatter(sb, new Locale("pt", "BR"));


        this.nome = (EditText) findViewById(R.id.nome);
        this.altura = (EditText) findViewById(R.id.altura);
        this.masculino = (RadioButton) findViewById(R.id.masculino);
        this.feminino = (RadioButton) findViewById(R.id.feminino);
        this.calcular = (Button) findViewById(R.id.calcular);
        this.limpar = (Button) findViewById(R.id.limpar);
        this.mensagem = (TextView) findViewById(R.id.mensagem);

        /**
         * calcula o peso ideal da pessoa;
         */
        this.calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (nome.getText().toString().equals("")) {
                    Toast.makeText(Principal.this, "Preencha o campo nome", Toast.LENGTH_LONG).show();
                    return;
                }
                if (altura.getText().toString().equals("")) {
                    Toast.makeText(Principal.this, "Preencha o campo altura", Toast.LENGTH_LONG).show();
                    return;
                }
                if (masculino.isChecked()) {
                    pesoIdeal = (72.7 * Double.parseDouble(altura.getText().toString())) - 58;
                    mensagem.setText(nome.getText().toString() + formatter.format(" seu peso ideal é %.2f Kg.", pesoIdeal));
                } else if (feminino.isChecked()) {
                    pesoIdeal = (62.1 * Double.parseDouble(altura.getText().toString())) - 44.7;
                    mensagem.setText(nome.getText().toString() + formatter.format(" seu peso ideal é %.2f Kg.", pesoIdeal));
                } else {
                    Toast.makeText(Principal.this, "Selecione o sexo", Toast.LENGTH_LONG).show();
                }

            }
        });

        /**
         * Limpa todos os campos para serem inseridos outros novamente;
         */
        this.limpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limparCampos();
            }
        });


        this.masculino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (masculino.isChecked()) {
                    feminino.setChecked(false);
                }
            }
        });

        this.feminino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (feminino.isChecked()) {
                    masculino.setChecked(false);
                }
            }
        });
    }

    private void limparCampos() {

        this.nome.setText("");
        this.nome.requestFocus();
        this.altura.setText("");
        this.mensagem.setText("");
        this.masculino.setChecked(false);
        this.feminino.setChecked(false);

    }

}
