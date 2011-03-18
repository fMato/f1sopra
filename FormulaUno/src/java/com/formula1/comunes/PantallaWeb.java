package com.formula1.comunes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mato
 */
public interface PantallaWeb {

    HttpServletRequest processRequest (HttpServletRequest request, HttpServletResponse response, DatosPantalla datosPantalla);

}
