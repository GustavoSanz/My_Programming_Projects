public class Numeros_maiores_3 {
    public static void main(String[] args) {
        try (java.util.Scanner sc = new java.util.Scanner(System.in)) {
            System.out.println("Determinar o maior de três números");

            System.out.print("Insira o primeiro número: ");
            double num1 = Double.parseDouble(sc.nextLine().trim());

            System.out.print("Insira o segundo número: ");
            double num2 = Double.parseDouble(sc.nextLine().trim());

            System.out.print("Insira o terceiro número: ");
            double num3 = Double.parseDouble(sc.nextLine().trim());

            double maior;

            if (num1 >= num2 && num1 >= num3) {
                maior = num1;
            } else if (num2 >= num1 && num2 >= num3) {
                maior = num2;
            } else {
                maior = num3;
            }

            System.out.printf("O maior número é: %.2f%n", maior);
        }
    }
}