<!--
    Document   : template_inicio
    Created on : 02-mar-2011, 18:06:55
    Author     : fMato
-->

<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%
response.setDateHeader("Expires",-1);
response.setHeader("Pragma","no-cache");
if(request.getProtocol().equals("HTTP/1.1"))
response.setHeader("Cache-Control","no-cache");

String titulo = (String) request.getAttribute("tituloPantalla");
String pantallaContenido = (String) request.getAttribute("jspPresentacion");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link rel="shortcut icon" href="./Imagenes/otras/favicon2.ico">
        <title><%=titulo%></title>
        <link rel="stylesheet" type="text/css" href="./css/general.css" />
        <script type="text/javascript" src="./javascript/comues.js"></script>
    </head>
    <body onload="muestraReloj()">
        <div align="center" style="font-size:0px;">
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
                    <a href="./desconectar.f1?antiCache=<%=antiCache%>" class="linkAdmin">Cerrar sesión</a>
                    <%}%>
            </div> <!-- fin .menuLateral -->
            <div class="contenido">
                <!-- Include del jsp con el contenido -->
                <%try{%>
                <jsp:include page='<%=pantallaContenido%>' flush="false"/>
                <%}catch (Exception spe){
                System.out.println("Error al pintar el jsp");%>
                <jsp:include page='./error_jsp.jsp' flush="false"/>
               <% }%>
            </div><!-- fin .contenido -->


            <div class="menuLateral"></div>
            <div class="pie">
                Página optimizada para Mozilla-Firefox. Para cualquier problema contactar con <a href="mailto:francisco.mato@gmail.com">Francisco Mato</a> o <a href="mailto:fuensa82@gmail.com">Victor Palomo</a>.
            </div>
        </div><!-- fin .container -->
        <div id="contenidoAjax"></div>
    </body>
</html>
