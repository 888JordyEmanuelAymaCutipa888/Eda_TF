import java.util.ArrayList;

public class PlagiarismChecker{

  public static void main(String[] args) {

    ArrayList<String> paths = new ArrayList<String>();
    String plagioPath = "/home/jordy/tercerS/eda/misP/archivosPrueba/plagio.txt";
    AVLTree arbolDB = new AVLTree();
    paths.add("/home/jordy/tercerS/eda/misP/archivosPrueba/uno.txt");
    paths.add("/home/jordy/tercerS/eda/misP/archivosPrueba/dos.txt");
    paths.add("/home/jordy/tercerS/eda/misP/archivosPrueba/99.txt");
    paths.add("/home/jordy/tercerS/eda/misP/archivosPrueba/tres.txt");

    arbolDB = loadFiles(paths);
    ResultChecker Oresult = verifyPlagiarism(plagioPath, arbolDB);

    for (int i = 0; i< Oresult.result.length; i++) {
      System.out.println("texto "+i+": "+Oresult.result[i]);
    }

  }

  public static AVLTree loadFiles(ArrayList<String> paths){
    boolean error = false;
    ArrayList<String> textos = new ArrayList<String>();
    for (int i = 0; i < paths.size(); i++) {
      String text = LeerTexto.leerTexto(paths.get(i));
      if (text != null) {
        textos.add(text);
      }else{
        System.out.println("No se leyó su archivo "+(i+1));
        error = true;
      }
    }
    AVLTree arbolDB = new AVLTree();
    if (!textos.isEmpty() && error == false) {
      arbolDB = PreProcesamiento.generarArbol(textos);
    }

    return arbolDB;
  }

  public static ResultChecker verifyPlagiarism(String path, AVLTree arbolDB){
    //retornar retultados del sistema
    ResultChecker result = null;
    String plagio = LeerTexto.leerTexto(path);
    if (plagio != null) {
      result = new ResultChecker(Algoritmo.retonarBooleanos(arbolDB, plagio));
    }else{
      System.out.println("No se leyó su archivo ");
    }
    return result;
  }

  public static boolean[] buscarPlagio(String path, ArrayList <String> paths){

    boolean[] result = null;
    AVLTree arbolDB = new AVLTree();
    arbolDB = loadFiles(paths);
    ResultChecker RCresult = verifyPlagiarism(path, arbolDB);
    if (RCresult != null) {
      result = RCresult.getResult();
    }
    return result;
  }


}
