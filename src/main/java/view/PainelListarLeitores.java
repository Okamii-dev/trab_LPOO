package view;

import javax.swing.*;
import controller.LeitorCSV;
import model.Leitor;
import java.awt.*;
import java.util.ArrayList;

public class PainelListarLeitores extends JPanel {

    private JTable tabela;
    private JScrollPane scroll;

    public PainelListarLeitores() {
        setLayout(new BorderLayout());

        tabela = new JTable();
        scroll = new JScrollPane(tabela);

        add(scroll, BorderLayout.CENTER);
    }

    public void atualizarTabela() {
        LeitorCSV lerCSV = new LeitorCSV();
        ArrayList<Leitor> leitores = lerCSV.lerLeitores("dados/leitores.CSV");

        String[] colunas = {"Nome", "Matrícula", "Tipo"};
        Object[][] dados = new Object[leitores.size()][3];

        int linhas = 0;
        for (Leitor l : leitores) {
            dados[linhas][0] = l.getNome();
            dados[linhas][1] = l.getIdUser();
            dados[linhas][2] = l.getTipo();
            linhas++;
        }

        tabela.setModel(new javax.swing.table.DefaultTableModel(dados, colunas));
    }
}
