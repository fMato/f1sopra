<%@page import="java.util.HashMap"%>
<%
HashMap apuestaCarrera = (HashMap) request.getAttribute("apuestaCarrera");
HashMap listaPilotos = (HashMap) request.getAttribute("listaPilotos");
String nombreUsuairo = (String) request.getAttribute("nombreUsuairo");
String nombreCarrera = (String) request.getAttribute("nombreCarrera");

String UP=(String)apuestaCarrera.get("pole");
String U1=(String)apuestaCarrera.get("primero");
String U2=(String)apuestaCarrera.get("segundo");
String U3=(String)apuestaCarrera.get("tercero");
String U4=(String)apuestaCarrera.get("cuarto");
String U5=(String)apuestaCarrera.get("quinto");
String U6=(String)apuestaCarrera.get("sexto");
String U7=(String)apuestaCarrera.get("septimo");
String U8=(String)apuestaCarrera.get("octavo");
String U9=(String)apuestaCarrera.get("noveno");
String U10=(String)apuestaCarrera.get("decimo");
%>
<div>
    <%=nombreUsuairo%><BR />
    <%=nombreCarrera%><BR /><BR />
    <label class="apuestaAnteriorLabelMini">Pole:</label> <%=(String)listaPilotos.get(UP)%><BR />
    <label class="apuestaAnteriorLabelMini">Primero:</label> <%=(String)listaPilotos.get(U1)%><BR />
    <label class="apuestaAnteriorLabelMini">Segundo:</label> <%=(String)listaPilotos.get(U2)%><BR />
    <label class="apuestaAnteriorLabelMini">Tercero:</label> <%=(String)listaPilotos.get(U3)%><BR />
    <label class="apuestaAnteriorLabelMini">Cuarto:</label> <%=(String)listaPilotos.get(U4)%><BR />
    <label class="apuestaAnteriorLabelMini">Quinto:</label> <%=(String)listaPilotos.get(U5)%><BR />
    <label class="apuestaAnteriorLabelMini">Sexto:</label> <%=(String)listaPilotos.get(U6)%><BR />
    <label class="apuestaAnteriorLabelMini">Séptimo:</label> <%=(String)listaPilotos.get(U7)%><BR />
    <label class="apuestaAnteriorLabelMini">Octavo:</label> <%=(String)listaPilotos.get(U8)%><BR />
    <label class="apuestaAnteriorLabelMini">Noveno:</label> <%=(String)listaPilotos.get(U9)%><BR />
    <label class="apuestaAnteriorLabelMini">Décimo:</label> <%=(String)listaPilotos.get(U10)%>
</div>