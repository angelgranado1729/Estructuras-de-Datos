package implementacionesedd.ListaDoblementeEnlazada.ConApuntadores;

public class DoubleLinkedList<T> {

    public interface DoubleLinkedListMethods<T>{
        public int compareTo(T data1, T data2);
        public boolean equals(T data1, T data2);
    }

    private class DefaulMethods implements DoubleLinkedListMethods<T>{
        public int compareTo(T data1, T data2){
            return data1.toString().compareToIgnoreCase(data2.toString());
        }

        public boolean equals(T data1, T data2){
            return data1.equals(data2);
        }
    }

    private Node<T> pFirst;
    private int iSize;
    private DoubleLinkedListMethods<T> methods;


    public DoubleLinkedList(){
        this.pFirst = null;
        this.iSize = 0;
        this.methods = new DefaulMethods();
    }

    public DoubleLinkedList(DoubleLinkedListMethods<T> methods){
        this.pFirst = null;
        this.iSize = 0;
        this.methods = methods;
    }

    public Node<T> first(){
        return this.pFirst;
    }

    public Node<T> last(){
        if (isEmpty()) return null;
        Node<T> pAux = pFirst;
        while (next(pAux) != null){
            pAux = next(pAux);
        }
        return pAux;
    }

    public boolean isEmpty(){
        return this.pFirst == null;
    }

    public int size(){
        return this.iSize;
    }

    public Node<T> next(Node<T> node){
        if (node == null) return null;
        return node.getNext();
    }

    public Node<T> prev(Node<T> node){
        if (node == null) return null;
        return node.getPrev();
    }

    public T getData(Node<T> node){
        return node.getData();
    }

    public void destroy(){
        this.pFirst = null;
        this.iSize = 0;
        System.gc();
    }

    public void addFirst(Node<T> node){
        node.setNext(pFirst);
        node.setPrev(null);
        if (!isEmpty()){
            pFirst.setPrev(node);
        }
        pFirst = node;
        iSize++;
    }

    public void addFirst(T data){
        Node<T> node = new Node<>(data);
        addFirst(node);
    }

    public void add(Node<T> node){
        if(isEmpty()){
            addFirst(node);
        }else{
            Node<T> pAux = pFirst;
            while (next(pAux) != null){
                pAux = next(pAux);
            }
            node.setPrev(pAux);
            node.setNext(null);
            pAux.setNext(node);
            iSize++;
        }
    }

    public void add(T data){
        Node<T> node = new Node<>(data);
        add(node);
    }

    public void addByIndex(Node<T> node, int index){
        if (index >= 0 || index < iSize){
            if (index == 0){
                addFirst(node);
            }else{
                Node<T> pAux = pFirst;
                Node<T> pAux2 = null;
                int count = 0;
                while (count < index - 1){
                    pAux = next(pAux);
                    count++;
                }
                pAux2 = next(pAux);
                node.setNext(pAux2);
                node.setPrev(pAux);
                pAux.setNext(node);
                pAux2.setPrev(node);
                iSize++;
            }
        }
    }

    public int indexOf(Node<T> node){
        if (!isEmpty()){
            Node<T> pAux = pFirst;
            int count = 0;
            while (pAux != null){
                if (pAux.equals(node)){
                    return count;
                }
                pAux = next(pAux);
                count++;
            }
        }
        return -1;
    }

    public void addByIndex(T data, int index){
        Node<T> newNodo = new Node<>(data);
        addByIndex(newNodo, index);
    }

    public void deleteFirst(){
        if (!isEmpty()){
            Node<T> pAux = pFirst;
            if (next(pAux) == null){
                pFirst = null;
            } else {
                pFirst = next(pAux);
                pFirst.setPrev(null);
            }
            iSize--;
        }
    }

    public void deleteLast(){
        if (!isEmpty()){
            Node<T> pAux = pFirst;
            Node<T> pAux2 = null;
            while (next(pAux) != null){
                pAux = next(pAux);
            }
            pAux2 = prev(pAux);
            pAux2.setNext(null);
            pAux = null;
            iSize--;
        }

    }

    public void pop(int index){
        if (!isEmpty()){
            if (index == 0){
                deleteFirst();
            } else if (index == iSize - 1){
                deleteLast();
            } else if (index > 0 && index < iSize - 1){
                Node<T> pAux = pFirst;
                Node<T> pAux2 = null;
                Node<T> pAux3 = null;
                int count = 0;
                while (count < index - 1){
                    pAux = next(pAux);
                    count++;
                }
                pAux2 = next(pAux);
                pAux3 = next(pAux2);
                pAux.setNext(pAux3);
                pAux3.setPrev(pAux);
                pAux2 = null;
                iSize--;
            }
        }
    }

