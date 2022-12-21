package br.ifpe.transtech.transtech.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioDAO extends JpaRepository<Usuario, Integer> {
    public Usuario findByEmailAndSenha(String email, String senha);
}
