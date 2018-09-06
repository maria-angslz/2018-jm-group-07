import static org.junit.Assert.assertEquals;

import org.junit.Test;
import Fixture.FSumistro;

public class SuministroTest extends FSumistro{
	@Test
	public void calculoEnergiaTransformador() {
		assertEquals("hay un solo cliente, por lo que para los dos dispositivos que tiene, debe tener un consumo de 1.2 kwh", 1.2,unTransformador.energiaSuministrada(),2);
		
	}
	@Test
	public void calculoConsumoTotalZona() {
		assertEquals("hay un solo transformador, por lo que el consumo total deberia ser justamente 1.2kwh, el mismo que el del transformador",1.2,capitalFederal.consumoTotal(),2);
	}
}

