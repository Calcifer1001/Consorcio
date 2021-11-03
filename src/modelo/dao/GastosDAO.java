package modelo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import entidades.Departamento;
import entidades.Gasto;
import modelo.conectores.DBManager;
import utils.DateUtils;

public class GastosDAO implements GastosDAOI{
	private Connection c;
	
	public GastosDAO() {
		this.c = DBManager.getConnection();
	}

	@Override
	public void crearTabla() throws SQLException {
		try {
			String crear = "CREATE TABLE IF NOT EXISTS GASTO ("
					+ " id_gasto int NOT NULL AUTO_INCREMENT,"
					+ " nombre_gasto varchar(40) NOT NULL,"
					+ " fecha_facturacion DATE NOT NULL,"
					+ " fecha_registro DATE NOT NULL,"
					+ " monto float,"
					+ " PRIMARY KEY (id_gasto)"
					+ "); ";
			
			Statement s = c.createStatement();
			int r = s.executeUpdate(crear);
		} catch (SQLException e) {
			throw new SQLException("Hubo un error al crear la tabla de gasto");
		}
	}
	
	@Override
	public int crearGastos(Gasto g) throws SQLException, ParseException {
		try {
			Statement s = c.createStatement();
			int r = s.executeUpdate("INSERT INTO GASTO ("
					+ "nombre_gasto"
					+ ", monto"
					+ ", fecha_facturacion"
					+ ", fecha_registro"
					+ ") VALUES ("
					
					+ "'" + g.getNombreGasto() + "'"
					+ ", " + g.getMontoGasto()
					+ ", parsedatetime('" + DateUtils.formatAnoMesDia(g.getFechaFacturacion()) + "', 'yyyy-MM-dd')"
					+ ", parsedatetime('" + DateUtils.formatAnoMesDia(g.getFechaRegistro()) + "', 'yyyy-MM-dd')"
					+ ")", Statement.RETURN_GENERATED_KEYS);
			return r;
		}catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Hubo un problema al crear un gasto. Por favor reintente nuevamente");
		}
		
	}

	@Override
	public boolean eliminarGastos(Gasto g) throws SQLException {
		try {
			Statement s = c.createStatement();
			int r = s.executeUpdate("DELETE FROM GASTO WHERE id_gasto = " + g.getIdGasto() + "");
			return true;
		}catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Hubo un problema al eliminar un gasto. Por favor reintente nuevamente");
		}
	}

	@Override
	public int editarGastos(Gasto g) throws SQLException {
		try {
			Statement s = c.createStatement();
			String update = "UPDATE GASTO SET ";
			update += "nombre_gasto='" + g.getNombreGasto() + "'";
			update += ", monto=" + g.getMontoGasto();
			update += " WHERE id_gasto=" + g.getIdGasto();
			int r = s.executeUpdate(update);
			return r;
		}catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Hubo un problema al editar un gasto. Por favor reintente nuevamente");
		}
		
	}

	@Override
	public ArrayList<Gasto> listarTodos() throws SQLException {
		try {
			return this.getGastos(0);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Hubo un problema al listar los gastos de este mes. Por favor reintente nuevamente");
		}
	}
	
	public Float getGastosMesPasado() throws SQLException {
		try {
			List<Gasto> gastosDelMes = this.getGastos(-1);
			float gastoTotal = 0f;
			for (Gasto gasto : gastosDelMes) {
				gastoTotal += gasto.getMontoGasto();
			}

			return gastoTotal;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Hubo un problema al obtener los gastos del mes pasado. Por favor reintente nuevamente");
		}
	}
	
	public Float getGastosMes(int relaDepartamento, Date periodo) throws SQLException {
		try {
			List<Gasto> gastosDelMes = this.getGastos(-1);
			float gastoTotal = 0f;
			for (Gasto gasto : gastosDelMes) {
				gastoTotal += gasto.getMontoGasto();
			}

			return gastoTotal;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Hubo un problema al obtener los gastos del mes pasado. Por favor reintente nuevamente");
		}
	}
	
	private ArrayList<Gasto> getGastos(int mesesAdelante) throws SQLException {
		String[] mes = DateUtils.obtenerMes(mesesAdelante);
		
		Statement s = c.createStatement();
		ResultSet r = s.executeQuery("SELECT * FROM GASTO WHERE FECHA_REGISTRO > '" + mes[0] + "' AND FECHA_REGISTRO < '" + mes[1] + "'");
		ArrayList<Gasto> resultado = new ArrayList<Gasto>();
		while(r.next()) {
			Gasto g = new Gasto(r.getInt("id_gasto"), r.getString("nombre_gasto"), r.getFloat("monto"), r.getDate("fecha_facturacion"), r.getDate("fecha_registro"));
			resultado.add(g);

		}
		return resultado;
	}

	public ArrayList<Gasto> getGastos(Date periodo) throws SQLException {
		String[] mes = DateUtils.obtenerMesDeDate(periodo);
		
		Statement s = c.createStatement();
		ResultSet r = s.executeQuery("SELECT * FROM GASTO WHERE FECHA_REGISTRO >= '" + mes[0] + "' AND FECHA_REGISTRO <= '" + mes[1] + "'");
		ArrayList<Gasto> resultado = new ArrayList<Gasto>();
		while(r.next()) {
			Gasto g = new Gasto(r.getInt("id_gasto"), r.getString("nombre_gasto"), r.getFloat("monto"), r.getDate("fecha_facturacion"), r.getDate("fecha_registro"));
			resultado.add(g);

		}
		return resultado;
	}

}
