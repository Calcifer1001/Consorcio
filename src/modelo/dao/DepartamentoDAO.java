package modelo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entidades.Departamento;
import modelo.conectores.DBManager;

public class DepartamentoDAO implements DepartamentoDAOI {
	private Connection c;
	
	public DepartamentoDAO() {
		this.c = DBManager.getConnection();
	}
	
	public void crearTabla() throws SQLException {
		try {
			String crear = "CREATE TABLE IF NOT EXISTS DEPARTAMENTO ("
					+ " id_departamento int NOT NULL AUTO_INCREMENT,"
					+ " unidad int NOT NULL,"
					+ " nombre_propietario varchar(40) NOT NULL,"
					+ " nombre_copropietario varchar(40),"
					+ " saldo_actual float NOT NULL,"
					+ " PRIMARY KEY (id_departamento)"
					+ "); ";
			
			Statement s = c.createStatement();
			int r = s.executeUpdate(crear);
		} catch (SQLException e) {
			throw new SQLException("Hubo un error al crear la tabla de departamento");
		}
	}
	
	@Override
	public int crearDepartamento(Departamento d) throws SQLException {
		try {
			int unidad = d.getUnidad();
			String prop = d.getNombreProp();
			String coprop = d.getNombreCop();
			float saldoActual = d.getSaldoActual();
			Statement s = c.createStatement();
			int r = s.executeUpdate("INSERT INTO departamento ("
					+ "unidad"
					+ ", nombre_propietario"
					+ ", nombre_copropietario"
					+ ", saldo_actual"
					+ ") VALUES ("
					
					+ unidad + ""
					+ ", '" + prop +"'"
					+ ", '" + coprop + "'"
					+ ", " + saldoActual 
					+ ")", Statement.RETURN_GENERATED_KEYS);
			return r;
		}catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Hubo un problema al crear un departamento. Por favor reintente nuevamente");
		}

	}

	@Override
	public void eliminarDepartamento(Departamento d) throws SQLException {
		this.eliminarDepartamento(d.getId());
	}
	
	public void eliminarDepartamento(int id) throws SQLException {
		try {
			Statement s = c.createStatement();
			int r = s.executeUpdate("DELETE FROM departamento WHERE id_departamento = " + id + "");
			
		}catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Hubo un problema al eliminar un departamento. Por favor reintente nuevamente");
		}

	}

	@Override
	public int editarDeparatmento(Departamento d) throws SQLException {
		try {
			int unidad = d.getUnidad();
			String prop = d.getNombreProp();
			String coprop = d.getNombreCop();

			Statement s = c.createStatement();
			String update = "UPDATE departamento SET ";
			update += "unidad=" + unidad;
			update += ", nombre_propietario='" + prop + "'";
			update += ", nombre_copropietario='" + coprop + "'";
			update += " WHERE id_departamento=" + d.getId();
			int r = s.executeUpdate(update);
			return r;
		}catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Hubo un problema al editar un departamento. Por favor reintente nuevamente");
		}
	}
	
	@Override
	public int registrarPagoDeparatmento(Departamento d) throws SQLException {
		try {
			
			Statement s = c.createStatement();
			String update = "UPDATE departamento SET ";
			update += "saldo_actual=" + d.getSaldoActual();
			update += " WHERE id_departamento=" + d.getId();
			int r = s.executeUpdate(update);
			return r;
		}catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Hubo un problema al editar un departamento. Por favor reintente nuevamente");
		}
	}
	
	public int registrarExpensaDeparatmento(Departamento d, double monto) throws SQLException {
		try {
			
			Statement s = c.createStatement();
			String update = "UPDATE departamento SET ";
			update += "saldo_actual=saldo_actual + " + monto;
			update += " WHERE id_departamento=" + d.getId();
			int r = s.executeUpdate(update);
			return r;
		}catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Hubo un problema al registrar una expensa. Por favor reintente nuevamente");
		}
	}

	@Override
	public ArrayList<Departamento> listarTodos() throws SQLException {
		try {
			Statement s = c.createStatement();
			ResultSet r = s.executeQuery("SELECT * FROM departamento");
			ArrayList<Departamento> resultado = new ArrayList<Departamento>();
			while(r.next()) {
				Departamento d = registroADepartamento(r);
				resultado.add(d);

			}
			return resultado;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Hubo un problema al listar los departamentos. Por favor reintente nuevamente");
		}
	}
	
	public Departamento departamentoSeleccionar(int id) throws SQLException {
		try {
			Statement s = c.createStatement();
			String query = "Select * from DEPARTAMENTO WHERE id_departamento=" + id;
			ResultSet r = s.executeQuery(query);
			return registroADepartamento(r);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Hubo un problema al obtener el departamento con id " + id);
		}
	}
	
	private Departamento registroADepartamento(ResultSet r) throws SQLException {
		return new Departamento(r.getInt("id_departamento"), r.getInt("unidad"), r.getString("nombre_propietario"), r.getString("nombre_copropietario"), r.getFloat("saldo_actual"));
	}
	
	public int getCantidadDeDepartamentos() throws SQLException {
		return this.listarTodos().size();
	}
}
