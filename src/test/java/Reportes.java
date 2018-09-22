
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import sge.clientes.Cliente;


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
	
}
