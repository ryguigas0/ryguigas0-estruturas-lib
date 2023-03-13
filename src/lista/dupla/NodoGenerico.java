package lista.dupla;

public class NodoGenerico<T> {
    T dado;
    NodoGenerico<T> esq, dir;

    public NodoGenerico(T dado) {
        this.dado = dado;
    }

    @Override
    public String toString() {
        return dado.toString();
    }

    public T getDado() {
        return this.dado;
    }
}
