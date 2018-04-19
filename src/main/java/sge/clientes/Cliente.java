package sge.clientes;

import java.util.ArrayList;
import java.util.List;

import sge.Documento;
import sge.categorias.*;
import sge.dispositivos.Dispositivo;

public class Cliente {
	private String[] nombres;
	private String[] apellidos;
	private Documento documento;
	private String domicilio;
	private String telefono;
	private Categoria categoria;
	private List<Dispositivo> dispositivos;
	
	public Cliente(String[] nombres, String[] apellidos, Documento documento, String domicilio, String telefono, Categoria categoria, List<Dispositivo> dispositivos) {
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
		return (int) dispositivos.stream().filter(disp->disp.encendido()).count();
	}
	public int cantidadDispositivosApagados() {
		return (int) dispositivos.stream().filter(disp->!disp.encendido()).count();
	}
	public boolean algunDispositivoEstaEncendido() {
		return dispositivos.stream().anyMatch(disp->disp.encendido());
	}
}
