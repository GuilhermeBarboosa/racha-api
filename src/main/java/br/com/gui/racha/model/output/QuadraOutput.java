package br.com.gui.racha.model.output;

import br.com.gui.racha.model.defaults.DefaultEntityDTO;
import br.com.gui.racha.model.entity.Quadra;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuadraOutput extends DefaultEntityDTO {
    private Long id;
    private String nome;

    private Integer valorQuadra;
    public QuadraOutput(Quadra quadra) {
        this.id = quadra.getId();
        this.nome = quadra.getNome();
        this.valorQuadra = quadra.getValorQuadra();
        this.setActived(quadra.getActived());
        this.setCreated(quadra.getCreated());
        this.setUpdated(quadra.getUpdated());
    }

}
