package Fixture;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import org.junit.Before;
import org.mockito.Mockito;

import sge.dispositivosInteligentes.DispositivoInteligente;
import sge.dispositivosInteligentes.DispositivoInteligenteFisico;
import sge.reglas.Actuador;
import sge.reglas.Regla;
import sge.reglas.Sensor;

public class Fdispositivo {
	protected List<DispositivoInteligente> dispoInteligentes;
	protected DispositivoInteligente LuzInteligente;
	protected Regla unaRegla;
	protected DispositivoInteligenteFisico mockDispositivoFisico;
	
	@Before
	
	public void Init() {
		LuzInteligente = new DispositivoInteligente("Luz",100);
		
		dispoInteligentes = new ArrayList<DispositivoInteligente>();
		
		dispoInteligentes.add(LuzInteligente);

		Consumer<DispositivoInteligente> accionAEjecutar = (dispositivo) -> dispositivo.encender();
		Sensor sensorLuminosidad = new Sensor("luminosidad");
		Actuador unActuador = new Actuador("apaga luz", dispoInteligentes, accionAEjecutar);
		Function<Float,Boolean> funcionCumplir = (medicion) -> (Boolean) ((50) > (medicion));
		unaRegla = new Regla("Determinacion de encendido de luz", sensorLuminosidad, unActuador, funcionCumplir);
		
		mockDispositivoFisico = Mockito.mock(DispositivoInteligenteFisico.class);
		LuzInteligente.setDispositivoFisico(mockDispositivoFisico);
	}

}
