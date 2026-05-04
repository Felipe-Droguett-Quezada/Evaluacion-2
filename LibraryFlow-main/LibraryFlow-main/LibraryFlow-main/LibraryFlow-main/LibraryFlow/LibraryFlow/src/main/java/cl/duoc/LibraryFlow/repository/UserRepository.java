package cl.duoc.LibraryFlow.repository;

import org.springframework.stereotype.Repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import cl.duoc.LibraryFlow.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    @param id
    @return
    Optional<User> findById(Long id);

    @param id
    @return
    boolean existsById(String id);

    @param id
    @param nombre_completo
    @return 

    boolean existsByIdAndIdNot(Long id, String fullName);

}
