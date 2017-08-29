package br.com.edecio.controledefilmes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListaActivity extends AppCompatActivity {

    private ListView lvFilmes;
    private List<Filme> filmes;
    private FilmesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        lvFilmes = (ListView) findViewById(R.id.lvFilmes);
        filmes = new ArrayList<Filme>();

        Intent intent = getIntent();
        String filtro = "";
        if (intent.hasExtra("genero")) {
            filtro = intent.getStringExtra("genero");
        }

        String filename = "filmes.txt";
        FileInputStream inputStream;

        try {
            inputStream = openFileInput(filename);
            Scanner entrada = new Scanner(inputStream);

            // enquanto houver linhas para serem lidas
            while (entrada.hasNextLine()) {
                // lê uma linha do arquivo
                String linha = entrada.nextLine();
                // separa a linha pela ocorrência do ";"
                String[] partes = linha.split(";");

                if (filtro.equals("") || filtro.equals(partes[1])) {
                    // adiciona ao adapter
                    filmes.add(new Filme(partes[0], partes[1], Integer.parseInt(partes[2])));
                }
            }
            // fecha o arquivo
            inputStream.close();

            adapter = new FilmesAdapter(this, filmes);

            // insere os dados no listView
            lvFilmes.setAdapter(adapter);

        } catch (Exception e) {
            Toast.makeText(this, "Erro: "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
