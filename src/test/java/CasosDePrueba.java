import static org.junit.Assert.assertEquals;

import org.junit.Test;

import sge.Coordenates;
import sge.clientes.Cliente;
import sge.dispositivos.inteligentes.*;
import sge.reglas.Actuador;
import sge.reglas.Regla;

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
		
		// en caso de no correr previamente el caso de prueba 1, ejecutar también las tres líneas 
		// comentadas debajo

//		transaction.begin();
//		entityManager.persist(smartTv);
//		transaction.commit();		
		
		transaction.begin();
		//recupero dispositivo
		DispositivoInteligente unDispositivoInteligente = entityManager.find(DispositivoInteligente.class, new Integer(1));
		System.out.println("El nombre del dispotivo es " + unDispositivoInteligente.getNombre());
		
		//muestro intervalos de encendido del mes

		//esta es una idea aproximada sobre lo que tiene que quedar en el caso de prueba
		
//		Calendar c = Calendar.getInstance();
//		
//		List<RegistroEstado> resultado;
//				
//		resultado = entityManager.createQuery("SELECT * FROM registroestado WHERE MONTH(fechaCambio)= :mesDeHoy AND idNuevoEstado = :idNuevoEst").setParameter("mesDeHoy",c.get(Calendar.MONTH)).setParameter("idNuevoEst",1).getResultList();
//				
//		for (int i = 0; i < resultado.size(); i++) {
//		    System.out.println(resultado.get(i).getIdDispositivo());
//		    System.out.println(resultado.get(i).getIdNuevoEstado());
//		    System.out.println(resultado.get(i).getFechaCambio());
//			}
//
		
		//modifico atributo
		unDispositivoInteligente.setNombre("Nombre modificado");
		//lo persisto
		transaction.commit();
		entityManager.clear(); //limpio cache
		
		unDispositivoInteligente = entityManager.find(DispositivoInteligente.class, new Integer(1));
		
		assertEquals("El nombre del dispositivo debe ser Nombre Modificado","Nombre modificado",unDispositivoInteligente.getNombre());
		
	}
	
	@Test
	public void casoDePrueba3() { //Todavía no funciona correctamente
				
		//persisto la regla con su actuador
		transaction.begin();
		entityManager.persist(unActuador);
		entityManager.persist(unaRegla);
		transaction.commit();
		
		transaction.begin();
		
		//recupero la regla
		Regla reglaCasoPrueba3 = entityManager.find(Regla.class, new Integer(1));
		
		//ejecuto la regla asociada al dispositivo smartTv
		//DispositivoInteligente tele = entityManager.find(DispositivoInteligente.class, new Integer(1));
		reglaCasoPrueba3.ejecutar(50,100,smartTv);

		//le modifico y persisto
		reglaCasoPrueba3.setIdFuncion(1);
		transaction.commit();
		
		entityManager.clear(); //limpio la caché
		
		//la recupero nuevamente
		reglaCasoPrueba3 = entityManager.find(Regla.class, new Integer(1));
		
		assertEquals("El id de la funcion debe ser 1",unaRegla.getIdFuncion(),1);
		
	}
	

}
