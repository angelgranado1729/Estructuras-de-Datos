package implementacionesedd.ListasSimplementeEnlazada.ConOperadorTipoT.ConApuntadores;

public class h{
    public static void main(String[] args){
        // String data = """
        //     Almacenes;
        //     Almacen A:
        //     Pantalla,3
        //     RAM,2
        //     Procesador,1;
        //     Almacen B:
        //     Pantalla,3
        //     Grafica,5;
        //     Almacen C:
        //     Placa,7
        //     Teclado,8;
        //     Almacen D:
        //     Mouse,2;
        //     Almacen E:
        //     Microfono,7
        //     Audifonos,10;
        //     Rutas;
        //     A,B,10
        //     A,C,20
        //     B,C,5
        //     B,D,8
        //     C,D,4
        //     C,E,13
        //     D,E,3
        //     E,A,25""";

        // String[] almacenes = data.substring("Almacenes;".length(), data.indexOf("Rutas;") - 1).strip().split(";");
        // String[] rutas = data.substring(data.indexOf("Rutas;") + "Rutas;".length()).strip().split("\n");

        // // String almacen1 = almacenes[0].split("\n")[0].replaceAll("Almacen ", "").strip().replace(":", "");
        // // System.out.println(almacen1);
        // // for (String almacen : almacenes) {
            
        // //     System.out.println(almacen);
        // // }
        
        // for (String ruta : rutas) {
            
        //     System.out.print(ruta);
        // }
        
        
        // for (String ruta : rutas) {
        //     System.out.println(ruta);
        // }

        // String d = "dasdas       hhhhhh";

        // //lo que esta despues de los espacios
        // System.out.println(d.substring(d.indexOf(" ") + 1).replace("\s", ""));
        
        LinkedList<Integer> lista = new LinkedList<Integer>();
        lista.addEnd(1);
        lista.addEnd(2);
        lista.addEnd(3);
        lista.addEnd(4);
        
        
        System.out.println("Lista original");
        Node<Integer> Node = lista.first();
        while (Node != null) {
            System.out.println(Node.getTInfo());
            Node = lista.next(Node);
        }


        
        System.out.println("--------");
        System.out.println("Lista invertida");
        LinkedList<Integer> lista2 = lista.returnReverse();
        Node<Integer> Node2 = lista2.first();
        while (Node2 != null) {
             System.out.println(Node2.getTInfo());
             Node2 = lista2.next(Node2);
        }

        System.out.println("--------");
        System.out.println("Lista copia");
        LinkedList<Integer> lista3 = lista.copy();
        Node<Integer> Node3 = lista3.first();
        while (Node3 != null) {
             System.out.println(Node3.getTInfo());
             Node3 = lista3.next(Node3);
        }

        System.out.println("--------");
        System.out.println("Lista original");
        Node<Integer> Node4 = lista.first();
        while (Node4 != null) {
            System.out.println(Node4.getTInfo());
            Node4 = lista.next(Node4);
        }

        System.out.println("--------");
        lista.toCircularList();
        System.out.println("Lista circular");
        Node<Integer> Node5 = lista.first();
        while (lista.next(Node5) != lista.first()) {
            System.out.println(Node5.getTInfo());
            Node5 = lista.next(Node5);
        }
        System.out.println(lista.next(Node5).getTInfo());

        System.out.println("--------");
        System.out.println("Lista original");
        lista.toSimpleList();
        lista.reverse();
        Node<Integer> Node6 = lista.first();
        while (Node6 != null) {
            System.out.println(Node6.getTInfo());
            Node6 = lista.next(Node6);
        }
        System.out.println(lista.next(Node6));
        
    }
}