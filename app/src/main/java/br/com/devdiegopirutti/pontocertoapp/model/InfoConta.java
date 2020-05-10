package br.com.devdiegopirutti.pontocertoapp.model;

public class InfoConta {

    String name, empresa;

    public InfoConta(String name, String empresa){
        this.name = name;
        this.empresa = empresa;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
}
