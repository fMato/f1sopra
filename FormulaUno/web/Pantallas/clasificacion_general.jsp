<%@page import="com.formula1.comunes.DatosPersona"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%
DatosPersona datosPersonaSesion = (DatosPersona)session.getAttribute("datosPersona");
String usuarioSesion = "";
if(datosPersonaSesion!=null)
    usuarioSesion=datosPersonaSesion.getNick();
%>
<h1>Clasificación general</h1>
<BR />
<table id="tablaGeneral" border="0" width="100%">
    <TR>
        <TH></TH>
        <TH><img src="./Imagenes/banderas/01.png" alt="Australia" title="Australia" width="20px"/></TH>
        <TH><img src="./Imagenes/banderas/02.png" alt="Malasia" title="Malasia" width="20px"/></TH>
        <TH><img src="./Imagenes/banderas/03.png" alt="China" title="China" width="20px"/></TH>
        <TH><img src="./Imagenes/banderas/04.png" alt="Turquía" title="Turquía" width="20px"/></TH>
        <TH><img src="./Imagenes/banderas/05.png" alt="España" title="España" width="20px"/></TH>
        <TH><img src="./Imagenes/banderas/06.png" alt="Mónaco" title="Mónaco" width="20px"/></TH>
        <TH><img src="./Imagenes/banderas/07.png" alt="Canadá" title="Canadá" width="20px"/></TH>
        <TH><img src="./Imagenes/banderas/08.png" alt="Europa" title="Europa" width="20px"/></TH>
        <TH><img src="./Imagenes/banderas/09.png" alt="Gran Bretaña" title="Gran Bretaña" width="20px"/></TH>
        <TH><img src="./Imagenes/banderas/10.png" alt="Alemania" title="Alemania" width="20px"/></TH>
        <TH><img src="./Imagenes/banderas/11.png" alt="Hungría" title="Hungría" width="20px"/></TH>
        <TH><img src="./Imagenes/banderas/12.png" alt="Bélgica" title="Bélgica" width="20px"/></TH>
        <TH><img src="./Imagenes/banderas/13.png" alt="Italia" title="Italia" width="20px"/></TH>
        <TH><img src="./Imagenes/banderas/14.png" alt="Singapur" title="Singapur" width="20px"/></TH>
        <TH><img src="./Imagenes/banderas/15.png" alt="Japón" title="Japón" width="20px"/></TH>
        <TH><img src="./Imagenes/banderas/16.png" alt="Corea" title="Corea" width="20px"/></TH>
        <TH><img src="./Imagenes/banderas/17.png" alt="India" title="India" width="20px"/></TH>
        <TH><img src="./Imagenes/banderas/18.png" alt="Abu Dhabi" title="Abu Dhabi" width="20px"/></TH>
        <TH><img src="./Imagenes/banderas/19.png" alt="Brasil" title="Brasil" width="20px"/></TH>
        <TH>PT</TH>
    </TR>
<%
ArrayList pilotosOrdenados = (ArrayList)request.getAttribute("pilotosOrdenados");
HashMap datosClasifGeneral = (HashMap) request.getAttribute("datosClasifGeneral");
HashMap puntosMaximos = (HashMap) request.getAttribute("puntosMaximos");

String puntosMax1=(String)puntosMaximos.get("1");
if(puntosMax1==null)puntosMax1="0";
String puntosMax2=(String)puntosMaximos.get("2");
if(puntosMax2==null)puntosMax2="0";
String puntosMax3=(String)puntosMaximos.get("3");
if(puntosMax3==null)puntosMax3="0";
String puntosMax4=(String)puntosMaximos.get("4");
if(puntosMax4==null)puntosMax4="0";
String puntosMax5=(String)puntosMaximos.get("5");
if(puntosMax5==null)puntosMax5="0";
String puntosMax6=(String)puntosMaximos.get("6");
if(puntosMax6==null)puntosMax6="0";
String puntosMax7=(String)puntosMaximos.get("7");
if(puntosMax7==null)puntosMax7="0";
String puntosMax8=(String)puntosMaximos.get("8");
if(puntosMax8==null)puntosMax8="0";
String puntosMax9=(String)puntosMaximos.get("9");
if(puntosMax9==null)puntosMax9="0";
String puntosMax10=(String)puntosMaximos.get("10");
if(puntosMax10==null)puntosMax10="0";
String puntosMax11=(String)puntosMaximos.get("11");
if(puntosMax11==null)puntosMax11="0";
String puntosMax12=(String)puntosMaximos.get("12");
if(puntosMax12==null)puntosMax12="0";
String puntosMax13=(String)puntosMaximos.get("13");
if(puntosMax13==null)puntosMax13="0";
String puntosMax14=(String)puntosMaximos.get("14");
if(puntosMax14==null)puntosMax14="0";
String puntosMax15=(String)puntosMaximos.get("15");
if(puntosMax15==null)puntosMax15="0";
String puntosMax16=(String)puntosMaximos.get("16");
if(puntosMax16==null)puntosMax16="0";
String puntosMax17=(String)puntosMaximos.get("17");
if(puntosMax17==null)puntosMax17="0";
String puntosMax18=(String)puntosMaximos.get("18");
if(puntosMax18==null)puntosMax18="0";
String puntosMax19=(String)puntosMaximos.get("19");
if(puntosMax19==null)puntosMax19="0";


