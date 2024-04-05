package shop.mtcoding.blog.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserJPARepository extends JpaRepository<User, Integer> {

    @Query("""
        SELECT u
        FROM User u
        WHERE u.username = :username
        AND u.password = :password
        """)
    Optional<User> login(@Param("username") String username, @Param("password") String password);
}