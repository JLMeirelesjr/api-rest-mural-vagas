package br.com.apirestmuralvagas.dto;

import java.math.BigDecimal;
import java.time.LocalDate;


public record VagaResponseDTO(
        Long id,
        String titulo,
        String descricao,
        String localizacao,
        BigDecimal salario,
        String status,
        LocalDate dataCriacao,
        Long recrutadorId
) {}