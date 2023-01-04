package br.ifpe.transtech.transtech.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VagaDao extends JpaRepository<Vaga, Integer>{
	@Query("select v from Vaga v where v.empresa.codigo like :codigo")
	public List <Vaga> listaVaga(Integer codigo);

	@Query("select v from Vaga v where v.nome like %?1%")
	public List <Vaga> findByName(String nome);
	
}
