package minions_SL.PlazasTransporte;

@SuppressWarnings("serial")
public class ExcepcionNivelNoValido extends Exception{
    public ExcepcionNivelNoValido() {
        System.out.println("El nivel de casos evaluado no es valido");
    }
}
