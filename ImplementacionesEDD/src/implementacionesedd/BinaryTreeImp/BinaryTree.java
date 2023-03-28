package implementacionesedd.BinaryTreeImp;

public class BinaryTree<T> {
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

    Node<T> root;
    utilMethods<T> util = new methods();

    public BinaryTree() {
        this.util = new methods();
        this.root = null;
    }

    public BinaryTree(utilMethods<T> util) {
        this.util = util;
        this.root = null;
    }

    public Node<T> getRoot() {
        return root;
    }

    public void setRoot(Node<T> root) {
        this.root = root;
    }

    public utilMethods<T> getUtil() {
        return util;
    }

    public void setUtil(utilMethods<T> util) {
        this.util = util;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void visitar(Node<T> pRoot) {
        System.out.println(pRoot.getData());
    }

    // Recorrido en preorden (NID)
    private String preOrder(Node<T> pRoot, String path) {
        if (pRoot != null) {
            path += pRoot.getData() + ",";
            if (pRoot.getLeft() != null) {
                path = this.preOrder(pRoot.getLeft(), path);
            }
            if (pRoot.getRight() != null) {
                path = this.preOrder(pRoot.getRight(), path);
            }
        }
        return path;
    }

    public String preOrder() {
        return this.preOrder(this.root, "");
    }

    // Recorrido en inorden (IND)
    private String inOrder(Node<T> pRoot, String path) {
        if (pRoot != null) {
            if (pRoot.getLeft() != null) {
                path = this.inOrder(pRoot.getLeft(), path);
            }
            path += pRoot.getData() + ",";
            if (pRoot.getRight() != null) {
                path = this.inOrder(pRoot.getRight(), path);
            }
        }
        return path;
    }

    public String inOrder() {
        return this.inOrder(this.root, "");
    }

    // Recorrido en postorden (IDN)
    private String postOrder(Node<T> pRoot, String path) {
        if (pRoot != null) {
            if (pRoot.getLeft() != null) {
                path = this.postOrder(pRoot.getLeft(), path);
            }
            if (pRoot.getRight() != null) {
                path = this.postOrder(pRoot.getRight(), path);
            }
            path += pRoot.getData() + ",";
        }
        return path;
    }

    public String postOrder() {
        return this.postOrder(this.root, "");
    }

    // Recorrido en anchura

    // Recorrido en profundidad

    // Determinar altura
    public int height(Node<T> pRoot) {
        if (pRoot == null) {
            return 0;
        } else {
            int leftHeight = height(pRoot.getLeft());
            int rightHeight = height(pRoot.getRight());
            if (leftHeight > rightHeight) {
                return leftHeight + 1;
            } else {
                return rightHeight + 1;
            }
        }
    }

    // nivel del arbol
    public int level(Node<T> pRoot) {
        if (pRoot == null) {
            return 0;
        } else {
            int leftLevel = level(pRoot.getLeft());
            int rightLevel = level(pRoot.getRight());
            if (leftLevel > rightLevel) {
                return leftLevel + 1;
            } else {
                return rightLevel + 1;
            }
        }
    }

    // Determinar si es hoja
    public boolean isLeaf(Node<T> pRoot) {
        return pRoot.getLeft() == null && pRoot.getRight() == null;
    }

    // Determinar si es interno
    public boolean isInternal(Node<T> pRoot) {
        return !isLeaf(pRoot);
    }

    // es equilibrado
    public boolean isBalanced(Node<T> pRoot) {
        if (pRoot == null) {
            return true;
        } else {
            int leftHeight = this.height(pRoot.getLeft());
            int rightHeight = this.height(pRoot.getRight());
            return Math.abs(leftHeight - rightHeight) <= 1 && isBalanced(pRoot.getLeft())
                    && isBalanced(pRoot.getRight());
        }
    }

    // es completo
    public boolean isComplete(Node<T> pRoot) {
        if (pRoot == null) {
            return true;
        } else {
            int leftHeight = this.height(pRoot.getLeft());
            int rightHeight = this.height(pRoot.getRight());
            return leftHeight == rightHeight && isComplete(pRoot.getLeft()) && isComplete(pRoot.getRight());
        }
    }

    // es lleno
    public boolean isFull(Node<T> pRoot) {
        if (pRoot == null) {
            return true;
        } else {
            int leftHeight = this.height(pRoot.getLeft());
            int rightHeight = this.height(pRoot.getRight());
            return leftHeight == rightHeight && isFull(pRoot.getLeft()) && isFull(pRoot.getRight());
        }
    }

    // es perfecto
    public boolean isPerfect(Node<T> pRoot) {
        if (pRoot == null) {
            return true;
        } else {
            int leftHeight = this.height(pRoot.getLeft());
            int rightHeight = this.height(pRoot.getRight());
            return leftHeight == rightHeight && isPerfect(pRoot.getLeft()) && isPerfect(pRoot.getRight());
        }
    }

    // es degenerado
    public boolean isDegenerate(Node<T> pRoot) {
        if (pRoot == null) {
            return true;
        } else {
            int leftHeight = this.height(pRoot.getLeft());
            int rightHeight = this.height(pRoot.getRight());
            return leftHeight == 0 && rightHeight == 0 && isDegenerate(pRoot.getLeft())
                    && isDegenerate(pRoot.getRight());
        }
    }

    // es binario de busqueda
    // public boolean isBinarySearchTree(Node<T> pRoot) {
    // if (pRoot == null) {
    // return true;
    // } else {
    // if (pRoot.getLeft() != null && pRoot.getRight() != null) {
    // return pRoot.getData().compareTo(pRoot.getLeft().getData()) > 0
    // && pRoot.getData().compareTo(pRoot.getRight().getData()) < 0
    // && isBinarySearchTree(pRoot.getLeft()) &&
    // isBinarySearchTree(pRoot.getRight());
    // } else if (pRoot.getLeft() != null) {
    // return pRoot.getData().compareTo(pRoot.getLeft().getData()) > 0
    // && isBinarySearchTree(pRoot.getLeft());
    // } else if (pRoot.getRight() != null) {
    // return pRoot.getData().compareTo(pRoot.getRight().getData()) < 0
    // && isBinarySearchTree(pRoot.getRight());
    // } else {
    // return true;
    // }
    // }
    // }

    // num nodos
    public int numNodes(Node<T> pRoot) {
        if (pRoot == null) {
            return 0;
        } else {
            return 1 + numNodes(pRoot.getLeft()) + numNodes(pRoot.getRight());
        }
    }

    // num hojas
    public int numLeaves(Node<T> pRoot) {
        if (pRoot == null) {
            return 0;
        } else if (isLeaf(pRoot)) {
            return 1;
        } else {
            return numLeaves(pRoot.getLeft()) + numLeaves(pRoot.getRight());
        }
    }

    // num internos
    public int numInternals(Node<T> pRoot) {
        if (pRoot == null) {
            return 0;
        } else if (isInternal(pRoot)) {
            return 1 + numInternals(pRoot.getLeft()) + numInternals(pRoot.getRight());
        } else {
            return numInternals(pRoot.getLeft()) + numInternals(pRoot.getRight());
        }
    }

    // num nodos de un nivel
    public int numNodesLevel(Node<T> pRoot, int level) {
        if (pRoot == null) {
            return 0;
        } else if (level == 0) {
            return 1;
        } else {
            return numNodesLevel(pRoot.getLeft(), level - 1) + numNodesLevel(pRoot.getRight(), level - 1);
        }
    }

    // altura del arbol
    public int heightTree(Node<T> pRoot) {
        if (pRoot == null) {
            return 0;
        } else {
            int leftHeight = heightTree(pRoot.getLeft());
            int rightHeight = heightTree(pRoot.getRight());
            if (leftHeight > rightHeight) {
                return leftHeight + 1;
            } else {
                return rightHeight + 1;
            }
        }
    }

    // num nodos completos
    public int numCompleteNodes(Node<T> pRoot) {
        if (pRoot == null) {
            return 0;
        } else if (isComplete(pRoot)) {
            return 1 + numCompleteNodes(pRoot.getLeft()) + numCompleteNodes(pRoot.getRight());
        } else {
            return numCompleteNodes(pRoot.getLeft()) + numCompleteNodes(pRoot.getRight());
        }
    }

    // Buscar un nodo
    public Node<T> search(Node<T> pRoot, T data) {
        Node<T> result = null;
        if (pRoot != null) {
            if (pRoot.getData().equals(data)) {
                result = pRoot;
            } else {
                if (pRoot.getLeft() != null) {
                    result = this.search(pRoot.getLeft(), data);
                }
                if (pRoot.getRight() != null && result == null) {
                    result = this.search(pRoot.getRight(), data);
                }
            }
        }
        return result;
    }

    // Buscar nodo padre
    public Node<T> searchParent(Node<T> pRoot, T data) {
        Node<T> result = null;
        if (pRoot != null) {
            if (this.util.areEqual(pRoot.getData(), data)) {
                return pRoot;
            }
            if (pRoot.getLeft() != null && result == null) {
                result = this.searchParent(pRoot.getLeft(), data);
            }
            if (pRoot.getRight() != null && result == null) {
                result = this.searchParent(pRoot.getRight(), data);
            }
        }
        return result;
    }

    // Agregar un nodo
    public void addNode(T data, T parentData, String side) {
        Node<T> newNode = new Node<>(data);
        if (this.root == null) {
            this.root = newNode;
        } else {
            Node<T> pAux = this.searchParent(this.root, parentData);
            if (pAux == null) {
                System.out.println("No existe el nodo");
            } else if (side.equalsIgnoreCase("Left") && pAux != null) {
                pAux.setLeft(newNode);
            } else if (side.equalsIgnoreCase("Right") && pAux != null) {
                pAux.setRight(newNode);
            } else {
                System.out.println("Los nodos izq y der ya estan ocupados");
            }

        }
    }

    // Eliminar un nodo, manteniendo la estructura del arbol
    private Node<T> delete(Node<T> pRoot, T data) {
        if (pRoot != null) {
            if (this.util.areEqual(pRoot.getData(), data)) {
                if (pRoot.getLeft() == null && pRoot.getRight() == null) {
                    pRoot = null;
                } else if (pRoot.getLeft() != null && pRoot.getRight() == null) {
                    pRoot = pRoot.getLeft();
                } else if (pRoot.getLeft() == null && pRoot.getRight() != null) {
                    pRoot = pRoot.getRight();
                } else {
                    Node<T> pAux = pRoot.getRight();
                    while (pAux.getLeft() != null) {
                        pAux = pAux.getLeft();
                    }
                    pRoot.setData(pAux.getData());
                    pRoot.setRight(this.delete(pRoot.getRight(), pAux.getData()));
                }
            } else {
                pRoot.setLeft(this.delete(pRoot.getLeft(), data));
                pRoot.setRight(this.delete(pRoot.getRight(), data));
            }
        }
        return pRoot;
    }

    // Eliminar un nodo
    public void deleteNode(T data) {
        this.root = this.delete(this.root, data);
    }     

    // Imprimir el arbol
    public void printTree(Node<T> pRoot) {
        if (pRoot != null) {
            System.out.println(pRoot.getData());
            if (pRoot.getLeft() != null) {
                System.out.println("Left: " + pRoot.getLeft().getData());
            }
            if (pRoot.getRight() != null) {
                System.out.println("Right: " + pRoot.getRight().getData());
            }
            System.out.println();
            printTree(pRoot.getLeft());
            printTree(pRoot.getRight());
        }
    }

    public int countLeaves(Node<T> pRoot) {
        if (pRoot == null) {
            return 0;
        } else if (isLeaf(pRoot)) {
            return 1;
        } else {
            return countLeaves(pRoot.getLeft()) + countLeaves(pRoot.getRight());
        }
    }

    public int countNodes(Node<T> pRoot) {
        if (pRoot == null) {
            return 0;
        } else {
            return 1 + countNodes(pRoot.getLeft()) + countNodes(pRoot.getRight());
        }
    }

    // Convertir en una Arbol Binario de Busqueda, no puede haber elementos
    // repetidos
    public void convertToBinarySearchTree(Node<T> pRoot) {
        if (pRoot != null) {
            if (pRoot.getLeft() != null) {
                if (this.util.isGreaterThan(pRoot.getLeft().getData(), pRoot.getData())) {
                    Node<T> aux = pRoot.getLeft();
                    pRoot.setLeft(aux.getLeft());
                    aux.setLeft(pRoot);
                    pRoot = aux;
                }
            }
            if (pRoot.getRight() != null) {
                if (this.util.isGreaterThan(pRoot.getData(), pRoot.getRight().getData())) {
                    Node<T> aux = pRoot.getRight();
                    pRoot.setRight(aux.getRight());
                    aux.setRight(pRoot);
                    pRoot = aux;
                }
            }
            convertToBinarySearchTree(pRoot.getLeft());
            convertToBinarySearchTree(pRoot.getRight());
        }
    }

    private boolean contains(Node<T> pRoot, T data) {
        if (pRoot == null) {
            return false;
        } else if (this.util.areEqual(pRoot.getData(), data)) {
            return true;
        } else {
            return contains(pRoot.getLeft(), data) || contains(pRoot.getRight(), data);
        }
    }

    public boolean contains(T data) {
        return contains(this.root, data);
    }

    public boolean isMirror(Node<T> pRoot1, Node<T> pRoot2) {
        if (pRoot1 == null && pRoot2 == null) {
            return true;
        } else if (pRoot1 != null && pRoot2 != null) {
            return this.util.areEqual(pRoot1.getData(), pRoot2.getData())
                    && isMirror(pRoot1.getLeft(), pRoot2.getRight())
                    && isMirror(pRoot1.getRight(), pRoot2.getLeft());
        } else {
            return false;
        }
    }

    ////Print the diagram of the tree
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
