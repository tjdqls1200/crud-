package toyproject.juniorforum.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
public class BoardSaveForm {
    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

}
