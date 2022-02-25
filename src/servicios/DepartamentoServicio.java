package servicios;

import java.sql.SQLException;
import java.util.List;

import entidades.Departamento;
import modelo.dao.DepartamentoDAO;

public class DepartamentoServicio {
	
	private DepartamentoDAO departamentoDao;
	private String errorMessage;
	
	
	public DepartamentoServicio() {
		this.departamentoDao = new DepartamentoDAO();
	}
	
	/**
	 * Busca en la base de datos todos los departamentos y se los devuelve a la capa de presentacion
	 * @throws SQLException 
	 */
	public List<Departamento> departamentosListar() throws SQLException {
		return this.departamentoDao.listarTodos();
	}
	
	
	/**
	 * Busca en la base de datos el departamento que tenga el id pasado por par�metro.
	 * @param id del departamento
	 * @return El departamento que tenga el id recibido
	 * @throws SQLException 
	 */
	public Departamento departamentoObtener(int id) throws SQLException {
		return this.departamentoDao.departamentoSeleccionar(id);
	}
	
	/**
	 * Se agrega a la base de datos el departamento que se recibe por par�metro
	 * @param dpto Departamento a agregar
	 * @return id del departamento agregado
	 * @throws SQLException 
	 */
	public int departamentoAgregar(Departamento dpto) throws Exception {
		if(!departamentoValidar(dpto)) {
			throw new Exception(errorMessage);
		}
		return this.departamentoDao.crearDepartamento(dpto);
	}
	
	
	/**
	 * Elimina el departamento de la base de datos
	 * @param id Id del departamento a eliminar
	 * @return true si el departamento pudo ser eliminado. false si no
	 * @throws SQLException 
	 */
	public boolean departamentoEliminar(int id) throws SQLException {
		this.departamentoDao.eliminarDepartamento(id);
		return true;
	}
	
	/**
	 * Modifca el departamento de la base de datos
	 * @param departamento Departamento a editar
	 * @return la cantidad de filas editadas
	 * @throws SQLException 
	 */
	public int departamentoModificar(Departamento departamento) throws Exception {
		if(!departamentoValidar(departamento)) {
			throw new Exception(errorMessage);
		}
		return this.departamentoDao.editarDeparatmento(departamento);
	}
	
	/**
	 * Valida que los par�metros recibidos son v�lidos (no necesariamente tiene que haber una �nica validaci�n, ni un �nico m�todo de validaci�n)
	 * @param departamento
	 * @return true si todos los datos del departamento son v�lidos.
	 */
	private boolean departamentoValidar(Departamento departamento) {
		errorMessage = "";
		if(departamento.getNombreProp().equals("")) {
			errorMessage = "No se introdujo propietario";
			return false;
		}
		if(departamento.getNombreCop().equals("")) {
			errorMessage = "No se introdujo copropietario";
			return false;
		}
		return true;
	}
	
	public void departamentoExpensaRegistrar(Departamento departamento, float monto) throws SQLException {
		this.departamentoValidar(departamento);
		this.departamentoDao.registrarExpensaDeparatmento(departamento, monto);
	}

}
