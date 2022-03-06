package toyproject.juniorforum.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.util.UriComponentsBuilder;

@Data
public class Criteria {
    private int pageNum;
    private int amount;
    private int startNum;
//    private String keyword;
//    private String category;

    public Criteria() {
        this(1, 10);
    }

    public Criteria(int pageNum, int amount) {
        this.pageNum = pageNum;
        this.amount = amount;
        /**
         * if) pageNum = 1, startNum = 0, amount = 10
         * result = 1, 2, 3, 4, 5, 6, 7, 8, 9, 10
         * if) pageNum = 2, startNum = 10, amount = 10
         * result = 11, 12, 13, 14, 15, 16, 17, 18, 19, 20
         */
        this.startNum = (pageNum - 1) * amount;
    }

//    public String getListLink() {
//        return UriComponentsBuilder.fromPath("")
//                .queryParam("pageNum", pageNum)
//                .queryParam("amount", amount)
//                .toUriString();
//    }

    // 아직 이해 안
//    public String[] getTypeArr() {
//        return keyword == null ? new String[]{} : keyword.split("");
//    }
}
