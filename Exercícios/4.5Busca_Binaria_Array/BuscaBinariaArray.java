import java.util.Arrays;
import java.util.Scanner;

public class BuscaBinariaArray {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        final int TAMANHO_ARRAY = 5;
        int[] numeros = new int[TAMANHO_ARRAY];

        System.out.println("--- Preenchimento do Array ---");
        System.out.println("Por favor, digite " + TAMANHO_ARRAY + " números inteiros:");

        // 1. Loop para ler os valores para o array
        for (int i = 0; i < numeros.length; i++) {
            System.out.print("Digite o valor " + (i + 1) + ": ");
            numeros[i] = scanner.nextInt();
        }

        // 2. Ler o valor 'x' a ser procurado
        System.out.println("\n--- Busca no Array ---");
        System.out.print("Digite o número (x) que deseja procurar: ");
        int x = scanner.nextInt();

        // 3. Ordenar o array (Pré-requisito para a busca binária)
        // Como pedido, usamos o método 'sort' da classe 'Arrays'
        Arrays.sort(numeros);

        // (Opcional) Mostrar o array ordenado para o utilizador entender o resultado
        System.out.println("O array foi ordenado para a busca: " + Arrays.toString(numeros));

        // 4. Procurar 'x' no array com 'binarySearch'
        // Este método retorna o índice se encontrar, ou um número negativo se não encontrar.
        int indiceEncontrado = Arrays.binarySearch(numeros, x);

        // 5. Imprimir o resultado
        if (indiceEncontrado >= 0) {
            // Se o 'indiceEncontrado' for 0 ou maior, o número foi encontrado
            System.out.println("--- Resultado ---");
            System.out.println("O valor " + x + " foi encontrado!");
            System.out.println("Ele está na posição (índice) " + indiceEncontrado + " do array *ordenado*.");
        } else {
            // Se for negativo, não foi encontrado
            System.out.println("--- Resultado ---");
            System.out.println("O valor " + x + " não foi encontrado no array.");
            System.out.println("(Retorno do binarySearch: " + indiceEncontrado + ")");
        }

        scanner.close();
    }
}
