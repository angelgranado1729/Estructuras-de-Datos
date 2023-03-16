package implementacionesedd.ListaCircularDoblementeEnlazada.ConApuntadores;

public class DoubleCircularList<T>{

    public interface ListMethods<T>{
        int compareTo(T d1, T d2);
        boolean equals(T d1, T d2);
        int hashCode(T d1);
        String toString(T d1);
    }

    private class DefaultMethods implements ListMethods<T>{
        @Override
        public int compareTo(T d1, T d2) {
            return d1.toString().compareToIgnoreCase(d2.toString());
        }

        @Override
        public boolean equals(T d1, T d2) {
            return d1.equals(d2);
        }

        @Override
        public int hashCode(T d1) {
            return d1.hashCode();
        }

        @Override
        public String toString(T d1) {
            return d1.toString();
        }
    }

    private Node<T> pFirst;
    private Node<T> pLast;
    private int size;
    private ListMethods<T> methods;

    public DoubleCircularList(){
        pFirst = null;
        pLast = null;
        size = 0;
        methods = new DefaultMethods();
    }

    public DoubleCircularList(ListMethods<T> methods){
        pFirst = null;
        pLast = null;
        size = 0;
        this.methods = methods;
    }

    public boolean isEmpty(){
        return pFirst == null;
    }

    public int size(){
        return size;
    }

    public Node<T> getFirst(){
        return pFirst;
    }

    public Node<T> getLast(){
        return pLast;
    }

    public Node<T> next(Node<T> p){
        if (p == null) return null;
        return p.getNext();
    }

    public Node<T> prev(Node<T> p){
        if (p == null) return null;
        return p.getPrev();
    }

    public void addFirst(T data){
        Node<T> newNode = new Node<>(data);
        if(isEmpty()){
            pFirst = newNode;
            pLast = newNode;
            pFirst.setNext(pLast);
            pFirst.setPrev(pLast);
            pLast.setNext(pFirst);
            pLast.setPrev(pFirst);
        }else{
            newNode.setNext(pFirst);
            newNode.setPrev(pLast);
            pFirst.setPrev(newNode);
            pLast.setNext(newNode);
            pFirst = newNode;
        }
        size++;
    }

    public void addLast(T data){
        Node<T> newNode = new Node<>(data);
        if(isEmpty()){
            pFirst = newNode;
            pLast = newNode;
            pFirst.setNext(pLast);
            pFirst.setPrev(pLast);
            pLast.setNext(pFirst);
            pLast.setPrev(pFirst);
        }else{
            newNode.setNext(pFirst);
            newNode.setPrev(pLast);
            pFirst.setPrev(newNode);
            pLast.setNext(newNode);
            pLast = newNode;
        }
        size++;
    }

