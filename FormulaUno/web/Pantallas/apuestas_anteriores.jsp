<%@page import="com.formula1.comunes.DatosPersona"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%
DatosPersona datosPersonaSesion = (DatosPersona)session.getAttribute("datosPersona");
String usuarioSesion = "";
if(datosPersonaSesion!=null)
    usuarioSesion=datosPersonaSesion.getNick();
%>
<h1>Consultar apuestas anteriores.</h1>
<BR />
<table id="tablaGeneral" border="0" width="96%" align="center">
    <TR>
        <TH></TH>
        <TH><img src="./Imagenes/banderas/01.png" alt="GP de Australia" title="GP de Australia" width="20px"/></TH>
        <TH><img src="./Imagenes/banderas/02.png" alt="GP de Malasia" title="GP de Malasia" width="20px"/></TH>
        <TH><img src="./Imagenes/banderas/03.png" alt="GP de China" title="GP de China" width="20px"/></TH>
        <TH><img src="./Imagenes/banderas/04.png" alt="GP de Turquía" title="GP de Turquía" width="20px"/></TH>
        <TH><img src="./Imagenes/banderas/05.png" alt="GP de España" title="GP de España" width="20px"/></TH>
        <TH><img src="./Imagenes/banderas/06.png" alt="GP de Mónaco" title="GP de Mónaco" width="20px"/></TH>
        <TH><img src="./Imagenes/banderas/07.png" alt="GP de Canadá" title="GP de Canadá" width="20px"/></TH>
        <TH><img src="./Imagenes/banderas/08.png" alt="GP de Europa" title="GP de Europa" width="20px"/></TH>
        <TH><img src="./Imagenes/banderas/09.png" alt="GP de Gran Bretaña" title="GP de Gran Bretaña" width="20px"/></TH>
        <TH><img src="./Imagenes/banderas/10.png" alt="GP de Alemania" title="GP de Alemania" width="20px"/></TH>
        <TH><img src="./Imagenes/banderas/11.png" alt="GP de Hungría" title="GP de Hungría" width="20px"/></TH>
        <TH><img src="./Imagenes/banderas/12.png" alt="GP de Bélgica" title="GP de Bélgica" width="20px"/></TH>
        <TH><img src="./Imagenes/banderas/13.png" alt="GP de Italia" title="GP de Italia" width="20px"/></TH>
        <TH><img src="./Imagenes/banderas/14.png" alt="GP de Singapur" title="GP de Singapur" width="20px"/></TH>
        <TH><img src="./Imagenes/banderas/15.png" alt="GP de Japón" title="GP de Japón" width="20px"/></TH>
        <TH><img src="./Imagenes/banderas/16.png" alt="GP de Corea" title="GP de Corea" width="20px"/></TH>
        <TH><img src="./Imagenes/banderas/17.png" alt="GP de India" title="GP de India" width="20px"/></TH>
        <TH><img src="./Imagenes/banderas/18.png" alt="GP de Abu Dhabi" title="GP de Abu Dhabi" width="20px"/></TH>
        <TH><img src="./Imagenes/banderas/19.png" alt="GP de Brasil" title="GP de Brasil" width="20px"/></TH>
    </TR>
<%
HashMap datosClasifAnteriores = (HashMap) request.getAttribute("datosClasifAnteriores");
ArrayList listaUsuarios = (ArrayList)request.getAttribute("listaUsuarios");
ArrayList nombresUsuarios = (ArrayList)request.getAttribute("nombresUsuarios");

