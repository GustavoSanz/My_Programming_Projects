import java.util.Scanner;  

public class Par_e_impar {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        // 1. Inicializar os contadores
        int contadorPares = 0;
        int contadorImpares = 0;
        
        int numero; // Variável para guardar o número lido

        System.out.println("--- Contador de Pares e Ímpares ---");
        System.out.println("Digite uma sequência de números inteiros positivos.");
        System.out.println("Digite 0 para terminar e ver o resultado.");

        // Usaremos um loop 'while(true)' que será interrompido
        // manualmente quando o utilizador digitar 0.
        while (true) {
            System.out.print("Digite um número: ");
            numero = scanner.nextInt();

            // 2. Verificar a condição de paragem (0)
            if (numero == 0) {
                break; // Interrompe o loop
            }

            // 3. Ignorar números negativos (o exercício pede "inteiros positivos")
            if (numero < 0) {
                System.out.println("Número negativo ignorado. Por favor, insira apenas positivos.");
                continue; // Pula para a próxima iteração do loop
            }

            // 4. Verificar se o número é par ou ímpar
            // Usamos o operador módulo (resto da divisão)
            if (numero % 2 == 0) {
                // Se o resto da divisão por 2 for 0, é par
                contadorPares++; // Incrementa o contador de pares
            } else {
                // Se o resto não for 0, é ímpar
                contadorImpares++; // Incrementa o contador de ímpares
            }
        }

        // 5. Imprimir os resultados finais
        System.out.println("--- Resultado Final ---");
        System.out.println("Total de números PARES: " + contadorPares);
        System.out.println("Total de números ÍMPARES: " + contadorImpares);

        scanner.close();
    }
}
