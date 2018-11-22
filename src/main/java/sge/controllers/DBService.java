package sge.controllers;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import sge.clientes.Cliente;

public class DBService {
	
	public static Query obtenerClientePorDNI(String dni) {
		
		String queryString = "SELECT * FROM cliente WHERE documento_numero = :dni";
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		return entityManager.createNativeQuery(queryString, Cliente.class).setParameter("dni", Integer.parseInt(dni));
		 
	}

}
