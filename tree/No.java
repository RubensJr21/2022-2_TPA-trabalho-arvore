package tree;

public class No <T extends Comparable<T>>{
    private T valor;
    private No<T> filhoEsquerda, filhoDireita;

    public No(T valor){
        this.valor = valor;
        this.filhoEsquerda = null;
        this.filhoDireita = null;
    }

    public void setValor(T valor){
        this.valor = valor;
    }
    public T getValor(){
        return this.valor;
    }

    public void setFilhoEsquerda(No<T> no) {
        this.filhoEsquerda = no;
    }

    public void setFilhoDireita(No<T> no) {
        this.filhoDireita = no;
    }

    public No<T> getFilhoEsquerda() {
        return filhoEsquerda;
    }

    public No<T> getFilhoDireita() {
        return filhoDireita;
    }
}