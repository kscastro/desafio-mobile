package br.com.kaike.mobilestone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import br.com.kaike.mobilestone.Model.Item;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "LOJA";
    //private static final Carrinho carrinho = new Carrinho();
    List<Item> itemList = new ArrayList<>();
    private Retrofit retrofit;
    private RecyclerView recyclerView;
    private LojaAdapter lojaAdapter;
    private int offset;
    private boolean aptoParaCarregar;
    private static final String UrlBase = "https://raw.githubusercontent.com/stone-pagamentos/desafio-mobile/master/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        lojaAdapter = new LojaAdapter(this);
        recyclerView.setAdapter(lojaAdapter);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();


                }
            }
        });
        retrofit = new Retrofit.Builder()
                .baseUrl(UrlBase)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        offset = 0;
        obterDados(offset);
    }


    private void obterDados(int offset) {
        lojaServices service = retrofit.create(lojaServices.class);
        Call<ArrayList<Item>> lojaRespostaCall = service.obterListaLoja(2, offset);
        lojaRespostaCall.enqueue(new Callback<ArrayList<Item>>() {
            @Override
            public void onResponse(Call<ArrayList<Item>> call, Response<ArrayList<Item>> response) {
                aptoParaCarregar = true;
                if (response.isSuccessful()) {
//                    LojaResposta lojaResposta = response.body();
                    ArrayList<Item> listaItem = response.body();
                    lojaAdapter.adicionarListaLoja(listaItem);

                } else {
                    Log.e(TAG, " onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Item>> call, Throwable t) {
                aptoParaCarregar = false;

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_carrinho) {
            startActivity(new Intent(MainActivity.this, ActivityCarrinho.class));
        }

        return super.onOptionsItemSelected(item);
    }
}

