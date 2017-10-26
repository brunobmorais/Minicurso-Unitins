package br.com.brunomorais.minicursounitins.bean;

/**
 * Created by Bruno Morais2 on 20/09/2017.
 */

public class VagaEmprego {

    private int codvaga;
    private String cargo;
    private String empresa;
    private String salario;
    private String cidade;
    private String imagem;
    private String datapostagem;


    public VagaEmprego(int codvaga, String cargo, String empresa, String salario, String cidade, String imagem, String datapostagem) {
        this.codvaga = codvaga;
        this.cargo = cargo;
        this.empresa = empresa;
        this.salario = salario;
        this.cidade = cidade;
        this.imagem = imagem;
        this.datapostagem = datapostagem;
    }

    public VagaEmprego() {
    }

    public int getCodvaga() {
        return codvaga;
    }

    public void setCodvaga(int codvaga) {
        this.codvaga = codvaga;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getDatapostagem() {
        return datapostagem;
    }

    public void setDatapostagem(String datapostagem) {
        this.datapostagem = datapostagem;
    }
}
