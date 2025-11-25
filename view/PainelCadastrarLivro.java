package view;
import javax.swing.*;
import java.awt.*;

public class PainelCadastrarLivro extends JPanel {

    private JTextField campoId;
    private JTextField campoTitulo;
    private JTextField campoEditora;
    private JTextField campoAutor;
    private JTextField campoIsbn;
    private JButton salvar;

    public PainelCadastrarLivro() {
        setLayout(new GridLayout(6, 2, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(40, 80, 40, 80));

        add(new JLabel("ID: "));
        campoId = new JTextField();
        add(campoId);

        add(new JLabel("Título: "));
        campoTitulo = new JTextField();
        add(campoTitulo);

        add(new JLabel("Editora: "));
        campoEditora = new JTextField();
        add(campoEditora);

        add(new JLabel("Autor: "));
        campoAutor = new JTextField();
        add(campoAutor);

        add(new JLabel("ISBN: "));
        campoIsbn = new JTextField();
        add(campoIsbn);

        // Botão salvar
        salvar = new JButton("Salvar");
        add(salvar);
        add(new JLabel());
    }

    public int getIdLivro() {
        return Integer.parseInt(campoId.getText());
    }

    public String getTituloLivro() {
        return campoTitulo.getText();
    }

    public String getEditora() {
        return campoEditora.getText();
    }

    public String getAutor() {
        return campoAutor.getText();
    }

    public long getIsbn() {
        return Long.parseLong(campoIsbn.getText());
    }

    public JButton getBotaoSalvar() {
        return salvar;
    }

    public void limparCampos() {
        campoId.setText("");
        campoTitulo.setText("");
        campoEditora.setText("");
        campoAutor.setText("");
        campoIsbn.setText("");
    }
}
