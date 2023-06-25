/**
 * @author Nathalia Ferrari
 */
public class Carta
{
    private int numero;
    private String naipe;

    public Carta(String naipe,int numero) {
        this.numero = numero;
        this.naipe = naipe;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNaipe() {
        return naipe;
    }

    public void setNaipe(String naipe) {
        this.naipe = naipe;
    }
}
