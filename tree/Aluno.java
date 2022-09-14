package tree;

public class Aluno implements Comparable<Aluno>{

    private int matricula;
    private String nome;
    private float nota;

    public Aluno(int matricula, String nome, float nota){
        this.matricula = matricula;
        this.nome = nome;
        this.nota = nota;
    }

    @Override
    public int compareTo(Aluno a) {
        if(this.matricula == a.getMatricula()){
            return 0;
        } else if(this.matricula < a.getMatricula()){
            return -1;
        } else {
            return 1;
        }
    }
    
    public int getMatricula() {
        return matricula;
    }

    public String getNome(){
        return nome;
    }

    public float getNota(){
        return nota;
    }
    public float setNota(){
        return nota;
    }

    @Override
    public String toString() {
        return String.format("%d;%s;%.2f", this.matricula, this.nome, this.nota);
    }
}