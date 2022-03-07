package toyproject.juniorforum.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

public class DTO {
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BoardDTO {
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

    @Data
    public static class BoardSaveForm {
        @NotEmpty
        private String title;

        @NotEmpty
        private String content;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BoardUpdateForm {
        private int boardId;

        @NotEmpty
        private String title;

        @NotEmpty
        private String content;
    }
}
