package br.ifpe.transtech.transtech.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioDAO extends JpaRepository<Usuario, Integer> {

    boolean existsByEmailAndSenha(String email, String senha);

    boolean existsByEmail(String email);

    public Usuario findByEmailAndSenha(String email, String senha);

    public Usuario findByEmail(String email);

    public Usuario findByEmailAndCodRecuperacao(String email, long codRecuperacao);
}
