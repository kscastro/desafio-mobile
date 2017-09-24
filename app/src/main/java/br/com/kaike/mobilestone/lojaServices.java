package br.com.kaike.mobilestone;

import java.util.ArrayList;

import br.com.kaike.mobilestone.Model.Item;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by kaike on 21/09/2017.
 */

public interface lojaServices {


    @GET("products.json")
    Call<ArrayList<Item>> obterListaLoja(@Query("limit") int limit, @Query("offset") int offset);
}
