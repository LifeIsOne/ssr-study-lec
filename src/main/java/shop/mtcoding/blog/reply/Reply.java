package shop.mtcoding.blog.reply;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.mtcoding.blog.board.Board;
import shop.mtcoding.blog.user.User;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@Table(name = "reply_tb")
@Entity
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT 전략
    private Integer id;

    private String comment;
    private Timestamp createdAt;

    @ManyToOne
    private Board board;    // 여러개의 댓글이 하나의 게시글에

    @ManyToOne
    private User user;      // 여러개의 댓글이 한명의 유저에

    @Transient
    private boolean isReplyOwner;   // 댓글 주인만 삭제

    @Builder
    public Reply(Integer id, String comment, User user, Board board, Timestamp createdAt) {
        this.id = id;
        this.comment = comment;
        this.user = user;
        this.board = board;
        this.createdAt = createdAt;
    }
}