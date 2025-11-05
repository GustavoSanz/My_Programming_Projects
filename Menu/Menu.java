import java.util.ArrayList;
import java.util.Scanner;
public class Menu {
   public static void main(String[] args) throws InterruptedException {
    int opcao = 0;
    ArrayList<String> dadosArmazenados = new ArrayList<>(); // Nova lista Array para armazenar os dados inseridos
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
        scanner.nextLine(); // Limpa o buffer do scanner

        switch (opcao) {
        case 1:
            System.out.println("Opção 1 - Inserir.");
            System.out.print("Insira os dados (número ou palavra):");
           // Ler qualquer texto que o utilizador digitar
           String dados = scanner.nextLine();
           //Adiciona os dados inseridos ao array
              dadosArmazenados.add(dados);
              System.out.println("Dado '" + dados + "' inserido com sucesso.");
            break;
            case 2:
            System.out.println("Opção 2 - Remover.");
            System.out.print("Insira os dados para remoção:");
            String dadosRemover = scanner.nextLine();
            // Verifica se o dado existe no array e remove
            boolean removido = dadosArmazenados.remove(dadosRemover);

            if (removido) {
                System.out.println("Dado '" + dadosRemover + "' removido com sucesso.");
            } else {
                System.out.println("Dado '" + dadosRemover + "' não encontrado.");
            }
            break;
            case 3:
            System.out.println("Opção 3 - Consultar.");
            System.out.println("Consultando dados...");
            //Verifica se há dados armazenados
            if (dadosArmazenados.isEmpty()) {
                System.out.println("Nenhum dado armazenado.");
            } else {
                System.out.println("---Dados armazenados---");
                // Percorre e exibe todos os dados armazenados
                for (int i = 0; i < dadosArmazenados.size(); i++) {
                                System.out.println((i + 1) + ": " + dadosArmazenados.get(i));
                            }
                            System.out.println("------------------------------------");
                        }
            

        
            break;
            case 4:
            System.out.println("Opção 4 - Gravar.");
           //Simula o processo de gravação  mostrando o que seria guardado
           if (dadosArmazenados.isEmpty()) {
                            System.out.println("Não há dados novos para gravar.");
                        } else {
                            System.out.println("Gravando os seguintes dados (simulação)...");
                            for (String dado : dadosArmazenados) {
                                System.out.println("-> " + dado);
                            }
                            System.out.println("Dados gravados com sucesso!");
                        }
           
            /*  System.out.println("Gravando dados...");
            Thread.sleep(3000); // Simula o tempo de gravação
            System.out.println("Dados gravados com sucesso!"); */
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
    
