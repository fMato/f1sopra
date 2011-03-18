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
            apuestaCarrera = getDatosCarreraAnteriorSQL(usuario, carrera);
            nombreCarrera=getNombreCarreraSQL(carrera);
            nombreUsuairo=getNombreUsuarioSQL(usuario);
            listaPilotos=getListaPilotosSQL();
            resultadoCarrera =getResultadoCarreraSQL(carrera);

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

    public HashMap getDatosCarreraAnteriorSQL(String usuario, String carrera) throws SQLException {
        System.out.println(this.getClass().getName()+".getDatosCarreraAnteriorSQL()");
        BaseDeDatos bbdd = new BaseDeDatos();
        Connection conexion = bbdd.establecerConexion();
        String query="SELECT * FROM apuestas_carreras WHERE usuario='"+usuario+"' AND carrera='"+carrera+"'";

        Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery (query);

        HashMap apuestaCarrera = new HashMap();

        if(rs!=null){
            while(rs.next()){
                apuestaCarrera.put("pole", rs.getString("pole"));
                apuestaCarrera.put("primero", rs.getString("primero"));
                apuestaCarrera.put("segundo", rs.getString("segundo"));
                apuestaCarrera.put("tercero", rs.getString("tercero"));
                apuestaCarrera.put("cuarto", rs.getString("cuarto"));
                apuestaCarrera.put("quinto", rs.getString("quinto"));
                apuestaCarrera.put("sexto", rs.getString("sexto"));
                apuestaCarrera.put("septimo", rs.getString("septimo"));
                apuestaCarrera.put("octavo", rs.getString("octavo"));
                apuestaCarrera.put("noveno", rs.getString("noveno"));
                apuestaCarrera.put("decimo", rs.getString("decimo"));
            }
        }
        bbdd.cerrarConexion(conexion);

        return apuestaCarrera;
    }

    public HashMap getResultadoCarreraSQL(String carrera) throws SQLException {
        System.out.println(this.getClass().getName()+".getResultadoCarreraSQL()");
        BaseDeDatos bbdd = new BaseDeDatos();
        Connection conexion = bbdd.establecerConexion();
        String query="SELECT * FROM resultado_real_carreras WHERE identificador="+carrera+"";

        Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery (query);

        HashMap resultadoCarrera = new HashMap();

        if(rs!=null){
            while(rs.next()){
                resultadoCarrera.put("pole", rs.getString("pole"));
                resultadoCarrera.put("primero", rs.getString("primero"));
                resultadoCarrera.put("segundo", rs.getString("segundo"));
                resultadoCarrera.put("tercero", rs.getString("tercero"));
                resultadoCarrera.put("cuarto", rs.getString("cuarto"));
                resultadoCarrera.put("quinto", rs.getString("quinto"));
                resultadoCarrera.put("sexto", rs.getString("sexto"));
                resultadoCarrera.put("septimo", rs.getString("septimo"));
                resultadoCarrera.put("octavo", rs.getString("octavo"));
                resultadoCarrera.put("noveno", rs.getString("noveno"));
                resultadoCarrera.put("decimo", rs.getString("decimo"));
            }
        }
        bbdd.cerrarConexion(conexion);

        return resultadoCarrera;
    }

    public String getNombreCarreraSQL(String carrera) throws SQLException {
        System.out.println(this.getClass().getName()+".getNombreCarreraSQL()");
        BaseDeDatos bbdd = new BaseDeDatos();
        Connection conexion = bbdd.establecerConexion();
        String query="SELECT nombre FROM carreras WHERE identificador="+carrera+"";

        Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery (query);

        String nombreCarrera = "";

        if(rs!=null){
            while(rs.next()){
                nombreCarrera=rs.getString("nombre");
            }
        }
        bbdd.cerrarConexion(conexion);

        return nombreCarrera;
    }
    public String getNombreUsuarioSQL(String usuario) throws SQLException {
        System.out.println(this.getClass().getName()+".getNombreCarreraSQL()");
        BaseDeDatos bbdd = new BaseDeDatos();
        Connection conexion = bbdd.establecerConexion();
        String query="SELECT nombre FROM usuarios WHERE nick='"+usuario+"'";

        Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery (query);

        String nombreCarrera = "";

        if(rs!=null){
            while(rs.next()){
                nombreCarrera=rs.getString("nombre");
            }
        }
        bbdd.cerrarConexion(conexion);

        return nombreCarrera;
    }
    
    public HashMap getListaPilotosSQL() throws SQLException {
        System.out.println(this.getClass().getName()+".getListaPilotosSQL()");
        BaseDeDatos bbdd = new BaseDeDatos();
        Connection conexion = bbdd.establecerConexion();
        String query="SELECT numero, nombre FROM pilotos";

        Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery (query);

        HashMap listaPilotos = new HashMap();

        if(rs!=null){
            while(rs.next()){
                listaPilotos.put(rs.getString("numero"), rs.getString("nombre"));
            }
        }
        bbdd.cerrarConexion(conexion);

        return listaPilotos;
    }
}
