package br.com.kaike.mobilestone;

import java.util.ArrayList;

import br.com.kaike.mobilestone.Model.Item;
import br.com.kaike.mobilestone.Model.Pedido;

/**
 * Created by kaike on 23/09/2017.
 */

public class Carrinho {
    private static Carrinho instance;
    private ArrayList<Item> items = new ArrayList<>();

    private Carrinho() {
    }

    public static Carrinho getInstance() {
        if (instance == null) {
            instance = new Carrinho();
        }
        return instance;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public Pedido preparaPedido(String card_number, String cvv, String name, String expDate) {
        Pedido pedido = new Pedido();
        pedido.setCard_number(card_number);
        pedido.setCard_holder_name(name);
        pedido.setCvv(Integer.parseInt(cvv));
        pedido.setExp_date(expDate);
        double total = getSubtotal();
        pedido.setValue(total);
        items.clear();
        return pedido;
    }

    public double getSubtotal() {
        double total = 0;
        for (Item i : items)
            total += i.getValor();
        return total;
    }

    public ArrayList<Item> getItems() {
        return items;
    }
}
