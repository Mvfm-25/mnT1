// Resolução do t1 de mn.
// [mvfm] 11-mai-2026

import java.util.random.*;
import java.util.*;

// Classe de execução.
public class Carrossel {
    
    private class Processo {
        private String nome;
        
        public Processo(String nome) {
            this.nome = nome;
        }

        public String getNome() {
            return nome;
        }
    }

    private class Processador{
        private Random random = new Random();

        public void enviaProcesso(Processo processo, Processador processador, int numProcessadores){
            System.out.println("Enviando processo " + processo.getNome() + " para processador " + processador);
            processador.rodaProcesso(processo, numProcessadores);
        }

        public boolean rodaProcesso(Processo processo, int numProcessadores){
            System.out.println("Rodando processo " + processo.getNome());
            double prob = 1.0 / (numProcessadores + 1);
            
            if (random.nextDouble() < prob){
                System.out.println("Processo " + processo.getNome() + " finalizado.");
                return true;
            } else {
                System.out.println("Processo " + processo.getNome() + " não finalizado, reenviando...");
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
            Processo processo = new Carrossel().new Processo("Processo " + p);
            p.enviaProcesso(processo, p, numProcessadores);
        }
    }
}