// Importa as classes necessárias para lidar com ficheiros (IO) e para ler a entrada do utilizador (Scanner).
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
public class Menu_CRUD {
    private static final String ARQUIVO_DADOS = "dados.txt"; // Nome do arquivo para armazenar os dados

    // O método main é o ponto de entrada do programa.
    // O 'throws IOException' indica que os métodos de ficheiro podem lançar exceções
   public static void main(String[] args) throws IOException {
    int opcao = 0;
    try (Scanner scanner = new Scanner(System.in)) {
      do {
        System.out.println("======MENU JAVA======");
        System.out.println("Escolha uma opção:");
        System.out.println("1 - Inserir Registo");
        System.out.println("2 - Remover Registo");
        System.out.println("3 - Listar Registos");
        System.out.println("4 - Procurar por Registo");
        System.out.println("5 - Atualizar Registo");
        System.out.println("6 - Sair");
        System.out.print("Digite o número da opção desejada: ");

        if (scanner.hasNextInt()) {
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do scanner
        } else {
            System.err.println("ERRO: Entrada inválida. Por favor, insira um número entre 1 e 6.");
            scanner.next(); // Limpa a entrada inválida
            continue; // Volta ao início do loop
        }
        
        switch (opcao) {
        case 1:
        // Chama o método para inserir um novo registo.   
        inserirRegisto(scanner);
            break;
            case 2:
            /* System.out.println("Opção 2 - Remover Registro.");
            System.out.print("Insira os dados para remoção:");
            String dadosRemover = scanner.next(); */

            // Chama o método para remover um registo.
            removerRegisto(scanner);
            break;
            case 3:
            /* System.out.println("Opção 3 - Listar Registros."); */

            // Chama o método para listar os registos.
            listarRegistos();
        
            break;
            case 4:
            /* System.out.println("Opção 4 - Procurar Registro."); */

           // Chama o método para procurar um registo.
            procurarRegisto(scanner);

            break;
            case 5:
            /* System.out.println("Opção 5 - Atualizar Registro."); */

           // Chama o método para atualizar um registo.
            atualizarRegisto(scanner);

            break;
            case 6:
            
            System.out.println("Saindo do programa. Até logo!");
            scanner.close();
            break;
            default:
            System.err.println("Opção inválida. Por favor, escolha uma opção entre 1 e 6.");
        }
    }while (opcao != 6);
    }   
    }
     
    
/**
     * Método para Inserir (Create) um novo registo no ficheiro.
     * Esta operação abre o ficheiro em modo 'append' (adição).
     */

