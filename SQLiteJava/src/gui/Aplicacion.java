package gui;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;

import conexion.*;

public class Aplicacion extends JFrame implements ActionListener {

	ConexionOracle objConex;

	JButton btnBuscar, btnListar, btnIngresar, btnActualizar, btnEliminar,
			btnCrearTabla, btneliminarTabla, btnConectar, btnDesconectar;
	JTextArea txaArea;
	JScrollPane scpArea;

	public Aplicacion() {
		objConex = new ConexionOracle();

		getContentPane().setLayout(null);
		setSize(470, 330);
		setTitle("Datos de Oracle");

		btnCrearTabla = new JButton("Crear Tabla");
		btnCrearTabla.setBounds(10, 10, 160, 20);
		btnCrearTabla.addActionListener(this);
		getContentPane().add(btnCrearTabla);

		btneliminarTabla = new JButton("Eliminar Tabla");
		btneliminarTabla.setBounds(280, 10, 160, 20);
		btneliminarTabla.addActionListener(this);
		getContentPane().add(btneliminarTabla);

		btnIngresar = new JButton("Ingresar");
		btnIngresar.setBounds(10, 50, 100, 20);
		btnIngresar.addActionListener(this);
		getContentPane().add(btnIngresar);

		btnListar = new JButton("Listar");
		btnListar.setBounds(120, 50, 100, 20);
		btnListar.addActionListener(this);
		getContentPane().add(btnListar);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(230, 50, 100, 20);
		btnBuscar.addActionListener(this);
		getContentPane().add(btnBuscar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(340, 50, 100, 20);
		btnEliminar.addActionListener(this);
		getContentPane().add(btnEliminar);

		txaArea = new JTextArea();
		txaArea.setEditable(false);
		scpArea = new JScrollPane(txaArea);
		scpArea.setBounds(20, 100, 410, 140);
		getContentPane().add(scpArea);

		btnConectar = new JButton("Conectar a la BD");
		btnConectar.setBounds(10, 260, 160, 20);
		btnConectar.addActionListener(this);
		getContentPane().add(btnConectar);

		btnDesconectar = new JButton("Desconectar la BD");
		btnDesconectar.setBounds(280, 260, 160, 20);
		btnDesconectar.setForeground(Color.red);
		btnDesconectar.addActionListener(this);
		getContentPane().add(this.btnDesconectar);

		setLocationRelativeTo(null);
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		try {
			if (e.getSource() == btnConectar) {
				objConex.conectar();
			}

			if (e.getSource() == btnCrearTabla) {
				objConex.crearTabla();
			}

			if (e.getSource() == btnIngresar) {
				String id = JOptionPane.showInputDialog("Ingrese el id");
				String nombre = JOptionPane
						.showInputDialog("Ingrese el nombre");
				int edad = Integer.parseInt(JOptionPane
						.showInputDialog("Ingrese la edad"));
				objConex.ingresarDatosBD(id, nombre, edad);

			}

			if (e.getSource() == btnListar) {
				txaArea.setText(objConex.leerDatosBD());
			}

			if (e.getSource() == btnBuscar) {
				String id = JOptionPane.showInputDialog("Ingrese el id");
				txaArea.setText(objConex.buscarDatosBD(id));
			}

			
			if (e.getSource() == btnEliminar) {
				String id = JOptionPane.showInputDialog("Ingrese el id");
				objConex.eliminarDatos(id);
				txaArea.setText(objConex.leerDatosBD());
			}

			if (e.getSource() == btneliminarTabla) {
				objConex.eliminarTabla();
			}

			if (e.getSource() == btnDesconectar) {

				try {
					objConex.getConex().close();

					JOptionPane.showMessageDialog(null,
							"Base de datos desconectada");
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null,
							"La base de datos aun sigue conectada");
				}

			}

		} catch (Exception e2) {
			// TODO: handle exception
		}

	}

	public static void main(String[] args) throws SQLException {

		new Aplicacion();

	}

}
