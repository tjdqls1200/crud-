package toyProject.juniorForum;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import toyProject.juniorForum.domain.post.Post;
import toyProject.juniorForum.domain.post.PostRepository;

import javax.annotation.PostConstruct;

@Slf4j
@Component
@RequiredArgsConstructor
public class TestDataInit {

    //private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        postRepository.save(new Post("test1", "lee"));
        postRepository.save(new Post("test2", "kim"));
        postRepository.save(new Post("test3", "Jun"));
        log.info("Success PostConstruct");
    }
}