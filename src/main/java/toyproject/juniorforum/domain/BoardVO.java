package toyproject.juniorforum.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class BoardVO {
    private int boardId;
    private String title;
    private String writer;
    private String content;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private int hit;

    public BoardDTO convertToDTO() {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setBoardId(this.getBoardId());
        boardDTO.setTitle(this.getTitle());
        boardDTO.setWriter(this.getContent());
        boardDTO.setContent(this.getContent());
        boardDTO.setCreateTime(this.getCreateTime());
        boardDTO.setUpdateTime(this.getUpdateTime());
        return boardDTO;
    }


}
