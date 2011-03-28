/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.formula1.manejadoras;

import com.formula1.comunes.AccesosBBDD;
import com.formula1.comunes.DatosPantalla;
import com.formula1.comunes.PantallaWeb;
import java.sql.SQLException;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mato
 */
public class RecuperarApuestaAnterior implements PantallaWeb{

    public HttpServletRequest processRequest(HttpServletRequest request, HttpServletResponse response, DatosPantalla datosPantalla) {
        System.out.println(this.getClass().getName());

        String usuario=request.getParameter("usuario");
        String carrera=request.getParameter("carrera");

        if(usuario==null)usuario="";
        if(carrera==null)carrera="";

        HashMap apuestaCarrera = new HashMap();
        HashMap listaPilotos = new HashMap();
        HashMap resultadoCarrera = new HashMap();
        String nombreCarrera = "";
        String nombreUsuairo = "";
        try {
            apuestaCarrera = AccesosBBDD.getDatosCarreraAnteriorSQL(usuario, carrera);
            nombreCarrera=AccesosBBDD.getNombreCarreraSQL(carrera);
            nombreUsuairo=AccesosBBDD.getNombreUsuarioSQL(usuario);
            listaPilotos=AccesosBBDD.getListaNombrePilotosSQL();
            resultadoCarrera =AccesosBBDD.getResultadoCarreraSQL(carrera);

        } catch (SQLException ex) {
            System.out.println("Error al recuperar la clasificación de "+usuario+" para la carrera "+carrera+".");
            datosPantalla.setJsp("./error.jsp");
            datosPantalla.setTitulo("Error");
            request.setAttribute("ERROR", "No se ha podido calcular la clasificación anterior de "+usuario+" para la carrera "+carrera+", por favor ponte en contacto con el administrador.");
        }

        request.setAttribute("apuestaCarrera", apuestaCarrera);
        request.setAttribute("nombreCarrera", nombreCarrera);
        request.setAttribute("nombreUsuairo", nombreUsuairo);
        request.setAttribute("listaPilotos", listaPilotos);
        request.setAttribute("resultadoCarrera", resultadoCarrera);
        
        return request;
    }
    
}
