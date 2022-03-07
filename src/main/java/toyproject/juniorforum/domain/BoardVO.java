package toyproject.juniorforum.domain;

import lombok.*;
import toyproject.juniorforum.domain.DTO.BoardDTO;
import toyproject.juniorforum.domain.DTO.BoardUpdateForm;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class BoardVO {
    private int boardId;
    private String title;
    private String writer;
    private String content;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private int hit;


    public BoardUpdateForm convertToUpdateDTO() {
        return BoardUpdateForm.builder()
                .boardId(this.getBoardId())
                .title(this.getTitle())
                .content(this.getContent())
                .build();
    }

    public BoardDTO convertDTO() {
        return BoardDTO.builder()
                .boardId(this.getBoardId())
                .title(this.getTitle())
                .writer(this.getWriter())
                .content(this.getContent())
                .createTime(this.getCreateTime())
                .updateTime(this.getUpdateTime())
                .build();
    }
}
