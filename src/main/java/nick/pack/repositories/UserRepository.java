package nick.pack.repositories;

import nick.pack.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<Users, Integer> {
    @Query("SELECT u FROM users u where u.username = :username")
    Users findByName(@Param("username") String username);
}
