package minions_SL.PlazasTransporte;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
    
	@Test
	public void testApp() {
			App app=new App();
			assertNotNull(app);	
	}
    
    @Test
    public void obtenerNivelIATest1() {
    	int esperado=-1;
    	int actual=App.obtenerNivelIA(-500);
		assertEquals(esperado, actual);
    }
    
    @Test
    public void obtenerNivelIATest2() {
    	int esperado=0;
    	int actual=App.obtenerNivelIA(50);
		assertEquals(esperado, actual);
    }
    
    @Test
    public void obtenerNivelIATest3() {
    	int esperado=1;
    	int actual=App.obtenerNivelIA(150);
		assertEquals(esperado, actual);
    }
    
    @Test
    public void obtenerNivelIATest4() {
    	int esperado=2;
    	int actual=App.obtenerNivelIA(250);
		assertEquals(esperado, actual);
    }
    
    @Test
    public void obtenerNivelIATest5() {
    	int esperado=3;
    	int actual=App.obtenerNivelIA(350);
		assertEquals(esperado, actual);
    }
    
    @Test
    public void obtenerNivelIATest6() {
    	int esperado=4;
    	int actual=App.obtenerNivelIA(1000);
		assertEquals(esperado, actual);
    }
}
