import java.util.ArrayList;
import java.util.Arrays;

public class PruebaDan {
                                                                                                                                                                                                                                                                                                                                                                                                        
  /* method to create a simple linked list with 3 nodes */
  public static void main(String[] args) {

    int cantidadPalabras = 8;
    String txtprb = "Conjunto de enunciados con que el creyente se dirige a Dios. Doña Anaya Uzeada de Ribera rechoncha Maldonado de Bracamonte y Anaya era baja, rechoncha, abigotada. Ya no existia razon para llamar talle al suyo. Sus colores vivos, sanos, podian mas que el y el soliman del afeite, con que se blanqueaba por simular melancolias. Gastaba dos parches oscuros, adheridos a las sienes y que fingian medicamentos. Tenia los ojitos ratoniles, maliciosos. Sabia dilatarlos duramente o desmayarlos con recato o levantarlos con disimulo. Caminaba contoneando las imposibles caderas y era dificil, al verla, no asociar su estampa achaparrada con la de ciertos palmipedos domesticos. Sortijas celestes y azules le ahorcaban las falanges";

    String text2 = "Bracamonte y Anaya era baja, rechoncha, abigotada. Ya no existia razon para llamar talle al suyo. Sus colores vivos, sanos, podian mas que el albayalde y el soliman del afeite, con que se blanqueaba por simular melancolias. Gastaba dos parches oscuros, adheridos a las sienes y que fingian medicamentos. Tenia los ojitos ratoniles, maliciosos. Sabia dilatarlos duramente o desmayarlos con recato o levantarlos con disimulo. Caminaba contoneando las imposibles caderas y era dificil, al verla, no asociar su estampa achaparrada con la de ciertos palmipedos domesticos. Sortijas celestes y azules le ahorcaban las falanges.Conjunto de enunciados con que el dirige creyente se  a Dios.";
    AVLTree arbolUbicacion = new AVLTree();
    ArrayList<String[]> textoTabla = new ArrayList<String[]>();
    ArrayList<String[]> t2 = new ArrayList<String[]>();

    System.out.println("T1----------------------------------------");
    textoTabla = generarTabla(txtprb);
    System.out.println("T2----------------------------------------");
    t2 = generarTabla(text2);
    System.out.println("------------------------------------------");
    arbolUbicacion = generarArbol(textoTabla);

    ArrayList<OracionPlagio> oracionesPlagio = new ArrayList<OracionPlagio>();
    oracionesPlagio = encontrarOracionesPlagiadas(textoTabla.size(), arbolUbicacion, t2);
    System.out.println(mostrarOraciones(textoTabla, t2, oracionesPlagio));
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
      System.out.println("IMPRESION TABLA-----------------------------");
      for (int i = 0; i < list.size(); i++) {
          for (int j = 0; j < list.get(i).length; j++) {
              System.out.print(" "+ list.get(i)[j]);
          }
          System.out.println("");
      }
      System.out.println("IMPRESION TABLA-----------------------------");
    return list;
  }



