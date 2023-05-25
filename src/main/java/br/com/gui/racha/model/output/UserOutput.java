package br.com.gui.racha.model.output;

import br.com.gui.racha.model.defaults.DefaultEntityDTO;
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
    private String email;
    private String username;
    private String password;
    private RoleOutput role;

}
