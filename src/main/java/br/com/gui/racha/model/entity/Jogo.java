package br.com.gui.racha.model.entity;

import br.com.gui.racha.model.defaults.DefaultEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "jogo")
public class Jogo  extends DefaultEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "data", nullable = false)
    private LocalDateTime data;

    @NotNull
    @Column(name = "valor_pago", nullable = false)
    private Integer valorPago;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "racha", nullable = false)
    private Racha racha;


}