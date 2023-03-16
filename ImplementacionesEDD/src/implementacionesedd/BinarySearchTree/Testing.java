package implementacionesedd.BinarySearchTree;

public class Testing {
    public class myMethods implements BinarySearchTree.utilMethods<Integer> {
        @Override
        public boolean areEqual(Integer a, Integer b) {
            return a == b;
        }

        @Override
        public boolean isLessThan(Integer a, Integer b) {
            return a < b;
        }

        @Override
        public boolean isGreaterThan(Integer a, Integer b) {
            return a > b;
        }
    }
    public static void main(String[] args){
        // testiando el codigo
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(new Testing().new myMethods());
        bst.add(5);
        bst.add(24);
        bst.add(100);
        bst.add(3);
        bst.add(4);
        bst.add(300);
        bst.add(3);
        bst.add(1);
        bst.add(40);
        bst.add(11);
        bst.add(10);
        System.out.println("Preorden: " + bst.preOrder());
        System.out.println("Posorden: " + bst.postOrder());
        System.out.println("Inorden: " + bst.inOrder());

        System.out.println("El arbol contiene el dato 40? " + bst.contains(40));
        System.out.println("El arbol contiene el dato 100? " + bst.contains(100));
        System.out.println("El arbol contiene el dato 300? " + bst.contains(300));
        
        bst.delete(24);
        System.out.println("El arbol contiene el dato 40? " + bst.contains(5));
        System.out.println("inorden: " + bst.inOrder());
        
    }
}
