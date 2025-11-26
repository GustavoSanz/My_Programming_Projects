// Importa as classes de IO (Input/Output), incluindo as de dados binários.
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

// Declaração da classe principal.
public class Menu_CRUD_2 {

    // Define o nome do ficheiro binário como uma constante.
    private static final String NOME_FICHEIRO = "dados_bin.txt";

    // O método main é o ponto de entrada.
    // 'throws Exception' é usado para apanhar IOExceptions, EOFExceptions, etc.
    public static void main(String[] args) throws Exception {

        // Cria uma única instância do Scanner para ler a entrada do utilizador.
        try (Scanner scanner = new Scanner(System.in)) {
            // Variável para guardar a escolha do utilizador.
            int opcao = 0;

            // Loop 'do-while' para manter o menu a correr até o utilizador escolher sair (opção 6).
            do {
                // Imprime o menu de opções.
                System.out.println("\n====== MENU CRUD - FICHEIRO BINÁRIO ======");
                System.out.println("Escolha uma opção:");
                System.out.println("1 - Inserir Registo");
                System.out.println("2 - Listar Todos os Registos");
                System.out.println("3 - Procurar Registo por ID");
                System.out.println("4 - Atualizar Registo por ID");
                System.out.println("5 - Remover Registo por ID");
                System.out.println("6 - Sair");
                System.out.print("Digite o número da opção desejada: ");

                // Verifica se a entrada é um número inteiro.
                if (scanner.hasNextInt()) {
                    // Se for, lê o número.
                    opcao = scanner.nextInt();
                    // Limpa o buffer do scanner (consome o "Enter" pendente).
                    scanner.nextLine();
                } else {
                    // Se a entrada não for um número.
                    System.err.println("ERRO: Entrada inválida. Por favor, insira um número entre 1 e 6.");
                    // Limpa a entrada inválida do scanner.
                    scanner.next();
                    // Volta ao início do loop sem executar o switch.
                    continue;
                }

                // Estrutura 'switch' para executar a ação correspondente à opção escolhida.
                switch (opcao) {
                    case 1:
                        // Chama o método para inserir um novo registo.
                        inserirRegisto(scanner);
                        break;
                    case 2:
                        // Chama o método para listar todos os registos.
                        listarRegistos();
                        break;
                    case 3:
                        // Chama o método para procurar um registo.
                        procurarRegisto(scanner);
                        break;
                    case 4:
                        // Chama o método para atualizar um registo.
                        atualizarRegisto(scanner);
                        break;
                    case 5:
                        // Chama o método para remover um registo.
                        removerRegisto(scanner);
                        break;
                    case 6:
                        // Imprime mensagem de saída.
                        System.out.println("Saindo do programa. Até logo!");
                        break;
                    default:
                        // Mensagem para opções fora do intervalo 1-6.
                        System.err.println("Opção inválida. Por favor, escolha uma opção entre 1 e 6.");
                }
            // O loop continua enquanto a opção for diferente de 6.
            } while (opcao != 6);
        } // O 'try-with-resources' fecha o 'scanner' aqui automaticamente.
    }

    /**
     * Método para Inserir (Create) um novo registo no ficheiro binário.
     */
    private static void inserirRegisto(Scanner scanner) {
        System.out.println("--- Inserir Novo Registo ---");

        // Usamos try-with-resources para garantir que o DataOutputStream é fechado.
        // FileOutputStream(..., true) abre o ficheiro em modo 'append' (adição).
        try (DataOutputStream dos = new DataOutputStream(
                                     new BufferedOutputStream(
                                     new FileOutputStream(NOME_FICHEIRO, true)))) {
            
            // Pede os dados ao utilizador.
            System.out.print("Insira o ID (número inteiro): ");
            int id = -1;
            
            // Validação para garantir que o ID é um número
            while (true) {
                try {
                    id = Integer.parseInt(scanner.nextLine());
                    break; // Sai do loop se a conversão for bem-sucedida
                } catch (NumberFormatException e) {
                    System.err.println("ID inválido. Por favor, insira um número inteiro.");
                    System.out.print("Insira o ID: ");
                }
            }

            System.out.print("Insira o Nome: ");
            String nome = scanner.nextLine();
            System.out.print("Insira o Email: ");
            String email = scanner.nextLine();

            // Grava os dados no ficheiro binário, na ordem correta.
            dos.writeInt(id);      // Escreve o ID como um inteiro binário
            dos.writeUTF(nome);    // Escreve o Nome como uma string em formato UTF
            dos.writeUTF(email);   // Escreve o Email como uma string em formato UTF

            System.out.println("Registo inserido com sucesso!");

        } catch (IOException e) {
            System.err.println("Erro ao escrever no ficheiro: " + e.getMessage());
        }
    }

