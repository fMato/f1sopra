var peticionRealizada=false;
var idPeticion=0;

function muestraContenido() {
  if(peticion_http.readyState == READY_STATE_COMPLETE) {
    if(peticion_http.status == 200) {
      document.getElementById("contenidoAjax").innerHTML=peticion_http.responseText;
    }
  }
}

function buscaClasificacion(usuario, carrera, tempX, tempY) {
    var d = new Date();
    cargaContenido("./apuestaAntAjax.ajax?usuario="+usuario+"&carrera="+carrera+"&antiCache="+d.getTime(), "GET", muestraContenido);
    posicionaDiv2(tempX, tempY);
}

function esperaBusqueda(usuario, carrera, event, elem){
    document.getElementById("contenidoAjax").innerHTML="<img src='./Imagenes/otras/espera.gif' alt='espera...'/>";
    var tempX = 0;
    var tempY = 0;

    tempX=findPosX(elem);
    tempY=findPosY(elem);

    if (tempX < 0){tempX = 0;}
	if (tempY < 0){tempY = 0;}
    peticionRealizada=true;

    if(carrera<9){
        tempY=tempY-92;
        tempX=tempX-160;
        //La variable IE determina si estamos utilizando IE
	var IE = document.all?true:false;
        if(IE){
            tempY=tempY-7;
            tempX=tempX+4;
        }
    }else{
        tempY=tempY-92;
        tempX=tempX-410
        //La variable IE determina si estamos utilizando IE
	var IE = document.all?true:false;
        if(IE){
            tempY=tempY-7;
            tempX=tempX+12;
        }
    }

    idPeticion=setTimeout("buscaClasificacion('"+usuario+"', '"+carrera+"', "+tempX+", "+tempY+")", 1500);
}

function cancelarBusqueda(){
    if(peticionRealizada){
        peticionRealizada=false;
        clearInterval(idPeticion);
        idPeticion=0;
        document.getElementById('contenidoAjax').style.display='none';
    }
}

function posicionaDiv2(tempX, tempY)
{

	document.getElementById('contenidoAjax').style.top = tempY;
	document.getElementById('contenidoAjax').style.left = tempX;
	document.getElementById('contenidoAjax').style.display='block';
	return;
}
function posicionaDiv(event)
{
	//La variable IE determina si estamos utilizando IE
	var IE = document.all?true:false;
	//Si no utilizamos IE capturamos el evento del mouse
	if (!IE) document.captureEvents(Event.MOUSEMOVE)

	var tempX = 0;
	var tempY = 0;

	if(IE)
	{ //para IE
		tempX = event.clientX;
		tempY = event.clientY;
	}else{ //para netscape
		tempX = event.pageX;
		tempY = event.pageY;
	}

	if (tempX < 0){tempX = 0;}
	if (tempY < 0){tempY = 0;}

	document.getElementById('contenidoAjax').style.top = tempY-100;
	document.getElementById('contenidoAjax').style.left = tempX-175;
	document.getElementById('contenidoAjax').style.display='block';
	return;
}