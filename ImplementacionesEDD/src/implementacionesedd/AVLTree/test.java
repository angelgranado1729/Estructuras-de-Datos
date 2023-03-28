package implementacionesedd.AVLTree;

public class test {
    
    public static void main(String[] args){
        //tetstiando el arbol AVL
        AVLTree tree = new AVLTree();
        tree.insert(33);
        tree.insert(13);
        tree.insert(53);
        tree.insert(61);
        tree.insert(11);
        tree.insert(21);
        tree.insert(8);
        tree.insert(9);


        // System.out.println("Preorder traversal" +
        //         " of constructed tree is : ");
        //         tree.preOrder();
        //         System.out.println(" ");
                tree.printTree();

        System.out.println("\n\n ");

        tree.delete(13);
        tree.printTree();
                

    }
}
