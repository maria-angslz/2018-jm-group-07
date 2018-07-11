package sge.simplex;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.DoubleStream;

import org.apache.commons.math3.optim.linear.LinearConstraint;
import org.apache.commons.math3.optim.linear.Relationship;
import org.apache.commons.math3.optim.linear.SimplexSolver;

public class SgeSimplex {
	
	public SgeSimplex(){
		
	}

	private SimplexSolver ss = new SimplexSolver();
	private double consumoMaximo = 612;
	
	private List<LinearConstraint> restricciones = new ArrayList<LinearConstraint>();

	/*
	public void inicializar(double[] consumos) { 
		restricciones.add(new LinearConstraint(double[] consumos, Relationship.LEQ, consumoMaximo));
		//falta continuar
	}*/ //ver tipo de dato de la lista de consumos
	
	public double maximizar() {
		return 100; //agregar funcionalidad
	}

}
