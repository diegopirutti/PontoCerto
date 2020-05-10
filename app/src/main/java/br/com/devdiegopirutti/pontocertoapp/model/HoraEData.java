package br.com.devdiegopirutti.pontocertoapp.model;

public class HoraEData {

    private long timestamp;
    private boolean entrada;

    public HoraEData(long timestamp, boolean entrada) {
        this.timestamp = timestamp;
        this.entrada = entrada;
    }

    public HoraEData() {

    }
    public long getTimestamp() {
        return timestamp;
    }

    public boolean isEntrada() {
        return entrada;
    }
}
