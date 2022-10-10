
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
// import java.nio.file.FileSystems;
// import java.nio.file.Path;
// import java.util.Arrays;
import java.util.Scanner;

import tree.Aluno;
import tree.BinaryTree;

public class Main {
    private static Scanner entrada = new Scanner(System.in);
    
    private static void println(String line){
        System.out.println(line);
    }

    private static void printStatiscts(BinaryTree<Aluno> tree){
        System.out.println("==========IMPRIMINDO ÁRVORE ==========");
        System.out.println("Quantidade de elementos: "+ tree.getAmountItems());
        System.out.println("Altura da árvore: "+ tree.getHeightTree());
        System.out.println("Maior elemento: " + tree.getBiggerItem().toString());
        System.out.println("Menor elemento: " + tree.getLesserItem().toString());
        System.out.println("Pior caso de busca: " + tree.getWorstCase().toString());
        System.out.println("=====ENCERRANDO IMPRESSÃO ÁRVORE =====\n");
    }

    private static void fillTree(BinaryTree<Aluno> tree, String nameFile) throws IOException{
        System.out.println("===========CARREGANDO ÁRVORE==========");
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
        System.out.println("===========>CARREGOU ÁRVORE<==========\n");
    }
    
    private static void searchByMatricula(BinaryTree<Aluno> tree){
        System.out.println("===========BUSCANDO MATRICULA=========");
        System.out.println("Qual a matricula que deseja buscar? ");
        
        int matricula = entrada.nextInt();
        String nome = null;
        float nota = 0;
        
        Aluno a = new Aluno(matricula, nome, nota);
        long start = System.currentTimeMillis();
        Aluno item = tree.searchItem(a);
        long ends = System.currentTimeMillis();

        System.out.println(ends);
        System.out.println(start);
        System.out.println("Tempo de busca: " + (ends - start));
        
        if(item != null){
            println(item.toString());
        }else{
            System.out.println("Matricula não existe");
        }
        System.out.println("======================================\n");
    }

    private static void includeAluno(BinaryTree<Aluno> tree){
        System.out.println("=============INSERINDO ALUNO==========");
        System.out.println("Qual a matricula? ");
        int matricula = entrada.nextInt();
        
        System.out.println("Qual o nome? ");
        String nome = entrada.next();
        
        System.out.println("Qual a nota? ");
        float nota = entrada.nextFloat();
        
        Aluno aluno = new Aluno(matricula, nome, nota);

        tree.insertItem(aluno);
        System.out.println("======================================\n");
    }

    private static void deleteByMatricula(BinaryTree<Aluno> tree){
        System.out.println("=============DELETANDO ALUNO==========");
        System.out.println("Qual a matricula que deseja excluir?");
        int matricula = entrada.nextInt();
        String nome = null;
        float nota = 0;
        
        Aluno aluno = new Aluno(matricula, nome, nota);
        tree.removeItem(aluno);
        System.out.println("======================================\n");
    }
    
    private static void sair(BinaryTree<Aluno> tree){
        // Sair o programa deve percorrer a árvore usando caminhamento "em ordem" e gerar um arquivo em que cada linha apresentará a matrícula, o nome e a nota de um aluno, sempre separados por ;.
        tree.walkInOrder();
    }

    private static void printMenu(){
        System.out.println("=================MENU=================");
        System.out.println("1 - Exibir estatísticas");
        System.out.println("2 - Efetuar busca por matrícula");
        System.out.println("3 - Excluir por matrícula");
        System.out.println("4 - Incluir aluno");
        System.out.println("5 - Sair");
        System.out.println("======================================");
    }

    private static int getSelection(){
        System.out.println("Escolha uma opção: ");
        return entrada.nextInt();
    }

    public static void main(String[] args){        
        BinaryTree<Aluno> tree = new BinaryTree<Aluno>();
        try {
            // fillTree(tree, "entradaBalanceada10000000.txt");
            fillTree(tree, "entrada.txt");
            
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
        } catch (IOException e) {
            println("Erro ao abrir o arquivo");
        }
    }
}

