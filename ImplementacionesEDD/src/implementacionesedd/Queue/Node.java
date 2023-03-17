package implementacionesedd.Queue;

public class Node<T> {

    /**
     * La infomacion a guardad en el nodo.
     */
    private T tInfo;
    /**
     * El siguiente nodo.
     */
    private Node<T> pNextNode;

    /**
     * Constructor de la clase.
     *
     * @param tInfo, la informacion a almacenar en el nodo.
     */
    public Node(T tInfo) {
        this.tInfo = tInfo;
        this.pNextNode = null;
    }

    /**
     * Constructor de la clase.
     */
    public Node() {
        this.tInfo = null;
        this.pNextNode = null;
    }

    /**
     * Getter para acceder a la informacion que guarda el nodo.
     *
     * @return
     */
    public T getTInfo() {
        return this.tInfo;
    }

    /**
     * Setter para modificar la informacion que guarda el nodo.
     *
     * @param tInfo
     */
    public void setTInfo(T tInfo) {
        this.tInfo = tInfo;
    }

    /**
     * Getter para acceder al siguiente nodo enlazado.
     *
     * @return el nodo siguiente que esta enlazado.
     */
    public Node<T> getNextNode() {
        return this.pNextNode;
    }

    /**
     * Setter para nodo siguiente que esta enlazado.
     *
     * @param pNextNode
     */
    public void setNextNode(Node<T> pNextNode) {
        this.pNextNode = pNextNode;
    }

    @Override
    public String toString() {
        return this.tInfo.toString();
    }

    public void addAttribute(String uilabel, String toString) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
