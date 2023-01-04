package br.ifpe.transtech.transtech.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity
public class Candidatura {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codVaga; 
    private String nome;
    private int idade;
    private String endereco;
    private int telefone;
    private String email;
    private Escolaridade escolaridade;
    @Lob
    private byte[] curriculo;

    @ManyToOne
    private Usuario usuario;
    public Usuario getUsuario() {
        return usuario;
    }
    public static void setUsuario(Usuario usuario) {
        usuario = usuario;
    }
    public Integer getCodVaga() {
        return codVaga;
    }
    public void setCodVaga(Integer codVaga) {
        this.codVaga = codVaga;
    }
    public String getNome() {
        return nome;
    }
    public int getIdade() {
        return idade;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public int getTelefone() {
        return telefone;
    }
    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Escolaridade getEscolaridade() {
        return escolaridade;
    }
    public void setEscolaridade(Escolaridade escolaridade) {
        this.escolaridade = escolaridade;
    }
	public byte[] getCurriculo() {
		return curriculo;
	}
	public void setCurriculo(byte[] curriculo) {
		this.curriculo = curriculo;
	}
}
