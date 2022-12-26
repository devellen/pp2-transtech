package br.ifpe.transtech.transtech.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Vaga {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codVaga;
    private String nome;
    private String descricao;
    private double salario;
    private String horario;
    private Cidade cidade;
    @ManyToOne
    private Empresa empresa;
    
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
    
    public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
	public Cidade getCidade() {
		return cidade;
	}
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
}
