package implementacionesedd.ListasSimplementeEnlazada.ConOperadorTipoT.ConArrays;

public class Node<T>{
    private T data;
    private int next;
    
    public Node(){
        this.data = null;
        this.next = -1;
    }
    
    public Node(T data){
        this.data = data;
        this.next = -1;
    }

    public T getData(){
        return this.data;
    }

    public int getNext(){
        return this.next;
    }

    public void setNext(int next){
        this.next = next;
    }

    public void setData(T data){
        this.data = data;
    }
    
}
