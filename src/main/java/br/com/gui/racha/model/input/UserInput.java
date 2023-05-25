package br.com.gui.racha.model.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
