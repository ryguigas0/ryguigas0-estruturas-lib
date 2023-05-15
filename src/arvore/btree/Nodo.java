package arvore.btree;

public class Nodo<T extends Comparable<T>> {
    private T dado;
    private Nodo<T> esq;
    private Nodo<T> dir;

    public Nodo(T dado) {
        this.dado = dado;
        this.esq = null;
        this.dir = null;
    }

    public T getDado() {
        return dado;
    }

    public Nodo<T> getEsq() {
        return esq;
    }

    public Nodo<T> getDir() {
        return dir;
    }

    public void setDado(T dado) {
        this.dado = dado;
    }

    public void setEsq(Nodo<T> esq) {
        this.esq = esq;
    }

    public void setDir(Nodo<T> dir) {
        this.dir = dir;
    }

    @Override
    public String toString() {
        return dado.toString();
    }

    public void conectarNodo(Nodo<T> novoNodo) {
        if (novoNodo.getDado().compareTo(this.dado) < 0) {
            if (this.esq == null) {
                esq = new Nodo<T>(novoNodo.getDado());
                return;
            } else {
                esq.conectarNodo(novoNodo);
            }
        } else if (novoNodo.getDado().compareTo(this.dado) > 0) {
            if (this.dir == null) {
                dir = new Nodo<T>(novoNodo.getDado());
                return;
            } else {
                dir.conectarNodo(novoNodo);
            }
        } else {
            return;
        }
    }
}