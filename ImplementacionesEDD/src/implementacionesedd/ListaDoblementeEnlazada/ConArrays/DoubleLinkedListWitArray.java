package implementacionesedd.ListaDoblementeEnlazada.ConArrays;

public class DoubleLinkedListWitArray<T> {

    // Interfaz para definir los métodos que se pueden sobreescribir
    public interface ListMethods<T>{
        int compareTo(T tInfo1, T tInfo2);
        void print(T tInfo);
        Node<T> search(T tInfo);
        int hashCode(T tInfo);
        boolean equals(T tInfo1, T tInfo2);
    }

    // // Clase privada para definir los métodos por defecto
    private class DefaulMethods implements ListMethods<T>{
        @Override
        public int compareTo(T tInfo1, T tInfo2) {
            return 0;
        }

        @Override
        public void print(T tInfo) {
            System.out.println(tInfo);
        }

        @Override
        public Node<T> search(T tInfo) {
            return null;
        }

        @Override
        public int hashCode(T tInfo) {
            return 0;
        }

        @Override
        public boolean equals(T tInfo1, T tInfo2) {
            return false;
        }
    }

    private Node<T>[] nodes;
    private int size;
    private ListMethods<T> methods;
    private int max;
    private int first;
    private int last;

    public DoubleLinkedListWitArray(int max) {
        this.max = max;
        this.nodes = new Node[max];
        this.size = 0;
        this.methods = new DefaulMethods();
        this.first = -1;
        this.last = -1;
    }

    public DoubleLinkedListWitArray(int max, ListMethods<T> methods) {
        this.max = max;
        this.nodes = new Node[max];
        this.size = 0;
        this.methods = methods;
        this.first = -1;
        this.last = -1;
    }

    public DoubleLinkedListWitArray(ListMethods<T> methods) {
        this.max = 15;
        this.nodes = new Node[15];
        this.size = 0;
        this.methods = methods;
        this.first = -1;
        this.last = -1;
    }

    public DoubleLinkedListWitArray() {
        this.max = 15;
        this.nodes = new Node[15];
        this.size = 0;
        this.methods = new DefaulMethods();
        this.first = -1;
        this.last = -1;
    }

    public void setMethods(ListMethods<T> methods) {
        this.methods = methods;
    }

