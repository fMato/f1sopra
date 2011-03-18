<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
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
    <TR>
        <TD><%=nombre%></TD>
        <TD align="center"><%=carrera1%></TD>
        <TD align="center"><%=carrera2%></TD>
        <TD align="center"><%=carrera3%></TD>
        <TD align="center"><%=carrera4%></TD>
        <TD align="center"><%=carrera5%></TD>
        <TD align="center"><%=carrera6%></TD>
        <TD align="center"><%=carrera7%></TD>
        <TD align="center"><%=carrera8%></TD>
        <TD align="center"><%=carrera9%></TD>
        <TD align="center"><%=carrera10%></TD>
        <TD align="center"><%=carrera11%></TD>
        <TD align="center"><%=carrera12%></TD>
        <TD align="center"><%=carrera13%></TD>
        <TD align="center"><%=carrera14%></TD>
        <TD align="center"><%=carrera15%></TD>
        <TD align="center"><%=carrera16%></TD>
        <TD align="center"><%=carrera17%></TD>
        <TD align="center"><%=carrera18%></TD>
        <TD align="center"><%=carrera19%></TD>
        <TD align="center"><%=total%></TD>
    </TR>
<%
}
%>
</table>