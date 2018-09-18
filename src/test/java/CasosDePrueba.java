import static org.junit.Assert.assertEquals;

import org.junit.Test;

import sge.Coordenates;
import sge.clientes.Cliente;
import sge.dispositivos.inteligentes.*;

public class CasosDePrueba extends Fixture.FCasosDePrueba {
	
	
	@Test
	public void casoDePrueba1 () {
		
		//persisto un cliente
		transaction.begin();
		entityManager.persist(clienteConDosDispositivos);
		transaction.commit();
		
		//lo recupero
		transaction.begin();
		Cliente clientitoPerez = entityManager.find(Cliente.class, new Integer(1));
		System.out.println(clientitoPerez.nombre());
				
		//le modifico la geolocalizacion y lo actualizao
		
		Coordenates coordenadaNueva =  new Coordenates(50,50);
		clientitoPerez.nuevaCoordenada(coordenadaNueva);		
		transaction.commit();  
		entityManager.clear(); //limpio la cache para obligarlo a que vuelva a buscar de la bd
		
		//lo recupero y verifico que se haya actualizado
		clientitoPerez = entityManager.find(Cliente.class, new Integer(1));
		assertEquals(coordenadaNueva.X(), clientitoPerez.miCoordenada().X(),0);
		
				
	}
	
	@Test
	public void casoDePrueba2() {
		
		transaction.begin();
		entityManager.persist(smartTv);
		transaction.commit();		
		
		transaction.begin();
		//recupero dispositivo
		DispositivoInteligente unDispositivoInteligente = entityManager.find(DispositivoInteligente.class, new Integer(1));
		System.out.println(unDispositivoInteligente.getNombre());
		
		//muestro intervalos de encendido del mes
		
		// ??????????????????????????????????????
		
		
		//modifico atributo
		unDispositivoInteligente.setNombre("Nombre modificado");
		//lo persisto
		transaction.commit();
		entityManager.clear(); //limpio cache
		
		unDispositivoInteligente = entityManager.find(DispositivoInteligente.class, new Integer(1));
		
		assertEquals("El nombre del dispositivo debe ser Nombre Modificado","Nombre modificado",unDispositivoInteligente.getNombre());
		
	}
	

}
