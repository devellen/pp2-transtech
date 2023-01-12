package br.ifpe.transtech.transtech.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidaturaDAO extends JpaRepository<Candidatura, Integer>{

    boolean existsByVagaAndUsuario(Vaga vaga, Usuario usuario);
	
	List <Candidatura> findByVaga(Vaga vaga);
}
