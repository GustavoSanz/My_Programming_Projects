import java.util.Scanner;

public class Conversor{

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		final double FACTOR = 1.60934; // 1 milha = 1.60934 km

		while (true) {
			System.out.print("Insira milhas (ou 'sair' para terminar): ");
			String line = sc.nextLine().trim();
			if (line.equalsIgnoreCase("sair") || line.equalsIgnoreCase("exit")) {
				System.out.println("Programa terminado.");
				break;
			}

			// aceitar vírgula como separador decimal
			line = line.replace(',', '.');

			double miles;
			try {
				miles = Double.parseDouble(line);
			} catch (NumberFormatException e) {
				System.out.println("Entrada inválida. Por favor introduza um número válido.");
				continue;
			}

			double km = miles * FACTOR;
			System.out.printf("%.2f milhas = %.2f km%n", miles, km);

			System.out.print("Deseja converter outra? (s/n): ");
			String resp = sc.nextLine().trim();
			if (resp.equalsIgnoreCase("n") || resp.equalsIgnoreCase("não") || resp.equalsIgnoreCase("nao")) {
				System.out.println("Programa terminado.");
				break;
			}
		}

		sc.close();
	}

}