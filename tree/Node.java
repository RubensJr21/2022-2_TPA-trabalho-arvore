package tree;

public class Node <T extends Comparable<T>>{
    private T value;
    private Node<T> leftChild, rightChild;

    public Node(T value){
        this.value = value;
        this.leftChild = null;
        this.rightChild = null;
    }

    public void setValue(T value){
        this.value = value;
    }
    public T getValue(){
        return this.value;
    }

    public void setLeftChild(Node<T> node) {
        this.leftChild = node;
    }

    public void setRightChild(Node<T> node) {
        this.rightChild = node;
    }

    public Node<T> getLeftChild() {
        return leftChild;
    }

    public Node<T> getRightChild() {
        return rightChild;
    }
}