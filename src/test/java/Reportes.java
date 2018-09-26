
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import sge.Suministro.Transformador;
import sge.clientes.Cliente;
import sge.dispositivos.Dispositivo;
import sge.dispositivos.inteligentes.DispositivoInteligente;

public class Reportes extends Fixture.FCasosDePrueba {

	@Test
	public void imprimirConsumoPorCliente() {
		double consumoEsperado = clienteConDosDispositivos.cantidadDeConsumoDelMes();
		System.out.println(clienteConDosDispositivos.nombre());
		System.out.println(clienteConDosDispositivos.cantidadDeConsumoDelMes() + "Kw");
		assertEquals(consumoEsperado, consumoPorHogar(clienteConDosDispositivos), 0);
	}

	// el reporte es del mes actual.
	public double consumoPorHogar(Cliente unCliente) {
		String queryString = "SELECT * FROM Cliente WHERE id = :idABuscar";
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();

		Query query = entityManager.createNativeQuery(queryString, Cliente.class).setParameter("idABuscar", 1);

		Cliente ClienteEncontrado = (Cliente) query.getSingleResult();

		return ClienteEncontrado.cantidadDeConsumoDelMes();
	}

	@Test
	public void imprimirPromedioPorDispositivoParaUnClienteEspecifico() {
		double resultadoEsperado = clienteConDosDispositivos.promedioPorDispositivo();
		assertEquals(resultadoEsperado, PromedioPorDispositivo(clienteConDosDispositivos), 1);
	}

	public double PromedioPorDispositivo(Cliente unCliente) {
		String queryString = "SELECT * FROM Cliente WHERE id = :idABuscar";
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		// esta linea tiene el id del cliente, pero no es un dato que podemos obtener de
		// objetos, sino que se genera solo en la bd.
		Query query = entityManager.createNativeQuery(queryString, Cliente.class).setParameter("idABuscar", 1);

		Cliente ClienteEncontrado = (Cliente) query.getSingleResult();

		return ClienteEncontrado.promedioPorDispositivo();
	}

	@Test
	public void aumentoConsumoPromedioDelTransformador() {
		// persistimos un transformador
		transaction.begin();
		entityManager.persist(unTransformador);
		transaction.commit();

		// recuperamos un transformador y calculamos el consumo promedio
		String queryStringTransformador = "SELECT * FROM Transformador Where id = :idTransformador";
		Query query = entityManager.createNativeQuery(queryStringTransformador, Transformador.class)
				.setParameter("idTransformador", 1);
		Transformador elTransformador = (Transformador) query.getSingleResult();
		double consumoPromedioInicial = elTransformador.promedioEnergiaSuministrada();

		String queryStringDispositivo = "SELECT * FROM dispositivointeligente WHERE id = :idDispositivo";
		Query queryDispositivo = entityManager.createNativeQuery(queryStringDispositivo, DispositivoInteligente.class)
				.setParameter("idDispositivo", 1);
		DispositivoInteligente unDispositivo = (DispositivoInteligente) queryDispositivo.getSingleResult();

		// incrementamos el consumo del dispositivo al 1000%
		unDispositivo.incrementarConsumo(10);

		// persistimos el dispositivo
		transaction.begin();
		entityManager.persist(unDispositivo);
		transaction.commit();

		// volvemos a traer el transformador
		elTransformador = (Transformador) query.getSingleResult();
		double consumoPromedioFinal = elTransformador.promedioEnergiaSuministrada();

		assertTrue(consumoPromedioFinal > consumoPromedioInicial);

	}
}
