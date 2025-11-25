package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controller.LeitorCSV;
import model.Livro;

import java.awt.*;
import java.util.ArrayList;

public class PainelListarLivros extends JPanel {

    private JTable tabela;
    private JScrollPane scroll;

    public PainelListarLivros() {
        setLayout(new BorderLayout());

        tabela = new JTable();
        scroll = new JScrollPane(tabela);

        add(scroll, BorderLayout.CENTER);
    }

    public void atualizarTabela() {
        LeitorCSV lerCSV = new LeitorCSV();
        ArrayList<Livro> livros = lerCSV.lerLivros("dados/livros.CSV");

        String[] colunas = {"Título", "Autor", "Código", "Disponível"};
        Object[][] dados = new Object[livros.size()][4];

        int linhas = 0;
        for (Livro l : livros) {
            dados[linhas][0] = l.getTitulo();
            dados[linhas][1] = l.getAutor();
            dados[linhas][2] = l.getId();
            dados[linhas][3] = l.isDisponibilidade() ? "Sim" : "Não"; // ✔ Mais visual
            linhas++;
        }

        tabela.setModel(new DefaultTableModel(dados, colunas));
    }
}
