package br.com.gui.racha.model.output;

import br.com.gui.racha.model.defaults.DefaultEntityDTO;
import br.com.gui.racha.model.entity.Posicao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PosicaoOutput extends DefaultEntityDTO {
    private Long id;
    private String posicao;

    public PosicaoOutput(Posicao posicao) {
        this.id = posicao.getId();
        this.posicao = posicao.getPosicao();
        this.setActived(posicao.getActived());
        this.setCreated(posicao.getCreated());
        this.setUpdated(posicao.getUpdated());
    }

}
