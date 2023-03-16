package implementacionesedd.ListaCircularDoblementeEnlazada.ConApuntadores;

import implementacionesedd.ListaCircularDoblementeEnlazada.ConApuntadores.DoubleCircularList.ListMethods;

public class Methods implements ListMethods<Integer>{

    public int compareTo(Integer a, Integer b) {
        if (a > b) {
            return 1;
        } else if (a < b) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Integer a, Integer b) {
        return a.equals(b);
    }

    @Override
    public int hashCode(Integer a) {
        return a.hashCode();
    }

    @Override
    public String toString(Integer a) {
        return String.valueOf(a);
    }

    
}
