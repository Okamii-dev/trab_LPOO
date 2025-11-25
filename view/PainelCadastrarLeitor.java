package view;

import javax.swing.*;
import java.awt.*;

public class PainelCadastrarLeitor extends JPanel {

    private JTextField campoIdLeitor;
    private JTextField campoCPF;
    private JTextField campoNome;
    private JTextField campoEmail;
    private JComboBox<String> selecao;
    private JButton salvar;

    public PainelCadastrarLeitor() {
        setLayout(new GridLayout(6, 2, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(40, 80, 40, 80));

        add(new JLabel("Nome:"));
        campoNome = new JTextField();
        add(campoNome);

        add(new JLabel("CPF:"));
        campoCPF = new JTextField();
        add(campoCPF);

        add(new JLabel("Matrícula:"));
        campoIdLeitor = new JTextField();
        add(campoIdLeitor);

        add(new JLabel("Email:"));
        campoEmail = new JTextField();
        add(campoEmail);

        add(new JLabel("Selecione: "));
        String [] tipos = {"Aluno", "Professor"};
        selecao = new JComboBox<>(tipos);
        add(selecao);
        
        
        // Botão salvar
        salvar = new JButton("Salvar");
        add(salvar);
        add(new JLabel());
    }

    public int getCampoIdLeitor() {
        return Integer.parseInt(campoIdLeitor.getText());
    }

    public String getCampoCPF() {
        return campoCPF.getText();
    }

    public String getCampoNome() {
        return campoNome.getText();
    }

    public String getCampoEmail() {
        return campoEmail.getText();
    }

    public String getTipo(){
        return selecao.getSelectedItem().toString();
    }

    public JButton getBotaoSalvar() {
        return salvar;
    }

    public void limparCampos(){
        campoCPF.setText("");
        campoEmail.setText("");
        campoIdLeitor.setText("");
        campoNome.setText("");
        selecao.setSelectedIndex(0);
        
    }

}
