package implementacionesedd.ListaCircularSimple.ConOperadorTipoT.ConArrays;

public class CircularListWithArrays<T> {

    public interface ListMethods<T>{
        public int compareTo(T d1, T d2);
        public boolean equals(T d1, T d2);
        public int hashCode(T d);
        public String toString(T d);
    }

    private class DefaulMethods implements ListMethods<T>{
        @Override
        public int compareTo(T d1, T d2) {
            return d1.toString().compareToIgnoreCase(d2.toString());
        }

        @Override
        public boolean equals(T d1, T d2) {
            return d1.equals(d2);
        }

        @Override
        public int hashCode(T d) {
            return d.hashCode();
        }

        @Override
        public String toString(T d) {
            return d.toString();
        }
    }

    private ListMethods<T> methods;
    private Node<T>[] data;
    private int size;
    private int first;
    private int last;
    private int max;

    public CircularListWithArrays(int max) {
        this.max = max;
        data = new Node[max];
        size = 0;
        first = -1;
        last = -1;
        methods = new DefaulMethods();
    }

    public CircularListWithArrays(int max, ListMethods<T> methods) {
        this.max = max;
        data = new Node[max];
        size = 0;
        first = -1;
        last = -1;
        this.methods = methods;
    }

    public CircularListWithArrays(ListMethods<T> methods){
        this.max = 15;
        data = new Node[max];
        size = 0;
        first = -1;
        last = -1;
        this.methods = methods;
    }

    public CircularListWithArrays(){
        this.max = 15;
        data = new Node[max];
        size = 0;
        first = -1;
        last = -1;
        methods = new DefaulMethods();
    }

    public boolean isEmpty(){
        return this.first == -1;
    }

    public boolean isFull(){
        return this.size == max;
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
        if (p < 0 || p >= max || data[p] == null) {
            return -1;
        }
        return data[p].getNext();
    }

    public T get(int p){
        if (p < 0 || p >= max || data[p] == null) {
            return null;
        }
        return data[p].getData();
    }

    public int searchFreeSlot(){
        for (int i = 0; i < max; i++) {
            if (data[i] == null) {
                return i;
            }
        }
        return -1;
    }

    public void addFirst(T data){
        if (isFull()) {
            return;
        }
        int p = searchFreeSlot();
        this.data[p] = new Node<T>(data);
        if (isEmpty()) {
            this.data[p].setNext(p);
            this.first = p;
            this.last = p;
        }else{
            this.data[p].setNext(this.first);
            this.data[this.last].setNext(p);
            this.first = p;
        }
        this.size++;
    }

    public void addLast(T data){
        if (isFull()) {
            return;
        }
        int p = searchFreeSlot();
        this.data[p] = new Node<T>(data);
        if (isEmpty()) {
            this.data[p].setNext(p);
            this.first = p;
            this.last = p;
        }else{
            this.data[p].setNext(this.first);
            this.data[this.last].setNext(p);
            this.last = p;
        }
        this.size++;
    }

    public void add(T data, int position){
        if (isFull()) {
            return;
        }
        if (position < 0 || position > size) {
            return;
        }
        if (position == 0) {
            addFirst(data);
            return;
        }
        if (position == size-1) {
            addLast(data);
            return;
        }
        int p = searchFreeSlot();
        this.data[p] = new Node<T>(data);
        int prev = this.first;
        int current = this.first;
        int count = 0;
        while (count < position) {
            prev = current;
            current = this.data[current].getNext();
            count++;
        }
        this.data[p].setNext(current);
        this.data[prev].setNext(p);
        this.size++;
    }

    public void removeFirst(){
        if (isEmpty()) {
            return;
        }
        if (size == 1) {
            this.data[this.first] = null;
            this.first = -1;
            this.last = -1;
        }else{
            int p = this.first;
            this.first = this.data[this.first].getNext();
            this.data[p] = null;
            this.data[this.last].setNext(this.first);
        }
        this.size--;
    }

    public void removeLast(){
        if (isEmpty()) {
            return;
        }
        if (size == 1) {
            this.data[this.first] = null;
            this.first = -1;
            this.last = -1;
        }else{
            int p = this.first;
            int prev = this.first;
            while (this.data[p].getNext() != this.first) {
                prev = p;
                p = this.data[p].getNext();
            }
            this.data[p] = null;
            this.data[prev].setNext(this.first);
            this.last = prev;
        }
        this.size--;
    }

    public void remove(int position){
        if (isEmpty()) {
            return;
        }
        if (position < 0 || position >= size) {
            return;
        }
        if (position == 0) {
            removeFirst();
            return;
        }
        if (position == size-1) {
            removeLast();
            return;
        }
        int p = this.first;
        int prev = this.first;
        int count = 0;
        while (count < position) {
            prev = p;
            p = this.data[p].getNext();
            count++;
        }
        this.data[prev].setNext(this.data[p].getNext());
        this.data[p] = null;
        this.size--;
    }

