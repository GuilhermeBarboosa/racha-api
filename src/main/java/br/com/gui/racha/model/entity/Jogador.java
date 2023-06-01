package br.com.gui.racha.model.entity;

import br.com.gui.racha.model.defaults.DefaultEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@ToString
@Table(name = "jogador")
public class Jogador extends DefaultEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user", nullable = false, referencedColumnName = "id")
    private User user;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "posicao", nullable = false)
    private Posicao posicao;

    @NotNull
    @Column(name = "gols", nullable = false)
    private Integer gols;

    @NotNull
    @Column(name = "assitencias", nullable = false)
    private Integer assitencias;

    @OneToMany(mappedBy = "jogador")
    private Set<JogadorRacha> jogadorRachas = new LinkedHashSet<>();

}