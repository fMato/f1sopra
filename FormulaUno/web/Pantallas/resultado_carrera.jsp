<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%
long antiCache=System.currentTimeMillis();
%>

<script type="text/javascript">
    var mensaje="";
    function guardarResultado(){
        if(validarApuesta()){
            document.getElementById("semiTransp").style.display="inline";
            document.frmDatos.submit();
        }
        else
            alert(mensaje);
    }
    function validarApuesta(){
        mensaje="";
        var validacionApuestas=new Array(10);
        validacionApuestas[0]=document.frmDatos.primero.options[document.frmDatos.primero.selectedIndex].value;
        validacionApuestas[1]=document.frmDatos.segund.options[document.frmDatos.segund.selectedIndex].value;
        validacionApuestas[2]=document.frmDatos.tercero.options[document.frmDatos.tercero.selectedIndex].value;
        validacionApuestas[3]=document.frmDatos.cuarto.options[document.frmDatos.cuarto.selectedIndex].value;
        validacionApuestas[4]=document.frmDatos.quinto.options[document.frmDatos.quinto.selectedIndex].value;
        validacionApuestas[5]=document.frmDatos.sexto.options[document.frmDatos.sexto.selectedIndex].value;
        validacionApuestas[6]=document.frmDatos.septimo.options[document.frmDatos.septimo.selectedIndex].value;
        validacionApuestas[7]=document.frmDatos.octavo.options[document.frmDatos.octavo.selectedIndex].value;
        validacionApuestas[8]=document.frmDatos.noveno.options[document.frmDatos.noveno.selectedIndex].value;
        validacionApuestas[9]=document.frmDatos.decimo.options[document.frmDatos.decimo.selectedIndex].value;

        for(var i=0; i<validacionApuestas.length;i++){
            if(validacionApuestas[i]==""){
                mensaje="Has dejado algún puesto en blanco.";
                return false;
            }
            
            for(var j=i+1;j<validacionApuestas.length;j++){
                if(validacionApuestas[i]==validacionApuestas[j]){
                    mensaje="Hay pilotos repetidos entre los 10 primeros.";
                    return false;
                }
            }
        }

        if(document.frmDatos.pole.options[document.frmDatos.pole.selectedIndex].value==""){
            mensaje="Te falta por elegir la pole.";
            return false;
        }

        if(document.frmDatos.carrera.options[document.frmDatos.carrera.selectedIndex].value==""){
            mensaje="Te falta por elegir la carrera.";
            return false;
        }

        return true;
    }
</script>

<h1>Introducir el resultado.</h1>

<FORM name="frmDatos" method="post" action="./guardarResultadoCarrera.f1?antiCache=<%=antiCache%>">
<input type="hidden" name="accion" value="guardar"/>
<div style="width:360px;float:left;">
        <p>
            <label class="posicionesLabel" for="pole">Pole:</label>
            <select name="pole">
                <jsp:include page="./comboPilotos.jsp?indSelect=pole" flush="false"/>
            </select>
        </p>
        <hr style="width:85%"/>
        <p>
            <label class="posicionesLabel" for="primero">Primer clasificado:</label>
            <select name="primero">
                <jsp:include page="./comboPilotos.jsp?indSelect=primero" flush="false"/>
            </select><br /><br />
            <label class="posicionesLabel" for="segund">Segundo clasificado:</label>
            <select name="segund">
                <jsp:include page="./comboPilotos.jsp?indSelect=segundo" flush="false"/>
            </select><br /><br />
            <label class="posicionesLabel" for="tercero">Tercer clasificado:</label>
            <select name="tercero">
                <jsp:include page="./comboPilotos.jsp?indSelect=tercero" flush="false"/>
            </select><br /><br />
            <label class="posicionesLabel" for="cuarto">Cuarto clasificado:</label>
            <select name="cuarto">
                <jsp:include page="./comboPilotos.jsp?indSelect=cuarto" flush="false"/>
            </select><br /><br />
            <label class="posicionesLabel" for="quinto">Quinto clasificado:</label>
            <select name="quinto">
                <jsp:include page="./comboPilotos.jsp?indSelect=quinto" flush="false"/>
            </select><br /><br />
            <label class="posicionesLabel" for="sexto">Sexto clasificado:</label>
            <select name="sexto">
                <jsp:include page="./comboPilotos.jsp?indSelect=sexto" flush="false"/>
            </select><br /><br />
            <label class="posicionesLabel" for="septimo">Séptimo clasificado:</label>
            <select name="septimo">
                <jsp:include page="./comboPilotos.jsp?indSelect=septimo" flush="false"/>
            </select><br /><br />
            <label class="posicionesLabel" for="octavo">Octavo clasificado:</label>
            <select name="octavo">
                <jsp:include page="./comboPilotos.jsp?indSelect=octavo" flush="false"/>
            </select><br /><br />
            <label class="posicionesLabel" for="noveno">Noveno clasificado:</label>
            <select name="noveno">
                <jsp:include page="./comboPilotos.jsp?indSelect=noveno" flush="false"/>
            </select><br /><br />
            <label class="posicionesLabel" for="decimo">Décimo clasificado:</label>
            <select name="decimo">
                <jsp:include page="./comboPilotos.jsp?indSelect=decimo" flush="false"/>
            </select>
            <BR />
            <BR />
            <span style="float:right;">
                <a href="#" onclick="guardarResultado();" class="enlacef1">Guardar resultado</a>
            </span>
        </p>
</div>
<div style="width:350px;float:left;">
    <p>
        <%
        ArrayList carreras = (ArrayList) request.getAttribute("carreras");

        if(carreras==null){
            carreras=new ArrayList();
        }
        %>
    <label class="apuestaAnteriorLabel" for="carrera">Carrera:</label>
    <select name="carrera">
        <option value="">--Selecciona una carrera--</option>
        <%
        String indCarrSelect=(String) request.getAttribute("ultimaCarreraDisputada");
        if(indCarrSelect==null)indCarrSelect="";
        for(int i=0; i<carreras.size(); i++){
            HashMap carreraItem = (HashMap) carreras.get(i);
            String numeroCarrera = (String) carreraItem.get("identificador");
            String nombreCarrera = (String) carreraItem.get("nombre");
            %>
            <option value="<%=numeroCarrera%>"<%if(indCarrSelect.equals(numeroCarrera)){ %>selected="true"<%}%>>
                <%=nombreCarrera%>
            </option>
            <%
        }
        %>
    </select>
    <BR />
    <BR />
    <input type="checkbox" name="chCont" id="chCont" value="S" checked="true" /> Contabilizar después de guardar.
    <p>
    <BR />
    <BR />
    <BR />
    <BR />
    <%
    String mensajeConfirm = (String)request.getAttribute("mensajeConfirm");
    if(mensajeConfirm==null)mensajeConfirm="";

    if(!mensajeConfirm.equals("")){%>
    <div id="divConfirmacion">
        <%=mensajeConfirm%>
    </div>
    <script type="text/javascript">
        function ocultaDivConfirm(){
            document.getElementById("divConfirmacion").style.display="none";
        }
        setTimeout("ocultaDivConfirm()",6000);
    </script>
    <%}%>
</div>
</FORM>

<div id="semiTransp">
    <BR /><BR /><BR />
    Estamos contabilizando los puntos,<BR /> esto puede tardar unos segundos, espera por favor...<BR /><BR />
    <img src="./Imagenes/otras/espera.gif" alt="espera..."/>
</div>