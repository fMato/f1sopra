/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.formula1.manejadoras;

import com.formula1.comunes.AccesosBBDD;
import com.formula1.comunes.DatosPantalla;
import com.formula1.comunes.PantallaWeb;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mato
 */
public class Estadisticas implements PantallaWeb{

    public HttpServletRequest processRequest(HttpServletRequest request, HttpServletResponse response, DatosPantalla datosPantalla) {

        String estadistica=request.getParameter("ActividadApuestas");
        if(estadistica==null)estadistica="";

        ArrayList nombreUsuario = new ArrayList();
        ArrayList actividadUsuario = new ArrayList();
        ArrayList colorCelda = new ArrayList();
        HashMap resultadoActividad = new HashMap();

        try {
            resultadoActividad=AccesosBBDD.getActividadApuestasSQL();
            nombreUsuario=(ArrayList)resultadoActividad.get("nombreUsuario");
            actividadUsuario=(ArrayList)resultadoActividad.get("actividadUsuario");
            colorCelda=(ArrayList)resultadoActividad.get("colorCelda");
        } catch (SQLException ex) {
            System.out.println("Error al calcular la actividad de la tabla apuestas.");
            datosPantalla.setJsp("./error.jsp");
            datosPantalla.setTitulo("Error");
            request.setAttribute("ERROR", "Error al calcular la actividad de la tabla apuestas");
        }
        request.setAttribute("nombreUsuario", nombreUsuario);
        request.setAttribute("actividadUsuario", actividadUsuario);
        request.setAttribute("colorCelda", colorCelda);

        return request;
    }
}
