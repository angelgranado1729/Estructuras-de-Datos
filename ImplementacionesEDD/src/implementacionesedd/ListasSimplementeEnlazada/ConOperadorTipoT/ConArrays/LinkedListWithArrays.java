package implementacionesedd.ListasSimplementeEnlazada.ConOperadorTipoT.ConArrays;

public class LinkedListWithArrays<T> {

    // Interfaz para definir los métodos que se pueden sobreescribir
    public interface LinkedListMethods<T>{
        int compareTo(T tInfo1, T tInfo2);
        void print(T tInfo);
        Node<T> search(T tInfo);
        int hashCode(T tInfo);
        boolean equals(T tInfo1, T tInfo2);
    }

    // // Clase privada para definir los métodos por defecto
    private class DefaulMethods implements LinkedListMethods<T>{
        @Override
        public int compareTo(T tInfo1, T tInfo2) {
            return tInfo1.toString().compareTo(tInfo2.toString());
        }

        @Override
        public void print(T tInfo) {
            System.out.println(tInfo.toString());
        }

        @Override
        public Node<T> search(T tInfo) {
            int p = first;
            while (p != -1){
                if (methods.equals(tInfo, nodes[p].getData())){
                    return nodes[p];
                }
                p = nodes[p].getNext();
            }
            return null;
        }

        @Override
        public int hashCode(T tInfo) {
            return tInfo.hashCode();
        }

        @Override
        public boolean equals(T tInfo1, T tInfo2) {
            return tInfo1.equals(tInfo2);
        }
    }
     
    private int first;
    private int last;
    private int size;
    private int max;
    private Node<T>[] nodes;
    private LinkedListMethods<T> methods;


    public LinkedListWithArrays(int max){
        this.first = -1;
        this.last = -1;
        this.size = 0;
        this.max = max;
        this.nodes = new Node[max];
        this.methods = new DefaulMethods();
    }

    public LinkedListWithArrays(int max, LinkedListMethods<T> methods){
        this.first = -1;
        this.last = -1;
        this.size = 0;
        this.max = max;
        this.nodes = new Node[max];
        this.methods = methods;
    }

    public LinkedListWithArrays(){
        this.first = -1;
        this.last = -1;
        this.size = 0;
        this.max = 15;
        this.nodes = new Node[15];
        this.methods = new DefaulMethods();
    }

    public LinkedListWithArrays(LinkedListMethods<T> methods){
        this.first = -1;
        this.last = -1;
        this.size = 0;
        this.max = 15;
        this.nodes = new Node[15];
        this.methods = methods;
    }

    public void setMethods(LinkedListMethods<T> methods){
        this.methods = methods;
    }

    public LinkedListMethods<T> getMethods(){
        return this.methods;
    }

    public boolean isEmpty(){
        return this.first == -1;
    }

    public boolean isFull(){
        return this.size == this.max;
    }

    public int size(){
        return this.size;
    }

    public int first(){
        return this.first;
    }

    public int last(){
        return this.last;
    }

    public int next(int p){
        if (p < 0 || p >= this.max){
            return -1;
        } else if (this.nodes[p] == null){
            return -1;
        } else {
            return this.nodes[p].getNext();
        }
    }

    public T get(int p){
        if (p < 0 || p >= this.max){
            return null;
        } else if (this.nodes[p] == null){
            return null;
        } else {
            return this.nodes[p].getData();
        }
    }

    public int searchFreeSlot(){
        for (int i = 0; i < this.max; i++){
            if (this.nodes[i] == null){
                return i;
            }
        }
        return -1;
    }

    public String toString(){
        String result = "";
        int p = this.first;
        while (p != -1){
            result += this.nodes[p].getData().toString() + "\n";
            p = this.nodes[p].getNext();
        }
        return result;
    }

