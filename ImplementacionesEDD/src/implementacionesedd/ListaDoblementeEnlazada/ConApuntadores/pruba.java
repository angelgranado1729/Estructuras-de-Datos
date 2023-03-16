package implementacionesedd.ListaDoblementeEnlazada.ConApuntadores;

public class pruba {
    public static void main(String[] args){
        DoubleLinkedList<String> lista = new DoubleLinkedList<>();
        lista.addFirst("Hola");
        lista.addFirst("Hola");
        lista.addFirst("Hola");
        lista.addFirst("Mundo");
        lista.addFirst("Cruel");
        lista.addFirst("!");
        lista.addFirst("!");
        lista.addFirst("!");
        lista.addFirst("!");

        Node<String> p = lista.first();
        while(p != null){
            System.out.println(p.getData());
            p = lista.next(p);
        }

        
        System.out.println("\n---------------------");
        System.out.println("Lista invertida\n");
        DoubleLinkedList<String> lista2 = lista.returnReverse();
        Node<String> p2 = lista2.first();
        while(p2 != null){
            System.out.println(p2.getData());
            p2 = lista2.next(p2);
        }
        System.out.println(lista2.size());

        System.out.println("\n---------------------");
        System.out.println("Lista original\n");
        Node<String> p3 = lista.first();
        while(p3 != null){
            System.out.println(p3.getData());
            p3 = lista.next(p3);
        }
        System.out.println(lista.size());

        System.out.println("\n---------------------");
        System.out.println("Lista copia\n");
        DoubleLinkedList<String> lista3 = lista.copy();
        Node<String> p4 = lista3.first();
        while(p4 != null){
            System.out.println(p4.getData());
            p4 = lista3.next(p4);
        }
        System.out.println(lista3.size());

        System.out.println("\n---------------------");
        System.out.println("Lista original\n");
        Node<String> p5 = lista.first();
        while(p5 != null){
            System.out.println(p5.getData());
            p5 = lista.next(p5);
        }
        System.out.println(lista.size());

        System.out.println("\n---------------------");
        System.out.println("Lista sin duplicados\n");
        lista.removeDuplicates();
        Node<String> p6 = lista.first();
        while(p6 != null){
            System.out.println(p6.getData());
            p6 = lista.next(p6);
        }

        System.out.println("\n---------------------");
        lista.addEndWithoutRepetitions("hola");
        Node<String> p7 = lista.first();
        while(p7 != null){
            System.out.println(p7.getData());
            p7 = lista.next(p7);
        }
        System.out.println("\n---------------------");



    }
}
