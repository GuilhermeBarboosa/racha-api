package br.com.gui.racha.model.repository;

import br.com.gui.racha.model.entity.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JogadorRepository extends JpaRepository<Jogador, Long> {

    List<Jogador> findAll();

    Optional<Jogador> findById(Long id);

    @Query("select j from Jogador j where j.id = :id")
    Optional<Jogador> findByIdDesactived(Long id);

    Optional<Jogador> findByUserId(Long user);
}