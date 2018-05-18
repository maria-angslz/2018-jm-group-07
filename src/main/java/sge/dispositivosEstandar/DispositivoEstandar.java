package sge.dispositivosEstandar;

public class DispositivoEstandar {
	private String nombre;
	private double consumoKWxHora;
	private int horasDeUsoDiarias;
	
	public DispositivoEstandar(String nombre, double consumoKWxH, int horas){
		this.nombre = nombre;
		this.consumoKWxHora = consumoKWxH;
		this.horasDeUsoDiarias = horas;
	}
	
	public String nombre() {
		return this.nombre;
	}
	
	public double consumoKWxHora() {
		return consumoKWxHora;
	}
	
	public double consumoMensual() {
		return consumoKWxHora * horasDeUsoDiarias * 30; //el 30 seria la cantidad de dias del mes
	}
	
}