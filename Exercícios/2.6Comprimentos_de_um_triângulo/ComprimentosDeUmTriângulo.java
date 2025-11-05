import java.util.Locale;
import java.util.Scanner;

/**
 * Solução para o Exercício 2.6:
 * Lê os três lados de um triângulo e o classifica de acordo com seus ângulos.
 */
public class ComprimentosDeUmTriângulo {

    public static void main(String[] args) {
        
        // Usar Locale.US para garantir que o separador decimal seja o ponto (.)
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

        System.out.println("--- Classificador de Triângulos ---");
        System.out.print("Digite o comprimento do primeiro lado: ");
        double s1 = scanner.nextDouble();

        System.out.print("Digite o comprimento do segundo lado: ");
        double s2 = scanner.nextDouble();

        System.out.print("Digite o comprimento do terceiro lado: ");
        double s3 = scanner.nextDouble();

        // 1. Identificar o lado maior (Lm) e os outros dois (La, Lb)
        // Usamos Math.max para encontrar o maior valor de forma eficiente.
        double lm = Math.max(s1, Math.max(s2, s3));
        double la, lb;

        if (lm == s1) {
            la = s2;
            lb = s3;
        } else if (lm == s2) {
            la = s1;
            lb = s3;
        } else { // lm == s3
            la = s1;
            lb = s2;
        }

        // 2. Aplicar as regras de classificação da imagem
        
        // Regra 1: Verificar a condição de existência (desigualdade triangular)
        // Se o lado maior for maior ou igual à soma dos outros dois, não é um triângulo.
        if (lm >= la + lb) {
            System.out.println("Classificação: não é um triângulo");
        } else {
            // Se chegou aqui, é um triângulo. Vamos classificá-lo.
            
            // Calcular os quadrados para aplicar as outras regras
            double lm_quadrado = lm * lm;
            double la_lb_soma_quadrados = (la * la) + (lb * lb);

            // Definir uma pequena tolerância (EPSILON) para comparar números double.
            // Comparar doubles com "==" direto pode falhar devido a erros de precisão.
            final double EPSILON = 1e-9;

            // Regra 2: Triângulo Rectângulo
            if (Math.abs(lm_quadrado - la_lb_soma_quadrados) < EPSILON) {
                System.out.println("Classificação: triângulo rectângulo");
            
            // Regra 3: Triângulo Obtuso
            } else if (lm_quadrado > la_lb_soma_quadrados) {
                System.out.println("Classificação: triângulo obtuso");
            
            // Regra 4: Triângulo Agudo
            } else { // (lm_quadrado < la_lb_soma_quadrados)
                System.out.println("Classificação: triângulo agudo");
            }
        }

        // Fechar o scanner para liberar recursos
        scanner.close();
    }
}