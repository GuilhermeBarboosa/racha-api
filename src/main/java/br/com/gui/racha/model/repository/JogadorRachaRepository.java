package br.com.gui.racha.model.repository;

import br.com.gui.racha.model.entity.JogadorRacha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JogadorRachaRepository extends JpaRepository<JogadorRacha, Long> {

    List<JogadorRacha> findAll();
    Optional<JogadorRacha> findById(Long id);

    @Query("select jr from JogadorRacha jr where jr.id = :id")
    Optional<JogadorRacha> findByIdDesactived(Long id);

    List<JogadorRacha> findByJogadorId(Long jogador);

    @Query("select jr from JogadorRacha jr where jr.jogador.id = :jogador")
    Optional<JogadorRacha> findByIdJogador(Long jogador);
}