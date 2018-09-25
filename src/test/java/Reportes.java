
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import sge.clientes.Cliente;
import sge.dispositivos.Dispositivo;
import sge.dispositivos.inteligentes.DispositivoInteligente;


public class Reportes {
	@Test
	//el reporte es del mes actual. 
	public void consumoPorHogar() {
		String queryString = "SELECT * FROM Cliente";
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		Query query = entityManager.createNativeQuery(queryString,Cliente.class);
		
		List<Cliente> resultado =  query.getResultList();
		
		resultado.forEach(unCliente -> imprimirConsumoPorCliente(unCliente));
	}
	
	public void imprimirConsumoPorCliente(Cliente unCliente) {
		System.out.println(unCliente.cantidadDeConsumoDelMes()); 
		System.out.println(unCliente.nombre());
	}
	
	@Test
	public void consumoPorDispositivo() {
		String queryString = "SELECT * FROM Dispositivointeligente";
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		entityManager.clear();
		Query query = entityManager.createNativeQuery(queryString,DispositivoInteligente.class);
		
		List<DispositivoInteligente> resultado =  query.getResultList();
		
		resultado.forEach(unDispositivo -> imprimirConsumoPromedioPorDispositivo(unDispositivo, 30)); // Periodo de 30 dias
	}
	
	public void imprimirConsumoPromedioPorDispositivo(Dispositivo unDispositivo, int unPeriodo) {
		System.out.println(unDispositivo.consumoPromedioPorPeriodo(unPeriodo)); 
		System.out.println(unDispositivo.getNombre());
	}
	
}
