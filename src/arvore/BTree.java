package arvore;

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

        if (nodo.getDado().compareTo(dado) == 0) {
            return true;
        }

        if (presente(nodo.getEsq(), dado)) {
            return true;
        }

        return presente(nodo.getDir(), dado);
    }
}
