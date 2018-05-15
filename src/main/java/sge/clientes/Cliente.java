package sge.clientes;

import java.util.List;

import sge.Documento;
import sge.categorias.Categoria;
import sge.dispositivos.Dispositivo;
import sge.dispositivosConModulo.DispositivoConModulo;
import sge.dispositivosEstandar.DispositivoEstandar;
import sge.persistencia.repos.RepoCatResidenciales;

public class Cliente {
	//final double horasDelMes = 720;
	private String nombreYApellido;
	private Documento documento;
	private String domicilio;
	private String telefono;
	private Categoria categoria;
	private List<Dispositivo> dispositivos;
	private int puntos;

	public Cliente(String nombreYApellido, Documento documento, String domicilio, String telefono,
			Categoria categoria, List<Dispositivo> dispositivos) {
		this.nombreYApellido = nombreYApellido;
		this.documento = documento;
		this.domicilio = domicilio;
		this.telefono = telefono;
		this.categoria = categoria;
		this.dispositivos = dispositivos;
		
		//esto habria que tener en cuenta, si cdo se carga los dispositivos del cliente que ya tenia, se deben sumar los puntos, se haria de esta forma:
		agregarPuntosIniciales();
	}
	
	public void agregarPuntosIniciales() {
		puntos += cantPuntosIniciales();
	}
	
	public double cantPuntosIniciales() {
		return dispositivos.stream().mapToDouble(disp -> disp.puntosPorAgregarDisp()).sum();
	}

	public int cantidadDispositivosTotal() {
		return dispositivos.size();
	}

	public int cantidadDispositivosEncendidos() {
		return (int) dispositivos.stream().filter(disp -> disp.encendido()).count();
	}
	
	public int getPuntos() {
		return puntos;
	}

	public int cantidadDispositivosApagados() {
		return (int) dispositivos.stream().filter(disp -> disp.apagado()).count();
	}

	public boolean algunDispositivoEstaEncendido() {
		return dispositivos.stream().anyMatch(disp -> disp.encendido());
	}

	public String nombre() {
		return nombreYApellido;
	}

	public double facturacionAproximada() {
		return categoria.aproximarFacturacion(this.cantidadDeConsumoDelMes());
	}

	public double cantidadDeConsumoDelMes() {
		return dispositivos.stream().mapToDouble(disp -> disp.consumoMensual()).sum();
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

	public List<Dispositivo> getDispositivos() {
		return dispositivos;
	}

	public Categoria getCategoria() {
		return categoria;
	}
	
	public void agregarDispositivo(Dispositivo unDispositivo) {
		dispositivos.add(unDispositivo);
		puntos += unDispositivo.puntosPorAgregarDisp();
	}
	
	public void ligarModuloADispositivo(DispositivoEstandar unDispositivo) {
		DispositivoConModulo unDispositivoConModulo= new DispositivoConModulo(unDispositivo.nombre(), unDispositivo.consumoKWxHora(), unDispositivo);
		dispositivos.add(unDispositivoConModulo);
		dispositivos.remove(unDispositivo);
		puntos += 10;
	}

}
