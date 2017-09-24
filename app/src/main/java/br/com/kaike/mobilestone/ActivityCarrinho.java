package br.com.kaike.mobilestone;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.zip.Inflater;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ActivityCarrinho extends AppCompatActivity {
    private final String urlBase = "https://us-central1-mobilestone-16397.cloudfunctions.net/";
    private Retrofit retrofit;
    private Button pedir;
    private TextView subtotal;
    private CarrinhoAdapter adapter = new CarrinhoAdapter(this);

    private String card_number, cvv, name, expDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);

        subtotal = (TextView) findViewById(R.id.carrinho_textView);
        pedir = (Button) findViewById(R.id.carrinho_button);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.carrinho_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.mostrasListaDeItens(Carrinho.getInstance().getItems());
        subtotal.setText(String.format("Subtotal: $ %s", Carrinho.getInstance().getSubtotal()));

        retrofit = new Retrofit.Builder()
                .baseUrl(urlBase)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        pedir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder alert = new AlertDialog.Builder(ActivityCarrinho.this);
                final View v = LayoutInflater.from(ActivityCarrinho.this).inflate(R.layout.dialog_cartao, new FrameLayout(getBaseContext()), false);
                alert
                        .setView(v)
                        .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                name = ((EditText) v.findViewById(R.id.dialog_nome)).getText().toString();
                                card_number = ((EditText) v.findViewById(R.id.dialog_cartao)).getText().toString();
                                cvv = ((EditText) v.findViewById(R.id.dialog_cvv)).getText().toString();
                                expDate = ((EditText) v.findViewById(R.id.dialog_data)).getText().toString();
                                enviarPedido();
                            }
                        })
                        .show();
            }
        });
    }

    private void enviarPedido() {
        Log.d("Carrinho", "enviarPedido: Enviando o pedido.. [" + name + ", " + card_number + ", " + cvv + "," + expDate + "]");
        PedidoService pedidoService = retrofit.create(PedidoService.class);
        pedidoService.enviarPedido(Carrinho.getInstance().preparaPedido(card_number, cvv, name, expDate))
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Toast.makeText(ActivityCarrinho.this, "Venda Finalizada!", Toast.LENGTH_SHORT).show();
                        Log.d("Carrinho", "onResponse: " + response.body());
                        finish();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.d("Carrinho", "onFailure: ", t);
                    }
                });
    }
}
