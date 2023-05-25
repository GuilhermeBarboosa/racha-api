package br.com.gui.racha.model.repository;

import br.com.gui.racha.model.entity.Racha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RachaRepository extends JpaRepository<Racha, Long> {


    @Override
    @Query("select r from Racha r where r.actived = true")
    List<Racha> findAll();

    @Query("select r from Racha r where r.actived = true and r.id = :id")
    Optional<Racha> findById(Long id);

    @Query("select r from Racha r")
    List<Racha> findAllQuadra();

    @Query("select r from Racha r where r.id = :id")
    Optional<Racha> findByIdDesactived(Long id);
}