    public ListMethods<T> getMethods() {
        return methods;
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

    public int next(int p){
        if (p < 0 || p >= this.max){
            return -1;
        } else if (this.nodes[p] == null){
            return -1;
        } else {
            return this.nodes[p].getNext();
        }
    }

    public int prev(int p){
        if (p < 0 || p >= this.max){
            return -1;
        } else if (this.nodes[p] == null){
            return -1;
        } else {
            return this.nodes[p].getPrev();
        }
    }

    public T get(int index){
        if (!isEmpty()){
            if (index == 0){
                return this.nodes[this.first].getData();
            } else if (index == this.size-1){
                return this.nodes[this.last].getData();
            } else if (index > 0 && index < this.size -1){
                int p = this.first;
                int count = 0;
                while (p != -1 && count != index){
                    p = next(p);
                    count++;
                }
                return this.nodes[p].getData();
            }
        }
        return null;
    }

    public void set(int index, T tInfo){
        if (!isEmpty()){
            if (index == 0){
                this.nodes[this.first].setData(tInfo);
            } else if (index == this.size-1){
                this.nodes[this.last].setData(tInfo);
            } else if (index > 0 && index < this.size -1){
                int p = this.first;
                int count = 0;
                while (p != -1 && count != index){
                    p = next(p);
                    count++;
                }
                this.nodes[p].setData(tInfo);
            }
        }
    }

    public int first(){
        return this.first;
    }

    public int last(){
        return this.last;
    }

    public int searchFreeSlot(){
        for (int i = 0; i < this.max; i++){
            if (this.nodes[i] == null){
                return i;
            }
        }
        return -1;
    }

    public int search(T tInfo){
        for (int i = 0; i < this.max; i++){
            if (this.nodes[i] != null && this.methods.equals(this.nodes[i].getData(), tInfo)){
                return i;
            }
        }
        return -1;
    }

    public void addFirst(T tInfo){
        if (!isFull()){
            int p = searchFreeSlot();
            this.nodes[p] = new Node<>(tInfo);
            if (isEmpty()){
                this.first = p;
                this.last = p;
            } else {
                this.nodes[p].setNext(this.first);
                this.nodes[this.first].setPrev(p);
                this.first = p;
            }
            this.size++;
        }
    }

    public void addLast(T tInfo){
        if (!isFull()){
            int p = searchFreeSlot();
            this.nodes[p] = new Node<>(tInfo);
            if (isEmpty()){
                this.first = p;
                this.last = p;
            } else {
                this.nodes[p].setPrev(this.last);
                this.nodes[this.last].setNext(p);
                this.nodes[p].setNext(-1);
                this.last = p;
            }
            this.size++;
        }
    }

    public void add(int index, T tInfo){
        if (!isFull()){
            if (index == 0){
                addFirst(tInfo);
            } else if (index == this.size -1){
                addLast(tInfo);
            } else if (index > 0 && index < this.size -1){
                int prev = this.first;
                int count = 0;
                while (prev != -1 && count != index-1){
                    prev = next(prev);
                    count++;
                }
                int next = next(prev);
                int p = searchFreeSlot();
                this.nodes[p] = new Node<>(tInfo);
                this.nodes[p].setPrev(prev);
                this.nodes[p].setNext(next);
                this.nodes[prev].setNext(p);
                this.nodes[next].setPrev(p);
                this.size++;
            } else {
                return;
            }
        }
    }

    public void removeFirst(){
        if (!isEmpty()){
            if (this.first == this.last){
                this.nodes[this.first] = null;
                this.first = -1;
                this.last = -1;
            } else {
                int p = this.first;
                this.first = next(p);
                this.nodes[p] = null;
                this.nodes[this.first].setPrev(-1);
            }
            this.size--;
        }
    }

    public void removeLast(){
        if (!isEmpty()){
            if (this.first == this.last){
                this.nodes[this.first] = null;
                this.first = -1;
                this.last = -1;
            } else {
                int p = this.last;
                this.last = prev(p);
                this.nodes[p] = null;
                this.nodes[this.last].setNext(-1);
            }
            this.size--;
        }
    }

    public void remove(int index){
        if (!isEmpty()){
            if (index == 0){
                removeFirst();
            } else if (index == this.size -1){
                removeLast();
            } else if (index > 0 && index < this.size -1){
                int p = this.first;
                int count = 0;
                while (p != -1 && count != index){
                    p = next(p);
                    count++;
                }
                int prev = prev(p);
                int next = next(p);
                this.nodes[p] = null;
                this.nodes[prev].setNext(next);
                this.nodes[next].setPrev(prev);
                this.size--;
            }
        }
    }

    public void remove(T tInfo){
        if (!isEmpty()){
            int p = search(tInfo);
            if (p != -1){
                if (p == this.first){
                    removeFirst();
                } else if (p == this.last){
                    removeLast();
                } else {
                    int prev = prev(p);
                    int next = next(p);
                    this.nodes[p] = null;
                    this.nodes[prev].setNext(next);
                    this.nodes[next].setPrev(prev);
                    this.size--;
                }
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

    //using recursion
    public void reverse(){
        Node<T> temp = this.nodes[this.first];
        if (temp != null){
            removeFirst();
            reverse();
            addLast(temp.getData());
        }
    }

    public DoubleLinkedListWitArray<T> returnReverse(){
        DoubleLinkedListWitArray<T> list = new DoubleLinkedListWitArray<>(this.max, this.methods);
        Node<T> temp = this.nodes[this.first];
        if (temp != null){
            removeFirst();
            list = returnReverse();
            list.addLast(temp.getData());
            addFirst(temp.getData());
        }
        return list;
    }
    
    public DoubleLinkedListWitArray<T> copy(){
        DoubleLinkedListWitArray<T> list = new DoubleLinkedListWitArray<>(this.max, this.methods);
        Node<T> temp = this.nodes[this.first];
        if (temp != null){
            removeFirst();
            list = copy();
            list.addLast(temp.getData());
            addFirst(temp.getData());
        }
        return list;
    }

    public void print(){
        int p = this.first;
        while (p != -1){
            System.out.println(this.nodes[p].getData().toString());
            p = next(p);
        }
    }

    public String toString(){
        String str = "";
        int p = this.first;
        while (p != -1){
            str += this.nodes[p].getData().toString() + "\n";
            p = next(p);
        }
        return str;
    }

    //use bubble sort
    public void sort(){
        int p = this.first;
        int q = next(p);
        while (p != -1){
            while (q != -1){
                if (this.methods.compareTo(this.nodes[p].getData(), this.nodes[q].getData()) > 0){
                    T temp = this.nodes[p].getData();
                    this.nodes[p].setData(this.nodes[q].getData());
                    this.nodes[q].setData(temp);
                }
                q = next(q);
            }
            p = next(p);
            q = next(p);
        }
    }

    //// Se cerea una clase Comparator<T> que implemente el metodo compare(T t1, T t2) y devuelva un entero
    // public void sort(Comparator<T> comparator){
    //     int p = this.first;
    //     int q = next(p);
    //     while (p != -1){
    //         while (q != -1){
    //             if (comparator.compare(this.nodes[p].getData(), this.nodes[q].getData()) > 0){
    //                 T temp = this.nodes[p].getData();
    //                 this.nodes[p].setData(this.nodes[q].getData());
    //                 this.nodes[q].setData(temp);
    //             }
    //             q = next(q);
    //         }
    //         p = next(p);
    //         q = next(p);
    //     }
    // }

    public void addWithOrder(T tInfo){
        if (isEmpty()){
            addFirst(tInfo);
        } else {
            addFirst(tInfo);
            sort();
        }
    }

    // public void addWithOrder(T tInfo, Comparator<T> comparator){
    //     if (isEmpty()){
    //         addFirst(tInfo);
    //     } else {
    //         addFirst(tInfo);
    //         sort(comparator);
    //     }
    // }

    public boolean detectLoop(){
        int fast = this.first;
        int slow = this.first;
        while (fast != -1 && next(fast) != -1 & slow != -1){
            fast = next(next(fast));
            slow = next(slow);
            if (fast == slow){
                return true;
            }
        }
        return false;
    }

    public Node<T> findLoopStarNode(){
        int fast = this.first;
        int slow = this.first;
        while (fast != -1 && next(fast) != -1 & slow != -1){
            fast = next(next(fast));
            slow = next(slow);
            if (fast == slow){
                break;
            }
        }
        if (fast == -1 || next(fast) == -1 || slow == -1){
            return null;
        }
        slow = this.first;
        while (fast != slow){
            fast = next(fast);
            slow = next(slow);
        }
        return this.nodes[fast];
    }

    public void removeLoop(){
        int fast = this.first;
        int slow = this.first;
        while (fast != -1 && next(fast) != -1 & slow != -1){
            fast = next(next(fast));
            slow = next(slow);
            if (fast == slow){
                break;
            }
        }
        if (fast == -1 || next(fast) == -1 || slow == -1){
            return;
        }
        slow = this.first;
        while (fast != slow){
            fast = next(fast);
            slow = next(slow);
        }
        int p = fast;
        int count = 0;
        while (next(p) != fast){
            p = next(p);
            count++;
        }
        this.nodes[p].setNext(-1);
        this.last = p;
        this.size -= count;
    }



    
}
