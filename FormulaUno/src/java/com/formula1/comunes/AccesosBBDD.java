/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.formula1.comunes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * 
 * @author Mato
 */
public class AccesosBBDD {

    /**
     * Devuelve las opciones de menú dependiendo del tipo de usuario que las solicite.
     * 
     * @param tipoUsuario
     * @return ArrayList que contiene en cada posición un HashMap con la información de una opción de menú. Cada HashMap de opción de menú contiene dos registros, el texto de la opción de menú (key: texto) y la url a la que debe ir (key: destino).
     */
    public static ArrayList getMenu(String tipoUsuario) throws SQLException{
        System.out.println("Llamada a BBDD: getMenu()");

        HashMap menuItem = new HashMap();
        Connection conexion = BaseDeDatos.establecerConexion();
        String query="";
        ArrayList opcionesMenu = new ArrayList();
        long antiCache=System.currentTimeMillis();

        if(tipoUsuario.equals("A"))
            query="SELECT destino, texto "
                    + "FROM menu "
                    + "ORDER BY perfil DESC, posicion ASC";
        else
            query="SELECT destino, texto "
                    + "FROM f1Sopra.menu "
                    + "WHERE perfil=? "
                    + "ORDER BY perfil DESC, posicion ASC";

        PreparedStatement selectStatement = conexion.prepareStatement(query);

        if(!tipoUsuario.equals("A"))
            selectStatement.setString(1, tipoUsuario);

        ResultSet rs = selectStatement.executeQuery ();

        if(rs!=null){
            while(rs.next()){
                menuItem=new HashMap();
                
                String textoRecuperado=rs.getString("texto");
                if(textoRecuperado==null)textoRecuperado="";
                String destinoRecuperado=rs.getString("destino");
                if(destinoRecuperado==null) destinoRecuperado="";

                menuItem.put("texto", textoRecuperado);
                menuItem.put("url", "./"+destinoRecuperado+"?antiCache="+antiCache);

                opcionesMenu.add(menuItem);
            }
        }

        BaseDeDatos.cerrarConexion(conexion);
        
        return opcionesMenu;
    }

    /**
     * Guarda en base de datos todos los datos de un nuevo usuario (el tipo de usuario por defecto va a U).
     * 
     * @param nick
     * @param nombre
     * @param correo
     * @param pass
     * @throws SQLException
     */
    public static void altaUsuarioSQL(String nick, String nombre, String correo, String pass) throws SQLException {
        System.out.println("Llamada a BBDD: altaUsuarioSQL()");

        Connection conexion = BaseDeDatos.establecerConexion();
        String query="";

        query="INSERT INTO usuarios(nick,pass,nombre,tipo_usuario,correo) "
                + "VALUES(?,?,?,?,?)";

        PreparedStatement selectStatement = conexion.prepareStatement(query);

        selectStatement.setString(1, nick);
        selectStatement.setString(2, pass);
        selectStatement.setString(3, nombre);
        selectStatement.setString(4, "U");
        selectStatement.setString(5, correo);

        selectStatement.executeUpdate();

        BaseDeDatos.cerrarConexion(conexion);
    }

    /**
     * Recupera el número de puntos que ha obtenido el ganador de cada carrera disputada.
     * 
     * @return HashMap con un registro por cada carrera disputada que contiene los puntos que obtuvo el ganador de esa carrera (key: número de la carrera).
     * @throws SQLException
     */
    public static HashMap getPuntosMaximosSQL() throws SQLException {
        System.out.println("Llamada a BBDD: getPuntosMaximosSQL()");
        
        BaseDeDatos bbdd = new BaseDeDatos();
        Connection conexion = BaseDeDatos.establecerConexion();
        String query="SELECT carrera, MAX(puntos) AS puntos "
                + "FROM resultados_apuestas "
                + "GROUP BY carrera";

        Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery (query);

        HashMap puntosMaximos = new HashMap();
        if(rs!=null){
            while(rs.next()){
                puntosMaximos.put(rs.getString("carrera"), rs.getString("puntos"));
            }
        }

        BaseDeDatos.cerrarConexion(conexion);
        
        return puntosMaximos;
    }

