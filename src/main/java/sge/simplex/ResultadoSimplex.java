package sge.simplex;

import java.util.List;

public class ResultadoSimplex {
	public List<Double> horasOptimasDisps;
	public double horasTotales;

	public ResultadoSimplex(List<Double> horasOptimasDisps, double horasTotales) {
		this.horasOptimasDisps = horasOptimasDisps;
		this.horasTotales = horasTotales;
	}
}
