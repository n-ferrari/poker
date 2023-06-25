/**
 * @author Nathalia Ferrari
 */

public class Jogo {
    private Baralho baralho;
    private Jogador[] jogadores;
    private Pote pote = new Pote();

    public Jogo(int numJogadores) {
        String[] name = {"Ze","Joao","Maria","Fernando"};
        this.jogadores = new Jogador[numJogadores];
        for (int i = 0; i < (jogadores.length - 1); i++) {
            jogadores[i] = new Jogador(name[i]);
        }
        jogadores[numJogadores - 1] = new JogadorTeclado("Você");
    }

    public void comeca() {
        System.out.println("Início do jogo");
        while(!fimDeJogo()){
            this.baralho = new Baralho();
            baralho.embaralha();
            //baralho.imprimeBaralho();

            distribuiCartas(5);
            apostasIniciais();
            //imprimeInfoJogo();
            trocaCarta();

            rodadaAposta();

            //imprimeInfoJogo();
            resultado();
            //imprimeInfoJogo();
            atualizaJogadores();
            //imprimeInfoJogo();

        }

    }

    private void distribuiCartas(int numCartas) {
        System.out.println("\nEtapa 1. Distribuição de cartas.");
        for (int i = 0; i < (jogadores.length); i++) {
            jogadores[i].setCartas(baralho.daCartas(numCartas));
        }
        System.out.println(numCartas + " cartas foram distribuídas para cada jogador.");
    }

    private void imprimeInfoJogadores() {
        for (int i = 0; i < jogadores.length; i++) {
            System.out.print("\n"+jogadores[i].getName());
            if (i == 0) {
                System.out.println(" (SM)");
            } else if (i == 1 && jogadores.length > 2) {
                System.out.println(" (BB)");
            } else if (i == (jogadores.length - 1)) {
                System.out.println(" (Dealer) ");
            } else {
                System.out.println();
            }
            jogadores[i].imprimeInfoJogador();
        }
    }

    private void apostasIniciais() {
        System.out.println("\nEtapa 2: Aposta iniciais.");
        int apostaSB;
        int apostaBB;
        if(jogadores[0].getFichas() < 5){
            apostaSB = jogadores[0].getFichas();
        }else{
            apostaSB = 5;
        }
        jogadores[0].setFichas(jogadores[0].getFichas() - apostaSB);
        System.out.println(jogadores[0].getName() + " (Small Blinder) jogou " + apostaSB + " fichas");
        pote.fazApostaInicial(apostaSB);

        if(jogadores.length > 2){
            if(jogadores[1].getFichas() < 10){
                apostaBB = jogadores[1].getFichas();
            }else{
                apostaBB = 10;
            }
            jogadores[1].setFichas(jogadores[1].getFichas() - apostaBB);
            System.out.println(jogadores[1].getName() +" (Big Blinder) jogou " + apostaBB + " fichas");
            pote.fazApostaInicial(apostaBB);
        }
    }

    private void imprimeInfoJogo() {
        pote.toString();
        imprimeInfoJogadores();
    }

    private void trocaCarta() {
        System.out.println("\nEtapa 3: Troca de cartas.");
        for (int i = 0; i < jogadores.length; i++) {
            int cartasASacar = jogadores[i].removeCartas();
            Carta[] cartasNovas = baralho.daCartas(cartasASacar);
            jogadores[i].recebeCartas(cartasNovas);
            System.out.println(jogadores[i].getName() + " trocou " + cartasASacar + " cartas");
        }
    }

    //rodada comeca logo após o BB
    private void rodadaAposta() {
        boolean terminouApostas = false;

        System.out.println("\nEtapa 4: Apostas");

        while (!terminouApostas) {
            for (int i = 2; i < jogadores.length; i++) {
                aposta(i);
                terminouApostas = terminouApostas();
                if (terminouApostas) {
                    break;
                }
            }
            if (!terminouApostas) {
                for (int i = 0; i < 2; i++) {
                    aposta(i);
                    terminouApostas = terminouApostas();
                    if (terminouApostas) {
                        break;
                    }
                }
            }
        }
        System.out.println("Apostas encerradas!");
    }