    /**
     * Devuelve un HashMap con una lista con los usuarios ordenados según sus puntos y además los puntos totales de cada usuario.
     * 
     * @return HashMap que devuevle un registro con la lista de usuarios ordenador por puntos (key: ListaUsuariosOrdenados) y un registro por cada usuario con los puntos totales del usuario (key: nick del usuario).
     * @throws SQLException
     */
    public static HashMap getPuntosTotalesSQL() throws SQLException {
        System.out.println("Llamada a BBDD: getPuntosTotalesSQL()");

        ArrayList usuariosOrdenados=new ArrayList();
        Connection conexion = BaseDeDatos.establecerConexion();
        String query="SELECT usuario, SUM(puntos) as puntos_totales "
                + "FROM resultados_apuestas "
                + "GROUP BY usuario "
                + "ORDER BY puntos_totales DESC, usuario ASC";

        Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery (query);

        HashMap resultado = new HashMap();
        if(rs!=null){
            while(rs.next()){
                resultado.put(rs.getString("usuario"), rs.getString("puntos_totales"));
                usuariosOrdenados.add(rs.getString("usuario"));
            }
        }
        resultado.put("ListaUsuariosOrdenados", usuariosOrdenados);

        BaseDeDatos.cerrarConexion(conexion);
        
        return resultado;
    }

    /**
     * Devuelve los datos de cada usuario para cada una de las carreras y el total de puntos que lleva en el campeonato
     * 
     * @param puntosTotales
     * @return HashMap cuya key es el nombre de usuario y que a su vez contiene un HashMap por usuario que contiene el nombre real del usuario (key: nombre), el número total de puntos del usuario (key: total) y el número de puntos obtenidos en cada carrera disputada (key: el número de carrera).
     * @throws SQLException
     */
    public static HashMap getPuntosCarrerasSQL(HashMap puntosTotales) throws SQLException {
        System.out.println("Llamada a BBDD: getPuntosCarrerasSQL()");

        Connection conexion = BaseDeDatos.establecerConexion();
        String query="SELECT a.usuario, a.carrera, a.puntos, b.nombre "
                + "FROM resultados_apuestas a, usuarios b "
                + "WHERE a.usuario=b.nick "
                + "ORDER BY a.usuario, a.carrera";

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

        BaseDeDatos.cerrarConexion(conexion);
        
        return resultadosTodosUsuarios;
    }

    /**
     * Devuelve la fecha de la última actualización de la tabla apuestas por parte de cada usuario.
     * 
     * @return
     * @throws SQLException
     */
    public static HashMap getActividadApuestasSQL() throws SQLException {
        System.out.println("Llamada a BBDD: getActividadApuestasSQL()");

        Connection conexion = BaseDeDatos.establecerConexion();
        ArrayList nombreUsuario = new ArrayList();
        ArrayList actividadUsuario = new ArrayList();
        ArrayList colorCelda = new ArrayList();
        HashMap resultado=new HashMap();

        String query="SELECT a.nombre, b.fecha_modif "
                + "FROM usuarios a LEFT JOIN apuestas b ON (a.nick=b.usuario) "
                + "ORDER BY b.fecha_modif ASC";

        Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery (query);

        if(rs!=null){
            while(rs.next()){
                nombreUsuario.add(rs.getString("nombre"));
                String registro_fecha="";
                Date fecha=rs.getDate("fecha_modif");
                long milisegFecha=0;
                long milisegAhora=System.currentTimeMillis();
                if(fecha!=null){
                    milisegFecha=fecha.getTime();
                    long milisegDif=milisegAhora-milisegFecha;
                    System.out.println("Milisegundos de diferencia: "+milisegDif);
                    long diasDif=milisegDif/1000/3600/24;
                    registro_fecha="Hace aprox. "+diasDif+" días";
                    if(diasDif<=2)
                         colorCelda.add("#00FF00");
                    else if(diasDif<=4)
                         colorCelda.add("#FFFF00");
                    else if(diasDif<=7)
                         colorCelda.add("#FF9900");
                    else
                         colorCelda.add("#FF0000");
                }else{
                    registro_fecha="No hay apuesta.";
                    colorCelda.add("#FF0000");
                }
                actividadUsuario.add(registro_fecha);
            }
        }
        BaseDeDatos.cerrarConexion(conexion);

        resultado.put("nombreUsuario", nombreUsuario);
        resultado.put("actividadUsuario", actividadUsuario);
        resultado.put("colorCelda", colorCelda);

        return resultado;
    }

