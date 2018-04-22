package sge.clientes;
import java.util.List;
import sge.Documento;
import sge.categorias.*;
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
	public String nombre() {
		return String.join(" ", nombres);
	}
	public double estimarFacturacionAFinDeMes() {
		//Una cosa a tener en cuenta aca, es que el consumoKWxHora del dispositivo, solo determina lo que consumiria por cada hora el dispositivo, 
		//en realidad nosotros deberiamos saber cuantas horas podria consumir en un mes, habria que definir como conseguir ese valor o como lo definimos
		//actualmente, el calculo me estaria dando el valor de una hora de cada dispositivo, no de lo que consumiria en un mes
		
		//Calculo la sumatoria de todos los consumoKWXHora de la lista de dispositivos del cliente
		return categoria.calcularConsumos(this.sumatoriaConsumosPorDispositivosMensual());
	}
	
	private double sumatoriaConsumosPorDispositivosMensual(){
		return horasDelMes * dispositivos.stream().mapToDouble(disp -> disp.consumoKWxHora()).sum();
	}
	
	public void recategorizar() {
		if(!categoria.perteneceAEstaCategoria((long) this.sumatoriaConsumosPorDispositivosMensual())) {
			cambiarCategoria();
		}
	}
	private void cambiarCategoria() {
		RepoCatResidenciales categorias = RepoCatResidenciales.getInstance();
		categoria = categorias.get().stream().filter(unaCategoria -> unaCategoria.perteneceAEstaCategoria( (long) this.sumatoriaConsumosPorDispositivosMensual() )).findFirst().get();
	}
}
