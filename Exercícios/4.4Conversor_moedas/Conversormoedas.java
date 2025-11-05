import java.util.Locale;
import java.util.Scanner;

public class Conversormoedas {
    /**
     * Função auxiliar para procurar o câmbio de uma moeda no array.
     * * @param codigoProcurado O código (int) da moeda a procurar.
     * @param arrayCodigos O array de códigos onde procurar.
     * @param arrayCambios O array de câmbios paralelo.
     * @return O câmbio (double) se encontrado, ou -1.0 se não encontrado.
     */
    public static double encontrarCambio(int codigoProcurado, int[] arrayCodigos, double[] arrayCambios) {
        
        // 1. Caso especial: O Euro (vamos usar o código 99)
        // A taxa do Euro para Euro é 1.0
        if (codigoProcurado == 99) {
            return 1.0;
        }

        // 2. Procurar nos arrays
        // O loop percorre as 10 posições
        for (int i = 0; i < arrayCodigos.length; i++) {
            // Verifica se o código na posição 'i' é o que procuramos
            if (arrayCodigos[i] == codigoProcurado) {
                // Se for, retorna o câmbio na *mesma posição* 'i'
                return arrayCambios[i];
            }
        }

        // 3. Se o loop terminar sem encontrar, retorna -1 (código de erro)
        return -1.0;
    }


    public static void main(String[] args) {
        
        // --- 1. Armazenar os dados das 10 moedas ---
        // (Vamos "hardcodar" os valores para o exercício)
        
        // Array de códigos (inteiros)
        int[] codigosMoedas = {
            1,    // Dólar Americano (USD)
            2,    // Iene Japonês (JPY)
            3,    // Real Brasileiro (BRL)
            4,    // Libra Esterlina (GBP)
            5,    // Dólar Australiano (AUD)
            6,    // Dólar Canadense (CAD)
            7,    // Franco Suíço (CHF)
            8,    // Yuan Chinês (CNY)
            9,    // Rúpia Indiana (INR)
            10    // Peso Mexicano (MXN)
        };
        
        // Array de câmbios (reais/double) - Valores de exemplo (1 EUR = X)
        double[] cambiosParaEuro = {
            1.08,   // 1 EUR = 1.08 USD
            169.50, // 1 EUR = 169.50 JPY
            5.50,   // 1 EUR = 5.50 BRL
            0.85,   // 1 EUR = 0.85 GBP
            1.65,   // 1 EUR = 1.65 AUD
            1.47,   // 1 EUR = 1.47 CAD
            0.98,   // 1 EUR = 0.98 CHF
            7.80,   // 1 EUR = 7.80 CNY
            90.0,   // 1 EUR = 90.0 INR
            18.5    // 1 EUR = 18.5 MXN
        };

        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        
        System.out.println("--- Conversor de Moedas (Base: Euro) ---");
        System.out.println("Códigos disponíveis: 1-10 (USD, JPY, BRL, ...)");
        System.out.println("Para Euro, use o código: 99");

        // --- 2. Aceitar a importância e os códigos X e Y ---
        
        System.out.print("\nDigite a importância na moeda de origem (X): ");
        double importanciaX = scanner.nextDouble();

        System.out.print("Digite o código da moeda de ORIGEM (X): ");
        int codigoX = scanner.nextInt();

        System.out.print("Digite o código da moeda de DESTINO (Y): ");
        int codigoY = scanner.nextInt();

        // --- 3. Encontrar as taxas de câmbio ---
        
        // Chama a função auxiliar para encontrar as taxas
        double taxaX = encontrarCambio(codigoX, codigosMoedas, cambiosParaEuro);
        double taxaY = encontrarCambio(codigoY, codigosMoedas, cambiosParaEuro);

        // --- 4. Validar e Realizar o Câmbio ---

        // Verificar se os códigos foram encontrados
        if (taxaX == -1.0) {
            System.out.println("Erro! O código da moeda de ORIGEM (" + codigoX + ") não foi encontrado.");
        } else if (taxaY == -1.0) {
            System.out.println("Erro! O código da moeda de DESTINO (" + codigoY + ") não foi encontrado.");
        } else {
            
            // Lógica de conversão
            // Passo 1: Converter X para Euros
            double importanciaEmEuros = importanciaX / taxaX;
            
            // Passo 2: Converter Euros para Y
            double importanciaY = importanciaEmEuros * taxaY;

            // --- 5. Imprimir as importâncias equivalentes ---
            System.out.println("\n--- Resultado da Conversão ---");
            System.out.printf("Importância de Origem: %.2f (Código %d)%n", importanciaX, codigoX);
            System.out.printf("Importância de Destino: %.2f (Código %d)%n", importanciaY, codigoY);
        }

        scanner.close();
    }
}
