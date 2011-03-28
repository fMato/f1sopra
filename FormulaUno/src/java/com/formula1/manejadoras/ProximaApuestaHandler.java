package com.formula1.manejadoras;

import com.formula1.comunes.AccesosBBDD;
import com.formula1.comunes.DatosPantalla;
import com.formula1.comunes.DatosPersona;
import com.formula1.comunes.PantallaWeb;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
        pilotos = AccesosBBDD.getDatosPilotosSQL("ACTIVO");
        request.setAttribute("pilotos", pilotos);
        proximaCarreaDatos=AccesosBBDD.getDatosProximaCarreraSQL();
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
            request.setAttribute("ERROR", "Ha ocurrido un error al consultar los datos para la próxima carrera.<BR />Por favor avisa al administrador.");
        }
        
        if(accion.equals("alta")){
            if(milisegAhora<=milisegCierre)
                try {
                    altaApuestaProxCarrera(request,usuarioSession,datosPantalla);
                    request.setAttribute("indAviso", "S");
                } catch (SQLException ex) {
                    System.out.println("Error al dar de alta una apuesta.");
                    datosPantalla.setJsp("./error.jsp");
                    datosPantalla.setTitulo("Error");
                    request.setAttribute("ERROR", "Ha ocurrido un error al guardar tu apuesta.<BR />Por favor avisa al administrador.");
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
            proximaCarreraApuesta=AccesosBBDD.getApuestaProximaCarreraSQL(usuarioSession);
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
    public void altaApuestaProxCarrera(HttpServletRequest request, String usuarioSession, DatosPantalla datosPantalla) throws SQLException {
        System.out.println(this.getClass().getName()+".altaApuestaProxCarrera()");
        
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
            
            AccesosBBDD.altaApuestaProxCarreraSQL(usuario, pole, primero, segundo, tercero, cuarto, quinto, sexto, septimo, octavo, noveno, decimo);
            
        }else{
            System.out.println("Error al dar de alta una apuesta.");
            datosPantalla.setJsp("./error.jsp");
            datosPantalla.setTitulo("Error");
            request.setAttribute("ERROR", "Hay pilotos repetidos entre las 10 primeras posiciones, revísalo e inténtalo de nuevo.");
        }
    }
    
}
