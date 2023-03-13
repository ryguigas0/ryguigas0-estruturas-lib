package lista.dupla;

public class ListaGenerica<T> {
    private NodoGenerico<T> inicio, fim;
    private int tamanho = 0;

    void inserirInicio(T dado) {
        NodoGenerico<T> novoNodo = new NodoGenerico<T>(dado);
        if (inicio == null && fim == null) {
            inicio = novoNodo;
            fim = novoNodo;
        } else {
            inicio.esq = novoNodo;
            novoNodo.dir = inicio;

            inicio = novoNodo;
        }

        tamanho++;
    }

    void inserirFim(T dado) {
        NodoGenerico<T> novoNodo = new NodoGenerico<T>(dado);
        if (inicio == null && fim == null) {
            inicio = novoNodo;
            fim = novoNodo;
        } else {
            fim.dir = novoNodo;
            novoNodo.esq = fim;

            fim = novoNodo;
        }

        tamanho++;
    }

    NodoGenerico<T> pesquisar(T busca) {
        NodoGenerico<T> curr = inicio;

        while (curr != null) {
            if (curr.dado.equals(busca)) {
                return curr;
            }
            curr = curr.dir;
        }

        return null;
    }

    void deletar(T busca) {
        NodoGenerico<T> nodoParaDeletar = pesquisar(busca);

        if (nodoParaDeletar == null) {
            return;
        }

        if (tamanho == 1) {
            inicio = null;
            fim = null;
        } else if (nodoParaDeletar.equals(inicio)) {
            // NodoGenerico<T> nodoDepois = nodoParaDeletar.dir;
            // nodoDepois.esq = null;

            // inicio = nodoDepois;

            nodoParaDeletar.dir.esq = null;

            inicio = nodoParaDeletar.dir;
        } else if (nodoParaDeletar.equals(fim)) {
            // NodoGenerico<T> nodoAnterior = nodoParaDeletar.esq;
            // nodoAnterior.dir = null;

            // fim = nodoAnterior;

            nodoParaDeletar.esq.dir = null;

            fim = nodoParaDeletar.esq;
        } else {
            // NodoGenerico<T> nodoAnterior = nodoParaDeletar.esq;
            // NodoGenerico<T> nodoDepois = nodoParaDeletar.dir;

            // nodoAnterior.dir = nodoDepois;
            // nodoDepois.esq = nodoAnterior;

            nodoParaDeletar.esq.dir = nodoParaDeletar.dir;
            
            nodoParaDeletar.dir.esq = nodoParaDeletar.esq;
        }

        nodoParaDeletar = null;
        tamanho--;
    }

    public NodoGenerico<T> getInicio() {
        return inicio;
    }

    public NodoGenerico<T> getFim() {
        return fim;
    }

    public int getTamanho() {
        return tamanho;
    }

    @Override
    public String toString() {
        String dadosString = "[\n";
        NodoGenerico<T> curr = inicio;

        while (curr != null) {
            dadosString += String.format("\t\t%s,\n", curr);

            curr = curr.dir;
        }

        dadosString += "\t]";

        String output = String.format("{\n\tinicio: %s,\n\tfim: %s, \n\ttamanho: %d, \n\tdados: %s\n}",
                inicio, fim, tamanho, dadosString);

        return output;
    }

}
