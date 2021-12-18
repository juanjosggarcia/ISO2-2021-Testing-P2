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
			transporte.obtenerRestrinciones();
			//System.out.println(transporte.toString());
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
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//	EVALUANDO --> (this.enfermo==true || this.sintomas==true || (this.diasContanto<=10 && this.diasContanto>=0) )    //
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Test
	public void testEvaluarPersona0() {
		//no puede viajar enfermo=TRUE
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
	    	//System.out.println(transporte.toString());
	    	
	    	double actual=persona.evaluarPersona(transporte);;
	    	assertEquals(esperado, actual, epsilon);
	    } catch (ExcepcionNoPlazas e) {
	    	fail("Error, ha saltado la excepcion");
	    }
	}
	
	@Test
	public void testEvaluarPersona1() {
		//no puede viajar sintomas=TRUE
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
	    	//System.out.println(transporte.toString());
	    	
	    	double actual=persona.evaluarPersona(transporte);;
	    	assertEquals(esperado, actual, epsilon);
	    } catch (ExcepcionNoPlazas e) {
	    	fail("Error, ha saltado la excepcion");
	    }
	}
	
	@Test
	public void testEvaluarPersona2() {
		//no puede viajar 0<=diasContanto<=10
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
	    	//System.out.println(transporte.toString());
	    	
	    	double actual=persona.evaluarPersona(transporte);;
	    	assertEquals(esperado, actual, epsilon);
	    } catch (ExcepcionNoPlazas e) {
	    	fail("Error, ha saltado la excepcion");
	    }
	}
	
	@Test
	public void testEvaluarPersona3() {
		//SI puede viajar diasContanto>10
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
	    	//System.out.println(transporte.toString());
	    	
	    	double actual=persona.evaluarPersona(transporte);;
	    	assertNotEquals(noEsperado, actual, epsilon);
	    } catch (ExcepcionNoPlazas e) {
	    	fail("Error, ha saltado la excepcion");
	    }
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//	EVALUANDO --> (this.pasaporteCovid==true && transporte.getPlazasLibres()>0)                                      //
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void testEvaluarPersona00() {
		//no puede viajar pasaporteCovid==FALSE
		boolean enfermo=false; 
		int diasContanto=-1;
		boolean sintomas=false;
		boolean pasaporteCovid=false;
		int edad=20;
		boolean esEsencial=false;
		
		persona=new Persona(enfermo, diasContanto, sintomas, pasaporteCovid, edad, esEsencial);

	    try {
	    	//System.out.println(transporte.toString());
	    	
	    	persona.evaluarPersona(transporte);
	        fail("No ha saltado la excepcion");
	    } catch (ExcepcionNoPlazas e) {
	    	assertTrue( true );
	    }

	}
	
	@Test
	public void testEvaluarPersona01() {
		//no puede viajar plazasLibres<=0
		boolean enfermo=false; 
		int diasContanto=-1;
		boolean sintomas=false;
		boolean pasaporteCovid=true;
		int edad=20;
		boolean esEsencial=false;	
		
		persona=new Persona(enfermo, diasContanto, sintomas, pasaporteCovid, edad, esEsencial);		

		while(transporte.getPlazasLibres()>0)
			transporte.llenarPlaza();

	    try {
	    	//System.out.println(transporte.toString());
	    	persona.evaluarPersona(transporte);
	        fail("No ha saltado la excepcion");
	    } catch (ExcepcionNoPlazas e) {
	    	assertTrue( true );
	    }

	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//	EVALUANDO --> (transporte.getNivelRestricciones()==4 && this.edad>65)                                            //
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void testEvaluarPersona001() {
		//nivel 4 restricciones del 30% de plazasTotales, plazasReservadasTotales del 90%
		//SI puede viajar pasaporteCovid==true && plazasLibres>0
		//no puede viajar nivelRestricciones=4 && edad>65
		boolean enfermo=false; 
		int diasContanto=-1;
		boolean sintomas=false;
		boolean pasaporteCovid=true;
		int edad=70;
		boolean esEsencial=false;
		
		int nivelRestricciones = 4;
		transporte.setNivelRestricciones(nivelRestricciones);
		transporte.obtenerRestrinciones();
		
		persona=new Persona(enfermo, diasContanto, sintomas, pasaporteCovid, edad, esEsencial);
		
		double esperado=-1;	
    	double epsilon=0.001;		

	    try {
	    	//System.out.println(transporte.toString());
	    	
	    	double actual=persona.evaluarPersona(transporte);;
	    	assertEquals(esperado, actual, epsilon);
	    } catch (ExcepcionNoPlazas e) {
	    	fail("Error, ha saltado la excepcion");
	    }

	}
	
	@Test
	public void testEvaluarPersona002() {
		//SI puede viajar pasaporteCovid==true && plazasLibres>0
		//SI puede viajar nivelRestricciones!=4 && edad>65
		boolean enfermo=false; 
		int diasContanto=-1;
		boolean sintomas=false;
		boolean pasaporteCovid=true;
		int edad=70;
		boolean esEsencial=false;
		
		int nivelRestricciones = 0;
		transporte.setNivelRestricciones(nivelRestricciones);
		transporte.obtenerRestrinciones();
		
		persona=new Persona(enfermo, diasContanto, sintomas, pasaporteCovid, edad, esEsencial);
		
		double esperado=0.20;	
    	double epsilon=0.001;		

	    try {
	    	//System.out.println(transporte.toString());
	    	
	    	double actual=persona.evaluarPersona(transporte);;
	    	assertEquals(esperado, actual, epsilon);
	    } catch (ExcepcionNoPlazas e) {
	    	fail("Error, ha saltado la excepcion");
	    }

	}
	
	@Test
	public void testEvaluarPersona003() {
		//nivel 4 restricciones del 30% de plazasTotales, plazasReservadasTotales del 90%
		//SI puede viajar pasaporteCovid==true && plazasLibres>0
		//SI puede viajar nivelRestricciones=4 && edad<65	
		boolean enfermo=false; 
		int diasContanto=-1;
		boolean sintomas=false;
		boolean pasaporteCovid=true;
		int edad=20;
		boolean esEsencial=false;
		
		int nivelRestricciones = 4;
		transporte.setNivelRestricciones(nivelRestricciones);
		transporte.obtenerRestrinciones();
		
		persona=new Persona(enfermo, diasContanto, sintomas, pasaporteCovid, edad, esEsencial);
		
		double esperado=1.50;	
    	double epsilon=0.001;		

	    try {
	    	//System.out.println(transporte.toString());
	    	
	    	double actual=persona.evaluarPersona(transporte);;
	    	assertEquals(esperado, actual, epsilon);
	    } catch (ExcepcionNoPlazas e) {
	    	fail("Error, ha saltado la excepcion");
	    }

	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//	EVALUANDO --> (transporte.getNivelRestricciones()<2)                                                             //
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void testEvaluarPersona0000() {
		//SI puede viajar nivelRestricciones<2
		//edad<23 descuentoJoven		
		boolean enfermo=false; 
		int diasContanto=-1;
		boolean sintomas=false;
		boolean pasaporteCovid=true;
		int edad=20;
		boolean esEsencial=false;
		
		persona=new Persona(enfermo, diasContanto, sintomas, pasaporteCovid, edad, esEsencial);

		double esperado=0.4;	
    	double epsilon=0.001;		

	    try {
	    	//System.out.println(transporte.toString());
	    	
	    	double actual=persona.evaluarPersona(transporte);;
	    	assertEquals(esperado, actual, epsilon);
	    } catch (ExcepcionNoPlazas e) {
	    	fail("Error, ha saltado la excepcion");
	    }

	}

	/*
	@Test
	public void testEvaluarPersona0001() {
		//SI puede viajar nivelRestricciones<2
		//edad>65 descuentoViejo		
		boolean enfermo=false; 
		int diasContanto=-1;
		boolean sintomas=false;
		boolean pasaporteCovid=true;
		int edad=70;
		boolean esEsencial=false;
		
		persona=new Persona(enfermo, diasContanto, sintomas, pasaporteCovid, edad, esEsencial);

		double esperado=0.2;	
    	double epsilon=0.001;		

	    try {
	    	//System.out.println(transporte.toString());
	    	
	    	double actual=persona.evaluarPersona(transporte);;
	    	assertEquals(esperado, actual, epsilon);
	    } catch (ExcepcionNoPlazas e) {
	    	fail("Error, ha saltado la excepcion");
	    }

	}*/
	
	@Test
	public void testEvaluarPersona0002() {
		//nivel 1 restricciones del 80% de plazasTotales
		//SI puede viajar nivelRestricciones<2
		//edad<23 descuentoJoven
		boolean enfermo=false; 
		int diasContanto=-1;
		boolean sintomas=false;
		boolean pasaporteCovid=true;
		int edad=20;
		boolean esEsencial=false;
		
		int nivelRestricciones = 1;
		transporte.setNivelRestricciones(nivelRestricciones);
		transporte.obtenerRestrinciones();
		
		persona=new Persona(enfermo, diasContanto, sintomas, pasaporteCovid, edad, esEsencial);

		double esperado=0.7;	
    	double epsilon=0.001;		

	    try {
	    	//System.out.println(transporte.toString());
	    	
	    	double actual=persona.evaluarPersona(transporte);;
	    	assertEquals(esperado, actual, epsilon);
	    } catch (ExcepcionNoPlazas e) {
	    	fail("Error, ha saltado la excepcion");
	    }

	}
	
	@Test
	public void testEvaluarPersona0003() {
		//nivel 1 restricciones del 80% de plazasTotales
		//SI puede viajar nivelRestricciones<2
		//edad>65 descuentoViejo		
		boolean enfermo=false; 
		int diasContanto=-1;
		boolean sintomas=false;
		boolean pasaporteCovid=true;
		int edad=70;
		boolean esEsencial=false;		
		
		int nivelRestricciones = 1;
		transporte.setNivelRestricciones(nivelRestricciones);
		transporte.obtenerRestrinciones();
		
		persona=new Persona(enfermo, diasContanto, sintomas, pasaporteCovid, edad, esEsencial);

		double esperado=0.5;	
    	double epsilon=0.001;		

	    try {
	    	//System.out.println(transporte.toString());
	    	
	    	double actual=persona.evaluarPersona(transporte);;
	    	assertEquals(esperado, actual, epsilon);
	    } catch (ExcepcionNoPlazas e) {
	    	fail("Error, ha saltado la excepcion");
	    }

	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//	EVALUANDO --> (this.esEsencial==true && transporte.getPlazasReservadasLibres()>0)                                //
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void testEvaluarPersona00000() {
		//nivel 2 restricciones del 60% de plazasTotales, plazasReservadasTotales del 60%
		//esEsencial=TRUE && plazasReservadasLibres>0
		//edad<23
		boolean enfermo=false; 
		int diasContanto=-1;
		boolean sintomas=false;
		boolean pasaporteCovid=true;
		int edad=20;
		boolean esEsencial=true;
		
		int nivelRestricciones = 2;
		transporte.setNivelRestricciones(nivelRestricciones);
		transporte.obtenerRestrinciones();
		
		persona=new Persona(enfermo, diasContanto, sintomas, pasaporteCovid, edad, esEsencial);

		double esperado=1.00;	
    	double epsilon=0.001;		

	    try {
	    	//System.out.println(transporte.toString());
	    	
	    	double actual=persona.evaluarPersona(transporte);;
	    	assertEquals(esperado, actual, epsilon);
	    } catch (ExcepcionNoPlazas e) {
	    	fail("Error, ha saltado la excepcion");
	    }

	}
	
	@Test
	public void testEvaluarPersona00001() {
		//nivel 2 restricciones del 60% de plazasTotales, plazasReservadasTotales del 60%
		//esEsencial=TRUE && plazasReservadasLibres<=0
		boolean enfermo=false; 
		int diasContanto=-1;
		boolean sintomas=false;
		boolean pasaporteCovid=true;
		int edad=20;
		boolean esEsencial=true;
		
		int nivelRestricciones = 2;
		transporte.setNivelRestricciones(nivelRestricciones);
		transporte.obtenerRestrinciones();

		System.out.println("ESTE ES");
		System.out.println(transporte.toString());
		
		persona=new Persona(enfermo, diasContanto, sintomas, pasaporteCovid, edad, esEsencial);
		
		while (transporte.getPlazasReservadasLibres()>0)
			transporte.llenarPlazaReservada();

	    try {
	    	System.out.println(transporte.toString());
	    	persona.evaluarPersona(transporte);
	        fail("No ha saltado la excepcion");
	    } catch (ExcepcionNoPlazas e) {
	    	assertTrue( true );
	    }

	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//	(this.esEsencial==false && transporte.getPlazasLibres()>transporte.getPlazasReservadasLibres() )                 //
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Test
	public void testEvaluarPersona00002() {
		//nivel 2 restricciones del 60% de plazasTotales, plazasReservadasTotales del 60%
		//esEsencial=FALSE && plazasReservadasLibres>0
		//esEsencial=FALSE && plazasLibres<=plazasReservadasLibres
		boolean enfermo=false; 
		int diasContanto=-1;
		boolean sintomas=false;
		boolean pasaporteCovid=true;
		int edad=20;
		boolean esEsencial=false;
		
		int nivelRestricciones = 2;
		transporte.setNivelRestricciones(nivelRestricciones);
		transporte.obtenerRestrinciones();
		
		persona=new Persona(enfermo, diasContanto, sintomas, pasaporteCovid, edad, esEsencial);
		
		while (transporte.getPlazasLibres()>transporte.getPlazasReservadasLibres())
			transporte.llenarPlaza();

	    try {
	    	System.out.println(transporte.toString());
	    	persona.evaluarPersona(transporte);
	        fail("No ha saltado la excepcion");
	    } catch (ExcepcionNoPlazas e) {
	    	assertTrue( true );
	    }

	}
	@Test
	public void testEvaluarPersona00003() {
		//nivel 2 restricciones del 60% de plazasTotales, plazasReservadasTotales del 60%
		//esEsencial=FALSE && plazasLibres>plazasReservadasLibres
		//edad>65
		boolean enfermo=false; 
		int diasContanto=-1;
		boolean sintomas=false;
		boolean pasaporteCovid=true;
		int edad=70;
		boolean esEsencial=true;
		
		int nivelRestricciones = 2;
		transporte.setNivelRestricciones(nivelRestricciones);
		transporte.obtenerRestrinciones();
		
		persona=new Persona(enfermo, diasContanto, sintomas, pasaporteCovid, edad, esEsencial);

		double esperado=1.2;	
    	double epsilon=0.001;		

	    try {
	    	//System.out.println(transporte.toString());
	    	
	    	double actual=persona.evaluarPersona(transporte);;
	    	assertEquals(esperado, actual, epsilon);
	    } catch (ExcepcionNoPlazas e) {
	    	fail("Error, ha saltado la excepcion");
	    }

	}

	
	
	
	
	
	
	/*
	@Test
	public void testEvaluarPersona0005() {
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
	public void testEvaluarPersona0006() {
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
	public void testEvaluarPersona0007() {
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

		System.out.println(transporte.toString());
		
		persona=new Persona(enfermo, diasContanto, sintomas, pasaporteCovid, edad, esEsencial);	
		
		System.out.println("numero plazas disponibles normales: "+transporte.getPlazasLibres()+"; plazas reservadas libres: "+transporte.getPlazasReservadasLibres());

	    try {
	    	System.out.println(transporte.toString());
	    	persona.evaluarPersona(transporte);
	    	assertTrue( true );
	    } catch (ExcepcionNoPlazas e) {
	    	fail("Error, ha saltado la excepcion");
	    	System.out.println("error, ha saltado la excepcion");
	    }
	    
	}
	
	@Test
	public void testEvaluarPersona0008() {
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
		
		
		System.out.println("numero plazas disponibles normales: "+transporte.getPlazasLibres()+"; plazas reservadas libres: "+transporte.getPlazasReservadasLibres());

	    try {
	    	System.out.println(transporte.toString());
	    	persona.evaluarPersona(transporte);
	    	assertTrue( true );
	    } catch (ExcepcionNoPlazas e) {
	    	fail("Error, ha saltado la excepcion");
	    	System.out.println("error, ha saltado la excepcion");
	    }
	    
	}
	
	@Test
	public void testEvaluarPersona0000002() {
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
	public void testEvaluarPersona000003() {
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
	
	@Test
	public void testEvaluarPersona000002() {
		//nivel 2 restricciones del 60% de plazasTotales, plazasReservadasTotales del 60%
		//no quedan plazas libres reservadas esencial FALSE
		//edad > 65
		boolean enfermo=false; 
		int diasContanto=-1;
		boolean sintomas=false;
		boolean pasaporteCovid=true;
		int edad=70;
		boolean esEsencial=false;
		
		transporte.setNivelRestricciones(2);
		transporte.obtenerRestrinciones();
		System.out.println(transporte.toString());
		
		persona=new Persona(enfermo, diasContanto, sintomas, pasaporteCovid, edad, esEsencial);
		
		System.out.println("numero plazas disponibles normales: "+transporte.getPlazasLibres()+"; plazas reservadas libres: "+transporte.getPlazasReservadasLibres());

	    try {
	    	System.out.println(transporte.toString());
	    	persona.evaluarPersona(transporte);
	    	assertTrue( true );
	    } catch (ExcepcionNoPlazas e) {
	    	fail("Error, ha saltado la excepcion");
	    	System.out.println("error, ha saltado la excepcion");
	    }

	}*/

}
