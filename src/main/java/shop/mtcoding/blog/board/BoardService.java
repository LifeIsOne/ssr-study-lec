package shop.mtcoding.blog.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog._core.errors.exception.Exception403;
import shop.mtcoding.blog._core.errors.exception.Exception404;
import shop.mtcoding.blog.user.User;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardJPARepository boardJPARepository;

    public Board 게조(int boardId){
        Board board = boardJPARepository.findById(boardId)
                .orElseThrow(() -> new Exception404("게시글이 없습니다."));
        return board;
    }

    public List<Board> 게목보() {
        List<Board> boardList = boardJPARepository.findAll();
        return boardList;
    }
    // 게시글 상세보기

    public Board 게상보(int boardId, User sessionUser) {
        Board board = boardJPARepository.findByIdJoinUser(boardId)
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다"));

        boolean isBoardOwner = false;
        if(sessionUser != null){
            if(sessionUser.getId() == board.getUser().getId()){
                isBoardOwner = true;
            }
        }

        board.setBoardOwner(isBoardOwner);

        board.getReplies().forEach(reply -> {
            boolean isReplyOwner = false;

            if(sessionUser != null){
                if(reply.getUser().getId() == sessionUser.getId()){
                    isReplyOwner = true;
                }
            }
            reply.setReplyOwner(isReplyOwner);
        });
        return board;
    }

    @Transactional
    public void 게쓰(BoardRequest.SaveDTO reqDTO, User sessionUser) {
        boardJPARepository.save(reqDTO.toEntity(sessionUser));
    }

    @Transactional
    public void 게수(int boardId, BoardRequest.UpdateDTO reqDTO, User sessionUser){
        // 게시물 조회를 계속한다.
        Board board = boardJPARepository.findById(boardId)
                .orElseThrow(() -> new Exception404("찾는 게시글이 없습니다."));

        if(sessionUser.getId() != board.getUser().getId()){
            throw new Exception403("게시글을 수정할 권한이 없습니다");
        }
        // 게시물 수정
        board.setTitle(reqDTO.getTitle());
        board.setContent(reqDTO.getContent());
    } // 더티체킹

    @Transactional
    public void 게삭(int boardId, User sessionUser){

        Board board = boardJPARepository.findById(boardId)
                .orElseThrow(() -> new Exception404("찾는 게시글이 없습니다."));

        if(sessionUser.getId() != board.getUser().getId()){
            throw new Exception403("게시글을 삭제할 권한이 없습니다");
        }

        boardJPARepository.deleteById(boardId);
    }
}
