package controller;

import model.*;
import java.util.ArrayList;

public class Biblioteca {

    private ArrayList<Livro> livros = new ArrayList<>();
    private ArrayList<Leitor> leitores = new ArrayList<>();
    private ArrayList<Emprestimo> emprestimos = new ArrayList<>();

    // ------------ PARTE DOS LIVROS ------------
    
    // MUDANÇA AQUI: Adicionei "throws Exception" para avisar erro se duplicar
    public void adicionarLivro(Livro novoLivro) throws Exception {
        
        // Antes de adicionar, verifica se já existe
        for (Livro l : livros) {
            // Verifica duplicidade de ID
            if (l.getId() == novoLivro.getId()) {
                throw new Exception("Já existe um livro com o ID " + novoLivro.getId());
            }
            // Verifica duplicidade de ISBN (opcional, mas recomendado)
            if (l.getIsbn() == novoLivro.getIsbn()) {
                throw new Exception("Já existe um livro com o ISBN " + novoLivro.getIsbn());
            }
        }

        livros.add(novoLivro);
    }

    public ArrayList<Livro> getLivros() {
        return livros;
    }

    public Livro buscarLivroPorCodigo(int codigo) {
        for (Livro l : livros) {
            if (l.getId() == codigo) {
                return l;
            }
        }
        return null;
    }

    // ------------ PARTE DOS LEITORES ------------
    
    // MUDANÇA AQUI: Adicionei "throws Exception"
    public void adicionarLeitor(Leitor novoLeitor) throws Exception {
        
        // Verifica duplicidade
        for (Leitor l : leitores) {
            // Não pode repetir matrícula
            if (l.getIdUser() == novoLeitor.getIdUser()) {
                throw new Exception("Já existe um leitor com a Matrícula " + novoLeitor.getIdUser());
            }
            // Não pode repetir CPF
            if (l.getCPF().equals(novoLeitor.getCPF())) {
                throw new Exception("Já existe um leitor com o CPF " + novoLeitor.getCPF());
            }
        }

        leitores.add(novoLeitor);
    }

    public ArrayList<Leitor> getLeitores() {
        return leitores;
    }

    public Leitor buscarLeitorPorMatricula(int matricula) {
        for (Leitor l : leitores) {
            if (l.getIdUser() == matricula) {
                return l;
            }
        }
        return null;
    }

    // ------------ PARTE DOS EMPRÉSTIMOS ------------

    public Emprestimo realizarEmprestimo(int matricula, int codigoLivro) throws Exception {

        Leitor leitor = buscarLeitorPorMatricula(matricula);
        if (leitor == null) {
            throw new Exception("Leitor não encontrado!");
        }

        Livro livro = buscarLivroPorCodigo(codigoLivro);
        if (livro == null) {
            throw new Exception("Livro não encontrado!");
        }

        if (!livro.isDisponibilidade()) {
            throw new Exception("O livro já está emprestado!");
        }

        Emprestimo emprestimo = new Emprestimo(leitor, livro);

        emprestimos.add(emprestimo);
        livro.setDisponibilidade(false);

        return emprestimo;
    }

    public void devolverLivro(int idEmprestimo) throws Exception {
        for (Emprestimo e : emprestimos) {
            if (e.getIdEmprestimo() == idEmprestimo) {

                if (e.getDateDevolucao() != null) {
                    throw new Exception("Este empréstimo já foi finalizado!");
                }

                e.devolver();
                return;
            }
        }
        throw new Exception("Empréstimo não encontrado!");
    }

    public ArrayList<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public void carregarDados() {

        LeitorCSV lerCSV = new LeitorCSV();
        livros = lerCSV.lerLivros("dados/livros.csv");
        leitores = lerCSV.lerLeitores("dados/leitores.csv");
        emprestimos = lerCSV.lerEmprestimos("dados/emprestimos.csv", leitores, livros);
    }
}