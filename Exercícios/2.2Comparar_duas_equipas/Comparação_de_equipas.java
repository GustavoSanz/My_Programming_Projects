import java.util.Scanner;

public class Comparação_de_equipas {
   public static void main(String[] args) {
       try (Scanner entrada = new Scanner(System.in)) {
        //EQUIPA 1
           System.out.println("=== Dados da Equipa 1 ===");
           System.out.print("Vitórias: ");
             int vitorias1 = entrada.nextInt();

             System.out.print("Empates: ");
                int empates1 = entrada.nextInt();

                System.out.print("Derrotas: ");
                   int derrotas1 = entrada.nextInt();

                   int totalJogos1 = vitorias1 + empates1 + derrotas1;
                   int pontos1 = vitorias1 * 3 + empates1;

             //EQUIPA 2

                System.out.println("=== Dados da Equipa 2 ===");
                System.out.print("Vitórias: ");
                   int vitorias2 = entrada.nextInt();

                   System.out.print("Empates: ");
                      int empates2 = entrada.nextInt();

                      System.out.print("Derrotas: ");
                         int derrotas2 = entrada.nextInt();

                         int totalJogos2 = vitorias2 + empates2 + derrotas2;
                         int pontos2 = vitorias2 * 3 + empates2;

                //COMPARAÇÃO
                            System.out.println("=== Comparação das Equipas ===");
                            System.out.printf("Equipa 1: %d jogos, %d pontos.%n", totalJogos1, pontos1);
                            System.out.printf("Equipa 2: %d jogos, %d pontos.%n", totalJogos2, pontos2);
        
                            if (pontos1 > pontos2) {
                                System.out.println("A Equipa 1 tem melhor desempenho.");
                            } else if (pontos2 > pontos1) {
                                System.out.println("A Equipa 2 tem melhor desempenho.");
                            } else {
                                System.out.println("Ambas as equipas têm o mesmo desempenho.");
                            }
       }
   }
}
