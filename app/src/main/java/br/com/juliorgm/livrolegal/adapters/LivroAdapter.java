package br.com.juliorgm.livrolegal.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.com.juliorgm.livrolegal.FormLivroActivity;
import br.com.juliorgm.livrolegal.R;
import br.com.juliorgm.livrolegal.model.Livro;

public class LivroAdapter extends
        RecyclerView.Adapter<LivroAdapter.LivrosHolder> {


    private Context mContext;
    private List<Livro> mListaDeLivros;

    public LivroAdapter(Context mContext,List<Livro> mListaDeLivros) {
        this.mListaDeLivros = mListaDeLivros;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public LivroAdapter.LivrosHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_livro,viewGroup,false);
        return new LivrosHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LivroAdapter.LivrosHolder livrosHolder, int i) {
        Livro livro = mListaDeLivros.get(i);
        livrosHolder.vincula(livro);
        livrosHolder.edit(livro);
    }

    @Override
    public int getItemCount() {
        return mListaDeLivros.size();
    }

    public class LivrosHolder extends RecyclerView.ViewHolder {

        private TextView textTitulo;
        private TextView textAutor;
        private TextView textEditora;
        private TextView textDescricao;
        private FloatingActionButton fabEditar;
        private FloatingActionButton fabDeletar;
        public LivrosHolder(@NonNull View itemView) {
            super(itemView);

            textTitulo = itemView.findViewById(R.id.text_item_titulo);
            textAutor = itemView.findViewById(R.id.text_item_autor);
            textEditora = itemView.findViewById(R.id.text_item_editora);
            textDescricao = itemView.findViewById(R.id.text_item_descricao);
            fabEditar = itemView.findViewById(R.id.fab_item_editar);
            fabDeletar = itemView.findViewById(R.id.fab_item_deletar);
        }

        public void vincula(Livro livro){
            textTitulo.setText(livro.getmTitulo());
            textAutor.setText(livro.getmAutor());
            textEditora.setText(livro.getmEditora());
            textDescricao.setText(livro.getmDescricao());
        }

        public void delete(){
            fabDeletar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
        public void edit(final Livro livro){
            fabEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, FormLivroActivity.class);
                    intent.putExtra("LIVRO",livro);
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
