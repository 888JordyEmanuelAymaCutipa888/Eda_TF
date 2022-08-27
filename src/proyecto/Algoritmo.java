import java.util.ArrayList;
public class Algoritmo {

  public static boolean[] retonarBooleanos(AVLTree arbolDB, String plagio){

    ArrayList<String[]> plagioTabla = new ArrayList<String[]>();
    plagioTabla = PreProcesamiento.generarTabla(plagio);
    ArrayList<OracionPlagio> oracionesPlagiadas = new ArrayList<OracionPlagio>();
    oracionesPlagiadas = encontrarOracionesPlagiadas(arbolDB, plagioTabla);

    boolean[] booleanosTextos = new boolean[arbolDB.cantidadDeTextos];
    for(int i=0;i<oracionesPlagiadas.size();i++){
      booleanosTextos[oracionesPlagiadas.get(i).textoPerteneciente] = true;
    }

    return booleanosTextos;	
  }

  public static ArrayList<OracionPlagio> encontrarOracionesPlagiadas(AVLTree tree, ArrayList <String[]> plagios){
    ArrayList<OracionPlagio> oracionesPlagio = new ArrayList<OracionPlagio>();

    for (int i = 0; i < plagios.size(); i++) {

      int numTexto = -1;
      int max = 0;
      int maxPlagiosPorOracion = 0;
      int oracionOriginal=-1;
      int oracionPlagio = i;

      int[][] palabrasPlagiadasXoracion = new int[tree.cantidadDeTextos][tree.maxPalabrasPorOracion];

      //a nivel de oracion
      for(int j = 0; j < plagios.get(i).length; j++){
        String palabraPlagio = plagios.get(i)[j];
        Node palabra = tree.getNode(palabraPlagio);
        /*System.out.println(tree.getNode(palabraPlagio));*/
        if(palabra != null){
          ArrayList<UbicacionPalabra> ubicacionesPalabra = new ArrayList<UbicacionPalabra>();
          ubicacionesPalabra = palabra.ubicacionesPalabras;

          //A nivel de palabras
          for(int k = 0; k < ubicacionesPalabra.size(); k++){
            palabrasPlagiadasXoracion[ubicacionesPalabra.get(k).texto][ubicacionesPalabra.get(k).oracion] += 1;
            if(palabrasPlagiadasXoracion[ubicacionesPalabra.get(k).texto][ubicacionesPalabra.get(k).oracion] > max){
              max = palabrasPlagiadasXoracion[ubicacionesPalabra.get(k).texto][ubicacionesPalabra.get(k).oracion];
              numTexto = ubicacionesPalabra.get(k).texto;
              oracionOriginal = ubicacionesPalabra.get(k).oracion;
              maxPlagiosPorOracion = palabrasPlagiadasXoracion[ubicacionesPalabra.get(k).texto][ubicacionesPalabra.get(k).oracion];
            }
          }
        }/*else if(tree.getNode(palabraPlagio) == null){
        System.out.println("No se encontró,  La palabra es: "+palabraPlagio);
        }*/

      }
      /*System.out.println("max Plagios por oracion: "+maxPlagiosPorOracion);*/
      if(maxPlagiosPorOracion >= 3){
        /*System.out.println("guarda: "+oracionOriginal+" p: "+oracionPlagio+" max: "+maxPlagiosPorOracion);*/
        oracionesPlagio.add(new OracionPlagio(numTexto, oracionOriginal, oracionPlagio, maxPlagiosPorOracion));
      }
    }
    return oracionesPlagio;
  } 
}
