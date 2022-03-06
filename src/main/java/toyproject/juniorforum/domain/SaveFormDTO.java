package toyproject.juniorforum.domain;

import lombok.Data;
import javax.validation.constraints.NotEmpty;

@Data
public class SaveFormDTO {
    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

}
