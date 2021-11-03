package modelo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;

import entidades.Expensas;
import modelo.conectores.DBManager;
import utils.DateUtils;

public class ExpensasDAO implements ExpensasDAOI {
	private Connection c;
	
	public ExpensasDAO() {
		this.c = DBManager.getConnection();
	}


	@Override
	public void crearTabla() throws SQLException {
		try {
			String crear = "CREATE TABLE IF NOT EXISTS EXPENSAS ("
					+ " id_expensa int NOT NULL AUTO_INCREMENT,"
					+ " rela_departamento int NOT NULL,"
					+ " expensa_periodo DATE NOT NULL,"
					+ " expensa_valor float NOT NULL,"
					+ " expensa_fecha_pago DATETIME,"
					+ " PRIMARY KEY (rela_departamento, expensa_periodo)"
					+ "); ";
			
			Statement s = c.createStatement();
			int r = s.executeUpdate(crear);
		} catch (SQLException e) {
			throw new SQLException("Hubo un error al crear la tabla de expensas");
		}
	}

	@Override
	public int crearExpensas(Expensas e) throws SQLException, ParseException {
		try {
			Statement s = c.createStatement();
			int r = s.executeUpdate("INSERT INTO EXPENSAS ("
					+ "rela_departamento, "
					+ "expensa_periodo, "
					+ "expensa_valor, "
					+ "expensa_fecha_pago"
					
					+ ") VALUES ("
					
					+ e.getRelaDepartamento()
					+ ", parsedatetime('" + DateUtils.formatAnoMes(e.getPeriodo()) + "', 'yyyy-MM')"
					+ ", " + e.getValor()
					+ ", " + "NULL"
					+ ")", Statement.RETURN_GENERATED_KEYS);
			return r;

		} catch(JdbcSQLIntegrityConstraintViolationException ex) {
			throw ex;
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException("Hubo un problema al crear una expensa. Por favor reintente nuevamente");
		}
		
	}

	@Override
	public boolean eliminarExpensas(Expensas e) throws SQLException {
		try {
			Statement s = c.createStatement();
			int r = s.executeUpdate("DELETE FROM EXPENSAS WHERE id_expensa = " + e.getIdExpensa());
			return true;
		}catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException("Hubo un problema al eliminar una expensa. Por favor reintente nuevamente");
		}
		
	}

	@Override
	public int editarExpensas(Expensas e) throws SQLException {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			String formattedDate = sdf.format(date);
			
			Statement s = c.createStatement();
			String update = "UPDATE EXPENSAS SET ";
			update += "expensa_periodo=" + e.getPeriodo();
			update += "expensa_valor=" + e.getValor();
			update += "expensa_fecha_pago=" + formattedDate;
			int r = s.executeUpdate(update);
			return r;
		}catch (SQLException ex) {
			ex.printStackTrace();
			throw new SQLException("Hubo un problema al editar una expensa. Por favor reintente nuevamente");
		}
		
	}
	
	@Override
	public ArrayList<Expensas> listarTodos() throws SQLException {
		try {
			Statement s = c.createStatement();
			ResultSet r = s.executeQuery("SELECT * FROM EXPENSAS "
					+ "INNER JOIN DEPARTAMENTO WHERE EXPENSAS.RELA_DEPARTAMENTO=DEPARTAMENTO.ID_DEPARTAMENTO");
			ArrayList<Expensas> resultado = new ArrayList<Expensas>();
			while(r.next()) {
				Expensas d = new Expensas(r.getInt("id_expensa"), r.getInt("rela_departamento"), r.getInt("unidad"), r.getDate("expensa_periodo"), r.getFloat("expensa_valor"), r.getDate("expensa_fecha_pago"));
				resultado.add(d);
			}
			return resultado;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Hubo un problema al listar las expensas. Por favor reintente nuevamente");
		}
	}

}
