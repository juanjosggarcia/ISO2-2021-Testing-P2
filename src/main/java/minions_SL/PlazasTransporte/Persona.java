package minions_SL.PlazasTransporte;

public class Persona {
	public boolean enfermo;
	public int diasContanto;
	public boolean sintomas;
	public boolean pasaporteCovid;
	public int edad;
	public boolean esEsencial;
	public double descuento;
	
	public Persona(boolean enfermo, int diasContanto, boolean sistomas, boolean pasCovid, int edad,
			boolean esEsencial) {
		super();
		this.enfermo = enfermo;
		this.diasContanto = diasContanto;
		this.sintomas = sistomas;
		this.pasaporteCovid = pasCovid;
		this.edad = edad;
		this.esEsencial = esEsencial;
		this.descuento = 1.0;
	}

    public double evaluarPersona(Transporte transporte) throws ExcepcionNoPlazas {
    	if (this.enfermo==true || this.sintomas==true || (this.diasContanto<=10 && this.diasContanto>=0) ) {
    		System.out.println("No puede viajar ha estado expuesto");
    		return -1;
    	}
    	if (this.pasaporteCovid==true && transporte.getPlazasLibres()>0) {
    		if (transporte.getNivelRestricciones()==4 && this.edad>65) {
        		System.out.println("Las restricciones actuales no permiten que usted viaje");
        		return -1;
    		}
    		if (transporte.getNivelRestricciones()<2) {
    			transporte.llenarPlaza();
    			if (this.edad<23)
    				this.descuento=transporte.getDescuentoJoven();
    			if (this.edad>65)
    				this.descuento=transporte.getDescuentoViejo();
    		}else {
    			if (this.esEsencial==true && transporte.getPlazasReservadasLibres()>0) {
    				transporte.llenarPlazaReservada();
    			}else if(this.esEsencial==false && transporte.getPlazasLibres()>transporte.getPlazasReservadasLibres()){
    				transporte.llenarPlaza();
    			}else {
    	    		throw new ExcepcionNoPlazas(" o estan reservadas");
    	    		//System.out.println("no quedan plazas de su tipo");
    	    		//return -1;
    			}
    			if (this.edad<23)
    				this.descuento=transporte.getDescuentoJoven();
    			if (this.edad>65)
    				this.descuento=transporte.getDescuentoViejo();
    		}

    	}else {
    		throw new ExcepcionNoPlazas("");
    		//System.out.println("no quedan plazas");
    		//return -1;
    	}
		return this.descuento;
    	
    }

	@Override
	public String toString() {
		return "Persona [enfermo=" + enfermo + ", diasContanto=" + diasContanto + ", sintomas=" + sintomas
				+ ", pasaporteCovid=" + pasaporteCovid + ", edad=" + edad + ", esEsencial=" + esEsencial
				+ ", descuento=" + descuento + "]";
	}
    
    
}
