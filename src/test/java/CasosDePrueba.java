import static org.junit.Assert.assertEquals;

import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Query;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import sge.Coordenates;
import sge.clientes.Cliente;
import sge.dispositivos.inteligentes.*;
import sge.reglas.Actuador;
import sge.reglas.Regla;
import sge.estados.*;

public class CasosDePrueba extends Fixture.FCasosDePrueba {

	@Test
	public void casoDePrueba1() {

		// persisto un cliente
		transaction.begin();
		entityManager.persist(clienteConDosDispositivos);
		transaction.commit();

		// lo recupero
		transaction.begin();
		Cliente clientitoPerez = entityManager.find(Cliente.class, new Integer(1));
		System.out.println(clientitoPerez.nombre());

		// le modifico la geolocalizacion y lo actualizao

		Coordenates coordenadaNueva = new Coordenates(50, 50);
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

//		transaction.begin();
//		entityManager.persist(smartTv);
//		transaction.commit();		
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
		
		unDispositivoInteligente = entityManager.find(DispositivoInteligente.class, new Integer(1));
		
		assertEquals("El nombre del dispositivo debe ser Nombre Modificado","Nombre modificado",unDispositivoInteligente.getNombre());
		
	}

	@Test
	public void casoDePrueba3() { // Todav�a no funciona correctamente

		// persisto la regla con su actuador
		transaction.begin();
		entityManager.persist(unActuador);
		entityManager.persist(unaRegla);
		transaction.commit();

		transaction.begin();

		// recupero la regla
		Regla reglaCasoPrueba3 = entityManager.find(Regla.class, new Integer(1));

		// ejecuto la regla asociada al dispositivo smartTv
		// DispositivoInteligente tele =
		// entityManager.find(DispositivoInteligente.class, new Integer(1));
		reglaCasoPrueba3.ejecutar(50, 100, smartTv);

		// le modifico y persisto
		reglaCasoPrueba3.setIdFuncion(1);
		transaction.commit();

		entityManager.clear(); // limpio la cach�

		// la recupero nuevamente
		reglaCasoPrueba3 = entityManager.find(Regla.class, new Integer(1));

		assertEquals("El id de la funcion debe ser 1", unaRegla.getIdFuncion(), 1);

	}

}
