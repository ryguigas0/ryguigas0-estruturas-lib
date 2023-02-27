package lista.simples;

public class Lista {
    private Nodo inicio, fim;

    public void inserirComeco(String nomeNodo) {
        Nodo novoNodo = Nodo.criarNodo(nomeNodo, null);
        if (inicio == null && fim == null) {
            inicio = novoNodo;
            fim = novoNodo;
        } else {
            novoNodo.proximo = inicio;
            inicio = novoNodo;
        }
    }

    public void inserirFim(String nomeNodo) {
        Nodo novoNodo = Nodo.criarNodo(nomeNodo, null);

        if (inicio == null && fim == null) {
            inicio = novoNodo;
            fim = novoNodo;
        } else {
            fim.proximo = novoNodo;
            fim = novoNodo;
        }
    }

    public Nodo primeiroNodo() {
        return inicio;
    }

    public Nodo ultimoNodo() {
        return fim;
    }

    @Override
    public String toString() {
        if (inicio == null) {
            return "Lista vazia!";
        }

        Nodo nodo = inicio;
        int counter = 0;
        String output = "";

        do {
            output += String.format("%d - %s\n", counter, nodo);
            counter++;
            nodo = nodo.proximo;
        } while (nodo != null);

        return output;
    }
}
