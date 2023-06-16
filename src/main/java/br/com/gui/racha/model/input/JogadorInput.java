package br.com.gui.racha.model.input;

import br.com.gui.racha.model.entity.Jogador;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class JogadorInput {
    private Long user;

    private Long posicao;

    private Integer gols;

    private Integer assistencias;


}
