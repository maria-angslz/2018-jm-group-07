package SGE.Clientes;

import SGE.Categorias.*;

public abstract class Cliente {
	String[] nombres;
	String[] apellidos;
	int documento;
	String domicilio;
	String telefono;
	Categoria categoria;
	
	public abstract int cantidadDispositivosTotal();
	public abstract int cantidadDispositivosEncendidos();
	public abstract int cantidadDispositivosApagados();
	public abstract boolean algunDispositivoEstaEncendido();
}
