package br.com.gui.racha.model.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RachaInput {
    private String nome;
    private Integer caixa;
    private Long quadra;
}
