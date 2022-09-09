package tree;

public class ArvoreBinaria <T extends Comparable<T>>{
    private No<T> raiz, lesserNo, biggerNo;
    private int amountItems = 0;

    public ArvoreBinaria(No<T> no){
        if(no != null) this.amountItems++;
        this.raiz = no;
        this.lesserNo = no;
        this.biggerNo = no;
    }

    public void insertItem(No<T> item) {
        //TO-DO: Implementar método de inserção
        this.amountItems++;
        if(item.getValor().compareTo(lesserNo.getValor()) < 0){
            lesserNo = item;
        }
        if(item.getValor().compareTo(biggerNo.getValor()) > 0){
            biggerNo = item;
        }
        No<T> noRaiz = this.raiz;
        while(noRaiz != null){
            int result = item.getValor().compareTo((noRaiz.getValor()));
            if(result == 0){
                // se for igual atualizar dados
                noRaiz.setValor(item.getValor());
            }else{
                if(result < 0){
                    if(noRaiz.getFilhoEsquerda() == null){
                        noRaiz.setFilhoEsquerda((item));
                    }else{
                        noRaiz = noRaiz.getFilhoEsquerda();
                    }
                } else{
                    if(noRaiz.getFilhoDireita() == null){
                        noRaiz.setFilhoDireita((item));
                    } else {
                        noRaiz = noRaiz.getFilhoDireita();
                    }
                }
            }
        }
        //
    }

    public No<T> searchItem(No<T> item) {
        No<T> noRaiz = this.raiz;
        if(noRaiz != null){
            int result = item.getValor().compareTo((noRaiz.getValor()));
            if(result == 0){
                return noRaiz;
            }else{
                if(result < 0){
                    searchItem(noRaiz.getFilhoEsquerda());
                } else{
                    searchItem(noRaiz.getFilhoDireita());
                }
            }
        }
        return null;
    }

    private void updateLesserNo(No<T> raiz){
        if(raiz.getFilhoEsquerda() != null){
            updateLesserNo(raiz.getFilhoEsquerda());
        } else {
            this.lesserNo = raiz;
        }
    }

    private void updateBiggerNo(No<T> raiz){
        if(raiz.getFilhoDireita() != null){
            updateBiggerNo(raiz.getFilhoDireita());
        } else {
            this.biggerNo = raiz;
        }
    }

    public void removeItem(No<T> item) {
        // TO-DO: Implementar método de remoção
        // remover item
        this.amountItems--;
        updateLesserNo(this.raiz);
        updateBiggerNo(this.raiz);
    }

    public void walkInOrder(){
        // TO-DO: Implementar método de caminhar em ordem
    }

    public void walkInNivel(){
        // TO-DO: Implementar método de caminhar em nível
    }

    public int getWidthTree(){
        return 0;
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
