import org.junit.Test;
import static org.junit.Assert.*;

public class ResultadoTest {

    private Carta[] criarCartas(String[] naipes, int[] valores) {
        Carta[] cartas = new Carta[5];
        for (int i = 0; i < 5; i++) {
            cartas[i] = new Carta(naipes[i], valores[i]);
        }
        return cartas;
    }

    @Test
    public void testResultadoRoyalStraightFlush() {
        Carta[] cartas = criarCartas(
            new String[] { "Ouro", "Ouro", "Ouro", "Ouro", "Ouro" },
            new int[] { 1, 10, 11, 12, 13 }
        );
        int resultado = Resultado.resultado(cartas);
        assertEquals(23, resultado);
    }

    @Test
    public void testResultadoStraightFlush() {
        Carta[] cartas = criarCartas(
            new String[] { "Paus", "Paus", "Paus", "Paus", "Paus" },
            new int[] { 5, 6, 7, 8, 9 }
        );
        int resultado = Resultado.resultado(cartas);
        assertEquals(22, resultado);
    }

    @Test
    public void testResultadoQuadra() {
        Carta[] cartas = criarCartas(
            new String[] { "Copas", "Paus", "Ouro", "Espada", "Copas" },
            new int[] { 2, 2, 2, 2, 5 }
        );
        int resultado = Resultado.resultado(cartas);
        assertEquals(21, resultado);
    }

    @Test
    public void testResultadoFullHouse() {
        Carta[] cartas = criarCartas(
            new String[] { "Ouro", "Copas", "Paus", "Ouro", "Copas" },
            new int[] { 7, 7, 7, 10, 10 }
        );
        int resultado = Resultado.resultado(cartas);
        assertEquals(20, resultado);
    }

    @Test
    public void testResultadoFlush() {
        Carta[] cartas = criarCartas(
            new String[] { "Paus", "Paus", "Paus", "Paus", "Paus" },
            new int[] { 2, 5, 7, 9, 11 }
        );
        int resultado = Resultado.resultado(cartas);
        assertEquals(19, resultado);
    }

    @Test
    public void testResultadoSequencia() {
        Carta[] cartas = criarCartas(
            new String[] { "Espada", "Copas", "Ouro", "Paus", "Ouro" },
            new int[] { 10, 9, 8, 7, 6 }
        );
        int resultado = Resultado.resultado(cartas);
        assertEquals(18, resultado);
    }

    @Test
    public void testResultadoTrinca() {
        Carta[] cartas = criarCartas(
            new String[] { "Copas", "Ouro", "Espada", "Copas", "Ouro" },
            new int[] { 3, 3, 3, 5, 9 }
        );
        int resultado = Resultado.resultado(cartas);
        assertEquals(17, resultado);
    }

    @Test
    public void testResultadoDoisPares() {
        Carta[] cartas = criarCartas(
            new String[] { "Paus", "Copas", "Espada", "Copas", "Ouro" },
            new int[] { 3, 3, 6, 6, 9 }
        );
        int resultado = Resultado.resultado(cartas);
        assertEquals(16, resultado);
    }

    @Test
    public void testResultadoUmPar() {
        Carta[] cartas = criarCartas(
            new String[] { "Ouro", "Paus", "Copas", "Espada", "Copas" },
            new int[] { 4, 8, 9, 11, 11 }
        );
        int resultado = Resultado.resultado(cartas);
        assertEquals(15, resultado);
    }

    @Test
    public void testResultadoSequenciaAsAlto() {
        Carta[] cartas = criarCartas(
            new String[] { "Espada", "Ouro", "Paus", "Copas", "Ouro" },
            new int[] { 10, 11, 12, 13, 1 }
        );
        int resultado = Resultado.resultado(cartas);
        assertEquals(18, resultado); //troquei para 18
    }

    @Test
    public void testResultadoSequenciaBaixa() {
        Carta[] cartas = criarCartas(
                new String[] { "Espada", "Ouro", "Paus", "Copas", "Ouro" },
                new int[] { 2, 4, 5, 3, 1 }
        );
        int resultado = Resultado.resultado(cartas);
        assertEquals(18, resultado);
    }

    @Test
    public void testResultadoSequenciaAsBaixo() {
        Carta[] cartas = criarCartas(
            new String[] { "Copas", "Espada", "Paus", "Ouro", "Ouro" },
            new int[] { 13, 1, 2, 3, 4 }
        );
        int resultado = Resultado.resultado(cartas);
        assertEquals(14, resultado);
    }

    @Test
    public void testResultadoOrdemAleatoria() {
        Carta[] cartas = criarCartas(
            new String[] { "Espada", "Ouro", "Ouro", "Paus", "Copas" },
            new int[] { 6, 1, 4, 5, 2 }
        );
        int resultado = Resultado.resultado(cartas);
        assertEquals(14, resultado); //troquei para 14
    }

    @Test
    public void testResultadoDoisParesOrdemDiferente() {
        Carta[] cartas = criarCartas(
            new String[] { "Ouro", "Copas", "Paus", "Copas", "Espada" },
            new int[] { 6, 6, 9, 9, 4 }
        );
        int resultado = Resultado.resultado(cartas);
        assertEquals(16, resultado);
    }

    @Test
    public void testResultadoTrincaOrdemDiferente() {
        Carta[] cartas = criarCartas(
            new String[] { "Espada", "Ouro", "Copas", "Ouro", "Copas" },
            new int[] { 8, 6, 6, 6, 10 }
        );
        int resultado = Resultado.resultado(cartas);
        assertEquals(17, resultado);
    }

    @Test
    public void testResultadoFullHouseOrdemDiferente() {
        Carta[] cartas = criarCartas(
            new String[] { "Copas", "Ouro", "Ouro", "Copas", "Paus" },
            new int[] { 7, 7, 10, 10, 7 }
        );
        int resultado = Resultado.resultado(cartas);
        assertEquals(20, resultado);
    }

    @Test
    public void testResultadoQuadraOrdemDiferente() {
        Carta[] cartas = criarCartas(
            new String[] { "Paus", "Copas", "Espada", "Ouro", "Ouro" },
            new int[] { 2, 5, 2, 2, 2 }
        );
        int resultado = Resultado.resultado(cartas);
        assertEquals(21, resultado);
    }

    @Test
    public void testResultadoCasosNumerosUnicos() {
        Carta[] cartas = criarCartas(
            new String[] { "Espada", "Copas", "Paus", "Ouro", "Copas" },
            new int[] { 2, 4, 5, 6, 7 }
        );
        int resultado = Resultado.resultado(cartas);
        assertEquals(7, resultado);
    }
}
