package Fixture;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import org.junit.Before;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

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
	protected Sensor mockSensorLuminosidad;
	
	@Before
	
	public void Init() {
		LuzInteligente = new DispositivoInteligente("Luz",100);
		
		dispoInteligentes = new ArrayList<DispositivoInteligente>();
		
		dispoInteligentes.add(LuzInteligente);

		Consumer<DispositivoInteligente> accionAEjecutar = (dispositivo) -> dispositivo.encender();
		mockSensorLuminosidad = Mockito.mock(Sensor.class);
		when(mockSensorLuminosidad.medir()).thenReturn((float) 0); //valor mockeado
		Actuador unActuador = new Actuador("apaga luz", dispoInteligentes, accionAEjecutar);
		Function<Float,Boolean> funcionCumplir = (medicion) -> (Boolean) ((50) > (medicion));
		unaRegla = new Regla("Determinacion de encendido de luz", mockSensorLuminosidad, unActuador, funcionCumplir);
		mockDispositivoFisico = Mockito.mock(DispositivoInteligenteFisico.class);
		LuzInteligente.setDispositivoFisico(mockDispositivoFisico);
	}

}
