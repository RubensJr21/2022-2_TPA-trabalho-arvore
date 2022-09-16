package tree;

public class BinaryTree <T extends Comparable<T>>{
    private Node<T> root;
    private T lesserNode, biggerNode, worstCase;
    private int amountItems = 0;

    public BinaryTree(){
        this.root = null;
        this.lesserNode = null;
        this.biggerNode = null;
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
                    System.out.println(String.format("Inseriu à esquerda do: %s", root.getValue().toString()));
                }else{
                    insert(root.getLeftChild(), item);
                }
            } else {
                if(root.getRightChild() == null){
                    root.setRightChild((item));
                    System.out.println(String.format("Inseriu à direita do: %s", root.getValue().toString()));
                } else {
                    insert(root.getRightChild(), item);
                }
            }
        }
    }

    public void insertItem(T item) {
        System.out.println(String.format("\n\nInserindo item: %s", item.toString()));
        //TO-DO: Implementar método de inserção
        this.amountItems++;
        if(lesserNode == null || item.compareTo(lesserNode) < 0){
            lesserNode = item;
        }
        if(biggerNode == null || item.compareTo(biggerNode) > 0){
            biggerNode = item;
        }
        Node<T> newNode = new Node<T>(item);
        if(this.root != null){
            insert(this.root, newNode);
        } else {
            this.root = newNode;
        }
        int alturaArvore = this.getWidthTree();
        updateWorstCaseAux(this.root, alturaArvore, 0);
    }

    private T search(Node<T> root, T item){
        if(root != null){
            int result = item.compareTo(root.getValue());
            // System.out.println(String.format("Comparando: %s || %s", root.getValue().toString(), item.toString()));
            // System.out.println(String.format("result = %d", result));
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
        System.out.println(String.format("\n\nProcurando item item: %s", item.toString()));
        return search(this.root, item);
    }

    private void updateLesserNo(Node<T> root){
        if(root.getLeftChild() != null){
            updateLesserNo(root.getLeftChild());
        } else {
            this.lesserNode = root.getValue();
        }
    }

    private void updateBiggerNo(Node<T> root){
        if(root.getRightChild() != null){
            updateBiggerNo(root.getRightChild());
        } else {
            this.biggerNode = root.getValue();
        }
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
        Node<T> son = dad.getLeftChild();
        if(son != null){
            int result = son.getValue().compareTo(item);
            if(result == 0){
                return -1;
            }
            son = dad.getRightChild();
            result = son.getValue().compareTo(item);
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
            System.out.println(String.format("result = %d", result));
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
        System.out.println(String.format("\n\nExcluindo item: %s", item.toString()));
        // TO-DO: Implementar método de remoção
        if(item.compareTo(this.root.getValue()) == 0){
            removeRoot();
        }else {
            remove(this.root, item);
        }
        this.amountItems--;
        updateLesserNo(this.root);
        updateBiggerNo(this.root);
        int alturaArvore = this.getWidthTree();
        updateWorstCaseAux(this.root, alturaArvore, 0);
    }

    private void walkInOrderAux(Node<T> root){
        // TO-DO: Implementar método de caminhar em ordem
        if(root != null){
            // System.out.println(String.format("Lado esquerdo: %s", (root.getLeftChild() == null) ? "null" : ""));
            walkInOrderAux(root.getLeftChild());
            // System.out.println("Meio: ");
            System.out.println(root.getValue().toString());
            // System.out.println(String.format("Lado direito: %s", (root.getRightChild() == null) ? "null" : ""));
            walkInOrderAux(root.getRightChild());
            // System.out.println("===============");
        }
    }

    public void walkInOrder(){
        System.out.println("\n\nCaminhando em Ordem:");
        walkInOrderAux(this.root);
    }

    private void walkInLevelAux(Node<T> root, int levelWanted, int levelCurrent){
        // verficar se está no nível
        if(levelWanted == levelCurrent){
            System.out.println(root.getValue().toString());
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
        System.out.println("\n\nCaminhando em Nível:");
        int width = getWidthTree();
        
        for(int i = 0;i <= width;i++){
            System.out.println("Nivel " + i);
            walkInLevelAux(this.root, i, 0);
            System.out.println("Fim do nivel: " + i);
        }
    }

    private int widthTree(Node<T> root, int level){
        // TO-DO: Pegar quantos niveis possui a arvore
        if(root != null){
            level++;
            if(root.getLeftChild() != null && root.getRightChild() != null){
                int widthE = widthTree(root.getLeftChild(), level);
                int widthD = widthTree(root.getRightChild(), level);
                if(widthE > widthD){
                    return widthE;
                } else {
                    return widthD;
                }
            } else if(root.getLeftChild() != null){
                return widthTree(root.getLeftChild(), level);
            } else if(root.getRightChild() != null){
                return widthTree(root.getRightChild(), level);
            }
        }
        return level;
    }

    public int getWidthTree() {
        return widthTree(this.root, -1);
    }

    public int getAmountItems(){
        return amountItems;
    }

    public T getLesserItem(){
        return lesserNode;
    }

    public T getBiggerItem(){
        return biggerNode;
    }

    private void updateWorstCaseAux(Node<T> root, int levelWanted, int levelCurrent){
        if(levelWanted == levelCurrent){
            this.worstCase = root.getValue();
            System.out.println("===> ACHEI O PIOR CASO <===");
            System.out.println(this.worstCase.toString());
            System.out.println("===> ***************** <===");
        } else if(levelCurrent < levelWanted) { 
            int haveChild = haveChild(root);
            if(haveChild == 2){
                updateWorstCaseAux(root.getLeftChild(), levelWanted, levelCurrent + 1);
                updateWorstCaseAux(root.getRightChild(), levelWanted, levelCurrent + 1);
            } else if(haveChild == 1){
                updateWorstCaseAux(root.getLeftChild(), levelWanted, levelCurrent + 1);
            } else if(haveChild == 0){
                updateWorstCaseAux(root.getRightChild(), levelWanted, levelCurrent + 1);
            }
        }
    }
    
    public T getWorstCase(){
        // pegar raiz
        // andar nivel por nivel, verificando se els possui filhos abaixo
        // quando não ter mais filhos, verifico quem tem o nivel mais alto
        return this.worstCase;
    }
}