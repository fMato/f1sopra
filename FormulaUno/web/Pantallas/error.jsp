<%
String error = (String) request.getAttribute("ERROR");
%>
<BR />
<BR />
<div align="center">
    <div class="contenedorError">
        <div class="tituloError">Error</div>
        <div class="contenidoError"><%=error%></div>
    </div>
</div>