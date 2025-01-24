package com.mycompany.app;

public class FrontendSearchResponse {
    private String cadena;
    private String cantidad;

    public FrontendSearchResponse(String cadena, String cantidad) {
        this.cadena = cadena;
        this.cantidad = cantidad;
    }

    public String getCadena() {
        return cadena;
    }

    public String getCantidad() {
        return cantidad;
    }
}
