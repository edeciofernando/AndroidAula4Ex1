package br.com.edecio.controledefilmes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

/**
 * Created by edecio on 24/08/2017.
 */

public class FilmesAdapter extends BaseAdapter {

    Context ctx;
    List<Filme> filmes;

    public FilmesAdapter(Context ctx, List<Filme> filmes) {
        this.ctx = ctx;
        this.filmes = filmes;
    }

    @Override
    public int getCount() {
        return filmes.size();
    }

    @Override
    public Object getItem(int position) {
        return filmes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Filme filme = filmes.get(position);

        View linha = LayoutInflater.from(ctx).inflate(R.layout.item_filme, null);

        TextView txtTitulo = (TextView) linha.findViewById(R.id.txtTitulo);
        TextView txtGenero = (TextView) linha.findViewById(R.id.txtGenero);
        RatingBar rbNota = (RatingBar) linha.findViewById(R.id.rbNota);

        txtTitulo.setText(filme.titulo);
        txtGenero.setText(filme.genero);
        rbNota.setRating(filme.nota);

        return linha;
    }
}
