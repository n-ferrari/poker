/**
 * @author Nathalia Ferrari
 */
import java.util.Random;
public class Baralho
{
    private static int seed = 1;
    private Carta arrayCarta[] = new Carta[52];

    int num = 1;
    public Baralho(){
        for (int i = 0; i < 13; i++){
            arrayCarta[i] = new Carta("Ouro",num);
            num++;
        }
        num = 1;
        for (int i = 13; i < 26; i++){
            arrayCarta[i] = new Carta("Espada",num);
            num++;
        }
        num = 1;
        for (int i = 26; i < 39; i++){
            arrayCarta[i] = new Carta("Copas",num);
            num++;
        }
        num = 1;
        for (int i = 39; i < 52; i++){
            arrayCarta[i] = new Carta("Paus",num);
            num++;
        }
    }
    public void imprimeBaralho(){
        System.out.println("\nBaralho");
        for (int i = 0; i < 52; i++){
            if (arrayCarta[i] != null){
                System.out.println(arrayCarta[i].getNumero()+" "+arrayCarta[i].getNaipe());
            }else{
                System.out.println("null");
            }

        }
    }
    public void embaralha(){
        Random random = new Random(seed);
        Carta cartaAuxiliar;
        for (int i = 0 ; i < 52; i++){
            int pos1 = (random.nextInt(52));
            int pos2 = (random.nextInt(52));
            
            cartaAuxiliar = arrayCarta[pos1];
            arrayCarta[pos1] = arrayCarta[pos2];
            arrayCarta[pos2] = cartaAuxiliar;
        }
    }

    public Carta[] daCartas(int num){
        Carta carta = null;
        Carta[] cartas = new Carta[num];
        int i = 0;
        while(carta == null){
            carta = this.arrayCarta[i];
            if(carta != null){
                for(int j = 0; j < num; j++){
                    this.arrayCarta[i] = null;
                    cartas[j] = carta;
                    i++;
                    carta = this.arrayCarta[i];
                }
                return cartas;
            }
            i++;
        }
        return null;
    }
}
