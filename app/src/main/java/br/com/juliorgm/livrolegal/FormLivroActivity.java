package br.com.juliorgm.livrolegal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.com.juliorgm.livrolegal.model.Livro;

public class FormLivroActivity extends AppCompatActivity {

    private EditText editTitulo;
    private EditText editAutor;
    private EditText editEditora;
    private EditText editDescricao;
    private Button buttonSalvar;
    private Livro livro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_livro);

        editTitulo = findViewById(R.id.edit_titulo);
        editAutor = findViewById(R.id.edit_autor);
        editEditora = findViewById(R.id.edit_editora);
        editDescricao = findViewById(R.id.edit_descricao);
        buttonSalvar = findViewById(R.id.button_salvar);
        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                livro = pegaLivro();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference reference = database.getReference("livros");

                reference.push().setValue(livro);
                finish();
            }
        });

        Intent intent = getIntent();
        if (intent != null){
            livro = (Livro)intent.getSerializableExtra("LIVRO");
            carregaCampos(livro);
        }
    }

    private void carregaCampos(Livro livro) {
        editTitulo.setText(livro.getmTitulo());
        editAutor.setText(livro.getmAutor());
        editEditora.setText(livro.getmEditora());
        editDescricao.setText(livro.getmDescricao());
    }

    private void cliqueBotoes() {
        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                livro = pegaLivro();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference reference = database.getReference("livros");

                reference.push().setValue(livro);
                finish();
            }
        });
    }

    private void carregaViews() {
        editTitulo = findViewById(R.id.edit_titulo);
        editAutor = findViewById(R.id.edit_autor);
        editEditora = findViewById(R.id.edit_editora);
        editDescricao = findViewById(R.id.edit_descricao);
        buttonSalvar = findViewById(R.id.button_salvar);
    }

    private Livro pegaLivro() {
        String titulo = editTitulo.getText().toString();
        String autor = editAutor.getText().toString();
        String editora = editEditora.getText().toString();
        String descricao = editDescricao.getText().toString();

        return new Livro(titulo,autor,editora,descricao);
    }
}
