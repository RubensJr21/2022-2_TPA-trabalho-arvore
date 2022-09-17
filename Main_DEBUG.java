
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
        print("=====ENCERRANDO IMPRESSÃO ÁRVORE =====\n\n");
    }

    private static void fillTree(BinaryTree<Aluno> tree, String nameFile) throws IOException{
        print("==========PREENCHENDO ÁRVORE==========");
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
        print("===ENCERRANDO PREENCHIMENTO ÁRVORE ===\n\n");
    }
    
    private static void searchByMatricula(BinaryTree<Aluno> tree){
        print("============BUSCANDO ALUNO============");
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
        print("========ENCERRANDO BUSCA ALUNO========\n\n");
    }

    private static void includeAluno(BinaryTree<Aluno> tree){
        print("===========INSERINDO ALUNO ===========");
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
        print("======ENCERRANDO INSERÇÃO ALUNO ======\n\n");
    }

    private static void deleteByMatricula(BinaryTree<Aluno> tree){
        print("===========EXCLUINDO ALUNO ===========");
        print("Qual a matricula que deseja excluir?");
        int matricula = entrada.nextInt();
        if(DEBUG.MODE == DEBUG.Modes.DEBUG) print(String.format("%d", matricula));
        String nome = null;
        float nota = 0;
        
        Aluno aluno = new Aluno(matricula, nome, nota);
        tree.removeItem(aluno);
        print("======ENCERRANDO EXCLUSÃO ALUNO ======");
    }
    
    public static void main(String[] args){ 
        DEBUG.MODE = DEBUG.Modes.DEBUG;       
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
           DEBUG.writeOutputInFile("Erro ao abrir o arquivo");
        }
    }
}

