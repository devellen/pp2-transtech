package br.ifpe.transtech.transtech.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ifpe.transtech.transtech.model.EmailModel;

public interface EmailRepository extends JpaRepository<EmailModel, Long>{
    
}
