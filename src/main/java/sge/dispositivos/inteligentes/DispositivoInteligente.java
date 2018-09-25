package sge.dispositivos.inteligentes;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;


import sge.dispositivos.Dispositivo;
import sge.dispositivos.TipoDeDispositivo;
import sge.estados.*;

@Entity
@DiscriminatorColumn(name = "especial")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class DispositivoInteligente extends Dispositivo {
	
	@ManyToOne(cascade = {CascadeType.PERSIST})
	private EstadoDispositivo estado; //aca podriamos usar un repo de estados, para no tener que andar instanciando todo el tiempo
	
	private double IDFabrica = (Math.random() * 100000) + 1; //deberia cambiarse dsps y meter los nros generados en una lista, para comprobar que no se repitan
	
	@Transient //no lo persistimos
	private transient DispositivoInteligenteFisico dispositivoFisico; //deberia haber un repo de dispositivos fisicos y pedir el dispositivo con el mismo IDFabrica?
	
	@OneToMany(cascade = {CascadeType.PERSIST})
	@JoinColumn(name="idDispositivoInteligente")
	private List<RegistroEstado> listaCambiosDeEstado = new ArrayList<RegistroEstado> ();

	public DispositivoInteligente(String nombre, double consumoKWxHora, TipoDeDispositivo tipo) { //double maximo, double minimo
//		this.maximo = maximo;
//		this.minimo = minimo;
		this.tipo = tipo;
		this.nombre = nombre;
		this.consumoKWxHora = consumoKWxHora;
		this.cambiarEstado(new Apagado()); //el dispositivo inicia apagado
	}
	
	public DispositivoInteligente() {
		super();
	}
	
//	public DispositivoInteligente(String nombre, double consumoKWxHora) {
//		this(nombre, consumoKWxHora, 0.0, 0.0);
//	}

	public List<IntervaloEstado> getIntervalosDeEstadoEnEsteMes(EntityManager mger) {
		return getIntervalosDeEstadoEnMes(new Date(), mger);
	}
	
	public List<IntervaloEstado> getIntervalosDeEstadoEnMes(Date fecha, EntityManager mger) {
		List<RegistroEstado> resultado = mger.createQuery("FROM RegistroEstado WHERE MONTH(fechaCambio) = MONTH(:fecha)").setParameter("fecha", fecha).getResultList();
		RegistroEstado ultimo = null;
		List<IntervaloEstado> intervalos = new LinkedList<IntervaloEstado>();
		for (int i = 0; i < resultado.size(); i++) {
			RegistroEstado reg = resultado.get(i);
			if( ultimo == null) {
				ultimo = reg;
				continue;
			}
			
			IntervaloEstado nuevoIntervalo = new IntervaloEstado(ultimo.getFechaCambio(), reg.getFechaCambio(), ultimo.getNuevoEstado());
			intervalos.add(nuevoIntervalo);
		    ultimo = reg;
		}
		if( ultimo != null)
			intervalos.add(new IntervaloEstado(ultimo.getFechaCambio(), new Date(), estado));
		return intervalos;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

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
		//crear un nuevo RegistroEstado y persistirlo
		//o teniendo una tabla aparte de registrode estados (que no estaria en el modelo de clases) armar un insert
		listaCambiosDeEstado.add(new RegistroEstado(this, estado, new Date()));
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
	
	public Double getMinimo() {
		return (double) tipo.getMinimo();
	}
	public Double getMaximo() {
		return (double) tipo.getMaximo();
	}
}
