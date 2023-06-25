/**
 * @author Nathalia Ferrari
 */
public class JogadorTeclado extends Jogador {


    public JogadorTeclado(String name) {
        super(name);
    }

    public int removeCartas() {
        Carta[] novasCartas = super.getCartas();
        imprimeCartas();
        int quantCartasTrocadas = -1;
        while (quantCartasTrocadas < 0 || quantCartasTrocadas > 5) {
            quantCartasTrocadas = Teclado.leInt("Quantas cartas você deseja trocar?");
        }
        if (quantCartasTrocadas > 0){
            System.out.println("Digite quais deseja trocar (número + enter).");
            for (int i = 0; i < quantCartasTrocadas; i++) {
                int cartaNum = 0;
                while (cartaNum < 1 || cartaNum > 5) {
                    cartaNum = Teclado.leInt();
                }
                for (int j = 0; j < super.getCartas().length; j++) {
                    if (cartaNum == (j + 1)) {
                        novasCartas[j] = null;
                        break;
                    }
                }
            }
            super.setCartas(novasCartas);
        }
        return quantCartasTrocadas;
    }

    public void recebeCartas(Carta[] cartasNovas){
        super.recebeCartas(cartasNovas);
        imprimeCartas();
    }

    public void imprimeCartas() {
        System.out.println("Você possui as seguintes cartas: ");
        super.imprimeCartas();
    }

    public int aposta(int ultimaAposta) {
        int opcao = 0;
        int aposta = 0;
        System.out.println("É a sua vez de apostar. Você tem "+super.getFichas()+" fichas.");
        if (super.getFichas() > ultimaAposta) {
            System.out.println("1. Iguala a aposta anterior de "+ultimaAposta);
            System.out.println("2. Aumenta a aposta anterior");
            System.out.println("3. Desiste da mão");
            
            while (opcao !=1 && opcao != 2 && opcao != 3){
                opcao = Teclado.leInt();
            }
            
            if(opcao == 1){
                aposta = super.igualaAposta(ultimaAposta);
            } else if (opcao == 2) {
                aposta = aumentaAposta(ultimaAposta);
            }else{
                super.desisteMao();
            }
        }else if ((super.getFichas() == ultimaAposta)){
            System.out.println("1. Iguala a aposta anterior de "+ultimaAposta);
            System.out.println("2. Desiste da mão");

            while (opcao !=1 && opcao != 2){
                opcao = Teclado.leInt();
            }

            if(opcao == 1){
                aposta = super.igualaAposta(ultimaAposta);
            } else{
                super.desisteMao();
            }
        } else{
            System.out.println("Você não tem fichas suficientes para igualar a aposta.");
            System.out.println("1. Jogar todas as fichas que tem");
            System.out.println("2. Desistir da mão");
            while (opcao != 1 && opcao != 2){
                opcao = Teclado.leInt();
            }
            if(opcao == 1){
                aposta = super.apostaMenorValor();
            }else{
                super.desisteMao();
            }
        }
        return aposta;
    }

    private int aumentaAposta(int ultimaAposta) {
        int aposta = 0;
        while (aposta < (ultimaAposta + 1) || aposta > super.getFichas()){
            aposta = Teclado.leInt("Quantas fichas você aposta?");
            if(aposta > super.getFichas()){
                System.out.println("Você não tem fichas suficientes");
            }
        }
        super.setFichas(super.getFichas() - aposta);
        return aposta;
    }
}


