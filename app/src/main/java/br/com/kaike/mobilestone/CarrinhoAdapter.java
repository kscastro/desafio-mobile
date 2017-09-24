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

public class CarrinhoAdapter extends RecyclerView.Adapter<CarrinhoAdapter.ViewHolder> {

    private ArrayList<Item> items;

    private Context context;

    public CarrinhoAdapter(Context context) {
        this.context = context;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pedido, parent, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(final CarrinhoAdapter.ViewHolder holder, int position) {
        final Item l = items.get(position);
        holder.tituloTextView.setText(l.getTitulo());
        holder.valorTextView.setText(String.valueOf(l.getValor()));
        Glide.with(context)
               .load(l.getUrlFoto())
                .into(holder.fotoImageView);

    }

    @Override
    public int getItemCount() {
        if (items == null)
            return 0;
        return items.size();
    }

    public void mostrasListaDeItens(ArrayList<Item> items) {
        this.items = items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView fotoImageView;
        private TextView tituloTextView;
        private TextView valorTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            fotoImageView = (ImageView) itemView.findViewById(R.id.foto_item);
            tituloTextView = (TextView) itemView.findViewById(R.id.titulo_item);
            valorTextView = (TextView) itemView.findViewById(R.id.valor_item);
        }
    }

}
