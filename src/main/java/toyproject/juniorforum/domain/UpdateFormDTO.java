package toyproject.juniorforum.domain;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class UpdateFormDTO {
    private int boardId;

    @NotEmpty
    private String title;

    @NotEmpty
    private String content;
}
