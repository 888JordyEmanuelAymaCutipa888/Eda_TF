import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PruebaDan {

  /* method to create a simple linked list with 3 nodes */
  public static void main(String[] args) {

    int cantidadPalabras = 8;
    String txtprb = "Doña Anaya Uzeada de Ribera rechoncha Maldonado de Bracamonte y Anaya era baja, rechoncha, abigotada. Ya no existia razon para llamar talle al suyo. Sus colores vivos, sanos, podian mas que el albayalde y el soliman del afeite, con que se blanqueaba por simular melancolias. Gastaba dos parches oscuros, adheridos a las sienes y que fingian medicamentos. Tenia los ojitos ratoniles, maliciosos. Sabia dilatarlos duramente o desmayarlos con recato o levantarlos con disimulo. Caminaba contoneando las imposibles caderas y era dificil, al verla, no asociar su estampa achaparrada con la de ciertos palmipedos domesticos. Sortijas celestes y azules le ahorcaban las falanges";

    AVLTree arbolUbicacion = new AVLTree();
    ArrayList<String[]> textoTabla = new ArrayList<String[]>();

    textoTabla = generarTabla(txtprb);
    arbolUbicacion = generarArbol(textoTabla);

    ArrayList<Integer> num = new ArrayList<Integer>();
    System.out.println(num.toString());
    System.out.println(arbolUbicacion.inOrden());
    System.out.println("buscado rechoncha"+arbolUbicacion.getNode("rechoncha").ubicacionesPalabras.toString());

    String oracion = "Conjunto de enunciados con que el creyente se dirige a Dios, a una divinidad, a un santo, etc, especialmente la que tiene una forma fija y establecida";
    String oracionn = "Acción de orar mental o vocalmente tiene una forma fija y establecida";
    String[] o = generarArrayOracion(oracion);
    System.out.println(o.length);
    String[] o2 = generarArrayOracion(oracionn);
    System.out.println(o2.length);
    System.out.println(o[11]);
    System.out.println(o2[7]);
    System.out.println("lo que retorna"+comparacionOraciones(o, o2, 8, 4, 4));
    System.out.println("asdf");


  }




  public static String[] generarArrayOracion(String oracion){
    oracion = oracion.replace(",", "").replaceAll("\\s[^\\s]{1,3}\\s", " ").replaceAll("\\s[^\\s]{1,3}\\s", " ").replaceAll("^\\s", "");
    String[] arregloPalabras = oracion.split(" ", -1);
    return arregloPalabras;
  }
  public static ArrayList<String[]> generarTabla(String texto){
    ArrayList<String[]> list = new ArrayList<String[]>();
    //esta funcion (split) retorna un arreglo dependiendo del parametro que se le entregue para separar
    String[] parts = texto.split("\\.");              

    for (int index = 0; index < parts.length; index++) {
      String temp = parts[index].replace(",", "").replaceAll("\\s[^\\s]{1,3}\\s", " ").replaceAll("\\s[^\\s]{1,3}\\s", " ").replaceAll("^\\s", "");

      //aqui sse genero un arreglo apartir de solo los espacios, por lo que convierte oraciones en un arreglo de palabras
      System.out.println(Arrays.toString(temp.split(" ", -1)));
      list.add(temp.split(" ", -1));
    }
    return list;
  }



  public static AVLTree generarArbol(ArrayList<String[]> text){
    AVLTree tree = new AVLTree();
    for(int i=0;i<text.size();i++){
      System.out.println("-"+i);
      for(int j=0;j<text.get(i).length;j++){
        tree.root = tree.insert(tree.root, j,i, text.get(i)[j]);
      }
    }
    return tree;
  }



  public static boolean plagioEncontrado(){
    return true;
  }

/*
  public static int verificacionOracion(ArrayList<String> texto, String[] plagio, int plagioMinimo, AVLTree tree){

    ArrayList<Integer> indicesRevision = new ArrayList<Integer>();
    ArrayList<UbicacionPalabra> ubicacionesDeUnaPalabra = tree.getNodes().UbicacionesPalabras;

    int indiceTexto = ubicacionesDeUnaPalabra.get(0).indice;
    int numOracion = ubicacionesDeUnaPalabra.get(0).oracion;
    
    int indicePlagio = plagioMinimo;
    int indicePlagio = comparacionOraciones(texto.get(numOracion), plagio, indiceTexto, indicePlagio, plagioMinimo);
    if(indicePlagio == -1){
      return numOracion;
    }
    if(ubicacionesDeUnaPalabra.length > 1){
    }
    if((plagio.length - indicePlagio) > plagioMinimo){
      while(indice < oracion.length && i != -1 && (oracion.length-indice) > plagioMinimo){
        int indicePlagio = comparacionOraciones(texto.get(numOracion), plagio, indiceTexto, indicePlagio, plagioMinimo);
      }
    }


    return -1;
  }*/

  public static int comparacionOraciones(String[] original, String[] plagio, int posOriginal, int posPlagio, int plagioMinimo){
    System.out.println("aqui"+posPlagio+posOriginal);
    int palabrasIguales = 1;
    posOriginal--;
    posPlagio--;
    while(original[posOriginal].equals(plagio[posPlagio]) && palabrasIguales <= plagioMinimo){
      System.out.println(original[posOriginal]);
      System.out.println(plagio[posPlagio]);
      System.out.println("pri");
      posOriginal--;
      posPlagio--;
      palabrasIguales++;
    }
    System.out.println(palabrasIguales+" "+plagioMinimo);
    if(palabrasIguales < plagioMinimo && (original.length - posOriginal) > plagioMinimo &&  (plagio.length - posPlagio) > plagioMinimo){
      posOriginal = posOriginal+ palabrasIguales+1;
      posPlagio = posPlagio+ palabrasIguales+1;
      System.out.println("antes"+posPlagio+posOriginal);
      while(palabrasIguales < plagioMinimo && original[posOriginal].equals(plagio[posPlagio])){
        System.out.println(original[posOriginal]);
        System.out.println(plagio[posPlagio]);
        posOriginal++;
        posPlagio++;
        palabrasIguales++;
        System.out.println("aqui"+posPlagio+posOriginal);
        if(plagio.length == posPlagio || original.length == posOriginal){
          break;
        }
      }
    }
    System.out.println(palabrasIguales);
    System.out.println(plagioMinimo);
    if(palabrasIguales == plagioMinimo){
      return -1;
    }
    return  posPlagio;
  }
}














