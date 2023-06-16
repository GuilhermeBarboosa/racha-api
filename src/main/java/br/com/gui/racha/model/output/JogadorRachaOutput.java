package br.com.gui.racha.model.output;

import br.com.gui.racha.model.defaults.DefaultEntityDTO;
import br.com.gui.racha.model.entity.Jogador;
import br.com.gui.racha.model.entity.JogadorRacha;
import br.com.gui.racha.model.entity.Racha;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JogadorRachaOutput extends DefaultEntityDTO {
    private Long id;
    private Long idJogador;
    private String nomeJogador;
    private Integer idade;
    private String telefone;
    private String cpf;
    private String email;
    private String posicao;
    private Integer gols;
    private Integer assistencias;
    //    Racha
    private Long idRacha;
    private String nomeRacha;
    private Integer caixa;
    //    Quadra
    private Long idQuadra;
    private String nomeQuadra;

    public JogadorRachaOutput(JogadorRacha jogadorRacha) {
        this.id = jogadorRacha.getId();
        this.idJogador = jogadorRacha.getJogador().getId();
        this.nomeJogador = jogadorRacha.getJogador().getUser().getNome();
        this.idade = jogadorRacha.getJogador().getUser().getIdade();
        this.telefone = jogadorRacha.getJogador().getUser().getTelefone();
        this.cpf = jogadorRacha.getJogador().getUser().getCpf();
        this.email = jogadorRacha.getJogador().getUser().getEmail();
        this.posicao = jogadorRacha.getJogador().getPosicao().getPosicao();
        this.gols = jogadorRacha.getJogador().getGols();
        this.assistencias = jogadorRacha.getJogador().getAssistencias();
        this.idRacha = jogadorRacha.getRacha().getId();
        this.nomeRacha = jogadorRacha.getRacha().getNome();
        this.caixa = jogadorRacha.getRacha().getCaixa();
        this.idQuadra = jogadorRacha.getRacha().getQuadra().getId();
        this.nomeQuadra = jogadorRacha.getRacha().getQuadra().getNome();
        this.setActived(jogadorRacha.getActived());
        this.setCreated(jogadorRacha.getCreated());
        this.setUpdated(jogadorRacha.getUpdated());
    }
}
