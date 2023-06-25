/**
 * @author Nathalia Ferrari
 */
public class Resultado {

    public static int resultado(Carta[] carta){
        if(royalFlush(carta)){
            System.out.println("Royal Flush");
            return 23;
        }
        if(straightFlush(carta)){
            System.out.println("Straight Flush");
            return 22;
        }
        if(quadra(carta)){
            System.out.println("Quadra");
            return 21;
        }
        if(fullHouse(carta)){
            System.out.println("Full House");
            return 20;
        }
        if(flush(carta)){
            System.out.println("Flush");
            return 19;
        }
        if(straight(carta)){
            System.out.println("Straight");
            return 18;
        }
        if(trinca(carta)){
            System.out.println("Trinca");
            return 17;
        }
        if(doisPares(carta)){
            System.out.println("Dois pares");
            return 16;
        }
        if(umPar(carta)){
            System.out.println("Um par");
            return 15;
        }
        if(cartaAlta(carta) == 14){
            System.out.println("Carta mais alta 1");
        }else
            System.out.println("Carta mais alta "+cartaAlta(carta));
        return cartaAlta(carta);
    }

    public static boolean royalFlush(Carta[] carta) {
        ordenar(carta);
        if (flush(carta)) {
            return carta[0].getNumero() == 1 && carta[1].getNumero() == 10;
        }
        return false;
    }

    public static boolean straightFlush(Carta[] carta) {
        return straight(carta) && flush(carta);
    }

    public static boolean quadra(Carta[] carta){
        int num1 = carta[0].getNumero();
        int num2 = carta[1].getNumero();
        int count1 = 0;
        int count2 = 0;

        for(int i = 0; i < carta.length ; i++){
            if(carta[i].getNumero() == num1){
                count1++;
            }
            if(carta[i].getNumero() == num2){
                count2++;
            }
        }
        return (count1 == 4 || count2 == 4);
    }

    public static boolean fullHouse(Carta[] carta) {
        int num1 = carta[0].getNumero();
        int vezesNum1 = 1;
        int vezesNum2 = 0;
        int num2 = 0;

        for (int i = 1; i < carta.length; i++) {
            if (carta[i].getNumero() == num1) {
                vezesNum1++;
            }else{
                num2 = carta[i].getNumero();
            }
        }
        for (int i = 1; i < carta.length; i++) {
            if (carta[i].getNumero() == num2) {
                vezesNum2++;
            }
        }

        if ((vezesNum1 == 3 && vezesNum2 == 2) || (vezesNum1 == 2 && vezesNum2 == 3)) {
            return true;
        }
        return false;
    }

    public static boolean trinca(Carta[] carta) {
        int num1 = carta[0].getNumero();
        int num2 = carta[1].getNumero();
        int num3 = carta[2].getNumero();
        int vezesNum1 = 0;
        int vezesNum2 = 0;
        int vezesNum3 = 0;

        for (int i = 0; i < carta.length; i++) {
            if (carta[i].getNumero() == num1) {
                vezesNum1++;
            } else if (carta[i].getNumero() == num2) {
                vezesNum2++;
            } else if (carta[i].getNumero() == num3) {
                vezesNum3++;
            }
        }
        return (vezesNum1 == 3 || vezesNum2 == 3 || vezesNum3 == 3);
    }

    public static boolean umPar(Carta[] carta) {
        ordenar(carta);
        int par = 0;
        for (int i = 0; i < (carta.length - 1); i++) {
            int num = carta[i].getNumero();
            if (carta[i + 1].getNumero() == num) {
                par++;
                i++;
            }
        }
        return par == 1;
    }

    public static boolean doisPares(Carta[] carta) {
        ordenar(carta);
        int par = 0;
        for (int i = 0; i < (carta.length - 1); i++) {
            int num = carta[i].getNumero();
            if (carta[i + 1].getNumero() == num) {
                par++;
                i++;
            }
        }
        return par == 2;
    }


    public static boolean straight(Carta[] carta) {
        ordenar(carta);
        for (int i = 0; i < (carta.length - 1); i++) {
            if (i == 0 && carta[i].getNumero() == 1) {
                if (carta[i + 1].getNumero() != 2 && carta[i + 1].getNumero() != 10) {
                    return false;
                }
            } else {
                if ((carta[i + 1].getNumero() - carta[i].getNumero()) != 1) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean flush(Carta[] carta){
        for(int i = 0; i < (carta.length - 1) ; i++){
            if(!(carta[i + 1].getNaipe().equals(carta[i].getNaipe()))){
                return false;
            }
        }
        return true;
    }


    public static Carta[] ordenar(Carta[] carta){
        Carta cartaAux;
        boolean troca = true;
        while(troca) {
            troca = false;
            for (int i = 1; i < carta.length; i++) {
                if (carta[i].getNumero() < carta[i - 1].getNumero()) {
                    troca = true;
                    cartaAux = carta[i];
                    carta[i] = carta[i - 1];
                    carta[i - 1] = cartaAux;
                }
            }
        }
        return carta;
    }

    public static int cartaAlta(Carta[] carta){
        ordenar(carta);
        if(carta[0].getNumero() == 1){
            return 14;
        }else{
            return carta[4].getNumero();
        }
    }
}
