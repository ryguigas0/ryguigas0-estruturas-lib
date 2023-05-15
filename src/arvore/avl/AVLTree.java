// CÓDIGO DO PROFESSOR

package arvore.avl;

public class AVLTree<T extends Comparable<T>> {
    private Nodo<T> raiz;

    public void inserir(T dado) {
        this.raiz = inserir(this.raiz, dado);
    }

    private Nodo<T> inserir(Nodo<T> nodo, T dado) {
        if (nodo == null) {
            return new Nodo<T>(dado);
        }

        if (dado.compareTo(nodo.getDado()) < 0) {
            nodo.setEsq(inserir(nodo.getEsq(), dado));
        } else if (dado.compareTo(nodo.getDado()) > 0) {
            nodo.setDir(inserir(nodo.getDir(), dado));
        } else {
            // nó já existe na árvore AVL
            return nodo;
        }

        // atualiza a altura do nó correnete
        nodo.setAltura(1 + Math.max(getAltura(nodo.getEsq()), getAltura(nodo.getDir())));

        // verifica se a árvore está desbalanceada para realizar as rotações
        int fatorBalanceamento = getFatorBalanceamento(nodo);

        if (fatorBalanceamento > 1 && dado.compareTo(nodo.getEsq().getDado()) < 0) {
            return rotacaoDireita(nodo);
        }

        if (fatorBalanceamento < -1 && dado.compareTo(nodo.getDir().getDado()) > 0) {
            return rotacaoEsquerda(nodo);
        }

        if (fatorBalanceamento > 1 && dado.compareTo(nodo.getEsq().getDado()) > 0) {
            nodo.setEsq(rotacaoEsquerda(nodo.getEsq()));
            return rotacaoDireita(nodo);
        }

        if (fatorBalanceamento < -1 && dado.compareTo(nodo.getDir().getDado()) < 0) {
            nodo.setDir(rotacaoDireita(nodo.getDir()));
            return rotacaoEsquerda(nodo);
        }

        return nodo;
    }

    // método para realizar a rotação a direita
    private Nodo<T> rotacaoDireita(Nodo<T> nodo) {
        Nodo<T> novaRaiz = nodo.getEsq();
        Nodo<T> subtreeDireita = novaRaiz.getDir();

        novaRaiz.setDir(nodo);
        nodo.setEsq(subtreeDireita);

        nodo.setAltura(1 + Math.max(getAltura(nodo.getEsq()), getAltura(nodo.getDir())));
        novaRaiz.setAltura(1 + Math.max(getAltura(novaRaiz.getEsq()), getAltura(novaRaiz.getDir())));

        return novaRaiz;
    }

    // método para realizar a rotação a esquerda
    private Nodo<T> rotacaoEsquerda(Nodo<T> nodo) {
        Nodo<T> novaRaiz = nodo.getDir();
        Nodo<T> subtreeEsquerda = novaRaiz.getEsq();

        novaRaiz.setEsq(nodo);
        nodo.setDir(subtreeEsquerda);

        nodo.setAltura(1 + Math.max(getAltura(nodo.getEsq()), getAltura(nodo.getDir())));
        novaRaiz.setAltura(1 + Math.max(getAltura(novaRaiz.getEsq()), getAltura(novaRaiz.getDir())));

        return novaRaiz;
    }

    // método para retornar a altura de um nó
    private int getAltura(Nodo<T> nodo) {
        if (nodo == null) {
            return 0;
        }
        return nodo.getAltura();
    }

    // método para calcular o fator de balanceamento de um nó
    private int getFatorBalanceamento(Nodo<T> nodo) {
        if (nodo == null) {
            return 0;
        }
        return getAltura(nodo.getEsq()) - getAltura(nodo.getDir());
    }

    public String toStringPreOrdem() {
        return toStringPreOrdem(this.raiz);
    }

