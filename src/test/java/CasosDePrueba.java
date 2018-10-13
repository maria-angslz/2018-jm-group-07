import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.Query;

import org.junit.Test;

import sge.Coordenates;
import sge.Suministro.Transformador;
import sge.Suministro.ZonaGeografica;
import sge.clientes.Cliente;
import sge.dispositivos.inteligentes.*;
import sge.reglas.Regla;
import sge.estados.*;

public class CasosDePrueba extends Fixture.FCasosDePrueba {

	@Test
	public void casoDePrueba1() {

		// persisto un cliente
		transaction.begin();
		entityManager.persist(LaZulma);
		transaction.commit();

		// lo recupero
		transaction.begin();
		Cliente clientitoPerez = entityManager.find(Cliente.class, new Integer(1));
		System.out.println(clientitoPerez.nombre());

		// le modifico la geolocalizacion y lo actualizao

		Coordenates coordenadaNueva = new Coordenates(50.0, 50.0);
		clientitoPerez.nuevaCoordenada(coordenadaNueva);
		transaction.commit();
		entityManager.clear(); // limpio la cache para obligarlo a que vuelva a buscar de la bd

		// lo recupero y verifico que se haya actualizado
		clientitoPerez = entityManager.find(Cliente.class, new Integer(1));
		assertEquals(coordenadaNueva.X(), clientitoPerez.miCoordenada().X(), 0);

	}

	@Test
	public void casoDePrueba2() {
		
		// en caso de no correr previamente el caso de prueba 1, ejecutar tambi�n las tres l�neas 
		// comentadas debajo

		transaction.begin();
		entityManager.persist(smartTv);
		transaction.commit();		
		transaction.begin();
		
		//recupero dispositivo
		DispositivoInteligente unDispositivoInteligente = entityManager.find(DispositivoInteligente.class, new Integer(1));
		System.out.println("El nombre del dispositivo es " + unDispositivoInteligente.getNombre());
		
		//muestro intervalos de encendido del mes
		List<IntervaloEstado> intervalos = unDispositivoInteligente.getIntervalosDeEstadoEnEsteMes(entityManager);
		intervalos.stream().filter(inter->inter.getEstado().encendido()).forEach(inter -> 
	    	System.out.println(inter.toString())
		);

		//modifico atributo
		unDispositivoInteligente.setNombre("Nombre modificado");
		//lo persisto
		transaction.commit();
		entityManager.clear(); //limpio cache
		
		//lo recupero nuevamente y verifico lo actualizado
		unDispositivoInteligente = entityManager.find(DispositivoInteligente.class, new Integer(1));
		
		assertEquals("El nombre del dispositivo debe ser Nombre Modificado","Nombre modificado",unDispositivoInteligente.getNombre());
		
	}

	@Test
	public void casoDePrueba3() {

		// persisto la regla con su actuador
		transaction.begin();
		entityManager.persist(unActuador);
		entityManager.persist(unaRegla);
		transaction.commit();

		transaction.begin();

		// recupero la regla
		Regla reglaCasoPrueba3 = entityManager.find(Regla.class, new Integer(1));

		// ejecuto la regla asociada al dispositivo smartTv
		reglaCasoPrueba3.ejecutar(50, 100, smartTv);

		// le modifico y persisto
		reglaCasoPrueba3.setIdFuncion(1);
		transaction.commit();

		entityManager.clear(); // limpio la cache

		// la recupero nuevamente
		reglaCasoPrueba3 = entityManager.find(Regla.class, new Integer(1));

		assertEquals("El id de la funcion debe ser 1", unaRegla.getIdFuncion(), 1);

	}

