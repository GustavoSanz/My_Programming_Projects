import java.util.Scanner;

public class Numeros_pares {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Números Pares até N ---");
        System.out.print("Digite um número inteiro N: ");
        int n = scanner.nextInt();

        System.out.println("Os números pares de 1 até " + n + " são:");

        // Abordagem 1: Iterar de 2 em 2 (Mais eficiente)
        // Começamos o loop no primeiro número par (2)
        // e incrementamos o contador (i) de 2 em 2.
        
        for (int i = 2; i <= n; i = i + 2) {
            System.out.println(i);
        }
        scanner.close();
    }
}
    
