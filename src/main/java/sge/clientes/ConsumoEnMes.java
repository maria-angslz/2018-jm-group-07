package sge.clientes;

public class ConsumoEnMes {
	String mes;
	Double consumo;

	public ConsumoEnMes(String mes, Double consumo) {
		super();
		this.mes = mes;
		this.consumo = consumo;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public Double getConsumo() {
		return consumo;
	}

	public void setConsumo(Double consumo) {
		this.consumo = consumo;
	}
}
