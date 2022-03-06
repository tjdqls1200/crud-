package toyproject.juniorforum.domain;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@Builder
public class BoardDTO {
    private int boardId;

    @NotEmpty
    private String title;
    @NotEmpty
    private String content;
    private String writer;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private int hit;
}