import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LeerTexto {

  public static String leerTexto(String rutaFichero) {

    ArrayList<String> lineas = new ArrayList<String>();
    String texto = "";

    try {
      BufferedReader lector = new BufferedReader(new FileReader(rutaFichero));
      String linea = lector.readLine();
      while (linea != null) { 
        texto = texto + linea;	
        lineas.add(linea); 
        linea = lector.readLine(); 
      }

      lector.close(); 
      return texto; 

    } catch (FileNotFoundException e) {
      System.out.println("No sXT(e encuentra fichero:\n" + rutaFichero);
      return null;

    } catch (IOException e) {
      System.out.println("Error accediendo a:\n" + rutaFichero);
      return null;
    }
  }
}
