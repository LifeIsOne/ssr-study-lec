package shop.mtcoding.blog.reply;


import lombok.Data;
import shop.mtcoding.blog.board.Board;
import shop.mtcoding.blog.user.User;

public class ReplyRequest {

    @Data
    public static class SaveDTO {
        private String commnet;
        private int boardId;

        public Reply toEntity(Board board) {
            return Reply.builder()
                    .board(board)
                    .comment(commnet)
                    .build();
        }
    }
}
