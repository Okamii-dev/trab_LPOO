package view;

import javax.swing.*;
import java.awt.*;
import model.*;
import controller.*;

// Janela Principal do sistema (Interface Gráfica)
public class TelaPrincipal extends JFrame {

    private CardLayout cardLayout; // Gerenciador para trocar as telas
    private JPanel conteudo;
    private Biblioteca biblioteca;
    private Funcionario funcLogado;

    // Utilitários para salvar e ler arquivos
    private GravadorCSV salvarCSV = new GravadorCSV();
    private LeitorCSV lerCSV = new LeitorCSV();

    public TelaPrincipal() {

        // 1. INICIALIZAÇÃO
        // Cria o funcionário "logado" e carrega os dados dos arquivos para a memória
        funcLogado = new Funcionario("Alexsander Amorim Borchardt", "214.608.277-16", "admin@biblio.com", 1, "Bibliotecário Chefe");
        biblioteca = new Biblioteca();
        biblioteca.carregarDados();

        // Configurações visuais da janela (Título, Tamanho, etc)
        setTitle("Sistema de Biblioteca - Logado como: " + funcLogado.getNome() + " (" + funcLogado.getCargo() + ")");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 2. MENU LATERAL
        // Cria os botões de navegação à esquerda
        JPanel menu = new JPanel();
        menu.setLayout(new GridLayout(10, 1, 5, 5));
        menu.setPreferredSize(new Dimension(200, 600));
        menu.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        JButton btnHome = new JButton("🏠 Início");
        JButton btnCadastrarLivro = new JButton("Cadastrar Livro");
        JButton btnListarLivros = new JButton("Listar Livros");
        JButton btnCadastrarLeitor = new JButton("Cadastrar Leitor");
        JButton btnListarLeitores = new JButton("Listar Leitores");
        JButton btnEmprestar = new JButton("Empréstimo");
        JButton btnListarEmprestimos = new JButton("Listar Empréstimos");
        JButton btnDevolver = new JButton("Devolução");
        JButton btnSobre = new JButton("Sobre o Usuário");

        menu.add(btnHome);
        menu.add(btnSobre);
        menu.add(btnCadastrarLivro);
        menu.add(btnListarLivros);
        menu.add(btnCadastrarLeitor);
        menu.add(btnListarLeitores);
        menu.add(btnEmprestar);
        menu.add(btnListarEmprestimos);
        menu.add(btnDevolver);

        add(menu, BorderLayout.WEST);

        // 3. ÁREA DE CONTEÚDO (TELAS)
        // Instancia todos os painéis e adiciona no CardLayout
        cardLayout = new CardLayout();
        conteudo = new JPanel(cardLayout);

        PainelHome painelHome = new PainelHome();
        PainelCadastrarLivro painelCadLivro = new PainelCadastrarLivro();
        PainelListarLivros painelListLivros = new PainelListarLivros();
        PainelCadastrarLeitor painelCadLeitor = new PainelCadastrarLeitor();
        PainelListarLeitores painelListLeitor = new PainelListarLeitores();
        PainelEmprestar painelEmprestar = new PainelEmprestar();
        PainelListarEmprestimos painelListEmp = new PainelListarEmprestimos();
        PainelDevolver painelDevolver = new PainelDevolver();

        // Dá "nomes" para cada tela para podermos chamar depois
        conteudo.add(painelHome, "home");
        conteudo.add(painelCadLivro, "cadLivro");
        conteudo.add(painelListLivros, "listLivro");
        conteudo.add(painelCadLeitor, "cadLeitor");
        conteudo.add(painelListLeitor, "listLeitor");
        conteudo.add(painelEmprestar, "emprestar");
        conteudo.add(painelListEmp, "listEmp");
        conteudo.add(painelDevolver, "devolver");

        add(conteudo, BorderLayout.CENTER);

        // 4. AÇÕES DE NAVEGAÇÃO
        // Botões que apenas trocam a tela visível
        btnHome.addActionListener(e -> cardLayout.show(conteudo, "home"));

        // Botão que prova o Polimorfismo do Funcionário
        btnSobre.addActionListener(e -> { 
            JOptionPane.showMessageDialog(null, funcLogado.exibirDados());
        });

        btnCadastrarLivro.addActionListener(e -> cardLayout.show(conteudo, "cadLivro"));

        // Ao listar, atualiza a tabela antes de mostrar a tela
        btnListarLivros.addActionListener(e -> {
            painelListLivros.atualizarTabela();
            cardLayout.show(conteudo, "listLivro");
        });

        btnCadastrarLeitor.addActionListener(e -> cardLayout.show(conteudo, "cadLeitor"));

        btnListarLeitores.addActionListener(e -> {
            painelListLeitor.atualizarTabela();
            cardLayout.show(conteudo, "listLeitor");
        });

        btnEmprestar.addActionListener(e -> cardLayout.show(conteudo, "emprestar"));

        btnListarEmprestimos.addActionListener(e -> {
            painelListEmp.atualizarTabela();
            cardLayout.show(conteudo, "listEmp");
        });

        btnDevolver.addActionListener(e -> cardLayout.show(conteudo, "devolver"));

        // ============================================================
        //                     AÇÕES DE CADASTRO (LÓGICA)
        // ============================================================
        
        // Botão Salvar Livro: Pega dados da tela -> Salva na Lista -> Salva no Arquivo
        painelCadLivro.getBotaoSalvar().addActionListener(e -> {
            try {
                int idLivro = painelCadLivro.getIdLivro();
                String titulo = painelCadLivro.getTituloLivro();
                String editora = painelCadLivro.getEditora();
                String autor = painelCadLivro.getAutor();
                long isbn = painelCadLivro.getIsbn();

                Livro livro = new Livro(idLivro, titulo, editora, autor, isbn);

                biblioteca.adicionarLivro(livro);

                // Persistência
                salvarCSV.salvarLivros("dados/livros.CSV", biblioteca.getLivros());

                JOptionPane.showMessageDialog(null, "Livro cadastrado com sucesso!");
                painelCadLivro.limparCampos();

            } catch (Exception li) {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar livro!");
            }
        });

        // Botão Salvar Leitor: Salva e mostra popup com Polimorfismo
        painelCadLeitor.getBotaoSalvar().addActionListener(e -> {
            try {
                int idLeitor = painelCadLeitor.getCampoIdLeitor();
                String nome = painelCadLeitor.getCampoNome();
                String CPF = painelCadLeitor.getCampoCPF();
                String email = painelCadLeitor.getCampoEmail();
                String tipo = painelCadLeitor.getTipo();

                Leitor leitor = new Leitor(nome, CPF, email, idLeitor, tipo);

                biblioteca.adicionarLeitor(leitor);

                salvarCSV.salvarLeitores("dados/leitores.CSV", biblioteca.getLeitores());

                // Exibe dados usando o método sobrescrito (Polimorfismo)
                JOptionPane.showMessageDialog(null, "Cadastro realizado!\n\n" + leitor.exibirDados());
                painelCadLeitor.limparCampos();

            } catch (Exception le) {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar leitor!");
            }
        });

        // Botão Confirmar Empréstimo
        painelEmprestar.getConfirmar().addActionListener(e -> {
            try {
                int idLeitor = painelEmprestar.getCampoIdLeitor();
                int idLivro = painelEmprestar.getCampoIdLivro();

                // Chama regra de negócio na biblioteca
                biblioteca.realizarEmprestimo(idLeitor, idLivro);

                // Atualiza arquivos de Empréstimos e de Livros (status mudou)
                salvarCSV.salvarEmprestimos("dados/emprestimos.CSV", biblioteca.getEmprestimos());
                salvarCSV.salvarLivros("dados/livros.CSV", biblioteca.getLivros());

                JOptionPane.showMessageDialog(null, "Emprestimo realizado com sucesso!");
                painelEmprestar.limparCampos();
            } catch (Exception em) {
                JOptionPane.showConfirmDialog(null, "Erro ao realizar empréstimo!" + em.getMessage());
            }
        });

        // Botão Devolução
        painelDevolver.getBtnDevolver().addActionListener(e -> {
            try {
                int idEmp = painelDevolver.getIdEmprestimo();

                biblioteca.devolverLivro(idEmp);

                // Atualiza os arquivos novamente
                salvarCSV.salvarLivros("dados/livros.CSV", biblioteca.getLivros());
                salvarCSV.salvarEmprestimos("dados/emprestimos.CSV", biblioteca.getEmprestimos());

                JOptionPane.showMessageDialog(null, "Livro devolvido com sucesso!");
                painelDevolver.limparCampos();

            } catch (Exception ev) {
                JOptionPane.showMessageDialog(null, "Erro ao devolver: " + ev.getMessage());
            }
        });

    }
}