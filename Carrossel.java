// Resolução do t1 de mn.
// [mvfm] 11-mai-2026

import java.util.random.*;
import java.util.*;

// Classe de execução.
public class Carrossel {

    //Id's
    public static int processoId = 0;
    public static int processadorId = 0;

    private class Processo {
        private int id;
        public Processo() {
            this.id = processoId++;
        }

        public int getId() {
            return id;
        }
    }

    private class Processador{
        private Random random = new Random();
        private int id;

        public Processador() {
            this.id = processadorId++;
        }

        public int getId() {
            return id;
        }

        public void enviaProcesso(Processo processo, Processador processador, int numProcessadores){
            System.out.println("Enviando processo " + processo.getId() + " para processador " + processador.getId());
            processador.rodaProcesso(processo, numProcessadores);
        }

        public boolean rodaProcesso(Processo processo, int numProcessadores){
            System.out.println("Rodando processo " + processo.getId() + " No processador " + this.getId());
            double prob = 1.0 / (numProcessadores + 1);
            
            if (random.nextDouble() < prob){
                System.out.println("Processo " + processo.getId() + " finalizado.");
                return true;
            } else {
                System.out.println("Processo " + processo.getId() + " não finalizado, reenviando...");
                return false;
            }
        }

    }

    public static void main(String[] args) {
        int numProcessadores = Integer.parseInt(args[0]);
        Processador[] processadores = new Processador[numProcessadores];

        for (int i = 0; i < numProcessadores; i++) {
            processadores[i] = new Carrossel().new Processador();
        }

        for(Processador p : processadores){
            Processo processo = new Carrossel().new Processo();
            p.enviaProcesso(processo, p, numProcessadores);
        }
    }
}