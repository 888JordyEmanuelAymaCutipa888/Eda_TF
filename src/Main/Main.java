import javax.swing.SwingUtilities;

/**
 *
 * @author jordy
 */
public class Main {
  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        new BuscadorDePlagio();
      }
    });
  }
}
