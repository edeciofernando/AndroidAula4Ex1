package br.com.edecio.controledefilmes;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtTitulo;
    private Spinner spnGenero;
    private Spinner spnNota;
    private Button btnSalvar;
    private Button btnListar;
    private Button btnFiltrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtTitulo = (EditText) findViewById(R.id.edtTitulo);
        spnGenero = (Spinner) findViewById(R.id.spnGenero);
        spnNota = (Spinner) findViewById(R.id.spnNota);
        btnSalvar = (Button) findViewById(R.id.btnSalvar);
        btnListar = (Button) findViewById(R.id.btnListar);
        btnFiltrar = (Button) findViewById(R.id.btnFiltrar);

        btnSalvar.setOnClickListener(this);
        btnListar.setOnClickListener(this);
        btnFiltrar.setOnClickListener(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item);

        adapter.add("Aventura");
        adapter.add("Comédia");
        adapter.add("Drama");
        adapter.add("Romance");
        adapter.add("Suspense");

// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spnGenero.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item);

        adapter2.add("5");
        adapter2.add("4");
        adapter2.add("3");
        adapter2.add("2");
        adapter2.add("1");

// Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spnNota.setAdapter(adapter2);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnSalvar) {
            String titulo = edtTitulo.getText().toString();
            String genero = spnGenero.getSelectedItem().toString();
            String nota = spnNota.getSelectedItem().toString();

            if (titulo.trim().isEmpty()) {
                Toast.makeText(this, "Preencha os dados", Toast.LENGTH_SHORT).show();
                edtTitulo.requestFocus();
                return;
            }

            String filename = "filmes.txt";
            FileOutputStream outputStream;

            try {
                // MODE_APPEND: adiciona dados ao arquivo
                outputStream = openFileOutput(filename, Context.MODE_APPEND);
                outputStream.write((titulo+";"+genero+";"+nota+"\n").getBytes());
                outputStream.close();
                Toast.makeText(this, "Ok! Filme Cadastrado", Toast.LENGTH_SHORT).show();
                edtTitulo.setText("");
                spnGenero.setSelection(0);
                spnNota.setSelection(0);
                edtTitulo.requestFocus();
            } catch (Exception e) {
                Toast.makeText(this, "Erro: "+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else if (v.getId() == R.id.btnListar){
            Intent intent = new Intent(this, ListaActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, ListaActivity.class);
            String genero = spnGenero.getSelectedItem().toString();
            intent.putExtra("genero", genero);
            startActivity(intent);
        }
    }
}
