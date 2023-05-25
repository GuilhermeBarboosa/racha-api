package br.com.gui.racha.model.repository;

import br.com.gui.racha.model.entity.Jogo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JogoRepository extends JpaRepository<Jogo, Long> {


    @Override
    @Query("select j from Jogo j where j.actived = true")
    List<Jogo> findAll();

    @Query("select j from Jogo j where j.actived = true and j.id = :id")
    Optional<Jogo> findById(Long id);

    @Query("select j from Jogo j")
    List<Jogo> findAllJogos();

    @Query("select j from Jogo j where j.id = :id")
    Optional<Jogo> findByIdDesactived(Long id);
}
