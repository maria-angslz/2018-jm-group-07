package sge.simplex;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import sge.dispositivos.Dispositivo;

public class ResultadoSimplex {
	public List<Double> getHorasOptimasDisps() {
		return horasOptimasDisps;
	}

	public double getHorasTotales() {
		return horasTotales;
	}
	
	public List<ConsumoYDispositivo> getPares() {
	    return IntStream.range(0, Math.min(horasOptimasDisps.size(), dispositivos.size()))
	            .mapToObj(i -> new ConsumoYDispositivo(horasOptimasDisps.get(i), dispositivos.get(i)))
	            .collect(Collectors.toList());
	}

	public List<Dispositivo> getDispositivos() {
		return dispositivos;
	}

	public List<Double> horasOptimasDisps;
	public double horasTotales;
	public List<Dispositivo> dispositivos;

	public ResultadoSimplex(List<Double> horasOptimasDisps, double horasTotales, List<Dispositivo> dispositivos) {
		this.horasOptimasDisps = horasOptimasDisps;
		this.horasTotales = horasTotales;
		this.dispositivos = dispositivos;
	}
}
