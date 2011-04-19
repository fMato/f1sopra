<script type="text/javascript" src="./javascript/clasificacion_general.js"></script>
<%@page import="com.formula1.comunes.DatosPersona"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%
DatosPersona datosPersonaSesion = (DatosPersona)session.getAttribute("datosPersona");
String usuarioSesion = "";
if(datosPersonaSesion!=null)
    usuarioSesion=datosPersonaSesion.getNick();
%>
<table id="tablaGeneral" border="0" width="96%" align="center">
    <TR>
        <TH></TH>
        <TH></TH>
        <TH style="cursor: pointer;" onClick="ordenarCarrera('1','GP de Australia.');"><img src="./Imagenes/banderas/01.png" alt="Australia" title="Australia" width="20px"/></TH>
        <TH style="cursor: pointer;" onClick="ordenarCarrera('2','GP de Malasia.');"><img src="./Imagenes/banderas/02.png" alt="Malasia" title="Malasia" width="20px"/></TH>
        <TH style="cursor: pointer;" onClick="ordenarCarrera('3','GP de China.');"><img src="./Imagenes/banderas/03.png" alt="China" title="China" width="20px"/></TH>
        <TH style="cursor: pointer;" onClick="ordenarCarrera('4','GP de Turquía.');"><img src="./Imagenes/banderas/04.png" alt="Turquía" title="Turquía" width="20px"/></TH>
        <TH style="cursor: pointer;" onClick="ordenarCarrera('5','GP de España.');"><img src="./Imagenes/banderas/05.png" alt="España" title="España" width="20px"/></TH>
        <TH style="cursor: pointer;" onClick="ordenarCarrera('6','GP de Mónaco');"><img src="./Imagenes/banderas/06.png" alt="Mónaco" title="Mónaco" width="20px"/></TH>
        <TH style="cursor: pointer;" onClick="ordenarCarrera('7','GP de Canadá.');"><img src="./Imagenes/banderas/07.png" alt="Canadá" title="Canadá" width="20px"/></TH>
        <TH style="cursor: pointer;" onClick="ordenarCarrera('8','GP de Europa.');"><img src="./Imagenes/banderas/08.png" alt="Europa" title="Europa" width="20px"/></TH>
        <TH style="cursor: pointer;" onClick="ordenarCarrera('9','GP de Gran Bretaña.');"><img src="./Imagenes/banderas/09.png" alt="Gran Bretaña" title="Gran Bretaña" width="20px"/></TH>
        <TH style="cursor: pointer;" onClick="ordenarCarrera('10','GP de Alemania.');"><img src="./Imagenes/banderas/10.png" alt="Alemania" title="Alemania" width="20px"/></TH>
        <TH style="cursor: pointer;" onClick="ordenarCarrera('11','GP de Hungría.');"><img src="./Imagenes/banderas/11.png" alt="Hungría" title="Hungría" width="20px"/></TH>
        <TH style="cursor: pointer;" onClick="ordenarCarrera('12','GP de Bélgica.');"><img src="./Imagenes/banderas/12.png" alt="Bélgica" title="Bélgica" width="20px"/></TH>
        <TH style="cursor: pointer;" onClick="ordenarCarrera('13','GP de Italia.');"><img src="./Imagenes/banderas/13.png" alt="Italia" title="Italia" width="20px"/></TH>
        <TH style="cursor: pointer;" onClick="ordenarCarrera('14','GP de Singapur.');"><img src="./Imagenes/banderas/14.png" alt="Singapur" title="Singapur" width="20px"/></TH>
        <TH style="cursor: pointer;" onClick="ordenarCarrera('15','GP de Japón.');"><img src="./Imagenes/banderas/15.png" alt="Japón" title="Japón" width="20px"/></TH>
        <TH style="cursor: pointer;" onClick="ordenarCarrera('16','GP de Corea.');"><img src="./Imagenes/banderas/16.png" alt="Corea" title="Corea" width="20px"/></TH>
        <TH style="cursor: pointer;" onClick="ordenarCarrera('17','GP de India.');"><img src="./Imagenes/banderas/17.png" alt="India" title="India" width="20px"/></TH>
        <TH style="cursor: pointer;" onClick="ordenarCarrera('18','GP de Abu Dhabi.');"><img src="./Imagenes/banderas/18.png" alt="Abu Dhabi" title="Abu Dhabi" width="20px"/></TH>
        <TH style="cursor: pointer;" onClick="ordenarCarrera('19','GP de Brasil.');"><img src="./Imagenes/banderas/19.png" alt="Brasil" title="Brasil" width="20px"/></TH>
        <TH style="cursor: pointer;" onClick="ordenarCarrera('PT','Clasificación general.');">PT</TH>
    </TR>
<%
ArrayList usuariosOrdenados = (ArrayList)request.getAttribute("usuariosOrdenados");
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

int pos=1;
String puntosUsuarioAnt="";

