package tree;

public class Node <T extends Comparable<T>>{
    private T valor;
    private Node<T> LeftChild, filhoDireita;

    public Node(T valor){
        this.valor = valor;
        this.LeftChild = null;
        this.filhoDireita = null;
    }

    public void setValor(T valor){
        this.valor = valor;
    }
    public T getValue(){
        return this.valor;
    }

    public void setLeftChild(Node<T> no) {
        this.LeftChild = no;
    }

    public void setRightChild(Node<T> no) {
        this.filhoDireita = no;
    }

    public Node<T> getLeftChild() {
        return LeftChild;
    }

    public Node<T> getRightChild() {
        return filhoDireita;
    }
}