package br.com.gui.racha.model.input;

import br.com.gui.racha.model.entity.Jogador;
import br.com.gui.racha.model.entity.Racha;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JogadorRachaInput {
    private Long jogador;

    private Long racha;
}
