/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.formula1.manejadoras;

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

        HttpSession session = request.getSession();
        DatosPersona datosPersona = (DatosPersona) session.getAttribute("datosPersona");
        String usuarioSession=datosPersona.getNick();

        HashMap datosUsuario = new HashMap();
        try {
            datosUsuario = getDatosUsuarioSQL(usuarioSession);
        } catch (SQLException ex) {
            System.out.println("Error al recuperar los datos de "+usuarioSession+".");
            datosPantalla.setJsp("./error.jsp");
            datosPantalla.setTitulo("Error");
            request.setAttribute("ERROR", "No se han podido recuperar tus datos, por favor ponte en contacto con el administrador.");
        }

        request.setAttribute("datosUsuario", datosUsuario);

        return request;
    }

    public HashMap getDatosUsuarioSQL(String nick) throws SQLException {
        System.out.println(this.getClass().getName()+".getDatosUsuarioSQL()");
        BaseDeDatos bbdd = new BaseDeDatos();
        Connection conexion = bbdd.establecerConexion();
        String query="SELECT * FROM usuarios WHERE nick='"+nick+"'";

        Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery (query);

        HashMap datosUsuario = new HashMap();

        if(rs!=null){
            while(rs.next()){
                datosUsuario.put("nick", rs.getString("nick"));
                datosUsuario.put("nombre", rs.getString("nombre"));
                datosUsuario.put("correo", rs.getString("correo"));
                datosUsuario.put("pass", rs.getString("pass"));
            }
        }
        bbdd.cerrarConexion(conexion);

        return datosUsuario;
    }
}
