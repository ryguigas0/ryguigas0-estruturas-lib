// CÓDIGO DO PROFESSOR

package arvore.avl;

public class Nodo<T extends Comparable<T>> {
	private T dado;
	private int altura;
	private Nodo<T> esq;
	private Nodo<T> dir;

	public Nodo(T dado) {
		this.dado = dado;
		this.altura = 1;
		this.esq = null;
		this.dir = null;
	}

	public T getDado() {
		return dado;
	}

	public void setDado(T dado) {
		this.dado = dado;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public Nodo<T> getEsq() {
		return esq;
	}

	public void setEsq(Nodo<T> esq) {
		this.esq = esq;
	}

	public Nodo<T> getDir() {
		return dir;
	}

	public void setDir(Nodo<T> dir) {
		this.dir = dir;
	}

	@Override
	public String toString() {
		return dado.toString();
	}
}
