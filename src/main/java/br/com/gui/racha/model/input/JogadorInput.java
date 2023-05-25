package br.com.gui.racha.model.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JogadorInput {
    private Long user;

    private Long posicao;

    private Integer gols;

    private Integer assitencias;
}
