package tree;

public class ArvoreBinaria <T extends Comparable<T> & Info>{
    private No<T> raiz, lesserNo, biggerNo;
    private int amountItems = 0;

    public ArvoreBinaria(){
        this.raiz = null;
        this.lesserNo = null;
        this.biggerNo = null;
    }

    private void insert(No<T> raiz, No<T> item){
        int result = item.getValor().compareTo((raiz.getValor()));
        if(result == 0){
            // se for igual atualizar dados
            this.raiz = item;
        }else{
            if(result < 0){
                if(raiz.getFilhoEsquerdo() == null){
                    raiz.setFilhoEsquerdo((item));
                    System.out.println(String.format("Inseriu à esquerda do: %s", raiz.getValor().compileInfos()));
                }else{
                    insert(raiz.getFilhoEsquerdo(), item);
                }
            } else {
                if(raiz.getFilhoDireito() == null){
                    raiz.setFilhoDireita((item));
                    System.out.println(String.format("Inseriu à direita do: %s", raiz.getValor().compileInfos()));
                } else {
                    insert(raiz.getFilhoDireito(), item);
                }
            }
        }
    }

    public void insertItem(No<T> item) {
        //TO-DO: Implementar método de inserção
        this.amountItems++;
        if(lesserNo == null || item.getValor().compareTo(lesserNo.getValor()) < 0){
            lesserNo = item;
        }
        if(biggerNo == null || item.getValor().compareTo(biggerNo.getValor()) > 0){
            biggerNo = item;
        }
        if(this.raiz != null){
            insert(this.raiz, item);
        } else {
            this.raiz = item;
        }
    }

    private T search(No<T> raiz, T item){
        if(raiz != null){
            int result = item.compareTo(raiz.getValor());
            // System.out.println(String.format("Comparando: %s || %s", raiz.getValor().compileInfos(), item.compileInfos()));
            // System.out.println(String.format("result = %d", result));
            if(result == 0){
                return raiz.getValor();
            } else if(result < 0){
                return search(raiz.getFilhoEsquerdo(), item);
            } else {
                return search(raiz.getFilhoDireito(), item);
            }
        }else {
            return null;
        }
    }

    public T searchItem(T item) {
        return search(this.raiz, item);
    }

    private void updateLesserNo(No<T> raiz){
        if(raiz.getFilhoEsquerdo() != null){
            updateLesserNo(raiz.getFilhoEsquerdo());
        } else {
            this.lesserNo = raiz;
        }
    }

    private void updateBiggerNo(No<T> raiz){
        if(raiz.getFilhoDireito() != null){
            updateBiggerNo(raiz.getFilhoDireito());
        } else {
            this.biggerNo = raiz;
        }
    }

    private void remove(No<T> raiz, No<T> item){
        if(raiz != null){
            int result = item.getValor().compareTo(raiz.getValor());
            System.out.println(String.format("result = %d", result));
            if(result == 0){
                if(raiz.getFilhoEsquerdo() != null && raiz.getFilhoDireito() != null){
                    System.out.println(raiz.getValor().compileInfos());
                    No<T> filhoDireito = raiz.getFilhoDireito();
                    // Java não passa referência
                    // Guardar referêcia do anterior para setar filho à esquerda ou à direita
                    raiz = raiz.getFilhoEsquerdo();
                    insert(this.raiz, filhoDireito);
                } else if(raiz.getFilhoEsquerdo() != null){
                    raiz = raiz.getFilhoEsquerdo();
                } else if(raiz.getFilhoDireito() != null){
                    raiz = raiz.getFilhoDireito();
                } else {
                    raiz = null;
                }
            } else {
                if(result < 0){
                    remove(raiz.getFilhoEsquerdo(), item);
                } else {
                    remove(raiz.getFilhoDireito(), item);
                }
            }
        }
    }

    public void removeItem(No<T> item) {
        // TO-DO: Implementar método de remoção
        remove(this.raiz, item);
        this.amountItems--;
        updateLesserNo(this.raiz);
        updateBiggerNo(this.raiz);
    }

    private void walkInOrderAux(No<T> raiz){
        // TO-DO: Implementar método de caminhar em ordem
        if(raiz != null){
            // System.out.println(String.format("Lado esquerdo: %s", (raiz.getFilhoEsquerdo() == null) ? "null" : ""));
            walkInOrderAux(raiz.getFilhoEsquerdo());
            // System.out.println("Meio: ");
            System.out.println(raiz.getValor().compileInfos());
            // System.out.println(String.format("Lado direito: %s", (raiz.getFilhoDireito() == null) ? "null" : ""));
            walkInOrderAux(raiz.getFilhoDireito());
            // System.out.println("===============");
        }
    }

    public void walkInOrder(){
        walkInOrderAux(this.raiz);
    }

    public void walkInNivelAux(No<T> no){
        // TO-DO: Implementar método de caminhar em nível
        
        
    }
    public void walkInNivel(){
        walkInNivelAux(this.raiz);
        
    }

    private int widthTree(No<T> raiz, int nivel){
        // TO-DO: Pegar quantos niveis possui a arvore
        if(raiz != null){
            nivel++;
            if(raiz.getFilhoEsquerdo() != null && raiz.getFilhoDireito() != null){
                int widthE = widthTree(raiz.getFilhoEsquerdo(), nivel);
                int widthD = widthTree(raiz.getFilhoDireito(), nivel);
                if(widthE > widthD){
                    return widthE;
                } else {
                    return widthD;
                }
            } else if(raiz.getFilhoEsquerdo() != null){
                return widthTree(raiz.getFilhoEsquerdo(), nivel);
            } else if(raiz.getFilhoDireito() != null){
                return widthTree(raiz.getFilhoDireito(), nivel);
            }
        }
        return nivel;
    }

    public int getWidthTree() {
        return widthTree(this.raiz, -1);
    }

    public int getAmountItems(){
        return amountItems;
    }

    public No<T> getLesserNo(){
        return lesserNo;
    }

    public No<T> getBiggerNo(){
        return biggerNo;
    }
}