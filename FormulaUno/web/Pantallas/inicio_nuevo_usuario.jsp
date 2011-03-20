<script type="text/javascript">
    function darAlta(){
        if(document.frmDatos.nombre_alta.value==""){
            alert("El nombre es obligatorio.");
        }else if(document.frmDatos.nick_alta.value==""){
            alert("El nick es obligatorio.");
        }else if(document.frmDatos.correo_alta.value==""){
            alert("El correo es obligatorio.");
        }else if(document.frmDatos.pass_alta.value==""){
            alert("La contraseña es obligatoria.");
        }else if(document.frmDatos.pass_alta_re.value==""){
            alert("Debes repetir la contraseña.");
        }else if(document.frmDatos.pass_alta.value!=document.frmDatos.pass_alta_re.value){
            alert("Las contraseñas no coinciden.");
        }else{
            document.frmDatos.pass_alta.value="";
            document.frmDatos.pass_alta_re.value="";
            document.frmDatos.submit();
        }
    }
document.onkeypress=function(e){
    var esIE=(document.all);
    var esNS=(document.layers);
    tecla=(esIE) ? event.keyCode : e.which;
    if(tecla==13){
            darAlta(); return false;
      }
    }
</script>

<h1>Alta de usuario</h1>
<BR />
<BR />
<BR />
<BR />
<div align="center">
    <div style="width:500px;text-align:left;">
        <form name="frmDatos" action="./altaUsuario.f1" method="post">
        <p>
            <label class="posicionesLabel" for="nombre_alta">Nombre:</label>
            <input type="text" name="nombre_alta" id="nombre_alta" value="" maxlength="50" size="40"/>
        </p>
        <p>
            <label class="posicionesLabel" for="nick_alta">Nick:</label>
            <input type="text" name="nick_alta" id="nick_alta" value="" maxlength="12" size="12"/>
        </p>
        <p>
            <label class="posicionesLabel" for="correo_alta">Correo:</label>
            <input type="text" name="correo_alta" id="correo_alta" value="" maxlength="100" size="50"/>
        </p>
        <p>
            <label class="posicionesLabel" for="pass_alta">Contrase&ntilde;a:</label>
            <input type="password" name="pass_alta" id="pass_alta" maxlength="40" value="" onkeyup="document.frmDatos.pass_alta_encrpt.value=SHA1(this.value);"/>
        </p>
        <p>
            <label class="posicionesLabel" for="pass_alta_re">Repetir contrase&ntilde;a:</label>
            <input type="password" name="pass_alta_re" id="pass_alta_re" maxlength="40" value=""/>
            &#160;<a href="#" onClick="darAlta();" class="enlacef1" title="Crear un nuevo usuario">Continuar</a>
        </p>
        <p>
            <input type="hidden" name="pass_alta_encrpt" id="pass_alta_encrpt" value=""/>
            <input type="hidden" name="accion" id="accion" value="Alta"/>
        </p>
        </form>
    </div>
</div>