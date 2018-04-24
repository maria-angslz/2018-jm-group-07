package sge.clientes;

import java.util.List;

import sge.Documento;
import sge.categorias.Categoria;
import sge.dispositivos.Dispositivo;
import sge.persistencia.repos.RepoCatResidenciales;

public class Cliente {
	final int horasDelMes = 720;
	private String[] nombres;
	private String[] apellidos;
	private Documento documento;
	private String domicilio;
	private String telefono;
	private Categoria categoria;
	private List<Dispositivo> dispositivos;

	public Cliente(String[] nombres, String[] apellidos, Documento documento, String domicilio, String telefono,
			Categoria categoria, List<Dispositivo> dispositivos) {
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.documento = documento;
		this.domicilio = domicilio;
		this.telefono = telefono;
		this.categoria = categoria;
		this.dispositivos = dispositivos;
	}

	public int cantidadDispositivosTotal() {
		return dispositivos.size();
	}

	public int cantidadDispositivosEncendidos() {
		return (int) dispositivos.stream().filter(disp -> disp.encendido()).count();
	}

	public int cantidadDispositivosApagados() {
		return (int) dispositivos.stream().filter(disp -> !disp.encendido()).count();
	}

	public boolean algunDispositivoEstaEncendido() {
		return dispositivos.stream().anyMatch(disp -> disp.encendido());
	}

	public String nombre() {
		return String.join(" ", nombres);
	}

	public double facturacionAproximada() {
		return categoria.aproximarFacturacion(this.consumoDeEsteMes());
	}

	public double consumoDeEsteMes() {
		return dispositivos.stream().mapToDouble(disp -> disp.consumoDeEsteMes()).sum();
	}

	// En un futuro puede que querramos que los clientes
	// tengan tipos de categoria y esta se encargue
	// de recategorizarlo
	public void recategorizar() {
		if (!categoria.perteneceAEstaCategoria(this)) {
			cambiarCategoria();
		}
	}

	private void cambiarCategoria() {
		RepoCatResidenciales categorias = RepoCatResidenciales.getInstance();
		categoria = categorias.get().stream().filter(unaCategoria -> unaCategoria.perteneceAEstaCategoria(this))
				.findFirst().get();
	}

	public List<Dispositivo> getDispositivos() {
		return dispositivos;
	}

	public Categoria getCategoria() {
		return categoria;
	}

}
