package sge;

import sge.persistencia.json.CargaDatosWrapper;
import sge.persistencia.repos.RepoAdmins;
import sge.persistencia.repos.RepoCatResidenciales;
import sge.persistencia.repos.RepoZonas;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import sge.Web.Server;
import sge.jobBackround.*;

public class Main {
	public static CargaDatosWrapper cargador = new CargaDatosWrapper();
	
	public static void main(String[] args) {
		cargador.cargarAdmins();
		cargador.cargarCategorias();
		cargador.cargarClientes();
		cargador.cargarZona();
		
		persistirRepos();
		
		JobRecategorizacion recategorizacion = new JobRecategorizacion();
		JobSimplex simplex = new JobSimplex();
		recategorizacion.iniciar();
		simplex.iniciar();
		
		//Cargo server
		Server servidor = new Server();
		servidor.iniciar();
	}
	
	public static void persistirRepos() {
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		
		transaction.begin();
		RepoZonas.getInstance().get().forEach(unaZona -> entityManager.persist(unaZona));
		transaction.commit();
	}
	
}
