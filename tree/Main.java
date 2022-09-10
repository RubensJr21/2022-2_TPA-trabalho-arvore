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

    private static void printStatiscts(ArvoreBinaria<Aluno> tree){
        //estatisticas
        System.out.println("Quantidade de elementos: "+ tree.getAmountItems());
        System.out.println("Altura da árvore: "+ tree.getWidthTree());
        System.out.println("Maior elemento: " + tree.getBiggerNo().getValor().compileInfos());
        System.out.println("Menor elemento: " + tree.getLesserNo().getValor().compileInfos());
        // System.out.println("Pior Caso: ");
    }

    private static void fillTree(ArvoreBinaria<Aluno> tree, String nameFile) throws IOException{
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
                No<Aluno> no = new No<Aluno>(a);

                tree.insertItem(no);
                
			} else{
                break;
            }
		}
		buffRead.close();
    }

    private static void searchByMatricula(ArvoreBinaria<Aluno> tree){
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
    }

    private static void includeAluno(ArvoreBinaria<Aluno> tree){
        System.out.println("Qual a matricula? ");
        int matricula = entrada.nextInt();

        System.out.println("Qual o nome? ");
        String nome = entrada.next();

        System.out.println("Qual a nota? ");
        float nota = entrada.nextFloat();

        Aluno aluno = new Aluno(matricula, nome, nota);
        No<Aluno> no = new No<Aluno>(aluno);
        tree.insertItem(no);
    }

    private static void deleteByMatricula(ArvoreBinaria<Aluno> tree){
        System.out.println("Qual a matricula que deseja excluir?");
        int matricula = entrada.nextInt();
        String nome = null;
        float nota = 0;
        
        Aluno a = new Aluno(matricula, nome, nota);
        No<Aluno> no = new No<Aluno>(a);
        tree.removeItem(no);
    }
    
    public static void main(String[] args){        
        ArvoreBinaria<Aluno> tree = new ArvoreBinaria<Aluno>();
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

        //incluir aluno
        includeAluno(tree);
        
        //sair
        tree.walkInOrder();
    }
}

