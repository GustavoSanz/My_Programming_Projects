import java.util.Random;
import java.util.Scanner;

public class JogoAdivinha {
    public static void main(String[] args) {
        
        // --- Preparação do Jogo (Jogador A) ---
        
        // 1. O Jogador A (computador) "pensa" num número.
        // Vamos definir um limite, por ex., entre 1 e 100.
        final int LIMITE_MAXIMO = 100;
        Random random = new Random();
        
        // random.nextInt(LIMITE_MAXIMO) gera de 0 a 99.
        // Somamos 1 para que o número seja de 1 a 100.
        int numeroSecreto = random.nextInt(LIMITE_MAXIMO) + 1;
        
        // --- Início do Jogo (Jogador B) ---
        
        Scanner scanner = new Scanner(System.in);
        final int MAX_TENTATIVAS = 10;
        boolean acertou = false; // "Flag" para saber se o jogador ganhou

        System.out.println("--- Jogo da Adivinha ---");
        System.out.println("Eu pensei num número inteiro positivo entre 1 e " + LIMITE_MAXIMO + ".");
        System.out.println("Você tem " + MAX_TENTATIVAS + " tentativas para adivinhar!");
        
        // 2. O Jogador B tem 10 tentativas. Um loop 'for' é perfeito para isto.
        for (int tentativa = 1; tentativa <= MAX_TENTATIVAS; tentativa++) {
            
            System.out.println("\n--- Tentativa " + tentativa + " de " + MAX_TENTATIVAS + " ---");
            System.out.print("Digite o seu palpite: ");
            int palpite = scanner.nextInt();

            // 3. O programa indica o resultado da tentativa
            if (palpite == numeroSecreto) {
                System.out.println("Acertou! Parabéns!");
                acertou = true; // Marca que o jogador ganhou
                break; // Interrompe o loop 'for' imediatamente
                
            } else if (palpite < numeroSecreto) {
                System.out.println("Muito baixa! Tente um número maior.");
                
            } else { // (palpite > numeroSecreto)
                System.out.println("Muito alta! Tente um número menor.");
            }
        } // Fim do loop 'for'

        // --- Fim do Jogo ---
        
        // 4. Se o loop terminar e o jogador não tiver acertado, ele perde.
        // Verificamos a nossa "flag"
        if (!acertou) {
            System.out.println("\n--- Fim de Jogo ---");
            System.out.println("Você perdeu! Suas 10 tentativas acabaram.");
            System.out.println("O número secreto era: " + numeroSecreto);
        }

        scanner.close();
    }
}
