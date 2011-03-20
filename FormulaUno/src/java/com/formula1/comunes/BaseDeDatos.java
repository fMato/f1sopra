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

        public Connection establecerConexion() throws SQLException{
            System.out.println(this.getClass().getName()+".establecerConexion()");
         // Se registra el Driver de MySQL
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());
        // Establecemos la conexión con la base de datos.
           Connection conexion = DriverManager.getConnection ("jdbc:mysql://127.0.0.1/"+baseDeDatos,usuario, password);

           return conexion;
        }

        public void cerrarConexion(Connection conexion) throws SQLException{
            System.out.println(this.getClass().getName()+".cerrarConexion()");

            // Cerramos la conexion a la base de datos.
            conexion.close();
        }

}
