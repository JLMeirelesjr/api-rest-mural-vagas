package br.com.apirestmuralvagas.repository;


import br.com.apirestmuralvagas.entity.VagaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VagaRepository extends JpaRepository<VagaEntity, Long> {

}
