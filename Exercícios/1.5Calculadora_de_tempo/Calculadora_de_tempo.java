import java.util.Scanner;

public class Calculadora_de_tempo {
   public static void main(String[] args) {
       try (Scanner sc = new Scanner(System.in)) {
           System.out.println("Calculadora de Tempo");
           System.out.print("Insira o número de segundos a converter: ");
           int totalSegundos = Integer.parseInt(sc.nextLine().trim());
           
           int horas = totalSegundos / 3600;
           int minutos = (totalSegundos % 3600) / 60;
           int segundos = totalSegundos % 60;
           
           System.out.printf("%d segundos correspondem a %d horas, %d minutos e %d segundos.%n",
                   totalSegundos, horas, minutos, segundos);
       }       
}
}