package br.com.devdiegopirutti.pontocertoapp.model;

public class ModelHist {

    String data, entrada, saida;

public ModelHist( String data, String entrada, String saida){
    this.data = data;
    this.entrada = entrada;
    this.saida = saida;
}



    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getEntrada() {
        return entrada;
    }

    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }

    public String getSaida() {
        return saida;
    }

    public void setSaida(String saida) {
        this.saida = saida;
    }
}