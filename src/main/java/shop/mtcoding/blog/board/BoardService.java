package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog._core.errors.exception.Exception401;
import shop.mtcoding.blog._core.errors.exception.Exception403;
import shop.mtcoding.blog._core.errors.exception.Exception404;
import shop.mtcoding.blog.user.User;

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

    // 게시글 상세보기
    public Board 게상보(int boarId, User sessionUser) {
        Board board = boardJPARepository.findById(boarId)
                .orElseThrow(() -> new Exception401("찾는 게시글이 없습니다."));

        boolean isOwner = false;
        if(sessionUser != null){
            if(sessionUser.getId() == board.getUser().getId()){
                isOwner = true;
            }
        }
        board.setOwner(isOwner);

        // 댓글 추가
        board.getReplies();

        return board;
    }

    @Transactional
    public Board 게쓰(Board board){
        boardJPARepository.save(board);
        return board;
    }

    public Board 게조(int boardId){
        Board board = boardJPARepository.findById(boardId
        ).orElseThrow(() -> new Exception404("게시글이 없습니다."));
        return board;
    }

    @Transactional
    public Board 게수(int boardId, BoardRequest.UpdateDTO reqDTO, User sessionUser){
        // 게시물 조회를 계속한다.
        Board board = boardJPARepository.findById(boardId).orElseThrow(() -> new Exception404("찾을 수 없는 게시글 입니다."));

        if(sessionUser.getId() != board.getUser().getId()){
            throw new Exception403("게시글을 수정할 권한이 없습니다");
        }
        // 게시물 업데이트
        board.setTitle(reqDTO.getTitle());
        board.setContent(reqDTO.getContent());

        return board;
    } // 더티체킹

    @Transactional
    public void 게삭(int boardId, User sessionUser){

        Board board = boardJPARepository.findById(boardId).orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다."));

        if(sessionUser.getId() != board.getUser().getId()){
            throw new Exception403("게시글을 삭제할 권한이 없습니다");
        }

        boardJPARepository.deleteById(boardId);
    }
}
