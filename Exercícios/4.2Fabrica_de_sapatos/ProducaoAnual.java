import java.util.Scanner;

public class ProducaoAnual {
  public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        
        // Arrays para guardar os dados
        int[] producaoMensal = new int[12];
        String[] nomesMeses = {
            "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho",
            "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"
        };

        // --- 1. Leitura dos Dados e Cálculo do Total Anual ---
        
        int producaoTotal = 0; // Item 2: Produção total
        
        System.out.println("--- Leitura da Produção Anual de Sapatos ---");
        System.out.println("Digite a produção para cada mês:");

        for (int i = 0; i < 12; i++) {
            System.out.print("Produção de " + nomesMeses[i] + ": ");
            int producaoDoMes = scanner.nextInt();
            
            producaoMensal[i] = producaoDoMes; // Guarda no array
            producaoTotal = producaoTotal + producaoDoMes; // Acumula no total
        }

        // --- 2. Apresentação do Total e Cálculo da Metade ---
        
        System.out.println("\n--- Produção Total Anual ---");
        System.out.println("A produção total no final do ano foi: " + producaoTotal + " sapatos.");
        
        // Precisamos da metade para o próximo passo.
        // Usamos 'double' para o caso de o total ser ímpar (ex: 101 / 2 = 50.5)
        double metadeDaProducao = (double) producaoTotal / 2.0;
        
        
        // --- 3. Cálculo da Produção Acumulada e Mês da Metade ---
        
        int producaoAcumulada = 0; // Item 1: Produção acumulada
        String mesMetadeProducao = ""; // Item 3: Mês da metade
        boolean metadeAlcancada = false; // "Flag" para marcar quando a metade for encontrada

        System.out.println("\n--- Produção Acumulada (mês a mês) ---");

        for (int i = 0; i < 12; i++) {
            // Soma a produção do mês atual ao acumulado
            producaoAcumulada = producaoAcumulada + producaoMensal[i];
            
            // Item 1: Imprime o valor acumulado para o mês atual
            System.out.println("Até o final de " + nomesMeses[i] + ": " + producaoAcumulada + " sapatos.");

            // Item 3: Verifica se a metade foi alcançada *neste mês*
            // Usamos a 'flag' !metadeAlcancada para garantir que isto só
            // acontece na *primeira* vez que o valor é ultrapassado.
            if (!metadeAlcancada && producaoAcumulada >= metadeDaProducao) {
                mesMetadeProducao = nomesMeses[i];
                metadeAlcancada = true; // Marca como encontrado
            }
        }

        // --- 4. Apresentação do Mês da Metade ---
        
        System.out.println("\n--- Mês de Metade da Produção ---");
        if (producaoTotal > 0) {
            System.out.println("Metade da produção (" + metadeDaProducao + ") foi alcançada no mês de: " + mesMetadeProducao);
        } else {
            System.out.println("Não houve produção durante o ano.");
        }

        scanner.close();
    }  
}
