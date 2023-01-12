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
    private Integer cod_vaga; 
    private String endereco;
    private Escolaridade escolaridade;
    @Lob
    private byte[] curriculo;

    @ManyToOne
    private Vaga vaga;
    
    public Vaga getVaga() {
		return vaga;
	}
    public void setVaga(Vaga vaga){
        this.vaga = vaga;
    }
    @ManyToOne
    private Usuario usuario;
    public Usuario getUsuario() {
        return usuario;
    }
    public  void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    
    }
    public Integer getCodVaga() {
        return cod_vaga;
    }
    public void setCodVaga(Integer codVaga) {
        this.cod_vaga = codVaga;
    }
    
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
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
