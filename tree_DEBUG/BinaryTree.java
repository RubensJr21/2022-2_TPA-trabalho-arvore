package tree_DEBUG;

import tree_DEBUG.utils.DEBUG;

public class BinaryTree <T extends Comparable<T>>{
    private Node<T> root;
    private T lesserNode, biggerNode, worstCase;
    private int heightTree = 0, amountItems = 0;

    public BinaryTree(){
        this.root = null;
        this.lesserNode = null;
        this.biggerNode = null;
    }
    
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

    private void insert(Node<T> root, Node<T> item){
        int result = item.getValue().compareTo((root.getValue()));
        if(result == 0){
            // se for igual atualizar dados
            this.root = item;
        }else{
            if(result < 0){
                if(root.getLeftChild() == null){
                    root.setLeftChild((item));
                    // print(String.format("Inseriu à esquerda do: %s", root.getValue().toString()));
                } else {
                    insert(root.getLeftChild(), item);
                }
            } else {
                if(root.getRightChild() == null){
                    root.setRightChild((item));
                    // print(String.format("Inseriu à direita do: %s", root.getValue().toString()));
                } else {
                    insert(root.getRightChild(), item);
                }
            }
        }
    }

    public void insertItem(T item) {
        //TO-DO: Implementar método de inserção
        Node<T> newNode = new Node<T>(item);
        if(this.root != null){
            insert(this.root, newNode);
        } else {
            this.root = newNode;
        }
        this.amountItems++;
    }

    private T search(Node<T> root, T item){
        if(root != null){
            int result = item.compareTo(root.getValue());
            if(result == 0){
                return root.getValue();
            } else if(result < 0){
                return search(root.getLeftChild(), item);
            } else {
                return search(root.getRightChild(), item);
            }
        }else {
            return null;
        }
    }

    public T searchItem(T item) {
        return search(this.root, item);
    }

    // Retorna  2 se tiver dois filhos
    // Retorna  1 se tiver 1 filho que esteja à esquerda
    // Retorna  0 se tiver 1 filho que esteja à direita
    // Retorna -1 se não tiver dois filhos
    private int haveChild(Node<T> dad){
        if(dad.getLeftChild() != null && dad.getRightChild() != null){
            return 2;
        } else if(dad.getLeftChild() != null){
            return 1;
        } else if(dad.getRightChild() != null){
            return 0;
        } else {
            return -1;
        }
    }

    // Retorna  0 se não removerá
    // Retorna -1 se for o filho à esquerda
    // Retorna  1 se for o filho à direita
    private int anyOfMyChildWillBeRemoved(Node<T> dad, T item){
        Node<T> child = dad.getLeftChild();
        if(child != null){
            int result = child.getValue().compareTo(item);
            if(result == 0){
                return -1;
            }
            child = dad.getRightChild();
            result = child.getValue().compareTo(item);
            if(result == 0){
                return 1;
            }
        }
        return 0;
    }

    private void removeChild(Node<T> root, Node<T> child){
        int childHaveChild;
        childHaveChild = haveChild(child);
        if(childHaveChild == 2){ // O filho tiver 2 filhos
            root.setLeftChild(child.getLeftChild());
            insert(this.root, child.getLeftChild());
        } else if(childHaveChild == 1){ // O filho tiver apenas 1 filho à esquerda
            root.setLeftChild(child.getLeftChild());
        } else if(childHaveChild == 0){ // O filho tiver apenas 1 filho à direita
            root.setRightChild(child.getRightChild());
        } else { // O filho não ter filhos
            root.setLeftChild(null);
        }
    }

    private void removeRoot(){
        int haveChild = haveChild(this.root);
        if(haveChild == 2){
            this.root = this.root.getLeftChild();
            insert(this.root, this.root.getRightChild());
        } else if(haveChild == 1){
            this.root = this.root.getLeftChild();
        } else if (haveChild == 0){
            this.root = this.root.getRightChild();
        } else {
            this.root = null;
        }
    }
    private void remove(Node<T> root, T item){
        if(root != null){
            int result = item.compareTo(root.getValue());
            // print(String.format("result = %d", result));
            if(result == 0){ // só vai acontecer caso deseje remover a root
                int childRemove = anyOfMyChildWillBeRemoved(root, item);
                if(childRemove == -1){ // caso do filho esquerdo do nó atual ser removido
                    removeChild(root, root.getLeftChild());
                } else if(childRemove == 1){ // caso do filho direito do nó atual ser removido
                    removeChild(root, root.getRightChild());
                }
            } else {
                int childRemove = anyOfMyChildWillBeRemoved(root, item);
                if(result < 0){
                    if(childRemove == -1){ // caso do filho esquerdo do nó atual ser removido
                        removeChild(root, root.getLeftChild());
                    } else {
                        remove(root.getLeftChild(), item);
                    }
                } else {
                    if(childRemove == 1){ // caso do filho direito do nó atual ser removido
                        removeChild(root, root.getRightChild());
                    } else {
                        remove(root.getRightChild(), item);
                    }
                }
            }
        }
    }

