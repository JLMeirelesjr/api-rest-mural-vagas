package br.com.apirestmuralvagas.dto;

public record UsuarioResponseDTO(
        Long id,
        String nome,
        String email
) {}