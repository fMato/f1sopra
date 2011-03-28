<%
long antiCache=System.currentTimeMillis();
%>
<script type="text/javascript">
    function enviarLogin(){
        var f=document.frmDatosLogin;
        if(f.usuario_login.value==""){
            alert("El usuario es obligatorio.");
            return false;
        }else if(f.pass_login.value==""){
            alert("La contraseña es obligatoria.");
            return false;
        }
        f.pass_login.value="";
        f.action="./hacerLogin.f1?antiCache=<%=antiCache%>";
        f.submit();
    }

    document.onkeypress=function(e){
    var esIE=(document.all);
    var esNS=(document.layers);
    tecla=(esIE) ? event.keyCode : e.which;
    if(tecla==13){
            enviarLogin(); return false;
      }
    }
</script>
<h1>Identificación de usuario.</h1>
<BR />
<BR />
<BR />
<BR />
<div align="center">
    <div style="width:310px;text-align:left;">
        <form name="frmDatosLogin" action="./hacerLogin.f1" method="post">
        <p>
            <label class="loginLabel" for="usuario_login">Usuario:</label>
            <input type="text" name="usuario_login" id="usuario_login" value="" maxlength="12"/>
        </p>
        <p>
            <label class="loginLabel" for="pass_login">Contrase&ntilde;a:</label>
            <input type="password" name="pass_login" id="pass_login" maxlength="40" value="" onkeyup="document.frmDatosLogin.pass_login_encrpt.value=SHA1(this.value);"/>
            &#160;<a href="#" onClick="enviarLogin();" class="enlacef1" title="Identificarse">Entrar</a>
        </p>
        <p>
            <input type="hidden" name="pass_login_encrpt" id="pass_login_encrpt" value=""/>
            <input type="hidden" name="accion" id="accion" value="login"/>
        </p>
        </form>
    </div>
</div>