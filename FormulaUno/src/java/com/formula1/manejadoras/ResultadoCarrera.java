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
                guardarResultadoCarreraSQL(request);
                mensajeConfirm+="Carrera guardada";
                String indicadorContabilizacion=request.getParameter("chCont");
                if(indicadorContabilizacion==null) indicadorContabilizacion="";
                if(indicadorContabilizacion.equals("S")){
                    System.out.println("Lanzamos clase de contabilización.");
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
            
            pilotos = getDatosPilotosSQL();
            request.setAttribute("pilotos", pilotos);
            ultimaCarreraDisputada=getCarreraAnteriorSQL();
            request.setAttribute("ultimaCarreraDisputada", ultimaCarreraDisputada);
            carreras=getDatosCarrerasSQL();
            request.setAttribute("carreras", carreras);

        } catch (SQLException ex) {
            System.out.println("Error en las consultas de la pantalla para introducir el resultado de la carrera.");
            datosPantalla.setJsp("./error.jsp");
            datosPantalla.setTitulo("Error");
            request.setAttribute("ERROR", "Error al recuperar la lista de pilotos... o la de carreras... o algo. Ups!!");
        }

        return request;
    }

    public ArrayList getDatosPilotosSQL() throws SQLException {
        System.out.println(this.getClass().getName()+".getDatosPilotosSQL()");
        BaseDeDatos bbdd = new BaseDeDatos();
        Connection conexion = bbdd.establecerConexion();
        String query="SELECT * FROM pilotos WHERE estado='ACTIVO' ORDER BY numero";

        Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery (query);

        ArrayList datosRecuperados = new ArrayList();

        if(rs!=null){
            while(rs.next()){
                HashMap registroRecuperado=new HashMap();
                registroRecuperado.put("numero", rs.getString("numero"));
                registroRecuperado.put("nombre", rs.getString("nombre"));
                datosRecuperados.add(registroRecuperado);
            }
        }

        return datosRecuperados;
    }

    public ArrayList getDatosCarrerasSQL() throws SQLException {
        System.out.println(this.getClass().getName()+".getDatosCarrerasSQL()");
        BaseDeDatos bbdd = new BaseDeDatos();
        Connection conexion = bbdd.establecerConexion();
        String query="SELECT identificador, nombre FROM carreras ORDER BY identificador ASC";

        Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery (query);

        ArrayList datosRecuperados = new ArrayList();

        if(rs!=null){
            while(rs.next()){
                HashMap registroRecuperado=new HashMap();
                registroRecuperado.put("identificador", rs.getString("identificador"));
                registroRecuperado.put("nombre", rs.getString("nombre"));
                datosRecuperados.add(registroRecuperado);
            }
        }

        return datosRecuperados;
    }

    public String getCarreraAnteriorSQL() throws SQLException {
        System.out.println(this.getClass().getName()+".getCarreraAnteriorSQL()");
        BaseDeDatos bbdd = new BaseDeDatos();
        Connection conexion = bbdd.establecerConexion();

        Calendar c = Calendar.getInstance();
        String dia = Integer.toString(c.get(Calendar.DATE));
        String mes = Integer.toString(c.get(Calendar.MONTH)+1);
        String annio = Integer.toString(c.get(Calendar.YEAR));

        String fecha_hoy=annio+"-"+mes+"-"+dia;
        String query="SELECT * FROM carreras WHERE fecha_carrera = (SELECT max(fecha_carrera)FROM carreras WHERE fecha_carrera <= '"+fecha_hoy+"')";

        Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery (query);
        String ultimaCarreraDisputada="";

        if(rs!=null){
                while(rs.next()){
                    ultimaCarreraDisputada=rs.getString("identificador");
                    if(ultimaCarreraDisputada==null)ultimaCarreraDisputada="";
                }
        }

        return ultimaCarreraDisputada;
    }

    public int guardarResultadoCarreraSQL(HttpServletRequest request) throws SQLException {
        System.out.println(this.getClass().getName()+".guardarResultadoCarreraSQL()");
        BaseDeDatos bbdd = new BaseDeDatos();
        Connection conexion = bbdd.establecerConexion();

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

        String query="INSERT INTO resultado_real_carreras (identificador,pole,primero,segundo,tercero,cuarto,quinto,sexto,septimo,octavo,noveno,decimo) "
                + "VALUES ("+carrera+", '"+pole+"', '"+primero+"', '"+segundo+"', '"+tercero+"', '"+cuarto+"', '"+quinto+"', '"+sexto+"', '"+septimo+"', '"+octavo+"', '"+noveno+"', '"+decimo+"') "
                + "ON DUPLICATE KEY "
                + "UPDATE identificador="+carrera+", pole='"+pole+"', primero='"+primero+"', segundo='"+segundo+"', tercero='"+tercero+"', cuarto='"+cuarto+"', quinto='"+quinto+"', sexto='"+sexto+"', septimo='"+septimo+"', octavo='"+octavo+"', noveno='"+noveno+"', decimo='"+decimo+"'";
        
        Statement s = conexion.createStatement();
        int i = s.executeUpdate(query);

        return i;
    }
}
