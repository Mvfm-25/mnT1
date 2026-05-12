// Resolução do t1 de mn.
// [mvfm] 11-mai-2026

import java.util.Random;

// Classe de execução.
public class Carrossel {

    static final int    AQUECIMENTO    = 50_000;
    static final int    AMOSTRA        = 200_000;
    static final Random random         = new Random();

    public static void main(String[] args) {
        int numProcessadores = Integer.parseInt(args[0]);

        int[]    fila       = new int[numProcessadores];
        double[] acumulado  = new double[numProcessadores];

        for (int passo = 0; passo < AQUECIMENTO + AMOSTRA; passo++) {
            fila[0]++;

            int[] proximaFila = new int[numProcessadores];
            for (int i = 0; i < numProcessadores; i++) {
                int    processador  = i + 1;
                double probCompleta = 1.0 / (numProcessadores + 1);
                double probP1       = (numProcessadores + 1 - processador) / (numProcessadores + 1.0);

                for (int k = 0; k < fila[i]; k++) {
                    double sorteio = random.nextDouble();
                    if (sorteio < probCompleta) {
                        // pedido concluído
                    } else if (sorteio < probCompleta + probP1) {
                        proximaFila[(i + 1) % numProcessadores]++;
                    } else {
                        proximaFila[(i + 2) % numProcessadores]++;
                    }
                }
            }
            fila = proximaFila;

            if (passo >= AQUECIMENTO) {
                for (int i = 0; i < numProcessadores; i++) acumulado[i] += fila[i];
            }
        }

        double[] media = new double[numProcessadores];
        double   total = 0;
        for (int i = 0; i < numProcessadores; i++) {
            media[i]  = acumulado[i] / AMOSTRA;
            total    += media[i];
        }

        int maxProcessador = 0, minProcessador = 0;
        for (int i = 1; i < numProcessadores; i++) {
            if (media[i] > media[maxProcessador]) maxProcessador = i;
            if (media[i] < media[minProcessador]) minProcessador = i;
        }

        System.out.printf("Pedidos no carrossel (estado estacionário): %.4f%n", total);
        System.out.printf("Processador com mais pedidos:  %d (%.4f pedidos em média)%n",
                maxProcessador + 1, media[maxProcessador]);
        System.out.printf("Processador com menos pedidos: %d (%.4f pedidos em média)%n",
                minProcessador + 1, media[minProcessador]);
    }
}
