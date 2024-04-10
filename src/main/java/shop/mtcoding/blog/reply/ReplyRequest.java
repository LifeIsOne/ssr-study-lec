package shop.mtcoding.blog.reply;


import lombok.Data;
import shop.mtcoding.blog.board.Board;
import shop.mtcoding.blog.user.User;

public class ReplyRequest {

    @Data
    public static class SaveDTO {
        private String comment;
        private Integer boardId;

        public Reply toEntity(User sessionUser, Board board) {
            return Reply.builder()
                    .board(board)
                    .user(sessionUser)
                    .comment(comment)
                    .build();
        }
    }
}
