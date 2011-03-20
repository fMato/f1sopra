/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.formula1.manejadoras;

import com.formula1.comunes.BaseDeDatos;
import com.formula1.comunes.DatosPantalla;
import com.formula1.comunes.PantallaWeb;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author fMato
 */
public class AltaUsuario implements PantallaWeb{

    public HttpServletRequest processRequest(HttpServletRequest request, HttpServletResponse response, DatosPantalla datosPantalla) {
        System.out.println(this.getClass().getName());

        String accion = request.getParameter("accion");
        if(accion==null) accion="";

        if(accion.equals("Alta")){
            try {
                altaUsuarioSQL(request, datosPantalla);
            } catch (SQLException ex) {
                System.out.println("Error al dar de alta un usuario.");
                datosPantalla.setJsp("./error.jsp");
                datosPantalla.setTitulo("Error");
                request.setAttribute("ERROR", "Error al dar de alta el usuario.");
                return request;
            }
        }

        return request;
    }
    public void altaUsuarioSQL(HttpServletRequest request, DatosPantalla datosPantalla) throws SQLException {
        System.out.println(this.getClass().getName()+".altaUsuarioSQL()");
        BaseDeDatos bbdd = new BaseDeDatos();
        Connection conexion = bbdd.establecerConexion();

        String nick=request.getParameter("nick_alta");
        String nombre=request.getParameter("nombre_alta");
        String correo=request.getParameter("correo_alta");
        String pass=request.getParameter("pass_alta_encrpt");

        if(nick==null) nick="";
        if(nombre==null) nombre="";
        if(correo==null) correo="";
        if(pass==null) pass="";

        String query="INSERT INTO usuarios (nick,pass,nombre,tipo_usuario,correo) VALUES ('"+nick+"', '"+pass+"', '"+nombre+"', 'U', '"+correo+"')";
        Statement s = conexion.createStatement();
        int i = s.executeUpdate(query);

        System.out.println("Apuesta guardada. Resultado: "+i);

        if(i!=0){
            System.out.println("Error al dar de alta un usuario.");
            datosPantalla.setJsp("./error.jsp");
            datosPantalla.setTitulo("Error");
            request.setAttribute("ERROR", "Error al dar de alta el usuario.");
        }
    }
}
