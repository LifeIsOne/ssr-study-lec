package shop.mtcoding.blog.reply;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.blog.user.User;

@Controller
@RequiredArgsConstructor
public class ReplyController {
    private final ReplyService replyService;
    private final HttpSession session;

    @PostMapping("/reply/{replyId}/delete")
    public String delete(@PathVariable int replyId) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        replyService.댓삭(replyId, sessionUser);

        return "redirect:/";
    }

    @PostMapping("/reply/save")
    public String save(ReplyRequest.SaveDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        replyService.댓쓰(reqDTO, sessionUser);
        return "redirect:/";
    }

}
