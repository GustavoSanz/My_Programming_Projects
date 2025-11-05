import java.util.Scanner;

public class BuscaEmArray {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        // 1. Criar o array com 5 posições
        // (Em Java, os arrays têm um tamanho fixo após a criação)
        final int TAMANHO_ARRAY = 5;
        int[] numeros = new int[TAMANHO_ARRAY];

        System.out.println("--- Preenchimento do Array ---");
        System.out.println("Por favor, digite " + TAMANHO_ARRAY + " números inteiros:");

        // 2. Primeiro Loop: Ler os valores para o array
        // (i vai de 0 até 4)
        for (int i = 0; i < numeros.length; i++) {
            System.out.print("Digite o valor para a posição " + i + ": ");
            numeros[i] = scanner.nextInt();
        }

        // 3. Ler o valor 'x' a ser procurado
        System.out.println("\n--- Busca no Array ---");
        System.out.print("Digite o número (x) que deseja procurar: ");
        int x = scanner.nextInt();

        // 4. Segundo Loop: Procurar 'x' no array
        
        // Usamos uma "flag" booleana para saber se encontrámos o valor
        boolean encontrado = false;
        // E uma variável para guardar a posição onde foi encontrado
        int posicaoEncontrada = -1; // Começa em -1 (um índice inválido)

        for (int i = 0; i < numeros.length; i++) {
            if (numeros[i] == x) {
                encontrado = true;
                posicaoEncontrada = i;
                
                // Otimização: Se já encontrámos o número, podemos parar de procurar.
                break; 
            }
        }

        // 5. Imprimir o resultado final
        if (encontrado) {
            System.out.println("--- Resultado ---");
            System.out.println("O valor " + x + " foi encontrado!");
            System.out.println("Ele está na posição (índice) " + posicaoEncontrada + " do array.");
        } else {
            System.out.println("--- Resultado ---");
            System.out.println("O valor " + x + " não foi encontrado no array.");
        }

        scanner.close();
    }
}
