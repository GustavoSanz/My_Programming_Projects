import java.util.Scanner;

public class Potências {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Calculadora de Potência (a^b) ---");
        
        System.out.print("Digite o valor da base (a): ");
        int a = scanner.nextInt();

        System.out.print("Digite o valor do expoente (b): ");
        int b = scanner.nextInt();

        // Usamos 'long' para o resultado, pois potências
        // crescem muito rápido e podem estourar o limite de 'int'.
        long resultado = 1;

        // O exercício não especifica o que fazer com expoentes negativos.
        // Assumindo que b >= 0 (expoente não-negativo).
        if (b < 0) {
            System.out.println("Este programa só calcula potências com expoentes não-negativos (b >= 0).");
        } else {
            
            // 1. O loop 'for' vai executar 'b' vezes.
            //    (Começa em 1 e vai até b)
            for (int i = 1; i <= b; i++) {
                // 2. Em cada iteração, multiplicamos o 'resultado' atual pela base 'a'.
                resultado = resultado * a;
                
                // Exemplo: a=3, b=4
                // i=1: resultado = 1 * 3  (resultado = 3)
                // i=2: resultado = 3 * 3  (resultado = 9)
                // i=3: resultado = 9 * 3  (resultado = 27)
                // i=4: resultado = 27 * 3 (resultado = 81)
            }

            // 3. Caso especial: b = 0
            // Se b=0, o loop 'for' (int i = 1; i <= 0; ...) nunca executa.
            // O 'resultado' permanece 1 (que é o valor correto para a^0).
            // A nossa lógica já trata este caso automaticamente!

            System.out.println("O resultado de " + a + " elevado à potência de " + b + " é: " + resultado);
        }

        scanner.close();
    }
}
