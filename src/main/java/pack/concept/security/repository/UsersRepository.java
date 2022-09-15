package pack.concept.security.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import pack.concept.security.model.UsersEntity;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, Long> {
    Optional<UsersEntity> findByUsername(String username);
    Boolean existsByUsername(String username);
    Optional<UsersEntity> findByPassword(String password);
}
