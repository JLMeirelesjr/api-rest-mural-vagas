package br.com.apirestmuralvagas.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "tb_vagas") // Adicionado "tb_" para manter o padrão
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VagaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O título da vaga é obrigatório.")
    @Column(nullable = false)
    private String titulo;

    @NotBlank(message = "A descrição da vaga é obrigatória.")
    @Column(nullable = false, columnDefinition = "TEXT") // Permite textos longos no banco
    private String descricao;

    @NotBlank(message = "A localização é obrigatória.")
    @Column(nullable = false)
    private String localizacao;

    // Usado BigDecimal para valores monetários.
    @Column(nullable = false)
    private BigDecimal salario;

    @NotBlank(message = "O status da vaga é obrigatório.")
    @Column(nullable = false)
    private String status;

    @CreationTimestamp // O Hibernate preenche a data automaticamente no momento do insert
    @Column(updatable = false) // Garante que a data de criação nunca seja alterada num "update"
    private LocalDate dataCriacao;

    @Column(nullable = false)
    private Long recrutadorId;


}