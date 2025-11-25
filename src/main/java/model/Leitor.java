package model;

public class Leitor extends Pessoa {

    private int idUser;
    private String tipo;

    public Leitor(String nome, String cPF, String email, int idUser, String tipo) {
        super(nome, cPF, email);
        this.idUser = idUser;
        this.tipo = tipo;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String exibirDados() {
        return "Leitor: " + getNome()
                + " | CPF: " + getCPF()
                + " | Email: " + getEmail()
                + " | Matrícula: " + idUser
                + " | Tipo: " + tipo;
    }

}
