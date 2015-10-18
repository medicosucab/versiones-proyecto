	package conexion;

import java.sql.*;
import javax.swing.*;

public class ConexionOracle {
	String user;
	String pass;
	String url;
	Connection conex;
	Statement stm;

	public ConexionOracle() {
		user = "pepe";
		pass = "pepe";
		url = "jdbc:oracle:thin:@localhost:1521:xe";
		conex = null;
		stm = null;

	}

	public void conectar() {

		try {
			Class.forName("org.sqlite.JDBC").newInstance();
			conex = DriverManager.getConnection("jdbc:sqlite:test.db");
			JOptionPane.showMessageDialog(null, "Conexon realizada");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No se realizo la conexion");
		}

	}

	public void crearTabla() throws SQLException {
		stm = conex.createStatement();
		int r=0;
		String cadSql;
		try {
			cadSql = "create table ALUMNOS (ID VARCHAR2(2) PRIMARY KEY, NOMBRE VARCHAR2(20), EDAD NUMBER(2))";
			
			r=stm.executeUpdate(cadSql);
			
			JOptionPane.showMessageDialog(null, (r+1)+" tabla creada");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "La tabla no se creo");
		}

	}

	public String leerDatosBD() throws SQLException {
		stm = conex.createStatement();
		String cadSql = "";
		try {
			cadSql = "select * from ALUMNOS";
			ResultSet rs = stm.executeQuery(cadSql);
			String datos = "";
			while (rs.next()) {
				datos += (rs.getString("ID") + " " + rs.getString(2) + " " + rs
						.getInt(3)) + "\n";
			}

			return datos;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "La tabla no existe");
			return null;
		}

	}

	public void ingresarDatosBD(String id, String nombre, int edad)
			throws SQLException {

		int r;
		String cadSql;
		stm = conex.createStatement();

		try {

			cadSql = "insert into ALUMNOS values ('" + id + "', '" + nombre
					+ "','" + edad + "')";
			r = stm.executeUpdate(cadSql);
			JOptionPane.showMessageDialog(null, r + " registro agregado");

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Registro no agregado");
		}

	}

	public String buscarDatosBD(String id) throws SQLException {
		stm = conex.createStatement();

		String cadSql;

		try {
			String nombre = "";
			cadSql = "select * from ALUMNOS where ID='" + id + "'";
			ResultSet rs = stm.executeQuery(cadSql);
			while (rs.next()) {
				nombre = rs.getString(1) + " " + rs.getString(2) + " "
						+ rs.getInt(3);

			}
			return nombre;

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "El codigo no existe");

			return null;
		}

	}

	public void eliminarDatos(String id) throws SQLException {
		int r;
		String cadSql;
		stm = conex.createStatement();
		try {
			cadSql = "delete from ALUMNOS where ID='" + id + "'";
			r = stm.executeUpdate(cadSql);
			JOptionPane.showMessageDialog(null, r + " registro eliminado");
			;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No se encontro el registro");
		}
	}

	public void eliminarTabla() throws SQLException {
		int r;
		String cadSql;
		stm = conex.createStatement();
		try {
			cadSql = "drop table ALUMNOS";
			r = stm.executeUpdate(cadSql);
			JOptionPane.showMessageDialog(null, (r + 1) + "tabla eliminada");
			;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No se encontro la tabla");

		}
	}

	public Connection getConex() {
		return conex;
	}

}