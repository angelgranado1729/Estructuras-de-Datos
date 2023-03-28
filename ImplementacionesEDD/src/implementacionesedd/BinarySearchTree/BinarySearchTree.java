package implementacionesedd.BinarySearchTree;

public class BinarySearchTree<T> {

    // Esto es para poder hacer callouts
    public interface utilMethods<T> {

        boolean areEqual(T a, T b);

        boolean isLessThan(T a, T b);

        boolean isGreaterThan(T a, T b);
    }

    // Metodos default para comparar
    public class methods implements utilMethods<T> {

        @Override
        public boolean areEqual(T a, T b) {
            return a.hashCode() == b.hashCode();
        }

        @Override
        public boolean isLessThan(T a, T b) {
            return a.hashCode() < b.hashCode();
        }

        @Override
        public boolean isGreaterThan(T a, T b) {
            return a.hashCode() > b.hashCode();
        }
    }

    private Node<T> root;
    private utilMethods<T> methods;

    public BinarySearchTree() {
        this.root = null;
        this.methods = new methods();
    }

    public BinarySearchTree(utilMethods<T> methods) {
        this.root = null;
        this.methods = methods;
    }

    public Node<T> getRoot() {
        return root;
    }

    public void setRoot(Node<T> root) {
        this.root = root;
    }

    public utilMethods<T> getMethods() {
        return methods;
    }