for(int i=0; i<pilotosOrdenados.size();i++){
    String usuario = (String)pilotosOrdenados.get(i);
    HashMap datosUnPiloto = (HashMap)datosClasifGeneral.get(usuario);
    String nombre=(String)datosUnPiloto.get("nombre");
    if(nombre==null)nombre=usuario;
    String carrera1=(String)datosUnPiloto.get("1");
    if(carrera1==null)carrera1="-";
    String carrera2=(String)datosUnPiloto.get("2");
    if(carrera2==null)carrera2="-";
    String carrera3=(String)datosUnPiloto.get("3");
    if(carrera3==null)carrera3="-";
    String carrera4=(String)datosUnPiloto.get("4");
    if(carrera4==null)carrera4="-";
    String carrera5=(String)datosUnPiloto.get("5");
    if(carrera5==null)carrera5="-";
    String carrera6=(String)datosUnPiloto.get("6");
    if(carrera6==null)carrera6="-";
    String carrera7=(String)datosUnPiloto.get("7");
    if(carrera7==null)carrera7="-";
    String carrera8=(String)datosUnPiloto.get("8");
    if(carrera8==null)carrera8="-";
    String carrera9=(String)datosUnPiloto.get("9");
    if(carrera9==null)carrera9="-";
    String carrera10=(String)datosUnPiloto.get("10");
    if(carrera10==null)carrera10="-";
    String carrera11=(String)datosUnPiloto.get("11");
    if(carrera11==null)carrera11="-";
    String carrera12=(String)datosUnPiloto.get("12");
    if(carrera12==null)carrera12="-";
    String carrera13=(String)datosUnPiloto.get("13");
    if(carrera13==null)carrera13="-";
    String carrera14=(String)datosUnPiloto.get("14");
    if(carrera14==null)carrera14="-";
    String carrera15=(String)datosUnPiloto.get("15");
    if(carrera15==null)carrera15="-";
    String carrera16=(String)datosUnPiloto.get("16");
    if(carrera16==null)carrera16="-";
    String carrera17=(String)datosUnPiloto.get("17");
    if(carrera17==null)carrera17="-";
    String carrera18=(String)datosUnPiloto.get("18");
    if(carrera18==null)carrera18="-";
    String carrera19=(String)datosUnPiloto.get("19");
    if(carrera19==null)carrera19="-";
    String total=(String)datosUnPiloto.get("total");
    if(total==null)total="0";
    %>
    <TR <%if(usuarioSesion.equals(usuario)){%>class="filaSel"<%}%>>
        <TD><%=nombre%></TD>
        <TD align="center" <%if(puntosMax1.equals(carrera1)){%>class="maxPunt"<%}%>><%=carrera1%></TD>
        <TD align="center" <%if(puntosMax2.equals(carrera2)){%>class="maxPunt"<%}%>><%=carrera2%></TD>
        <TD align="center" <%if(puntosMax3.equals(carrera3)){%>class="maxPunt"<%}%>><%=carrera3%></TD>
        <TD align="center" <%if(puntosMax4.equals(carrera4)){%>class="maxPunt"<%}%>><%=carrera4%></TD>
        <TD align="center" <%if(puntosMax5.equals(carrera5)){%>class="maxPunt"<%}%>><%=carrera5%></TD>
        <TD align="center" <%if(puntosMax6.equals(carrera6)){%>class="maxPunt"<%}%>><%=carrera6%></TD>
        <TD align="center" <%if(puntosMax7.equals(carrera7)){%>class="maxPunt"<%}%>><%=carrera7%></TD>
        <TD align="center" <%if(puntosMax8.equals(carrera8)){%>class="maxPunt"<%}%>><%=carrera8%></TD>
        <TD align="center" <%if(puntosMax9.equals(carrera9)){%>class="maxPunt"<%}%>><%=carrera9%></TD>
        <TD align="center" <%if(puntosMax10.equals(carrera10)){%>class="maxPunt"<%}%>><%=carrera10%></TD>
        <TD align="center" <%if(puntosMax11.equals(carrera11)){%>class="maxPunt"<%}%>><%=carrera11%></TD>
        <TD align="center" <%if(puntosMax12.equals(carrera12)){%>class="maxPunt"<%}%>><%=carrera12%></TD>
        <TD align="center" <%if(puntosMax13.equals(carrera13)){%>class="maxPunt"<%}%>><%=carrera13%></TD>
        <TD align="center" <%if(puntosMax14.equals(carrera14)){%>class="maxPunt"<%}%>><%=carrera14%></TD>
        <TD align="center" <%if(puntosMax15.equals(carrera15)){%>class="maxPunt"<%}%>><%=carrera15%></TD>
        <TD align="center" <%if(puntosMax16.equals(carrera16)){%>class="maxPunt"<%}%>><%=carrera16%></TD>
        <TD align="center" <%if(puntosMax17.equals(carrera17)){%>class="maxPunt"<%}%>><%=carrera17%></TD>
        <TD align="center" <%if(puntosMax18.equals(carrera18)){%>class="maxPunt"<%}%>><%=carrera18%></TD>
        <TD align="center" <%if(puntosMax19.equals(carrera19)){%>class="maxPunt"<%}%>><%=carrera19%></TD>
        <TD align="center"><%=total%></TD>
    </TR>
<%
}
%>
</table>