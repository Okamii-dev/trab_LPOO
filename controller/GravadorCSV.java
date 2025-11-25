package controller;

import model.*;
import java.io.*;
import java.util.ArrayList;

public class GravadorCSV {

    // ===============================
    // SALVAR LISTA DE LIVROS
    // ===============================
    public void salvarLivros(String caminho, ArrayList<Livro> livros) {

        // Lista temporária que vai guardar as linhas de texto
        ArrayList<String> linhas = new ArrayList<>();

        // Cria o cabeçalho do arquivo (nome das colunas)
        linhas.add("id;titulo;editora;autor;isbn;disponivel");

        // Transforma cada Livro em uma linha de texto separada por ";"
        for (Livro l : livros) {
            linhas.add(
                l.getId() + ";" +
                l.getTitulo() + ";" +
                l.getEditora() + ";" +
                l.getAutor() + ";" +
                l.getIsbn() + ";" +
                l.isDisponibilidade()
            );
        }

        salvarCSV(caminho, linhas);
    }

    // ===============================
    // SALVAR LISTA DE LEITORES
    // ===============================
    public void salvarLeitores(String caminho, ArrayList<Leitor> leitores) {

        ArrayList<String> linhas = new ArrayList<>();

        // Cabeçalho
        linhas.add("nome;cpf;email;idUser;tipo");

        // Transforma cada Leitor em texto
        for (Leitor l : leitores) {
            linhas.add(
                l.getNome() + ";" +
                l.getCPF() + ";" +
                l.getEmail() + ";" +
                l.getIdUser() + ";" +
                l.getTipo()
            );
        }

        salvarCSV(caminho, linhas);
    }

    // ===============================
    // SALVAR LISTA DE EMPRÉSTIMOS
    // ===============================
    public void salvarEmprestimos(String caminho, ArrayList<Emprestimo> emprestimos){
        ArrayList<String> linhas = new ArrayList<>();

        linhas.add("id;matricula;codigoLivro;dataEmprestimo;dataDevolucao");

        for (Emprestimo e: emprestimos){
            // IMPORTANTE: Aqui salvamos apenas os IDs (códigos) do Leitor e do Livro.
            // Não salvamos o nome deles de novo para economizar espaço e manter integridade.
            linhas.add(
                e.getIdEmprestimo()  + ";" +
                e.getLeitor().getIdUser() + ";" + // Salva ID do Leitor
                e.getLivro().getId()  + ";" +     // Salva ID do Livro
                e.getDateEmprestimo()  + ";" +
                e.getDateDevolucao()
            );
        }

        salvarCSV(caminho, linhas);
    }

    // ===============================
    // MÉTODO GENÉRICO (O "MOTOR")
    // ===============================
    // Este método recebe qualquer lista de textos e salva no arquivo.
    // É "private" porque só é usado internamente por essa classe.
    private void salvarCSV(String caminho, ArrayList<String> linhas) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminho))) {

            for (String linha : linhas) {
                bw.write(linha);   // Escreve a linha
                bw.newLine();      // Pula para a próxima linha (Enter)
            }

        } catch (Exception e) {
            System.out.println("Erro ao salvar arquivo: " + e.getMessage());
        }
    }
}