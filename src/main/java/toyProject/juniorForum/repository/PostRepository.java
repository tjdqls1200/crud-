package toyProject.juniorForum.repository;

import org.springframework.stereotype.Repository;
import toyProject.juniorForum.model.Post;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class PostRepository {
    private static final Map<Long, Post> posts = new ConcurrentHashMap<>();
    private static long sequence = 0L;

    public Post save(Post post) {
        post.setId(++sequence);
        posts.put(post.getId(), post);
        return post;
    }
}
