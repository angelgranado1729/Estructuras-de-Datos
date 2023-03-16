package implementacionesedd.ListaCircularSimple.ConOperadorTipoT.ConArrays;

public class Node<T> {

    private T data;
    private int next;

    public Node(T data) {
        this.data = data;
        this.next = -1;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node{" + "data=" + data + ", next=" + next + '}';
    } 
    
}
