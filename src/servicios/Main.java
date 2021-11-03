package servicios;

import javax.swing.*;

import modelo.dao.DepartamentoDAO;
import modelo.dao.ExpensasDAO;
import modelo.dao.ExpensasDAOI;
import modelo.dao.GastosDAO;
import presentacion.PresentacionBasica;
import presentacion.tabladatos.TablaDatosPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		// Voy a la base de datos a buscar informacion para dibujar lo principal
		try {
			crearTablas();
		
			PresentacionBasica pb = new PresentacionBasica();
			pb.iniciarAplicacion();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Hubo un error inesperado");
		}
	}
	
	private static void crearTablas() throws SQLException {
		DepartamentoDAO departamentoDAO = new DepartamentoDAO();
		departamentoDAO.crearTabla();
		
		GastosDAO gastosDAO = new GastosDAO();
		gastosDAO.crearTabla();
		
		ExpensasDAO expensasDAO = new ExpensasDAO();
		expensasDAO.crearTabla();
	}
	
	
}
