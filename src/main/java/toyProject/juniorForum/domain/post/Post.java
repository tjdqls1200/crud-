package toyProject.juniorForum.domain.post;

import lombok.Data;

@Data
public class Post {
    private long id;
    private String title;
    private String writer;
    private int postRead;

    public Post() {

    }

    public Post(String title, String writer) {
        this.title = title;
        this.writer = writer;
        this.postRead = 0;
    }
}
