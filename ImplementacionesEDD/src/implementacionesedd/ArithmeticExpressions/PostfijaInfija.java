package implementacionesedd.ArithmeticExpressions;

import implementacionesedd.Stack.*;
import implementacionesedd.BinaryTreeImp.*;
import implementacionesedd.BinaryTreeImp.Node;

public class PostfijaInfija {

    public String toPostFija(String entrada) {
        Stack<Character> stack = new Stack<>();
        String salida = "";
        for (int i = 0; i < entrada.length(); i++) {
            // Tomamos el caracter
            char ch = entrada.charAt(i);

            // Si es operando, lo mandamos a la salida
            if (Character.isLetterOrDigit(ch)) {
                salida += ch;
                // Si no, se distinguen tres casos:
            } else {
                // caso 1: se lee un parentesis izquierdo
                if (ch == '(') {
                    stack.push(ch);
                    // caso 2: se lee un parentesis derecho
                } else if (ch == ')') {
                    while (!stack.isEmpty()) {
                        if (stack.peek() == '(') {
                            stack.pop();
                            break;
                        } else {
                            salida += stack.popAndReturn().getTInfo();
                        }
                    }
                    // caso 3: se lee un operador
                } else {
                    // si la prioridad es menor o igual que la cima, se empieza de desapilar
                    while (!stack.isEmpty() && (prioridad(ch) <= prioridad(stack.peek()))) {
                        salida += stack.popAndReturn().getTInfo();
                    }
                    // si la prioridad del operador es mayor que la cima, entonces se apila
                    stack.push(ch);
                }
            }
        }

        // vaciamos toda la pila
        while (!stack.isEmpty()) {
            if (stack.peek() != '(') {
                salida += stack.popAndReturn().getTInfo();
            }
        }

        return salida;
    }

    // funcion que devuelve la prioridad de un operador
    public int prioridad(char operador) {
        switch (operador) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return 0;
        }
    }

    //Funcion que invierte un String
    public String reverse(String string){
        if (string != ""){
            char c = string.charAt(0);
            string = reverse(string.substring(1));
            string += c;
        }
        return string;
    }


    //Funcion que convierte una expresion infija a prefija
    public String toPrefija(String entrada){
        //Primero invertimos la expresion
        String entradaInvertida = reverse(entrada);
        //Luego cambiamos los parentesis
        String entradaInvertida2 = "";
        for (int i = 0; i < entradaInvertida.length(); i++) {
            if (entradaInvertida.charAt(i) == '(') {
                entradaInvertida2 += ')';
            } else if (entradaInvertida.charAt(i) == ')') {
                entradaInvertida2 += '(';
            } else {
                entradaInvertida2 += entradaInvertida.charAt(i);
            }
        }
        //Luego convertimos a postfija
        String postfija = toPostFija(entradaInvertida2);
        //Luego invertimos la postfija
        String prefija = reverse(postfija);
        return prefija;
    }

    //Funcion que crea el arbol de expresion
    public BinaryTree<Character> createTree(String entrada){
        //Primero convertimos a postfija
        entrada = toPostFija(entrada);
        Stack<Node<Character>> stack = new Stack<>();
        BinaryTree<Character> tree = new BinaryTree<>();
        //recorremos la expresion
        for (int i = 0; i < entrada.length(); i++) {
            char ch = entrada.charAt(i);
            //Si es un operando, se crea un nodo y se apila
            if (Character.isLetterOrDigit(ch)) {
                Node<Character> node = new Node<>(ch);
                stack.push(node);
                //Si es un operador, se desapilan los dos ultimos nodos y se crea un nodo padre, y ese se apila
            } else {
                Node<Character> node = new Node<>(ch);
                //Se asignan los hijos
                node.setRight(stack.popAndReturn().getTInfo());
                node.setLeft(stack.popAndReturn().getTInfo());
                //Se apila el nodo padre
                stack.push(node);
            }
        }
        tree.setRoot(stack.popAndReturn().getTInfo());
        return tree;
    }


 
    public static void main(String[] args) {
        PostfijaInfija myClass = new PostfijaInfija();
        String entrada = "a+b*c+(d*e+f)*g";
        String entrada2 = "(a+b*c)+((d*e+f)*g)";
        String entrada3 = "(A+B^C)*D+E^5";
        System.out.println(myClass.toPostFija(entrada));
        System.out.println(myClass.toPostFija(entrada2));
        System.out.println(myClass.toPrefija(entrada2));
        System.out.println(String.join("", myClass.createTree(entrada2).preOrder().split(",")));
        // System.out.println(String.join("", myClass.createTree(entrada2).inOrder().split(",")));
        // System.out.println(String.join("", myClass.createTree(entrada2).postOrder().split(",")));
        // System.out.println(String.join("", myClass.createTree(entrada3).preOrder().split(",")));
        // System.out.println(String.join("", myClass.createTree(entrada3).postOrder().split(",")));
        // System.out.println(String.join("", myClass.createTree(entrada3).inOrder().split(",")));


    }

}
