<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<h1>Actividad en la tabla de apuestas.</h1>
<BR />
<BR />
<table id="tablaGeneral" border="0" align="center">
    <TR>
        <TH>Usuario</TH>
        <TH>Última apuesta</TH>
    </TR>
    <%
    ArrayList nombreUsuario = (ArrayList)request.getAttribute("nombreUsuario");
    ArrayList actividadUsuario = (ArrayList)request.getAttribute("actividadUsuario");
    ArrayList colorCelda = (ArrayList)request.getAttribute("colorCelda");

    for(int i=0; i<nombreUsuario.size(); i++){%>
    <TR>
        <TD style="padding: 5px;"><%=nombreUsuario.get(i)%></TD>
        <TD style="padding: 5px;text-align: center;background-color: <%=colorCelda.get(i)%>"><%=actividadUsuario.get(i)%></TD>
    </TR>
    <%}%>
</table>

