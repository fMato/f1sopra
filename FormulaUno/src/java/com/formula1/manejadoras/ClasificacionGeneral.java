/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.formula1.manejadoras;

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
 * @author Mato
 */
public class ClasificacionGeneral implements PantallaWeb{

    private ArrayList pilotosOrdenados=new ArrayList();

    public HttpServletRequest processRequest(HttpServletRequest request, HttpServletResponse response, DatosPantalla datosPantalla) {
        System.out.println(this.getClass().getName());

        HashMap puntosTotales = new HashMap();
        HashMap datosClasifGeneral = new HashMap();
        HashMap puntosMaximos = new HashMap();
        try {
            puntosTotales=getPuntosTotalesSQL();
            datosClasifGeneral = getPuntosCarrerasSQL(puntosTotales);
            puntosMaximos=getPuntosMaximosSQL();
            
        } catch (SQLException ex) {
            System.out.println("Error al calcular la clasificación general.");
            datosPantalla.setJsp("./error.jsp");
            datosPantalla.setTitulo("Error");
            request.setAttribute("ERROR", "No se ha podido calcular la clasifiación general, por favor ponte en contacto con el administrador.");
            return request;
        }

        request.setAttribute("pilotosOrdenados", pilotosOrdenados);
        request.setAttribute("datosClasifGeneral", datosClasifGeneral);
        request.setAttribute("puntosMaximos", puntosMaximos);

        return request;
    }
    public HashMap getPuntosCarrerasSQL(HashMap puntosTotales) throws SQLException {
        System.out.println(this.getClass().getName()+".getPuntosCarreraSQL()");
        BaseDeDatos bbdd = new BaseDeDatos();
        Connection conexion = bbdd.establecerConexion();
        String query="SELECT a.usuario, a.carrera, a.puntos, b.nombre FROM resultados_apuestas a, usuarios b WHERE a.usuario=b.nick ORDER BY a.usuario, a.carrera";

        Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery (query);

        HashMap resultadosTodosUsuarios = new HashMap();
        HashMap resultadosUnUsuario = new HashMap();
        String usuarioAnt="";
        if(rs!=null){
            while(rs.next()){
                String usuarioActual=rs.getString("usuario");
                String puntos=rs.getString("puntos");
                String carrera=rs.getString("carrera");
                String nombre=rs.getString("nombre");
                
                if(!usuarioActual.equals(usuarioAnt)){
                    resultadosUnUsuario = new HashMap();
                    resultadosUnUsuario.put("nombre", nombre);
                    resultadosUnUsuario.put("total", (String)puntosTotales.get(usuarioActual));
                    resultadosUnUsuario.put(carrera, puntos);
                    resultadosTodosUsuarios.put(usuarioActual, resultadosUnUsuario);
                }
                else{
                    resultadosUnUsuario.put(carrera, puntos);
                    resultadosTodosUsuarios.put(usuarioActual, resultadosUnUsuario);
                }
                usuarioAnt=usuarioActual;
            }
        }
        bbdd.cerrarConexion(conexion);
        return resultadosTodosUsuarios;
    }

    public HashMap getPuntosTotalesSQL() throws SQLException {
        System.out.println(this.getClass().getName()+".getPuntosTotalesSQL()");
        BaseDeDatos bbdd = new BaseDeDatos();
        Connection conexion = bbdd.establecerConexion();
        String query="SELECT usuario, SUM(puntos) as puntos_totales FROM resultados_apuestas GROUP BY usuario ORDER BY puntos_totales DESC, usuario ASC";

        Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery (query);

        HashMap puntosTotales = new HashMap();
        if(rs!=null){
            while(rs.next()){
                puntosTotales.put(rs.getString("usuario"), rs.getString("puntos_totales"));
                pilotosOrdenados.add(rs.getString("usuario"));
            }
        }
        bbdd.cerrarConexion(conexion);
        return puntosTotales;
    }

    public HashMap getPuntosMaximosSQL() throws SQLException {
        System.out.println(this.getClass().getName()+".getPuntosMaximoSQL()");
        BaseDeDatos bbdd = new BaseDeDatos();
        Connection conexion = bbdd.establecerConexion();
        String query="SELECT carrera, MAX(puntos) AS puntos FROM resultados_apuestas GROUP BY carrera";

        Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery (query);

        HashMap puntosMaximos = new HashMap();
        if(rs!=null){
            while(rs.next()){
                puntosMaximos.put(rs.getString("carrera"), rs.getString("puntos"));
            }
        }
        bbdd.cerrarConexion(conexion);
        return puntosMaximos;
    }
}