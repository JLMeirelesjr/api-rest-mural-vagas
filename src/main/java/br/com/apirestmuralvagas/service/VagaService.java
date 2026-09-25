package br.com.apirestmuralvagas.service;

import br.com.apirestmuralvagas.dto.VagaRequestDTO;
import br.com.apirestmuralvagas.dto.VagaResponseDTO;
import br.com.apirestmuralvagas.entity.VagaEntity;
import br.com.apirestmuralvagas.exception.VagaNaoEncontraexception;
import br.com.apirestmuralvagas.repository.UsuarioRepository;
import br.com.apirestmuralvagas.repository.VagaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VagaService {

    private final VagaRepository vagaRepository;
    private final UsuarioRepository usuarioRepository;

    // 1. Criar Vaga
    public VagaResponseDTO criarVaga(VagaRequestDTO requestDTO) {
        if (!usuarioRepository.existsById(requestDTO.recrutadorId())) {
            throw new IllegalArgumentException("Recrutador não encontrado pelo ID: " + requestDTO.recrutadorId());
        }

        VagaEntity entity = toEntity(requestDTO);
        entity.setDataCriacao(LocalDate.now()); // Garante que a data de criação seja definida no cadastro

        VagaEntity vagaSalva = vagaRepository.save(entity);
        return toResponseDTO(vagaSalva);
    }

    // 2. Listar todas as Vagas
    public List<VagaResponseDTO> listaVaga() {
        return vagaRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    // 3. Buscar Vaga por ID
    public VagaResponseDTO buscaPorId(Long id) {
        VagaEntity vaga = vagaRepository.findById(id)
                .orElseThrow(() -> new VagaNaoEncontraexception("Vaga não encontrada pelo ID: " + id));
        return toResponseDTO(vaga);
    }

    // 4. Atualizar Vaga
    public VagaResponseDTO atualizarVaga(Long id, VagaRequestDTO dadosAtualizados) {
        VagaEntity vagaExistente = vagaRepository.findById(id)
                .orElseThrow(() -> new VagaNaoEncontraexception("Vaga não encontrada pelo ID: " + id));

        if (!usuarioRepository.existsById(dadosAtualizados.recrutadorId())) {
            throw new IllegalArgumentException("Recrutador não encontrado pelo ID: " + dadosAtualizados.recrutadorId());
        }

        vagaExistente.setTitulo(dadosAtualizados.titulo());
        vagaExistente.setDescricao(dadosAtualizados.descricao());
        vagaExistente.setLocalizacao(dadosAtualizados.localizacao());
        vagaExistente.setSalario(dadosAtualizados.salario());
        vagaExistente.setStatus(dadosAtualizados.status());
        vagaExistente.setRecrutadorId(dadosAtualizados.recrutadorId());

        VagaEntity vagaAtualizada = vagaRepository.save(vagaExistente);
        return toResponseDTO(vagaAtualizada);
    }

    // 5. Deletar Vaga
    public void deletarVaga(Long id) {
        if (!vagaRepository.existsById(id)) {
            throw new VagaNaoEncontraexception("Vaga não encontrada pelo ID: " + id);
        }
        vagaRepository.deleteById(id);
    }

    // --- Métodos Utilitários de Mapeamento ---
    private VagaEntity toEntity(VagaRequestDTO dto) {
        VagaEntity entity = new VagaEntity();
        entity.setTitulo(dto.titulo());
        entity.setDescricao(dto.descricao());
        entity.setLocalizacao(dto.localizacao());
        entity.setSalario(dto.salario());
        entity.setStatus(dto.status());
        entity.setRecrutadorId(dto.recrutadorId());
        return entity;
    }

    private VagaResponseDTO toResponseDTO(VagaEntity entity) {
        return new VagaResponseDTO(
                entity.getId(),
                entity.getTitulo(),
                entity.getDescricao(),
                entity.getLocalizacao(),
                entity.getSalario(),
                entity.getStatus(),
                entity.getDataCriacao(),
                entity.getRecrutadorId()
        );
    }
}