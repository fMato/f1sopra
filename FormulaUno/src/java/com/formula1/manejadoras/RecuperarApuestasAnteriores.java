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
 * @author fMato
 */
public class RecuperarApuestasAnteriores implements PantallaWeb{

    private ArrayList listaUsuarios=new ArrayList();
    private ArrayList nombresUsuarios=new ArrayList();
    
    public HttpServletRequest processRequest(HttpServletRequest request, HttpServletResponse response, DatosPantalla datosPantalla) {
        System.out.println(this.getClass().getName());
        HashMap datosClasifAnteriores = new HashMap();

        try {

            datosClasifAnteriores=getApuestasAnterioresSQL();
            getUsuariosSQL();

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

    public HashMap getApuestasAnterioresSQL() throws SQLException {
        System.out.println(this.getClass().getName()+".getApuestasAnterioresSQL()");
        BaseDeDatos bbdd = new BaseDeDatos();
        Connection conexion = bbdd.establecerConexion();
        String query="SELECT a.usuario, b.nombre, a.carrera FROM apuestas_carreras a, usuarios b WHERE a.usuario=b.nick ORDER BY b.nombre, a.carrera";

        Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery (query);

        HashMap porUsuarios = new HashMap();
        HashMap unUsuario = new HashMap();
        
        if(rs!=null){
            String usuarioAnterior="";
            while(rs.next()){
                String usuarioActual=rs.getString("usuario");
                String nombre=rs.getString("nombre");
                String carrera=rs.getString("carrera");

                if(!usuarioAnterior.equals(usuarioActual)){
                    unUsuario = new HashMap();
                    unUsuario.put("nombre", nombre);
                    unUsuario.put(carrera,"S");
                    porUsuarios.put(usuarioActual, unUsuario);
                }else{
                    unUsuario.put(carrera,"S");
                    porUsuarios.put(usuarioActual, unUsuario);
                }
                usuarioAnterior=usuarioActual;
            }
        }
        bbdd.cerrarConexion(conexion);

        return porUsuarios;
    }

    public void getUsuariosSQL() throws SQLException {
        System.out.println(this.getClass().getName()+".getUsuariosSQL()");
        BaseDeDatos bbdd = new BaseDeDatos();
        Connection conexion = bbdd.establecerConexion();
        String query="SELECT nick, nombre FROM usuarios ORDER BY nombre ASC";

        Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery (query);

        if(rs!=null){
            while(rs.next()){
                listaUsuarios.add(rs.getString("nick"));
                nombresUsuarios.add(rs.getString("nombre"));
            }
        }
        bbdd.cerrarConexion(conexion);
    }
}
