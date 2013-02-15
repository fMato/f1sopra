package f1sopra;

import java.sql.*;
import javax.naming.*;



import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;


public class Conectar {

    private static final String driver = "com.mysql.jdbc.Driver";// = "COM.cloudscape.core.JDBCDriver";
    private static final String driver2 = "org.gjt.mm.mysql.Driver";
    //private static final String protocol1 = "jdbc:mysql://localhost/pruebasgecofuensa";
    private static final String protocol1 = "jdbc:mysql://localhost/f1Sopra";
    //private static final String protocol2 = "jdbc:mysql://localhost/";
    
    
        
    private static final String usuario="f1Sopra";
    private static final String password = "Traicion";

    private Conectar() {

    }
    /**
     * Devuelve una conexion a la base de datos, para poder hacer consultas, ...
     * @return Un objeto Connection para conectar con la base de datos ya definida en la clase.
     * @throws javax.naming.NamingException
     * @throws java.sql.SQLException
     */
    public static Connection getConnection() throws NamingException, SQLException {


        
        //System.out.println("Conector");
        try {
            //System.out.println("Se procede a conectar");
            Class.forName(driver).newInstance();
            Connection connection = DriverManager.getConnection(protocol1, usuario, password);
            //System.out.println("Se ha conectado");
            return connection;
        } catch (InstantiationException ex) {
            System.out.println("error 1");
        } catch (IllegalAccessException ex) {
            System.out.println("error 2");
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Excepcion Driver");
            System.out.println("Error excepcion: "+cnfe.getMessage());
            cnfe.printStackTrace();
            throw new SQLException("Driver <" + driver + "> not found in the classpath.");
        }
        return null;

    }
}
