package controller;

import model.*;
import java.io.*;
import java.util.ArrayList;

public class LeitorCSV {

    // ===============================
    // LER ARQUIVO DE LIVROS
    // ===============================
    public ArrayList<Livro> lerLivros(String caminho) {

        ArrayList<Livro> lista = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {

            String linha;

            while ((linha = br.readLine()) != null) {

                if (linha.isBlank() || linha.startsWith("id")) {
                    continue;
                }

                String[] dados = linha.split(";");

                if (dados.length < 6) {
                    continue; // evita erro
                }
                int id = Integer.parseInt(dados[0]);
                String titulo = dados[1];
                String editora = dados[2];
                String autor = dados[3];
                long isbn = Long.parseLong(dados[4]);
                boolean disponivel = Boolean.parseBoolean(dados[5]);

                Livro livro = new Livro(id, titulo, editora, autor, isbn);
                livro.setDisponibilidade(disponivel);

                lista.add(livro);
            }

        } catch (Exception e) {
            System.out.println("Erro ao ler livros: " + e.getMessage());
        }

        return lista;
    }

    // ===============================
    // LER ARQUIVO DE LEITORES
    // ===============================
    public ArrayList<Leitor> lerLeitores(String caminho) {

        ArrayList<Leitor> lista = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {

            String linha;

            while ((linha = br.readLine()) != null) {

                if (linha.isBlank() || linha.startsWith("nome")) {
                    continue;
                }

                String[] dados = linha.split(";");

                if (dados.length < 5) {
                    continue;
                }

                String nome = dados[0];
                String cpf = dados[1];
                String email = dados[2];
                int idUser = Integer.parseInt(dados[3]);
                String tipo = dados[4];

                Leitor leitor = new Leitor(nome, cpf, email, idUser, tipo);

                lista.add(leitor);
            }

        } catch (Exception e) {
            System.out.println("Erro ao ler arquivo de leitores: " + e.getMessage());
        }

        return lista;
    }

    public ArrayList<Emprestimo> lerEmprestimos(String caminho, ArrayList<Leitor> leitores, ArrayList<Livro> livros) {

        ArrayList<Emprestimo> lista = new ArrayList<>();
        int maiorId = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {

            String linha;

            while ((linha = br.readLine()) != null) {

                if (linha.isBlank() || linha.startsWith("id")) {
                    continue;
                }

                String[] dados = linha.split(";");

                if (dados.length < 5) {
                    continue;
                }

                int idEmp = Integer.parseInt(dados[0]);
                int idLeitor = Integer.parseInt(dados[1]);
                int idLivro = Integer.parseInt(dados[2]);
                String dataEmp = dados[3];
                String dataDev = dados[4];

                // ======= buscar leitor =======
                Leitor leitor = null;
                for (Leitor l : leitores) {
                    if (l.getIdUser() == idLeitor) {
                        leitor = l;
                        break;
                    }
                }

                // ======= buscar livro =======
                Livro livro = null;
                for (Livro lv : livros) {
                    if (lv.getId() == idLivro) {
                        livro = lv;
                        break;
                    }
                }

                if (livro == null || leitor == null) {
                    continue;
                }

                // ======= criar empréstimo =======
                Emprestimo emp = new Emprestimo(leitor, livro);

                emp.setIdEmprestimo(idEmp);
                emp.setDateEmprestimo(java.time.LocalDate.parse(dataEmp));

                if (!dataDev.equals("null")) {
                    emp.setDateDevolucao(java.time.LocalDate.parse(dataDev));
                }

                // Se não devolveu → manter livro como indisponível
                if (dataDev.equals("null")) {
                    livro.setDisponibilidade(false);
                }

                lista.add(emp);

                // ======= atualizar maior ID =======
                if (idEmp > maiorId) {
                    maiorId = idEmp;
                }
            }

            // Atualiza contador estático igual seu código exige
            Emprestimo.setIdCont(maiorId + 1);

        } catch (Exception e) {
            System.out.println("Erro ao ler empréstimos: " + e.getMessage());
        }

        return lista;
    }

}
