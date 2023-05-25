package br.com.gui.racha.model.input;

import br.com.gui.racha.model.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInput {

    private String nome;

    private Integer idade;

    private String telefone;

    private String email;

    private String username;

    private String password;

    private Long role;
}
