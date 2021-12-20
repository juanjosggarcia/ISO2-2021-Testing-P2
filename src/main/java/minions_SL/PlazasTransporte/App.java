package minions_SL.PlazasTransporte;


public class App 
{
	public static void main(String[] args) {
		
		Transporte coche;
		try {
			int numCasos=Integer.parseInt(args[1]);
			int plazasTotales=Integer.parseInt(args[2]);
			coche = new Transporte(numCasos, plazasTotales);
			coche.obtenerRestrinciones();
			Persona juan=new Persona(false, -1, false, true, 20, false);
			System.out.println(coche.toString());
			System.out.println(juan.toString());
			System.out.println("Con estas caracteristicas el precio para la persona es: precio*"+juan.evaluarPersona(coche));
		} catch (ExcepcionNivelNoValido e1) {
			e1.toString();
		} catch (ExcepcionNoPlazas e) {
			e.toString();
		}
		
	}

}
