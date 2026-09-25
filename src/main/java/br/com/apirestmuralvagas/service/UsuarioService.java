package br.com.apirestmuralvagas.service;

import br.com.apirestmuralvagas.dto.UsuarioRequestDTO;
import br.com.apirestmuralvagas.dto.UsuarioResponseDTO;
import br.com.apirestmuralvagas.entity.UsuarioEntity;
import br.com.apirestmuralvagas.exception.UsuarioNaoEncontraexception;
import br.com.apirestmuralvagas.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    // 1. Criar usuário recebendo DTO e retornando ResponseDTO
    public UsuarioResponseDTO criarUsuario(UsuarioRequestDTO requestDTO) {
        if (usuarioRepository.existsByEmail(requestDTO.email())) {
            throw new IllegalArgumentException("Este e-mail já está em uso.");
        }

        UsuarioEntity entity = toEntity(requestDTO);
        UsuarioEntity usuarioSalvo = usuarioRepository.save(entity);
        return toResponseDTO(usuarioSalvo);
    }

    // 2. Listar todos convertendo para Lista de ResponseDTO
    public List<UsuarioResponseDTO> listaUsuario() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    // 3. Buscar por ID retornando ResponseDTO
    public UsuarioResponseDTO buscaPorId(Long id) {
        UsuarioEntity usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontraexception("Usuário não encontrado pelo ID: " + id));
        return toResponseDTO(usuario);
    }

    // 4. Atualizar usuário recebendo DTO
    public UsuarioResponseDTO atualizarUsuario(Long id, UsuarioRequestDTO dadosAtualizados) {
        UsuarioEntity usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontraexception("Usuário não encontrado pelo ID: " + id));

        if (!usuarioExistente.getEmail().equals(dadosAtualizados.email())
                && usuarioRepository.existsByEmail(dadosAtualizados.email())) {
            throw new IllegalArgumentException("Este e-mail já está em uso por outro usuário.");
        }

        usuarioExistente.setNome(dadosAtualizados.nome());
        usuarioExistente.setEmail(dadosAtualizados.email());
        usuarioExistente.setSenha(dadosAtualizados.senha());

        UsuarioEntity usuarioAtualizado = usuarioRepository.save(usuarioExistente);
        return toResponseDTO(usuarioAtualizado);
    }

    // 5. Deletar usuário
    public void deletarUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new UsuarioNaoEncontraexception("Usuário não encontrado pelo ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }

    // --- Métodos Utilitários de Mapeamento ---
    private UsuarioEntity toEntity(UsuarioRequestDTO dto) {
        UsuarioEntity entity = new UsuarioEntity();
        entity.setNome(dto.nome());
        entity.setEmail(dto.email());
        entity.setSenha(dto.senha());
        return entity;
    }

    private UsuarioResponseDTO toResponseDTO(UsuarioEntity entity) {
        return new UsuarioResponseDTO(
                entity.getId(),
                entity.getNome(),
                entity.getEmail()
        );
    }
}