package view;

import javax.swing.*;
import java.awt.*;

public class PainelEmprestar extends JPanel {

    private JTextField campoIdLeitor;
    private JTextField campoIdLivro;
    private JButton confirmar;

    public PainelEmprestar() {
        setLayout(new GridLayout(4, 2, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(40, 80, 40, 80));


        add(new JLabel("Matrícula do Leitor:"));
        campoIdLeitor = new JTextField();
        add(campoIdLeitor);

        add(new JLabel("Código do Livro:"));
        campoIdLivro = new JTextField();
        add(campoIdLivro);

        confirmar = new JButton("Confirmar");
        add(confirmar);
        add(new JLabel());
    }

    public int  getCampoIdLeitor() {
        return Integer.parseInt(campoIdLeitor.getText());
    }

    public int getCampoIdLivro() {
        return Integer.parseInt(campoIdLivro.getText());
    }

    public JButton getConfirmar() {
        return confirmar;
    }

    public void limparCampos() {
        campoIdLeitor.setText("");
        campoIdLivro.setText("");
    }

}