	@Test
	public void casoDePrueba4() {
		
		
		this.cargarTransformadores();
	
		//persisto las zonas
		transaction.begin();
		repoZonas.get().forEach(unaZona -> entityManager.persist(unaZona));
		System.out.println(transformadores.size());
		transaction.commit();
		
		//recupero todos los transformadores persistidos
		Query query = entityManager.createNativeQuery("SELECT * FROM transformador",Transformador.class);
		 
		List<Transformador> listaTransformadores =  query.getResultList();
		//registro su cantidad
		System.out.println(listaTransformadores.size());
		
		
		//creo un nuevo transformador
		Transformador transformadorcitoNuevo = new Transformador(new Coordenates(8.0,6.0));
		transformadorcitoNuevo.setCliente(clienteConDosDispositivos);
		
		
		ZonaGeografica Zonita = repoZonas.get().stream().findFirst().get();
		Zonita.agregarTransformador(transformadorcitoNuevo);
		cargador.guardarZona();
		
		//habria que leer nuevamente el json de entrada
		this.cargarTransformadores();	
		
		//persistir lo nuevo

		transaction.begin();	
		Query query2 = entityManager.createNativeQuery("select * from cliente where documento_numero = :idABuscar", Cliente.class ).setParameter("idABuscar", clienteConDosDispositivos.documento());
		
		clienteConDosDispositivos = (Cliente) query2.getSingleResult();
		entityManager.merge(Zonita);
		transaction.commit();	
		entityManager.clear(); // limpio la cache
		
		
		//vuelvo a traer todos los transformadores y evaluo que sea uno mas.
		
	
		Query queryNueva = entityManager.createNativeQuery("SELECT * FROM transformador",Transformador.class);
		 
		List<Transformador> listaTransformadoresNuevos =  queryNueva.getResultList();
		
		assertEquals("La cantidad debe ser 19", listaTransformadores.size() + 1, listaTransformadoresNuevos.size());
	}
/*	
	@Test
	public void casoDePrueba5() {
		
		Reporte reportePrueba = new Reporte();
		
		int idPrueba = 1;
		
		System.out.println("El consumo total del cliente Martin Perez es: " + reportePrueba.consumoPorHogarParticular(idPrueba));
		
		System.out.println("El consumo promedio por dispositivo del cliente Martin Perez es: " + reportePrueba.PromedioPorDispositivo(idPrueba));
		
		System.out.println("La energia suministrada promedio del primer transformador es: " + reportePrueba.consumoPromedioPorTransformador(idPrueba));
		
		String queryString = "SELECT * FROM Transformador Where id = :idTransformador";
		Query query = entityManager.createNativeQuery(queryString, Transformador.class).setParameter("idTransformador", idPrueba);
		Transformador elTransformador = (Transformador) query.getSingleResult();
		
		entityManager.clear();
		
		Cliente clienteDePrueba = elTransformador.getClientes().stream().findFirst().get();
		
		DispositivoInteligente dispositivoDePrueba = clienteDePrueba.getDispositivosInteligentes().stream().findFirst().get(); //en realidad tendria que usar el ID de este dispositivo, pero no lo puedo indicar, por eso uso el id harcodeado
		
		transaction.begin();
		
		queryString = "SELECT * FROM dispositivoInteligente Where id = :idDispositivo";
		query = entityManager.createNativeQuery(queryString, Transformador.class).setParameter("idTransformador", idPrueba);
		dispositivoDePrueba = (DispositivoInteligente) query.getSingleResult();
		
		// incrementamos el consumo del dispositivo al 1000%
		dispositivoDePrueba.incrementarConsumoEnPorcentaje(10);
		
		entityManager.persist(dispositivoDePrueba);
		transaction.commit();
		
		entityManager.clear();
		
		//vuelvo a traer el transformador
		
		queryString = "SELECT * FROM Transformador Where id = :idTransformador";
		query = entityManager.createNativeQuery(queryString, Transformador.class).setParameter("idTransformador", idPrueba);
		elTransformador = (Transformador) query.getSingleResult();
		
		System.out.println("El consumo para el transformador con el dispositivo aumentado es: " + reportePrueba.consumoPromedioPorTransformador(idPrueba)); //deberia ser el id del transformador
		
	}
*/	
	public void cargarTransformadores() {
		repoZonas.get().clear(); //limpio el repo para que no me agregue elementos repetidos si ya habian
		
		cargador.cargarZona();
		
		transformadores.clear();//limpio la lista de transformadores para que no me agregue elementos repetidos si ya habian
		repoZonas.get().forEach(unaZona -> transformadores.addAll(unaZona.transformadores()));
	}
	
}