    public void addFirst(T data){
        if (this.isFull()){
            return;
        }
        int p = this.searchFreeSlot();
        this.nodes[p] = new Node<T>(data);
        
        if (isEmpty()){
            this.first = this.last = p;
        } else{
            this.nodes[p].setNext(this.first);
            this.first = p;
        }
        this.size++;
    }

    public void addLast(T data){
        if (this.isFull()){
            return;
        }
        int p = this.searchFreeSlot();
        this.nodes[p] = new Node<T>(data);
        
        if (isEmpty()){
            this.first = this.last = p;
        } else{
            this.nodes[this.last].setNext(p);
            this.last = p;
        }
        this.size++;
    }

    public void insert(T data, int position){
        if (!isFull()){
            if (position == 0){
                this.addFirst(data);
            } else if (position == this.size-1){
                this.addLast(data);
            } else if (position > 0 && position < this.size -1){
                int free = searchFreeSlot();
                nodes[free] = new Node<T>(data);
                int p = this.first;
                int count = 0;
                while (p != -1 && count != position-1){
                    p = next(p);
                    count++;
                }
                nodes[free].setNext(nodes[p].getNext());
                nodes[p].setNext(free);
                this.size++;
            } else {
                return;
            }
        }
    }

    //the complexity of this algorithm is O(n^2)
    public void sort(){
        if (!isEmpty()){
            int p = this.first;
            int q = this.first;
            while (p != -1){
                q = p;
                while (q != -1){
                    if (methods.compareTo(this.nodes[p].getData(), this.nodes[q].getData()) < 0){
                        T temp = this.nodes[p].getData();
                        this.nodes[p].setData(this.nodes[q].getData());
                        this.nodes[q].setData(temp);
                    }
                    q = this.nodes[q].getNext();
                }
                p = this.nodes[p].getNext();
            }
        }
    }

    public void removeFirst(){
        if (!isEmpty()){
            int p = this.first;
            if (this.first == this.last){
                nodes[this.first] = null;
                this.first = this.last = -1;
            } else {
                this.first = this.nodes[this.first].getNext();
                nodes[p] = null;
            }
            this.size--;
        }
    }

    public void removeLast(){
        if (!isEmpty()){
            int p = this.first;
            if (this.first == this.last){
                nodes[this.first] = null;
                this.first = this.last = -1;
            } else {
                while (this.nodes[p].getNext() != this.last){
                    p = this.nodes[p].getNext();
                }
                nodes[this.last] = null;
                this.last = p;
                this.nodes[this.last].setNext(-1);
            }
            this.size--;
        }
    }

    public void remove(int position){
        if (!isEmpty()){
            if (position == 0){
                this.removeFirst();
            } else if (position == this.size-1){
                this.removeLast();
            } else if (position > 0 && position < this.size -1){
                int p = this.first;
                int count = 0;
                while (p != -1 && count != position-1){
                    p = next(p);
                    count++;
                }
                int q = this.nodes[p].getNext();
                this.nodes[p].setNext(this.nodes[q].getNext());
                nodes[q] = null;
                this.size--;
            }
        }
    }

    public void remove(T data){
        if (!isEmpty()){
            int p = this.first;
            int count = 0;
            while (p != -1 && !methods.equals(this.nodes[p].getData(), data)){
                p = next(p);
                count++;
            }
            if (p != -1){
                remove(count);
            }
        }
    }

    public Node<T> deleteAndRNode(int position){
        if (!isEmpty()){
            if (position == 0){
                Node<T> temp = this.nodes[this.first];
                this.removeFirst();
                return temp;
            } else if (position == this.size-1){
                Node<T> temp = this.nodes[this.last];
                this.removeLast();
                return temp;
            } else if (position > 0 && position < this.size -1){
                int p = this.first;
                int count = 0;
                while (p != -1 && count != position-1){
                    p = next(p);
                    count++;
                }
                int q = this.nodes[p].getNext();
                this.nodes[p].setNext(this.nodes[q].getNext());
                Node<T> temp = this.nodes[q];
                nodes[q] = null;
                this.size--;
                return temp;
            }
        }
        return null;
    }

