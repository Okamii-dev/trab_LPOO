import javax.swing.SwingUtilities;
import view.TelaPrincipal;

public class main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaPrincipal().setVisible(true));
    }
}