     private static void inserirRegisto(Scanner scanner) throws IOException {
        System.out.println("------INSERIR NOVO REGISTO-----");
        //Pede os dados ao utilizador.
        System.out.print("Insira o ID do registo: ");
        String id = scanner.nextLine();
        System.out.print("Insira o nome: ");
        String nome = scanner.nextLine();
        System.out.print("Insira o email: ");
        String email = scanner.nextLine();

        //Formata a string de dados conforme o requisito (separado por ';') 
        String novoRegisto = (id + ";" + nome + ";" + email);

        //Abre o ficheiro em modo append para adicionar o novo registo.
        //Se o ficheiro não existir, ele será criado.
        //Usei o try-with-resources para garantir que o BufferedWriter seja fechado corretamente.
        
        
        try (FileWriter fw = new FileWriter("dados.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw)) { 

            //Escreve o novo registo no ficheiro.
            out.println(novoRegisto);
            System.out.println("Registo inserido com sucesso!");
        } //Os ficheiros (out, bw, fw) são fechados automaticamente aqui.

     }
/**
     * Método para Listar (Read) todos os registos do ficheiro.
     */
    private static void listarRegistos() throws IOException {
        System.out.println("------LISTAR REGISTOS-----");
        //Blcoco try-with-resources para abrir o ficheiro para leitura
        try (BufferedReader br = new BufferedReader(new FileReader("dados.txt"))){

    //Variavel para ler cada linha
    String linha;
    //Variavel para verificar se o ficheiro estava vazio
    boolean encontrouRegistos = false;
    //Loop while para ler cada linha do ficheiro
    while ((linha = br.readLine()) != null) {
        System.out.println(linha); //Imprime a linha lida
        encontrouRegistos = true;
        }
        if (!encontrouRegistos) {
            System.out.println("Nenhum registo encontrado.");
        }
        //Captura a exceção caso o ficheiro não exista
    } catch (FileNotFoundException e) {
        System.err.println("Arquivo 'dados.txt' não encontrado.");
    } catch (IOException e) { 
        System.err.println("Erro ao ler o arquivo: " + e.getMessage());
    } //O BufferedReader é fechado automaticamente aqui
}

    /**
    *Método para Procurar (Read) um registo específico por ID.   
    */

     private static void procurarRegisto(Scanner scanner) {
        System.out.println("------PROCURAR REGISTO-----");
        System.out.print("Insira o ID do registo a procurar: ");
        String idProcurar = scanner.nextLine();

        //Tenta abrir o ficheiro para leitura
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_DADOS))) {
            String linha;
        boolean encontrado = false;

        //Lê o ficheiro linha por linha
        while ((linha = br.readLine()) !=null) {
            //Divide a linha pelo separador ';'
            String[] campos = linha.split(";");
            //Verifica se a linha tem campos e se o primeiro ID é igual ao ID procurado
            if (campos.length > 0 && campos[0].equals(idProcurar)) {
                //Se encontrado, imprime o registo
                System.out.println("Registo encontrado: " + linha);
                encontrado = true;
                break; //Sai do loop após encontrar o registo
            }
        }
        if (!encontrado) {
            System.out.println("Registo não encontrado. " + idProcurar);
        }
        //Trata o caso de o ficheiro não existir
        } catch (FileNotFoundException e) {
            System.err.println("O ficheiro '" + ARQUIVO_DADOS + "' não foi encontrado.");
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        
     }

     /**
     * Método para Atualizar (Update) um registo.
     * Esta é a operação mais complexa sem usar listas.
     * Estratégia: Ler do ficheiro original e escrever num ficheiro temporário.
     * A linha a ser atualizada é substituída, as outras são copiadas.
     */
    private static void atualizarRegisto(Scanner scanner) throws IOException {
        System.out.println("------ATUALIZAR REGISTO-----");
        System.out.print("Insira o ID do registo a atualizar: ");
        String idAtualizar = scanner.nextLine();

        //Define o ficheiro original e o ficheiro temporário
        File ficheiroOriginal = new File(ARQUIVO_DADOS);
        File ficheiroTemp = new File("dados_temp.txt");

        //Variável para verificar se o registo foi encontrado
        boolean encontrado = false;
        // Bloco 'try' para garantir que os leitores e escritores são fechados.
        // Abre o original para leitura (br) e o temporário para escrita (pw).
        try (BufferedReader br = new BufferedReader(new FileReader(ficheiroOriginal));
        PrintWriter pw = new PrintWriter(new FileWriter(ficheiroTemp))){ 

            String linha;
            //Lê cada linha do ficheiro original
            while ((linha = br.readLine()) !=null) {
                //Divide a linha para obter o ID
                String[] campos = linha.split(";");

                //Compara o ID da linha com o ID a atualizar
                if (campos.length > 0 && campos[0].equals(idAtualizar)) {
                    //Se for o registo a atualizar, marca como encontrado
                    encontrado = true;
                    //Pede os novos dados ao utilizador
                    System.out.println("Registo encontrado. Insira os novos dados.");
                    System.out.print("Novo nome: ");
                    String novoNome = scanner.nextLine();
                    System.out.print("Novo email: ");
                    String novoEmail = scanner.nextLine();
                    //Cria a nova linha formatada
                    String novaLinha = idAtualizar + ";" + novoNome + ";" + novoEmail;
                    //Escreve a nova linha no ficheiro temporário
                    pw.println(novaLinha);
                } else {
                    //Se não for o registo a atualizar, copia a linha original
                    pw.println(linha);
                }
            }
        }// Fim do try-with-resources (fecha br e pw)

        // Se, após ler o ficheiro todo, o registo não foi encontrado.
        if (!encontrado) {
            System.out.println("Nenhum registo com o ID " + idAtualizar + " foi encontrado.");
            // Apaga o ficheiro temporário, pois não houve atualização
            ficheiroTemp.delete();
        } else {
            //Se o registo foi atualizado com sucesso:
            //Apaga o ficheiro original
            if (!ficheiroOriginal.delete()) {
                System.err.println("Não foi possível apagar o ficheiro original.");
                return;
            }
            //Renomeia o ficheiro temporário para o nome do ficheiro original
            if (!ficheiroTemp.renameTo(ficheiroOriginal)) {
                System.err.println("Não foi possível renomear o ficheiro temporário.");
                return;
            }
            //Informa o utilizador do sucesso da operação
            System.out.println("Registo atualizado com sucesso.");
        }
    }
    
    /**
     * Método para Remover (Delete) um registo.
     * Utiliza a mesma estratégia do 'Atualizar':
     * Copia todas as linhas do original para o temporário, *exceto* a linha a ser removida.
     */

     private static void removerRegisto(Scanner scanner) throws IOException {
        System.out.println("------REMOVER REGISTO-----");
        System.out.print("Insira o ID do registo a remover: ");
        String idRemover = scanner.nextLine();

        //Define o ficheiro original e o ficheiro temporário
        File ficheiroOriginal = new File(ARQUIVO_DADOS);
        File ficheiroTemp = new File("dados_temp.txt");

        boolean encontrado = false;

        //Abre o original para leitura e o temporário para escrita
        try (BufferedReader br = new BufferedReader(new FileReader(ficheiroOriginal));
        PrintWriter pw = new PrintWriter(new FileWriter(ficheiroTemp))) {

            String linha;
            //Lê o ficheiro original linha a linha
            while ((linha = br.readLine()) !=null) {
                String[] campos = linha.split(";");

                //Verifica se é a linha a remover
                if (campos.length > 0 && campos[0].equals(idRemover)) {
                    //Se for, marca como encontrado e não escreve no temporário
                    encontrado = true;
                } else {
                    //Se não for, copia a linha para o temporário
                    pw.println(linha);
                }
            }
        }//Fecha os ficheiros (br e pw).

        //Se o registo foi encontrado e removido (ignorado)
        if (encontrado) {
            // Apaga o ficheiro original
            if (!ficheiroOriginal.delete()) {
                System.err.println("ERRO: Não foi possível apagar o ficheiro original.");
                return;               
            }
            //Renomeia o temporário para o nome original
            if(!ficheiroTemp.renameTo(ficheiroOriginal)) {
                System.err.println("ERRO: Não foi possível renomear o ficheiro temporário.");
                return;
            }
            System.out.println("Registo removido com sucesso.");
        } else {
            //Se não encontrou o ID, informa o utilizador.
            System.out.println("Nenhum registo com o ID " + idRemover + " foi encontrado.");
            //Apaga o ficheiro temporário, que é uma cópia exata do original.
            ficheiroTemp.delete();
        }
     }
}
    

    