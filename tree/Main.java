package tree;

public class Main {
    public static void main(String[] args) {
        Aluno aluno1 = new Aluno(00000000000, "João", 29);
        Aluno aluno2 = new Aluno(00000000001, "Maria", 30);
        No<Aluno> no1 = new No<Aluno>(aluno1);
        No<Aluno> no2 = new No<Aluno>(aluno2);
        ArvoreBinaria<Aluno> a = new ArvoreBinaria<Aluno>(no1);
        a.insertItem(no2);
    }
}
