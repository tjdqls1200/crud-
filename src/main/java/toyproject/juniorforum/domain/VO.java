package toyproject.juniorforum.domain;

import lombok.*;
import org.apache.tomcat.jni.Local;
import org.springframework.format.annotation.DateTimeFormat;
import toyproject.juniorforum.domain.DTO.BoardDTO;
import toyproject.juniorforum.domain.DTO.BoardUpdateForm;

import java.time.LocalDateTime;

public class VO {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BoardVO {
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
                    .hit(this.getHit())
                    .build();
        }
    }

    @Data
    public static class ReplyVO {
        private int replyId;
        private int boardId;
        private String writer;
        private String content;

        private LocalDateTime createTime;

        private LocalDateTime updateTime;
    }
}