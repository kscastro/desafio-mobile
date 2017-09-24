package br.com.kaike.mobilestone;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import br.com.kaike.mobilestone.Model.Item;

/**
 * Created by kaike on 23/09/2017.
 */

public class LojaAdapter extends RecyclerView.Adapter<LojaAdapter.ViewHolder> {

    private ArrayList<Item> dataset;
    private Context context;

    public LojaAdapter(Context context) {
        this.context = context;
        dataset = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.loja_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Item l = dataset.get(position);
        holder.tituloTextView.setText(l.getTitulo());
        Glide.with(context)
                .load(l.getUrlFoto())
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.fotoImageView);
        holder.fotoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.fotoImageView.getContext(), ActivityDoItem.class);
                intent.putExtra("ITEM", l);
                holder.fotoImageView.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void adicionarListaLoja(ArrayList<Item> listaItem) {
        dataset.addAll(listaItem);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView fotoImageView;
        private TextView tituloTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            fotoImageView = (ImageView) itemView.findViewById(R.id.fotoImageView);
            tituloTextView = (TextView) itemView.findViewById(R.id.tv_titulo);
        }
    }


}
