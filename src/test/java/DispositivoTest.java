
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.mockito.Mockito;

import Fixture.Fdispositivo;
import sge.estados.AhorroDeEnergia;
import sge.estados.Apagado;
import sge.estados.Encendido;

public class DispositivoTest extends Fdispositivo {
	@Test
	public void testEncederAlCumplirseCondicionDada() {
		unaRegla.ejecutar(LuzInteligente,50);
		assertTrue("El actuador debe enviar la orden de encender la luz",LuzInteligente.encendido());
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
	
	@Test
	public void testCambioDeEstadoDeApagadoAEncendido() {
		//Inicia apagado asi que le cambio el estado a encendido
		LuzInteligente.cambiarEstado(new Encendido());
		assertTrue("El dispositivo ahora debe estar en estado encendido", LuzInteligente.encendido());
	}
	
	@Test
	public void testCambioDeEstadoDeEncendidoAapagado() {
		//Inicia apagado asi que le cambio el estado a encendido y luego a apagado
		LuzInteligente.cambiarEstado(new Encendido());
		LuzInteligente.cambiarEstado(new Apagado());
		assertTrue("El dispositivo ahora debe estar en estado apagado", LuzInteligente.apagado());
	}
	
	@Test
	public void testCambioDeEstadoDeEncendidoAModoAhorroDeEnergia() {
		//Inicia apagado asi que le cambio el estado a encendido y luego a modo ahorro de energia
		LuzInteligente.cambiarEstado(new Encendido());
		LuzInteligente.cambiarEstado(new AhorroDeEnergia());
		assertTrue("El dispositivo ahora debe estar en estado modo ahorro de energia", LuzInteligente.modoAhorroDeEnergia());
	}
}