    /**
     * Devuelve los datos del usuario solicitado.
     *
     * @param usuario
     * @return HashMap con los datos de usuario, identificador del usuario (key: nick), contraseña encriptada (key: pass), nombre real del usuario (key: nombre), tipo de usuario (key: tipo) y correo electrónico (key: correo).
     * @throws SQLException
     */
    public static HashMap getDatosUsuarioSQL(String usuario) throws SQLException {
        System.out.println("Llamada a BBDD: getDatosUsuarioSQL()");

        Connection conexion = BaseDeDatos.establecerConexion();
        String query="SELECT * "
                + "FROM usuarios "
                + "WHERE nick=?";

        PreparedStatement selectStatement = conexion.prepareStatement(query);

        selectStatement.setString(1, usuario);

        ResultSet rs = selectStatement.executeQuery ();

        HashMap datosRecuperados=new HashMap();

        if(rs!=null){
            rs.next();
            datosRecuperados.put("nick", rs.getString("nick"));
            datosRecuperados.put("pass", rs.getString("pass"));
            datosRecuperados.put("nombre", rs.getString("nombre"));
            datosRecuperados.put("tipo", rs.getString("tipo_usuario"));
            datosRecuperados.put("correo", rs.getString("correo"));
        }

        BaseDeDatos.cerrarConexion(conexion);
        return datosRecuperados;
    }

    /**
     * Actualiza el correo y contraseña del usuario.
     *
     * @param nick
     * @param correo
     * @param pass
     * @return Un entero resultado del update.
     * @throws SQLException
     */
    public static int modificarDatosUsuarioSQL(String nick, String correo, String pass) throws SQLException {
        System.out.println("Llamada a BBDD: modificarDatosUsuarioSQL()");

        Connection conexion = BaseDeDatos.establecerConexion();
        String query="UPDATE usuarios "
                + "SET correo=?, pass=? "
                + "WHERE nick=?";

        PreparedStatement selectStatement = conexion.prepareStatement(query);

        selectStatement.setString(1, correo);
        selectStatement.setString(2, pass);
        selectStatement.setString(3, nick);

        int i = selectStatement.executeUpdate();

        BaseDeDatos.cerrarConexion(conexion);

        return i;
    }

