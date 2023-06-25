/**
 * @author Nathalia Ferrari
 */
import java.util.Random;
public class Jogador {
    private static int seed = 2;
    private int fichas = 200;
    private boolean vivo = true;
    private Carta cartas[] = new Carta[5];
    private String name;
    private static Random random = new Random(seed);


    public Jogador(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFichas() {
        return fichas;
    }

    public void setFichas(int fichas) {
        this.fichas = fichas;
    }

    public boolean isVivo() {
        return vivo;
    }

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }

    public Carta[] getCartas() {
        return cartas;
    }

    public void setCartas(Carta[] cartas) {
        this.cartas = cartas;
    }

    public void imprimeInfoJogador(){
        imprimeCartas();
        System.out.println("Fichas: "+this.fichas);
        System.out.println("Não desistiu: "+this.vivo);
    }

    public void imprimeCartas(){
        for(int i = 0; i < cartas.length; i++){
            System.out.print((i + 1) + ". " + this.cartas[i].getNumero()+" "+this.cartas[i].getNaipe());
            if(i < cartas.length - 1){
                System.out.print(", ");
            }
        }
        System.out.println();
    }

    public int aposta(int ultimaAposta){
        if(!this.vivo){
            return 0;
        }
        int opcao = this.random.nextInt(3);
        int aposta = 0;

        if (opcao == 0){
            //iguala aposta ou coloca todas as fichas se não tem suficiente
            aposta = igualaAposta(ultimaAposta);
        } else if (opcao == 1){
            //aumenta aposta ou coloca todas as fichas se não tem suficiente
            aposta = aumentaAposta(ultimaAposta);
        } else{
            desisteMao();
        }
        return aposta;
    }


    protected int igualaAposta(int ultimaAposta) {
        if (this.fichas >= ultimaAposta) {
            System.out.println(" igualou aposta de valor " + ultimaAposta);
            this.fichas -= ultimaAposta;
            return ultimaAposta;
        } else {
            return apostaMenorValor();
        }
    }

    private int aumentaAposta(int ultimaAposta) {
        //aumenta aposta ou coloca todas as fichas se não tem suficiente
        int aposta;
        if (this.fichas > ultimaAposta) {
            aposta = this.random.nextInt((ultimaAposta + 1), this.fichas);
            System.out.println(" aumentou aposta para " + aposta);
            this.fichas -= aposta;
        } else {
            aposta = apostaMenorValor();
        }
        return aposta;
    }

    protected int apostaMenorValor(){
        System.out.println(" não possui fichas para igualar e jogou todas as suas fichas");
        int aposta = this.fichas;
        this.setFichas(0);
        return aposta;
    }

    protected void desisteMao(){
        System.out.println(" desistiu da mão.");
        setVivo(false);
    }

    public int removeCartas() {
        int quantCartasTrocadas = 0;
        for (int j = 0; j < 5; j++) {
            int random = this.random.nextInt() % 100;
            if (random >= 0) {
                quantCartasTrocadas++;
                this.cartas[j] = null;
            }
        }
        return quantCartasTrocadas;
    }

    public void recebeCartas(Carta[] cartasNovas){
        int i = 0;
        for (int j = 0; j < 5; j++) {
            if (this.cartas[j] == null) {
                this.cartas[j] = cartasNovas[i];
                i++;
            }
        }
    }

    public void incrementa(int valorIncremento){
        this.setFichas(valorIncremento + this.getFichas());
    }
}