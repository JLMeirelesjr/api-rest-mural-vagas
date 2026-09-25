package br.com.apirestmuralvagas.controller;

import br.com.apirestmuralvagas.dto.UsuarioRequestDTO;
import br.com.apirestmuralvagas.dto.UsuarioResponseDTO;
import br.com.apirestmuralvagas.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> criarUsuario(@Valid @RequestBody UsuarioRequestDTO usuarioDTO) {
        UsuarioResponseDTO novoUsuario = usuarioService.criarUsuario(usuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarUsuarios() {
        List<UsuarioResponseDTO> usuarios = usuarioService.listaUsuario();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarPorId(@PathVariable Long id) {
        UsuarioResponseDTO usuario = usuarioService.buscaPorId(id);
        return ResponseEntity.ok(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizarUsuario(@PathVariable Long id, @Valid @RequestBody UsuarioRequestDTO dadosAtualizados) {
        UsuarioResponseDTO usuarioAtualizado = usuarioService.atualizarUsuario(id, dadosAtualizados);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        usuarioService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}