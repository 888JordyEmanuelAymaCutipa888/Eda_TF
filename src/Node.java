import java.util.ArrayList;

// Java program for insertion in AVL Tree
class Node {

    int height;
    ArrayList<UbicacionPalabra> ubicacionesPalabras = new ArrayList<UbicacionPalabra>();
    Node left, right;
    String valor;

    Node(int indice, int oracion, String valor) {
        height = 1;
        this.valor = valor;
        this.ubicacionesPalabras.add(new UbicacionPalabra(indice, oracion));
    }

    void setHeight(int h) {
        height = h;
    }
}
