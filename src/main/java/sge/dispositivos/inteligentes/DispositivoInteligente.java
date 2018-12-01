package sge.dispositivos.inteligentes;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import sge.dispositivos.Dispositivo;
import sge.dispositivos.TipoDeDispositivo;
import sge.estados.*;

@Entity
@DiscriminatorColumn(name = "especial")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class DispositivoInteligente extends Dispositivo {

	public EstadoDispositivo getEstado() {
		return estado;
	}

	@ManyToOne(cascade = { CascadeType.PERSIST })
	private EstadoDispositivo estado; 

	private double IDFabrica = (Math.random() * 100000) + 1; 

	@Transient 
	private transient DispositivoInteligenteFisico dispositivoFisico; 

	@OneToMany(cascade = { CascadeType.PERSIST })
	@JoinColumn(name = "idDispositivoInteligente")
	private List<RegistroEstado> listaCambiosDeEstado = new ArrayList<RegistroEstado>();

	public DispositivoInteligente(String nombre, double consumoKWxHora, TipoDeDispositivo tipo) { // double maximo,
		
		this.tipo = tipo;
		this.nombre = nombre;
		this.consumoKWxHora = consumoKWxHora;
		this.cambiarEstado(new Apagado()); // el dispositivo inicia apagado
	}

	public DispositivoInteligente() {
		super();
	}


	public List<IntervaloEstado> getIntervalosDeEstadoEnEsteMes(EntityManager mger) {
		
		return getIntervalosDeEstadoEnMes(new Date(), mger);
	}
	
	public LocalDate dateLocal(Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	public List<IntervaloEstado> getIntervalosDeEstadoEnMes(Date fecha, EntityManager mger) {
		List<RegistroEstado> resultado = mger
				.createNativeQuery("select r.id, r.fechacambio, r.nuevoestado_id FROM RegistroEstado as r left join dispositivointeligente as d on r.iddispositivointeligente = d.id WHERE date_part('month', fechaCambio) = :mes AND date_part('year', fechaCambio) = :anio and idfabrica = :idfabrica", RegistroEstado.class)
				.setParameter("mes", dateLocal(fecha).getMonthValue()).setParameter("anio", dateLocal(fecha).getYear()).setParameter("idfabrica",this.IDFabrica).getResultList();
		RegistroEstado ultimo = null;
		List<IntervaloEstado> intervalos = new LinkedList<IntervaloEstado>();
		for (int i = 0; i < resultado.size(); i++) {
			RegistroEstado reg = resultado.get(i);
			if (ultimo == null) {
				ultimo = reg;
				continue;
			}

			IntervaloEstado nuevoIntervalo = new IntervaloEstado(ultimo.getFechaCambio(), reg.getFechaCambio(),
					ultimo.getNuevoEstado());
			intervalos.add(nuevoIntervalo);
			ultimo = reg;
		}
		if (ultimo != null)
			intervalos.add(new IntervaloEstado(ultimo.getFechaCambio(), new Date(), ultimo.getNuevoEstado()));
		return intervalos;
	}

	public Double totalConsumoIntervalo(IntervaloEstado intervalo) {

		Date inicioIntervalo = intervalo.getInicio();
		Date finIntervalo = intervalo.getFin();

		long diferencia1 = finIntervalo.getTime() - inicioIntervalo.getTime();

		Double diferencia = (double) TimeUnit.DAYS.convert(diferencia1, TimeUnit.MILLISECONDS); // esta en dias

		System.out.println(diferencia1);

		return this.consumoKWxHora() * 24 * diferencia;

	}

	public double getConsumoEnPeriodo(Date fecha) {
		EntityManager mger = PerThreadEntityManagers.getEntityManager();
		List<IntervaloEstado> intervalosDeEsteMes = this.getIntervalosDeEstadoEnMes(fecha, mger);

		List<IntervaloEstado> intervalosDeEsteMesEncendido = intervalosDeEsteMes.stream()
				.filter(intervalo -> intervalo.getEstado().getEncendido()).collect(Collectors.toList());
		List<Double> listaHoras = intervalosDeEsteMesEncendido.stream().map(i -> i.getHoras())
				.collect(Collectors.toList());
	
		Double horas = listaHoras.stream().mapToDouble(Double::doubleValue).sum();		
		return consumoKWxHora * horas;

	}

	public double getConsumoUltimoPeriodo() {
		return getConsumoEnPeriodo(new Date());

	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDispositivoFisico(DispositivoInteligenteFisico unDispositivoFisico) {
		this.dispositivoFisico = unDispositivoFisico;
	}

	public DispositivoInteligenteFisico getDispositivoFisico() {
		return dispositivoFisico;
	}

	public String nombre() {
		return this.nombre;
	}

	public double consumoKWxHora() {
		return consumoKWxHora;
	}

	public double consumoMensual() {
		return consumoTotalUnPeriodo(30); // el 30 seria la cantidad de dias del mes
	}

	public double getConsumoMensual() {
		return consumoMensual();
	}

	public double consumoDuranteUltimasHoras(int cantHoras) {
		return consumoKWxHora * cantHoras;
	}

	public double consumoTotalUnPeriodo(int dias) {
		return consumoKWxHora * 24 * dias; 
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
