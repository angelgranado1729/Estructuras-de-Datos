package implementacionesedd.ListaDoblementeEnlazada.ConArrays;

public class Node<T>{
    private T data;
    private int next;
    private int prev;
    
    public Node(){
        this.data = null;
        this.next = -1;
        this.prev = -1;
    }
    
    public Node(T data){
        this.data = data;
        this.next = -1;
        this.prev = -1;
    }

    public T getData(){
        return this.data;
    }

    public int getNext(){
        return this.next;
    }

    public int getPrev(){
        return this.prev;
    }

    public void setNext(int next){
        this.next = next;
    }

    public void setPrev(int prev){
        this.prev = prev;
    }

    public void setData(T data){
        this.data = data;
    }

}
