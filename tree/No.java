package tree;

public class No <T extends Comparable<T>>{
    private T valor;
    private No<T> filhoEsquerdo, filhoDireita;

    public No(T valor){
        this.valor = valor;
        this.filhoEsquerdo = null;
        this.filhoDireita = null;
    }

    public void setValor(T valor){
        this.valor = valor;
    }
    public T getValor(){
        return this.valor;
    }

    public void setFilhoEsquerdo(No<T> no) {
        this.filhoEsquerdo = no;
    }

    public void setFilhoDireita(No<T> no) {
        this.filhoDireita = no;
    }

    public No<T> getFilhoEsquerdo() {
        return filhoEsquerdo;
    }

    public No<T> getFilhoDireito() {
        return filhoDireita;
    }
}