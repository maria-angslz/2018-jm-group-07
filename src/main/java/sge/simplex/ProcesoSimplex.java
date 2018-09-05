package sge.simplex;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import sge.clientes.Cliente;
import sge.dispositivos.Dispositivo;
import sge.dispositivos.inteligentes.DispositivoInteligente;
import sge.persistencia.repos.RepoClientes;
import sge.reglas.Actuador;
import sge.reglas.Regla;

public class ProcesoSimplex {

//-----------ejecutar sin reglas ni actuadores--------------

//	public static void ejecutar() {
//		List<Cliente> autorizados = RepoClientes.getInstance().obtenerClientesSimplex();
//		autorizados.forEach(cliente-> {
//			ResultadoSimplex res = cliente.consumoIdeal();
//			for(int i=0; i<res.dispositivos.size();i++) {
//				Dispositivo disp = res.dispositivos.get(i);
//				double maximo = res.horasOptimasDisps.get(i);
//				Optional<DispositivoInteligente> optDispInteligente = cliente.getDispositivosInteligentes().stream().filter(d->d==disp).findFirst();
//				optDispInteligente.ifPresent(dispInteligente->{
//					if(dispInteligente.consumoMensual() > maximo)
//						dispInteligente.apagar();
//				});
//			}
//		});
//	}

	public static void ejecutar() {
		List<Cliente> clientesSimplex = RepoClientes.getInstance().obtenerClientesSimplex();
		
		Actuador unActuador = new Actuador("apagar dispositivo", (d -> d.apagar()));

		Regla unaRegla = new Regla("Controlar consumo mensual", unActuador);
		
		clientesSimplex.forEach(cliente -> {
			ResultadoSimplex resultado = cliente.consumoIdeal();
//			List<DispositivoInteligente> dispositivosApagar = new ArrayList<DispositivoInteligente>();

			for (int i = 0; i < resultado.dispositivos.size(); i++) {
				Dispositivo disp = resultado.dispositivos.get(i);
				double maximo = resultado.horasOptimasDisps.get(i);
				Optional<DispositivoInteligente> optDispInteligente = cliente.getDispositivosInteligentes().stream()
						.filter(d -> d == disp).findFirst();
				optDispInteligente.ifPresent(dispInteligente -> {
					
					
					Function<Float,Boolean> funcionCumplir = (medicion) -> (Boolean) ((maximo) < (medicion));
					
					unaRegla.setFuncion(funcionCumplir);
					
					unaRegla.ejecutar((float) dispInteligente.consumoMensual(),dispInteligente);
					
//					if (dispInteligente.consumoMensual() > maximo)
//						dispositivosApagar.add(dispInteligente);
				});		
			}
		});

	}

}
