package com.formula1.manejadoras;

import com.formula1.comunes.BaseDeDatos;
import com.formula1.comunes.DatosPantalla;
import com.formula1.comunes.DatosPersona;
import com.formula1.comunes.PantallaWeb;
import java.io.IOException;
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
public class LoginHandler implements PantallaWeb{

    public HttpServletRequest processRequest(HttpServletRequest request, HttpServletResponse response, DatosPantalla datosPantalla) {
        System.out.println(this.getClass().getName());

        String accion=request.getParameter("accion");
        if(accion==null)accion="";
        System.out.println("Accion: "+accion);
        if(accion.equals("login")){

            String usuario = request.getParameter("usuario_login");
            if(usuario==null) usuario="";
            String clave = request.getParameter("pass_login_encrpt");
            if(clave==null) clave="";

            HashMap consulta=new HashMap();
            try {
                consulta = getDatosUsuarioSQL(usuario);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            String clave_recuperada=(String)consulta.get("pass");
            if(clave_recuperada==null)clave_recuperada="";
            HttpSession session = request.getSession();
            if(clave.equals(clave_recuperada) && !clave.equals("") && !clave_recuperada.equals("")){
                session.setAttribute("hayUsuarioSesion","S");
                System.out.println("Login Ok para el usuario "+(String)consulta.get("nick"));

                // Guardamos en sesión los datos del usuario conectado.
                String nick_recuperado=(String)consulta.get("nick");
                if(nick_recuperado==null)nick_recuperado="";
                String nombre_recuperado=(String)consulta.get("nombre");
                if(nombre_recuperado==null)nombre_recuperado="";
                String tipo_usuario_recuperado=(String)consulta.get("tipo");
                if(tipo_usuario_recuperado==null)tipo_usuario_recuperado="";
                String correo_recuperado=(String)consulta.get("correo");
                if(correo_recuperado==null)correo_recuperado="";

                DatosPersona datosPersona = new DatosPersona(nick_recuperado, nombre_recuperado, tipo_usuario_recuperado, correo_recuperado);
                System.out.println("Nick: "+datosPersona.getNick());
                session.setAttribute("datosPersona",datosPersona);

                DatosPersona datosPersona2 = (DatosPersona) session.getAttribute("datosPersona");
                if(datosPersona2==null){
                    System.out.println("El datosPersona guardado en sesion es nulo.");
                }else{
                    System.out.println("El datosPersona guardado en sesion NO es nulo.");
                    System.out.println("Tipo de usuario recuperado de sesion: "+datosPersona2.getTipoUsuario());
                }


                try {
                    long antiCache=System.currentTimeMillis();
                    response.sendRedirect("./inicio.f1?antiCache="+antiCache);
                } catch (IOException ex) {
                    System.out.println("Error al redireccionar después de conexión.");
                    ex.printStackTrace();
                }
            }
            else{
                System.out.println("Login ERRONEO para el usuario "+(String)consulta.get("nick"));
                session.setAttribute("hayUsuarioSesion","N");
                session.invalidate();
                datosPantalla.setJsp("./error.jsp");
                datosPantalla.setTitulo("Error");
                request.setAttribute("ERROR", "Ha ocurrido un error al intentar conectarte. Por favor comprueba tu usuario y contraseña y vuelve a intentarlo.");
            }

        }else{
            HttpSession session = request.getSession();
            session.invalidate();
            request.setAttribute("hayUsuarioSesion", "N");
        }
        
        return request;
    }

    public HashMap getDatosUsuarioSQL(String usuario) throws SQLException {
        System.out.println(this.getClass().getName()+".getDatosUsuarioSQL()");
        BaseDeDatos bbdd = new BaseDeDatos();
        Connection conexion = bbdd.establecerConexion();
        System.out.println("Conexión con la base de datos ok.");
        String query="SELECT * FROM usuarios WHERE nick='"+usuario+"'";

        Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery (query);

        HashMap datosRecuperados=new HashMap();

        if(rs!=null){
            rs.next();
            datosRecuperados.put("nick", rs.getString("nick"));
            datosRecuperados.put("pass", rs.getString("pass"));
            datosRecuperados.put("nombre", rs.getString("nombre"));
            datosRecuperados.put("tipo", rs.getString("tipo_usuario"));
            datosRecuperados.put("correo", rs.getString("correo"));
            System.out.println("Nick: "+rs.getString("nick"));
            System.out.println("Nombre: "+rs.getString("nombre"));
            System.out.println("Tipo: "+rs.getString("tipo_usuario"));

        }

        return datosRecuperados;
    }

}
