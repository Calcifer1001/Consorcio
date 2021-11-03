package entidades;

public class Departamento {
	private int id;
	private int unidad;
	private String nombreProp;
	private String nombreCop;
	private float saldoActual;
	
	
	public Departamento(int id, int unidad, String nombreProp, String nombreCop, float saldoActual) {
		super();
		this.id = id;
		this.unidad = unidad;
		this.nombreProp = nombreProp;
		this.nombreCop = nombreCop;
		this.saldoActual = saldoActual;
	}
	
	public Departamento(int unidad, String nombreProp, String nombreCop) {
		this(-1, unidad, nombreProp, nombreCop);
	}
	
	public Departamento(int id, int unidad, String nombreProp, String nombreCop) {
		this(id, unidad, nombreProp, nombreCop, 0);
	}
	
	public int getId() {
		return this.id;
	}

	public int getUnidad() {
		return unidad;
	}

	public void setUnidad(int unidad) {
		this.unidad = unidad;
	}

	public String getNombreProp() {
		return nombreProp;
	}

	public void setNombreProp(String nombreProp) {
		this.nombreProp = nombreProp;
	}

	public String getNombreCop() {
		return nombreCop;
	}

	public void setNombreCop(String nombreCop) {
		this.nombreCop = nombreCop;
	}

	public float getSaldoActual() {
		return saldoActual;
	}

	public void setSaldoActual(float saldoActual) {
		this.saldoActual = saldoActual;
	}
	
	public void registrarPago(float monto) {
		this.saldoActual -= monto;
	}


}
