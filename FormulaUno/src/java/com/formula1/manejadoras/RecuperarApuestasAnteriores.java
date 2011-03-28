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
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author fMato
 */
public class RecuperarApuestasAnteriores implements PantallaWeb{
    
    public HttpServletRequest processRequest(HttpServletRequest request, HttpServletResponse response, DatosPantalla datosPantalla) {
        System.out.println(this.getClass().getName());
        HashMap datosClasifAnteriores = new HashMap();
        HashMap resultadoUsuarios = new HashMap();
        ArrayList listaUsuarios=new ArrayList();
        ArrayList nombresUsuarios=new ArrayList();

        try {

            datosClasifAnteriores=AccesosBBDD.getApuestasAnterioresSQL();
            resultadoUsuarios=AccesosBBDD.getUsuariosSQL();
            listaUsuarios=(ArrayList) resultadoUsuarios.get("listaUsuarios");
            nombresUsuarios=(ArrayList) resultadoUsuarios.get("nombresUsuarios");

        } catch (SQLException ex) {
            System.out.println("Error al calcular las clasificaciones anteriores.");
            datosPantalla.setJsp("./error.jsp");
            datosPantalla.setTitulo("Error");
            request.setAttribute("ERROR", "No se ha podido calcular las clasificaciones anteriores, por favor ponte en contacto con el administrador.");
        }

        request.setAttribute("datosClasifAnteriores", datosClasifAnteriores);
        request.setAttribute("listaUsuarios", listaUsuarios);
        request.setAttribute("nombresUsuarios", nombresUsuarios);
        
        return request;
    }
}
