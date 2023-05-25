package br.com.gui.racha.model.repository;

import br.com.gui.racha.model.entity.Quadra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuadraRepository extends JpaRepository<Quadra, Long> {


    @Override
    @Query("select q from Quadra q where q.actived = true")
    List<Quadra> findAll();

    @Query("select q from Quadra q where q.actived = true and q.id = :id")
    Optional<Quadra> findById(Long id);

    @Query("select q from Quadra q")
    List<Quadra> findAllQuadra();

    @Query("select q from Quadra q where q.id = :id")
    Optional<Quadra> findByIdDesactived(Long id);
}
