package com.formula1.manejadoras;

import com.formula1.comunes.AccesosBBDD;
import com.formula1.comunes.DatosPantalla;
import com.formula1.comunes.DatosPersona;
import com.formula1.comunes.PantallaWeb;
import java.io.IOException;
import java.sql.SQLException;
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
        if(accion.equals("login")){

            String usuario = request.getParameter("usuario_login");
            if(usuario==null) usuario="";
            String clave = request.getParameter("pass_login_encrpt");
            if(clave==null) clave="";

            HashMap consulta=new HashMap();
            try {
                consulta = AccesosBBDD.getDatosUsuarioSQL(usuario);
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
                session.setAttribute("datosPersona",datosPersona);

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
                request.setAttribute("ERROR", "Ha ocurrido un error al intentar conectarte.<BR />Por favor comprueba tu usuario y contraseña y vuelve a intentarlo.");
            }

        }else{
            HttpSession session = request.getSession();
            session.invalidate();
            request.setAttribute("hayUsuarioSesion", "N");
        }
        
        return request;
    }

}
