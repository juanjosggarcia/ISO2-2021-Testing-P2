package minions_SL.PlazasTransporte;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PersonaTest {
	
	private static Transporte transporte;
	private static Persona persona;

	@BeforeClass
	public static void setUpBeforeClass() {
		System.out.println("Antes de todas las pruebas de PersonaTest");
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
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//	EVALUANDO --> (this.enfermo==true || this.sintomas==true || (this.diasContanto<=10 && this.diasContanto>=0) )    //
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Test
	public void testEvaluarPersona0() throws ExcepcionNoPlazas {
		//nivel 0
		//NO PUEDE VIAJAR, enfermo=TRUE
		boolean enfermo=true; 
		int diasContanto=-1;
		boolean sintomas=false;
		boolean pasaporteCovid=true;
		int edad=20;
		boolean esEsencial=false;
		
		persona=new Persona(enfermo, diasContanto, sintomas, pasaporteCovid, edad, esEsencial);
		
		double esperado=-1;	
    	double epsilon=0.001;		
    	double actual=persona.evaluarPersona(transporte);
	    assertEquals(esperado, actual, epsilon);
	}
	
	@Test
	public void testEvaluarPersona1() throws ExcepcionNoPlazas {
		//nivel 0
		//NO PUEDE VIAJAR, sintomas=TRUE
		boolean enfermo=false; 
		int diasContanto=-1;
		boolean sintomas=true;
		boolean pasaporteCovid=true;
		int edad=20;
		boolean esEsencial=false;
		
		persona=new Persona(enfermo, diasContanto, sintomas, pasaporteCovid, edad, esEsencial);
		
		double esperado=-1;	
    	double epsilon=0.001;		
	    double actual=persona.evaluarPersona(transporte);
	    assertEquals(esperado, actual, epsilon);
	}
	
	@Test
	public void testEvaluarPersona2() throws ExcepcionNoPlazas {
		//nivel 0
		//NO PUEDE VIAJAR, 0<=diasContanto<=10
		boolean enfermo=false; 
		int diasContanto=5;
		boolean sintomas=false;
		boolean pasaporteCovid=true;
		int edad=20;
		boolean esEsencial=false;
		
		persona=new Persona(enfermo, diasContanto, sintomas, pasaporteCovid, edad, esEsencial);
		
		double esperado=-1;	
    	double epsilon=0.001;		
	    double actual=persona.evaluarPersona(transporte);
	    assertEquals(esperado, actual, epsilon);
	}
	
	@Test
	public void testEvaluarPersona3() throws ExcepcionNoPlazas {
		//nivel 0
		//SI PUEDE VIAJAR, diasContanto<0
		boolean enfermo=false; 
		int diasContanto=-5;
		boolean sintomas=false;
		boolean pasaporteCovid=true;
		int edad=20;
		boolean esEsencial=false;
		
		persona=new Persona(enfermo, diasContanto, sintomas, pasaporteCovid, edad, esEsencial);
		
		double noEsperado=-1;	
    	double epsilon=0.001;		
	    double actual=persona.evaluarPersona(transporte);
	    assertNotEquals(noEsperado, actual, epsilon);
	}
	
	@Test
	public void testEvaluarPersona4() throws ExcepcionNoPlazas {
		//nivel 0
		//SI PUEDE VIAJAR, diasContanto>10
		boolean enfermo=false; 
		int diasContanto=15;
		boolean sintomas=false;
		boolean pasaporteCovid=true;
		int edad=20;
		boolean esEsencial=false;
		
		persona=new Persona(enfermo, diasContanto, sintomas, pasaporteCovid, edad, esEsencial);
		
		double noEsperado=-1;	
    	double epsilon=0.001;		    	
	    double actual=persona.evaluarPersona(transporte);
	    assertNotEquals(noEsperado, actual, epsilon);
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//	EVALUANDO --> (this.pasaporteCovid==true && transporte.getPlazasLibres()>0)                                      //
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@Test(expected = ExcepcionNoPlazas.class)
	public void testEvaluarPersona00() throws ExcepcionNoPlazas {
		//nivel 0
		//NO PUEDE VIAJAR, pasaporteCovid==FALSE
		boolean enfermo=false; 
		int diasContanto=-1;
		boolean sintomas=false;
		boolean pasaporteCovid=false;
		int edad=20;
		boolean esEsencial=false;
		
		persona=new Persona(enfermo, diasContanto, sintomas, pasaporteCovid, edad, esEsencial);

	    persona.evaluarPersona(transporte);

	}
	
	@Test(expected = ExcepcionNoPlazas.class)
	public void testEvaluarPersona01() throws ExcepcionNoPlazas {
		//nivel 0
		//NO PUEDE VIAJAR, plazasLibres<=0
		boolean enfermo=false; 
		int diasContanto=-1;
		boolean sintomas=false;
		boolean pasaporteCovid=true;
		int edad=20;
		boolean esEsencial=false;	
		
		persona=new Persona(enfermo, diasContanto, sintomas, pasaporteCovid, edad, esEsencial);		

		while(transporte.getPlazasLibres()>0)
			transporte.llenarPlaza();

	    persona.evaluarPersona(transporte);

	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//	EVALUANDO --> (transporte.getNivelRestricciones()==4 && this.edad>65)                                            //
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void testEvaluarPersona001() throws ExcepcionNoPlazas, ExcepcionNivelNoValido {
		//nivel 4 restricciones del 30% de plazasTotales, plazasReservadasTotales del 90%
		//SI PUEDE VIAJAR, pasaporteCovid==true && plazasLibres>0
		//NO PUEDE VIAJAR, nivelRestricciones=4 && edad>65
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
    	double actual=persona.evaluarPersona(transporte);
	    assertEquals(esperado, actual, epsilon);

	}
	
	@Test
	public void testEvaluarPersona002() throws ExcepcionNoPlazas, ExcepcionNivelNoValido {
		//nivel 0
		//SI PUEDE VIAJAR, pasaporteCovid==true && plazasLibres>0
		//SI PUEDE VIAJAR, nivelRestricciones!=4 && edad>65
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
	    double actual=persona.evaluarPersona(transporte);
	    assertEquals(esperado, actual, epsilon);

	}
	
	@Test
	public void testEvaluarPersona003() throws ExcepcionNoPlazas, ExcepcionNivelNoValido {
		//nivel 4 restricciones del 30% de plazasTotales, plazasReservadasTotales del 90%
		//SI PUEDE VIAJAR, pasaporteCovid==true && plazasLibres>0
		//SI PUEDE VIAJAR, nivelRestricciones=4 && edad<65	
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
    	double actual=persona.evaluarPersona(transporte);
	    assertEquals(esperado, actual, epsilon);

	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//	EVALUANDO --> (transporte.getNivelRestricciones()<2)                                                             //
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void testEvaluarPersona0000() throws ExcepcionNoPlazas {
		//nivel 0
		//SI PUEDE VIAJAR, nivelRestricciones<2
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
    	double actual=persona.evaluarPersona(transporte);
	    assertEquals(esperado, actual, epsilon);

	}
	
	@Test
	public void testEvaluarPersona0002() throws ExcepcionNoPlazas, ExcepcionNivelNoValido {
		//nivel 1 restricciones del 80% de plazasTotales
		//SI PUEDE VIAJAR, nivelRestricciones<2
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
    	double actual=persona.evaluarPersona(transporte);
	    assertEquals(esperado, actual, epsilon);

	}
	
	@Test
	public void testEvaluarPersona0003() throws ExcepcionNoPlazas, ExcepcionNivelNoValido {
		//nivel 1 restricciones del 80% de plazasTotales
		//SI PUEDE VIAJAR, nivelRestricciones<2
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
    	double actual=persona.evaluarPersona(transporte);
	    assertEquals(esperado, actual, epsilon);
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//	EVALUANDO --> (this.esEsencial==true && transporte.getPlazasReservadasLibres()>0)                                //
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void testEvaluarPersona00000() throws ExcepcionNoPlazas, ExcepcionNivelNoValido {
		//nivel 2 restricciones del 60% de plazasTotales, plazasReservadasTotales del 60%
		//SI PUEDE VIAJAR, esEsencial=TRUE && plazasReservadasLibres>0
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
    	double actual=persona.evaluarPersona(transporte);
	    assertEquals(esperado, actual, epsilon);

	}
	
	@Test(expected = ExcepcionNoPlazas.class)
	public void testEvaluarPersona00001() throws ExcepcionNoPlazas, ExcepcionNivelNoValido {
		//nivel 2 restricciones del 60% de plazasTotales, plazasReservadasTotales del 60%
		//NO PUEDE VIAJAR, esEsencial=TRUE && plazasReservadasLibres<=0
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
		
		while (transporte.getPlazasReservadasLibres()>0)
			transporte.llenarPlazaReservada();

	    persona.evaluarPersona(transporte);

	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//	(this.esEsencial==false && transporte.getPlazasLibres()>transporte.getPlazasReservadasLibres() )                 //
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Test(expected = ExcepcionNoPlazas.class)
	public void testEvaluarPersona00002() throws ExcepcionNoPlazas, ExcepcionNivelNoValido {
		//nivel 2 restricciones del 60% de plazasTotales, plazasReservadasTotales del 60%
		//NO PUEDE VIAJAR, esEsencial=FALSE && plazasLibres<=plazasReservadasLibres (no quedan plazas normales libres)
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

	    persona.evaluarPersona(transporte);

	}
	@Test
	public void testEvaluarPersona00003() throws ExcepcionNoPlazas, ExcepcionNivelNoValido {
		//nivel 2 restricciones del 60% de plazasTotales, plazasReservadasTotales del 60%
		//SI PUEDE VIAJAR, esEsencial=FALSE && plazasLibres>plazasReservadasLibres (quedan plazas normales libres)
		//edad>65
		boolean enfermo=false; 
		int diasContanto=-1;
		boolean sintomas=false;
		boolean pasaporteCovid=true;
		int edad=70;
		boolean esEsencial=false;
		
		int nivelRestricciones = 2;
		transporte.setNivelRestricciones(nivelRestricciones);
		transporte.obtenerRestrinciones();
		
		persona=new Persona(enfermo, diasContanto, sintomas, pasaporteCovid, edad, esEsencial);

		double esperado=1.2;	
    	double epsilon=0.001;		
    	double actual=persona.evaluarPersona(transporte);
	    assertEquals(esperado, actual, epsilon);

	}

}