    /**
     * Guarda la apuesta de un usuario para la próxima carrera.
     *
     * @param usuario
     * @param pole
     * @param primero
     * @param segundo
     * @param tercero
     * @param cuarto
     * @param quinto
     * @param sexto
     * @param septimo
     * @param octavo
     * @param noveno
     * @param decimo
     * @return Un entero resultado del insert o update.
     * @throws SQLException
     */
    public static int altaApuestaProxCarreraSQL(String usuario, String pole, String primero, String segundo, String tercero, String cuarto, String quinto, String sexto, String septimo, String octavo, String noveno, String decimo) throws SQLException {
        System.out.println("Llamada a BBDD: altaApuestaProxCarreraSQL()");

        int i=0;

        Connection conexion = BaseDeDatos.establecerConexion();

        String query="INSERT INTO apuestas (usuario,pole,primero,segundo,tercero,cuarto,quinto,sexto,septimo,octavo,noveno,decimo,fecha_modif) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?, NOW()) "
                + "ON DUPLICATE KEY "
                + "UPDATE usuario=?, pole=?, primero=?, segundo=?, tercero=?, cuarto=?, quinto=?, sexto=?, septimo=?, octavo=?, noveno=?, decimo=?, fecha_modif=NOW()";

        PreparedStatement selectStatement = conexion.prepareStatement(query);
        
        selectStatement.setString(1, usuario);
        selectStatement.setString(2, pole);
        selectStatement.setString(3, primero);
        selectStatement.setString(4, segundo);
        selectStatement.setString(5, tercero);
        selectStatement.setString(6, cuarto);
        selectStatement.setString(7, quinto);
        selectStatement.setString(8, sexto);
        selectStatement.setString(9, septimo);
        selectStatement.setString(10, octavo);
        selectStatement.setString(11, noveno);
        selectStatement.setString(12, decimo);
        selectStatement.setString(13, usuario);
        selectStatement.setString(14, pole);
        selectStatement.setString(15, primero);
        selectStatement.setString(16, segundo);
        selectStatement.setString(17, tercero);
        selectStatement.setString(18, cuarto);
        selectStatement.setString(19, quinto);
        selectStatement.setString(20, sexto);
        selectStatement.setString(21, septimo);
        selectStatement.setString(22, octavo);
        selectStatement.setString(23, noveno);
        selectStatement.setString(24, decimo);     
        
        i = selectStatement.executeUpdate();

        BaseDeDatos.cerrarConexion(conexion);

        return i;
    }

    /**
     * Devuelve los pilotos que en la tabla están con el estado indicado. Si estado="TODOS", devuelve todos los pilotos de la tabla.
     * @param estadoPiloto
     * @return ArrayList con un HashMap por cada piloto que contiene los datos de este, número (key: numero), nombre (key: nombre), equipo (key: equipo) y estado (key: estado).
     * @throws SQLException
     */
    public static ArrayList getDatosPilotosSQL(String estadoPiloto) throws SQLException {
        System.out.println("Llamada a BBDD: getDatosPilotosSQL()");

        Connection conexion = BaseDeDatos.establecerConexion();
        String query="";
        
        if(estadoPiloto.equals("TODOS"))
            query="SELECT * "
                + "FROM pilotos "
                + "ORDER BY numero";
        else
            query="SELECT * "
                + "FROM pilotos "
                + "WHERE estado=? "
                + "ORDER BY numero";

        PreparedStatement selectStatement = conexion.prepareStatement(query);

        if(!estadoPiloto.equals("TODOS"))
            selectStatement.setString(1, estadoPiloto);

        ResultSet rs = selectStatement.executeQuery ();

        ArrayList datosRecuperados = new ArrayList();

        if(rs!=null){
            while(rs.next()){
                HashMap registroRecuperado=new HashMap();
                registroRecuperado.put("numero", rs.getString("numero"));
                registroRecuperado.put("nombre", rs.getString("nombre"));
                registroRecuperado.put("equipo", rs.getString("equipo"));
                registroRecuperado.put("estado", rs.getString("estado"));
                datosRecuperados.add(registroRecuperado);
            }
        }

        BaseDeDatos.cerrarConexion(conexion);

        return datosRecuperados;
    }

    /**
     * Devuelve los datos de la próxima carrera que se va a celebrar.
     * 
     * @return
     * @throws SQLException
     */
    public static HashMap getDatosProximaCarreraSQL() throws SQLException {
        System.out.println("Llamada a BBDD: getDatosProximaCarreraSQL()");

        Connection conexion = BaseDeDatos.establecerConexion();

        Calendar c = Calendar.getInstance();
        String dia = Integer.toString(c.get(Calendar.DATE));
        String mes = Integer.toString(c.get(Calendar.MONTH)+1);
        String annio = Integer.toString(c.get(Calendar.YEAR));

        String fecha_hoy=annio+"-"+mes+"-"+dia;
        String query="SELECT * "
                + "FROM carreras "
                + "WHERE fecha_carrera = ("
                + "SELECT min(fecha_carrera)"
                + "FROM carreras "
                + "WHERE fecha_carrera >= ?"
                + ")";

        PreparedStatement selectStatement = conexion.prepareStatement(query);

        selectStatement.setString(1, fecha_hoy);

        ResultSet rs = selectStatement.executeQuery();

        HashMap registroRecuperado=new HashMap();

        if(rs!=null && rs.next()){

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

                String fecha_carrera_formateada=fecha_carrera.substring(8)+" de "+Utils.NombreMeses(fecha_carrera.substring(5,7))+".";
                String fecha_cierre_apuestas_formateada=fecha_cierre_apuestas.substring(8,10)+" de "+Utils.NombreMeses(fecha_cierre_apuestas.substring(5,7))+" a las "+fecha_cierre_apuestas.substring(11,16);

                registroRecuperado.put("fecha_carrera_formateada", fecha_carrera_formateada);
                registroRecuperado.put("fecha_cierre_apuestas_formateada", fecha_cierre_apuestas_formateada);
        }

        BaseDeDatos.cerrarConexion(conexion);

        return registroRecuperado;
    }