    public void print(){
        if (isEmpty()) {
            System.out.println("Empty list");
            return;
        }
        int p = this.first;
        while (p != this.last) {
            System.out.print(this.data[p].getData().toString() + " ");
            p = this.data[p].getNext();
        }
        System.out.println(this.data[p].getData().toString());
    }

    public Node<T> getNode(int p){
        if (p < 0 || p >= max || data[p] == null) {
            return null;
        }
        return data[p];
    }

    public Node<T> deleteAndReturnFirNode(){
        if (isEmpty()) {
            return null;
        }
        if (size == 1) {
            Node<T> node = this.data[this.first];
            this.data[this.first] = null;
            this.first = -1;
            this.last = -1;
            this.size--;
            return node;
        }
        Node<T> node = this.data[this.first];
        int p = this.first;
        this.first = this.data[this.first].getNext();
        this.data[p] = null;
        this.data[this.last].setNext(this.first);
        this.size--;
        return node;
    }

    public Node<T> deleteAndReturnLastNode(){
        if (isEmpty()) {
            return null;
        }
        if (size == 1) {
            Node<T> node = this.data[this.first];
            this.data[this.first] = null;
            this.first = -1;
            this.last = -1;
            this.size--;
            return node;
        }
        Node<T> node = this.data[this.last];
        int p = this.first;
        int prev = this.first;
        while (this.data[p].getNext() != this.first) {
            prev = p;
            p = this.data[p].getNext();
        }
        this.data[p] = null;
        this.data[prev].setNext(this.first);
        this.last = prev;
        this.size--;
        return node;
    }

    public Node<T> deleteAndReturnNode(int position){
        if (isEmpty()) {
            return null;
        }
        if (position < 0 || position >= size) {
            return null;
        }
        if (position == 0) {
            return deleteAndReturnFirNode();
        }
        if (position == size-1) {
            return deleteAndReturnLastNode();
        }
        int p = this.first;
        int prev = this.first;
        int count = 0;
        while (count < position) {
            prev = p;
            p = this.data[p].getNext();
            count++;
        }
        Node<T> node = this.data[p];
        this.data[prev].setNext(this.data[p].getNext());
        this.data[p] = null;
        this.size--;
        return node;
    }

    public void toSimpleList(){
        if (isEmpty()) {
            return;
        }
        Node<T> lastNode = this.data[this.last];
        lastNode.setNext(-1);
    }

    public void toCircularList(){
        if (isEmpty()) {
            return;
        }
        Node<T> lastNode = this.data[this.last];
        lastNode.setNext(this.first);
    }

    public void reverse(){
        Node<T> aux = this.data[this.first];
        if (aux != null){
            removeFirst();
            reverse();
            addLast(aux.getData());
        }
    }

    public CircularListWithArrays<T> returnReverse(){
        CircularListWithArrays<T> list = new CircularListWithArrays<>(this.max);
        Node<T> aux = this.data[this.first];
        while (aux != null){
            list.addLast(aux.getData());
            aux = this.data[aux.getNext()];
        }
        list.reverse();
        return list;
    }

    public CircularListWithArrays<T> copy(){
        CircularListWithArrays<T> list = new CircularListWithArrays<>(this.max);
        Node<T> aux = this.data[this.first];
        while (aux != null){
            list.addLast(aux.getData());
            aux = this.data[aux.getNext()];
        }
        return list;
    }

    //sort method using bubble sort algorithm for circular lists
    public void sort(){
        if (size < 2){
            return;
        } 
        for (int i = 0; i < size; i++) {
            int p = this.first;
            for (int j = 0; j < size-1; j++) {
                if (methods.compareTo(this.data[p].getData(), this.data[this.data[p].getNext()].getData()) > 0) {
                    T aux = this.data[p].getData();
                    this.data[p].setData(this.data[this.data[p].getNext()].getData());
                    this.data[this.data[p].getNext()].setData(aux);
                }
                p = this.data[p].getNext();
            }
        }
    }

    public int search(T data){
        if (isEmpty()) {
            return -1;
        }
        int p = this.first;
        int count = 0;
        while (p != this.last) {
            if (methods.compareTo(this.data[p].getData(), data) == 0) {
                return count;
            }
            p = this.data[p].getNext();
            count++;
        }
        if (methods.compareTo(this.data[p].getData(), data) == 0) {
            return count;
        }
        return -1;
    }

    public boolean contains(T data){
        return search(data) != -1;
    }

    public void removeDuplicates(){
        if (isEmpty()) {
            return;
        }
        int p = this.first;
        while (p != this.last) {
            int q = this.data[p].getNext();
            int prev = p;
            while (q != this.first) {
                if (methods.compareTo(this.data[p].getData(), this.data[q].getData()) == 0) {
                    this.data[prev].setNext(this.data[q].getNext());
                    this.data[q] = null;
                    this.size--;
                }
                prev = q;
                q = this.data[q].getNext();
            }
            p = this.data[p].getNext();
        }
    }

    public void addWithOrder(T data){
        addFirst(data);
        sort();
    }

    



    
}
