package implementacionesedd.Stack;

public class test {
    //testing the stack

    public static void main(String[] args){
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println("Size: " + stack.getSize());
        System.out.println("Top: " + stack.getTop().getTInfo());
        System.out.println("Is empty: " + stack.isEmpty());
        System.out.println("Pop: " + stack.popAndReturn().getTInfo());
        System.out.println("Size: " + stack.getSize());
        System.out.println(stack.toString());
        System.out.println("top: " + stack.getTop().getTInfo());

    }
    
}
