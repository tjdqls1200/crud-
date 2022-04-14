package toyproject.juniorforum.mapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static toyproject.juniorforum.domain.DTO.*;
import static toyproject.juniorforum.domain.Paging.*;
import static toyproject.juniorforum.domain.VO.*;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Slf4j
public class ReplyMapperTest {
    @Autowired
    private ReplyMapper replyMapper;

    @DisplayName("댓글 생성")
    @Test
    void create() {
        //given
        ReplyDTO replyDTO = new ReplyDTO();
        replyDTO.setWriter("test writer");
        replyDTO.setContent("test reply");
        replyDTO.setBoardId(3);

        //when
        int result = replyMapper.createReply(replyDTO);

        //then
        assertThat(result).isEqualTo(1);
    }

    @DisplayName("댓글 읽기")
    @Test
    void read() {
        //given
        ReplyDTO replyDTO = new ReplyDTO();
        replyDTO.setWriter("test writer");
        replyDTO.setContent("test reply");
        replyDTO.setBoardId(3);
        replyMapper.createReply(replyDTO);

        //when
        ReplyVO replyVO = replyMapper.readReply(replyDTO.getReplyId());

        //then
        assertThat(replyVO.getContent()).isEqualTo("test reply");
    }


    @DisplayName("댓글 개수 조회")
    @Test
    void getCount() {

        //given
        for (int i = 0; i < 20; i++) {
            ReplyDTO replyDTO = new ReplyDTO();
            replyDTO.setBoardId(3);
            replyDTO.setWriter("test writer");
            replyDTO.setContent("test content");
            replyMapper.createReply(replyDTO);
        }

        //when
        int replyCount = replyMapper.getReplyCount(3);

        assertThat(replyCount).isEqualTo(20);
    }

    @DisplayName("댓글 삭제")
    @Test
    void delete() {
        //given
        ReplyDTO replyDTO = new ReplyDTO();
        replyDTO.setWriter("test writer ");
        replyDTO.setContent("test reply ");
        replyDTO.setBoardId(3);
        replyMapper.createReply(replyDTO);

        //when
        replyMapper.deleteReply(replyDTO.getReplyId());

        // 삭제 후에는 댓글 개수 0
        assertThat(replyMapper.getReplyCount(3)).isEqualTo(0);
    }

    @DisplayName("댓글 수정")
    @Test
    void update() {
        //given
        ReplyDTO replyDTO = new ReplyDTO();
        replyDTO.setWriter("test writer");
        replyDTO.setContent("test reply");
        replyDTO.setBoardId(3);
        replyMapper.createReply(replyDTO);

        //when
        replyDTO.setContent("update reply");
        replyMapper.updateReply(replyDTO);

        //then
        assertThat(replyMapper.readReply(replyDTO.getReplyId()).getContent()).isEqualTo("update reply");
    }

    @DisplayName("댓글 페이징")
    @Test
    void replyPaging() {
        //given
        for (int i = 1; i <= 20; i++) {
            ReplyDTO replyDTO = new ReplyDTO();
            replyDTO.setBoardId(3);
            replyDTO.setWriter("test writer" + i);
            replyDTO.setContent("test content" + i);
            replyMapper.createReply(replyDTO);
        }
        //when
        Criteria criteria = new Criteria(2, 10);
        criteria.calculateStarNum();
        List<ReplyVO> replyList = replyMapper.getListWithReplyPaging(criteria, 3);

        //then
        assertThat(replyList.size()).isEqualTo(10);
        assertThat(replyList.get(0).getContent()).isEqualTo("test content11");
        assertThat(replyList.get(9).getContent()).isEqualTo("test content20");
    }
}
