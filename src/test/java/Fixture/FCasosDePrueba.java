package Fixture;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.Before;
import org.mockito.Mockito;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import sge.Coordenates;
import sge.Documento;
import sge.TipoDocumento;
import sge.categorias.Categoria;
import sge.categorias.CategoriaResidencial;
import sge.clientes.Cliente;
import sge.dispositivos.construccion.Factory;
import sge.dispositivos.estandar.DispositivoEstandar;
import sge.dispositivos.inteligentes.DispositivoInteligente;
import sge.dispositivos.inteligentes.DispositivoInteligenteFisico;
import sge.reglas.Actuador;
import sge.reglas.Regla;
import sge.reglas.Sensor;

public class FCasosDePrueba //extends AbstractPersistenceTest implements WithGlobalEntityManager 
{
	public EntityManager entityManager;
	public EntityTransaction transaction;
	public Cliente clienteConDosDispositivos;
	public DispositivoInteligente smartTv;
	public Regla unaRegla;
	public Actuador unActuador;
	public Sensor mockSensor;
	
	@Before
	public void init() {
		
		entityManager = PerThreadEntityManagers.getEntityManager();
		transaction = entityManager.getTransaction();
		
		smartTv = Factory.Inteligente().Televisor(0.6);
		DispositivoInteligente pc = Factory.Inteligente().Computadora(0.6);
		
		Categoria r3 = new CategoriaResidencial(60.71, 0.681, 325, 400);
		
		List<DispositivoInteligente> dosDispositivosInteligentes = new ArrayList<DispositivoInteligente>();
		List<DispositivoEstandar> sinDispositivosEstandar = new ArrayList<DispositivoEstandar>();
		
		DispositivoInteligenteFisico mockDispositivoFisico = Mockito.mock(DispositivoInteligenteFisico.class);
		
		smartTv.setDispositivoFisico(mockDispositivoFisico);
		smartTv.encender();
		smartTv.apagar();
		smartTv.encender();
		smartTv.apagar();
		smartTv.encender();
		dosDispositivosInteligentes.add(smartTv);
		dosDispositivosInteligentes.add(pc);
		
		
		clienteConDosDispositivos = new Cliente("Martin Perez",
				new Documento(40732178, TipoDocumento.DNI), "Belgrano 2251", "01149212334", r3,sinDispositivosEstandar, dosDispositivosInteligentes, new Coordenates(1,1));
		
		unActuador = new Actuador("apagar dispositivo", 0);
		unaRegla = new Regla("Caso De¨Prueba3", unActuador, 0);
		

	}
}
