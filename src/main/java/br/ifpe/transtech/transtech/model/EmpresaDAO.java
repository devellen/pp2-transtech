package br.ifpe.transtech.transtech.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaDAO extends JpaRepository<Empresa, Integer> {
    public Empresa findByEmailAndSenha(String email, String senha);
}