    public void delete(Node<T> node){
        if (!isEmpty()){
            if (node.equals(pFirst)){
                deleteFirst();
            } else if (next(node) == null){
                deleteLast();
            } else {
                Node<T> pAux = pFirst;
                Node<T> pAux2 = null;
                Node<T> pAux3 = null;
                while (!(next(pAux).equals(node))){
                    pAux = next(pAux);
                }
                pAux2 = next(pAux);
                pAux3 = next(pAux2);
                pAux.setNext(pAux3);
                pAux3.setPrev(pAux);
                pAux2 = null;
                iSize--;
            }
        }
    }

    public void delete(T data){
        if (!isEmpty()){
            Node<T> pAux = pFirst;
            while (pAux != null){
                if (pAux.getData().equals(data)){
                    delete(pAux);
                    break;
                }
                pAux = next(pAux);
            }
        }
    }

    public Node<T> delateAndReturnFirst(){
        if (!isEmpty()){
            Node<T> pAux = pFirst;
            deleteFirst();
            return pAux;
        } else {
            return null;
        }
    }

    public Node<T> delateAndReturnLast(){
        if (!isEmpty()){
            Node<T> pAux = pFirst;
            while (next(pAux) != null){
                pAux = next(pAux);
            }
            delete(pAux);
            return pAux;
        } 
        return null;
    }

    public Node<T> delateAndReturn(int index){
        if (!isEmpty()){
            Node<T> pAux = pFirst;
            int count = 0;
            while (count < index){
                pAux = next(pAux);
                count++;
            }
            delete(pAux);
            return pAux;
        } 
        return null;        
    }

    public Node<T> delateAndReturn(Node<T> node){
        if (!isEmpty()){
            delete(node);
            return node;
        } 
        return null;        
    }

    public void reverse(){
        Node<T> pAux = new Node<>();
        if (!isEmpty()){
            pAux = first();
            deleteFirst();
            reverse();
            add(pAux);            
        }
    }

    public DoubleLinkedList<T> returnReverse(){
        DoubleLinkedList<T> list = new DoubleLinkedList<>();
        Node<T> current = first();
        if (current != null){
            deleteFirst();
            list = returnReverse();
            list.add(current.getData());
            addFirst(current.getData());
        }
        return list;
    }

    public DoubleLinkedList<T> copy(){
        DoubleLinkedList<T> list = new DoubleLinkedList<>();
        Node<T> current = first();
        if (current != null){
            deleteFirst();
            list = copy();
            list.addFirst(current.getData());
            addFirst(current.getData());
        }
        return list;
    }

    public void toDoubleCircularList(){
        if (!isEmpty()){
            Node<T> pAux = pFirst;
            while (next(pAux) != null){
                pAux = next(pAux);
            }
            pAux.setNext(pFirst);
            pFirst.setPrev(pAux);
        }
    }

    public void toDoubleList(){
        if (!isEmpty()){
            Node<T> pAux = pFirst;
            while (!(next(pAux).equals(pFirst))){
                pAux = next(pAux);
            }
            pAux.setNext(null);
            pFirst.setPrev(null);
        }
    }

    public Node<T> getMiddle(){
        if (!isEmpty()){
            Node<T> pAux = pFirst;
            Node<T> pMiddle = pFirst;
            while (next(pAux) != null && next(next(pAux)) != null){
                pAux = next(next(pAux));
                pMiddle = next(pMiddle);
            }
            return pMiddle;
        }
        return null;
    }

    public void removeDuplicates(){
        if (!isEmpty()){
            Node<T> pAux = pFirst;
            Node<T> pAux2 = new Node<>();
            while (next(pAux) != null){
                pAux2 = next(pAux);
                while (pAux2 != null){
                    if (pAux.getData().equals(pAux2.getData())){
                        delete(pAux2);
                    }
                    pAux2 = next(pAux2);
                }
                pAux = next(pAux);
            }
        }
    }

    public DoubleLinkedList<T> returnWithoutDuplicates(){
        DoubleLinkedList<T> list = copy();
        list.removeDuplicates();
        return list;
    }

    public void addEndWithoutRepetitions(T data){
        add(data);
        removeDuplicates();
    }

    public void addFirstWithoutRepetitions(T data){
        addFirst(data);
        removeDuplicates();
    }

    public void addWithoutRepetitions(T data, int index){
        addByIndex(data, index);
        removeDuplicates();
    }