    public void setMethods(utilMethods<T> methods) {
        this.methods = methods;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    // num de nodos
    public int size(Node<T> pRoot) {
        if (this.isEmpty()) {
            return 0;
        } else {
            return 1 + this.size(pRoot.getLeft()) + this.size(pRoot.getRight());
        }
    }

    private String preOrder(Node<T> pRoot, String path) {
        if (pRoot != null) {
            path += pRoot.getData().toString() + ", ";
            if (pRoot.getLeft() != null) {
                path = preOrder(pRoot.getLeft(), path);
            }
            if (pRoot.getRight() != null) {
                path = preOrder(pRoot.getRight(), path);
            }
        }
        return path;
    }

    public String preOrder() {
        String result = this.preOrder(this.root, "");
        return result.substring(0, result.length() - 2);
    }

    private String inOrder(Node<T> pRoot, String path) {
        if (pRoot != null) {
            if (pRoot.getLeft() != null) {
                path = inOrder(pRoot.getLeft(), path);
            }
            path += pRoot.getData().toString() + ", ";
            if (pRoot.getRight() != null) {
                path = inOrder(pRoot.getRight(), path);
            }
        }
        return path;
    }

    public String inOrder() {
        String result = this.inOrder(this.root, "");
        return result.substring(0, result.length() - 2);
    }

    private String postOrder(Node<T> pRoot, String path) {
        if (pRoot != null) {
            if (pRoot.getLeft() != null) {
                path = postOrder(pRoot.getLeft(), path);
            }
            if (pRoot.getRight() != null) {
                path = postOrder(pRoot.getRight(), path);
            }
            path += pRoot.getData().toString() + ", ";
        }
        return path;
    }

    public String postOrder() {
        String result = this.postOrder(this.root, "");
        return result.substring(0, result.length() - 2);
    }

    // Busca el nodo padre de un nuevo dato a insertar
    private Node<T> searchParent(Node<T> pRoot, T data) {
        Node<T> found = null;
        if (pRoot != null) {
            if (this.methods.isLessThan(data, pRoot.getData())) {
                if (pRoot.getLeft() == null) {
                    return pRoot;
                } else {
                    found = this.searchParent(pRoot.getLeft(), data);
                }
            } else if (this.methods.isGreaterThan(data, pRoot.getData())) {
                if (pRoot.getRight() == null) {
                    return pRoot;
                } else {
                    found = this.searchParent(pRoot.getRight(), data);
                }
            }
        }
        return found;
    }

    private Node<T> searchParentNode(T data, Node<T> pRoot) {
        Node<T> found = null;
        if (pRoot != null) {
            if (this.methods.isLessThan(data, pRoot.getData())) {
                if (pRoot.getLeft() != null) {
                    if (this.methods.areEqual(data, pRoot.getLeft().getData())) {
                        return pRoot;
                    } else {
                        found = this.searchParentNode(data, pRoot.getLeft());
                    }
                } else {
                    return null;
                }
            }
            if (this.methods.isGreaterThan(data, pRoot.getData())) {
                if (this.methods.isGreaterThan(data, pRoot.getData())) {
                    if (pRoot.getRight() != null) {
                        if (this.methods.areEqual(data, pRoot.getRight().getData())) {
                            return pRoot;
                        } else {
                            found = this.searchParentNode(data, pRoot.getRight());
                        }
                    } else {
                        return null;
                    }
                }
            }
            if (this.methods.areEqual(data, pRoot.getData())) {
                return pRoot;
            }
        }
        return found;
    }

    public Node<T> searchParentNode(T data) {
        if (this.contains(data)) {
            return this.searchParentNode(data, this.root);
        }
        return new Node<T>();
    }

    public void add(T data) {
        if (this.isEmpty()) {
            this.root = new Node<>(data);
        } else {
            Node<T> parent = this.searchParent(this.root, data);
            if (parent == null) {
                System.out.println("El dato ya existe");
            } else {
                if (this.methods.isLessThan(data, parent.getData())) {
                    parent.setLeft(new Node<>(data));
                } else {
                    parent.setRight(new Node<>(data));
                }
            }
        }
    }

    public Node<T> search(Node<T> pRoot, T data) {
        if (pRoot == null) {
            return null;
        } else {
            if (this.methods.areEqual(data, pRoot.getData())) {
                return pRoot;
            } else if (this.methods.isLessThan(data, pRoot.getData())) {
                return this.search(pRoot.getLeft(), data);
            } else {
                return this.search(pRoot.getRight(), data);
            }
        }
    }

    // Buscar el nodo mas pequeño de un subarbol
    public Node<T> maxLeft(Node<T> pRoot) {
        if (pRoot.getRight() == null) {
            return pRoot;
        }
        return this.maxLeft(pRoot.getRight());

    }

    private Node<T> delete(Node<T> pRoot, T data) {
        if (pRoot == null) {
            return null;
        } else {
            //Si es menor, buscar en el subarbol izquierdo
            if (this.methods.isLessThan(data, pRoot.getData())) {
                pRoot.setLeft(this.delete(pRoot.getLeft(), data));
                //Si es mayor, buscar en el subarbol derecho
            } else if (this.methods.isGreaterThan(data, pRoot.getData())) {
                pRoot.setRight(this.delete(pRoot.getRight(), data));
                //Si es igual, eliminar el nodo
            } else {
                //caso1: es una hoja
                if (pRoot.getLeft() == null && pRoot.getRight() == null) {
                    pRoot = null;
                    //caso2: tiene un solo hijo
                } else if (pRoot.getLeft() == null) {
                    pRoot = pRoot.getRight();
                } else if (pRoot.getRight() == null) {
                    pRoot = pRoot.getLeft();
                    //caso3: tiene dos hijos
                } else {
                    Node<T> maxLeft = this.maxLeft(pRoot.getLeft());
                    pRoot.setData(maxLeft.getData());
                    pRoot.setLeft(this.delete(pRoot.getLeft(), maxLeft.getData()));
                }
            }
            //Retornar el nodo actual para que se actualice el enlace
            return pRoot;
        }
    }

    public void delete(T data) {
        this.delete(this.root, data);
    }

    public boolean isLeaf(Node<T> pRoot) {
        return pRoot.getLeft() == null && pRoot.getRight() == null;
    }

    public int height(Node<T> pRoot) {
        if (pRoot == null) {
            return 0;
        } else {
            return 1 + Math.max(this.height(pRoot.getLeft()), this.height(pRoot.getRight()));
        }
    }

    public int height() {
        return this.height(this.root);
    }

    public int countNodes(Node<T> pRoot) {
        if (pRoot == null) {
            return 0;
        } else {
            return 1 + this.countNodes(pRoot.getLeft()) + this.countNodes(pRoot.getRight());
        }
    }

    public int countNodes() {
        return this.countNodes(this.root);
    }

    public int countLeaves(Node<T> pRoot) {
        if (pRoot == null) {
            return 0;
        } else if (this.isLeaf(pRoot)) {
            return 1;
        } else {
            return this.countLeaves(pRoot.getLeft()) + this.countLeaves(pRoot.getRight());
        }
    }

    public int countLeaves() {
        return this.countLeaves(this.root);
    }

    private boolean isBalanced(Node<T> pRoot) {
        if (pRoot == null) {
            return true;
        } else {
            int leftHeight = this.height(pRoot.getLeft());
            int rightHeight = this.height(pRoot.getRight());
            return Math.abs(leftHeight - rightHeight) <= 1 && this.isBalanced(pRoot.getLeft()) && this.isBalanced(pRoot.getRight());
        }
    }

    public boolean isBalanced() {
        return this.isBalanced(this.root);
    }

    private boolean isComplete(Node<T> pRoot, int index, int count) {
        if (pRoot == null) {
            return true;
        } else if (index >= count) {
            return false;
        } else {
            return this.isComplete(pRoot.getLeft(), 2 * index + 1, count) && this.isComplete(pRoot.getRight(), 2 * index + 2, count);
        }
    }

    public boolean isComplete() {
        int count = this.countNodes();
        return this.isComplete(this.root, 0, count);
    }

    public boolean contains(T data) {
        return this.search(this.root, data) != null;
    }

    public void clear() {
        this.root = null;
    }

    public int level(T data) {
        return this.level(this.root, data, 0);
    }

    private int level(Node<T> pRoot, T data, int level) {
        if (pRoot == null) {
            return -1;
        } else if (this.methods.areEqual(data, pRoot.getData())) {
            return level;
        } else {
            int leftLevel = this.level(pRoot.getLeft(), data, level + 1);
            if (leftLevel != -1) {
                return leftLevel;
            } else {
                return this.level(pRoot.getRight(), data, level + 1);
            }
        }
    }

    public Node<T> search(T data) {
        return this.search(this.root, data);
    }

    public String ancestros(T data) {
        return this.ancestros(this.root, data).substring(0, this.ancestros(this.root, data).length() - 1);
    }

    private String ancestros(Node<T> pRoot, T data) {
        if (pRoot == null) {
            return "";
        } else if (this.methods.areEqual(data, pRoot.getData())) {
            return "";
        } else if (this.methods.isLessThan(data, pRoot.getData())) {
            return pRoot.getData() + "," + this.ancestros(pRoot.getLeft(), data);
        } else {
            return pRoot.getData() + "," + this.ancestros(pRoot.getRight(), data);
        }
    }


    //verifica si dos arboles binarios tienen la misma estructura
    public boolean sameStructure(BinarySearchTree<T> tree) {
        return this.sameStructure(this.root, tree.root);
    }

    private boolean sameStructure(Node<T> pRoot1, Node<T> pRoot2) {
        boolean result = true;

        if (pRoot1 == null || pRoot2 == null) {
            return false;
        } else if ( (pRoot1.getLeft() != null || pRoot2.getLeft() != null) && result) {
            result = this.sameStructure(pRoot1.getLeft(), pRoot2.getLeft());
        } else if ( (pRoot1.getRight() != null || pRoot2.getRight() != null) && result) {
            result = this.sameStructure(pRoot1.getRight(), pRoot2.getRight());
        }
        return result;
    }

    //verifica si dos arboles binarios tienen los mismos datos
    public boolean sameData(BinarySearchTree<T> tree) {
        return this.sameData(this.root, tree.root);
    }

    private boolean sameData(Node<T> pRoot1, Node<T> pRoot2) {
        boolean result = true;

        if (pRoot1 == null || pRoot2 == null) {
            return false;
        } else if (this.methods.areEqual(pRoot1.getData(), pRoot2.getData()) && result) {
            result = this.sameData(pRoot1.getLeft(), pRoot2.getLeft());
        } else if (this.methods.areEqual(pRoot1.getData(), pRoot2.getData()) && result) {
            result = this.sameData(pRoot1.getRight(), pRoot2.getRight());
        }
        return result;
    }

    //verifica si dos arboles binarios son iguales
    public boolean equals(BinarySearchTree<T> tree) {
        return this.sameStructure(tree) && this.sameData(tree);
    }

    //verifica si un arbol binario es un subarbol de otro
    public boolean isSubtree(BinarySearchTree<T> tree) {
        return this.isSubtree(this.root, tree.root);
    }

    private boolean isSubtree(Node<T> pRoot1, Node<T> pRoot2) {
        boolean result = false;

        if (pRoot1 == null || pRoot2 == null) {
            return false;
        } else if (this.methods.areEqual(pRoot1.getData(), pRoot2.getData())) {
            result = this.sameStructure(pRoot1, pRoot2) && this.sameData(pRoot1, pRoot2);
        } else if (!result) {
            result = this.isSubtree(pRoot1.getLeft(), pRoot2);
        } else if (!result) {
            result = this.isSubtree(pRoot1.getRight(), pRoot2);
        }
        return result;
    }

    //verifica si un arbol es espejo de otro
    public boolean isMirror(BinarySearchTree<T> tree) {
        return this.isMirror(this.root, tree.root);
    }

    private boolean isMirror(Node<T> pRoot1, Node<T> pRoot2) {
        boolean result = true;

        if (pRoot1 == null || pRoot2 == null) {
            return false;
        } else if (this.methods.areEqual(pRoot1.getData(), pRoot2.getData()) && result) {
            result = this.isMirror(pRoot1.getLeft(), pRoot2.getRight());
        } else if (this.methods.areEqual(pRoot1.getData(), pRoot2.getData()) && result) {
            result = this.isMirror(pRoot1.getRight(), pRoot2.getLeft());
        }
        return result;
    }

    //Print the diagram of the tree
    public void printTree() {
        this.printTree(this.root, 0);
    }

    private void printTree(Node<T> pRoot, int level) {
        if (pRoot != null) {
            this.printTree(pRoot.getRight(), level + 1);
            if (level != 0) {
                for (int i = 0; i < level - 1; i++) {
                    System.out.print("|\t");
                }
                System.out.println("|-------" + pRoot.getData());
            } else {
                System.out.println(pRoot.getData());
            }
            this.printTree(pRoot.getLeft(), level + 1);
        }
    }

}

