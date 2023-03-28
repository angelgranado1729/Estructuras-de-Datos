package implementacionesedd.AVLTree;

public class AVLTree {

    private NodeAVL root;

    public AVLTree() {
        this.root = null;
    }

    public NodeAVL getRoot() {
        return root;
    }

    public void setRoot(NodeAVL root) {
        this.root = root;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    // num de nodos
    public int size(NodeAVL pRoot) {
        if (this.isEmpty()) {
            return 0;
        } else {
            return 1 + this.size(pRoot.getLeft()) + this.size(pRoot.getRight());
        }
    }

    public int height(NodeAVL node) {
        if (node == null)
            return 0;

        return node.getBalancingFactor();
    }

    // A utility function to get maximum of two integers
    public int max(int a, int b) {
        return (a > b) ? a : b;
    }

    // A utility function to right rotate subtree rooted with y
    // See the diagram given above.
    private NodeAVL rightRotate(NodeAVL y) {
        NodeAVL x = y.getLeft();
        NodeAVL T2 = x.getRight();

        // Perform rotation
        x.setRight(y);
        y.setLeft(T2);

        // Update heights
        y.setBalancingFactor(max(height(y.getLeft()), height(y.getRight())) + 1);
        x.setBalancingFactor(max(height(x.getLeft()), height(x.getRight())) + 1);

        // Return new root
        return x;
    }

    // A utility function to left rotate subtree rooted with x
    // See the diagram given above.
    private NodeAVL leftRotate(NodeAVL x) {
        NodeAVL y = x.getRight();
        NodeAVL T2 = y.getLeft();

        // Perform rotation
        y.setLeft(x);
        x.setRight(T2);

        // Update heights
        x.setBalancingFactor(max(height(x.getLeft()), height(x.getRight())) + 1);
        y.setBalancingFactor(max(height(y.getLeft()), height(y.getRight())) + 1);

        // Return new root
        return y;
    }

    // Get Balance factor of node N
    private int getBalance(NodeAVL node) {
        if (node == null)
            return 0;

        return height(node.getLeft()) - height(node.getRight());
    }

    public void insert(int data) {
        this.root = insert(this.root, data);
    }
    private NodeAVL insert(NodeAVL node, int data) {

        /* 1. Perform the normal BST insertion */
        if (node == null)
            return (new NodeAVL(data));

        if (data < node.getData())
            node.setLeft(insert(node.getLeft(), data));
        else if (data > node.getData())
            node.setRight(insert(node.getRight(), data));
        else // Duplicate keys not allowed
            return node;

        /* 2. Update height of this ancestor node */
        node.setBalancingFactor(1 + max(height(node.getLeft()),
                height(node.getRight())));

        /*
         * 3. Get the balance factor of this ancestor
         * node to check whether this node became
         * unbalanced
         */
        int balance = getBalance(node);

        // If this node becomes unbalanced, then there
        // are 4 cases Left Left Case
        if (balance > 1 && data < node.getLeft().getData()) {
            return rightRotate(node);
        }

        // Right Right Case
        if (balance < -1 && data > node.getRight().getData()) {
            return leftRotate(node);
        }

        // Left Right Case
        if (balance > 1 && data > node.getLeft().getData()) {
            node.setLeft(leftRotate(node.getLeft()));
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && data < node.getRight().getData()) {
            node.setRight(rightRotate(node.getRight()));
            return leftRotate(node);
        }

        /* return the (unchanged) node pointer */
        return node;
    }

    // A utility function to print preorder traversal
    // of the tree.
    // The function also prints height of every node
    private void preOrder(NodeAVL node) {
        if (node != null) {
            System.out.print(node.getData() + " ");
            preOrder(node.getLeft());
            preOrder(node.getRight());
        }
    }

    public void preOrder() {
        preOrder(this.root);
    }


    /* Given a non-empty binary search tree, return the
    node with minimum key value found in that tree.
    Note that the entire tree does not need to be
    searched. */
    private NodeAVL minValueNode(NodeAVL node)
    {
        NodeAVL current = node;
 
        /* loop down to find the leftmost leaf */
        while (current.getLeft() != null)
        current = current.getLeft();
 
        return current;
    }

    public void delete(int data) {
        this.root = deleteNode(this.root, data);
    }
 
    private NodeAVL deleteNode(NodeAVL root, int data)
    {
        // STEP 1: PERFORM STANDARD BST DELETE
        if (root == null)
            return root;
 
        // If the key to be deleted is smaller than
        // the root's key, then it lies in left subtree
        if (data < root.getData())
            root.setLeft(deleteNode(root.getLeft(), data));
 
        // If the key to be deleted is greater than the
        // root's key, then it lies in right subtree
        else if (data > root.getData())
            root.setRight(deleteNode(root.getRight(), data));
 
        // if key is same as root's key, then this is the node
        // to be deleted
        else
        {
 
            // node with only one child or no child
            if ((root.getLeft() == null) || (root.getRight() == null))
            {
                NodeAVL temp = null;
                if (temp == root.getLeft())
                    temp = root.getRight();
                else
                    temp = root.getLeft();
 
                // No child case
                if (temp == null)
                {
                    temp = root;
                    root = null;
                }
                else // One child case
                    root = temp; // Copy the contents of
                                // the non-empty child
            }
            else
            {
 
                // node with two children: Get the inorder
                // successor (smallest in the right subtree)
                NodeAVL temp = minValueNode(root.getRight());
 
                // Copy the inorder successor's data to this node
                root.setData(temp.getData());
 
                // Delete the inorder successor
                root.setRight(deleteNode(root.getRight(), temp.getData()));
            }
        }
 
        // If the tree had only one node then return
        if (root == null)
            return root;
 
        // STEP 2: UPDATE HEIGHT OF THE CURRENT NODE
        root.setBalancingFactor(max(height(root.getLeft()), height(root.getRight())) + 1);
 
        // STEP 3: GET THE BALANCE FACTOR OF THIS NODE (to check whether
        // this node became unbalanced)
        int balance = getBalance(root);
 
        // If this node becomes unbalanced, then there are 4 cases
        // Left Left Case
        if (balance > 1 && getBalance(root.getLeft()) >= 0)
            return rightRotate(root);
 
        // Left Right Case
        if (balance > 1 && getBalance(root.getLeft()) < 0)
        {
            root.setLeft(leftRotate(root.getLeft()));
            return rightRotate(root);
        }
 
        // Right Right Case
        if (balance < -1 && getBalance(root.getRight()) <= 0)
            return leftRotate(root);
 
        // Right Left Case
        if (balance < -1 && getBalance(root.getRight()) > 0)
        {
            root.setRight(rightRotate(root.getRight()));
            return leftRotate(root);
        }
 
        return root;
    }

    //Print the tree
    public void printTree() {
        printTree(this.root, 0);
    }

    //Print the diagram of the tree
    private void printTree(NodeAVL node, int level) {
        if (node != null) {
            printTree(node.getRight(), level + 1);
            if (level != 0) {
                for (int i = 0; i < level - 1; i++)
                    System.out.print("|\t");
                System.out.println("|-------" + node.getData());
            } else
                System.out.println(node.getData());
            printTree(node.getLeft(), level + 1);
        }
    }

}
