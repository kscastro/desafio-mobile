package br.com.kaike.mobilestone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import br.com.kaike.mobilestone.Model.Item;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityDoItem extends AppCompatActivity {
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.fotoImageView)
    ImageView tvImagemText;
    @BindView(R.id.tv_valor)
    TextView tValor;
    @BindView(R.id.tv_titulo)
    TextView tTitulo;
    @BindView(R.id.tv_vendedor)
    TextView tVendedor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_item);
        ButterKnife.bind(this);

        if (getIntent() != null && getIntent().hasExtra("ITEM")) {
            final Item item = getIntent().getParcelableExtra("ITEM");

            tValor.setText(String.valueOf(item.getValor()));
            Glide.with(ActivityDoItem.this).load(item.getUrlFoto()).into(tvImagemText);
            tTitulo.setText(item.getTitulo());
            tVendedor.setText(item.getLoja());

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Carrinho.getInstance().addItem(item);
                    finish();
                    Toast.makeText(ActivityDoItem.this, "Item adicionado!", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "Nada para mostrar :(", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
