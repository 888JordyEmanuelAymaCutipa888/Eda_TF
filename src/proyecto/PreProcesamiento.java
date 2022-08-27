import java.util.ArrayList;
public class PreProcesamiento {

  public static ArrayList<String[]> generarTabla(String texto){
    ArrayList<String[]> list = new ArrayList<String[]>();
    //esta funcion (split) retorna un arreglo dependiendo del parametro que se le entregue para separar
    String[] parts = texto.replace("\n", "").split("\\.");
    for (int index = 0; index < parts.length; index++) {
      String oracionFiltrada = "";
      String oracion = parts[index].replace(",", "").replaceAll("\\s[^\\s]{1,3}\\s", " ").replaceAll("\\s[^\\s]{1,3}\\s", " ").replaceAll("^\\s", "");

      String[] conPalabrasRepetidas = oracion.split(" ", -1);
      for(int i=0;i<(conPalabrasRepetidas.length-1) ;i++){
        if(oracionFiltrada.contains(conPalabrasRepetidas[i]) == false){
          oracionFiltrada = oracionFiltrada+conPalabrasRepetidas[i] + " ";
        }
      }
      if(oracionFiltrada.contains(conPalabrasRepetidas[conPalabrasRepetidas.length-1]) == false){
        oracionFiltrada = oracionFiltrada+conPalabrasRepetidas[conPalabrasRepetidas.length-1];
      }
      //aqui sse genero un arreglo apartir de solo los espacios, por lo que convierte oraciones en un arreglo de palabras
      /*System.out.println(index+":"+Arrays.toString(oracionFiltrada.split(" ", -1)));*/
      list.add(oracionFiltrada.split(" ", -1));
    }
    /*System.out.println("\n\n================================");*/
    return list;
  }

  public static AVLTree generarArbol(ArrayList<String> textos){
    AVLTree tree = new AVLTree();
    int maxCantidadPalabras = 0;
    tree.setCantidadDeTextos(textos.size());

    for(int i=0;i<textos.size();i++){
      ArrayList<String[]> texto = generarTabla(textos.get(i));
      if (maxCantidadPalabras < texto.size()) {
        maxCantidadPalabras = texto.size();
      }
      for(int j=0;j<texto.size();j++){
        for(int k=0; k<texto.get(j).length;k++){
          tree.root = tree.insert(i,k,j, texto.get(j)[k]);
        }
      }
    }
    tree.setCantidadDePalabras(maxCantidadPalabras);
    return tree;
  }
}
