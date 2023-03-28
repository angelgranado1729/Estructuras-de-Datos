package implementacionesedd.Stack;

public class Stack<T>{
    private Node<T> top;
    private int size;

    public Stack(){
        this.top = null;
        this.size = 0;
    }

    public boolean isEmpty(){
        return this.top == null;
    }

    public int getSize(){
        return this.size;
    }

    public Node<T> getTop(){
        return this.top;
    }

    public void setTop(Node<T> top){
        this.top = top;
    }

    public void push(T data){
        Node<T> newNode = new Node<>(data);
        newNode.setNextNode(this.top);
        this.top = newNode;
        this.size++;
    }

    public Node<T> pushAndReturn(T data){
        Node<T> newNode = new Node<>(data);
        newNode.setNextNode(this.top);
        this.top = newNode;
        this.size++;
        return newNode;
    }

    public Node<T> popAndReturn(){
        if(this.isEmpty()){
            return null;
        }
        Node<T> temp = this.top;
        this.top = this.top.getNextNode();
        this.size--;
        return temp;
    }

    public void pop(){
        if(this.isEmpty()){
            return;
        }
        this.top = this.top.getNextNode();
        this.size--;
    }

    public void printStack(){
        Node<T> temp = this.top;
        while(temp != null){
            System.out.println(temp.getTInfo());
            temp = temp.getNextNode();
        }
    }

    @Override
    public String toString(){
        String str = "";
        Node<T> temp = this.top;
        while(temp != null){
            str += temp.getTInfo() + " ";
            temp = temp.getNextNode();
        }
        return str;
    }

    public T peek(){
        if(this.isEmpty()){
            return null;
        }
        return this.top.getTInfo();
    }

}
