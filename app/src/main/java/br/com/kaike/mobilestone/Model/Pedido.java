package br.com.kaike.mobilestone.Model;

/**
 * Created by kaike on 23/09/2017.
 */

public class Pedido {

    /**
     * card_number : 1234123412341234
     * value : 7990
     * cvv : 789
     * card_holder_name : Luke Skywalker
     * exp_date : 12/24
     */
    private String card_number;
    private double value;
    private int cvv;
    private String card_holder_name;
    private String exp_date;

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public String getCard_holder_name() {
        return card_holder_name;
    }

    public void setCard_holder_name(String card_holder_name) {
        this.card_holder_name = card_holder_name;
    }

    public String getExp_date() {
        return exp_date;
    }

    public void setExp_date(String exp_date) {
        this.exp_date = exp_date;
    }

}
