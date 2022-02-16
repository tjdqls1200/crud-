package toyProject.juniorForum.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import toyProject.juniorForum.domain.post.Post;
import toyProject.juniorForum.domain.post.PostRepository;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final PostRepository postRepository;

    @GetMapping("/posts")
    public String list(Model model) {
        List<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "board/posts";
    }

    @GetMapping("/post")
    public String addForm(Model model) {
        Post post = new Post();
        model.addAttribute("post", post);
        return "board/addPost";
    }

    @PostMapping("/post")
    public String addPost(@ModelAttribute Post post, Model model) {
        return "board/post";
    }
}