    private String toStringPreOrdem(Nodo<T> nodo) {
        String output = "";
        if (nodo == null) {
            return "";
        }

        output += nodo.toString() + " ";
        output += toStringPreOrdem(nodo.getEsq());
        output += toStringPreOrdem(nodo.getDir());

        return output;
    }

    public String toStringEmOrdem() {
        return toStringEmOrdem(this.raiz);
    }

    private String toStringEmOrdem(Nodo<T> nodo) {
        String output = "";
        if (nodo == null) {
            return "";
        }

        output += toStringEmOrdem(nodo.getEsq());
        output += nodo.toString() + " ";
        output += toStringEmOrdem(nodo.getDir());

        return output;
    }

    public String toStringPosOrdem() {
        return toStringPosOrdem(this.raiz);
    }

    private String toStringPosOrdem(Nodo<T> nodo) {
        String output = "";
        if (nodo == null) {
            return "";
        }

        output += toStringPosOrdem(nodo.getEsq());
        output += toStringPosOrdem(nodo.getDir());
        output += nodo.toString() + " ";

        return output;
    }

    public boolean presente(T dado) {
        return presente(this.raiz, dado);
    }

    private boolean presente(Nodo<T> nodo, T dado) {
        if (nodo == null) {
            return false;
        }

        return nodo.getDado().compareTo(dado) == 0 ||
                presente(nodo.getDir(), dado) ||
                presente(nodo.getEsq(), dado);
    }

    // método para realizar a remoção de um nó em uma árvore AVL
    public void remover(T dado) {
        raiz = remover(raiz, dado);
    }

    private Nodo<T> remover(Nodo<T> nodo, T dado) {
        if (nodo == null) {
            return nodo;
        }

        if (dado.compareTo(nodo.getDado()) < 0) {
            nodo.setEsq(remover(nodo.getEsq(), dado));
        } else if (dado.compareTo(nodo.getDado()) > 0) {
            nodo.setDir(remover(nodo.getDir(), dado));
        } else {
            // nó encontrado para remoção
            // caso 1: o nó não tem filhos ou tem apenas um
            if (nodo.getEsq() == null || nodo.getDir() == null) {
                Nodo<T> noFilho = (nodo.getEsq() == null) ? nodo.getDir() : nodo.getEsq();

                // nó não tem filhos
                if (noFilho == null) {
                    nodo = null;
                } else {
                    nodo = noFilho;
                }

                // caso 2: o nó tem dois filhos
            } else {
                // busca pelo sucessor (menor valor na subarvore direita)
                Nodo<T> successor = nodo.getDir();
                while (successor.getEsq() != null) {
                    successor = successor.getEsq();
                }

                // copia o valor do sucessor para o nó corrente
                nodo.setDado(successor.getDado());

                // remove o sucessor da subarvore direita
                nodo.setDir(remover(nodo.getDir(), successor.getDado()));
            }
        }

        // retorna null se o nó não foi encontrado
        if (nodo == null) {
            return null;
        }

        // atualiza a altura do nó corrente
        nodo.setAltura(1 + Math.max(getAltura(nodo.getEsq()), getAltura(nodo.getDir())));

        // a árvore está desbalanceada? Se estiver, aplica as rotações
        int fatorBalanceamento = getFatorBalanceamento(nodo);

        if (fatorBalanceamento > 1 && getFatorBalanceamento(nodo.getEsq()) >= 0) {
            return rotacaoDireita(nodo);
        }

        if (fatorBalanceamento > 1 && getFatorBalanceamento(nodo.getEsq()) < 0) {
            nodo.setEsq(rotacaoEsquerda(nodo.getEsq()));
            return rotacaoDireita(nodo);
        }

        if (fatorBalanceamento < -1 && getFatorBalanceamento(nodo.getDir()) <= 0) {
            return rotacaoEsquerda(nodo);
        }

        if (fatorBalanceamento < -1 && getFatorBalanceamento(nodo.getDir()) > 0) {
            nodo.setDir(rotacaoDireita(nodo.getDir()));
            return rotacaoEsquerda(nodo);
        }

        return nodo;
    }

}
