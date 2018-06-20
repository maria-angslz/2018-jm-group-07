
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.mockito.Mockito;

import Fixture.Fdispositivo;
import sge.estados.Encendido;

public class DispositivoTest extends Fdispositivo {
	@Test
	public void testEncederAlCumplirseCondicionDada() {
		unaRegla.ejecutar();
		assertTrue("El actuador debe enviar la orden de encender la luz",dispoInteligentes.stream().allMatch(dispositivo -> dispositivo.encendido()));
	}
	
	@Test
	public void testEncenderDispositivoApagado() {
		LuzInteligente.encender();
		Mockito.verify(mockDispositivoFisico, Mockito.only()).encender();
	}
	
	@Test
	public void testEncenderDispositivoYaEncendido() {
		//Inicia apagado asi que lo enciendo y luego trato de encenderlo nuevamente
		LuzInteligente.encender();
		LuzInteligente.encender();
		//El mensaje encender() deberia llamarse solo una vez
		Mockito.verify(mockDispositivoFisico, Mockito.only()).encender();
	}
	
	@Test
	public void testApagarDispositivoEncendido() {
		//Inicia apagado asi que primero le cambio el estado a encendido
		LuzInteligente.cambiarEstado(new Encendido());
		LuzInteligente.apagar();
		Mockito.verify(mockDispositivoFisico, Mockito.only()).apagar();
	}
	
	@Test
	public void testApagarDispositivoYaApagado() {
		LuzInteligente.apagar();
		Mockito.verify(mockDispositivoFisico, Mockito.never()).apagar();
	}
	
	@Test
	public void testModoAhorroDeEnergiaDispositivoApagado() {
		LuzInteligente.establecerModoAhorroDeEnergia();
		Mockito.verify(mockDispositivoFisico, Mockito.only()).entrarEnModoAhorroDeEnergia();
	}
	
	@Test
	public void testModoAhorroDeEnergiaDispositivoEncendido() {
		//Inicia apagado asi que primero le cambio el estado a encendido
		LuzInteligente.cambiarEstado(new Encendido());
		LuzInteligente.establecerModoAhorroDeEnergia();
		Mockito.verify(mockDispositivoFisico, Mockito.only()).entrarEnModoAhorroDeEnergia();
	}
	
	@Test
	public void testModoAhorroDeEnergiaDispositivoYaEnModoAhorroDeEnergia() {
		//Inicia apagado asi que primero le cambio el estado a encendido
		LuzInteligente.cambiarEstado(new Encendido());
		LuzInteligente.establecerModoAhorroDeEnergia();
		LuzInteligente.establecerModoAhorroDeEnergia();
		//El mensaje establecerModoAhorroDeEnergia() deberia llamarse solo una vez
		Mockito.verify(mockDispositivoFisico, Mockito.only()).entrarEnModoAhorroDeEnergia();
	}
}
