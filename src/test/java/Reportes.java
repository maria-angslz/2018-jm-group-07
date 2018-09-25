
import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import sge.clientes.Cliente;
import sge.dispositivos.Dispositivo;
import sge.dispositivos.inteligentes.DispositivoInteligente;


public class Reportes extends Fixture.FCasosDePrueba{
	
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
	public void imprimirPromedioPorDispositivoParaUnClienteEspecifico() {
		double resultadoEsperado = clienteConDosDispositivos.promedioPorDispositivo();
		assertEquals(resultadoEsperado, PromedioPorDispositivo(clienteConDosDispositivos),1);
	}
	
	
	public double PromedioPorDispositivo(Cliente unCliente) {
		String queryString = "SELECT * FROM Cliente WHERE id = :idABuscar";
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		//esta linea tiene el id del cliente, pero no es un dato que podemos obtener de objetos, sino que se genera solo en la bd.
		Query query = entityManager.createNativeQuery(queryString,Cliente.class).setParameter("idABuscar", 1);
		
		Cliente ClienteEncontrado =  (Cliente) query.getSingleResult();
		
		return ClienteEncontrado.promedioPorDispositivo();
	}
	
}
