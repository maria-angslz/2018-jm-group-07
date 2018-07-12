package sge.clientes;

import java.util.List;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import sge.Documento;
import sge.categorias.Categoria;
import sge.dispositivosConModulo.DispositivoConModulo;
import sge.dispositivosEstandar.DispositivoEstandar;
import sge.dispositivosInteligentes.DispositivoInteligente;
import sge.persistencia.repos.RepoCatResidenciales;
import sge.simplex.SgeSimplex;

public class Cliente {
	final int puntosAgregarDisp = 15;
	final int puntosConvDispEaI = 10;
	private String nombreYApellido;
	private Documento documento;
	private String domicilio;
	private String telefono;
	private Categoria categoria;
	private List<DispositivoEstandar> dispositivosEstandar;
	private List<DispositivoInteligente> dispositivosInteligentes;
	private int puntos;

	public Cliente(String nombreYApellido, Documento documento, String domicilio, String telefono,
			Categoria categoria, List<DispositivoEstandar> dispositivosEstandar, List<DispositivoInteligente> dispositivosInteligentes) {
		this.nombreYApellido = nombreYApellido;
		this.documento = documento;
		this.domicilio = domicilio;
		this.telefono = telefono;
		this.categoria = categoria;
		this.dispositivosEstandar = dispositivosEstandar;
		this.dispositivosInteligentes = dispositivosInteligentes;
		
		agregarPuntosIniciales();
	}
	
	public void agregarPuntosIniciales() {
		puntos += dispositivosInteligentes.size() * puntosAgregarDisp;
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
		return dispositivosInteligentes.stream().mapToDouble(disp -> disp.consumoMensual()).sum() + dispositivosEstandar.stream().mapToDouble(disp -> disp.consumoMensual()).sum();
	}

	// En un futuro puede que querramos que los clientes
	// tengan tipos de categoria y esta se encargue
	// de recategorizarlo
	public void recategorizar() {
		if (!categoria.perteneceAEstaCategoria(this.facturacionAproximada())) {
			cambiarCategoria();
		}
	}

	private void cambiarCategoria() {
		RepoCatResidenciales categorias = RepoCatResidenciales.getInstance();
		categoria = categorias.get().stream().filter(unaCategoria -> unaCategoria.perteneceAEstaCategoria(this.facturacionAproximada()))
				.findFirst().get();
	}
	
	public List<DispositivoEstandar> getDispositivosEstandar() {
		return dispositivosEstandar;
	}

	public List<DispositivoInteligente> getDispositivosInteligentes() {
		return dispositivosInteligentes;
	}

	public Categoria getCategoria() {
		return categoria;
	}
	
	public void agregarDispositivoInteligente(DispositivoInteligente unDispositivo) {
		dispositivosInteligentes.add(unDispositivo);
		puntos += puntosAgregarDisp;
	}
	
	public void agregarDispositivoEstandar(DispositivoEstandar unDispositivo) {
		dispositivosEstandar.add(unDispositivo);
	}
	
	public void ligarModuloADispositivo(DispositivoEstandar unDispositivo) {
		DispositivoConModulo unDispositivoConModulo= new DispositivoConModulo(unDispositivo);
		dispositivosInteligentes.add(unDispositivoConModulo);
		dispositivosEstandar.remove(unDispositivo);
		puntos += puntosConvDispEaI;
	}
	public Stream<DispositivoInteligente> dispositivosEncendidos() {
		return dispositivosInteligentes.stream().filter(disp -> disp.encendido());
	}
	
	public double consumos() {
		return dispositivosEncendidos().mapToDouble(unDispositivo -> unDispositivo.consumoKWxHora()).sum();
	}
	
	public String domicilio() {
		return domicilio;
	}
	
	public double consumoIdeal() {
		SgeSimplex simplex = SgeSimplex.getInstance();
		//DoubleStream consumos = dispositivosEncendidos().mapToDouble(unDispositivo -> unDispositivo.consumoKWxHora());
		List<DispositivoInteligente> dp = (List<DispositivoInteligente>) this.dispositivosEncendidos();
		//double[] consumos = dp.map(disp -> disp.consumoKWxHora());
		//simplex.inicializar(consumos);
		double consumoIdealFinal = simplex.maximizar();
		return consumoIdealFinal;
	}

}
