package implementacionesedd.BinaryTreeImp;

public class testing {
    public static void main(String[] args) {
        BinaryTree<Integer> tree = new BinaryTree<>();
        tree.addNode(5, null, null);
        tree.addNode(2, 5, "left");
        tree.addNode(9, 5, "right");
        tree.addNode(1, 2, "left");
        tree.addNode(3, 2, "right");
        tree.addNode(4, 3, "right");
        tree.addNode(7, 9, "left");
        tree.addNode(11, 9, "right");
        tree.addNode(6, 7, "left");
        tree.addNode(8, 7, "right");
        tree.addNode(10, 11, "left");
        tree.addNode(12, 11, "right");
        System.out.println("preorden " + tree.preOrder());
        System.out.println("inOrden " + tree.inOrder());
        System.out.println("postorden " + tree.postOrder());

        // testiando todos los metodos
        System.out.println("el arbol tiene " + tree.numNodes(tree.root) + " nodos");
        System.out.println("el arbol tiene " + tree.numLeaves(tree.root) + " hojas");
        System.out.println("el arbol tiene " + tree.numInternals(tree.root) + " nodos internos");
        System.out.println("el arbol tiene " + tree.height(tree.root) + " niveles");
        System.out.println("el arbol tiene " + tree.numCompleteNodes(tree.root) + " nodos completos");
        System.out.println("El arbol es equilibrado? " + tree.isBalanced(tree.root));
        System.out.println("El arbol es completo? " + tree.isComplete(tree.root));
        System.out.println("El arbol es lleno? " + tree.isFull(tree.root));
        System.out.println("El arbol es perfecto? " + tree.isPerfect(tree.root));
        System.out.println("El arbol es degenerado? " + tree.isDegenerate(tree.root));
        System.out.println("El arbol es vacio? " + tree.isEmpty());
        // System.out.println("Es un arbol binario de busqueda? " + tree.isBinarySearchTree(tree.root));


        // tree.printTree(tree.getRoot());

        // tree.convertToBinarySearchTree(tree.root);
        // tree.printTree(tree.getRoot());

        System.out.println("InOrden " + tree.inOrder());
        System.out.println("Postorden " + tree.postOrder());
        
        System.out.println("Preorden " + tree.preOrder());
        tree.deleteNode(11);
        System.out.println(tree.contains(2));
        System.out.println("Preorden " + tree.preOrder());
        // tree.printTree(tree.getRoot());


        
    }


    
}
