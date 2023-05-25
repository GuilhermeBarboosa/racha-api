package br.com.gui.racha.model.input;

import br.com.gui.racha.model.entity.Posicao;
import br.com.gui.racha.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JogadorInput {
    private Long user;

    private Long posicao;

    private Integer gols;

    private Integer assitencias;
}
