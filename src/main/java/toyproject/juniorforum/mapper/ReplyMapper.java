package toyproject.juniorforum.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import toyproject.juniorforum.domain.DTO;
import toyproject.juniorforum.domain.Paging;
import toyproject.juniorforum.domain.VO;

import java.util.List;

import static toyproject.juniorforum.domain.DTO.*;
import static toyproject.juniorforum.domain.Paging.*;
import static toyproject.juniorforum.domain.VO.*;

@Repository
@Mapper
public interface ReplyMapper {

    int createReply(ReplyDTO replyDTO);
    ReplyVO readReply(int replyId);
    int updateReply(ReplyDTO replyDTO);
    int deleteReply(int reply_id);
    List<ReplyVO> getListWithReplyPaging(@Param("criteria") Criteria criteria, @Param("boardId") int boardId);
    int getReplyCount(int boardId);



}
