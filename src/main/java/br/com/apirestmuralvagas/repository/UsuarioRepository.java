package br.com.apirestmuralvagas.repository;

import br.com.apirestmuralvagas.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    // Método criado automaticamente pelo Spring para verificar duplicidade de e-mail
    boolean existsByEmail(String email);

}