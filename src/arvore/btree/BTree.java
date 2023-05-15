package arvore.btree;

public class BTree<T extends Comparable<T>> {
    private Nodo<T> raiz;

    public Nodo<T> getRaiz() {
        return raiz;
    }

    public void inserir(T dado) {
        Nodo<T> novoNodo = new Nodo<>(dado);
        if (raiz == null) {
            raiz = novoNodo;
        } else {
            raiz.conectarNodo(novoNodo);
        }
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

    public void remover(T dado) {
        remover(raiz, dado);
    }

    private Nodo<T> remover(Nodo<T> raiz, T dado) {
        if (raiz == null) {
            return null;
        }

        if (dado.compareTo(raiz.getDado()) < 0) {
            raiz.setEsq(remover(raiz.getEsq(), dado));
        } else if (dado.compareTo(raiz.getDado()) > 0) {
            raiz.setDir(remover(raiz.getDir(), dado));
        } else {
            // O nó a ser removido tem 0 ou 1 filho
            if (raiz.getEsq() == null) {
                return raiz.getDir();
            } else if (raiz.getDir() == null) {
                return raiz.getEsq();
            }

            // O nó a ser removido tem 2 filhos
            Nodo<T> sucessor = getSucessor(raiz.getDir());
            raiz.setDado(sucessor.getDado());
            raiz.setDir(removerSucessor(raiz.getDir()));
        }

        return raiz;
    }

    private Nodo<T> getSucessor(Nodo<T> nodo) {
        if (nodo.getEsq() == null) {
            return nodo;
        }
        return getSucessor(nodo.getEsq());
    }

    private Nodo<T> removerSucessor(Nodo<T> nodo) {
        if (nodo.getEsq() == null) {
            return nodo.getDir();
        }
        nodo.setEsq(removerSucessor(nodo.getEsq()));
        return nodo;
    }
}
