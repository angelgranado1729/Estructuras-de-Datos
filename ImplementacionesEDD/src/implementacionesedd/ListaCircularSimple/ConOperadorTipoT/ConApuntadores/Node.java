package implementacionesedd.ListaCircularSimple.ConOperadorTipoT.ConApuntadores;

public class Node<T>{

    private T data;
    private Node<T> next;

    public Node(T data) {
        this.data = data;
        this.next = this;
    }

    public Node(){
        this.data = null;
        this.next = this;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }
    
}
