/**
 * @author Nathalia Ferrari
 * Classe que possui os parametros e funcoes do pote
 */
public class Pote {
    private int valor = 0;
    private int ultimaAposta = 10; // inicia com 10 que é o valor mínimo de aposta
    private int repeteAposta = 0;

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getUltimaAposta() {
        return ultimaAposta;
    }

    public int getRepeteAposta() {
        return repeteAposta;
    }

    public void setRepeteAposta(int repeteAposta) {
        this.repeteAposta = repeteAposta;
    }

    //atualiza valor ultima aposta apenas se a ultima aposta foi maior que a anterior
    public void setUltimaAposta(int ultimaAposta) {
        if (ultimaAposta > this.ultimaAposta){
            this.ultimaAposta = ultimaAposta;
        }
    }

    public void fazAposta(int valorIncremento){
        if(valorIncremento == this.ultimaAposta){
            this.repeteAposta++;
        }else {
            repeteAposta = 0;
        }
        this.setValor(valorIncremento + this.getValor());
        setUltimaAposta(valorIncremento);
    }
    public void fazApostaInicial(int valorIncremento){
        this.setValor(valorIncremento + this.getValor());
    }

    public String toString(){
        System.out.println("### Pote: " + this.valor + " ###");
        return null;
    }
}
