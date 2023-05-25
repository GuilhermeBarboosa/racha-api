package br.com.gui.racha.model.repository;

import br.com.gui.racha.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    @Override
    @Query("select u from User u where u.actived = true")
    List<User> findAll();

    @Query("select u from User u where u.actived = true and u.id = :id")
    Optional<User> findById(Long id);

    @Query("select u from User u")
    List<User> findAllUser();

    @Query("select u from User u where u.id = :id")
    Optional<User> findByIdDesactived(Long id);
}