    private void aposta(int i) {
        if (jogadores[i].getFichas() != 0 && jogadores[i].isVivo()) {
            if(!(jogadores[i] instanceof JogadorTeclado)){
                System.out.print(jogadores[i].getName());
            }
            int aposta = jogadores[i].aposta(pote.getUltimaAposta());
            pote.fazAposta(aposta);
            pote.toString();
        }
    }

    private boolean terminouApostas() {
        int vivos = 0;
        int vivoEtemFicha = 0;
        for (int i = 0; i < jogadores.length; i++) {
            if (jogadores[i].isVivo()) {
                vivos++;
                if (jogadores[i].getFichas() > 0) {
                    vivoEtemFicha++;
                }
            }
        }
        if ((pote.getRepeteAposta() + 1) == vivos) {
            System.out.println("Todas as apostas foram iguais na mesma rodada. Fim da etapa de apostas.");
        }
        if (vivos <= 1) {
            System.out.println("Número de jogadores vivos <= 1. Fim da etapa de apostas.");
        } else if (vivoEtemFicha < 2) {
            System.out.println("Apenas um jogador vivo e com fichas. Fim da etapa de apostas.");
        }
        return ((pote.getRepeteAposta() + 1) == vivos || vivos <= 1 || vivoEtemFicha < 2);
    }

    private void resultado() {

        int[] pontuacao = new int[5];
        int pontuacaoMaisAlta = 0;
        int numGanhadores = 0;

        System.out.println("\nEtapa 5: Resultado");

        for (int i = 0; i < jogadores.length; i++) {
            System.out.print(jogadores[i].getName() + ": ");
            if (!jogadores[i].isVivo()) {
                System.out.println("desistiu");
            } else {
                pontuacao[i] = Resultado.resultado(jogadores[i].getCartas());
                if (pontuacao[i] > pontuacaoMaisAlta) {
                    numGanhadores = 1;
                    pontuacaoMaisAlta = pontuacao[i];
                } else if (pontuacao[i] == pontuacaoMaisAlta) {
                    numGanhadores++;
                }
            }
        }
        if (numGanhadores == 1) {
            System.out.print("Ganhador(a): ");
        } else if (numGanhadores > 1) {
            System.out.print("Empate. Ganhadores: ");
        } else {
            System.out.print("Não há ganhadores nesta rodada.");
        }
        if (numGanhadores >= 1) {
            int restoPote = pote.getValor() % numGanhadores;
            int poteDivisao = pote.getValor() - restoPote;
            for (int i = 0; i < jogadores.length; i++) {
                if (pontuacao[i] == pontuacaoMaisAlta) {
                    jogadores[i].incrementa(poteDivisao / numGanhadores);
                    System.out.print(jogadores[i].getName() + " ");
                }
            }
            System.out.println();
            pote.setValor(restoPote);
        }
    }

    private void atualizaJogadores() {
        int jogadoresVivos = getJogadoresVivos();
        Jogador[] jogadoresNovaRodada = new Jogador[jogadoresVivos];

        //cria um novo array com apenas os jogadores vivos e roda 1 vez
        int j = 1;
        for (int i = 0; i < this.jogadores.length; i++) {
            if (this.jogadores[i].getFichas() > 0) {
                this.jogadores[i].setVivo(true);
                if (jogadoresVivos != 1) {
                    jogadoresNovaRodada[j] = this.jogadores[i];
                    j++;
                    jogadoresVivos--;
                } else {
                    jogadoresNovaRodada[0] = this.jogadores[i];
                    break;
                }
            }
        }
        this.jogadores = jogadoresNovaRodada;
    }

    private int getJogadoresVivos() {
        int jogadoresVivos = 0;
        //conta quantos jogadores ainda estão no jogo (ficha >0)
        for (int i = 0; i < this.jogadores.length; i++) {
            if (this.jogadores[i].getFichas() > 0) {
                jogadoresVivos++;
            }
        }
        return jogadoresVivos;
    }

    private boolean fimDeJogo() {
        return (getJogadoresVivos() == 1);
    }
}