for(int i=0; i<listaUsuarios.size();i++){
    String usuario = (String)listaUsuarios.get(i);
    HashMap datosUnPiloto = (HashMap)datosClasifAnteriores.get(usuario);

    if(datosUnPiloto==null) datosUnPiloto=new HashMap();

    String nombre=(String)nombresUsuarios.get(i);
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
    String rutaImagenLupa="./Imagenes/otras/lupa2.png";
    long antiCache=System.currentTimeMillis();
    %>
    <TR <%if(usuarioSesion.equals(usuario)){%>class="filaSel"<%}%>>
        <TD><%=nombre%></TD>
        <TD align="center">
            <%
            if(carrera1.equals("S")){%>
            <a href="./apuestaAnt.f1?antiCache=<%=antiCache%>&usuario=<%=usuario%>&carrera=1"><img src="<%=rutaImagenLupa%>" alt="Ver clasificación de <%=nombre%> para la carrera 1." title="Ver clasificación de <%=nombre%> para la carrera 1."/></a>
           <%}else{%> - <%}
            %>
        </TD>
        <TD align="center">
            <%
            if(carrera2.equals("S")){%>
            <a href="./apuestaAnt.f1?antiCache=<%=antiCache%>&usuario=<%=usuario%>&carrera=2"><img src="<%=rutaImagenLupa%>" alt="Ver clasificación de <%=nombre%> para la carrera 2." title="Ver clasificación de <%=nombre%> para la carrera 2."/></a>
           <%}else{%> - <%}
            %>
        </TD>
        <TD align="center">
            <%
            if(carrera3.equals("S")){%>
            <a href="./apuestaAnt.f1?antiCache=<%=antiCache%>&usuario=<%=usuario%>&carrera=3"><img src="<%=rutaImagenLupa%>" alt="Ver clasificación de <%=nombre%> para la carrera 3." title="Ver clasificación de <%=nombre%> para la carrera 3."/></a>
           <%}else{%> - <%}
            %>
        </TD>
        <TD align="center">
            <%
            if(carrera4.equals("S")){%>
            <a href="./apuestaAnt.f1?antiCache=<%=antiCache%>&usuario=<%=usuario%>&carrera=4"><img src="<%=rutaImagenLupa%>" alt="Ver clasificación de <%=nombre%> para la carrera 4." title="Ver clasificación de <%=nombre%> para la carrera 4."/></a>
           <%}else{%> - <%}
            %>
        </TD>
        <TD align="center">
            <%
            if(carrera5.equals("S")){%>
            <a href="./apuestaAnt.f1?antiCache=<%=antiCache%>&usuario=<%=usuario%>&carrera=5"><img src="<%=rutaImagenLupa%>" alt="Ver clasificación de <%=nombre%> para la carrera 5." title="Ver clasificación de <%=nombre%> para la carrera 5."/></a>
           <%}else{%> - <%}
            %>
        </TD>
        <TD align="center">
            <%
            if(carrera6.equals("S")){%>
            <a href="./apuestaAnt.f1?antiCache=<%=antiCache%>&usuario=<%=usuario%>&carrera=6"><img src="<%=rutaImagenLupa%>" alt="Ver clasificación de <%=nombre%> para la carrera 6." title="Ver clasificación de <%=nombre%> para la carrera 6."/></a>
           <%}else{%> - <%}
            %>
        </TD>
        <TD align="center">
            <%
            if(carrera7.equals("S")){%>
            <a href="./apuestaAnt.f1?antiCache=<%=antiCache%>&usuario=<%=usuario%>&carrera=7"><img src="<%=rutaImagenLupa%>" alt="Ver clasificación de <%=nombre%> para la carrera 7." title="Ver clasificación de <%=nombre%> para la carrera 7."/></a>
           <%}else{%> - <%}
            %>
        </TD>
        <TD align="center">
            <%
            if(carrera8.equals("S")){%>
            <a href="./apuestaAnt.f1?antiCache=<%=antiCache%>&usuario=<%=usuario%>&carrera=8"><img src="<%=rutaImagenLupa%>" alt="Ver clasificación de <%=nombre%> para la carrera 8." title="Ver clasificación de <%=nombre%> para la carrera 8."/></a>
           <%}else{%> - <%}
            %>
        </TD>
        <TD align="center">
            <%
            if(carrera9.equals("S")){%>
            <a href="./apuestaAnt.f1?antiCache=<%=antiCache%>&usuario=<%=usuario%>&carrera=9"><img src="<%=rutaImagenLupa%>" alt="Ver clasificación de <%=nombre%> para la carrera 9." title="Ver clasificación de <%=nombre%> para la carrera 9."/></a>
           <%}else{%> - <%}
            %>
        </TD>
        <TD align="center">
            <%
            if(carrera10.equals("S")){%>
            <a href="./apuestaAnt.f1?antiCache=<%=antiCache%>&usuario=<%=usuario%>&carrera=10"><img src="<%=rutaImagenLupa%>" alt="Ver clasificación de <%=nombre%> para la carrera 10." title="Ver clasificación de <%=nombre%> para la carrera 10."/></a>
           <%}else{%> - <%}
            %>
        </TD>
        <TD align="center">
            <%
            if(carrera11.equals("S")){%>
            <a href="./apuestaAnt.f1?antiCache=<%=antiCache%>&usuario=<%=usuario%>&carrera=11"><img src="<%=rutaImagenLupa%>" alt="Ver clasificación de <%=nombre%> para la carrera 11." title="Ver clasificación de <%=nombre%> para la carrera 11."/></a>
           <%}else{%> - <%}
            %>
        </TD>
        <TD align="center">
            <%
            if(carrera12.equals("S")){%>
            <a href="./apuestaAnt.f1?antiCache=<%=antiCache%>&usuario=<%=usuario%>&carrera=12"><img src="<%=rutaImagenLupa%>" alt="Ver clasificación de <%=nombre%> para la carrera 12." title="Ver clasificación de <%=nombre%> para la carrera 12."/></a>
           <%}else{%> - <%}
            %>
        </TD>
        <TD align="center">
            <%
            if(carrera13.equals("S")){%>
            <a href="./apuestaAnt.f1?antiCache=<%=antiCache%>&usuario=<%=usuario%>&carrera=13"><img src="<%=rutaImagenLupa%>" alt="Ver clasificación de <%=nombre%> para la carrera 13." title="Ver clasificación de <%=nombre%> para la carrera 13."/></a>
           <%}else{%> - <%}
            %>
        </TD>
        <TD align="center">
            <%
            if(carrera14.equals("S")){%>
            <a href="./apuestaAnt.f1?antiCache=<%=antiCache%>&usuario=<%=usuario%>&carrera=14"><img src="<%=rutaImagenLupa%>" alt="Ver clasificación de <%=nombre%> para la carrera 14." title="Ver clasificación de <%=nombre%> para la carrera 14."/></a>
           <%}else{%> - <%}
            %>
        </TD>
        <TD align="center">
            <%
            if(carrera15.equals("S")){%>
            <a href="./apuestaAnt.f1?antiCache=<%=antiCache%>&usuario=<%=usuario%>&carrera=15"><img src="<%=rutaImagenLupa%>" alt="Ver clasificación de <%=nombre%> para la carrera 15." title="Ver clasificación de <%=nombre%> para la carrera 15."/></a>
           <%}else{%> - <%}
            %>
        </TD>
        <TD align="center">
            <%
            if(carrera16.equals("S")){%>
            <a href="./apuestaAnt.f1?antiCache=<%=antiCache%>&usuario=<%=usuario%>&carrera=16"><img src="<%=rutaImagenLupa%>" alt="Ver clasificación de <%=nombre%> para la carrera 16." title="Ver clasificación de <%=nombre%> para la carrera 16."/></a>
           <%}else{%> - <%}
            %>
        </TD>
        <TD align="center">
            <%
            if(carrera17.equals("S")){%>
            <a href="./apuestaAnt.f1?antiCache=<%=antiCache%>&usuario=<%=usuario%>&carrera=17"><img src="<%=rutaImagenLupa%>" alt="Ver clasificación de <%=nombre%> para la carrera 17." title="Ver clasificación de <%=nombre%> para la carrera 17."/></a>
           <%}else{%> - <%}
            %>
        </TD>
        <TD align="center">
            <%
            if(carrera18.equals("S")){%>
            <a href="./apuestaAnt.f1?antiCache=<%=antiCache%>&usuario=<%=usuario%>&carrera=18"><img src="<%=rutaImagenLupa%>" alt="Ver clasificación de <%=nombre%> para la carrera 18." title="Ver clasificación de <%=nombre%> para la carrera 18."/></a>
           <%}else{%> - <%}
            %>
        </TD>
        <TD align="center">
            <%
            if(carrera19.equals("S")){%>
            <a href="./apuestaAnt.f1?antiCache=<%=antiCache%>&usuario=<%=usuario%>&carrera=19"><img src="<%=rutaImagenLupa%>" alt="Ver clasificación de <%=nombre%> para la carrera 19." title="Ver clasificación de <%=nombre%> para la carrera 19."/></a>
           <%}else{%> - <%}
            %>
        </TD>
    </TR>
<%
}
%>
</table>