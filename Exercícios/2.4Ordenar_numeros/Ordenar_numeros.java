public class Ordenar_numeros {
    public static void main(String[] args) {
        try (java.util.Scanner sc = new java.util.Scanner(System.in)) {
            System.out.println("Ordenar três números em ordem crescente");

            System.out.print("Insira o primeiro número: ");
            double num1 = Double.parseDouble(sc.nextLine().trim());

            System.out.print("Insira o segundo número: ");
            double num2 = Double.parseDouble(sc.nextLine().trim());

            System.out.print("Insira o terceiro número: ");
            double num3 = Double.parseDouble(sc.nextLine().trim());

            double primeiro, segundo, terceiro;

            if (num1 <= num2 && num1 <= num3) {
                primeiro = num1;
                if (num2 <= num3) {
                    segundo = num2;
                    terceiro = num3;
                } else {
                    segundo = num3;
                    terceiro = num2;
                }
            } else if (num2 <= num1 && num2 <= num3) {
                primeiro = num2;
                if (num1 <= num3) {
                    segundo = num1;
                    terceiro = num3;
                } else {
                    segundo = num3;
                    terceiro = num1;
                }
            } else {
                primeiro = num3;
                if (num1 <= num2) {
                    segundo = num1;
                    terceiro = num2;
                } else {
                    segundo = num2;
                    terceiro = num1;
                }
            }

            System.out.printf("Números em ordem crescente: %.2f, %.2f, %.2f%n", primeiro, segundo, terceiro);
        }
    }
}
