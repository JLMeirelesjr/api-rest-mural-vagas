package br.com.apirestmuralvagas.controller;

import br.com.apirestmuralvagas.dto.VagaRequestDTO;
import br.com.apirestmuralvagas.dto.VagaResponseDTO;
import br.com.apirestmuralvagas.service.VagaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vagas")
@RequiredArgsConstructor
public class VagaController {

    private final VagaService vagaService;

    @PostMapping
    public ResponseEntity<VagaResponseDTO> criarVaga(@Valid @RequestBody VagaRequestDTO vagaDTO) {
        VagaResponseDTO novaVaga = vagaService.criarVaga(vagaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaVaga);
    }

    @GetMapping
    public ResponseEntity<List<VagaResponseDTO>> listarVagas() {
        List<VagaResponseDTO> vagas = vagaService.listaVaga();
        return ResponseEntity.ok(vagas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VagaResponseDTO> buscarPorId(@PathVariable Long id) {
        VagaResponseDTO vaga = vagaService.buscaPorId(id);
        return ResponseEntity.ok(vaga);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VagaResponseDTO> atualizarVaga(@PathVariable Long id, @Valid @RequestBody VagaRequestDTO dadosAtualizados) {
        VagaResponseDTO vagaAtualizada = vagaService.atualizarVaga(id, dadosAtualizados);
        return ResponseEntity.ok(vagaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarVaga(@PathVariable Long id) {
        vagaService.deletarVaga(id);
        return ResponseEntity.noContent().build();
    }
}