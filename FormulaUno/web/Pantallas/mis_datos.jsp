<%@page import="java.util.HashMap"%>
<%
HashMap datosUsuario = (HashMap)request.getAttribute("datosUsuario");
%>
<script type="text/javascript">
var ncrpt = "<%=(String)datosUsuario.get("pass")%>";
var mail = "<%=(String)datosUsuario.get("correo")%>";
function cambiarPass(){
    document.getElementById("bloquePassAct").style.display="none";
    document.getElementById("bloqueNuevaPass1").style.display="block";
    document.getElementById("bloqueNuevaPass2").style.display="block";
    document.getElementById("bloqueNuevaPass3").style.display="block";
}
function modificarDatos(){
    var hayModif=false;
    var modPass=false;
    
    var mensaje1="Vas a modificar los siguientes datos:\n";

    if(document.getElementById("correoAct").value==""){
        alert("El correo es obligatorio.");
        return false;
    }else if(document.getElementById("correoAct").value!=mail){
        mensaje1+="Tu correo.\n";
        hayModif=true;
    }
    if(document.getElementById("nuevaPass1").value!="" || document.getElementById("nuevaPass2").value!="" || document.getElementById("nuevaPass3").value!=""){
        hayModif=true;
        if(document.getElementById("nuevaPass1").value==""){
            alert("Debes escribir tu contraseña actual.");
            return false;
        }else if(document.getElementById("nuevaPass2").value==""){
            alert("Debes escribir tu nueva contraseña.");
            return false;
        }else if(document.getElementById("nuevaPass3").value==""){
            alert("Debes repetir tu nueva contraseña.");
            return false;
        }else if(SHA1(document.getElementById("nuevaPass1").value)!=ncrpt){
            alert("La contraseña actual que has puesto no coincide con la que tenemos guardada.");
            return false;
        }else if(document.getElementById("nuevaPass2").value!=document.getElementById("nuevaPass3").value){
            alert("Las contraseñas nuevas no coinciden.");
            return false;
        }else{
            mensaje1+="Tu contraseña.\n";
            modPass=true;
        }
    }

    if(hayModif){
        if(confirm(mensaje1)){
           document.frmDatos.correo.value=document.getElementById("correoAct").value;
           if(modPass)
                document.frmDatos.pass.value=SHA1(document.getElementById("nuevaPass2").value);
           else{
                document.frmDatos.pass.value=ncrpt;
           }
           document.frmDatos.submit();
        }
    }else{
        alert("No has modificado nada...");
    }
}

document.onkeypress=function(e){
    var esIE=(document.all);
    var esNS=(document.layers);
    tecla=(esIE) ? event.keyCode : e.which;
    if(tecla==13){
            modificarDatos(); return false;
      }
    }
</script>
<form name="frmDatos" method="post" action="./modificarMisDatos.f1">
    <input type="hidden" name="nick" value="<%=(String)datosUsuario.get("nick")%>"/>
    <input type="hidden" name="correo" value=""/>
    <input type="hidden" name="pass" value=""/>
</form>
<h1>Mis datos.</h1>
<BR />
<BR />
<p>
    <label class="apuestaAnteriorLabel">Nombre:</label> <b><%=(String)datosUsuario.get("nombre")%></b>
</p>
<p>
    <label class="apuestaAnteriorLabel">Nick:</label> <b><%=(String)datosUsuario.get("nick")%></b>
</p>
<p>
    <label class="apuestaAnteriorLabel" for="correoAct">Correo:</label>
    <input size="50" maxlength="100" type="text" name="correoAct" id="correoAct" value="<%=(String)datosUsuario.get("correo")%>"/>
</p>
<p id="bloquePassAct">
    <label class="apuestaAnteriorLabel">Contraseña:</label> <a class="enlacef1" href="#" onClick="cambiarPass();">Quiero cambiarla.</a>
</p>
<p id="bloqueNuevaPass1" style="display: none;">
    <label class="posicionesLabel" for="nuevaPass1">Contraseña actual:</label>
    <input size="50" maxlength="100" type="password" name="nuevaPass1" id="nuevaPass1" value=""/>
</p>
<p id="bloqueNuevaPass2" style="display: none;">
    <label class="posicionesLabel" for="nuevaPass2">Nueva contraseña:</label>
    <input size="50" maxlength="100" type="password" name="nuevaPass2" id="nuevaPass2" value=""/>
</p>
<p id="bloqueNuevaPass3" style="display: none;">
    <label class="posicionesLabel" for="nuevaPass3">Repítela:</label>
    <input size="50" maxlength="100" type="password" name="nuevaPass3" id="nuevaPass3" value=""/>
</p>
<p>
    <a href="#" class="enlacef1" onClick="modificarDatos();">Modificar mis datos.</a>
</p>