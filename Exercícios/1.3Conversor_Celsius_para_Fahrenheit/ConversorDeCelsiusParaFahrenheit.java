import java.util.Scanner;

/**
 * Conversor simples de Celsius para Fahrenheit.
 *
 * Como usar (Windows PowerShell):
 *   javac ConversorDeCelsiusParaFahrenheit.java
 *   java ConversorDeCelsiusParaFahrenheit
 *
 * Exemplo:
 *   Entrada: 100
 *   Saída: 100.0 °C = 212.0 °F
 */
public class ConversorDeCelsiusParaFahrenheit {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Conversor de Celsius para Fahrenheit");
        System.out.print("Insira a temperatura em °C (ex: 25.5): ");

        String line = sc.nextLine().trim();
        if (line.isEmpty()) {
            System.out.println("Nenhuma entrada detectada. Abortando.");
            sc.close();
            return;
        }

        try {
            double celsius = Double.parseDouble(line.replace(',', '.'));
            double fahrenheit = celsius * 9.0 / 5.0 + 32.0;
            // Formatação simples com uma casa decimal quando apropriado
            System.out.printf("%.1f °C = %.1f °F%n", celsius, fahrenheit);
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Por favor insira um número (ex: 20 ou 20.5).\nErro: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}