    /**
     * Devuelve la apuesta del usuario para la próxima carrera.
     * @param usuario
     * @return
     * @throws SQLException
     */
    public static HashMap getApuestaProximaCarreraSQL(String usuario) throws SQLException {
        System.out.println("Llamada a BBDD: getApuestaProximaCarreraSQL()");

        Connection conexion = BaseDeDatos.establecerConexion();

        String query="SELECT * "
                + "FROM apuestas "
                + "WHERE usuario = ?";

        PreparedStatement selectStatement = conexion.prepareStatement(query);

        selectStatement.setString(1, usuario);

        ResultSet rs = selectStatement.executeQuery ();

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

        BaseDeDatos.cerrarConexion(conexion);
        
        return registroRecuperado;
    }

    /**
     * Devuelve el la relación numero-nombre de los pilotos.
     * @return HashMap con el numero de piloto como key y el nombre del piloto como valor.
     * @throws SQLException
     */
    public static HashMap getListaNombrePilotosSQL() throws SQLException {
        System.out.println("Llamada a BBDD: getListaNombrePilotosSQL()");

        Connection conexion = BaseDeDatos.establecerConexion();
        String query="SELECT numero, nombre FROM pilotos";

        Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery (query);

        HashMap listaPilotos = new HashMap();

        if(rs!=null){
            while(rs.next()){
                listaPilotos.put(rs.getString("numero"), rs.getString("nombre"));
            }
        }
        BaseDeDatos.cerrarConexion(conexion);

        return listaPilotos;
    }

    /**
     * Devuelve el nombre del usuario a partir del nick
     * @param usuario
     * @return String con el nombre de usuario.
     * @throws SQLException
     */
    public static String getNombreUsuarioSQL(String usuario) throws SQLException {
        System.out.println("Llamada a BBDD: getNombreUsuarioSQL()");

        Connection conexion = BaseDeDatos.establecerConexion();
        String query="SELECT nombre FROM usuarios WHERE nick=?";

        PreparedStatement selectStatement = conexion.prepareStatement(query);

        selectStatement.setString(1, usuario);

        ResultSet rs = selectStatement.executeQuery ();

        String nombreUsuario = "";

        if(rs!=null){
            while(rs.next()){
                nombreUsuario=rs.getString("nombre");
            }
        }
        BaseDeDatos.cerrarConexion(conexion);

        return nombreUsuario;
    }

    /**
     * Devuelve el nombre de la carrera indicada.
     * @param carrera
     * @return
     * @throws SQLException
     */
    public static String getNombreCarreraSQL(String carrera) throws SQLException {
        System.out.println("Llamada a BBDD: getNombreCarreraSQL()");

        Connection conexion = BaseDeDatos.establecerConexion();
        String query="SELECT nombre FROM carreras WHERE identificador=?";

        PreparedStatement selectStatement = conexion.prepareStatement(query);

        selectStatement.setString(1, carrera);

        ResultSet rs = selectStatement.executeQuery ();

        String nombreCarrera = "";

        if(rs!=null){
            while(rs.next()){
                nombreCarrera=rs.getString("nombre");
            }
        }
        BaseDeDatos.cerrarConexion(conexion);

        return nombreCarrera;
    }

