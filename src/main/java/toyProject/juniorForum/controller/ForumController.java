package toyProject.juniorForum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ForumController {
    @GetMapping("/forum")
    public String forum() {
        return "forum";
    }
}
