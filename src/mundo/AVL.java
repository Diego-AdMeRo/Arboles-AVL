package mundo;

public class AVL {

	// Atributos
	private Node root;

	public AVL() {
		root = null;
	}

	public void makeTree(int tree[]) {
		int i = 0;
		while (i < tree.length) {
			if (root == null) {
				root = new Node(tree[i]);
			} else
				root = insert(root, tree[i]);
			i++;
		}
	}

	private Node insert(Node proot, int info) {
		int gE;
		if (proot.getInfo() > info) {
			if (proot.getLeft() == null) {
				proot.setLeft(new Node(info));
			} else
				proot.setLeft(insert(proot.getLeft(), info));
		} else if (proot.getInfo() <= info) {
			if (proot.getRight() == null) {
				proot.setRight(new Node(info));
			} else
				proot.setRight(insert(proot.getRight(), info));
		}

		proot.setHeight(1 + max(height(proot.getLeft()), height(proot.getRight())));
		gE = weightT(proot);

		if (gE > 1 && info < proot.getLeft().getInfo()) {
			return rightSimple(proot);
		} else if (gE < -1 && info > proot.getRight().getInfo()) {
			return leftSimple(proot);
		} else if (gE > 1 && info > proot.getLeft().getInfo()) {
			// proot.setLeft(leftSimple(proot.getLeft()));
			// return rightSimple(proot);
			return leftDouble(proot);
		} else if (gE < -1 && info < proot.getRight().getInfo()) {
			// proot.setRight(rightSimple(proot.getRight()));
			// return leftSimple(proot);
			return rightDouble(proot);
		}
		return proot;
	}

	// Este método se encarga de retornar el nodo padre del árbol
	public Node getRoot() {
		return root;
	}

	/*
	 * Este método se encarga de dar el peso factor de equilibrio que tiene cada
	 * sub-arbol
	 */
	private int weightT(Node a) {
		if (a == null)
			return 0;
		return height(a.getLeft()) - height(a.getRight());
	}

	/*
	 * Este método se encarga de calcular la altura que tiene cada nodo para que no
	 * de errores si es null
	 */
	public int height(Node a) {
		if (a == null)
			return 0;
		return a.getHeight();
	}

	/*
	 * Este método se encarga de calcular cual es la máxima altura de los nodos de
	 * un sub-arbol
	 */
	public int max(int left, int right) {
		if (left > right)
			return left;
		return right;
	}

	// Este método se encarga de hacer las rotaciones hacia la derecha
	public Node rightSimple(Node proot) {
		Node left = proot.getLeft();
		proot.setLeft(left.getRight());
		proot.setHeight(max(height(proot.getLeft()), height(proot.getRight())) + 1);
		left.setRight(proot);
		left.setHeight(max(height(left.getLeft()), height(left.getRight())) + 1);
		return left;
	}

	// Este método se encarga de hacer las rotaciones hacia la izquierda
	public Node leftSimple(Node proot) {
		Node right = proot.getRight();
		proot.setRight(right.getLeft());
		proot.setHeight(max(height(proot.getLeft()), height(proot.getRight())) + 1);
		right.setLeft(proot);
		right.setHeight(max(height(right.getLeft()), height(right.getRight())) + 1);
		return right;
	}

	// Este método se encarga de hacer las rotaciones dobles a la izquierda
	public Node leftDouble(Node proot) {
		Node left = proot.getLeft(), right = left.getRight();
		left.setRight(right.getLeft());
		left.setHeight(max(height(left.getLeft()), height(left.getRight())) + 1);
		right.setLeft(left);
		right.setHeight(max(height(right.getLeft()), height(right.getRight())) + 1);
		proot.setLeft(right);
		proot.setHeight(max(height(proot.getLeft()), height(proot.getRight())) + 1);
		return rightSimple(proot);
	}

	// Este método se encarga de hacer las rotaciones dobles a la derecha
	public Node rightDouble(Node proot) {
		Node right = proot.getRight(), left = right.getLeft();
		right.setLeft(left.getRight());
		right.setHeight(max(height(right.getLeft()), height(right.getRight())) + 1);
		left.setRight(right);
		left.setHeight(max(height(left.getLeft()), height(left.getRight())) + 1);
		proot.setRight(left);
		proot.setHeight(max(height(proot.getLeft()), height(proot.getRight())) + 1);
		return leftSimple(proot);
	}

	// Este método se encarga de imprimir el árbol por pre-orden
	public void preOrden(Node root) {
		System.out.print(root.getInfo() + "\t");
		if (root.getLeft() != null)
			preOrden(root.getLeft());
		if (root.getRight() != null)
			preOrden(root.getRight());
	}

}
