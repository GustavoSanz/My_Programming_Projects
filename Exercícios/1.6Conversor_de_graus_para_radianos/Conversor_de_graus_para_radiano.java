public class Conversor_de_graus_para_radiano {
    public static void main(String[] args) {
        try (java.util.Scanner sc = new java.util.Scanner(System.in)) {
            System.out.println("Conversor de Graus para Radianos");
            System.out.print("Insira o valor em graus a converter: ");
            double graus = Double.parseDouble(sc.nextLine().trim());
            
            double radianos = graus * (Math.PI / 180);
            
            System.out.printf("%.2f graus correspondem a %.4f radianos.%n", graus, radianos);
        }
    }
}
