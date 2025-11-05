import java.util.Arrays;
import java.util.Scanner;

public class BuscaBinariaNomes {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        final int TAMANHO_ARRAY = 5;
        // 1. Mudar o tipo do array para String[]
        String[] nomes = new String[TAMANHO_ARRAY]; 

        System.out.println("--- Preenchimento do Array de Nomes ---");
        System.out.println("Por favor, digite " + TAMANHO_ARRAY + " nomes:");

        // 2. Loop para ler os nomes (Strings)
        for (int i = 0; i < nomes.length; i++) {
            System.out.print("Digite o nome " + (i + 1) + ": ");
            // 3. Mudar para scanner.nextLine() para ler o texto
            nomes[i] = scanner.nextLine(); 
        }

        // 4. Ler o nome 'x' a ser procurado (também String)
        System.out.println("\n--- Busca no Array ---");
        System.out.print("Digite o nome (x) que deseja procurar: ");
        String nomeProcurado = scanner.nextLine(); // Usar nextLine()

        // 5. Ordenar o array (ordena alfabeticamente)
        Arrays.sort(nomes);

        // (Opcional) Mostrar o array ordenado
        System.out.println("O array foi ordenado alfabeticamente: " + Arrays.toString(nomes));

        // 6. Procurar 'x' no array com 'binarySearch'
        int indiceEncontrado = Arrays.binarySearch(nomes, nomeProcurado);

        // 7. Imprimir o resultado (a lógica é a mesma)
        if (indiceEncontrado >= 0) {
            System.out.println("--- Resultado ---");
            System.out.println("O nome \"" + nomeProcurado + "\" foi encontrado!");
            System.out.println("Ele está na posição (índice) " + indiceEncontrado + " do array *ordenado*.");
        } else {
            System.out.println("--- Resultado ---");
            System.out.println("O nome \"" + nomeProcurado + "\" não foi encontrado no array.");
        }

        scanner.close();
    }
}
