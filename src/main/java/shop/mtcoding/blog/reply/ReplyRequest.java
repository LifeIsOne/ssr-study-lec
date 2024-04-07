package shop.mtcoding.blog.reply;


import lombok.Data;

public class ReplyRequest {

    @Data
    public static class SaveDTO {
        private String content;
        private int boardId;

    }
}
