package minions_SL.PlazasTransporte;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.AfterClass;
import org.junit.After;

public class TransporteTest {
	
	private static Transporte transporte;

	@BeforeClass
	public static void setUpBeforeClass(){
		System.out.println("Antes de todas las pruebas");

	}
	
	@Before
	public void before() {
		try {
			transporte=new Transporte(0, 5);
			//System.out.println(transporte.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@After
	public void after() {
		transporte=null;
		/*
		if (transporte==null)
		System.out.println("transporte eliminado");*/
	}

	@Test
	public void testObtenerRestrinciones0() {
		//nivel 0 sin restricciones 100% de plazasTotales
    	int esperado=transporte.getPlazasTotales();
    	transporte.obtenerRestrinciones();
    	int actual=transporte.getPlazasTotales();
		assertEquals(esperado, actual);
	}
	
    @Test
    public void testObtenerRestrinciones1() {
		//nivel 1 restricciones del 80% de plazasTotales
    	transporte.setNivelRestricciones(1);
    	int esperado=(int) (transporte.getPlazasTotales()*0.8);
    	transporte.obtenerRestrinciones();
    	int actual=transporte.getPlazasTotales();
		assertEquals(esperado, actual);
    }

    @Test
    public void testObtenerRestrinciones2() {
		//nivel 2 restricciones del 60% de plazasTotales
    	transporte.setNivelRestricciones(2);
    	int esperado=(int) (transporte.getPlazasTotales()*0.6);
    	transporte.obtenerRestrinciones();
    	int actual=transporte.getPlazasTotales();
		assertEquals(esperado, actual);
    }
    
    @Test
    public void testObtenerRestrinciones3() {
		//nivel 3 restricciones del 40% de plazasTotales
    	transporte.setNivelRestricciones(3);
    	int esperado=(int) (transporte.getPlazasTotales()*0.4);
    	transporte.obtenerRestrinciones();
    	int actual=transporte.getPlazasTotales();
		assertEquals(esperado, actual);
    }
    
    @Test
    public void testObtenerRestrinciones4() {
		//nivel 4 restricciones del 30% de plazasTotales
    	transporte.setNivelRestricciones(4);
    	int esperado=(int) (transporte.getPlazasTotales()*0.3);
    	transporte.obtenerRestrinciones();
    	int actual=transporte.getPlazasTotales();
		assertEquals(esperado, actual);
    }

    @Test
    public void testObtenerRestrinciones03() {
		//nivel 2 restricciones del 60% de plazasTotales, plazasReservadasTotales del 60%
    	transporte.setNivelRestricciones(2);
    	int esperado=(int) ((int)(transporte.getPlazasTotales()*0.6)*0.6);
    	transporte.obtenerRestrinciones();
    	int actual=transporte.getPlazasReservadasTotales();
		assertEquals(esperado, actual);
    }
    
    @Test
    public void testObtenerRestrinciones04() {
		//nivel 3 restricciones del 40% de plazasTotales, plazasReservadasTotales del 80%
    	transporte.setNivelRestricciones(3);
    	int esperado=(int) ((int)(transporte.getPlazasTotales()*0.4)*0.8);
    	transporte.obtenerRestrinciones();
    	int actual=transporte.getPlazasReservadasTotales();
		assertEquals(esperado, actual);
    }
    
    @Test
    public void testObtenerRestrinciones05() {
		//nivel 4 restricciones del 30% de plazasTotales, plazasReservadasTotales del 90%
    	transporte.setNivelRestricciones(4);
    	int esperado=(int) ((int)(transporte.getPlazasTotales()*0.3)*0.9);
    	transporte.obtenerRestrinciones();
    	int actual=transporte.getPlazasReservadasTotales();
		assertEquals(esperado, actual);
    }
    
	@Test
	public void testObtenerRestrinciones000() {
		//nivel 0 sin restricciones 100% de plazasTotales, descuentoJoven 0.4
    	double esperado=0.4;
    	transporte.obtenerRestrinciones();
    	double actual=transporte.getDescuentoJoven();
    	double epsilon=0.001;
		assertEquals(esperado, actual, epsilon);
	}
    
	@Test
	public void testObtenerRestrinciones001() {
		//nivel 1 restricciones del 80% de plazasTotales, descuentoJoven 0.7
    	transporte.setNivelRestricciones(1);
    	double esperado=0.7;
    	transporte.obtenerRestrinciones();
    	double actual=transporte.getDescuentoJoven();
    	double epsilon=0.001;
		assertEquals(esperado, actual, epsilon);
	}
	
	@Test
	public void testObtenerRestrinciones003() {
		//nivel 3 restricciones del 40% de plazasTotales, descuentoJoven 1.2
    	transporte.setNivelRestricciones(3);
    	double esperado=1.2;
    	transporte.obtenerRestrinciones();
    	double actual=transporte.getDescuentoJoven();
    	double epsilon=0.001;
		assertEquals(esperado, actual, epsilon);
	}
	
	@Test
	public void testObtenerRestrinciones004() {
		//nivel 4 restricciones del 30% de plazasTotales, descuentoJoven 1.5
    	transporte.setNivelRestricciones(4);
    	double esperado=1.5;
    	transporte.obtenerRestrinciones();
    	double actual=transporte.getDescuentoJoven();
    	double epsilon=0.001;
		assertEquals(esperado, actual, epsilon);
	}
	@Test
	public void testObtenerRestrinciones0000() {
		//nivel 0 sin restricciones 100% de plazasTotales, descuentoViejo 0.2
    	double esperado=0.2;
    	transporte.obtenerRestrinciones();
    	double actual=transporte.getDescuentoViejo();
    	double epsilon=0.001;
		assertEquals(esperado, actual, epsilon);
	}
    
	@Test
	public void testObtenerRestrinciones0001() {
		//nivel 1 restricciones del 80% de plazasTotales, descuentoViejo 0.5
    	transporte.setNivelRestricciones(1);
    	double esperado=0.5;
    	transporte.obtenerRestrinciones();
    	double actual=transporte.getDescuentoViejo();
    	double epsilon=0.001;
		assertEquals(esperado, actual, epsilon);
	}
	
	@Test
	public void testObtenerRestrinciones0002() {
		//nivel 2 restricciones del 60% de plazasTotales, descuentoViejo 1.2
    	transporte.setNivelRestricciones(2);
    	double esperado=1.2;
    	transporte.obtenerRestrinciones();
    	double actual=transporte.getDescuentoViejo();
    	double epsilon=0.001;
		assertEquals(esperado, actual, epsilon);
	}
	
	@Test
	public void testObtenerRestrinciones0003() {
		//nivel 3 restricciones del 40% de plazasTotales, descuentoViejo 1.5
    	transporte.setNivelRestricciones(3);
    	double esperado=1.5;
    	transporte.obtenerRestrinciones();
    	double actual=transporte.getDescuentoViejo();
    	double epsilon=0.001;
		assertEquals(esperado, actual, epsilon);
	}
	
	@Test
	public void testObtenerRestrinciones0004() {
		//nivel 3 restricciones del 30% de plazasTotales, descuentoViejo -1
    	transporte.setNivelRestricciones(4);
    	double esperado=-1;
    	transporte.obtenerRestrinciones();
    	double actual=transporte.getDescuentoViejo();
    	double epsilon=0.001;
		assertEquals(esperado, actual, epsilon);
	}
}
