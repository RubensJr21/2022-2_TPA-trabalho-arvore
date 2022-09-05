package tree;

public class Aluno implements Comparable{

    private int matricula;
    private String nome;
    private float nota;

    public Aluno(int matricula, String nome, float nota){
        this.matricula = matricula;
        this.nome = nome;
        this.nota = nota;
    }

    @Override
    public int compareTo(Object o) {
        Aluno a = (Aluno) o;
        if(this.matricula == a.getMatricula()){
            return 1;
        }
        return 0;
    }
    
    public int getMatricula() {
        return matricula;
    }

    public String getNome(){
        return nome;
    }

    public float nota(){
        return nota;
    }
}
