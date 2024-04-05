package shop.mtcoding.blog.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog._core.errors.exception.Exception400;
import shop.mtcoding.blog._core.errors.exception.Exception401;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class UserService {
    private final EntityManager em; // DI
    private final UserJPARepository userJPARepository;


    public User 로그인(String username, String password){
        User sessionUser = userJPARepository.login(username, password).orElseThrow(() -> new Exception401("가입되지 않은 아이디 이거나, 비밀번호를 틀렸습니다."));

        return sessionUser;
    }

    @Transactional
    public User 회원가입(UserRequest.JoinDTO reqDTO){

        Optional<User> usernameCheck = userJPARepository.findByUsername(reqDTO.getUsername());
        if (usernameCheck.isPresent())
            throw new Exception400("동일한 유저네임이 존재합니다");
        // 패스워드 X

        User user = userJPARepository.save(reqDTO.toEntity());

        return user;
    }

    @Transactional
    public User 회원정보수정(int sessionUserId, UserRequest.UpdateDTO reqDTO){
        User user = userJPARepository.findById(sessionUserId).orElseThrow(() -> new Exception401("회원 정보가 이상합니다."));

        user.setPassword(reqDTO.getPassword());
        user.setEmail(reqDTO.getEmail());
        return user;
    }

    public User findById(int id) {
        User user = em.find(User.class, id);
        return user;
    }



}