    /**
     * Recupera el resultado de una carrera
     * @param carrera
     * @return
     * @throws SQLException
     */
    public static HashMap getResultadoCarreraSQL(String carrera) throws SQLException {
        System.out.println("Llamada a BBDD: getResultadoCarreraSQL()");

        Connection conexion = BaseDeDatos.establecerConexion();
        String query="SELECT * FROM resultado_real_carreras WHERE identificador=?";

        PreparedStatement selectStatement = conexion.prepareStatement(query);

        selectStatement.setString(1, carrera);

        ResultSet rs = selectStatement.executeQuery ();

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
        
        BaseDeDatos.cerrarConexion(conexion);

        return resultadoCarrera;
    }

    /**
     * Devuelve los datos de un usario para una carrera ya disputada.
     * 
     * @param usuario
     * @param carrera
     * @return
     * @throws SQLException
     */
    public static HashMap getDatosCarreraAnteriorSQL(String usuario, String carrera) throws SQLException {
        System.out.println("Llamada a BBDD: getDatosCarreraAnteriorSQL()");

        Connection conexion = BaseDeDatos.establecerConexion();
        String query="SELECT * FROM apuestas_carreras WHERE usuario=? AND carrera=?";

        PreparedStatement selectStatement = conexion.prepareStatement(query);

        selectStatement.setString(1, usuario);
        selectStatement.setString(2, carrera);

        ResultSet rs = selectStatement.executeQuery ();

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
        
        BaseDeDatos.cerrarConexion(conexion);

        return apuestaCarrera;
    }

    /**
     * Devuelve el nick y nombre de todos los usuarios de la aplicación.
     * 
     * @return
     * @throws SQLException
     */
    public static HashMap getUsuariosSQL() throws SQLException {
        System.out.println("Llamada a BBDD: getUsuariosSQL()");

        HashMap resultado = new HashMap();
        Connection conexion = BaseDeDatos.establecerConexion();
        String query="SELECT nick, nombre FROM usuarios ORDER BY nombre ASC";
        ArrayList listaUsuarios = new ArrayList();
        ArrayList nombresUsuarios = new ArrayList();

        Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery (query);

        if(rs!=null){
            while(rs.next()){
                listaUsuarios.add(rs.getString("nick"));
                nombresUsuarios.add(rs.getString("nombre"));
            }
        }

        resultado.put("listaUsuarios", listaUsuarios);
        resultado.put("nombresUsuarios", nombresUsuarios);
        
        BaseDeDatos.cerrarConexion(conexion);

        return resultado;
    }

    /**
     * Devuelve todas las apuestas anteriores de todos los usuarios.
     *
     * @return
     * @throws SQLException
     */
    public static HashMap getApuestasAnterioresSQL() throws SQLException {
        System.out.println("Llamada a BBDD: getApuestasAnterioresSQL()");

        Connection conexion = BaseDeDatos.establecerConexion();
        String query="SELECT a.usuario, b.nombre, a.carrera "
                + "FROM apuestas_carreras a, usuarios b "
                + "WHERE a.usuario=b.nick "
                + "ORDER BY b.nombre, a.carrera";

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
        
        BaseDeDatos.cerrarConexion(conexion);

        return porUsuarios;
    }

    /**
     * Devuelve los datos de todas las carreras dadas de alta.
     * 
     * @return
     * @throws SQLException
     */
    public static ArrayList getDatosCarrerasSQL() throws SQLException {
        System.out.println("Llamada a BBDD: getDatosCarrerasSQL()");

        Connection conexion = BaseDeDatos.establecerConexion();
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

        BaseDeDatos.cerrarConexion(conexion);
        
        return datosRecuperados;
    }

