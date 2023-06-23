package br.com.gui.racha.model.output;

import br.com.gui.racha.model.defaults.DefaultEntityDTO;
import br.com.gui.racha.model.entity.Jogo;
import br.com.gui.racha.model.entity.Racha;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JogoOutput extends DefaultEntityDTO {
    private Long id;
    private LocalDateTime data;
    private Integer valorPago;

    //    Racha
    private Long idRacha;
    private String nomeRacha;
    private Integer caixa;

    //    Quadra
    private Long idQuadra;
    private String nomeQuadra;
    private Integer valorQuadra;

    public JogoOutput(Jogo jogo){
        this.id = jogo.getId();
        this.data = jogo.getData();
        this.valorPago = jogo.getValorPago();
        this.idRacha = jogo.getRacha().getId();
        this.nomeRacha = jogo.getRacha().getNome();
        this.caixa = jogo.getRacha().getCaixa();
        this.idQuadra = jogo.getRacha().getQuadra().getId();
        this.nomeQuadra = jogo.getRacha().getQuadra().getNome();
        this.valorQuadra = jogo.getRacha().getQuadra().getValorQuadra();
        this.setActived(jogo.getActived());
        this.setCreated(jogo.getCreated());
        this.setUpdated(jogo.getUpdated());
    }


}
