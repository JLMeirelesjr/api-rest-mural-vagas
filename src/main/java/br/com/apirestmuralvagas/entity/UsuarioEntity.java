package br.com.apirestmuralvagas.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_usuarios") // Padrão de mercado usar "tb_" para tabelas
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório.")
    @Column(nullable = false)
    private String nome;

    @NotBlank(message = "O e-mail é obrigatório.")
    @Email(message = "O formato do e-mail é inválido.")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "O campo senha é obrigatório e não pode estar vazio.")
    @Column(nullable = false) // Removido o length=10 para suportar senhas encriptadas futuramente
    private String senha;

    @NotBlank(message = "O tipo de usuário é obrigatório.")
    @Column(nullable = false)
    private String tipo;

}
