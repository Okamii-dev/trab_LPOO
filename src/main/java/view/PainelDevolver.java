package view;

import javax.swing.*;
import java.awt.*;

public class PainelDevolver extends JPanel {

    private JTextField campoIdEmprestimo;
    private JButton btnDevolver;

    public PainelDevolver() {

        setLayout(new GridLayout(3, 2, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(40, 80, 40, 80));

        add(new JLabel("ID do Empréstimo:"));
        campoIdEmprestimo = new JTextField();
        add(campoIdEmprestimo);

        btnDevolver = new JButton("Devolver");
        add(btnDevolver);

        // espaço vazio só para alinhar
        add(new JLabel());
    }

    public int getIdEmprestimo() {
        return Integer.parseInt(campoIdEmprestimo.getText());
    }

    public JButton getBtnDevolver() {
        return btnDevolver;
    }

    public void limparCampos() {
        campoIdEmprestimo.setText("");
    }
}
