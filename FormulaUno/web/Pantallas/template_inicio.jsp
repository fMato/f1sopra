<!--
    Document   : template_inicio
    Created on : 02-mar-2011, 18:06:55
    Author     : fMato
-->

<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
String titulo = (String) request.getAttribute("tituloPantalla");
String pantallaContenido = (String) request.getAttribute("jspPresentacion");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=titulo%></title>
        <link rel="stylesheet" type="text/css" href="./css/general.css" />
        <script type="text/javascript" src="./javascript/comues.js"></script>
    </head>
    <body onload="muestraReloj()">
        <div align="center">
            <img src="./Imagenes/cabeceraf1.jpg" alt="Formula Uno Sopra 2011"/>
        </div>
        <div class="container">
            <div class="menuLateral">
                <ul class="nav">
                    <%
                    ArrayList opcionesMenu = (ArrayList) request.getAttribute("opcionesMenu");

                    if(opcionesMenu==null){
                        opcionesMenu=new ArrayList();
                    }

                    for(int i=0; i<opcionesMenu.size(); i++){
                        HashMap menuItem = (HashMap) opcionesMenu.get(i);
                        String texto = (String) menuItem.get("texto");
                        String enlace = (String) menuItem.get("url");
                        %>
                        <li>
                            <a href="<%=enlace%>"><%=texto%></a>
                        </li>
                        <%
                    }
                    %>
                    
                </ul>
                <span>
                    <form action=""  name="form_reloj">
                        <input type="text" name="reloj" size="10" style="background-color : Black; color : White; font-family : Verdana, Arial, Helvetica; font-size : 8pt; text-align : center;" onfocus="window.document.form_reloj.reloj.blur()" />
                    </form>
                </span>
                    <%
                    String hayUsuarioSesion = (String)request.getAttribute("hayUsuarioSesion");
                    if(hayUsuarioSesion.equals("S")){
                        long antiCache=System.currentTimeMillis();
                    %>
                    <BR />
                    <BR />
                    <p><a href="./desconectar.f1?antiCache=<%=antiCache%>" class="linkAdmin">Cerrar sesión</a></p>
                    <%}%>
            </div> <!-- fin .menuLateral -->
            <div class="contenido">
                <!-- Include del jsp con el contenido -->
                <jsp:include page='<%=pantallaContenido%>' flush="false"/>
            </div><!-- fin .contenido -->


            <div class="menuLateral"></div>
            <div class="pie">
                Página optimizada para Mozilla-Firefox. Para cualquier problema contactar con <a href="mailto:francisco.mato@gmail.com">Francisco Mato</a> o <a href="mailto:vpalomos_profit@bankinter.es">Victor Palomo</a>.
            </div>
        </div><!-- fin .container -->
    </body>
</html>
