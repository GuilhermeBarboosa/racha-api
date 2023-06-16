package br.com.gui.racha.model.output;

import br.com.gui.racha.model.defaults.DefaultEntityDTO;
import br.com.gui.racha.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserOutput extends DefaultEntityDTO {
    private Long id;
    private String nome;
    private Integer idade;
    private String telefone;
    private String cpf;
    private String email;
    private String senha;

    private Long idRole;
    private String role;

    public UserOutput(User user) {
        this.id = user.getId();
        this.nome = user.getNome();
        this.idade = user.getIdade();
        this.telefone = user.getTelefone();
        this.cpf = user.getCpf();
        this.email = user.getEmail();
        this.senha = user.getSenha();
        this.idRole = user.getRole().getId();
        this.role = user.getRole().getRole();
        this.setActived(user.getActived());
        this.setCreated(user.getCreated());
        this.setUpdated(user.getUpdated());
    }

}
