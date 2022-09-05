package tree;

public class Main {
    public static void main(String[] args) {
        Aluno aluno1 = new Aluno(00000000000, "João", 29);
        Aluno aluno2 = new Aluno(00000000001, "Maria", 30);
        ArvoreBinaria a = new ArvoreBinaria(aluno1);
    }
}
