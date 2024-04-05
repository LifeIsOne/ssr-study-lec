package shop.mtcoding.blog.reply;

import jakarta.persistence.*;
import shop.mtcoding.blog.board.Board;
import shop.mtcoding.blog.user.User;

import java.sql.Timestamp;

@Table(name = "reply_tb")
@Entity
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT 전략
    private int id;

    private String comment;
    private Timestamp createdAt;

    @ManyToOne
    private Board board;    // 여러개의 댓글이 하나의 게시글에

    @ManyToOne
    private User user;      // 여러개의 댓글이 한명의 유저에
}