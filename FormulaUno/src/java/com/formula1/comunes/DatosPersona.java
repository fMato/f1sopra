package com.formula1.comunes;

/**
 *
 * @author Mato
 */
public class DatosPersona {
    private String nick="";
    private String nombre="";
    private String tipoUsuario="";
    private String correo="";

    public DatosPersona(String nick_recuperado, String nombre_recuperado, String tipoUsuario_recuperado, String correo_recuperado) {
        // Vacío
        nick=nick_recuperado;
        nombre=nombre_recuperado;
        tipoUsuario=tipoUsuario_recuperado;
        correo=correo_recuperado;
    }

    public void setNick(String nickIn){
        nick=nickIn;
    }

    public String getNick(){
        return nick;
    }

    public void setNombre(String nombreIn){
        nombre=nombreIn;
    }

    public String getNombre(){
        return nombre;
    }

    public void setTipoUsuario(String tipoUsuarioIn){
        tipoUsuario=tipoUsuarioIn;
    }

    public String getTipoUsuario(){
        return tipoUsuario;
    }

    public void setCorreo(String correoIn){
        correo=correoIn;
    }

    public String getCorreo(){
        return correo;
    }

    public String toString(){
        return nick+"|"+nombre+"|"+tipoUsuario;
    }
}
