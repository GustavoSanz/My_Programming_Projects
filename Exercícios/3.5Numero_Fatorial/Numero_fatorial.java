import java.util.Scanner;

public class Numero_fatorial {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Calculadora de Fatorial (N!) ---");
        System.out.print("Digite um número inteiro positivo (N): ");
        int n = scanner.nextInt();

        // 1. Validar a entrada
        // Fatorial não é definido para números negativos.
        if (n < 0) {
            System.out.println("Erro: Fatorial não é definido para números negativos.");
        } else {
            
            // 2. Inicializar o resultado
            // Usamos 'long' para caber números maiores (até 20!)
            // Começa em 1 (elemento neutro da multiplicação)
            long fatorial = 1;

            // 3. Calcular o fatorial usando um loop 'for'
            // O loop multiplica 1 * 2 * 3 * ... * N
            for (int i = 1; i <= n; i++) {
                fatorial = fatorial * i;
            }

            // Nota: Se n=0, o loop (i=1; i<=0; ...) não executa nenhuma vez.
            // O resultado 'fatorial' permanece 1, que é o correto (0! = 1).
            
            // 4. Imprimir o resultado
            System.out.println("O fatorial de " + n + " (ou " + n + "!) é: " + fatorial);
            
            if (n > 20) {
                System.out.println("Aviso: O resultado para N > 20 provavelmente está incorreto");
                System.out.println("devido a overflow (o número é grande demais para o tipo 'long').");
            }
        }

        scanner.close();
    }
}
