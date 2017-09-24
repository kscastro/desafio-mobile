package br.com.kaike.mobilestone;

import br.com.kaike.mobilestone.Model.Pedido;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by kaike on 23/09/2017.
 */

public interface PedidoService {
    @POST("postCartoCredito")
    Call<String> enviarPedido(@Body Pedido pedido);
}
