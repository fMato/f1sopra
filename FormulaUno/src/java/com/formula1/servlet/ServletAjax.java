package com.formula1.servlet;

import com.formula1.comunes.DatosPantalla;
import com.formula1.comunes.PantallaWeb;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Mato
 */
public class ServletAjax extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            DatosPantalla datosPantalla=new DatosPantalla();
            HashMap datosPeticion = new HashMap();
            String pantalla = "";
            HttpSession session = request.getSession(true);
            String hayUsuarioSesion=(String)session.getAttribute("hayUsuarioSesion");
            if(hayUsuarioSesion==null) hayUsuarioSesion="";
            request.setAttribute("hayUsuarioSesion", hayUsuarioSesion);

            System.out.println("Url Ajax solicitada: "+request.getServletPath());
            pantalla=getPantalla(request.getServletPath()); // Recuperamos la orden introducida por el usuario.
            if(pantalla==null) pantalla="";

            if(hayUsuarioSesion.equals("S") || pantalla.equals("hacerLogin")){

                datosPeticion=getDatosPantalla(pantalla);

                String jspPresentacion = (String) datosPeticion.get("jspPresentacion");
                String tituloPantalla = (String) datosPeticion.get("tituloPantalla");
                String claseManejadora = (String) datosPeticion.get("claseManejadora");
                if(jspPresentacion==null) jspPresentacion="";
                if(tituloPantalla==null) tituloPantalla="";
                if(claseManejadora==null) claseManejadora="";

                datosPantalla.setJsp(jspPresentacion);
                datosPantalla.setTitulo(tituloPantalla);

                System.out.println("Clase manejadora: "+claseManejadora);
                System.out.println("Jsp de presentación: "+jspPresentacion);

                if(!claseManejadora.equalsIgnoreCase("NO"))
                    try{
                        PantallaWeb objetoManejador = (PantallaWeb) Class.forName(claseManejadora).newInstance();
                        objetoManejador.processRequest(request, response, datosPantalla);
                    }catch(Exception ex){
                        datosPantalla.setJsp("./error.jsp");
                        datosPantalla.setTitulo("Error");
                        request.setAttribute("ERROR", "Ha ocurrido un error al intentar cargar la pantalla solicitada. Por favor comprueba que la dirección introducida es correcta o avisa al administrador.");
                    }
            }
            else{
                System.out.println("No hay sesión, es obligatorio un usuario conectado.");
                datosPantalla.setJsp("./login.jsp");
                datosPantalla.setTitulo("Acceso de usuario");
            }

            request.setAttribute("jspPresentacion", datosPantalla.getJsp());
            request.setAttribute("tituloPantalla", datosPantalla.getTitulo());

            RequestDispatcher rd = request.getRequestDispatcher ("/Pantallas/"+datosPantalla.getJsp());
            rd.include (request, response);

        } finally {
            out.close();
        }
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    public String getServletInfo() {
        return "ServletPrincipal: Servlet que atiende las peticiones ajax de la aplicación f1Sopra.";
    }

    
    /**
     * Método que recoge la pantalla solicitada por el usuario.
     * @param orden
     * @return
     */
    public String getPantalla(String orden) {

        if(orden==null) orden="";
        orden=orden.replaceFirst("/", "");
        orden=orden.replaceFirst(".ajax", "");
        System.out.println("Pantalla solicitada: "+orden);

        return orden;
    }
    /**
     * Método al que se le pasa la pantalla solicitada y se encarga de buscar en el fichero de configuración la clase manejadora, el jsp
     * de presentación de esta pantalla...
     * @param pantalla
     */
    public HashMap getDatosPantalla(String pantalla){

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document documento = null;
        HashMap datosPantalla = new HashMap();

        try{
            // Intentamos recuperar el fichero de configuración con la información de las pantallas
            DocumentBuilder builder = factory.newDocumentBuilder();
            documento = builder.parse( new File("/var/lib/tomcat5.5/config/config.pantallas.xml") );
            }
        catch (Exception spe){
            System.out.println("Error al leer fichero");
            spe.printStackTrace();
            }

        // Recuperamos el nodo raiz del XML.
        Node nodoRaiz = documento.getFirstChild();

        // Devuelve nodos hijos del raiz
        NodeList listaNodosHijos = nodoRaiz.getChildNodes();

        // Recorremos los nodos hijos
        for (int i=0; i<listaNodosHijos.getLength(); i++){
           // Leemos uno a uno los nodos hijos
           Node unNodoHijo = listaNodosHijos.item(i);

           // Obtener los atributos del nodo actual
           NamedNodeMap atributos = unNodoHijo.getAttributes();

           // Si tiene atributos...
           if(atributos!=null){
               // Leemos el atributo name
               Node unAtributo = atributos.getNamedItem("name");
               String valorAtributo = unAtributo.getNodeValue();

               // Si el atributo coincide con la pantalla lanzada por el usuario
               if(valorAtributo.equals(pantalla)){
                   // Recuperamos la lista de nodos hijos de nuestra pantalla
                   NodeList listaNodosNietos = unNodoHijo.getChildNodes();

                   // Recorremos la lista que acabamos de recuperar
                   for (int j=0; j<listaNodosNietos.getLength();j++){
                       Node unNodoNieto = listaNodosNietos.item(j);
                       // Buscamos los nodos que contienen la información que nos interesa
                       if(unNodoNieto.getNodeName().equals("manejador")){
                           datosPantalla.put("claseManejadora", unNodoNieto.getTextContent());
                       }
                       else if(unNodoNieto.getNodeName().equals("presentacion")){
                           datosPantalla.put("jspPresentacion", unNodoNieto.getTextContent());
                       }
                       else if(unNodoNieto.getNodeName().equals("titulo")){
                           datosPantalla.put("tituloPantalla", unNodoNieto.getTextContent());
                       }
                    }
               }
            }
        }
        return datosPantalla;
    }
}