    /**
     * Método para Listar (Read) todos os registos do ficheiro binário.
     */
    private static void listarRegistos() {
        System.out.println("--- Listar Todos os Registos ---");
        boolean encontrouRegistos = false;

        // Bloco try-with-resources para abrir o ficheiro para leitura binária.
        try (DataInputStream dis = new DataInputStream(
                                   new BufferedInputStream(
                                   new FileInputStream(NOME_FICHEIRO)))) {
            
            // Loop infinito para ler registos.
            // Este loop só será interrompido quando uma EOFException (Fim do Ficheiro)
            // for apanhada. Esta é a forma padrão de ler ficheiros DataInputStream.
            while (true) {
                // Lê os dados na *mesma ordem* em que foram escritos.
                int id = dis.readInt();
                String nome = dis.readUTF();
                String email = dis.readUTF();

                // Imprime os dados lidos.
                System.out.println("ID: " + id + ", Nome: " + nome + ", Email: " + email);
                encontrouRegistos = true;
            }
            
        // Apanha a exceção de Fim de Ficheiro (EOFException).
        // Isto *não* é um erro; é a forma esperada de parar o loop 'while(true)'.
        } catch (EOFException e) {
            // Fim do ficheiro alcançado.
            if (!encontrouRegistos) {
                System.out.println("O ficheiro está vazio. Nenhum registo encontrado.");
            } else {
                System.out.println("--- Fim da Lista ---");
            }
        // Captura a exceção caso o ficheiro 'dados_bin.txt' ainda não exista.
        } catch (FileNotFoundException e) {
            System.err.println("O ficheiro '" + NOME_FICHEIRO + "' ainda não existe. Insira um registo primeiro.");
        // Captura outros erros de Leitura/Escrita.
        } catch (IOException e) {
            System.err.println("Erro ao ler o ficheiro: " + e.getMessage());
        }
    }

