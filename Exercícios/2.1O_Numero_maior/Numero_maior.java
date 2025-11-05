public class Numero_maior {
    public static void main(String[] args) {
        try (java.util.Scanner sc = new java.util.Scanner(System.in)) {
            System.out.println("Número Maior entre Dois Números");
            System.out.print("Insira o primeiro número: ");
            double num1 = Double.parseDouble(sc.nextLine().trim());
            System.out.print("Insira o segundo número: ");
            double num2 = Double.parseDouble(sc.nextLine().trim());
            
            double maior = num1;
            if (num2 > maior) {
                maior = num2;
            }
            
    
            System.out.printf("O maior número é: %.2f%n", maior);
        }
    }
}
