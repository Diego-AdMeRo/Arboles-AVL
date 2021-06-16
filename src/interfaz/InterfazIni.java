package interfaz;

import mundo.AVL;

public class InterfazIni {

	private static AVL avl = new AVL();

	public static void main(String[] args) {
		int a[] = { 10, 100, 20, 80, 40, 70};
		avl.makeTree(a);
		avl.preOrden(avl.getRoot());

	}

}
