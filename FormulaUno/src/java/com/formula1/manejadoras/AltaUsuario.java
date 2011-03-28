/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.formula1.manejadoras;

import com.formula1.comunes.AccesosBBDD;
import com.formula1.comunes.DatosPantalla;
import com.formula1.comunes.PantallaWeb;
import java.sql.SQLException;
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
                String nick=request.getParameter("nick_alta");
                String nombre=request.getParameter("nombre_alta");
                String correo=request.getParameter("correo_alta");
                String pass=request.getParameter("pass_alta_encrpt");

                if(nick==null) nick="";
                if(nombre==null) nombre="";
                if(correo==null) correo="";
                if(pass==null) pass="";

                AccesosBBDD.altaUsuarioSQL(nick, nombre, correo, pass);
                
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
}
