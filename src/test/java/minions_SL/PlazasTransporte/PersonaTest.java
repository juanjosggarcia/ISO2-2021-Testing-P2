package minions_SL.PlazasTransporte;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PersonaTest {
	
	private static Transporte transporte;
	private static Persona persona;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	
	@Before
	public void before() {
		try {
			transporte=new Transporte(0, 5);
			System.out.println(transporte.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testPersona() {
		try {
			persona=new Persona(false, -1, false, true, 20, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertNotNull(persona);
	}

	@Test
	public void testEvaluarPersona0() {
		//no puede viajar
		boolean enfermo=true; 
		int diasContanto=-1;
		boolean sintomas=false;
		boolean pasaporteCovid=true;
		int edad=20;
		boolean esEsencial=false;
		
		persona=new Persona(enfermo, diasContanto, sintomas, pasaporteCovid, edad, esEsencial);
		
		double esperado=-1;	
    	double epsilon=0.001;		

	    try {
	    	System.out.println(transporte.toString());
	    	
	    	double actual=persona.evaluarPersona(transporte);;
	    	assertEquals(esperado, actual, epsilon);
	    } catch (ExcepcionNoPlazas e) {
	    	System.out.println("error, ha saltado la excepcion");
	    }
	}
	
	@Test
	public void testEvaluarPersona1() {
		//no puede viajar
		boolean enfermo=false; 
		int diasContanto=-1;
		boolean sintomas=true;
		boolean pasaporteCovid=true;
		int edad=20;
		boolean esEsencial=false;
		
		persona=new Persona(enfermo, diasContanto, sintomas, pasaporteCovid, edad, esEsencial);
		
		double esperado=-1;	
    	double epsilon=0.001;		

	    try {
	    	System.out.println(transporte.toString());
	    	
	    	double actual=persona.evaluarPersona(transporte);;
	    	assertEquals(esperado, actual, epsilon);
	    } catch (ExcepcionNoPlazas e) {
	    	System.out.println("error, ha saltado la excepcion");
	    }
	}
	
	@Test
	public void testEvaluarPersona2() {
		//no puede viajar
		boolean enfermo=false; 
		int diasContanto=0;
		boolean sintomas=false;
		boolean pasaporteCovid=true;
		int edad=20;
		boolean esEsencial=false;
		
		persona=new Persona(enfermo, diasContanto, sintomas, pasaporteCovid, edad, esEsencial);
		
		double esperado=-1;	
    	double epsilon=0.001;		

	    try {
	    	System.out.println(transporte.toString());
	    	
	    	double actual=persona.evaluarPersona(transporte);;
	    	assertEquals(esperado, actual, epsilon);
	    } catch (ExcepcionNoPlazas e) {
	    	System.out.println("error, ha saltado la excepcion");
	    }
	}
	
	@Test
	public void testEvaluarPersona3() {
		//puede viajar
		boolean enfermo=false; 
		int diasContanto=11;
		boolean sintomas=false;
		boolean pasaporteCovid=true;
		int edad=20;
		boolean esEsencial=false;
		
		persona=new Persona(enfermo, diasContanto, sintomas, pasaporteCovid, edad, esEsencial);
		
		double noEsperado=-1;	
    	double epsilon=0.001;		

	    try {
	    	System.out.println(transporte.toString());
	    	
	    	double actual=persona.evaluarPersona(transporte);;
	    	assertNotEquals(noEsperado, actual, epsilon);
	    } catch (ExcepcionNoPlazas e) {
	    	System.out.println("error, ha saltado la excepcion");
	    }
	}
	
	@Test
	public void testEvaluarPersona00() {
		//no pasaporte covid
		boolean enfermo=false; 
		int diasContanto=-1;
		boolean sintomas=false;
		boolean pasaporteCovid=false;
		int edad=20;
		boolean esEsencial=false;
		
		persona=new Persona(enfermo, diasContanto, sintomas, pasaporteCovid, edad, esEsencial);
		
		System.out.println("numero plazas dispo: "+transporte.getPlazasLibres());

	    try {
	    	System.out.println(transporte.toString());
	    	persona.evaluarPersona(transporte);
	        fail("No ha saltado la excepcion");
	    } catch (ExcepcionNoPlazas e) {
	    	assertTrue( true );
	    }

	}
	
	@Test
	public void testEvaluarPersona01() {
		//no quedan plazas libres
		boolean enfermo=false; 
		int diasContanto=-1;
		boolean sintomas=false;
		boolean pasaporteCovid=true;
		int edad=20;
		boolean esEsencial=false;
		
		persona=new Persona(enfermo, diasContanto, sintomas, pasaporteCovid, edad, esEsencial);
		
		transporte.llenarPlaza();
		transporte.llenarPlaza();
		transporte.llenarPlaza();
		transporte.llenarPlaza();
		transporte.llenarPlaza();
		System.out.println("numero plazas dispo: "+transporte.getPlazasLibres());

	    try {
	    	System.out.println(transporte.toString());
	    	persona.evaluarPersona(transporte);
	        fail("No ha saltado la excepcion");
	    } catch (ExcepcionNoPlazas e) {
	    	assertTrue( true );
	    }

	}
	
	@Test
	public void testEvaluarPersona001() {
		//nivel 4 restricciones del 30% de plazasTotales, plazasReservadasTotales del 90%
		//edad mayor 65
		boolean enfermo=false; 
		int diasContanto=-1;
		boolean sintomas=false;
		boolean pasaporteCovid=true;
		int edad=70;
		boolean esEsencial=false;
		
		transporte.setNivelRestricciones(4);
		transporte.obtenerRestrinciones();
		System.out.println(transporte.toString());
		
		persona=new Persona(enfermo, diasContanto, sintomas, pasaporteCovid, edad, esEsencial);
		
		double esperado=-1;	
    	double epsilon=0.001;		

	    try {
	    	System.out.println(transporte.toString());
	    	
	    	double actual=persona.evaluarPersona(transporte);;
	    	assertEquals(esperado, actual, epsilon);
	    } catch (ExcepcionNoPlazas e) {
	    	System.out.println("error, ha saltado la excepcion");
	    }

	}
	
	@Test
	public void testEvaluarPersona002() {
		//nivel !=4 restricciones del 30% de plazasTotales, plazasReservadasTotales del 90%
		//edad mayor 65
		boolean enfermo=false; 
		int diasContanto=-1;
		boolean sintomas=false;
		boolean pasaporteCovid=true;
		int edad=70;
		boolean esEsencial=false;
		
		transporte.setNivelRestricciones(0);
		transporte.obtenerRestrinciones();
		System.out.println(transporte.toString());
		
		persona=new Persona(enfermo, diasContanto, sintomas, pasaporteCovid, edad, esEsencial);
		
		double noEsperado=-1;	
    	double epsilon=0.001;		

	    try {
	    	System.out.println(transporte.toString());
	    	
	    	double actual=persona.evaluarPersona(transporte);;
	    	assertNotEquals(noEsperado, actual, epsilon);
	    } catch (ExcepcionNoPlazas e) {
	    	System.out.println("error, ha saltado la excepcion");
	    }

	}
	
	@Test
	public void testEvaluarPersona003() {
		//nivel 4 restricciones del 30% de plazasTotales, plazasReservadasTotales del 90%
		//edad menor 65
		boolean enfermo=false; 
		int diasContanto=-1;
		boolean sintomas=false;
		boolean pasaporteCovid=true;
		int edad=40;
		boolean esEsencial=false;
		
		transporte.setNivelRestricciones(4);
		transporte.obtenerRestrinciones();
		System.out.println(transporte.toString());
		
		persona=new Persona(enfermo, diasContanto, sintomas, pasaporteCovid, edad, esEsencial);
		
		double noEsperado=-1;	
    	double epsilon=0.001;		

	    try {
	    	System.out.println(transporte.toString());
	    	
	    	double actual=persona.evaluarPersona(transporte);;
	    	assertNotEquals(noEsperado, actual, epsilon);
	    } catch (ExcepcionNoPlazas e) {
	    	System.out.println("error, ha saltado la excepcion");
	    }

	}
	
	@Test
	public void testEvaluarPersona0002() {
		//nivel 2 restricciones del 60% de plazasTotales, plazasReservadasTotales del 60%
		//no quedan plazas libres reservadas esencial=TRUE
		boolean enfermo=false; 
		int diasContanto=-1;
		boolean sintomas=false;
		boolean pasaporteCovid=true;
		int edad=20;
		boolean esEsencial=true;
		
		transporte.setNivelRestricciones(2);
		transporte.obtenerRestrinciones();
		System.out.println("ESTE ES");
		System.out.println(transporte.toString());
		
		persona=new Persona(enfermo, diasContanto, sintomas, pasaporteCovid, edad, esEsencial);
		
		transporte.llenarPlazaReservada();
		System.out.println(transporte.toString());

	    try {
	    	System.out.println(transporte.toString());
	    	persona.evaluarPersona(transporte);
	        fail("No ha saltado la excepcion");
	    } catch (ExcepcionNoPlazas e) {
	    	System.out.println("bien, ha saltado la excepcion");
	    	assertTrue( true );
	    }

	}
	
	@Test
	public void testEvaluarPersona0003() {
		//nivel 3 restricciones del 40% de plazasTotales, plazasReservadasTotales del 80%
		//no quedan plazas libres reservadas esencial=TRUE
		boolean enfermo=false;
		int diasContanto=-1;
		boolean sintomas=false;
		boolean pasaporteCovid=true;
		int edad=20;
		boolean esEsencial=true;
		
		transporte.setNivelRestricciones(3);
		transporte.obtenerRestrinciones();
		System.out.println(transporte.toString());
		
		persona=new Persona(enfermo, diasContanto, sintomas, pasaporteCovid, edad, esEsencial);
		
		transporte.llenarPlazaReservada();
		System.out.println("numero plazas disponibles normales: "+transporte.getPlazasLibres()+"; plazas reservadas libres: "+transporte.getPlazasReservadasLibres());

	    try {
	    	System.out.println(transporte.toString());
	    	persona.evaluarPersona(transporte);
	        fail("No ha saltado la excepcion");
	    } catch (ExcepcionNoPlazas e) {
	    	System.out.println("bien, ha saltado la excepcion");
	    	assertTrue( true );
	    }

	}
	
	@Test
	public void testEvaluarPersona0004() {
		//nivel 4 restricciones del 30% de plazasTotales, plazasReservadasTotales del 90%
		//no quedan plazas libres reservadas esencial=TRUE
		boolean enfermo=false; 
		int diasContanto=-1;
		boolean sintomas=false;
		boolean pasaporteCovid=true;
		int edad=20;
		boolean esEsencial=true;
		
		transporte.setNivelRestricciones(4);
		transporte.obtenerRestrinciones();
		System.out.println(transporte.toString());
		
		persona=new Persona(enfermo, diasContanto, sintomas, pasaporteCovid, edad, esEsencial);
		
		transporte.llenarPlazaReservada();
		System.out.println("numero plazas disponibles normales: "+transporte.getPlazasLibres()+"; plazas reservadas libres: "+transporte.getPlazasReservadasLibres());

	    try {
	    	System.out.println(transporte.toString());
	    	persona.evaluarPersona(transporte);
	        fail("No ha saltado la excepcion");
	    } catch (ExcepcionNoPlazas e) {
	    	System.out.println("bien, ha saltado la excepcion");
	    	assertTrue( true );
	    }
	    
	}
	
	@Test
	public void testEvaluarPersona00002() {
		//nivel 2 restricciones del 60% de plazasTotales, plazasReservadasTotales del 60%
		//no quedan plazas libres reservadas esencial FALSE
		boolean enfermo=false; 
		int diasContanto=-1;
		boolean sintomas=false;
		boolean pasaporteCovid=true;
		int edad=20;
		boolean esEsencial=false;
		
		transporte.setNivelRestricciones(2);
		transporte.obtenerRestrinciones();
		System.out.println(transporte.toString());
		
		persona=new Persona(enfermo, diasContanto, sintomas, pasaporteCovid, edad, esEsencial);
		
		transporte.llenarPlaza();
		transporte.llenarPlaza();
		System.out.println("numero plazas disponibles normales: "+transporte.getPlazasLibres()+"; plazas reservadas libres: "+transporte.getPlazasReservadasLibres());

	    try {
	    	System.out.println(transporte.toString());
	    	persona.evaluarPersona(transporte);
	        fail("No ha saltado la excepcion");
	    } catch (ExcepcionNoPlazas e) {
	    	System.out.println("bien, ha saltado la excepcion");
	    	assertTrue( true );
	    }

	}
	
	@Test
	public void testEvaluarPersona00003() {
		//nivel 3 restricciones del 40% de plazasTotales, plazasReservadasTotales del 80%
		//no quedan plazas libres reservadas esencial FALSE
		boolean enfermo=false; 
		int diasContanto=-1;
		boolean sintomas=false;
		boolean pasaporteCovid=true;
		int edad=20;
		boolean esEsencial=false;
		
		transporte.setNivelRestricciones(3);
		transporte.obtenerRestrinciones();
		System.out.println(transporte.toString());
		
		persona=new Persona(enfermo, diasContanto, sintomas, pasaporteCovid, edad, esEsencial);
		
		transporte.llenarPlaza();
		System.out.println("numero plazas disponibles normales: "+transporte.getPlazasLibres()+"; plazas reservadas libres: "+transporte.getPlazasReservadasLibres());

	    try {
	    	System.out.println(transporte.toString());
	    	persona.evaluarPersona(transporte);
	        fail("No ha saltado la excepcion");
	    } catch (ExcepcionNoPlazas e) {
	    	System.out.println("bien, ha saltado la excepcion");
	    	assertTrue( true );
	    }

	}
	
	@Test
	public void testEvaluarPersona00004() {
		//nivel 4 restricciones del 30% de plazasTotales, plazasReservadasTotales del 90%
		//no quedan plazas libres reservadas esencial FALSE
		boolean enfermo=false; 
		int diasContanto=-1;
		boolean sintomas=false;
		boolean pasaporteCovid=true;
		int edad=20;
		boolean esEsencial=false;
		
		transporte.setNivelRestricciones(4);
		transporte.obtenerRestrinciones();
		
		System.out.println(transporte.toString());
		
		transporte.llenarPlaza();
		persona=new Persona(enfermo, diasContanto, sintomas, pasaporteCovid, edad, esEsencial);
		
		System.out.println("numero plazas disponibles normales: "+transporte.getPlazasLibres()+"; plazas reservadas libres: "+transporte.getPlazasReservadasLibres());

	    try {
	    	System.out.println(transporte.toString());
	    	persona.evaluarPersona(transporte);
	        fail("No ha saltado la excepcion");
	    } catch (ExcepcionNoPlazas e) {
	    	System.out.println("bien, ha saltado la excepcion");
	    	assertTrue( true );
	    }
	    
	}

}
