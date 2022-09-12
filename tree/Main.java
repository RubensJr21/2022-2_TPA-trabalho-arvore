package tree;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
// import java.nio.file.FileSystems;
// import java.nio.file.Path;
// import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static Scanner entrada = new Scanner(System.in);
    
    private static void println(String line){
        System.out.println(line);
    }

    private static void printStatiscts(BinaryTree<Aluno> tree){
        System.out.println("==========IMPRIMINDO ÁRVORE ==========");
        System.out.println("Quantidade de elementos: "+ tree.getAmountItems());
        System.out.println("Altura da árvore: "+ tree.getWidthTree());
        System.out.println("Maior elemento: " + tree.getBiggerNode().getValue().compileInfos());
        System.out.println("Menor elemento: " + tree.getLesserNode().getValue().compileInfos());
        System.out.println("=====ENCERRANDO IMPRESSÃO ÁRVORE =====\n\n");
    }

    private static void fillTree(BinaryTree<Aluno> tree, String nameFile) throws IOException{
        System.out.println("==========PREENCHENDO ÁRVORE==========");
        BufferedReader buffRead = new BufferedReader(new FileReader(nameFile));
		String linha = "";
		while (true) {
            linha = buffRead.readLine();
            if (linha != null) {
                
                String[] line = linha.split(";");
                //println(Arrays.toString(line));
                //println(String.format("Inserindo: %s;%s;%s\n", line[0], line[1], line[2]));
                
                int matricula = Integer.parseInt(line[0]);
                String nome = line[1];
                float nota = Float.parseFloat(line[2]);
                
                Aluno a = new Aluno(matricula, nome, nota);
                Node<Aluno> no = new Node<Aluno>(a);

                tree.insertItem(no);
                
			} else{
                break;
            }
		}
		buffRead.close();
        System.out.println("===ENCERRANDO PREENCHIMENTO ÁRVORE ===\n\n");
    }
    
    private static void searchByMatricula(BinaryTree<Aluno> tree){
        System.out.println("============BUSCANDO ALUNO============");
        System.out.println("Qual a matricula que deseja buscar? ");
        
        int matricula = entrada.nextInt();
        String nome = null;
        float nota = 0;
        
        Aluno a = new Aluno(matricula, nome, nota);
        Aluno item = tree.searchItem(a);
        
        if(item != null){
            println(item.compileInfos());
        }else{
            System.out.println("Matricula não existe");
        }
        System.out.println("========ENCERRANDO BUSCA ALUNO========\n\n");
    }

    private static void includeAluno(BinaryTree<Aluno> tree){
        System.out.println("===========INSERINDO ALUNO ===========");
        System.out.println("Qual a matricula? ");
        int matricula = entrada.nextInt();
        
        System.out.println("Qual o nome? ");
        String nome = entrada.next();
        
        System.out.println("Qual a nota? ");
        float nota = entrada.nextFloat();
        
        Aluno aluno = new Aluno(matricula, nome, nota);
        Node<Aluno> no = new Node<Aluno>(aluno);
        tree.insertItem(no);
        System.out.println("======ENCERRANDO INSERÇÃO ALUNO ======\n\n");
    }

    private static void deleteByMatricula(BinaryTree<Aluno> tree){
        System.out.println("===========EXCLUINDO ALUNO ===========");
        System.out.println("Qual a matricula que deseja excluir?");
        int matricula = entrada.nextInt();
        String nome = null;
        float nota = 0;
        
        Aluno a = new Aluno(matricula, nome, nota);
        Node<Aluno> no = new Node<Aluno>(a);
        tree.removeItem(no);
        System.out.println("======ENCERRANDO EXCLUSÃO ALUNO ======\n\n");
    }
    
    public static void main(String[] args){        
        BinaryTree<Aluno> tree = new BinaryTree<Aluno>();
        try {
            fillTree(tree, "teste.txt");
        } catch (IOException e) {
            println("Erro ao abrir o arquivo");
        }

        printStatiscts(tree);

        //efetuar busca por matricula
        searchByMatricula(tree);
        
        includeAluno(tree);
        
        tree.walkInOrder();
        
        //excluir aluno
        deleteByMatricula(tree);
        
        searchByMatricula(tree);

        //incluir aluno
        includeAluno(tree);
        
        //sair
        tree.walkInOrder();

        printStatiscts(tree);
    }
}

