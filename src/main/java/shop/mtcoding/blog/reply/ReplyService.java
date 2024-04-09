package shop.mtcoding.blog.reply;

import jakarta.persistence.Transient;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.mtcoding.blog._core.errors.exception.Exception403;
import shop.mtcoding.blog._core.errors.exception.Exception404;
import shop.mtcoding.blog.user.User;

@Service
@RequiredArgsConstructor
public class ReplyService {
    private final ReplyJPARepository replyJPARepository;

    @Transactional
    public void 댓삭(int replyId, User sessionUser) {
        // 댓글 조회
        Reply reply = replyJPARepository.findById(replyId)
                        .orElseThrow(() -> new Exception404("댓글이 없는데요?"));
        // 삭제 권한 조회
        if (reply.getUser().getId() != sessionUser.getId()){
            throw new Exception403("댓글을 삭제할 권한이 없어요~");
        }

        replyJPARepository.deleteById(replyId);
    }

}
