// Resolução do t1 de mn.
// [mvfm] 11-mai-2026

import java.util.random.*;
import java.util.*;

// Classe de execução.
public class Carrossel {

    //Id's
    public static int processoId = 0;
    public static int processadorId = 0;

    private static class Processo {
        private int id;
        public Processo() {
            this.id = processoId++;
        }

        public int getId() {
            return id;
        }
    }

    private static class Processador {
        private Random random = new Random();
        private int id;
        private Processador p1, p2;

        public Processador() {
            this.id = processadorId++;
        }

        public int getId() {
            return id;
        }

        public void setP1(Processador p1) {
            this.p1 = p1;
        }
        public void setP2(Processador p2) {
            this.p2 = p2;
        }

        public void enviaP1(Processo processo, int numProcessadores){
            double probP1 = (numProcessadores + 1 - this.id) / (numProcessadores + 1.0);
            if(random.nextDouble() < probP1){
                p1.rodaProcesso(processo, numProcessadores);
            } else {
                System.out.println("Processo " + processo.getId() + " não foi enviado para o processador " + p1.getId());
            }
        }
        
        public void enviaP2(Processo processo, int numProcessadores){
            double probP2 = (this.id - 1) / (numProcessadores + 1.0);
            if(random.nextDouble() < probP2){
                p2.rodaProcesso(processo, numProcessadores);
            } else {
                System.out.println("Processo " + processo.getId() + " não foi enviado para o processador " + p2.getId());
            }
        }

        public boolean rodaProcesso(Processo processo, int numProcessadores){
            System.out.println("Rodando processo " + processo.getId() + " No processador " + this.getId());
            double prob = 1.0 / (numProcessadores + 1);
            
            if (random.nextDouble() < prob){
                System.out.println("Processo " + processo.getId() + " finalizado.");
                return true;
            } else {
                System.out.println("Processo " + processo.getId() + " não finalizado, enviando para o próximo processador.");
                return false;
            }
        }

    }

    public static void main(String[] args) {
        int numProcessadores = Integer.parseInt(args[0]);
        Processador[] processadores = new Processador[numProcessadores];

        for (int i = 0; i < numProcessadores; i++) {
            processadores[i] = new Processador();
        }

        for (int i = 0; i < numProcessadores; i++) {
            processadores[i].setP1(processadores[(i + 1) % numProcessadores]);
            processadores[i].setP2(processadores[(i + 2) % numProcessadores]);
        }

        for (Processador p : processadores) {
            Processo processo = new Processo();
            p.rodaProcesso(processo, numProcessadores);
        }
    }
}