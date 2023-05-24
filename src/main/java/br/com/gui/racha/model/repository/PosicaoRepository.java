package br.com.gui.racha.model.repository;

import br.com.gui.racha.model.entity.Posicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PosicaoRepository extends JpaRepository<Posicao, Long> {

    @Override
    @Query("select p from Posicao p where p.actived = true")
    List<Posicao> findAll();

    @Query("select p from Posicao p where p.actived = true and p.id = :id")
    Optional<Posicao> findById(Long id);

    @Query("select p from Posicao p")
    List<Posicao> findAllPosicao();

    @Query("select p from Posicao p where p.id = :id")
    Optional<Posicao> findByIdDesactived(Long id);
}