    /**
     * Devuelve los datos de la última carrera disputada.
     * 
     * @return
     * @throws SQLException
     */
    public static String getCarreraAnteriorSQL() throws SQLException {
        System.out.println("Llamada a BBDD: getCarreraAnteriorSQL()");

        Connection conexion = BaseDeDatos.establecerConexion();

        Calendar c = Calendar.getInstance();
        String dia = Integer.toString(c.get(Calendar.DATE));
        String mes = Integer.toString(c.get(Calendar.MONTH)+1);
        String annio = Integer.toString(c.get(Calendar.YEAR));

        String fecha_hoy=annio+"-"+mes+"-"+dia;
        String query="SELECT * "
                + "FROM carreras "
                + "WHERE fecha_carrera = ("
                + "SELECT max(fecha_carrera)"
                + "FROM carreras "
                + "WHERE fecha_carrera <= ?)";

        PreparedStatement selectStatement = conexion.prepareStatement(query);

        selectStatement.setString(1, fecha_hoy);

        ResultSet rs = selectStatement.executeQuery ();
        
        String ultimaCarreraDisputada="";

        if(rs!=null){
                while(rs.next()){
                    ultimaCarreraDisputada=rs.getString("identificador");
                    if(ultimaCarreraDisputada==null)ultimaCarreraDisputada="";
                }
        }

        BaseDeDatos.cerrarConexion(conexion);
        
        return ultimaCarreraDisputada;
    }

     public static int guardarResultadoCarreraSQL(String carrera, String pole, String primero, String segundo, String tercero, String cuarto, String quinto, String sexto, String septimo, String octavo, String noveno, String decimo) throws SQLException {
        System.out.println("Llamada a BBDD: guardarResultadoCarreraSQL()");

        Connection conexion = BaseDeDatos.establecerConexion();

        String query="INSERT INTO resultado_real_carreras (identificador,pole,primero,segundo,tercero,cuarto,quinto,sexto,septimo,octavo,noveno,decimo) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?) "
                + "ON DUPLICATE KEY "
                + "UPDATE identificador=?, pole=?, primero=?, segundo=?, tercero=?, cuarto=?, quinto=?, sexto=?, septimo=?, octavo=?, noveno=?, decimo=?";

        PreparedStatement selectStatement = conexion.prepareStatement(query);

        selectStatement.setString(1, carrera);
        selectStatement.setString(2, pole);
        selectStatement.setString(3, primero);
        selectStatement.setString(4, segundo);
        selectStatement.setString(5, tercero);
        selectStatement.setString(6, cuarto);
        selectStatement.setString(7, quinto);
        selectStatement.setString(8, sexto);
        selectStatement.setString(9, septimo);
        selectStatement.setString(10, octavo);
        selectStatement.setString(11, noveno);
        selectStatement.setString(12, decimo);
        selectStatement.setString(13, carrera);
        selectStatement.setString(14, pole);
        selectStatement.setString(15, primero);
        selectStatement.setString(16, segundo);
        selectStatement.setString(17, tercero);
        selectStatement.setString(18, cuarto);
        selectStatement.setString(19, quinto);
        selectStatement.setString(20, sexto);
        selectStatement.setString(21, septimo);
        selectStatement.setString(22, octavo);
        selectStatement.setString(23, noveno);
        selectStatement.setString(24, decimo);

        int i = selectStatement.executeUpdate();

        return i;
    }

    public static ArrayList getOrdenadosCarreraSQL(String carrera) throws SQLException {
        System.out.println("Llamada a BBDD: getOrdenadosCarreraSQL()");

        ArrayList usuariosOrdenados=new ArrayList();
        Connection conexion = BaseDeDatos.establecerConexion();
        String query="SELECT usuario "
                + "FROM resultados_apuestas "
                + "WHERE carrera=? "
                + "ORDER BY puntos DESC";

        PreparedStatement selectStatement = conexion.prepareStatement(query);

        selectStatement.setString(1, carrera);

        ResultSet rs = selectStatement.executeQuery ();

        if(rs!=null){
            while(rs.next()){
                usuariosOrdenados.add(rs.getString("usuario"));
            }
        }

        BaseDeDatos.cerrarConexion(conexion);

        return usuariosOrdenados;
    }
}

