package lista.simples;

public class Nodo {
    String nome;
    Nodo proximo;

    public static Nodo criarNodo(String nome, Nodo proximoNodo) {
        Nodo n = new Nodo();
        n.nome = nome;
        n.proximo = proximoNodo;

        return n;
    }

    @Override
    public String toString() {
        return String.format("nome: %s", nome);
    }

    public String nomeNodo() {
        return nome;
    }
}
