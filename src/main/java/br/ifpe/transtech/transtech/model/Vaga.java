package br.ifpe.transtech.transtech.model;

public class Vaga {
    private Integer codVaga;
    private String nome;
    private String descricao;
    private Endereco endereco;
    
    public Integer getCodVaga() {
        return codVaga;
    }
    public void setCodVaga(Integer codVaga) {
        this.codVaga = codVaga;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public Endereco getEndereco() {
        return endereco;
    }
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
