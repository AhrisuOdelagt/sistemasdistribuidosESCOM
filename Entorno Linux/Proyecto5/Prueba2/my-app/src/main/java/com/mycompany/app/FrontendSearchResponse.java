/*    --- Información del proyecto ----
	No. de proyecto: Final
	Nombre completo: Franco Olvera Demian Oder
	Grupo: 7CM1
*/

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