  public static AVLTree generarArbol(ArrayList<String[]> text){
    AVLTree tree = new AVLTree();
    for(int i=0;i<text.size();i++){
      System.out.println("-"+i);
      for(int j=0;j<text.get(i).length;j++){
          System.out.println("generaRBol"+text.get(i)[j]);
          tree.root = tree.insert(j,i, text.get(i)[j]);
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
  public static ArrayList<OracionPlagio> encontrarOracionesPlagiadas(int original, AVLTree tree, ArrayList <String[]> plagios){
    ArrayList<OracionPlagio> oracionesPlagio = new ArrayList<OracionPlagio>();
    
    System.out.println("cantidad de arreglos de la tabla plagios" + plagios.size() + "");
    
    for (int i = 0; i < plagios.size(); i++) {

      System.out.println("----------------------------------------------------------------");
      int maxPlagiosPorOracion = 0;
      int oracionOriginal=-1;
      int oracionPlagio = i;
      
      int[] palabrasPlagiadasXoracion = new int[original];
      
      for(int z = 0; z < palabrasPlagiadasXoracion.length; z++){
        palabrasPlagiadasXoracion[z] = 0;
      }
     
      //a nivel de oracion
      for(int j = 0; j < plagios.get(i).length; j++){
        System.out.println("---value"+j);
        String palabraPlagio = plagios.get(i)[j];
        Node palabra = tree.getNode(palabraPlagio);
        System.out.println(tree.getNode(palabraPlagio));
        if(palabra != null){
          ArrayList<UbicacionPalabra> ubicacionesPalabra = new ArrayList<UbicacionPalabra>();
          ubicacionesPalabra = palabra.ubicacionesPalabras;
          
          //A nivel de palabras
          for(int k = 0; k < ubicacionesPalabra.size(); k++){
            System.out.println("Palabra: "+palabra.valor+" ---Oracion de ubicacion: "+ubicacionesPalabra.get(k).oracion+" ");
            palabrasPlagiadasXoracion[ubicacionesPalabra.get(k).oracion] += 1;
          }
        }else if(tree.getNode(palabraPlagio) == null){
            System.out.println("Notificando vacía,  La palabra es: "+palabraPlagio);
          }
        
      }

      System.out.println("Oraciones: ");
      for(int m = 0; m < palabrasPlagiadasXoracion.length; m++){
        System.out.println("pos: "+ m+" imprimiendo cantidadPalabras: "+ palabrasPlagiadasXoracion[m]);
        if(maxPlagiosPorOracion < palabrasPlagiadasXoracion[m]){
          System.out.println("entra if");
          maxPlagiosPorOracion = palabrasPlagiadasXoracion[m];
          oracionOriginal = m;
        }
      }
      System.out.println("max Plagios por oracion: "+maxPlagiosPorOracion);
      if(maxPlagiosPorOracion >= 3){
        System.out.println("guarda: "+oracionOriginal+" p: "+oracionPlagio+" max: "+maxPlagiosPorOracion);
        oracionesPlagio.add(new OracionPlagio(oracionOriginal, oracionPlagio, maxPlagiosPorOracion));
      }
    }
    System.out.println("funcionEncontrar Plagios");
    return oracionesPlagio;
  }

  public static String mostrarOraciones(ArrayList<String[]> original, ArrayList<String[]> plagio, ArrayList<OracionPlagio> plagios){

    System.out.println("funcion mostrar");
    String mostrar = "";
    for (int i = 0; i < plagios.size(); i++) {
      System.out.println("-------------------");
      mostrar += plagios.get(i).toString();
      System.out.println(plagios.get(i).oracionEnPlagio+"");
      mostrar += "\nORACION EN PLAGIO: "+ mostrarArreglo(plagio.get(plagios.get(i).oracionEnPlagio)) +"\n" + 
        "ORACIÓN EN  TEXTO: " + mostrarArreglo(original.get(plagios.get(i).oracionEnTexto))+"\n\n";
    }
    System.out.println("funcion mostrar");
    return mostrar;
  }

  public static String mostrarArreglo(String[] oracionArreglo){
    String oracionString = "";
    for(int i = 0; i < oracionArreglo.length; i++){
      oracionString = oracionString + " " + oracionArreglo[i];
    }
    return oracionString;
  }

  public static int[] retornarOracionesAnalizadas(int cantidadOraciones, AVLTree tree, String[] oracion){
    System.out.println("metodo----");
    int[] palabrasPlagiadasXoracion = new int[cantidadOraciones];
    for(int j = 0; j < oracion.length; j++){
      System.out.println("---value"+j);
      String palabraPlagio = oracion[j];
      Node palabra = tree.getNode(palabraPlagio);
      System.out.println(tree.getNode(palabraPlagio));
      if(palabra != null){
        ArrayList<UbicacionPalabra> ubicacionesPalabra = new ArrayList<UbicacionPalabra>();
        ubicacionesPalabra = palabra.ubicacionesPalabras;

        //A nivel de palabras
        for(int k = 0; k < ubicacionesPalabra.size(); k++){
          System.out.println("Palabra: "+palabra.valor+" ---Oracion de ubicacion: "+ubicacionesPalabra.get(k).oracion+" ");
          palabrasPlagiadasXoracion[ubicacionesPalabra.get(k).oracion] += 1;
        }
      }else if(tree.getNode(palabraPlagio) == null){
        System.out.println("Notificando vacía,  La palabra es: "+palabraPlagio);
      }

    }
    return palabrasPlagiadasXoracion;
  }
}













