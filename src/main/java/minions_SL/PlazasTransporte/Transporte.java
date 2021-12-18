package minions_SL.PlazasTransporte;

public class Transporte {
	
	private int nivelRestricciones;
	private int plazasTotales;
	private int plazasLibres;
	private int plazasReservadasTotales;
	private int plazasReservadasLibres;
	private double descuentoJoven;
	private double descuentoViejo;
	
	
	public Transporte(int nivelRestricciones, int plazasTotales) {
		super();
		this.nivelRestricciones = nivelRestricciones;
		this.plazasTotales = plazasTotales;
		this.plazasLibres = plazasTotales;
		this.plazasReservadasTotales = -1;
		this.plazasReservadasLibres = -1;
		this.descuentoJoven = 1.0;
		this.descuentoViejo = 1.0;
	}

	public int getNivelRestricciones() {
		return nivelRestricciones;
	}

	public void setNivelRestricciones(int nivelRestricciones) {
		this.nivelRestricciones = nivelRestricciones;
	}

	public int getPlazasTotales() {
		return plazasTotales;
	}

	public int getPlazasLibres() {
		return plazasLibres;
	}

	public void setPlazasLibres(int plazasLibres) {
		this.plazasLibres = plazasLibres;
	}

	public int getPlazasReservadasTotales() {
		return plazasReservadasTotales;
	}

	public int getPlazasReservadasLibres() {
		return plazasReservadasLibres;
	}

	public void setPlazasReservadasLibres(int plazasReservadasLibres) {
		this.plazasReservadasLibres = plazasReservadasLibres;
	}

	public double getDescuentoJoven() {
		return descuentoJoven;
	}

	public double getDescuentoViejo() {
		return descuentoViejo;
	}

	public void llenarPlaza() {
		this.plazasLibres-=1;
	}
	
	public void llenarPlazaReservada() {
		this.plazasReservadasLibres-=1;
		this.plazasLibres-=1;
	}
	
	public int obtenerRestrinciones() {
		switch (this.nivelRestricciones) {
			case 0:
				this.descuentoJoven=0.40;
				this.descuentoViejo=0.20;
				break;
			case 1:
				this.plazasTotales*=0.8;
				this.plazasLibres=this.plazasTotales;
				this.descuentoJoven=0.70;
				this.descuentoViejo=0.50;
				break;
			case 2:
				this.plazasTotales*=0.6;
				this.plazasLibres=this.plazasTotales;
				this.plazasReservadasTotales=(int) (this.plazasTotales*0.6);
				this.plazasReservadasLibres=this.plazasReservadasTotales;
				this.descuentoJoven=1.00;
				this.descuentoViejo=1.20;
				break;
			case 3:
				this.plazasTotales*=0.4;
				this.plazasLibres=this.plazasTotales;
				this.plazasReservadasTotales=(int) (this.plazasTotales*0.8);
				this.plazasReservadasLibres=this.plazasReservadasTotales;
				this.descuentoJoven=1.20;
				this.descuentoViejo=1.50;
				break;
			case 4:
				this.plazasTotales*=0.3;
				this.plazasLibres=this.plazasTotales;
				this.plazasReservadasTotales=(int) (this.plazasTotales*0.9);
				this.plazasReservadasLibres=this.plazasReservadasTotales;
				this.descuentoJoven=1.50;
				this.descuentoViejo=-1;
				break;
			default:
				return -1;
		}
		return 0;
	}

	@Override
	public String toString() {
		return "Transporte [nivelRestricciones=" + nivelRestricciones + ", plazasTotales=" + plazasTotales
				+ ", plazasLibres=" + plazasLibres + ", plazasReservadasTotales=" + plazasReservadasTotales
				+ ", plazasReservadasLibres=" + plazasReservadasLibres + ", descuentoJoven=" + descuentoJoven
				+ ", descuentoViejo=" + descuentoViejo + "]";
	}
	
}
