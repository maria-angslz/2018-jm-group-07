package Fixture;

import static org.junit.Assert.assertEquals;

import java.util.function.Consumer;
import java.util.function.Function;

import org.junit.Before;

import sge.dispositivosInteligentes.Actuador;
import sge.dispositivosInteligentes.DispositivoInteligente;
import sge.dispositivosInteligentes.Regla;
import sge.dispositivosInteligentes.Sensor;

public class Fdispositivo {
	protected DispositivoInteligente LuzInteligente;
	@Before
	public void Init() {
		LuzInteligente = new DispositivoInteligente("Luz",100);

		Consumer<DispositivoInteligente> accionAEjecutar = (dispositivo) -> dispositivo.encender();
		Sensor sensorLuminosidad = new Sensor("luminosidad");
		Actuador unActuador = new Actuador("apaga luz",accionAEjecutar);
		Function<Float,Boolean> funcionCumplir = (medicion) -> (Boolean) ((50) > (medicion));
		Regla unaRegla = new Regla("Determinacion de encendido de luz", sensorLuminosidad, unActuador, funcionCumplir);
		LuzInteligente.ejecutarRegla(unaRegla);
		
	}

}
