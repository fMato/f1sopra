/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.formula1.manejadoras;

import com.formula1.comunes.AccesosBBDD;
import com.formula1.comunes.BaseDeDatos;
import com.formula1.comunes.DatosPantalla;
import com.formula1.comunes.DatosPersona;
import com.formula1.comunes.PantallaWeb;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Mato
 */
public class RecuperarMisDatos implements PantallaWeb{

    public HttpServletRequest processRequest(HttpServletRequest request, HttpServletResponse response, DatosPantalla datosPantalla) {
        System.out.println(this.getClass().getName());
        HttpSession session = request.getSession();
        DatosPersona datosPersona = (DatosPersona) session.getAttribute("datosPersona");
        String usuarioSession=datosPersona.getNick();

        HashMap datosUsuario = new HashMap();
        try {
            datosUsuario = AccesosBBDD.getDatosUsuarioSQL(usuarioSession);
        } catch (SQLException ex) {
            System.out.println("Error al recuperar los datos de "+usuarioSession+".");
            datosPantalla.setJsp("./error.jsp");
            datosPantalla.setTitulo("Error");
            request.setAttribute("ERROR", "No se han podido recuperar tus datos, por favor ponte en contacto con el administrador.");
        }

        request.setAttribute("datosUsuario", datosUsuario);

        return request;
    }
}
