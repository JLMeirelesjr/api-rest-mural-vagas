package br.com.apirestmuralvagas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public record VagaRequestDTO(
        @NotBlank(message = "O título é obrigatório")
        String titulo,

        @NotBlank(message = "A descrição é obrigatória")
        String descricao,

        @NotBlank(message = "A localização é obrigatória")
        String localizacao,

        @NotNull(message = "O salário é obrigatório")
        @Positive(message = "O salário deve ser maior que zero")
        BigDecimal salario,

        @NotBlank(message = "O status é obrigatório")
        String status,

        @NotNull(message = "O ID do recrutador é obrigatório")
        Long recrutadorId
) {}