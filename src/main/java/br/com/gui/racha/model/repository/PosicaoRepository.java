package br.com.gui.racha.model.repository;

import br.com.gui.racha.model.entity.Posicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PosicaoRepository extends JpaRepository<Posicao, Long> {

    List<Posicao> findAll();

    Optional<Posicao> findById(Long id);

    @Query("select p from Posicao p where p.id = :id")
    Optional<Posicao> findByIdDesactived(Long id);

    @Query("select p from Posicao p where p.posicao like %:posicao%")
    List<Posicao> findAllDesc(String posicao);
}
