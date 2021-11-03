package entidades;

import java.util.Date;

public class Expensas {
	
	private int idExpensa;
	private int relaDepartamento;
	private int departamentoUnidad;
	private Date periodo;
	private float valor;
	private Date fechaPago;
	
	public Expensas(int idExpensa, int relaDepartamento, int unidad, Date periodo, float valor, Date fechaPago) {
		this.idExpensa = idExpensa;
		this.relaDepartamento = relaDepartamento;
		this.departamentoUnidad = unidad;
		this.periodo = periodo;
		this.valor = valor;
		this.fechaPago = fechaPago;
	}
	
	public int getIdExpensa() {
		return idExpensa;
	}
	public int getRelaDepartamento() {
		return relaDepartamento;
	}
	
	public void setIdExpensa(int idExpensa) {
		this.idExpensa = idExpensa;
	}
	public Date getPeriodo() {
		return periodo;
	}
	public int getUnidad() {
		return this.departamentoUnidad;
	}
	public void setPeriodo(Date periodo) {
		this.periodo = periodo;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	public Date getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}
	
	

}
