package sge.simplex;

import java.util.List;

import sge.dispositivos.Dispositivo;

public class ResultadoSimplex {
	public List<Double> horasOptimasDisps;
	public double horasTotales;
	public List<Dispositivo> dispositivos;

	public ResultadoSimplex(List<Double> horasOptimasDisps, double horasTotales, List<Dispositivo> dispositivos) {
		this.horasOptimasDisps = horasOptimasDisps;
		this.horasTotales = horasTotales;
		this.dispositivos = dispositivos;
	}
}
