package toyProject.juniorForum.domain.post;

import org.springframework.stereotype.Repository;
import toyProject.juniorForum.domain.post.Post;

import java.util.ArrayList;
import java.util.List;
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

    public Post findById(Long postId) {
        return posts.get(postId);
    }

    public List<Post> findAll() {
        return new ArrayList<>(posts.values());
    }

    public void delete(Long postId) {
        posts.remove(postId);
    }
}
