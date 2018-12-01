package Fixture;

import java.util.List;

import org.junit.Before;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

import sge.dispositivos.construccion.Factory;
import sge.dispositivos.inteligentes.DispositivoInteligente;
import sge.dispositivos.inteligentes.DispositivoInteligenteFisico;
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
		LuzInteligente = Factory.Inteligente().Lampara(100);

		mockSensorLuminosidad = Mockito.mock(Sensor.class);
		when(mockSensorLuminosidad.medir()).thenReturn((float) 0); //valor mockeado
		Actuador unActuador = new Actuador("encender luz", 1);
		unaRegla = new Regla("Determinacion de encendido de luz", mockSensorLuminosidad, unActuador,1);
		mockDispositivoFisico = Mockito.mock(DispositivoInteligenteFisico.class);
		LuzInteligente.setDispositivoFisico(mockDispositivoFisico);
	}

}
