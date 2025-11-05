import java.util.Scanner;
public class Pontuação_clubes {
public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
        System.out.println("Pontuação dos Clubes");
        System.out.print("Insira o nome do clube: ");
        String clube = sc.nextLine().trim();
        
        System.out.print("Insira o número de vitórias: ");
        int vitorias = Integer.parseInt(sc.nextLine().trim());
        
        System.out.print("Insira o número de empates: ");
        int empates = Integer.parseInt(sc.nextLine().trim());
        
        System.out.println("Inserir o número de derrotas: ");
        int derrotas = Integer.parseInt(sc.nextLine().trim());
        
        int totalJogos = vitorias + empates + derrotas;
        System.out.printf("O clube %s jogou %d partidas.%n", clube, totalJogos);
        
        int pontos = vitorias * 3 + empates + derrotas * 0;
        System.out.printf("O clube %s tem %d pontos.%n", clube, pontos);
    }
}    
}
