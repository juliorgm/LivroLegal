package br.com.juliorgm.livrolegal;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import br.com.juliorgm.livrolegal.adapters.LivroAdapter;
import br.com.juliorgm.livrolegal.model.Livro;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fabAddLivro;
    private RecyclerView recyclerViewLivros;
    private List<Livro> mListaDeLivros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fabAddLivro = findViewById(R.id.fab_add_livro);
        mListaDeLivros = new ArrayList<>();


        fabAddLivro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FormLivroActivity.class);
                startActivity(intent);
            }
        });

        carregaFirebase();
    }

    private void carregaFirebase(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("livros");

        // Read from the database
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                mListaDeLivros.clear();
                for (DataSnapshot d: dataSnapshot.getChildren()) {
                    Livro livro = d.getValue(Livro.class);
                    livro.setmId(d.getKey());
                    mListaDeLivros.add(livro);
                }
                carregaRecycler();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Main", "Failed to read value.", error.toException());
            }
        });
    }

    private void carregaRecycler() {
        recyclerViewLivros = findViewById(R.id.recycler_livro);
        LivroAdapter adapter = new LivroAdapter(this,mListaDeLivros);
        recyclerViewLivros.setAdapter(adapter);
    }
}
