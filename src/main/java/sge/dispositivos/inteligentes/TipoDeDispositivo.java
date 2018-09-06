package sge.dispositivos.inteligentes;

public enum TipoDeDispositivo {
	
	AireAcondicionado(90, 360),
	Lampara(90, 360),
	Televisor(90, 360),
	Lavarropas(6, 30),
	Computadora(60, 360),
	Microondas(3, 15),
	Plancha(3, 30),
	Ventilador(120, 360),
	Heladera(0,0); //Ver por qué no esta en la tabla de tipo de dispositivos
	
	private final int minimo;
	private final int maximo;
	
	TipoDeDispositivo(int minimo, int maximo){
		this.minimo = minimo;
		this.maximo = maximo;
	}
	
	public int getMinimo() {
		return minimo;
	}
	
	public int getMaximo() {
		return maximo;
	}
	
}
