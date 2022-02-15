package toyProject.juniorForum.model;

import lombok.Data;

@Data
public class Post {
    private long id;
    private String postName;
    private String writer;
    private int postRead;

    public Post(String postName, String writer) {
        this.postName = postName;
        this.writer = writer;
        this.postRead = 0;
    }
}
