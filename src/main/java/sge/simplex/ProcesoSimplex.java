package sge.simplex;

import java.util.List;
import java.util.Optional;
import sge.clientes.Cliente;
import sge.dispositivos.Dispositivo;
import sge.dispositivos.inteligentes.DispositivoInteligente;
import sge.persistencia.repos.RepoClientes;
import sge.reglas.Actuador;
import sge.reglas.Regla;

public class ProcesoSimplex {

	public static void ejecutar() {
		List<Cliente> clientesSimplex = RepoClientes.getInstance().obtenerClientesSimplex();
		Actuador unActuador = new Actuador("apagar dispositivo", 0);
		Regla unaRegla = new Regla("Controlar consumo mensual", unActuador,0); //0 es el id de la funcion MAYORQUE
				
		clientesSimplex.forEach(cliente -> {
			ResultadoSimplex resultado = cliente.consumoIdeal();
			
			for (int i = 0; i < resultado.dispositivos.size(); i++) {
				Dispositivo disp = resultado.dispositivos.get(i);
				double maximo = resultado.horasOptimasDisps.get(i);
				Optional<DispositivoInteligente> optDispInteligente = cliente.getDispositivosInteligentes().stream().filter(d -> d == disp).findFirst();
				optDispInteligente.ifPresent(dispInteligente -> {
					unaRegla.ejecutar((float) dispInteligente.getConsumoUltimoPeriodo(),maximo,dispInteligente);
					
				});		
			}
		});

	}

}
