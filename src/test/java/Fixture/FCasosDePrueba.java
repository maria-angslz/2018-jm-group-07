package Fixture;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Before;
import org.mockito.Mockito;
import org.omg.CORBA.PERSIST_STORE;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import sge.Documento;
import sge.TipoDocumento;
import sge.categorias.Categoria;
import sge.categorias.CategoriaResidencial;
import sge.clientes.Cliente;
import sge.dispositivos.construccion.Factory;
import sge.dispositivos.estandar.DispositivoEstandar;
import sge.dispositivos.inteligentes.DispositivoInteligente;
import sge.dispositivos.inteligentes.DispositivoInteligenteFisico;

public class FCasosDePrueba //extends AbstractPersistenceTest implements WithGlobalEntityManager 
{
	public EntityManager entityManager;
	public EntityTransaction transaction;
	public Cliente clienteConDosDispositivos;
	
	@Before
	public void init() {
		
		entityManager = PerThreadEntityManagers.getEntityManager();
		transaction = entityManager.getTransaction();
		
		DispositivoInteligente smartTv = Factory.Inteligente().Televisor(0.6);
		DispositivoInteligente pc = Factory.Inteligente().Computadora(0.6);
		
		Categoria r3 = new CategoriaResidencial(60.71, 0.681, 325, 400);
		
		List<DispositivoInteligente> dosDispositivosInteligentes = new ArrayList<DispositivoInteligente>();
		List<DispositivoEstandar> sinDispositivosEstandar = new ArrayList<DispositivoEstandar>();
		
		DispositivoInteligenteFisico mockDispositivoFisico = Mockito.mock(DispositivoInteligenteFisico.class);
		
		smartTv.setDispositivoFisico(mockDispositivoFisico);
		smartTv.encender();
		dosDispositivosInteligentes.add(smartTv);
		dosDispositivosInteligentes.add(pc);
		
		clienteConDosDispositivos = new Cliente("Martin Perez",
				new Documento(40732178, TipoDocumento.DNI), "Belgrano 2251", "01149212334", r3,sinDispositivosEstandar, dosDispositivosInteligentes);
		
	}
}
