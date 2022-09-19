
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
// import java.nio.file.FileSystems;
// import java.nio.file.Path;
// import java.util.Arrays;
import java.util.Scanner;

import tree_DEBUG.Aluno;
import tree_DEBUG.BinaryTree;
import tree_DEBUG.utils.DEBUG;

public class Main_DEBUG {
    private static Scanner entrada = new Scanner(System.in);

    private static void print(String line){
        switch (DEBUG.MODE) {
            case VERBOSE:
                System.out.println(line);
                DEBUG.writeOutputInFile(line);
                break;
            case DEBUG:
                DEBUG.writeOutputInFile(line);
                break;
            default:
                break;
        }
    }

    private static void printStatiscts(BinaryTree<Aluno> tree){
        print("==========IMPRIMINDO ÁRVORE ==========");
        print("Quantidade de elementos: "+ tree.getAmountItems());
        print("Altura da árvore: "+ tree.getHeightTree());
        print("Maior elemento: " + tree.getBiggerItem().toString());
        print("Menor elemento: " + tree.getLesserItem().toString());
        print("Pior caso de busca: " + tree.getWorstCase().toString());
        print("=====ENCERRANDO IMPRESSÃO ÁRVORE =====\n");
    }

    private static void fillTree(BinaryTree<Aluno> tree, String nameFile) throws IOException{
        print("===========CARREGANDO ÁRVORE==========");
        BufferedReader buffRead = new BufferedReader(new FileReader(nameFile));
		String linha = "";
        int qtdRegistros = Integer.parseInt(buffRead.readLine()); // Lê primeira linha que diz a quantidade de resgitros
		for(int i = 1; i <= qtdRegistros; i++){
            linha = buffRead.readLine();
            String[] line = linha.split(";");
            
            int matricula = Integer.parseInt(line[0]);
            String nome = line[1];
            float nota = Float.parseFloat(line[2]);
            
            Aluno student = new Aluno(matricula, nome, nota);

            tree.insertItem(student);
		}
		buffRead.close();
        print("===========>CARREGOU ÁRVORE<==========\n");
    }
    
    private static void searchByMatricula(BinaryTree<Aluno> tree){
        print("===========BUSCANDO MATRICULA=========");
        print("Qual a matricula que deseja buscar? ");
        
        int matricula = entrada.nextInt();
        String nome = null;
        float nota = 0;
        if(DEBUG.MODE == DEBUG.Modes.DEBUG) print(String.format("%d", matricula));
        
        Aluno a, item;
        a = new Aluno(matricula, nome, nota);
        item = tree.searchItem(a);
        
        if(item != null){
           print(item.toString());
        }else{
            print("Matricula não existe");
        }
        print("======================================\n");
    }

    private static void includeAluno(BinaryTree<Aluno> tree){
        print("=============INSERINDO ALUNO==========");
        print("Qual a matricula? ");
        int matricula = entrada.nextInt();
        if(DEBUG.MODE == DEBUG.Modes.DEBUG) print(String.format("%d", matricula));
        
        print("Qual o nome? ");
        String nome = entrada.nextLine();
        if(DEBUG.MODE == DEBUG.Modes.DEBUG) print(String.format("%s", nome));
        
        print("Qual a nota? ");
        float nota = entrada.nextFloat();
        if(DEBUG.MODE == DEBUG.Modes.DEBUG) print(String.format("%f", nota));
        
        Aluno aluno = new Aluno(matricula, nome, nota);

        tree.insertItem(aluno);
        print("======================================\n");
    }

    private static void deleteByMatricula(BinaryTree<Aluno> tree){
        print("=============DELETANDO ALUNO==========");
        print("Qual a matricula que deseja excluir?");
        int matricula = entrada.nextInt();
        if(DEBUG.MODE == DEBUG.Modes.DEBUG) print(String.format("%d", matricula));
        String nome = null;
        float nota = 0;
        
        Aluno aluno = new Aluno(matricula, nome, nota);
        tree.removeItem(aluno);
        print("======================================\n");
    }
    
    private static void sair(BinaryTree<Aluno> tree){
        // Sair o programa deve percorrer a árvore usando caminhamento "em ordem" e gerar um arquivo em que cada linha apresentará a matrícula, o nome e a nota de um aluno, sempre separados por ;.
        tree.walkInOrder();
    }

    private static void printMenu(){
        print("=================MENU=================");
        print("1 - Exibir estatísticas");
        print("2 - Efetuar busca por matrícula");
        print("3 - Excluir por matrícula");
        print("4 - Incluir aluno");
        print("5 - Sair");
        print("======================================");
    }

    private static int getSelection(){
        print("Escolha uma opção: ");
        int selection = entrada.nextInt();
        print("" + selection);
        return selection;
    }

    public static void main(String[] args){ 
        DEBUG.MODE = DEBUG.Modes.DEBUG;       
        BinaryTree<Aluno> tree = new BinaryTree<Aluno>();
        try {
            fillTree(tree, "entradaBalanceada10000000.txt");
            
            if(DEBUG.MODE == DEBUG.Modes.VERBOSE){
                int selection;
                do {
                    printMenu();
                    selection = getSelection();
                    switch (selection) {
                        case 1:
                            printStatiscts(tree);
                            break;
                        case 2:
                            searchByMatricula(tree);
                            break;
                        case 3:
                            deleteByMatricula(tree);
                            break;
                        case 4:
                            includeAluno(tree);
                            break;
                        case 5:
                            sair(tree); 
                            break;
                        default:
                            break;
                    }
                } while(selection != 5);
            } else if (DEBUG.MODE == DEBUG.Modes.DEBUG) {
                printStatiscts(tree);
                sair(tree);
            }
        } catch (IOException e) {
           print("Erro ao abrir o arquivo");
        }
    }
}

