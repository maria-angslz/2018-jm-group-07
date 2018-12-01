import static org.junit.Assert.assertEquals;

import org.junit.Test;
import Fixture.FSumistro;

public class SuministroTest extends FSumistro{
	@Test
	public void calculoEnergiaTransformador() {
		assertEquals("hay un solo cliente, por lo que para los dos dispositivos que tiene, debe tener un consumo de 1.2 kwh", 1.2,unTransformador.energiaSuministrada(),2);
		
	}
}

