/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.formula1.manejadoras;

import com.formula1.comunes.AccesosBBDD;
import com.formula1.comunes.BaseDeDatos;
import com.formula1.comunes.DatosPantalla;
import com.formula1.comunes.PantallaWeb;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mato
 */
public class ResultadoCarrera implements PantallaWeb {

    public HttpServletRequest processRequest(HttpServletRequest request, HttpServletResponse response, DatosPantalla datosPantalla) {

        ArrayList pilotos = new ArrayList();
        ArrayList carreras = new ArrayList();
        String ultimaCarreraDisputada = "";
        String accion="";

        accion=request.getParameter("accion");
        if(accion==null)accion="";

        if(accion.equals("guardar")){
            String mensajeConfirm="";
            try {
                guardarResultadoCarrera(request);
                mensajeConfirm+="Carrera guardada";
                String indicadorContabilizacion=request.getParameter("chCont");
                if(indicadorContabilizacion==null) indicadorContabilizacion="";
                if(indicadorContabilizacion.equals("S")){
                    System.out.println("Lanzamos clase de contabilización.");
                    String carrera=request.getParameter("carrera");
                    String[] param={carrera};
                    com.formula1.contabilizar.Main.main(param);
                    mensajeConfirm+=" y contabilizada";
                }
            } catch (SQLException ex) {
                System.out.println("Error al guardar el resultado de una carrera.");
                datosPantalla.setJsp("./error.jsp");
                datosPantalla.setTitulo("Error");
                request.setAttribute("ERROR", "No se ha podido guardar el resultado, el INSERT ha fallado.");
            }

            request.setAttribute("mensajeConfirm", mensajeConfirm+".");
            
        }
        
        try{
            
            pilotos = AccesosBBDD.getDatosPilotosSQL("ACTIVO");
            request.setAttribute("pilotos", pilotos);
            ultimaCarreraDisputada=AccesosBBDD.getCarreraAnteriorSQL();
            request.setAttribute("ultimaCarreraDisputada", ultimaCarreraDisputada);
            carreras=AccesosBBDD.getDatosCarrerasSQL();
            request.setAttribute("carreras", carreras);

        } catch (SQLException ex) {
            System.out.println("Error en las consultas de la pantalla para introducir el resultado de la carrera.");
            datosPantalla.setJsp("./error.jsp");
            datosPantalla.setTitulo("Error");
            request.setAttribute("ERROR", "Error al recuperar la lista de pilotos... o la de carreras... o algo. Ups!!");
        }

        return request;
    }

    public void guardarResultadoCarrera(HttpServletRequest request) throws SQLException {
        System.out.println(this.getClass().getName()+".guardarResultadoCarrera()");

        String carrera=request.getParameter("carrera");
        String pole=request.getParameter("pole");
        String primero=request.getParameter("primero");
        String segundo=request.getParameter("segund");
        String tercero=request.getParameter("tercero");
        String cuarto=request.getParameter("cuarto");
        String quinto=request.getParameter("quinto");
        String sexto=request.getParameter("sexto");
        String septimo=request.getParameter("septimo");
        String octavo=request.getParameter("octavo");
        String noveno=request.getParameter("noveno");
        String decimo=request.getParameter("decimo");

        if(carrera==null) carrera="";
        if(pole==null) pole="";
        if(primero==null) primero="";
        if(segundo==null) segundo="";
        if(tercero==null) tercero="";
        if(cuarto==null) cuarto="";
        if(quinto==null) quinto="";
        if(sexto==null) sexto="";
        if(septimo==null) septimo="";
        if(octavo==null) octavo="";
        if(noveno==null) noveno="";
        if(decimo==null) decimo="";

        AccesosBBDD.guardarResultadoCarreraSQL(carrera, pole, primero, segundo, tercero, cuarto, quinto, sexto, septimo, octavo, noveno, decimo);
    }
}
