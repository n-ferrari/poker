/**
 * @author Nathalia Ferrari
 * Classe main que inicializa o jogo
 */
public class Controle {
    public static void main(String[] args) {
        Jogo jogo = new Jogo(5);

        System.out.println("Início do jogo");
        while (!jogo.fimDeJogo()) {
            jogo.setBaralho(new Baralho());
            jogo.embaralhaBaralho();
            //jogo.imprimeBaralho();

            jogo.distribuiCartas(5);
            jogo.apostasIniciais();
            //jogo.imprimeInfoJogo();
            jogo.trocaCarta();
            jogo.rodadaAposta();
            //jogo.imprimeInfoJogo();
            jogo.resultado();
            //jogo.imprimeInfoJogo();
            jogo.atualizaJogadores();
            //jogo.imprimeInfoJogo();

        }
    }
}
