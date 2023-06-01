package br.com.gui.racha.model.output;

import br.com.gui.racha.model.defaults.DefaultEntityDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JogadorOutput extends DefaultEntityDTO {
    private Long id;
    private UserOutput user;
    private PosicaoOutput posicao;
    private Integer gols;
    private Integer assitencias;

}
