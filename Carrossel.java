// Resolução do t1 de mn, solução analítica via Eliminação de Gauss.
// [mvfm] 14-mai-2026

public class Carrossel {

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);

        // Monta o sistema
        double[][] A = new double[n][n + 1];
        for (int i = 0; i < n; i++) A[i][i] = 1.0;
        A[0][n] = 1.0;

        for (int j = 0; j < n; j++) {
            double fwd  = (double)(n - j) / (n + 1);
            double skip = (double) j       / (n + 1);
            A[(j + 1) % n][j] -= fwd;
            A[(j + 2) % n][j] -= skip;
        }

        // Eliminação de Gauss com pivotamento
        for (int col = 0; col < n; col++) {
            int piv = col;
            for (int row = col + 1; row < n; row++)
                if (Math.abs(A[row][col]) > Math.abs(A[piv][col])) piv = row;
            double[] tmp = A[col]; A[col] = A[piv]; A[piv] = tmp;

            for (int row = col + 1; row < n; row++) {
                double fator = A[row][col] / A[col][col];
                for (int k = col; k <= n; k++)
                    A[row][k] -= fator * A[col][k];
            }
        }

        // Substituição retroativa
        double[] pi = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            pi[i] = A[i][n];
            for (int j = i + 1; j < n; j++) pi[i] -= A[i][j] * pi[j];
            pi[i] /= A[i][i];
        }

        double total = 0;
        int max = 0, min = 0;
        for (int i = 0; i < n; i++) {
            total += pi[i];
            if (pi[i] > pi[max]) max = i;
            if (pi[i] < pi[min]) min = i;
        }

        System.out.printf("Pedidos no carrossel (estado estacionário): %.4f%n", total);
        System.out.printf("Processador com mais pedidos:  %d (%.4f pedidos em média)%n", max + 1, pi[max]);
        System.out.printf("Processador com menos pedidos: %d (%.4f pedidos em média)%n", min + 1, pi[min]);
    }
}
