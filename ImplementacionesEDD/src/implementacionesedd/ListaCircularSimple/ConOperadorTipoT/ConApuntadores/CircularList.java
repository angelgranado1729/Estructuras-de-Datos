package implementacionesedd.ListaCircularSimple.ConOperadorTipoT.ConApuntadores;

public class CircularList<T>{

    public interface ListMethods<T>{
        int compareTo(T tInfo1, T tInfo2);
        void print(T tInfo);
        Node<T> search(T tInfo);
        int hashCode(T tInfo);
        boolean equals(T tInfo1, T tInfo2);
    }

    private class DefaulMethods implements ListMethods<T>{
        @Override
        public int compareTo(Object tInfo1, Object tInfo2) {
            return 0;
        }

        @Override
        public void print(Object tInfo) {
            System.out.println(tInfo);
        }

        @Override
        public Node search(Object tInfo) {
            return null;
        }

        @Override
        public int hashCode(Object tInfo) {
            return 0;
        }

        @Override
        public boolean equals(Object tInfo1, Object tInfo2) {
            return false;
        }
    }
    
    private Node<T> accessPointer;
    private int size;
    private ListMethods<T> methods;

    public CircularList() {
        this.accessPointer = null;
        this.size = 0;
        this.methods = new DefaulMethods();
    }

    public CircularList(ListMethods<T> listMethods) {
        this();
        this.methods = listMethods;
    }

    public boolean isEmpty() {
        return accessPointer == null;
    }

    public int size(){
        return this.size;
    }

    public Node<T> getAccessPointer() {
        return this.accessPointer;
    }

    public void setAccessPointer(Node<T> accessPointer) {
        this.accessPointer = accessPointer;
    }

    public Node<T> next(Node<T> node){
        if (node != null) return node.getNext();
        return null;
    }

    public void addNewAccessPointer(Node<T> node){
        if (!isEmpty()) {
            node.setNext(accessPointer.getNext());
            accessPointer.setNext(node);
        } 
        accessPointer = node;
        size++;
    }

    public void addNewAccessPointer(T data){
        Node<T> node = new Node<>(data);
        addNewAccessPointer(node);
    }

