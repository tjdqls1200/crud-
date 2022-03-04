package toyproject.juniorforum.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
    private int boardId;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private int hit;
}
