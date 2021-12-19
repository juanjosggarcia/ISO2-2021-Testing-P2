package minions_SL.PlazasTransporte;

@SuppressWarnings("serial")
public class ExcepcionNoPlazas extends Exception{
    public ExcepcionNoPlazas(String txt) {
        System.out.println("No queda ninguna plaza libre"+txt);
    }
}
