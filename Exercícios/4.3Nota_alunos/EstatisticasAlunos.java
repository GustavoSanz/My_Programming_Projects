import java.util.Locale;
import java.util.Scanner;

public class EstatisticasAlunos {
 public static void main(String[] args) {
        
        // Usar Locale.US para garantir o ponto (.) como separador decimal
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

        System.out.println("--- Análise Estatística de Notas da Turma ---");
        System.out.print("Quantos alunos tem a turma? ");
        int nAlunos = scanner.nextInt();

        // Se o número de alunos for 0 ou negativo, não há o que fazer.
        if (nAlunos <= 0) {
            System.out.println("Número de alunos inválido. O programa terminará.");
            scanner.close();
            return; // Termina o método main
        }

        // --- 1. Inicialização das Variáveis ---
        
        // Array para guardar todas as notas (necessário para o item 7)
        double[] notas = new double[nAlunos]; 

        // Variáveis para as estatísticas
        double notaMaxima = Double.NEGATIVE_INFINITY; // Inicia com o menor valor possível
        double notaMinima = Double.POSITIVE_INFINITY; // Inicia com o maior valor possível
        int numNegativas = 0;
        double somaTotal = 0;
        int numPositivas = 0;
        double somaPositivas = 0;

        
        // --- 2. Primeira Passagem: Leitura e Cálculos Iniciais ---
        
        System.out.println("Por favor, insira as " + nAlunos + " notas (0-20).");
        System.out.println("Use PONTO (.) como separador decimal (ex: 12.5).");

        for (int i = 0; i < nAlunos; i++) {
            
            double nota;
            // Loop de validação (opcional, mas boa prática)
            while (true) {
                System.out.print("Digite a nota do aluno " + (i + 1) + ": ");
                nota = scanner.nextDouble();
                if (nota >= 0 && nota <= 20) {
                    break; // Sai do 'while' se a nota for válida
                } else {
                    System.out.println("Nota inválida. Por favor, insira um valor entre 0 e 20.");
                }
            }

            // Armazena a nota no array
            notas[i] = nota;

            // Atualiza as estatísticas
            somaTotal += nota; // (para item 5)
            
            if (nota > notaMaxima) notaMaxima = nota; // (para item 1)
            if (nota < notaMinima) notaMinima = nota; // (para item 2)

            if (nota < 10) {
                numNegativas++; // (para item 3)
            } else {
                numPositivas++; // (para item 6)
                somaPositivas += nota; // (para item 6)
            }
        }

        // --- 3. Cálculos Pós-Leitura ---

        // Item 5: Média das notas
        double mediaTotal = somaTotal / nAlunos;
        
        // Item 4: Percentagem de negativas
        double percNegativas = ((double) numNegativas / nAlunos) * 100.0;
        
        // Item 6: Média das notas positivas
        double mediaPositivas = 0.0; // Valor padrão
        if (numPositivas > 0) {
            mediaPositivas = somaPositivas / numPositivas;
        }

        
        // --- 4. Segunda Passagem: Contar notas acima da média ---
        
        // Item 7: Nº de notas superiores à média
        int numAcimaMedia = 0;
        
        // Usamos um loop "for-each" para percorrer o array
        for (double nota : notas) {
            if (nota > mediaTotal) {
                numAcimaMedia++;
            }
        }

        // --- 5. Apresentação dos Resultados ---
        
        System.out.println("\n--- Resultados Estatísticos ---");
        // System.out.printf é usado para formatar os números decimais
        // "%.2f" significa "formatar como decimal com 2 casas decimais"
        // "%n" significa "nova linha"
        System.out.printf("- Nota máxima: %.2f%n", notaMaxima);
        System.out.printf("- Nota mínima: %.2f%n", notaMinima);
        System.out.println("- Nº de negativas (<10): " + numNegativas);
        System.out.printf("- Percentagem de negativas: %.2f%%%n", percNegativas); // %% imprime um %
        System.out.printf("- Média das notas: %.2f%n", mediaTotal);

        if (numPositivas > 0) {
            System.out.printf("- Média das notas positivas (>=10): %.2f%n", mediaPositivas);
        } else {
            System.out.println("- Média das notas positivas (>=10): (Não houve notas positivas)");
        }
        
        System.out.println("- Nº de notas superiores à média: " + numAcimaMedia);

        scanner.close();
    }   
}
