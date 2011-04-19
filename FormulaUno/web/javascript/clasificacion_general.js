var peticionRealizada=false;
var idPeticion=0;

function muestraContenido() {
  if(peticion_http.readyState == READY_STATE_COMPLETE) {
    if(peticion_http.status == 200) {
      document.getElementById("contenidoAjax").innerHTML=peticion_http.responseText;
    }
  }
}

function buscaClasificacion(usuario, carrera) {
    var d = new Date();
    document.getElementById('contenidoAjax').style.display='block';
    cargaContenido("./apuestaAntAjax.ajax?usuario="+usuario+"&carrera="+carrera+"&antiCache="+d.getTime(), "GET", muestraContenido);
}

function esperaBusqueda(usuario, carrera){
    document.getElementById("contenidoAjax").innerHTML="<img src='./Imagenes/otras/espera.gif' alt='espera...'/>";
    peticionRealizada=true;

    idPeticion=setTimeout("buscaClasificacion('"+usuario+"', '"+carrera+"')", 1500);
}

function mueveDiv(event){
    //determina un margen de pixels del div al raton
    margin=5;

    //La variable IE determina si estamos utilizando IE
    var IE = document.all?true:false;
    //Si no utilizamos IE capturamos el evento del mouse
    if (!IE) document.captureEvents(Event.MOUSEMOVE)

    var tempX = 0;
    var tempY = 0;

    if(IE)
    { //para IE
            tempX = event.clientX + document.body.scrollLeft;
            tempY = event.clientY + document.body.scrollTop;
    }else{ //para netscape
            tempX = event.pageX;
            tempY = event.pageY;
    }
    if (tempX < 0){tempX = 0;}
    if (tempY < 0){tempY = 0;}

    tempX+=margin;
    tempY+=margin;
    if(parseInt(tempY)>350){
        tempY=tempY-210;
    }

    document.getElementById('contenidoAjax').style.top = tempY;
    document.getElementById('contenidoAjax').style.left = tempX;
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

function ordenarCarrera(carrera, titulo){

    document.getElementById("semiTransp").style.display="inline";
    document.getElementById("nombreClasificacion").innerHTML=titulo;

    var d = new Date();
    cargaContenido("./calsificacionAjax.ajax?ordenarPor="+carrera+"&antiCache="+d.getTime(), "GET", muestraContenidoOrdenado);

}

function muestraContenidoOrdenado() {
    
  if(peticion_http.readyState == READY_STATE_COMPLETE) {
    if(peticion_http.status == 200) {
      document.getElementById("tablaClasificacion").innerHTML=peticion_http.responseText;
      document.getElementById("semiTransp").style.display="none";
    }
  }
}