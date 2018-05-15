package sge.dispositivos;

public interface Dispositivo {
//	public String nombre;
//	public double consumoKWxHora;
//	private boolean encendido;
/*
	public Dispositivo(String Nombre, double d, boolean Encendido) {
		this.nombre = Nombre;
		this.consumoKWxHora = d;
		this.encendido = Encendido;
	}
*/
	public String nombre();// {
//		return nombre;
//	}
/*
	public boolean encendido() {
		return encendido;
	}
*/
/*
	public void encendido(boolean encendido) {
		this.encendido = encendido;
	}
*/
	public double consumoKWxHora();// {
//		return consumoKWxHora;
//}
	
	public double consumoDuranteUltimasHoras(int cantHoras);
	
	
	// para el siguiente metodo asumo que el periodo que se solicite sera dado en dias
	public double consumoTotalUnPeriodo(int dias);
	
	public int puntosPorAgregarDisp();
	
}
