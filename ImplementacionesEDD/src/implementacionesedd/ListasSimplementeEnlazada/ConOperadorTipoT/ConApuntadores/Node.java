package implementacionesedd.ListasSimplementeEnlazada.ConOperadorTipoT.ConApuntadores;

public class Node<T> {
    private T tInfo;
    private Node<T> pNextNode;

    public Node(T tInfo){
        this.tInfo = tInfo;
        this.pNextNode = null;
    }

    public Node(){
        this.tInfo = null;
        this.pNextNode = null;
    }

    public T getTInfo(){
        return this.tInfo;
    }

    public void setTInfo(T tInfo){
        this.tInfo = tInfo;
    }

    public Node<T> getNextNode(){
        return this.pNextNode;
    }

    public void setNextNode(Node<T> pNextNode){
        this.pNextNode = pNextNode;
    }
    
    @Override
    public String toString(){
        return this.tInfo.toString();
    }
}
