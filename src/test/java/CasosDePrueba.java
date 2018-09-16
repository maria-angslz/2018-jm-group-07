import org.junit.Test;

public class CasosDePrueba extends Fixture.FCasosDePrueba {
	
	@Test
	public void casoDePrueba1 () {
		
		transaction.begin();
		entityManager().persist(clienteConDosDispositivos);
		transaction.commit(); 
		
		
	}

}
