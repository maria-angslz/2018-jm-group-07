package sge.dispositivos.inteligentes;

import sge.dispositivos.Dispositivo;
import sge.estados.*;

public class DispositivoInteligente extends Dispositivo {
	private String nombre;
	private double consumoKWxHora;
	private EstadoDispositivo estado; //aca podriamos usar un repo de estados, para no tener que andar instanciando todo el tiempo
	private double IDFabrica = (Math.random() * 100000) + 1; //deberia cambiarse dsps y meter los nros generados en una lista, para comprobar que no se repitan
	private DispositivoInteligenteFisico dispositivoFisico; //deberia haber un repo de dispositivos fisicos y pedir el dispositivo con el mismo IDFabrica?

	public DispositivoInteligente(String nombre, double consumoKWxHora, TipoDeDispositivo tipo) { //double maximo, double minimo
//		this.maximo = maximo;
//		this.minimo = minimo;
		this.tipo = tipo;
		this.nombre = nombre;
		this.consumoKWxHora = consumoKWxHora;
		this.estado = new Apagado(); //el dispositivo inicia apagado
	}
//	public DispositivoInteligente(String nombre, double consumoKWxHora) {
//		this(nombre, consumoKWxHora, 0.0, 0.0);
//	}
	
	public void setDispositivoFisico(DispositivoInteligenteFisico unDispositivoFisico) {
		this.dispositivoFisico = unDispositivoFisico;
	}
	
	public DispositivoInteligenteFisico getDispositivoFisico(){
		return dispositivoFisico;
	}
	
	public String nombre() {
		return this.nombre;
	}
	
	public double consumoKWxHora() {
		return consumoKWxHora;
	}
	
	public double consumoMensual() {
		return consumoTotalUnPeriodo(30); //el 30 seria la cantidad de dias del mes
	}

	public double consumoDuranteUltimasHoras(int cantHoras) {
		return consumoKWxHora * cantHoras;
	}

	public double consumoTotalUnPeriodo(int dias) {
		return consumoKWxHora * 24 * dias; // el 24 refiere a las 24 hs de un dia
		//aca habria que hacer una limitiacion de ctos dias como maximo se puede volver atras, que calculo que el maximo atras debe ser 30 dias (1 mes)
	}

	public boolean encendido() {
		return estado.encendido();
	}
	
	public boolean apagado() {
		return !estado.encendido();
	}
	
	public boolean modoAhorroDeEnergia() {
		return estado.modoAhorroDeEnergia();
	}

	public void cambiarEstado(EstadoDispositivo estado) {
		this.estado = estado;
	}

	public void apagar() {
		estado.apagarse(this);
	}

	public void encender() {
		estado.encenderse(this);
	}
	
	public void establecerModoAhorroDeEnergia() {
		estado.entrarEnModoAhorroDeEnergia(this);
	}
	
	public double getIDfabrica() {
		return IDFabrica;
	}
	
	public TipoDeDispositivo getTipo() {
		return tipo;
	}
}
