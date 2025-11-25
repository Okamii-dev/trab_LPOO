package view;

import javax.swing.*;
import java.awt.*;

public class PainelHome extends JPanel {

    public PainelHome() {
        setLayout(new BorderLayout());
        JLabel lbl = new JLabel("Bem-vindo ao Sistema de Biblioteca, " , SwingConstants.CENTER);
        lbl.setFont(new Font("Arial", Font.BOLD, 26));
        add(lbl, BorderLayout.CENTER);
    }
}
