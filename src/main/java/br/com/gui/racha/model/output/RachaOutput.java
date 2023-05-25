package br.com.gui.racha.model.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RachaOutput {
    private Long id;
    private String nome;
    private Integer caixa;
    private QuadraOutput quadra;
}
