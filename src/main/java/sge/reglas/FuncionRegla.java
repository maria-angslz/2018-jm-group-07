package sge.reglas;

import java.util.function.BiFunction;

public enum FuncionRegla {

	MAYORQUE((medicion, maximo) -> (maximo) < (medicion)),
	MENORQUE((medicion, maximo) -> (maximo) > (medicion));

	BiFunction<Float, Double, Boolean> funcionCumplir;

	private FuncionRegla(BiFunction<Float, Double, Boolean> unaFuncion) {
		this.funcionCumplir = unaFuncion;
	}

	public Boolean ejecutar(float medicion, double maximo) {
		return funcionCumplir.apply(medicion, maximo);
	}

}