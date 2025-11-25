package model;
import java.time.LocalDate;

public class Emprestimo{
    private static int idCont = 1;
    private int idEmprestimo;
    private LocalDate dateEmprestimo;
    private LocalDate dateDevolucao;
    private Livro livro;
    private Leitor leitor;
    
    public Emprestimo(Leitor leitor, Livro livro){
        this.leitor = leitor;
        this.livro = livro;
        this.dateEmprestimo = LocalDate.now();
        this.dateDevolucao = null;
        idEmprestimo = idCont++;
    }

    public static int getIdCont() {
        return idCont;
    }

    public static void setIdCont(int idCont) {
        Emprestimo.idCont = idCont;
    }

    public int getIdEmprestimo() {
        return idEmprestimo;
    }

    public void setIdEmprestimo(int idEmprestimo) {
        this.idEmprestimo = idEmprestimo;
    }

    public LocalDate getDateEmprestimo() {
        return dateEmprestimo;
    }

    public void setDateEmprestimo(LocalDate dateEmprestimo) {
        this.dateEmprestimo = dateEmprestimo;
    }

    public LocalDate getDateDevolucao() {
        return dateDevolucao;
    }

    public void setDateDevolucao(LocalDate dateDevolucao) {
        this.dateDevolucao = dateDevolucao;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Leitor getLeitor() {
        return leitor;
    }

    public void setLeitor(Leitor leitor) {
        this.leitor = leitor;
    }

    @Override
    public String toString() {
        return "Emprestimo [idEmprestimo=" + idEmprestimo + ", dateEmprestimo=" + dateEmprestimo + ", dateDevolucao="
                + dateDevolucao + ", livro=" + livro + ", leitor=" + leitor + "]";
    }

    public void devolver(){
        this.dateDevolucao = LocalDate.now();
        livro.setDisponibilidade(true);
        System.out.println("Devolução realizda com sucesso!");
        System.out.println(toString());
    }
}
