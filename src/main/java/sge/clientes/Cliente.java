package sge.clientes;

import java.util.List;
import java.util.stream.Collectors;

import sge.Documento;
import sge.categorias.Categoria;
import sge.dispositivos.Dispositivo;
import sge.persistencia.repos.RepoCatResidenciales;

public class Cliente {
	final double horasDelMes = 720;
	private String nombreYApellido;
	//private String[] apellidos;
	private Documento documento;
	private String domicilio;
	private String telefono;
	private Categoria categoria;
	private List<Dispositivo> dispositivos;
	private int puntos;

	public Cliente(String nombreYApellido, Documento documento, String domicilio, String telefono,
			Categoria categoria, List<Dispositivo> dispositivos) {
		this.nombreYApellido = nombreYApellido;
		//this.apellidos = apellidos;
		this.documento = documento;
		this.domicilio = domicilio;
		this.telefono = telefono;
		this.categoria = categoria;
		this.dispositivos = dispositivos;
	}

	public int cantidadDispositivosTotal() {
		return dispositivos.size();
	}
	
	public List<Dispositivo> listaDispositivosInteligentes(){
		return dispositivos.stream().filter(disp -> esDispositivoInteligente(disp)).collect(Collectors.toList());
	}
	
	public boolean esDispositivoInteligente(Dispositivo unDisp) {
		return unDisp.getClass().getName() == "dispositivoInteligente";
	}

	public int cantidadDispositivosEncendidos() {
		return (int) listaDispositivosInteligentes().stream().filter(disp -> disp.encendido()).count();
	}

	public int cantidadDispositivosApagados() {
		return (int) (this.cantidadDispositivosTotal() - this.cantidadDispositivosEncendidos());
	}

	public boolean algunDispositivoEstaEncendido() {
		return dispositivos.stream().anyMatch(disp -> disp.encendido());
	}

	public String nombre() {
		return nombreYApellido;
		//return String.join(" ", nombres);
	}

	public double facturacionAproximada() {
		return categoria.aproximarFacturacion(this.cantidadDeConsumoDelMes());
	}

	public double consumoDispositivos() {
		return dispositivos.stream().mapToDouble(disp -> disp.consumoKWxHora()).sum();
	}
	
	public double cantidadDeConsumoDelMes() {
		return this.consumoDispositivos() * horasDelMes;
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

}
