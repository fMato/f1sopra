<%@page import="java.util.HashMap"%>
<%
HashMap apuestaCarrera = (HashMap) request.getAttribute("apuestaCarrera");
HashMap listaPilotos = (HashMap) request.getAttribute("listaPilotos");
HashMap resultadoCarrera = (HashMap) request.getAttribute("resultadoCarrera");
String nombreCarrera = (String) request.getAttribute("nombreCarrera");
String nombreUsuairo = (String) request.getAttribute("nombreUsuairo");

boolean carreraDisputada=false;
if(resultadoCarrera.get("pole")!=null)
    carreraDisputada=true;

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

String CP=(String)resultadoCarrera.get("pole");
String C1=(String)resultadoCarrera.get("primero");
String C2=(String)resultadoCarrera.get("segundo");
String C3=(String)resultadoCarrera.get("tercero");
String C4=(String)resultadoCarrera.get("cuarto");
String C5=(String)resultadoCarrera.get("quinto");
String C6=(String)resultadoCarrera.get("sexto");
String C7=(String)resultadoCarrera.get("septimo");
String C8=(String)resultadoCarrera.get("octavo");
String C9=(String)resultadoCarrera.get("noveno");
String C10=(String)resultadoCarrera.get("decimo");

if(CP==null) CP="";
if(C1==null) C1="";
if(C2==null) C2="";
if(C3==null) C3="";
if(C4==null) C4="";
if(C5==null) C5="";
if(C6==null) C6="";
if(C7==null) C7="";
if(C8==null) C8="";
if(C9==null) C9="";
if(C10==null) C10="";


%>
<h1>Apuesta de <%=nombreUsuairo%> para el <%=nombreCarrera%>.</h1>
<BR />
<BR />
<div style="float:left;padding-left: 100px;">
<p><u>Apuesta del usuario:</u></p>
<p>
    <label class="apuestaAnteriorLabel">Pole:</label> <b <%if(UP.equals(CP)){%> style="color:#00BB00;"<%}%>><%=(String)listaPilotos.get(UP)%></b>
</p>
<p>
    <label class="apuestaAnteriorLabel">Primero:</label> <b <%if(U1.equals(C1)){%> style="color:#00BB00;"<%}%>><%=(String)listaPilotos.get(U1)%></b>
</p>
<p>
    <label class="apuestaAnteriorLabel">Segundo:</label> <b <%if(U2.equals(C2)){%> style="color:#00BB00;"<%}%>><%=(String)listaPilotos.get(U2)%></b>
</p>
<p>
    <label class="apuestaAnteriorLabel">Tercero:</label> <b <%if(U3.equals(C3)){%> style="color:#00BB00;"<%}%>><%=(String)listaPilotos.get(U3)%></b>
</p>
<p>
    <label class="apuestaAnteriorLabel">Cuarto:</label> <b <%if(U4.equals(C4)){%> style="color:#00BB00;"<%}%>><%=(String)listaPilotos.get(U4)%></b>
</p>
<p>
    <label class="apuestaAnteriorLabel">Quinto:</label> <b <%if(U5.equals(C5)){%> style="color:#00BB00;"<%}%>><%=(String)listaPilotos.get(U5)%></b>
</p>
<p>
    <label class="apuestaAnteriorLabel">Sexto:</label> <b <%if(U6.equals(C6)){%> style="color:#00BB00;"<%}%>><%=(String)listaPilotos.get(U6)%></b>
</p>
<p>
    <label class="apuestaAnteriorLabel">Séptimo:</label> <b <%if(U7.equals(C7)){%> style="color:#00BB00;"<%}%>><%=(String)listaPilotos.get(U7)%></b>
</p>
<p>
    <label class="apuestaAnteriorLabel">Octavo:</label> <b <%if(U8.equals(C8)){%> style="color:#00BB00;"<%}%>><%=(String)listaPilotos.get(U8)%></b>
</p>
<p>
    <label class="apuestaAnteriorLabel">Noveno:</label> <b <%if(U9.equals(C9)){%> style="color:#00BB00;"<%}%>><%=(String)listaPilotos.get(U9)%></b>
</p>
<p>
    <label class="apuestaAnteriorLabel">Décimo:</label> <b <%if(U10.equals(C10)){%> style="color:#00BB00;"<%}%>><%=(String)listaPilotos.get(U10)%></b>
</p>
</div>
<% if(carreraDisputada){ %>
<div style="float:left;padding-left: 50px;">
<p><u>Resultado de la carrera:</u></p>
<p>
    <b><%=(String)listaPilotos.get(CP)%></b>
</p>
<p>
    <b><%=(String)listaPilotos.get(C1)%></b>
</p>
<p>
    <b><%=(String)listaPilotos.get(C2)%></b>
</p>
<p>
    <b><%=(String)listaPilotos.get(C3)%></b>
</p>
<p>
    <b><%=(String)listaPilotos.get(C4)%></b>
</p>
<p>
    <b><%=(String)listaPilotos.get(C5)%></b>
</p>
<p>
    <b><%=(String)listaPilotos.get(C6)%></b>
</p>
<p>
    <b><%=(String)listaPilotos.get(C7)%></b>
</p>
<p>
    <b><%=(String)listaPilotos.get(C8)%></b>
</p>
<p>
    <b><%=(String)listaPilotos.get(C9)%></b>
</p>
<p>
    <b><%=(String)listaPilotos.get(C10)%></b>
</p>
</div>
<%}%>