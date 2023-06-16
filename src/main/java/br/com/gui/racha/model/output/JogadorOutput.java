package br.com.gui.racha.model.output;

import br.com.gui.racha.model.defaults.DefaultEntityDTO;
import br.com.gui.racha.model.entity.Jogador;
import br.com.gui.racha.model.entity.Posicao;
import br.com.gui.racha.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JogadorOutput extends DefaultEntityDTO {
    private Long id;
    private String nome;
    private Integer idade;
    private String telefone;
    private String cpf;
    private String email;
    private String posicao;
    private Integer gols;
    private Integer assistencias;

   public JogadorOutput(Jogador jogador) {
        this.id = jogador.getId();
        this.nome = jogador.getUser().getNome();
        this.idade = jogador.getUser().getIdade();
        this.telefone = jogador.getUser().getTelefone();
        this.cpf = jogador.getUser().getCpf();
        this.email = jogador.getUser().getCpf();
        this.posicao = jogador.getPosicao().getPosicao();
        this.gols = jogador.getGols();
        this.assistencias = jogador.getAssistencias();
        this.setActived(jogador.getActived());
        this.setCreated(jogador.getCreated());
        this.setUpdated(jogador.getUpdated());
    }

}
