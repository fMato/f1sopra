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
public class ClasificacionGeneral implements PantallaWeb{

    public HttpServletRequest processRequest(HttpServletRequest request, HttpServletResponse response, DatosPantalla datosPantalla) {
        System.out.println(this.getClass().getName());

        HashMap resultadoPuntosTotales = new HashMap();
        HashMap datosClasifGeneral = new HashMap();
        HashMap puntosMaximos = new HashMap();
        ArrayList usuariosOrdenados=new ArrayList();

        try {
            resultadoPuntosTotales=AccesosBBDD.getPuntosTotalesSQL();
            datosClasifGeneral = AccesosBBDD.getPuntosCarrerasSQL(resultadoPuntosTotales);
            puntosMaximos=AccesosBBDD.getPuntosMaximosSQL();

            String ordenarPor=request.getParameter("ordenarPor");
            if(ordenarPor==null) ordenarPor="";
            
            if(ordenarPor.equals("") || ordenarPor.equals("PT"))
                usuariosOrdenados=(ArrayList)resultadoPuntosTotales.get("ListaUsuariosOrdenados");
            else{
                ArrayList usuariosOrdenados_aux=AccesosBBDD.getOrdenadosCarreraSQL(ordenarPor);

                if(!usuariosOrdenados_aux.isEmpty()){
                    usuariosOrdenados=usuariosOrdenados_aux;
                    resultadoPuntosTotales.put("ListaUsuariosOrdenados",usuariosOrdenados);
                }else{
                    usuariosOrdenados=(ArrayList)resultadoPuntosTotales.get("ListaUsuariosOrdenados");
                }
                
            }
      
        } catch (SQLException ex) {
            System.out.println("Error al calcular la clasificación general.");
            datosPantalla.setJsp("./error.jsp");
            datosPantalla.setTitulo("Error");
            request.setAttribute("ERROR", "No se ha podido calcular la clasifiación general, por favor ponte en contacto con el administrador.");
            return request;
        }

        request.setAttribute("usuariosOrdenados", usuariosOrdenados);
        request.setAttribute("datosClasifGeneral", datosClasifGeneral);
        request.setAttribute("puntosMaximos", puntosMaximos);

        return request;
    }

}
