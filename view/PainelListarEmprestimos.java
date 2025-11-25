package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controller.LeitorCSV;
import model.Emprestimo;
import model.Leitor;
import model.Livro;

import java.awt.*;
import java.util.ArrayList;

public class PainelListarEmprestimos extends JPanel {

    private JTable tabela;
    private JScrollPane scroll;

    public PainelListarEmprestimos() {
        setLayout(new BorderLayout());

        tabela = new JTable();
        scroll = new JScrollPane(tabela);

        add(scroll, BorderLayout.CENTER);
    }

    public void atualizarTabela() {

        LeitorCSV leitorCSV = new LeitorCSV();

        // Carregar leitores e livros para reconstruir empréstimos
        ArrayList<Leitor> leitores = leitorCSV.lerLeitores("dados/leitores.CSV");
        ArrayList<Livro> livros = leitorCSV.lerLivros("dados/livros.CSV");

        // Carregar empréstimos
        ArrayList<Emprestimo> emprestimos =
            leitorCSV.lerEmprestimos("dados/emprestimos.CSV", leitores, livros);

        String[] colunas = {"ID", "Matrícula", "Código Livro", "Empréstimo", "Devolução"};

        Object[][] dados = new Object[emprestimos.size()][5];

        int i = 0;
        for (Emprestimo e : emprestimos) {
            dados[i][0] = e.getIdEmprestimo();
            dados[i][1] = e.getLeitor().getIdUser();
            dados[i][2] = e.getLivro().getId();
            dados[i][3] = e.getDateEmprestimo();
            dados[i][4] = (e.getDateDevolucao() == null ? "—" : e.getDateDevolucao());
            i++;
        }

        tabela.setModel(new DefaultTableModel(dados, colunas));
    }
}
