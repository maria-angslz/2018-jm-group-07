package Fixture;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import org.junit.Before;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

import sge.dispositivos.inteligentes.DispositivoInteligente;
import sge.dispositivos.inteligentes.DispositivoInteligenteFisico;
import sge.dispositivos.inteligentes.TipoDeDispositivo;
import sge.reglas.Actuador;
import sge.reglas.Regla;
import sge.reglas.Sensor;

public class Fdispositivo {
	protected List<DispositivoInteligente> dispoInteligentes;
	protected Regla unaRegla;
	protected DispositivoInteligente LuzInteligente;
	protected DispositivoInteligenteFisico mockDispositivoFisico;
	protected Sensor mockSensorLuminosidad;
	
	@Before
	
	public void Init() {
		LuzInteligente = new DispositivoInteligente("Luz",100, TipoDeDispositivo.Lampara);
		
//		dispoInteligentes = new ArrayList<DispositivoInteligente>();
		
//		dispoInteligentes.add(LuzInteligente);

		Consumer<DispositivoInteligente> accionAEjecutar = (dispositivo) -> dispositivo.encender();
		mockSensorLuminosidad = Mockito.mock(Sensor.class);
		when(mockSensorLuminosidad.medir()).thenReturn((float) 0); //valor mockeado
		Actuador unActuador = new Actuador("apaga luz", accionAEjecutar);
		Function<Float,Boolean> funcionCumplir = (medicion) -> (Boolean) ((50) > (medicion));
		unaRegla = new Regla("Determinacion de encendido de luz", mockSensorLuminosidad, unActuador);
		unaRegla.setFuncion(funcionCumplir);
		mockDispositivoFisico = Mockito.mock(DispositivoInteligenteFisico.class);
		LuzInteligente.setDispositivoFisico(mockDispositivoFisico);
	}

}
