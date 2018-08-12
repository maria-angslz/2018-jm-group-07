package sge.simplex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.DoubleStream;

import org.apache.commons.math3.optim.MaxIter;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.LinearConstraint;
import org.apache.commons.math3.optim.linear.LinearConstraintSet;
import org.apache.commons.math3.optim.linear.LinearObjectiveFunction;
import org.apache.commons.math3.optim.linear.NonNegativeConstraint;
import org.apache.commons.math3.optim.linear.Relationship;
import org.apache.commons.math3.optim.linear.SimplexSolver;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

import sge.dispositivos.Dispositivo;
import sge.persistencia.json.JSONWrapper;

public class SgeSimplex {
	
	static SgeSimplex instancia;
	
	private SgeSimplex() { }
	
	public static SgeSimplex getInstance() {
		if (instancia == null)
			instancia = new SgeSimplex();
		return instancia;
	}

	private SimplexSolver ss = new SimplexSolver();
	static public double consumoMaximo = 612;
	
	private double[] listaConCerosYUnoEnLaPosicionNro(int size, int i) {
		double[] array = new double[size];
		array[i] = 1.0;
		return array;
	}
	private double[] unboxArray(List<Double> list){
	  double[] ret = new double[list.size()];
	  for(int i = 0;i < ret.length;i++)
	    ret[i] = list.get(i);
	  return ret;
	}
	private List<Double> boxArray(double[] array){
	  Double[] ret = new Double[array.length];
	  for(int i = 0;i < ret.length;i++)
	    ret[i] = array[i];
	  return Arrays.asList(ret);
	}
	private LinearConstraint constraint(int total, int indice, Relationship relacion, double valor) {
		return new LinearConstraint(listaConCerosYUnoEnLaPosicionNro(total,indice), relacion, valor);
	}
	private PointValuePair optimizarInterno(LinearObjectiveFunction f, List<LinearConstraint> restricciones) {
		return ss.optimize(new MaxIter(100), f, new LinearConstraintSet(restricciones), GoalType.MAXIMIZE, new NonNegativeConstraint(true));
	}
	
	public List<LinearConstraint> obtenerRestricciones(List<Dispositivo> dispositivos){
		List<Double> consumosHora = new ArrayList<Double>();
		List<LinearConstraint> minimos = new ArrayList<LinearConstraint>();
		List<LinearConstraint> maximos = new ArrayList<LinearConstraint>();
		
		// horas[i] entre minimo y maximo
		for(int i=0;i<dispositivos.size(); i++){
			Dispositivo d = dispositivos.get(i);
			consumosHora.add(d.consumoKWxHora());
			minimos.add(constraint(dispositivos.size(),i, Relationship.GEQ, d.getMinimo()));
			maximos.add(constraint(dispositivos.size(),i, Relationship.LEQ, d.getMaximo()));
		}
		
		List<LinearConstraint> restricciones = new ArrayList<LinearConstraint>();
		// Suma de horas*kwh <= consumoMaximo
		restricciones.add(new LinearConstraint(unboxArray(consumosHora), Relationship.LEQ, consumoMaximo));
		restricciones.addAll(minimos);
		restricciones.addAll(maximos);
		
		return restricciones;
	}
	
	public ResultadoSimplex optimizar(List<Dispositivo> dispositivos) {
		double[] coeficientes = new double[dispositivos.size()];
		java.util.Arrays.fill(coeficientes, 1.0);
		LinearObjectiveFunction f = new LinearObjectiveFunction(coeficientes, 0); 
		List<LinearConstraint> restricciones = this.obtenerRestricciones(dispositivos);
		PointValuePair res = optimizarInterno(f, restricciones);
		double[] resultados = res.getPoint();
		return new ResultadoSimplex(boxArray(resultados), res.getValue(), dispositivos);
	}

}
