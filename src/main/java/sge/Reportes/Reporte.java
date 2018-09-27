package sge.Reportes;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import sge.Suministro.Transformador;
import sge.clientes.Cliente;

public class Reporte {
	
	public double consumoPorHogarParticular(int idCliente) {
		String queryString = "SELECT * FROM Cliente WHERE id = :idABuscar";
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();

		Query query = entityManager.createNativeQuery(queryString, Cliente.class).setParameter("idABuscar", idCliente);

		Cliente ClienteEncontrado = (Cliente) query.getSingleResult();

		return ClienteEncontrado.cantidadDeConsumoDelMes();
	}
	
	public double PromedioPorDispositivo(int idCliente) {
		String queryString = "SELECT * FROM Cliente WHERE id = :idABuscar";
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		// esta linea tiene el id del cliente, pero no es un dato que podemos obtener de
		// objetos, sino que se genera solo en la bd.
		Query query = entityManager.createNativeQuery(queryString, Cliente.class).setParameter("idABuscar", idCliente);

		Cliente ClienteEncontrado = (Cliente) query.getSingleResult();

		return ClienteEncontrado.promedioPorDispositivo();
	}
	
	public double consumoPromedioPorTransformador(int idTransformador) {
		
		String queryStringTransformador = "SELECT * FROM Transformador Where id = :idTransformador";
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		Query query = entityManager.createNativeQuery(queryStringTransformador, Transformador.class).setParameter("idTransformador", 1);
		Transformador elTransformador = (Transformador) query.getSingleResult();
		return elTransformador.promedioEnergiaSuministrada();
		
	}

}
