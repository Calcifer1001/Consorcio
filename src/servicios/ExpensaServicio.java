package servicios;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import modelo.dao.ExpensasDAO;
import entidades.Expensas;


public class ExpensaServicio {
	
	private ExpensasDAO expensaDao;
	
	public ExpensaServicio() {
		this.expensaDao = new ExpensasDAO();
	}
	
	
	public List<Expensas> listarTodos() throws SQLException {
		return this.expensaDao.listarTodos();
	}
	
	
	public int crearExpensas(Expensas expensa) throws SQLException, ParseException {
		return this.expensaDao.crearExpensas(expensa);
	}
	
	
	public boolean eliminarExpensas(Expensas expensa) throws SQLException {
		return this.expensaDao.eliminarExpensas(expensa);
		
	}
	
	public int editarExpensas(Expensas expensa) throws SQLException {
		return this.expensaDao.editarExpensas(expensa);
	}
	
	

}

