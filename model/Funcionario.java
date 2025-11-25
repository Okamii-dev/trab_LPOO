package model;

public class Funcionario extends Pessoa {

    private int idFunc;
    private String cargo;

    public Funcionario(String nome, String cPF, String email, int idFunc, String cargo) {
        super(nome, cPF, email);
        this.idFunc = idFunc;
        this.cargo = cargo;
    }

    public int getIdFunc() {
        return idFunc;
    }

    public void setIdFunc(int idFunc) {
        this.idFunc = idFunc;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    @Override
    public String exibirDados() {
        return "Funcionário: " + getNome()
                + " | CPF: " + getCPF()
                + " | Email: " + getEmail()
                + " | ID Func.: " + idFunc
                + " | Cargo: " + cargo;
    }

}
