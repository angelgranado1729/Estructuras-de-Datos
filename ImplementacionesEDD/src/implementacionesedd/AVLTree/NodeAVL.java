package implementacionesedd.AVLTree;

public class NodeAVL {
    
    private int data;
    private NodeAVL left;
    private NodeAVL right;
    private int BalancingFactor;

    public NodeAVL(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.BalancingFactor = 1;
    }

    public NodeAVL() {
        this.left = null;
        this.right = null;
        this.BalancingFactor = 1;
    }

    public int getData() {
        return this.data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public NodeAVL getLeft() {
        return this.left;
    }

    public void setLeft(NodeAVL left) {
        this.left = left;
    }

    public NodeAVL getRight() {
        return this.right;
    }

    public void setRight(NodeAVL right) {
        this.right = right;
    }

    public int getBalancingFactor() {
        return BalancingFactor;
    }

    public void setBalancingFactor(int BalancingFactor) {
        this.BalancingFactor = BalancingFactor;
    }

    @Override
    public String toString() {
        return "Node{" + "data=" + data + ", left=" + left + ", right=" + right 
        + "BalancingFactor" +  this.getBalancingFactor() + '}';
    }

}
