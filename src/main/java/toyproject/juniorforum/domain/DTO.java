package toyproject.juniorforum.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

import static toyproject.juniorforum.domain.Paging.*;
import static toyproject.juniorforum.domain.VO.*;

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

    @Data
    public static class ReplyDTO {
        @NotNull
        private int boardId;

        private int replyId;

        @NotEmpty
        private String writer;
        @NotEmpty
        private String content;
    }

    @Data
    @AllArgsConstructor
    public static class ReplyPagingDTO {
        List<ReplyVO> list;

        PageDTO pageDTO;
    }
}
