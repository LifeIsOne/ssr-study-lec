package shop.mtcoding.blog.reply;

import jakarta.persistence.Transient;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.mtcoding.blog.user.User;

@Service
@RequiredArgsConstructor
public class ReplyService {
    private final ReplyJPARepository replyJPARepository;

    @Transactional
    public void 댓삭(int replyId, User sessionUser) {

        replyJPARepository.deleteById(replyId);
    }

}
