public class Conversor_de_coordenadas_cartesianas {
    public static void main(String[] args) {
        try (java.util.Scanner sc = new java.util.Scanner(System.in)) {
            System.out.println("Conversor de Coordenadas Cartesianas para Polares");
            System.out.print("Insira a coordenada X: ");
            double x = Double.parseDouble(sc.nextLine().trim());
            System.out.print("Insira a coordenada Y: ");
            double y = Double.parseDouble(sc.nextLine().trim());
            
            double raio = Math.sqrt(x * x + y * y);
            double angulo = Math.toDegrees(Math.atan2(y, x));
            
            System.out.printf("As coordenadas polares são: Raio = %.2f, Ângulo = %.2f graus.%n", raio, angulo);
        }
    }
}
