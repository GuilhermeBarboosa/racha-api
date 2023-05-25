package br.com.gui.racha.model.input;

import br.com.gui.racha.model.entity.Racha;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JogoInput {
    private LocalDateTime data;

    private Integer valorPago;

    private Long racha;
}
