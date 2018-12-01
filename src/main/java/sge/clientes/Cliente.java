package sge.clientes;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import sge.Coordenates;
import sge.Documento;
import sge.SuperClase;
import sge.categorias.Categoria;
import sge.dispositivos.Dispositivo;
import sge.dispositivos.conModulo.DispositivoConModulo;
import sge.dispositivos.estandar.DispositivoEstandar;
import sge.dispositivos.inteligentes.DispositivoInteligente;
import sge.persistencia.repos.RepoCatResidenciales;
import sge.simplex.ResultadoSimplex;
import sge.simplex.SgeSimplex;

@Entity
public class Cliente extends SuperClase {

	private int simplexAutomatico = 0;
	final int puntosAgregarDisp = 15;
	final int puntosConvDispEaI = 10;
	private String nombreYApellido;
	private String email;
	private int pass;

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Documento documento;

	@Embedded
	private Coordenates miCoordenada;

	private String domicilio;
	private String telefono;

	@ManyToOne(cascade = { CascadeType.PERSIST })
	private Categoria categoria;

	@OneToMany(cascade = { CascadeType.PERSIST })
	@JoinColumn(name = "idCliente")
	private List<DispositivoEstandar> dispositivosEstandar;

	@OneToMany(cascade = { CascadeType.PERSIST })
	@JoinColumn(name = "idCliente")
	private List<DispositivoInteligente> dispositivosInteligentes;

	private int puntos;

	public Cliente() {
		super();
	}

	public Cliente(String nombreYApellido, Documento documento, String domicilio, String telefono, Categoria categoria,
			List<DispositivoEstandar> dispositivosEstandar, List<DispositivoInteligente> dispositivosInteligentes,
			Coordenates miCoordenada) {
		this.nombreYApellido = nombreYApellido;
		this.documento = documento;
		this.domicilio = domicilio;
		this.telefono = telefono;
		this.categoria = categoria;
		this.dispositivosEstandar = dispositivosEstandar;
		this.dispositivosInteligentes = dispositivosInteligentes;
		this.miCoordenada = miCoordenada;

		agregarPuntosIniciales();
	}

	public String getNombreYApellido() {
		return nombreYApellido;
	}

	public String getEmail() {
		return email;
	}
	
	public int getDocumento() {
		return documento.numero();
	}
	
	public String getDomicilio() {
		return domicilio;
	}
	
	public Coordenates miCoordenada() {
		return miCoordenada;
	}

	public void nuevaCoordenada(Coordenates nuevaCoordenada) {
		miCoordenada = nuevaCoordenada;
	}

	public void agregarPuntosIniciales() {
		puntos += dispositivosInteligentes.size() * puntosAgregarDisp;
	}

	public List<ConsumoEnMes> getConsumosUltimosPeriodos() {
		List<DispositivoInteligente> disps = this.getDispositivosInteligentes();
		Calendar cal = Calendar.getInstance();
		DateFormatSymbols dfs = new DateFormatSymbols();
		String[] months = dfs.getMonths();
		List<ConsumoEnMes> periodos = new LinkedList<ConsumoEnMes>();
		for (int i = 0; i > -5; i--) {
			cal.setTime(new Date());
			cal.add(Calendar.MONTH, i);
			final Date fechaMes = cal.getTime();
			Double consumo = disps.stream().mapToDouble(d -> d.getConsumoEnPeriodo(fechaMes)).sum();
			periodos.add(new ConsumoEnMes(months[cal.get(Calendar.MONTH)], consumo));
		}
		return periodos;

	}

	public int cantidadDispositivosTotal() {
		return dispositivosEstandar.size() + dispositivosInteligentes.size();
	}

	public int cantidadDispositivosEncendidos() {
		return (int) dispositivosInteligentes.stream().filter(disp -> disp.encendido()).count();
	}

	public int getPuntos() {
		return puntos;
	}

	public int cantidadDispositivosApagados() {
		return (int) dispositivosInteligentes.stream().filter(disp -> disp.apagado()).count();
	}

	public boolean algunDispositivoEstaEncendido() {
		return dispositivosInteligentes.stream().anyMatch(disp -> disp.encendido());
	}

	public String nombre() {
		return nombreYApellido;
	}

	public double facturacionAproximada() {
		return categoria.aproximarFacturacion(this.cantidadDeConsumoDelMes());
	}

	public double cantidadDeConsumoDelMes() {
		return dispositivosInteligentes.stream().mapToDouble(disp -> disp.consumoMensual()).sum()
				+ dispositivosEstandar.stream().mapToDouble(disp -> disp.consumoMensual()).sum();
	}

	
	public void recategorizar() {
		if (!categoria.perteneceAEstaCategoria(this.facturacionAproximada())) {
			cambiarCategoria();
		}
	}

	private void cambiarCategoria() {
		RepoCatResidenciales categorias = RepoCatResidenciales.getInstance();
		categoria = categorias.get().stream()
				.filter(unaCategoria -> unaCategoria.perteneceAEstaCategoria(this.facturacionAproximada())).findFirst()
				.get();
	}

	public List<DispositivoEstandar> getDispositivosEstandar() {
		return dispositivosEstandar;
	}

	public List<Dispositivo> getDispositivos() {
		List<Dispositivo> disps = new LinkedList<Dispositivo>();
		disps.addAll(getDispositivosEstandar());
		disps.addAll(getDispositivosInteligentes());
		return disps;
	}

	public List<DispositivoInteligente> getDispositivosInteligentes() {
		return dispositivosInteligentes;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public int getSimplexAutomatico() {
		return simplexAutomatico;
	}

	public void setSimplexAutomatico(int b) {
		simplexAutomatico = b;
	}

	public void agregarDispositivoInteligente(DispositivoInteligente unDispositivo) {
		dispositivosInteligentes.add(unDispositivo);
		puntos += puntosAgregarDisp;
	}

	public void agregarDispositivoEstandar(DispositivoEstandar unDispositivo) {
		dispositivosEstandar.add(unDispositivo);
	}

	public void ligarModuloADispositivo(DispositivoEstandar unDispositivo) {
		DispositivoConModulo unDispositivoConModulo = new DispositivoConModulo(unDispositivo);
		dispositivosInteligentes.add(unDispositivoConModulo);
		dispositivosEstandar.remove(unDispositivo);
		puntos += puntosConvDispEaI;
	}

	public Stream<DispositivoInteligente> dispositivosEncendidos() {
		return dispositivosInteligentes.stream().filter(disp -> disp.encendido());
	}

	public double getConsumos() {
		return dispositivosInteligentes.stream().mapToDouble(unDispositivo -> unDispositivo.getConsumoUltimoPeriodo()).sum() + dispositivosEstandar.stream().mapToDouble(unDispositivo -> unDispositivo.consumoMensual()).sum();
	}

	public String domicilio() {
		return domicilio;
	}

	public ResultadoSimplex consumoIdeal() {
		return SgeSimplex.getInstance().optimizar(getDispositivos());
	}

	public ResultadoSimplex getConsumoIdeal() {
		return consumoIdeal();
	}

	public void activarAhorroAutomatico() {
		this.simplexAutomatico = 1;
	}

	public void desactivarAhorroAutomatico() {
		this.simplexAutomatico = 0;
	}

	public double promedioPorDispositivo() {
		return this.getConsumos() / this.getDispositivos().size();
	}

	public int documento() {
		return documento.numero();
	}

	public String email() {
		return email;
	}

	public Integer pass() {
		return pass;
	}
}
