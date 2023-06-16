package br.com.gui.racha.model.output;

import br.com.gui.racha.model.defaults.DefaultEntityDTO;
import br.com.gui.racha.model.entity.Racha;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RachaOutput extends DefaultEntityDTO {
    private Long id;
    private String nome;
    private Integer caixa;
    private Long idQuadra;
    private String nomeQuadra;

    public RachaOutput(Racha racha){
        this.id = racha.getId();
        this.nome = racha.getNome();
        this.caixa = racha.getCaixa();
        this.idQuadra = racha.getQuadra().getId();
        this.nomeQuadra = racha.getQuadra().getNome();
        this.setActived(racha.getActived());
        this.setCreated(racha.getCreated());
        this.setUpdated(racha.getUpdated());
    }
}
