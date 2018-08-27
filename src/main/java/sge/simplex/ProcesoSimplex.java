package sge.simplex;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import sge.clientes.Cliente;
import sge.dispositivos.Dispositivo;
import sge.dispositivos.inteligentes.DispositivoInteligente;
import sge.persistencia.repos.RepoClientes;

public class ProcesoSimplex {
	public static void ejecutar() {
		List<Cliente> autorizados = RepoClientes.getInstance().get().stream().filter(c->c.getSimplexAutomatico()).collect(Collectors.toList());
		autorizados.forEach(cliente-> {
			ResultadoSimplex res = cliente.consumoIdeal();
			for(int i=0; i<res.dispositivos.size();i++) {
				Dispositivo disp = res.dispositivos.get(i);
				double maximo = res.horasOptimasDisps.get(i);
				Optional<DispositivoInteligente> optDispInteligente = cliente.getDispositivosInteligentes().stream().filter(d->d==disp).findFirst();
				optDispInteligente.ifPresent(dispInteligente->{
					if(dispInteligente.consumoMensual() > maximo)
						dispInteligente.apagar();
				});
			}
		});
	}
}
