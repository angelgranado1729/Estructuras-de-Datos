package implementacionesedd.ListaDoblementeEnlazada.ConApuntadores;

public class Node<T> {
    private T data;
    private Node<T> pNext;
    private Node<T> pPrev;

    public Node(){
        this.data = null;
        this.pNext = null;
        this.pPrev = null;
    }

    public Node(T data){
        this.data = data;
        this.pNext = null;
        this.pPrev = null;
    }

    public T getData(){
        return this.data;
    }

    public Node<T> getNext(){
        return this.pNext;
    }

    public Node<T> getPrev(){
        return this.pPrev;
    }

    public void setNext(Node<T> pNext){
        this.pNext = pNext;
    }

    public void setPrev(Node<T> pPrev){
        this.pPrev = pPrev;
    }

    public void setData(T data){
        this.data = data;
    }

}
