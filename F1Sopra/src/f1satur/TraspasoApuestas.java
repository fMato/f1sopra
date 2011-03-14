package f1satur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class TraspasoApuestas {

	private static Connection conexion = null;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("Comienza el proceso de actualizacion de apuestas de carrera");
		try {
			
			//comprobamos los parametros
			if(args.length < 0){
				//no se recibe ningun parametro
				throw new Exception ("Error: ActualizarApuestas: No se ha recibido el " +
						"indicador de la carrera a actualizar");
			} else {
				if(args[0]!= null && !args[0].equals("")) {
					//el par�metro recogido es correcto, continua la ejecucion
					
					//conectamos a la base de datos
					conexion = connectDataBase();
//					conexion = otraFormaDeConectar();

					
					if(conexion != null){
						//realizamos el tratamiento para la actualizacion
						actualizarApuestaDefinitiva(args[0]);
					} else {
						throw new Exception ("Error: ActualizarApuestas: No es posible la conexion con la BBDD");
					}
					
				} else {
					//no se recibe ningun parametro
					throw new Exception ("Error: ActualizarApuestas: El parametro recibido es incorrecto");
				}	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		
		
		System.out.println("Comienza el proceso de actualizacion de apuestas de carrera");
		
	}
	
	/**
	 * Conexi�n a la base de datos de apuesta
	 * @return
	 */
	private static Connection connectDataBase () {
		System.out.println("Comienza el traspaso de apuestas");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexion  = DriverManager.getConnection("jdbc:mysql://localhost/f1Sopra", "f1Sopra", "Traicion");
			Statement sentencia;
			sentencia = conexion.createStatement();
			sentencia.execute("select * from apuestas");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Acaba el traspaso de apuestas");
		
		return conexion;
		
	}
	
	/**
	 * M�todo que ejecuta la sentencia sql para guardar las apuestas definitivas de una carrera
	 * @param carrera: par�metro que indica el numero de carrera a tratar
	 * @return result: n�mero de filas actualizadas
	 * @throws SQLException 
	 */
	private static int actualizarApuestaDefinitiva (String carrera) throws SQLException{
		int result = 0;
		
		System.out.println("Comienza la ejecuci�n del m�todo actualizarApuestaDefinitiva");
		
		//obtener los usuarios participantes de la tabla USUARIOS?? --> APUESTAS
		//"select USUARIO from APUESTAS"
		Statement sentencia;
		sentencia = conexion.createStatement();
		ResultSet res1 = sentencia.executeQuery("select usuario from apuestas");
		if(res1 != null){
			while (res1.next()){
				String user = (String)res1.getObject("usuario");
				System.out.println("Apuesta usuario: "+user); 
				//obtener por cada usuario la apuesta de la tabla APUESTAS
				//"select * from APUESTAS where USUARIO like user"
				ResultSet res2 = sentencia.executeQuery("select * from apuestas where usuario='" + user+"'");
				if(res2.next()){
					//recogemos la fila y la insertamos
					String dato1 = (String) res2.getObject("pole");
					String dato2 = (String) res2.getObject("primero");
					String dato3 = (String) res2.getObject("segundo");
					String dato4 = (String) res2.getObject("tercero");
					String dato5 = (String) res2.getObject("cuarto");
					String dato6 = (String) res2.getObject("quinto");
					String dato7 = (String) res2.getObject("sexto");
					String dato8 = (String) res2.getObject("septimo");
					String dato9 = (String) res2.getObject("octavo");
					String dato10 = (String) res2.getObject("noveno");
					String dato11 = (String) res2.getObject("decimo");
					
					//sentencia.executeUpdate("update apuestas_carreras set pole = '" +dato1 + "',primero = '" + dato2 + "',segundo = '" + dato3 + 
					//		"',tercero = '" + dato4 + "' ,cuarto = '" + dato5 + "',quinto = '" + dato6 + "',sexto = '" + dato7 +
					//		"',septimo = '" + dato8 + "' ,octavo = '" + dato9 + "',noveno = '" + dato10 + "',decimo = '" +dato11 + 
					//		"' where usuario='" + user + "' and carrera = " + carrera);
							
					sentencia.executeUpdate("insert into apuestas_carreras values('"+user+"', '"+carrera+"', '" +dato1 + "', '" + dato2 + "', '" + dato3 + 
							"' ,'" + dato4 + "', '" + dato5 + "', '" + dato6 + "', '" + dato7 +
							"' ,'" + dato8 + "', '" + dato9 + "', '" + dato10 + "', '" +dato11 + "')");
					//guardar por cada usuario la apuesta en la tabla APUESTAS_CARRERAS
					//"update APUESTAS_CARRERAS set USUARIO,CARRERA,POLE,PRIMERO,SEGUNDO,TERCERO,CUARTO,QUINTO,SEXTO,
					//SEPTIMO,OCTAVO,NOVENO,DECIMO where USUARIO like user"
					
					//una vez insertada actualizamos el result
					//conexion.commit();

				}
				
			}
		}
		System.out.println("Finaliza la ejecuci�n del m�todo actualizarApuestaDefinitiva");
		
		return result;
	}
	
	
}