    public DoubleLinkedList<T> filter(T data){
        DoubleLinkedList<T> list = new DoubleLinkedList<>();
        Node<T> pAux = pFirst;
        if (pAux != null){
            deleteFirst();
            list = filter(data);
            if (pAux.getData().equals(data)){
                list.add(pAux.getData());
            }
            addFirst(pAux.getData());
        }
        return list;
    }

    public void replace(Node<T> node, Node<T> newNode){
        if (!isEmpty()){
            if (node.equals(pFirst)){
                if (next(node) != null){
                    Node<T> pAux = next(node);
                    newNode.setNext(pAux);
                    newNode.setPrev(null);
                    pAux.setPrev(newNode);
                    pFirst = newNode;
                } else {
                    pFirst = newNode;
                }
            } else if (next(node) == null){
                Node<T> pAux = prev(node);
                newNode.setNext(null);
                newNode.setPrev(pAux);
                pAux.setNext(newNode);
            } else {
                Node<T> pAux = prev(node);
                Node<T> pAux2 = next(node);
                newNode.setNext(pAux2);
                newNode.setPrev(pAux);
                pAux.setNext(newNode);
                pAux2.setPrev(newNode);
            }
        }
    }

    public boolean detectLoop(){
        boolean loop = false;
        if (!isEmpty()){
            Node<T> pSlow = first();
            Node<T> pFast = next(pSlow);
            while (pFast != null && pSlow != null && next(pFast) != null){
                pSlow = next(pSlow);
                pFast = next(next(pFast));
                if (pSlow == pFast){
                    loop = true;
                    break;
                }
            }
            return loop;
        }
        return loop;
    }

    public Node<T> findLoopStart(){
        boolean loop = detectLoop();
        if (loop){
            Node<T> pSlow = first();
            Node<T> pFast = next(pSlow);
            while (pFast != null && pSlow != null && next(pFast) != null){
                pSlow = next(pSlow);
                pFast = next(next(pFast));
                if (pSlow == pFast){
                    break;
                }
            }
            pSlow = first();
            while (pSlow != pFast){
                pSlow = next(pSlow);
                pFast = next(pFast);
            }
            return pSlow;
        }
        return null;
    }

    public void detectAndRemoveLoop(){
        Node<T> loopStart = findLoopStart();
        if (loopStart != null){
            Node<T> pAux = loopStart;
            while (next(pAux) != loopStart){
                pAux = next(pAux);
            }
            pAux.setNext(null);
        }
    }

    public int nodesInLoop(){
        int nodes = 0;
        Node<T> loopStart = findLoopStart();
        if (loopStart != null){
            Node<T> pAux = loopStart;
            while (next(pAux) != loopStart){
                pAux = next(pAux);
                nodes++;
            }
            nodes++;
        }
        return nodes;
    }

    public void delateAll(){
        pFirst = null;
        iSize = 0;
        System.gc();
    }

    @Override
    public String toString() {
        String s = "";
        Node<T> pAux = pFirst;
        while (pAux != null){
            s += pAux.getData() + " ";
            pAux = next(pAux);
        }
        return s;
    }

    //Merge Sort method's
    public void mergeSort(){
        if (iSize > 1){
            DoubleLinkedList<T> left = new DoubleLinkedList<>();
            DoubleLinkedList<T> right = new DoubleLinkedList<>();
            Node<T> pAux = pFirst;
            int iMiddle = iSize / 2;
            for (int i = 0; i < iMiddle; i++){
                left.add(pAux.getData());
                pAux = next(pAux);
            }
            for (int i = iMiddle; i < iSize; i++){
                right.add(pAux.getData());
                pAux = next(pAux);
            }
            left.mergeSort();
            right.mergeSort();
            merge(left, right);
        }
    }

    private void merge(DoubleLinkedList<T> left, DoubleLinkedList<T> right){
        delateAll();
        Node<T> pAuxLeft = left.first();
        Node<T> pAuxRight = right.first();
        while (pAuxLeft != null && pAuxRight != null){
            if (methods.compareTo(pAuxLeft.getData(), pAuxRight.getData()) < 0){
                add(pAuxLeft.getData());
                pAuxLeft = next(pAuxLeft);
            } else {
                add(pAuxRight.getData());
                pAuxRight = next(pAuxRight);
            }
        }
        while (pAuxLeft != null){
            add(pAuxLeft.getData());
            pAuxLeft = next(pAuxLeft);
        }
        while (pAuxRight != null){
            add(pAuxRight.getData());
            pAuxRight = next(pAuxRight);
        }
    }


}