for(int i=0; i<usuariosOrdenados.size();i++){
    String usuario = (String)usuariosOrdenados.get(i);
    HashMap datosUnUsuario = (HashMap)datosClasifGeneral.get(usuario);
    String nombre=(String)datosUnUsuario.get("nombre");
    if(nombre==null)nombre=usuario;
    String carrera1=(String)datosUnUsuario.get("1");
    if(carrera1==null)carrera1="-";
    String carrera2=(String)datosUnUsuario.get("2");
    if(carrera2==null)carrera2="-";
    String carrera3=(String)datosUnUsuario.get("3");
    if(carrera3==null)carrera3="-";
    String carrera4=(String)datosUnUsuario.get("4");
    if(carrera4==null)carrera4="-";
    String carrera5=(String)datosUnUsuario.get("5");
    if(carrera5==null)carrera5="-";
    String carrera6=(String)datosUnUsuario.get("6");
    if(carrera6==null)carrera6="-";
    String carrera7=(String)datosUnUsuario.get("7");
    if(carrera7==null)carrera7="-";
    String carrera8=(String)datosUnUsuario.get("8");
    if(carrera8==null)carrera8="-";
    String carrera9=(String)datosUnUsuario.get("9");
    if(carrera9==null)carrera9="-";
    String carrera10=(String)datosUnUsuario.get("10");
    if(carrera10==null)carrera10="-";
    String carrera11=(String)datosUnUsuario.get("11");
    if(carrera11==null)carrera11="-";
    String carrera12=(String)datosUnUsuario.get("12");
    if(carrera12==null)carrera12="-";
    String carrera13=(String)datosUnUsuario.get("13");
    if(carrera13==null)carrera13="-";
    String carrera14=(String)datosUnUsuario.get("14");
    if(carrera14==null)carrera14="-";
    String carrera15=(String)datosUnUsuario.get("15");
    if(carrera15==null)carrera15="-";
    String carrera16=(String)datosUnUsuario.get("16");
    if(carrera16==null)carrera16="-";
    String carrera17=(String)datosUnUsuario.get("17");
    if(carrera17==null)carrera17="-";
    String carrera18=(String)datosUnUsuario.get("18");
    if(carrera18==null)carrera18="-";
    String carrera19=(String)datosUnUsuario.get("19");
    if(carrera19==null)carrera19="-";
    String total=(String)datosUnUsuario.get("total");
    if(total==null)total="0";

    if(!total.equals(puntosUsuarioAnt)){
    pos=i+1;
    }

    puntosUsuarioAnt=total;
    %>
    <TR <%if(usuarioSesion.equals(usuario)){%>class="filaSel"<%}%>>
        <TD align="right"><%=pos%>º</TD>
        <TD><%=nombre%></TD>
        <TD align="center" style="cursor: default;" <%if(puntosMax1.equals(carrera1)){%>class="maxPunt"<%} if(!carrera1.equals("-")){%>onmouseover="esperaBusqueda('<%=usuario%>','1');" onmouseout="cancelarBusqueda();" onmousemove="mueveDiv(event);"<%}%>><%=carrera1%></TD>
        <TD align="center" style="cursor: default;" <%if(puntosMax2.equals(carrera2)){%>class="maxPunt"<%} if(!carrera2.equals("-")){%>onmouseover="esperaBusqueda('<%=usuario%>','2');" onmouseout="cancelarBusqueda();" onmousemove="mueveDiv(event);"<%}%>><%=carrera2%></TD>
        <TD align="center" style="cursor: default;" <%if(puntosMax3.equals(carrera3)){%>class="maxPunt"<%} if(!carrera3.equals("-")){%>onmouseover="esperaBusqueda('<%=usuario%>','3');" onmouseout="cancelarBusqueda();" onmousemove="mueveDiv(event);"<%}%>><%=carrera3%></TD>
        <TD align="center" style="cursor: default;" <%if(puntosMax4.equals(carrera4)){%>class="maxPunt"<%} if(!carrera4.equals("-")){%>onmouseover="esperaBusqueda('<%=usuario%>','4');" onmouseout="cancelarBusqueda();" onmousemove="mueveDiv(event);"<%}%>><%=carrera4%></TD>
        <TD align="center" style="cursor: default;" <%if(puntosMax5.equals(carrera5)){%>class="maxPunt"<%} if(!carrera5.equals("-")){%>onmouseover="esperaBusqueda('<%=usuario%>','5');" onmouseout="cancelarBusqueda();" onmousemove="mueveDiv(event);"<%}%>><%=carrera5%></TD>
        <TD align="center" style="cursor: default;" <%if(puntosMax6.equals(carrera6)){%>class="maxPunt"<%} if(!carrera6.equals("-")){%>onmouseover="esperaBusqueda('<%=usuario%>','6');" onmouseout="cancelarBusqueda();" onmousemove="mueveDiv(event);"<%}%>><%=carrera6%></TD>
        <TD align="center" style="cursor: default;" <%if(puntosMax7.equals(carrera7)){%>class="maxPunt"<%} if(!carrera7.equals("-")){%>onmouseover="esperaBusqueda('<%=usuario%>','7');" onmouseout="cancelarBusqueda();" onmousemove="mueveDiv(event);"<%}%>><%=carrera7%></TD>
        <TD align="center" style="cursor: default;" <%if(puntosMax8.equals(carrera8)){%>class="maxPunt"<%} if(!carrera8.equals("-")){%>onmouseover="esperaBusqueda('<%=usuario%>','8');" onmouseout="cancelarBusqueda();" onmousemove="mueveDiv(event);"<%}%>><%=carrera8%></TD>
        <TD align="center" style="cursor: default;" <%if(puntosMax9.equals(carrera9)){%>class="maxPunt"<%} if(!carrera9.equals("-")){%>onmouseover="esperaBusqueda('<%=usuario%>','9');" onmouseout="cancelarBusqueda();" onmousemove="mueveDiv(event);"<%}%>><%=carrera9%></TD>
        <TD align="center" style="cursor: default;" <%if(puntosMax10.equals(carrera10)){%>class="maxPunt"<%} if(!carrera10.equals("-")){%>onmouseover="esperaBusqueda('<%=usuario%>','10');" onmouseout="cancelarBusqueda();" onmousemove="mueveDiv(event);"<%}%>><%=carrera10%></TD>
        <TD align="center" style="cursor: default;" <%if(puntosMax11.equals(carrera11)){%>class="maxPunt"<%} if(!carrera11.equals("-")){%>onmouseover="esperaBusqueda('<%=usuario%>','11');" onmouseout="cancelarBusqueda();" onmousemove="mueveDiv(event);"<%}%>><%=carrera11%></TD>
        <TD align="center" style="cursor: default;" <%if(puntosMax12.equals(carrera12)){%>class="maxPunt"<%} if(!carrera12.equals("-")){%>onmouseover="esperaBusqueda('<%=usuario%>','12');" onmouseout="cancelarBusqueda();" onmousemove="mueveDiv(event);"<%}%>><%=carrera12%></TD>
        <TD align="center" style="cursor: default;" <%if(puntosMax13.equals(carrera13)){%>class="maxPunt"<%} if(!carrera13.equals("-")){%>onmouseover="esperaBusqueda('<%=usuario%>','13');" onmouseout="cancelarBusqueda();" onmousemove="mueveDiv(event);"<%}%>><%=carrera13%></TD>
        <TD align="center" style="cursor: default;" <%if(puntosMax14.equals(carrera14)){%>class="maxPunt"<%} if(!carrera14.equals("-")){%>onmouseover="esperaBusqueda('<%=usuario%>','14');" onmouseout="cancelarBusqueda();" onmousemove="mueveDiv(event);"<%}%>><%=carrera14%></TD>
        <TD align="center" style="cursor: default;" <%if(puntosMax15.equals(carrera15)){%>class="maxPunt"<%} if(!carrera15.equals("-")){%>onmouseover="esperaBusqueda('<%=usuario%>','15');" onmouseout="cancelarBusqueda();" onmousemove="mueveDiv(event);"<%}%>><%=carrera15%></TD>
        <TD align="center" style="cursor: default;" <%if(puntosMax16.equals(carrera16)){%>class="maxPunt"<%} if(!carrera16.equals("-")){%>onmouseover="esperaBusqueda('<%=usuario%>','16');" onmouseout="cancelarBusqueda();" onmousemove="mueveDiv(event);"<%}%>><%=carrera16%></TD>
        <TD align="center" style="cursor: default;" <%if(puntosMax17.equals(carrera17)){%>class="maxPunt"<%} if(!carrera17.equals("-")){%>onmouseover="esperaBusqueda('<%=usuario%>','17');" onmouseout="cancelarBusqueda();" onmousemove="mueveDiv(event);"<%}%>><%=carrera17%></TD>
        <TD align="center" style="cursor: default;" <%if(puntosMax18.equals(carrera18)){%>class="maxPunt"<%} if(!carrera18.equals("-")){%>onmouseover="esperaBusqueda('<%=usuario%>','18');" onmouseout="cancelarBusqueda();" onmousemove="mueveDiv(event);"<%}%>><%=carrera18%></TD>
        <TD align="center" style="cursor: default;" <%if(puntosMax19.equals(carrera19)){%>class="maxPunt"<%} if(!carrera19.equals("-")){%>onmouseover="esperaBusqueda('<%=usuario%>','19');" onmouseout="cancelarBusqueda();" onmousemove="mueveDiv(event);"<%}%>><%=carrera19%></TD>
        <TD align="right" style="cursor: default;"><%=total%></TD>
    </TR>
<%
}
%>
</table>