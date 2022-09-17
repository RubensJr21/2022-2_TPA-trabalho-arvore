
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
        System.out.println("=====ENCERRANDO IMPRESSÃO ÁRVORE =====\n\n");
    }

    private static void fillTree(BinaryTree<Aluno> tree, String nameFile) throws IOException{
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
    }
    
    private static void searchByMatricula(BinaryTree<Aluno> tree){
        System.out.println("Qual a matricula que deseja buscar? ");
        
        int matricula = entrada.nextInt();
        String nome = null;
        float nota = 0;
        
        Aluno a = new Aluno(matricula, nome, nota);
        Aluno item = tree.searchItem(a);
        
        if(item != null){
            println(item.toString());
        }else{
            System.out.println("Matricula não existe");
        }
    }

    private static void includeAluno(BinaryTree<Aluno> tree){
        System.out.println("Qual a matricula? ");
        int matricula = entrada.nextInt();
        
        System.out.println("Qual o nome? ");
        String nome = entrada.next();
        
        System.out.println("Qual a nota? ");
        float nota = entrada.nextFloat();
        
        Aluno aluno = new Aluno(matricula, nome, nota);

        tree.insertItem(aluno);
    }

    private static void deleteByMatricula(BinaryTree<Aluno> tree){
        System.out.println("Qual a matricula que deseja excluir?");
        int matricula = entrada.nextInt();
        String nome = null;
        float nota = 0;
        
        Aluno aluno = new Aluno(matricula, nome, nota);
        tree.removeItem(aluno);
    }
    
    public static void main(String[] args){        
        BinaryTree<Aluno> tree = new BinaryTree<Aluno>();
        try {
            fillTree(tree, "entradaBalanceada10000000.txt");

            printStatiscts(tree);

            //efetuar busca por matricula
            // searchByMatricula(tree);
            
            // includeAluno(tree);
            
            tree.walkInOrder();
            
            //excluir aluno
            // deleteByMatricula(tree);
            
            // searchByMatricula(tree);

            //incluir aluno
            // includeAluno(tree);
            
            //sair
            tree.walkInOrder();

            tree.walkInLevel();

            printStatiscts(tree);
        } catch (IOException e) {
            println("Erro ao abrir o arquivo");
        }
    }
}
