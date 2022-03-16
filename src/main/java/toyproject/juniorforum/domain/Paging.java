package toyproject.juniorforum.domain;

import lombok.Data;
import org.springframework.web.util.UriComponentsBuilder;

public class Paging {

    @Data
    public static class PageDTO {
        private int pageSize;
        private int startPage;
        private int endPage;
        private int totalEndPage;
        private boolean prev, next;
        private int totalCount;
        private Criteria criteria;

        public PageDTO(int pageSize, int totalCount, Criteria criteria) {
            this.pageSize = pageSize;
            this.totalCount = totalCount;
            this.criteria = criteria;

            // if) pageNum = 25 -> Math.ceil(2.5) * 10 -> 3 * 10 = 30
            endPage = (int) (Math.ceil(criteria.pageNum / (double) pageSize)) * pageSize;
            startPage = endPage - (pageSize - 1);

            totalEndPage = (int) (Math.ceil((double) totalCount / criteria.amount));

            if (totalEndPage < endPage) {
                endPage = totalEndPage;
            }

            prev = startPage > 1;
            next = endPage < totalEndPage;
        }
    }

    @Data
    public static class Criteria {
        private int pageNum;
        private int amount;
        private int startNum;
        private String type;
        private String keyword;

        public Criteria() {
            this(1, 10);
        }

        public Criteria(int pageNum, int amount) {
            this.pageNum = pageNum;
            this.amount = amount;
        }

        public void calculateStarNum() {
            /**
             * if) pageNum = 1, startNum = 0, amount = 10
             * result = 1, 2, 3, 4, 5, 6, 7, 8, 9, 10
             * if) pageNum = 2, startNum = 10, amount = 10
             * result = 11, 12, 13, 14, 15, 16, 17, 18, 19, 20
             */
            this.startNum = (this.pageNum - 1) * this.amount;
        }

        public String getListLink() {
            return UriComponentsBuilder.fromPath("")
                    .queryParam("pageNum", this.pageNum)
                    .queryParam("amount", this.amount)
                    .toUriString();
        }

        public String[] getTypeArr() {
            return type == null ? new String[]{} : type.split("");
        }
    }
}
