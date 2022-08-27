import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

public class BuscadorDePlagio extends JFrame {

  private ArrayList<String> pathsBD = new ArrayList<String>();
  String pathPlagio;

  private JButton btElegir;
  private JTextField jtRuta;

  private JList rutasDB;
  private DefaultListModel rutasModelo;

  private JTextField plagioRuta;
  private JButton plagioElegir;

  private JButton btBuscarPlagio;

  private JTable tablaResult;
  private DefaultTableModel modeloResult;

  public BuscadorDePlagio() {

    iniciarComponentes();

    this.setSize(500,500);
    this.getContentPane().setBackground(Color.orange);

    JPanel pnNorte = new JPanel();
    pnNorte.setLayout(new BoxLayout(pnNorte, BoxLayout.Y_AXIS));
    JPanel pnElegir = new JPanel();

    JPanel panelPlagio = new JPanel();
    panelPlagio.add(new JLabel("Indique ruta del archivo a revisar: "));
    panelPlagio.add(plagioRuta);
    panelPlagio.add(plagioElegir);

    pnElegir.add(new JLabel("Indique ruta del Archivo: "));
    pnElegir.add(jtRuta);
    pnElegir.add(btElegir);

    JPanel panelArchivosDB = new JPanel();
    panelArchivosDB.add(new JLabel("cargados: "));
    panelArchivosDB.add(rutasDB);

    JPanel pnBuscarPlagio = new JPanel();
    pnBuscarPlagio.add(btBuscarPlagio);

    JPanel pnTablaResultado = new JPanel();
    pnTablaResultado.add(tablaResult);



    pnNorte.add(panelPlagio);
    pnNorte.add(new JSeparator(SwingConstants.HORIZONTAL));
    pnNorte.add(pnElegir);
    pnNorte.add(panelArchivosDB);

    pnNorte.setBorder(BorderFactory.createCompoundBorder(
          BorderFactory.createEmptyBorder(5, 5, 50, 5),
          BorderFactory.createRaisedSoftBevelBorder()));
    //Panel central
    JPanel pnCentro = new JPanel();
    pnCentro.add(pnBuscarPlagio);
    pnCentro.add(pnTablaResultado);

    pnCentro.setBorder(BorderFactory.createCompoundBorder(
          BorderFactory.createEmptyBorder(5, 5, 100, 5),
          BorderFactory.createRaisedSoftBevelBorder()));



    setLayout(new BorderLayout());
    add(pnNorte, BorderLayout.NORTH);
    pnCentro.setLayout(new BoxLayout(pnCentro,BoxLayout.Y_AXIS));
    add(pnCentro, BorderLayout.CENTER);

    setTitle("BUSCADOR DE PLAGIO");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
  }

  private void iniciarComponentes() {

    btElegir = new JButton("Seleccionar");
    btElegir.addActionListener(new AccionElegirArchivo());
    jtRuta = new JTextField(15);
    jtRuta.setToolTipText("Ruta del archivo para la base de datos");

    plagioElegir = new JButton("Seleccionar");
    plagioElegir.addActionListener(new AccionElegirPlagio());
    plagioRuta = new JTextField(15);
    plagioRuta.setToolTipText("Ruta del archivo a revisar");

    rutasDB = new JList();
    rutasModelo = new DefaultListModel();
    rutasDB.setModel(rutasModelo);

    btBuscarPlagio = new JButton("Buscar Plagio");
    btBuscarPlagio.addActionListener(new buscarPlagio());

    modeloResult = new DefaultTableModel(2, 10);
    tablaResult = new JTable (modeloResult);
  }


  class AccionElegirPlagio implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

      JFileChooser selector = new JFileChooser();
      selector.showOpenDialog(null);
      String ruta = selector.getSelectedFile().getAbsolutePath();
      plagioRuta.setText(ruta);
      pathPlagio = ruta;
    }
  }

  class AccionElegirArchivo implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

      JFileChooser selector = new JFileChooser();
      selector.showOpenDialog(null);
      String ruta = selector.getSelectedFile().getAbsolutePath();
      jtRuta.setText(ruta); 
      pathsBD.add(ruta);
      rutasModelo.addElement(ruta);
    }
  }

  public class buscarPlagio implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      if (pathsBD.isEmpty() || pathPlagio == null) {
        JOptionPane.showMessageDialog(null, "Parece que los datos no son correctos", "ALGO HA FALLADO",
            JOptionPane.ERROR_MESSAGE);
      } else {
        //Transferimos las lineas leidas, al area de texto

        boolean[] result = PlagiarismChecker.buscarPlagio(pathPlagio, pathsBD);
        if (result == null) {
          JOptionPane.showMessageDialog(null, "Algo falló en el procesamiento", "ALGO HA FALLADO",
              JOptionPane.ERROR_MESSAGE);
        } else {
          for (int i = 0; i < result.length; i++) {
            String tituloF = "Texto "+(i+1);
            String valor = ""+result[i];
            modeloResult.setValueAt(tituloF, 0, i);
            modeloResult.setValueAt(valor, 1, i);
          }
        }
      }
    }
  }
}
