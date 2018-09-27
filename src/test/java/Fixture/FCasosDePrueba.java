package Fixture;


import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.Before;
import org.mockito.Mockito;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import sge.Coordenates;
import sge.Documento;
import sge.TipoDocumento;
import sge.Suministro.Transformador;
import sge.Suministro.ZonaGeografica;
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
import sge.persistencia.json.*;
import sge.persistencia.repos.RepoZonas;


public class FCasosDePrueba //extends AbstractPersistenceTest implements WithGlobalEntityManager 
{
	public EntityManager entityManager;
	public EntityTransaction transaction;
	public Cliente clienteConDosDispositivos;
	public DispositivoInteligente smartTv;
	public Regla unaRegla;
	public Actuador unActuador;
	public Sensor mockSensor;
	public List<Transformador> transformadores = new ArrayList<>();
	public CargaDatosWrapper cargador = new CargaDatosWrapper();
	public RepoZonas repoZonas = RepoZonas.getInstance();
	public List<DispositivoInteligente> dosDispositivosInteligentes;
	public List<DispositivoEstandar> sinDispositivosEstandar;
	public Transformador unTransformador;
	
	@Before
	public void init() throws FileNotFoundException {
		
		entityManager = PerThreadEntityManagers.getEntityManager();
		transaction = entityManager.getTransaction();
		
		smartTv = Factory.Inteligente().Televisor(0.6);
		DispositivoInteligente pc = Factory.Inteligente().Computadora(0.6);
		
		Categoria r3 = new CategoriaResidencial(60.71, 0.681, 325, 400);
		
		dosDispositivosInteligentes = new ArrayList<DispositivoInteligente>();
		sinDispositivosEstandar = new ArrayList<DispositivoEstandar>();
		
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
		unaRegla = new Regla("Caso De Prueba3", unActuador, 0);
		
		//this.cargarTransformadores();
		
		unTransformador = new Transformador(new Coordenates(1, 1));
		unTransformador.setCliente(clienteConDosDispositivos);
		
	}
	
	
}