    public Node<T> deleteAndRNode(T data){
        if (!isEmpty()){
            int p = this.first;
            int count = 0;
            while (p != -1 && !methods.equals(this.nodes[p].getData(), data)){
                p = next(p);
                count++;
            }
            if (p != -1){
                return deleteAndRNode(count);
            }
        }
        return null;
    }

    public void addWithOrder(T data){
        if (!isFull()){
            int p = searchFreeSlot();
            nodes[p] = new Node<T>(data);
            sort();
        }
    }

    public void reverse(){
        Node<T> temp = nodes[first];
        if (temp != null){
            removeFirst();
            reverse();
            addLast(temp.getData());
        }
    }

    public LinkedListWithArrays<T> returnReverse(){
        LinkedListWithArrays<T> temp = new LinkedListWithArrays<T>(this.max, this.methods);
        int p = this.first;
        while (p != -1){
            temp.addFirst(this.nodes[p].getData());
            p = this.nodes[p].getNext();
        }
        temp.reverse();
        return temp;
    }

    public LinkedListWithArrays<T> copy(){
        LinkedListWithArrays<T> temp = new LinkedListWithArrays<T>(this.max, this.methods);
        int p = this.first;
        while (p != -1){
            temp.addLast(this.nodes[p].getData());
            p = this.nodes[p].getNext();
        }
        return temp;
    }

    public boolean detectLoop(){
        if (!isEmpty()){
            int fast = this.first;
            int slow = this.first;
            while (fast != -1 && slow != -1 && this.nodes[fast].getNext() != -1){
                fast = next(nodes[fast].getNext());
                slow = this.nodes[slow].getNext();
                if (fast == slow){
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public int findLoopStart(){
        if (!isEmpty()){
            int fast = this.first;
            int slow = this.first;
            while (fast != -1 && slow != -1 && this.nodes[fast].getNext() != -1){
                fast = next(nodes[fast].getNext());
                slow = this.nodes[slow].getNext();
                if (fast == slow){
                    break;
                }
            }
            if (fast == slow){
                slow = this.first;
                while (fast != slow){
                    fast = this.nodes[fast].getNext();
                    slow = this.nodes[slow].getNext();
                }
                return fast;
            }
        }
        return -1;
    }

    public void removeLoop(){
        if (!isEmpty()){
            int fast = this.first;
            int slow = this.first;
            while (fast != -1 && slow != -1 && this.nodes[fast].getNext() != -1){
                fast = next(nodes[fast].getNext());
                slow = this.nodes[slow].getNext();
                if (fast == slow){
                    break;
                }
            }
            if (fast == slow){
                slow = this.first;
                while (fast != slow){
                    fast = this.nodes[fast].getNext();
                    slow = this.nodes[slow].getNext();
                }
                int p = this.first;
                while (this.nodes[p].getNext() != fast){
                    p = this.nodes[p].getNext();
                }
                this.nodes[p].setNext(-1);
                this.last = p;
            }
        }
    }

    public void set(int position, T data){
        if (!isEmpty()){
            if (position == 0){
                this.nodes[this.first].setData(data);
            } else if (position == this.size-1){
                this.nodes[this.last].setData(data);
            } else if (position > 0 && position < this.size -1){
                int p = this.first;
                int count = 0;
                while (p != -1 && count != position){
                    p = next(p);
                    count++;
                }
                this.nodes[p].setData(data);
            }
        }
    }

    public void clear(){
        for (int i = 0; i < this.max; i++){
            this.nodes[i] = null;
        }
        this.first = -1;
        this.last = -1;
        this.size = 0;
    }

    public void print(){
        if (!isEmpty()){
            int p = this.first;
            while (p != -1){
                System.out.println(this.nodes[p].getData().toString());
                p = this.nodes[p].getNext();
            }
        }
    }

}