    public void add(T data, int index){
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException();
        }
        if(index == 0){
            addFirst(data);
        }else if(index == size){
            addLast(data);
        }else{
            Node<T> newNode = new Node<>(data);
            Node<T> p = pFirst;
            for(int i = 0; i < index; i++){
                p = p.getNext();
            }
            newNode.setNext(p);
            newNode.setPrev(p.getPrev());
            p.getPrev().setNext(newNode);
            p.setPrev(newNode);
            size++;
        }
    }

    public void removeFirst(){
        if(isEmpty()){
            throw new IndexOutOfBoundsException();
        }
        if(size == 1){
            pFirst = null;
            pLast = null;
        }else{
            Node<T> p = pFirst;
            pFirst = p.getNext();
            pFirst.setPrev(pLast);
            pLast.setNext(pFirst);
            p = null;
        }
        size--;
    }

    public void removeLast(){
        if(isEmpty()){
            throw new IndexOutOfBoundsException();
        }
        if(size == 1){
            pFirst = null;
            pLast = null;
        }else{
            Node<T> p = pLast;
            pLast = p.getPrev();
            pLast.setNext(pFirst);
            pFirst.setPrev(pLast);
            p = null;
        }
        size--;
    }

    public void remove(int index){
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
        if(index == 0){
            removeFirst();
        }else if(index == size - 1){
            removeLast();
        }else{
            Node<T> currentNode = pFirst;
            for(int i = 0; i < index; i++){
                currentNode = currentNode.getNext();
            }
            Node<T> prevNode = currentNode.getPrev();
            Node<T> nextNode = currentNode.getNext();
            prevNode.setNext(nextNode);
            nextNode.setPrev(prevNode);
            currentNode = null;
            size--;
        }
    }

    public void remove(Node<T> p){
        if(p == null){
            throw new NullPointerException();
        }
        if(p == pFirst){
            removeFirst();
        }else if(p == pLast){
            removeLast();
        }else{
            Node<T> prevNode = p.getPrev();
            Node<T> nextNode = p.getNext();
            prevNode.setNext(nextNode);
            nextNode.setPrev(prevNode);
            p = null;
            size--;
        }
    }

    public void remove(T data){
        Node<T> p = pFirst;
        for(int i = 0; i < size; i++){
            if(methods.equals(p.getData(), data)){
                remove(p);
                return;
            }
            p = p.getNext();
        }
    }

    public void removeAll(T data){
        Node<T> p = pFirst;
        for(int i = 0; i < size; i++){
            if(methods.equals(p.getData(), data)){
                remove(p);
                i--;
            }
            p = p.getNext();
        }
    }

    public boolean contains(T data){
        Node<T> p = pFirst;
        for(int i = 0; i < size; i++){
            if(methods.equals(p.getData(), data)){
                return true;
            }
            p = p.getNext();
        }
        return false;
    }

    public int indexOf(T data){
        Node<T> p = pFirst;
        for(int i = 0; i < size; i++){
            if(methods.equals(p.getData(), data)){
                return i;
            }
            p = p.getNext();
        }
        return -1;
    }

    public void reverse(){
        Node<T> temp = pFirst;
        if (temp != null){
            removeFirst();
            reverse();
            addLast(temp.getData());
        }
    }

    public void clear(){
        pFirst = null;
        pLast = null;
        size = 0;
    }

    public DoubleCircularList<T> copy(){
        DoubleCircularList<T> newList = new DoubleCircularList<>();
        Node<T> p = pFirst;
        for(int i = 0; i < size; i++){
            newList.addLast(p.getData());
            p = p.getNext();
        }
        return newList;
    }

    public DoubleCircularList<T> returnReverse(){
        DoubleCircularList<T> newList = new DoubleCircularList<>();
        Node<T> p = pLast;
        for(int i = 0; i < size; i++){
            newList.addLast(p.getData());
            p = p.getPrev();
        }
        return newList;
    }

    public void print(){
        Node<T> p = pFirst;
        for(int i = 0; i < size; i++){
            System.out.print(p.getData().toString() + " \n");
            p = p.getNext();
        }
        System.out.println();
    }

    public void sort() {
        if (size > 1) {
            Node<T> current = pFirst;
            Node<T> next;
            boolean flag = true;
    
            while (flag) {
                flag = false;
                next = current.getNext();
    
                while (next != pFirst) {
                    if (methods.compareTo(current.getData(), next.getData()) > 0) {
                        T temp = current.getData();
                        current.setData(next.getData());
                        next.setData(temp);
                        flag = true;
                    }
                    current = next;
                    next = current.getNext();
                }
                current = pFirst;
            }
        }
    }

    
    public void addWithSort(T data){
        if(isEmpty()){
            addFirst(data);
        }else{
            addFirst(data);
            sort();
        }
    }

    // public void addWithSort(T data, Comparator<T> comparator){
    //     if(isEmpty()){
    //         addFirst(data);
    //     }else{
    //         addFirst(data);
    //         sort(comparator);
    //     }
    // }

    // public void sort(Comparator<T> comparator) {
    //     if (size > 1) {
    //         Node<T> current = pFirst;
    //         Node<T> next;
    //         boolean flag = true;
    
    //         while (flag) {
    //             flag = false;
    //             next = current.getNext();
    
    //             while (next != pFirst) {
    //                 if (comparator.compare(current.getData(), next.getData()) > 0) {
    //                     T temp = current.getData();
    //                     current.setData(next.getData());
    //                     next.setData(temp);
    //                     flag = true;
    //                 }
    //                 current = next;
    //                 next = current.getNext();
    //             }
    //             current = pFirst;
    //         }
    //     }
    // }

    public void toLinkedList(){
        if (pFirst == null) {
            return;
        }
        pLast.setNext(null);
        pFirst.setPrev(null);
    }

    public void toDoubleCircularList(){
        if (pFirst == null) {
            return;
        }
        pLast.setNext(pFirst);
        pFirst.setPrev(pLast);
    }

    public void removeDuplicates(){
        if (pFirst == null) {
            return;
        }
        Node<T> current = pFirst;
        Node<T> next = pFirst;
        while (next(current) != pFirst) {
            next = next(current);
            if (methods.equals(current.getData(), next.getData())) {
                remove(next);
            } else {
                current = next;
            }
        }
    }

    // public void withoutDuplicate(Comparator<T> comparator){
    //     if (pFirst == null) {
    //         return;
    //     }
    //     Node<T> current = pFirst;
    //     Node<T> next = pFirst;
    //     while (next(current) != pFirst) {
    //         next = next(current);
    //         if (comparator.compare(current.getData(), next.getData()) == 0) {
    //             remove(next);
    //         } else {
    //             current = next;
    //         }
    //     }
    // }

    public boolean search(T data){
        Node<T> p = pFirst;
        for(int i = 0; i < size; i++){
            if(methods.equals(p.getData(), data)){
                return true;
            }
            p = p.getNext();
        }
        return false;
    }

    public void addBefore(Node<T> p, T data){
        if(p == pFirst){
            addFirst(data);
        }else{
            Node<T> newNode = new Node<>(data);
            newNode.setNext(p);
            newNode.setPrev(p.getPrev());
            p.getPrev().setNext(newNode);
            p.setPrev(newNode);
            size++;
        }
    }

    public void insertOrdered(T data){
        if(isEmpty()){
            addFirst(data);
        }else{
            Node<T> p = pFirst;
            for(int i = 0; i < size; i++){
                if(methods.compareTo(p.getData(), data) > 0){
                    addBefore(p, data);
                    return;
                }
                p = p.getNext();
            }
            addLast(data);
        }
    }

    public String toStringOrdered(){
        String s = "";
        Node<T> p = pFirst;
        for(int i = 0; i < size; i++){
            s += p.getData().toString() + " \n";
            p = p.getNext();
        }
        return s;
    }
    
    
}