    /**
     * Dado un índice, se agrega un nuevo nodo en la posición indicada. 
     * El accessPointer tiene indice 0 y el nodo que apunta al accessPointer tiene indice size - 1.
     * 
     * @param node
     * @param index
     */
    public void addByIndex(Node<T> node, int index){
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        } else if (index == 0) {
            addNewAccessPointer(node);
        } else {
            Node<T> aux = accessPointer;
            for (int i = 0; i < index; i++) {
                aux = aux.getNext();
            }
            node.setNext(next(aux));
            aux.setNext(node);
            size++;
        }
    }

    public void addByIndex(T data, int index){
        Node<T> node = new Node<>(data);
        addByIndex(node, index);
    }

    public void addFirst(Node<T> node){
        addByIndex(node, 0);
    }

    public void addFirst(T data){
        Node<T> node = new Node<>(data);
        addFirst(node);
    }

    public void addLast(Node<T> node){
        addByIndex(node, size);
    }

    public void addLast(T data){
        Node<T> node = new Node<>(data);
        addLast(node);
    }

    public void deleteAccessPointer(){
        if (!isEmpty()){
            Node<T> aux = getAccessPointer();
            while (next(aux) != getAccessPointer()) {
                aux = next(aux);
            }
            aux.setNext(next(getAccessPointer()));
            accessPointer = null;
            setAccessPointer(aux);
            size--;
        }
    }

    public void deleteByIndex(int index){
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        } else if (index == 0) {
            deleteAccessPointer();
        } else {
            Node<T> aux = accessPointer;
            for (int i = 0; i < index; i++) {
                aux = aux.getNext();
            }
            Node<T> temp = next(aux);
            aux.setNext(next(temp));
            temp = null;
            size--;
        }
    }

    public void deleteLast(){
        deleteByIndex(size - 1);
    }

    public void deleteAll(){
        while (!isEmpty()) {
            deleteAccessPointer();
        }
    }

    public void delete(Node<T> node){
        if (!isEmpty()){
            if (size() == 1){
                if (getAccessPointer() == node){
                    deleteAccessPointer();
                }
            } else{
                Node<T> aux = getAccessPointer();
                while (next(aux) != getAccessPointer()) {
                    if (next(aux) == node){
                        Node<T> temp = next(aux);
                        aux.setNext(next(temp));
                        node = temp = null;
                        size--;
                        break;
                    }
                    aux = next(aux);
                }
            }
        }
    }

    public Node<T> delateAndReturn(Node<T> node){
        if (!isEmpty()){
            Node<T> aux = getAccessPointer();
            if (node == aux){
                deleteAccessPointer();
                return aux;
            } else{
                while (next(aux) != getAccessPointer()) {
                    if (next(aux) == node){
                        delete(node);
                        return next(node);
                    }
                    aux = next(aux);
                }
                return null;
            }
        }
        return null;
    }

    public void reverse(){
        Node<T> aux = getAccessPointer();
        if (aux != null){
            deleteLast();
            reverse();
            addFirst(aux.getData());
        }
    }

    public CircularList<T> returnReverse(){
        CircularList<T> reverse = new CircularList<>();
        Node<T> aux = getAccessPointer();
        if (aux != null){
            deleteLast();
            reverse = returnReverse();
            addLast(aux.getData());
            reverse.addFirst(aux.getData());
        }
        return reverse;
    }

    public CircularList<T> copy(){
        CircularList<T> copy = new CircularList<>();
        Node<T> aux = getAccessPointer();
        if (aux != null){
            deleteLast();
            copy = copy();
            addLast(aux.getData());
            copy.addLast(aux.getData());
        }
        return copy;
    }

    public void eliminateRepetitions(){
        if (!isEmpty()){
            Node<T> pAux = getAccessPointer();
            Node<T> pAux2 = new Node<>();
            while (next(pAux) != getAccessPointer()) {
                pAux2 = next(pAux);
                while (next(pAux2) != getAccessPointer()) {
                    if (pAux.getData().equals(pAux2.getData())) {
                        delete(pAux2);
                    }
                    pAux2 = next(pAux2);
                }
                pAux = next(pAux);
            }
        }
    }

    public void addFirstWithoutRepetitions(Node<T> node){
        addFirst(node);
        eliminateRepetitions();
    }

    public void addEndWithoutRepetitions(Node<T> node){
        addLast(node);
        eliminateRepetitions();
    }

    public void addByIndexWithoutRepetitions(Node<T> node, int index){
        addByIndex(node, index);
        eliminateRepetitions();
    }

    public CircularList<T> returnWithoutRepetitions(){
        CircularList<T> withoutRepetitions = copy();
        withoutRepetitions.eliminateRepetitions();
        return withoutRepetitions;
    }

    public boolean search(Node<T> node){
        if (!isEmpty()){
            Node<T> aux = getAccessPointer();
            while (next(aux) != getAccessPointer()) {
                if (next(aux).equals(node)){
                    return true;
                }
                aux = next(aux);
            }
        }
        return false;
    }

    public CircularList<T> filter(T data){
        CircularList<T> filter = new CircularList<>();
        Node<T> aux = getAccessPointer();
        if (!isEmpty()){
            while (next(aux) != getAccessPointer()) {
                if (next(aux).getData().equals(data)){
                    filter.addLast(aux.getData());
                }
                aux = next(aux);
            }
            return filter;
        }
        return null;
    } 

    // sort using bubble sort
    public void sort(){
        if (!isEmpty()){
            Node<T> aux = getAccessPointer();
            Node<T> aux2 = new Node<>();
            while (next(aux) != getAccessPointer()) {
                aux2 = next(aux);
                while (next(aux2) != getAccessPointer()) {
                    if (methods.compareTo(aux.getData(), aux2.getData()) > 0) {
                        T temp = aux.getData();
                        aux.setData(aux2.getData());
                        aux2.setData(temp);
                    }
                    aux2 = next(aux2);
                }
                aux = next(aux);
            }
        }
    }

    //Crar una clase comparator y ordene la lista
    // public void sort(Comparator<T> comparator){
    //     if (!isEmpty()){
    //         Node<T> aux = getAccessPointer();
    //         Node<T> aux2 = new Node<>();
    //         while (next(aux) != getAccessPointer()) {
    //             aux2 = next(aux);
    //             while (next(aux2) != getAccessPointer()) {
    //                 if (comparator.compare(aux.getData(), aux2.getData()) > 0) {
    //                     T temp = aux.getData();
    //                     aux.setData(aux2.getData());
    //                     aux2.setData(temp);
    //                 }
    //                 aux2 = next(aux2);
    //             }
    //             aux = next(aux);
    //         }
    //     }
    // }

    public void addWithOrder(T data){
        if (isEmpty()){
            addFirst(data);
        } else{
            addFirst(data);
            sort();
        }
    }

}