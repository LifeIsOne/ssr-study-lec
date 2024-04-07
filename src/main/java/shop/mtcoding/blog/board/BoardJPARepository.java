package shop.mtcoding.blog.board;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.mtcoding.blog.user.User;

public interface BoardJPARepository extends JpaRepository<Board, Integer> {

}
