package entidades;

import java.util.Date;

public class Gasto {
	private int idGasto;
	private String nombreGasto;
	private float montoGasto;
	private Date fechaFacturacion;
	private Date fechaRegistro;
	
	public Gasto (int idGasto, String nombreGasto, float montoGasto) {
		this(idGasto, nombreGasto, montoGasto, new Date(), new Date());
	}

	public Gasto (int idGasto, String nombreGasto, float montoGasto, Date fechaFacturacion, Date fechaRegistro) {
		this.idGasto = idGasto;
		this.nombreGasto = nombreGasto;
		this.montoGasto = montoGasto;
		this.fechaFacturacion = fechaFacturacion;
		this.fechaRegistro = fechaRegistro;
	}
	
	public int getIdGasto() {
		return this.idGasto;
	}

	public String getNombreGasto() {
		return nombreGasto;
	}

	public void setNombreGasto(String nombreGasto) {
		this.nombreGasto = nombreGasto;
	}

	public float getMontoGasto() {
		return montoGasto;
	}

	public void setMontoGasto(int montoGasto) {
		this.montoGasto = montoGasto;
	}
	
	public Date getFechaFacturacion() {
		return this.fechaFacturacion;
	}
	
	public Date getFechaRegistro() {
		return this.fechaRegistro;
	}
}
