/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.formula1.manejadoras;

import com.formula1.comunes.AccesosBBDD;
import com.formula1.comunes.DatosPantalla;
import com.formula1.comunes.DatosPersona;
import com.formula1.comunes.PantallaWeb;
import java.sql.SQLException;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Mato
 */
public class ModificarMisDatos implements PantallaWeb{

    public HttpServletRequest processRequest(HttpServletRequest request, HttpServletResponse response, DatosPantalla datosPantalla) {

        String nick=request.getParameter("nick");
        String correo=request.getParameter("correo");
        String pass=request.getParameter("pass");

        if(nick==null)nick="";
        if(correo==null)correo="";
        if(pass==null)pass="";

        if(nick.equals("") || correo.equals("") || pass.equals("")){
            System.out.println("Error al modificar los datos de un usuario. Algun dato obligatorio llega a blancos.");
            datosPantalla.setJsp("./error.jsp");
            datosPantalla.setTitulo("Error");
            request.setAttribute("ERROR", "No se han podido modificar tus datos.<BR />Por favor ponte en contacto con el administrador.");
        }else{
            try {
                int actualizado = AccesosBBDD.modificarDatosUsuarioSQL(nick, correo, pass);

                if(actualizado==0 || actualizado > 1){
                    System.out.println("Error al modificar los datos de un usuario. El resultado es "+actualizado+".");
                    datosPantalla.setJsp("./error.jsp");
                    datosPantalla.setTitulo("Error");
                    request.setAttribute("ERROR", "No se han podido modificar tus datos.<BR />Por favor ponte en contacto con el administrador.");
                }else{
                    HashMap datosUsuario = new HashMap();
                    try {
                        datosUsuario = AccesosBBDD.getDatosUsuarioSQL(nick);

                        String nick_recuperado=(String)datosUsuario.get("nick");
                        String nombre_recuperado=(String)datosUsuario.get("nombre");
                        String tipo_usuario_recuperado=(String)datosUsuario.get("tipo");
                        String correo_recuperado=(String)datosUsuario.get("correo");

                        HttpSession session = request.getSession();
                        DatosPersona datosPersona = new DatosPersona(nick_recuperado, nombre_recuperado, tipo_usuario_recuperado, correo_recuperado);
                        session.setAttribute("datosPersona",datosPersona);

                    } catch (SQLException ex) {
                        System.out.println("Error al recuperar los datos de "+nick+".");
                        datosPantalla.setJsp("./error.jsp");
                        datosPantalla.setTitulo("Error");
                        request.setAttribute("ERROR", "No se han podido recuperar tus datos después de la modificación.<BR />Por favor ponte en contacto con el administrador.");
                    }
                }
            } catch (SQLException ex) {
                System.out.println("Error al modificar los datos de un usuario. Error en el update.");
                datosPantalla.setJsp("./error.jsp");
                datosPantalla.setTitulo("Error");
                request.setAttribute("ERROR", "No se han podido modificar tus datos.<BR />Por favor ponte en contacto con el administrador.");
            }
        }

        return request;
    }
}
