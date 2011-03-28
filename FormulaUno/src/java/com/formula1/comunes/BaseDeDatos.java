package com.formula1.comunes;

import java.sql.*;

/**
 *
 * @author Mato
 */
public class BaseDeDatos{
        private static final String baseDeDatos="";
        private static final String usuario="";
        private static final String password="";
        
        public BaseDeDatos(){
            // Vacio.
        }

        public static Connection establecerConexion() throws SQLException{
            System.out.println("Se establece conexión con la base de datos.");
         // Se registra el Driver de MySQL
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());
        // Establecemos la conexión con la base de datos.
           Connection conexion = DriverManager.getConnection ("jdbc:mysql://127.0.0.1/"+baseDeDatos,usuario, password);

           return conexion;
        }

        public static void cerrarConexion(Connection conexion) throws SQLException{
            System.out.println("Se cierra la conexión con la base de datos.");

            // Cerramos la conexion a la base de datos.
            conexion.close();
        }

}
