package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog._core.errors.exception.Exception401;

import java.util.List;

@RequiredArgsConstructor
@Repository // new BoardRepository() -> IoC 컨테이너 등록
public class BoardService {
    private final EntityManager em;
    private final BoardJPARepository boardJPARepository;

    public List<Board> 게목보() {
        List<Board> boardList = boardJPARepository.findAll();
        return boardList;
    }

    public Board 게상보(int boarId) {
        Board board = boardJPARepository.findById(boarId).orElseThrow(() -> new Exception401("찾는 게시글이 없습니다."));
        return board;
    }

//    public List<Board> findAll() {
//
//        Query query = em.createQuery("select b from Board b order by b.id desc", Board.class);

//        return query.getResultList();
//    }

    @Transactional
    public Board updateById(int id, String title, String content){
        Board board = 게상보(id);
        board.setTitle(title);
        board.setContent(content);
        return board;
    } // 더티체킹

    @Transactional
    public void deleteById(int id){
        Query query = em.createQuery("delete from Board b where b.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Transactional
    public Board save(Board board){
        em.persist(board);
        return board;
    }

    public Board findByIdJoinUser(int id) {
        Query query = em.createQuery("select b from Board b join fetch b.user u where b.id = :id", Board.class);
        query.setParameter("id", id);
        return (Board) query.getSingleResult();
    }
}
