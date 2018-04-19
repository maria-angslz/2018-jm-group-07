package sge.clientes;

import sge.categorias.*;

public abstract class Cliente {
	private String[] nombres;
	private String[] apellidos;
	private int documento;
	private String domicilio;
	private String telefono;
	private Categoria categoria;
	
	public abstract int cantidadDispositivosTotal();
	public abstract int cantidadDispositivosEncendidos();
	public abstract int cantidadDispositivosApagados();
	public abstract boolean algunDispositivoEstaEncendido();
}
