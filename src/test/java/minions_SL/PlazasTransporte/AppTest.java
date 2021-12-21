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
	public void testApp() {
		App app=new App();
		assertNotNull(app);	
	}
	
	@Test
	public void testMain0() {
		String [] args={"App","1000","5"};
		App.main(args);	
	}
	
	@Test
	public void testMain1() {
		String [] args={"App","-10","5"};
		App.main(args);	
	}
	
	@Test
	public void testMain2() {
		String [] args={"App","1000","1"};
		App.main(args);	
	}
    
}
