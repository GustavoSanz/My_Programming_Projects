import java.util.Scanner;
public class Menu {
   public static void main(String[] args) throws InterruptedException {
    int opcao = 0;
    try (Scanner scanner = new Scanner(System.in)) {
      do {
        System.out.println("======MENU JAVA======");
        System.out.println("Escolha uma opção:");
        System.out.println("1 - Inserir");
        System.out.println("2 - Remover");
        System.out.println("3 - Consultar");
        System.out.println("4 - Gravar");
        System.out.println("5 - Sair");
        System.out.print("Digite o número da opção desejada: ");
        
        if (scanner.hasNextInt()){
            opcao = scanner.nextInt();
        } else {
            System.err.println("ERRO: Entrada inválida. Por favor, insira um número entre 1 e 5.");
            scanner.next(); // Limpa a entrada inválida
            continue; // Volta ao início do loop
        }
        
        switch (opcao) {
        case 1:
            System.out.println("Opção 1 - Inserir.");
            System.out.print("Insira os dados:");
            String dados = scanner.next();
            break;
            case 2:
            System.out.println("Opção 2 - Remover.");
            System.out.print("Insira os dados para remoção:");
            String dadosRemover = scanner.next();
            break;
            case 3:
            System.out.println("Opção 3 - Consultar.");
            System.out.println("Consultando dados...");


        /*COLOCAR DEPOIS UM ARRAY PARA ARMAZENAR OS DADOS E ASSIM PODER CONSULTAR PRIMEIRO UMA CONDIÇÃO POR CAUSA DO CASO 4 GRAVAR*/
            break;
            case 4:
            System.out.println("Opção 4 - Gravar.");
            System.out.println("Gravando dados...");
            Thread.sleep(3000); // Simula o tempo de gravação
            System.out.println("Dados gravados com sucesso!");
            break;
            case 5:
            System.out.println("Saindo do programa. Até logo!");
            scanner.close();
            break;
            default:
            System.err.println("Opção inválida. Por favor, escolha uma opção entre 1 e 5.");
        }
    }while (opcao != 5);
    }   
    }
    } 
    
