package br.com.gui.racha.model.entity;

import br.com.gui.racha.model.defaults.DefaultEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "posicao")
public class Posicao extends DefaultEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 50)
    @NotNull
    @Column(name = "posicao", nullable = false, length = 50)
    private String posicao;

}