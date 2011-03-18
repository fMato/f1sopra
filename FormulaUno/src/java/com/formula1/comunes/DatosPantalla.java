package com.formula1.comunes;

/**
 *
 * @author Mato
 */
public class DatosPantalla {
    private String jspPresentacion="";
    private String tituloPantalla="";

    public DatosPantalla(){
            // Vacío.
        }

    public void setJsp(String jspPresentacionIn){
        jspPresentacion=jspPresentacionIn;
    }

    public String getJsp(){
        return jspPresentacion;
    }

    public void setTitulo(String tituloPantallaIn){
        tituloPantalla=tituloPantallaIn;
    }

    public String getTitulo(){
        return tituloPantalla;
    }
}
