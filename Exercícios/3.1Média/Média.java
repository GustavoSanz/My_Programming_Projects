import java.util.Scanner;
public class Média {


/**
 * Solução para o Exercício 3.1:
 * Lê uma sequência de inteiros positivos até que o utilizador digite 0 
 * e, em seguida, imprime a média.
 */


    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        // Variáveis para armazenar a soma e a contagem
        // Usamos 'double' para a soma para garantir que a divisão (média)
        // resulte num número decimal, se necessário.
        double soma = 0;
        int contador = 0;
        int numero; // Variável para armazenar cada número lido

        System.out.println("--- Calculadora de Média ---");
        System.out.println("Digite uma sequência de números inteiros positivos.");
        System.out.println("Digite 0 para terminar e calcular a média.");

        // Usamos um loop 'do-while' porque queremos que o código
        // dentro do loop execute pelo menos uma vez (para ler o primeiro número).
        do {
            System.out.print("Digite um número: ");
            numero = scanner.nextInt();

            // Verificamos se o número é positivo antes de processá-lo
            if (numero > 0) {
                soma = soma + numero; // Adiciona o número à soma total
                contador = contador + 1; // Incrementa o contador de números
            } else if (numero < 0) {
                // O exercício pede "inteiros positivos", 
                // por isso é boa prática avisar o utilizador se inserir um inválido.
                System.out.println("Número negativo ignorado. Por favor, insira apenas positivos.");
            }
            // Se 'numero' for 0, a condição do 'while' será falsa
            // e o loop terminará na próxima verificação.

        } while (numero != 0);

        System.out.println("--- Resultado ---");
        
        // Verificação final: só podemos dividir se o contador for maior que 0.
        // Se o utilizador digitar 0 logo no início, o contador será 0.
        if (contador > 0) {
            double media = soma / contador;
            System.out.printf("Foram digitados %d números positivos.%n", contador);
            System.out.printf("A média dos números é: %.2f%n", media);
        } else {
            System.out.println("Nenhum número positivo foi digitado.");
        }

        scanner.close();
    }
}

  
