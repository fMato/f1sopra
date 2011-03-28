/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.formula1.comunes;

/**
 *
 * @author Mato
 */
public class Utils {

    public static String NombreMeses(String mes){
        System.out.println("Obteniendo nombre del mes "+mes);
        
        String nombreMes="";
        
        if(mes.equals("1") || mes.equals("01")){
		nombreMes="Enero";
	}else if(mes.equals("2") || mes.equals("02")){
		nombreMes="Febrero";
	}else if(mes.equals("3") || mes.equals("03")){
		nombreMes="Marzo";
	}else if(mes.equals("4") || mes.equals("04")){
		nombreMes="Abril";
	}else if(mes.equals("5") || mes.equals("05")){
		nombreMes="Mayo";
	}else if(mes.equals("6") || mes.equals("06")){
		nombreMes="Junio";
	}else if(mes.equals("7") || mes.equals("07")){
		nombreMes="Julio";
	}else if(mes.equals("8") || mes.equals("08")){
		nombreMes="Agosto";
	}else if(mes.equals("9") || mes.equals("09")){
		nombreMes="Septiembre";
	}else if(mes.equals("10")){
		nombreMes="Octubre";
	}else if(mes.equals("11")){
		nombreMes="Noviembre";
	}else if(mes.equals("12")){
		nombreMes="Diciembre";
	}

        return nombreMes;
    }

}