    /**
     * Método para Procurar (Read) um registo específico por ID.
     */
    private static void procurarRegisto(Scanner scanner) {
        System.out.println("--- Procurar Registo por ID ---");
        System.out.print("Insira o ID do registo a procurar: ");
        int idProcurar = -1;
        
        // Validação do ID
        while (true) {
            try {
                idProcurar = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.err.println("ID inválido. Por favor, insira um número inteiro.");
                System.out.print("Insira o ID: ");
            }
        }
        
        boolean encontrado = false;

        // Tenta abrir o ficheiro para leitura binária.
        try (DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(NOME_FICHEIRO)))) {
            
            // Loop 'while(true)' para ler o ficheiro até ao fim (EOFException).
            while (true) {
                // Lê os campos de um registo.
                int id = dis.readInt();
                String nome = dis.readUTF();
                String email = dis.readUTF();

                // Compara o ID lido com o ID procurado.
                if (id == idProcurar) {
                    System.out.println("Registo encontrado: ");
                    System.out.println("ID: " + id + ", Nome: " + nome + ", Email: " + email);
                    encontrado = true;
                    // Para a procura (assumindo que IDs são únicos).
                    break;
                }
            }
        // Apanha o Fim do Ficheiro.
        } catch (EOFException e) {
            // Se o loop terminou (fim do ficheiro) e 'encontrado' é falso.
            if (!encontrado) {
                System.out.println("Nenhum registo encontrado com o ID: " + idProcurar);
            }
        // Trata o caso do ficheiro não existir.
        } catch (FileNotFoundException e) {
            System.err.println("O ficheiro '" + NOME_FICHEIRO + "' ainda não existe.");
        // Trata outros erros de IO.
        } catch (IOException e) {
            System.err.println("Erro ao ler o ficheiro: " + e.getMessage());
        }
    }

    /**
     * Método para Atualizar (Update) um registo binário.
     * Estratégia: Ler do ficheiro original (DataInputStream) e escrever
     * num ficheiro temporário (DataOutputStream).
     */
    private static void atualizarRegisto(Scanner scanner) {
        System.out.println("--- Atualizar Registo por ID ---");
        System.out.print("Insira o ID do registo a atualizar: ");
        int idAtualizar = -1;

        // Validação do ID
        while (true) {
            try {
                idAtualizar = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.err.println("ID inválido. Por favor, insira um número inteiro.");
                System.out.print("Insira o ID: ");
            }
        }

        // Define o ficheiro original e o ficheiro temporário.
        File ficheiroOriginal = new File(NOME_FICHEIRO);
        File ficheiroTemp = new File("dados_bin_temp.txt");

        boolean encontrado = false;

        // Bloco 'try' para abrir os dois ficheiros:
        // 'dis' para ler o original, 'dos' para escrever no temporário.
        try (DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(ficheiroOriginal)));
             DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(ficheiroTemp)))) {

            // Loop de leitura do ficheiro original.
            while (true) {
                // Lê um registo completo.
                int id = dis.readInt();
                String nome = dis.readUTF();
                String email = dis.readUTF();

                // Compara o ID lido com o ID a atualizar.
                if (id == idAtualizar) {
                    encontrado = true;
                    // Pede os novos dados.
                    System.out.println("Registo encontrado. Insira os novos dados:");
                    System.out.print("Insira o novo Nome: ");
                    String novoNome = scanner.nextLine();
                    System.out.print("Insira o novo Email: ");
                    String novoEmail = scanner.nextLine();

                    // Escreve o registo *atualizado* no ficheiro temporário.
                    dos.writeInt(idAtualizar);
                    dos.writeUTF(novoNome);
                    dos.writeUTF(novoEmail);
                } else {
                    // Se *não* for o registo, copia os dados originais para o ficheiro temp.
                    dos.writeInt(id);
                    dos.writeUTF(nome);
                    dos.writeUTF(email);
                }
            }
        // Fim do ficheiro original.
        } catch (EOFException e) {
            // Fim do ficheiro, o 'try' fecha os 'streams' (dis e dos).
        } catch (FileNotFoundException e) {
            System.err.println("O ficheiro '" + NOME_FICHEIRO + "' ainda não existe.");
            return; // Sai do método
        } catch (IOException e) {
            System.err.println("Erro de IO: " + e.getMessage());
            return; // Sai do método
        }

        // --- Lógica de substituição dos ficheiros (Igual ao Exercício 1) ---
        
        // Se, após ler o ficheiro todo, o registo não foi encontrado.
        if (!encontrado) {
            System.out.println("Nenhum registo encontrado com o ID: " + idAtualizar);
            // Apaga o ficheiro temporário, pois não é necessário.
            ficheiroTemp.delete();
        } else {
            // Se o registo foi atualizado com sucesso:
            // 1. Apaga o ficheiro original.
            if (!ficheiroOriginal.delete()) {
                System.err.println("Erro: Não foi possível apagar o ficheiro original.");
                return;
            }
            // 2. Renomeia o ficheiro temporário para o nome do original.
            if (!ficheiroTemp.renameTo(ficheiroOriginal)) {
                System.err.println("Erro: Não foi possível renomear o ficheiro temporário.");
                return;
            }
            // Informa o utilizador.
            System.out.println("Registo atualizado com sucesso!");
        }
    }

    /**
     * Método para Remover (Delete) um registo binário.
     * Estratégia: Copia todos os registos para o temp, *exceto* o que vai ser removido.
     */
    private static void removerRegisto(Scanner scanner) {
        System.out.println("--- Remover Registo por ID ---");
        System.out.print("Insira o ID do registo a remover: ");
        int idRemover = -1;

        // Validação do ID
        while (true) {
            try {
                idRemover = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.err.println("ID inválido. Por favor, insira um número inteiro.");
                System.out.print("Insira o ID: ");
            }
        }

        // Define os ficheiros (original e temporário).
        File ficheiroOriginal = new File(NOME_FICHEIRO);
        File ficheiroTemp = new File("dados_bin_temp.txt");

        boolean encontrado = false;

        // Abre o original (leitura) e o temporário (escrita).
        try (DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(ficheiroOriginal)));
             DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(ficheiroTemp)))) {

            // Loop de leitura do ficheiro original.
            while (true) {
                // Lê um registo.
                int id = dis.readInt();
                String nome = dis.readUTF();
                String email = dis.readUTF();

                // Verifica se é a linha a remover.
                if (id == idRemover) {
                    // Se for, marca como encontrado...
                    encontrado = true;
                    // ...e *não* faz nada (não escreve os dados no ficheiro temp).
                } else {
                    // Se *não* for a linha a remover, copia-a para o ficheiro temp.
                    dos.writeInt(id);
                    dos.writeUTF(nome);
                    dos.writeUTF(email);
                }
            }
        // Fim do ficheiro original.
        } catch (EOFException e) {
            // Fim do ficheiro.
        } catch (FileNotFoundException e) {
            System.err.println("O ficheiro '" + NOME_FICHEIRO + "' ainda não existe.");
            return;
        } catch (IOException e) {
            System.err.println("Erro de IO: " + e.getMessage());
            return;
        }

        // --- Lógica de substituição dos ficheiros ---
        
        // Se o registo foi encontrado e removido (ignorado).
        if (encontrado) {
            // 1. Apaga o ficheiro original.
            if (!ficheiroOriginal.delete()) {
                System.err.println("Erro: Não foi possível apagar o ficheiro original.");
                return;
            }
            // 2. Renomeia o temporário para o nome do original.
            if (!ficheiroTemp.renameTo(ficheiroOriginal)) {
                System.err.println("Erro: Não foi possível renomear o ficheiro temporário.");
                return;
            }
            System.out.println("Registo removido com sucesso!");
        } else {
            // Se não encontrou o ID, informa o utilizador.
            System.out.println("Nenhum registo encontrado com o ID: " + idRemover);
            // Apaga o ficheiro temporário, que é uma cópia exata do original.
            ficheiroTemp.delete();
        }
    }
}