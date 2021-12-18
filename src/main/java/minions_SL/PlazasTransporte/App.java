package minions_SL.PlazasTransporte;


public class App 
{
	public static void main(String[] args) {
		int casos=400;
			
		int nivel=obtenerNivelIA(casos);
		
		
		Transporte coche=new Transporte(nivel, 5);
		coche.obtenerRestrinciones();
		
		Persona juan=new Persona(false, -1, false, true, 20,
				false);
		Persona maria=new Persona(false, -1, false, true, 20,
				false);
		Persona drGru=new Persona(false, -1, false, true, 20,
				true);
		
		if (nivel!=-1) {
			try {
				juan.evaluarPersona(coche);
				drGru.evaluarPersona(coche);
				maria.evaluarPersona(coche);
			} catch (ExcepcionNoPlazas e) {
				e.printStackTrace();
			}

		}
		
	} 
	
    public static int obtenerNivelIA(int n) {
    	int nivel=-1;
    	if (n<0)
    			return -1;
    	if (n<100)
    		nivel= 0;
        else if (n<=200)
        	nivel=1;
        else if (n<=300)
        	nivel=2;
        else if (n<=500)
        	nivel=3;
        else
        	nivel=4;

    	return nivel;
    }
    
    /*
    public static double evaluarPersona(int nivel, Persona persona, Transporte transporte) {
    	if (persona.enfermo==true || persona.sistomas==true || (persona.diasContanto<=10 && persona.diasContanto>=0) ) {
    		System.out.println("no puede viajar");
    		return -1;
    	}
    	if (persona.pasCovid==true && transporte.getPlazasTotales()>0) {
    		if (nivel==4 && persona.edad>65) {
        		System.out.println("no puede viajar");
        		return -1;
    		}
    		if (nivel<2) {
    			transporte.llenarPlaza();
    			if (persona.edad<23)
    				persona.descuento=transporte.getDescuentoJoven();
    			if (persona.edad>65)
    				persona.descuento=transporte.getDescuentoViejo();
    		}else {
    			if (persona.esEsencial==true && transporte.getPlazasReservadasLibres()>0) {
    				transporte.llenarPlazaReservada();
    			}else if(transporte.getPlazasLibres()>transporte.getPlazasReservadasLibres()){
    				transporte.llenarPlaza();
    			}else {
    	    		System.out.println("no quedan plazas de su tipo");
    	    		return -1;
    			}
    			if (persona.edad<23)
    				persona.descuento=transporte.getDescuentoJoven();
    			if (persona.edad>65)
    				persona.descuento=transporte.getDescuentoViejo();
    		}

    	}else {
    		System.out.println("no puede viajar");
    		return -1;
    	}
		return persona.descuento;
    	
    }*/

    /*
	private static void obtenerRestrinciones(int nivel, Transporte transporte) {
		switch (nivel) {
			case 0:
				transporte.descuentoJoven=0.40;
				transporte.descuentoViejo=0.20;
				break;
			case 1:
				transporte.plazasTotales*=0.8;
				transporte.plazasLibres=transporte.plazasTotales;
				transporte.descuentoJoven=0.70;
				transporte.descuentoViejo=0.50;
				break;
			case 2:
				transporte.plazasTotales*=0.6;
				transporte.plazasLibres=transporte.plazasTotales;
				transporte.plazasReservadasTotales=(int) (transporte.plazasTotales*0.6);
				transporte.plazasReservadasLibres=transporte.plazasReservadasTotales;
				transporte.descuentoViejo=1.20;
				break;
			case 3:
				transporte.plazasTotales*=0.4;
				transporte.plazasLibres=transporte.plazasTotales;
				transporte.plazasReservadasTotales=(int) (transporte.plazasTotales*0.8);
				transporte.plazasReservadasLibres=transporte.plazasReservadasTotales;
				transporte.descuentoJoven=1.20;
				transporte.descuentoViejo=1.50;
				break;
			case 4:
				transporte.plazasTotales*=0.3;
				transporte.plazasLibres=transporte.plazasTotales;
				transporte.plazasReservadasTotales=(int) (transporte.plazasTotales*0.9);
				transporte.plazasReservadasLibres=transporte.plazasReservadasTotales;
				transporte.descuentoJoven=1.50;
				transporte.descuentoViejo=-1;
				break;
			default:
				break;
		}
	}*/
}