    public void removeItem(T item) {
        if(this.amountItems <= 0){
            return; // impede falhas para caso de árvore vazia
        }
        // TO-DO: Implementar método de remoção
        if(item.compareTo(this.root.getValue()) == 0){
            removeRoot();
        }else {
            remove(this.root, item);
        }
        this.amountItems--;
        // updateDataFromTreeAfterRemove();
    }

    private void walkInOrderAux(Node<T> root){
        // TO-DO: Implementar método de caminhar em ordem
        if(root != null){
            walkInOrderAux(root.getLeftChild());
            print(root.getValue().toString());
            walkInOrderAux(root.getRightChild());
        }
    }

    public void walkInOrder(){
        print("\n*****Caminhando em Ordem:*****");
        walkInOrderAux(this.root);
        print("\n******************************");
    }

    private void walkInLevelAux(Node<T> root, int levelWanted, int levelCurrent){
        // verficar se está no nível
        if(levelWanted == levelCurrent){
            print(root.getValue().toString());
        } else if(levelCurrent < levelWanted) { 
            int haveChild = haveChild(root);
            if(haveChild == 2){
                walkInLevelAux(root.getLeftChild(), levelWanted, levelCurrent + 1);
                walkInLevelAux(root.getRightChild(), levelWanted, levelCurrent + 1);
            } else if(haveChild == 1){
                walkInLevelAux(root.getLeftChild(), levelWanted, levelCurrent + 1);
            } else if(haveChild == 0){
                walkInLevelAux(root.getRightChild(), levelWanted, levelCurrent + 1);
            }
        }
    }

    // TO-DO: Implementar método de caminhar em nível
    // Usar altura da árvore para implementar
    // Caminha descendo por cada filho, incrementando até cada nível
    /* psedo código
    1. Começa da raiz
    2. Gera um intervalo de inicio 0 (primeiro nível da árvore) 
    até a altura da árvore
    3. Desce e imprime as informações se o nó atual for
    daquela nível que tem que ser impresso naquele momento
    3.1 Para saber o nó é do nível ou não, podemos passar
    uma variável na chamada informando o próximo nível
    */

    public void walkInLevel(){
        print("\n*****Caminhando em Nível:*****");
        updateHeightTree(this.root);
        
        for(int i = 0;i <= this.heightTree;i++){
            print("*****Nivel " + i + "*****");
            walkInLevelAux(this.root, i, 0);
            print("*****Fim do nivel: " + i + "*****");
        }
        print("\n******************************");
    }

    private int heightTree(Node<T> root, int level){
        // TO-DO: Pegar quantos niveis possui a arvore
        if(root != null){
            level++;
            if(root.getLeftChild() != null && root.getRightChild() != null){
                int heightE = heightTree(root.getLeftChild(), level);
                int heightD = heightTree(root.getRightChild(), level);
                if(heightE > heightD){
                    return heightE;
                } else {
                    return heightD;
                }
            } else if(root.getLeftChild() != null){
                return heightTree(root.getLeftChild(), level);
            } else if(root.getRightChild() != null){
                return heightTree(root.getRightChild(), level);
            }
        }
        return level;
    }

    private void updateHeightTree(Node<T> root){
        this.heightTree = heightTree(root, -1);
    }

    public int getHeightTree() {
        updateHeightTree(this.root);
        return heightTree;
    }

    public int getAmountItems(){
        return amountItems;
    }

    private void updateLesserNode(Node<T> root){
        if(root.getLeftChild() != null){
            updateLesserNode(root.getLeftChild());
        } else {
            this.lesserNode = root.getValue();
        }
    }
    public T getLesserItem(){
        updateLesserNode(root.getLeftChild());
        return lesserNode;
    }
    
    private void updateBiggerNode(Node<T> root){
        if(root.getRightChild() != null){
            updateBiggerNode(root.getRightChild());
        } else {
            this.biggerNode = root.getValue();
        }
    }
    public T getBiggerItem(){
        updateBiggerNode(root.getLeftChild());
        return biggerNode;
    }

    private void updateWorstCase(Node<T> root, int levelWanted, int levelCurrent){
        if(levelWanted == levelCurrent){
            this.worstCase = root.getValue();
        } else if(levelCurrent < levelWanted) { 
            int haveChild = haveChild(root);
            if(haveChild == 2){
                updateWorstCase(root.getLeftChild(), levelWanted, levelCurrent + 1);
                updateWorstCase(root.getRightChild(), levelWanted, levelCurrent + 1);
            } else if(haveChild == 1){
                updateWorstCase(root.getLeftChild(), levelWanted, levelCurrent + 1);
            } else if(haveChild == 0){
                updateWorstCase(root.getRightChild(), levelWanted, levelCurrent + 1);
            }
        }
    }
    
    public T getWorstCase(){
        updateHeightTree(this.root);
        updateWorstCase(this.root, this.heightTree, 0);
        // pegar raiz
        // andar nivel por nivel, verificando se els possui filhos abaixo
        // quando não ter mais filhos, verifico quem tem o nivel mais alto
        return this.worstCase;
    }
}