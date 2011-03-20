package com.formula1.manejadoras;

import com.formula1.comunes.BaseDeDatos;
import com.formula1.comunes.DatosPantalla;
import com.formula1.comunes.DatosPersona;
import com.formula1.comunes.PantallaWeb;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Mato
 */
public class ProximaApuestaHandler implements PantallaWeb{

    public HttpServletRequest processRequest(HttpServletRequest request, HttpServletResponse response, DatosPantalla datosPantalla) {
        System.out.println(this.getClass().getName());
        
        HttpSession session = request.getSession();
        DatosPersona datosPersona = (DatosPersona) session.getAttribute("datosPersona");
        String usuarioSession=datosPersona.getNick();        

        String accion = request.getParameter("accion");
        if(accion==null) accion="";

        long milisegAhora=0;
        long milisegCierre=0;

        ArrayList pilotos = new ArrayList();
        HashMap proximaCarreaDatos = new HashMap();

        try{
        pilotos = getDatosPilotosSQL();
        request.setAttribute("pilotos", pilotos);
        proximaCarreaDatos=getDatosProximaCarreraSQL();
        request.setAttribute("datosCarrera", proximaCarreaDatos);

        String cierre=(String)proximaCarreaDatos.get("fecha_cierre_apuestas");
            if(cierre==null) cierre="";

            SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Date dCierre;
            try {
                dCierre=sdf.parse(cierre);
                milisegAhora=System.currentTimeMillis();
                milisegCierre=dCierre.getTime();

                System.out.println("Fecha y hora actual: "+milisegAhora);
                System.out.println("Fecha y hora cierre: "+milisegCierre);

                if(milisegAhora>milisegCierre){
                    request.setAttribute("permiteGuardar", "N");
                }else{
                    request.setAttribute("permiteGuardar", "S");
                }
            } catch (ParseException ex) {
                request.setAttribute("permiteGuardar", "N");
            }
        } catch (SQLException ex) {
            System.out.println("Error al recuperar los pilotos o recuperar lo datos de la carrera.");
            datosPantalla.setJsp("./error.jsp");
            datosPantalla.setTitulo("Error");
            request.setAttribute("ERROR", "Ha ocurrido un error al consultar los datos para la próxima carrera. Por favor avisa al administrador.");
        }
        
        if(accion.equals("alta")){
            if(milisegAhora<=milisegCierre)
                try {
                    altaApuestaProxCarreraSQL(request,usuarioSession,datosPantalla);
                    request.setAttribute("indAviso", "S");
                } catch (SQLException ex) {
                    System.out.println("Error al dar de alta una apuesta.");
                    datosPantalla.setJsp("./error.jsp");
                    datosPantalla.setTitulo("Error");
                    request.setAttribute("ERROR", "Ha ocurrido un error al guardar tu apuesta. Por favor avisa al administrador.");
                }
            else{
                System.out.println("Error al dar de alta una apuesta. Fuera de hora");
                datosPantalla.setJsp("./error.jsp");
                datosPantalla.setTitulo("Error");
                request.setAttribute("ERROR", "Lo siento las apuestas están cerradas porque ya pasó la hora de cierre. La próxima vez no apures tanto.");
            }
        }

        HashMap proximaCarreraApuesta = new HashMap();
        try {
            proximaCarreraApuesta=getApuestaProximaCarreraSQL(usuarioSession);
            request.setAttribute("datosApuesta", proximaCarreraApuesta);
        } catch (SQLException ex) {
            System.out.println("Error al recuperar los pilotos o recuperar lo datos de la carrera.");
            datosPantalla.setJsp("./error.jsp");
            datosPantalla.setTitulo("Error");
            request.setAttribute("ERROR", "Ha ocurrido un error al consultar los datos para la próxima carrera. Por favor avisa al administrador.");
        }

        return request;
    }
    public boolean validarPosiciones(HttpServletRequest request){
        boolean correcto=true;

        ArrayList posiciones = new ArrayList();
        
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

        posiciones.add(primero);
        posiciones.add(segundo);
        posiciones.add(tercero);
        posiciones.add(cuarto);
        posiciones.add(quinto);
        posiciones.add(sexto);
        posiciones.add(septimo);
        posiciones.add(octavo);
        posiciones.add(noveno);
        posiciones.add(decimo);

        for(int i=0; i<posiciones.size();i++)
            for(int j=i+1; j<posiciones.size(); j++)
                if(posiciones.get(i).equals(posiciones.get(j)))
                    correcto=false;
        
        return correcto;
    }
    public void altaApuestaProxCarreraSQL(HttpServletRequest request, String usuarioSession, DatosPantalla datosPantalla) throws SQLException {
        System.out.println(this.getClass().getName()+".altaApuestaProxCarreraSQL()");
        BaseDeDatos bbdd = new BaseDeDatos();
        Connection conexion = bbdd.establecerConexion();
        String usuario=usuarioSession;
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

        if(usuario==null) usuario="";
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

        if(validarPosiciones(request)){
            String query="INSERT INTO apuestas (usuario,pole,primero,segundo,tercero,cuarto,quinto,sexto,septimo,octavo,noveno,decimo) "
                    + "VALUES ('"+usuario+"', '"+pole+"', '"+primero+"', '"+segundo+"', '"+tercero+"', '"+cuarto+"', '"+quinto+"', '"+sexto+"', '"+septimo+"', '"+octavo+"', '"+noveno+"', '"+decimo+"') "
                    + "ON DUPLICATE KEY "
                    + "UPDATE usuario='"+usuario+"', pole='"+pole+"', primero='"+primero+"', segundo='"+segundo+"', tercero='"+tercero+"', cuarto='"+cuarto+"', quinto='"+quinto+"', sexto='"+sexto+"', septimo='"+septimo+"', octavo='"+octavo+"', noveno='"+noveno+"', decimo='"+decimo+"'";
            Statement s = conexion.createStatement();
            int i = s.executeUpdate(query);
        }else{
            System.out.println("Error al dar de alta una apuesta.");
            datosPantalla.setJsp("./error.jsp");
            datosPantalla.setTitulo("Error");
            request.setAttribute("ERROR", "Hay pilotos repetidos entre las 10 primeras posiciones, revísalo e inténtalo de nuevo.");
        }
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

    public HashMap getDatosProximaCarreraSQL() throws SQLException {
        System.out.println(this.getClass().getName()+".getDatosProximaCarreraSQL()");
        BaseDeDatos bbdd = new BaseDeDatos();
        Connection conexion = bbdd.establecerConexion();

        Calendar c = Calendar.getInstance();
        String dia = Integer.toString(c.get(Calendar.DATE));
        String mes = Integer.toString(c.get(Calendar.MONTH)+1);
        String annio = Integer.toString(c.get(Calendar.YEAR));

        String fecha_hoy=annio+"-"+mes+"-"+dia;
        String query="SELECT * FROM carreras WHERE fecha_carrera = (SELECT min(fecha_carrera)FROM carreras WHERE fecha_carrera > '"+fecha_hoy+"')";

        Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery (query);

        HashMap registroRecuperado=new HashMap();

        if(rs!=null){
                rs.next();
                
                String identificador_carrera=rs.getString("identificador");
                if(identificador_carrera==null)identificador_carrera="";
                String nombre = rs.getString("nombre");
                if(nombre==null)nombre="";
                String fecha_carrera = rs.getString("fecha_carrera");
                if(fecha_carrera==null)fecha_carrera="";
                String fecha_cierre_apuestas = rs.getString("fecha_cierre_apuestas");
                if(fecha_cierre_apuestas==null)fecha_cierre_apuestas="";

                registroRecuperado.put("identificador", identificador_carrera);
                registroRecuperado.put("nombre", nombre);
                registroRecuperado.put("fecha_carrera", fecha_carrera);
                registroRecuperado.put("fecha_cierre_apuestas", fecha_cierre_apuestas);

                String fecha_carrera_formateada=fecha_carrera.substring(8)+" de "+getNombreMes(fecha_carrera.substring(5,7))+".";
                String fecha_cierre_apuestas_formateada=fecha_cierre_apuestas.substring(8,10)+" de "+getNombreMes(fecha_cierre_apuestas.substring(5,7))+" a las "+fecha_cierre_apuestas.substring(11,16);

                registroRecuperado.put("fecha_carrera_formateada", fecha_carrera_formateada);
                registroRecuperado.put("fecha_cierre_apuestas_formateada", fecha_cierre_apuestas_formateada);
        }

        return registroRecuperado;
    }

    public HashMap getApuestaProximaCarreraSQL(String usuario) throws SQLException {
        System.out.println(this.getClass().getName()+".getApuestaProximaCarreraSQL()");
        BaseDeDatos bbdd = new BaseDeDatos();
        Connection conexion = bbdd.establecerConexion();

        String query="SELECT * FROM apuestas WHERE usuario = '"+usuario+"'";

        Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery (query);

        HashMap registroRecuperado=new HashMap();

        if(rs!=null){
                while(rs.next()){
                registroRecuperado.put("pole", rs.getString("pole"));
                registroRecuperado.put("primero", rs.getString("primero"));
                registroRecuperado.put("segundo", rs.getString("segundo"));
                registroRecuperado.put("tercero", rs.getString("tercero"));
                registroRecuperado.put("cuarto", rs.getString("cuarto"));
                registroRecuperado.put("quinto", rs.getString("quinto"));
                registroRecuperado.put("sexto", rs.getString("sexto"));
                registroRecuperado.put("septimo", rs.getString("septimo"));
                registroRecuperado.put("octavo", rs.getString("octavo"));
                registroRecuperado.put("noveno", rs.getString("noveno"));
                registroRecuperado.put("decimo", rs.getString("decimo"));
            }
        }

        return registroRecuperado;
    }

    public String getNombreMes(String mes){
        System.out.println(this.getClass().getName()+".getNombreMes()");
        String nombreMes="";

        if(mes.equals("1") || mes.equals("01")){
		nombreMes="Enero";
	}else if(mes.equals("2") || mes.equals("02")){
		nombreMes="Febrero";
	}else if(mes.equals("3") || mes.equals("03")){
		nombreMes="Marzo";
	}else if(mes.equals("4") || mes.equals("04")){
		nombreMes="Abril";
	}else if(mes.equals("5") || mes.equals("05")){
		nombreMes="Mayo";
	}else if(mes.equals("6") || mes.equals("06")){
		nombreMes="Junio";
	}else if(mes.equals("7") || mes.equals("07")){
		nombreMes="Julio";
	}else if(mes.equals("8") || mes.equals("08")){
		nombreMes="Agosto";
	}else if(mes.equals("9") || mes.equals("09")){
		nombreMes="Septiembre";
	}else if(mes.equals("10")){
		nombreMes="Octubre";
	}else if(mes.equals("11")){
		nombreMes="Noviembre";
	}else if(mes.equals("12")){
		nombreMes="Diciembre";
	}

        return nombreMes;
    }
}
