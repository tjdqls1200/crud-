package toyproject.juniorforum.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardDTO {
    private int boardId;
    private String title;
    private String writer;
    private String content;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private int hit;
}
