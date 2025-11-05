public class Operação_aritmetica {
    public static void main(String[] args) {
        try (java.util.Scanner sc = new java.util.Scanner(System.in)) {
            System.out.println("Operações aritméticas básicas");

            System.out.print("Insira o primeiro número: ");
            double num1 = Double.parseDouble(sc.nextLine().trim());

            System.out.print("Insira o segundo número: ");
            double num2 = Double.parseDouble(sc.nextLine().trim());

            System.out.println("Escolha a operação desejada:");
            System.out.println("1 - Adição (+)");
            System.out.println("2 - Subtração (-)");
            System.out.println("3 - Multiplicação (*)");
            System.out.println("4 - Divisão (/)");
            System.out.print("Digite o número da operação (1-4): ");
            int operacao = Integer.parseInt(sc.nextLine().trim());
            double resultado;
            switch (operacao) {
                case 1:
                    resultado = num1 + num2;
                    System.out.printf("Resultado da adição: %.2f + %.2f = %.2f%n", num1, num2, resultado);
                    break;
                case 2:
                    resultado = num1 - num2;
                    System.out.printf("Resultado da subtração: %.2f - %.2f = %.2f%n", num1, num2, resultado);
                    break;
                case 3:
                    resultado = num1 * num2;
                    System.out.printf("Resultado da multiplicação: %.2f * %.2f = %.2f%n", num1, num2, resultado);
                    break;
                case 4:
                    if (num2 != 0) {
                        resultado = num1 / num2;
                        System.out.printf("Resultado da divisão: %.2f / %.2f = %.2f%n", num1, num2, resultado);
                    } else {
                        System.out.println("Erro: Divisão por zero não é permitida.");
                    }
                    break;
                default:
                    System.out.println("Operação inválida. Por favor, escolha uma opção entre 1 e 4.");
            }
        }
    }
}
