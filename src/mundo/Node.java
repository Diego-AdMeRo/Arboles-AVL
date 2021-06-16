package mundo;

public class Node {
	// Atributos
	private Node left, right;
	private int info, altura;

	// Constructor
	public Node(int info) {
		this.right = this.left = null;
		this.info = info;
		this.altura = 1;
	}

	// Métodos setters & getters
	public Node getLeft() {
		return left;
	}

	public Node getRight() {
		return right;
	}

	public int getInfo() {
		return info;
	}

	public int getHeight() {
		return altura;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public void setHeight(int altura) {
		this.altura = altura;
	}

}
